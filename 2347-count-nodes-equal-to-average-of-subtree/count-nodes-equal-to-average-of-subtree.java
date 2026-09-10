class Solution {

    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    public int[] dfs(TreeNode root) {

        // Base case
        if (root == null) {
            return new int[]{0, 0};
        }

        // Get sum and count from left subtree
        int[] left = dfs(root.left);

        // Get sum and count from right subtree
        int[] right = dfs(root.right);

        // Calculate current subtree sum
        int sum = left[0] + right[0] + root.val;

        // Calculate current subtree node count
        int count = left[1] + right[1] + 1;

        // Calculate average
        int average = sum / count;

        // Check condition
        if (root.val == average) {
            ans++;
        }

        // Return sum and count to parent
        return new int[]{sum, count};
    }
}