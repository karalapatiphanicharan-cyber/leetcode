class Solution {

    int[][] dp;

    public int numDistinct(String s, String t) {

        int n = s.length();
        int m = t.length();

        dp = new int[n][m];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solve(s, t, 0, 0);
    }

    int solve(String s, String t, int i, int j) {

        // Target is completely formed
        if (j == t.length()) {
            return 1;
        }

        // Source is finished but target is still remaining
        if (i == s.length()) {
            return 0;
        }

        // Already calculated
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // Characters match
        if (s.charAt(i) == t.charAt(j)) {

            // Use s[i] OR skip s[i]
            dp[i][j] =
                solve(s, t, i + 1, j + 1)
                +
                solve(s, t, i + 1, j);

        } 
        // Characters don't match
        else {

            // Skip s[i]
            dp[i][j] = solve(s, t, i + 1, j);
        }

        return dp[i][j];
    }
}