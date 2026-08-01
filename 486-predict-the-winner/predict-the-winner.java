class Solution 
{
    public boolean predictTheWinner(int[] nums) 
    {
        int n = nums.length;
        Integer[][] dp = new Integer[n][n];
        return solve(nums, 0, n - 1, dp) >= 0;
    }

    private int solve(int[] nums, int left, int right, Integer[][] dp)
    {
        if (left == right)
        {
            return nums[left];
        }

        if (dp[left][right] != null)
        {
            return dp[left][right];
        }

        int takeLeft = nums[left] - solve(nums, left + 1, right, dp);

        int takeRight = nums[right] - solve(nums, left, right - 1, dp);

        dp[left][right] = Math.max(takeLeft, takeRight);

        return dp[left][right];
    }
}