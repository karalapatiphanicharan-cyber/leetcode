class Solution {
    public int firstStableIndex(int[] nums, int k) {

        int n = nums.length;

        // right[i] = minimum value from i to n-1
        int[] right = new int[n];

        right[n - 1] = nums[n - 1];

        // Build suffix minimum array
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(right[i + 1], nums[i]);
        }

        // Maximum value from 0 to current index
        int left = 0;

        // Check every index
        for (int i = 0; i < n; i++) {

            left = Math.max(left, nums[i]);

            // Instability = max(nums[0..i]) - min(nums[i..n-1])
            if (left - right[i] <= k) {
                return i;
            }
        }

        return -1;
    }
}