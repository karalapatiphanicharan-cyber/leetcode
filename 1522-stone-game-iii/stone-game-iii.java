class Solution {
    public String stoneGameIII(int[] stoneValue) {
        
        int n = stoneValue.length;
        int[] dp = new int[n + 1];

        // Base case
        dp[n] = 0;

        // Fill dp from right to left
        for (int i = n - 1; i >= 0; i--) {

            int maxDiff = Integer.MIN_VALUE;
            int sum = 0;

            // Try taking 1, 2, or 3 stones
            for (int k = 0; k < 3 && i + k < n; k++) {

                sum += stoneValue[i + k];

                // Current player's score - opponent's score
                int difference = sum - dp[i + k + 1];

                maxDiff = Math.max(maxDiff, difference);
            }

            dp[i] = maxDiff;
        }

        // Decide winner
        if (dp[0] > 0) {
            return "Alice";
        } 
        else if (dp[0] < 0) {
            return "Bob";
        } 
        else {
            return "Tie";
        }
    }
}