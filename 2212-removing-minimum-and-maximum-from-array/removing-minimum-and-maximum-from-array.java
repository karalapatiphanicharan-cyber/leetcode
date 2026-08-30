class Solution {
    public int minimumDeletions(int[] nums) {

        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find indexes of minimum and maximum
        for (int i = 1; i < n; i++) {

            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Make first = smaller index
        // and last = larger index
        int first = Math.min(minIndex, maxIndex);
        int last = Math.max(minIndex, maxIndex);

        // Option 1: remove both from left
        int fromLeft = last + 1;

        // Option 2: remove both from right
        int fromRight = n - first;

        // Option 3: remove one from left and one from right
        int fromBothSides = (first + 1) + (n - last);

        return Math.min(
            fromLeft,
            Math.min(fromRight, fromBothSides)
        );
    }
}