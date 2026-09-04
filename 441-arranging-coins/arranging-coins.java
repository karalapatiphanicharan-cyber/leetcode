class Solution {
    public int arrangeCoins(int n) {
        long left = 1, right = n;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long coins_needed = mid * (mid + 1) / 2;
            if (coins_needed == n) return (int) mid;
            else if (coins_needed < n) left = mid + 1;
            else right = mid - 1;
        }
        return (int) right;
    }
}