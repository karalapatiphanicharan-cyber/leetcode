class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        // Store reserved seats for each row
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int seatNumber = seat[1];

            map.computeIfAbsent(row, k -> new HashSet<>()).add(seatNumber);
        }

        int answer = 0;

        // Rows with no reserved seats can fit 2 families
        int emptyRows = n - map.size();
        answer += emptyRows * 2;

        // Process rows that have reserved seats
        for (Set<Integer> reserved : map.values()) {

            boolean left = !reserved.contains(2)
                    && !reserved.contains(3)
                    && !reserved.contains(4)
                    && !reserved.contains(5);

            boolean middle = !reserved.contains(4)
                    && !reserved.contains(5)
                    && !reserved.contains(6)
                    && !reserved.contains(7);

            boolean right = !reserved.contains(6)
                    && !reserved.contains(7)
                    && !reserved.contains(8)
                    && !reserved.contains(9);

            if (left && right) {
                answer += 2;
            } else if (left || middle || right) {
                answer += 1;
            }
        }

        return answer;
    }
}