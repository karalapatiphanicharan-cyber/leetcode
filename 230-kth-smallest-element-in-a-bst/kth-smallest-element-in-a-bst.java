class Solution 
{
    int count =0;
    public int kthSmallest(TreeNode root, int k) 
    {
        return inorder(root,k);
    }
    private int inorder(TreeNode node, int k)
    {
        if(node == null)
        return -1;

        int left = inorder(node.left,k);

        if(left!=-1)
        return left;

        count++;

        if(count==k)
        return node.val;

        int right = inorder(node.right,k);
        return right;
    }
}