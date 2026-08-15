class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xor = 0;

        boolean hasNonZero = false;

        for (int num : nums) {
            xor ^= num;

            if (num != 0) {
                hasNonZero = true;
            }
        }

        // Case 1: Entire array has non-zero XOR
        if (xor != 0) {
            return n;
        }

        // Case 2: XOR is 0, but there is at least one non-zero element
        if (hasNonZero) {
            return n - 1;
        }

        // Case 3: All elements are 0
        return 0;
    }
}