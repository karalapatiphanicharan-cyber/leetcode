class Solution {
    public int lengthOfLIS(int[] nums) {

        int n = nums.length;

        int[] dp = new int[n];

        // Every element itself is a subsequence of length 1
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Check previous elements
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[j] < nums[i]) {

                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // Find maximum in dp
        int answer = 0;

        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }
}