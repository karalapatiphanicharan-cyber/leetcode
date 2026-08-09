class Solution
{
    int[][] dp;
    int[] suffix;
    int[] piles;

    public int stoneGameII(int[] piles)
    {
        this.piles = piles;

        int n = piles.length;

        dp = new int[n][n + 1];
        suffix = new int[n + 1];

        for(int i = n - 1; i >= 0; i--)
        {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return solve(0, 1);
    }

    public int solve(int index, int m)
    {
        if(index >= piles.length)
        {
            return 0;
        }

        if(dp[index][m] != 0)
        {
            return dp[index][m];
        }

        int maxStones = 0;

        for(int x = 1; x <= 2 * m && index + x <= piles.length; x++)
        {
            int opponent = solve(index + x, Math.max(m, x));

            int current = suffix[index] - opponent;

            maxStones = Math.max(maxStones, current);
        }

        dp[index][m] = maxStones;

        return maxStones;
    }
}