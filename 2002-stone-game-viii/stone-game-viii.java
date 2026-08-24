class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += stones[i];
        }
        
        int max = sum;
        for (int i = n - 1; i >= 2; --i) {
            sum -= stones[i];
            max = Math.max(max, sum - max);
        }
        return max;
    }
}