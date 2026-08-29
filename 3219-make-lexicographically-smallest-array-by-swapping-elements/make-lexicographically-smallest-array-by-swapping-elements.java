class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        // Store {value, original index}
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        // Sort by value
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];

        int start = 0;

        while (start < n) {

            int end = start;

            // Find one group
            while (end + 1 < n &&
                   arr[end + 1][0] - arr[end][0] <= limit) {
                end++;
            }

            // Store values and indices of this group
            List<Integer> values = new ArrayList<>();
            List<Integer> indices = new ArrayList<>();

            for (int i = start; i <= end; i++) {
                values.add(arr[i][0]);
                indices.add(arr[i][1]);
            }

            // Sort original indices
            Collections.sort(indices);

            // Values are already sorted because arr is sorted
            for (int i = 0; i < values.size(); i++) {
                result[indices.get(i)] = values.get(i);
            }

            start = end + 1;
        }

        return result;
    }
}