import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        // litterId[r][c] = index of litter at this cell
        int[][] litterId = new int[m][n];

        // Find starting position and number every litter
        int startR = 0;
        int startC = 0;
        int litterCount = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    startR = r;
                    startC = c;
                }

                else if (ch == 'L') {
                    litterId[r][c] = litterCount;
                    litterCount++;
                }
            }
        }

        // No litter
        if (litterCount == 0) {
            return 0;
        }

        /*
         * If there are k litter cells:
         *
         * Number of possible masks = 2^k
         *
         * Example:
         * k = 3
         *
         * 000 -> nothing collected
         * 001 -> litter 0 collected
         * 010 -> litter 1 collected
         * 111 -> all collected
         */
        int totalMasks = 1 << litterCount;

        /*
         * Initial mask:
         *
         * 111...111
         *
         * We use 1 to mean:
         * "this litter still needs to be collected"
         *
         * When we collect litter i, we turn its bit OFF.
         *
         * So:
         *
         * 111 -> all remaining
         * 101 -> litter 1 collected
         * 000 -> everything collected
         */
        int initialMask = totalMasks - 1;

        /*
         * visited[row][col][energy][mask]
         *
         * A state is completely determined by:
         *
         * 1. Current row
         * 2. Current column
         * 3. Remaining energy
         * 4. Remaining litter mask
         */
        boolean[][][][] visited =
                new boolean[m][n][energy + 1][totalMasks];

        /*
         * State:
         *
         * [row, column, currentEnergy, mask, moves]
         */
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[] {
                startR,
                startC,
                energy,
                initialMask,
                0
        });

        visited[startR][startC][energy][initialMask] = true;

        // Four directions
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];
            int currentEnergy = current[2];
            int mask = current[3];
            int moves = current[4];

            // All litter collected
            if (mask == 0) {
                return moves;
            }

            /*
             * If energy is 0, we cannot make another move.
             *
             * The only way to continue is to have reached R,
             * which would already have reset our energy.
             */
            if (currentEnergy == 0) {
                continue;
            }

            // Try four directions
            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // Outside grid
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                // Obstacle
                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                char nextCell = classroom[nr].charAt(nc);

                /*
                 * Normally:
                 *
                 * newEnergy = currentEnergy - 1
                 *
                 * But if we enter R:
                 *
                 * newEnergy = maximum energy
                 */
                int newEnergy;

                if (nextCell == 'R') {
                    newEnergy = energy;
                } else {
                    newEnergy = currentEnergy - 1;
                }

                /*
                 * Update litter mask.
                 *
                 * If next cell is litter:
                 *
                 * litterId[nr][nc] tells us which bit belongs
                 * to this litter.
                 */
                int newMask = mask;

                if (nextCell == 'L') {

                    int id = litterId[nr][nc];

                    /*
                     * Turn OFF this litter's bit.
                     *
                     * Example:
                     *
                     * mask = 111
                     * id = 1
                     *
                     * 1 << 1 = 010
                     *
                     * mask &= ~(010)
                     *
                     * result = 101
                     */
                    newMask = newMask & ~(1 << id);
                }

                /*
                 * Have we already visited exactly this state?
                 */
                if (visited[nr][nc][newEnergy][newMask]) {
                    continue;
                }

                visited[nr][nc][newEnergy][newMask] = true;

                queue.offer(new int[] {
                        nr,
                        nc,
                        newEnergy,
                        newMask,
                        moves + 1
                });
            }
        }

        // Impossible to collect all litter
        return -1;
    }
}