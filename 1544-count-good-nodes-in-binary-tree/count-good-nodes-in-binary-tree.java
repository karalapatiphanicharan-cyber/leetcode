class Solution 
{
    public int goodNodes(TreeNode root) 
    {
        return dfs(root,root.val);
    }
    private int dfs(TreeNode node,int maxsofar)
    {
        if(node == null)
        return 0;

        int count = 0;

        if(node.val>=maxsofar)
        count = 1;

        maxsofar = Math.max(maxsofar,node.val);
        count = count+dfs(node.left,maxsofar);
        count = count+dfs(node.right,maxsofar);
        return count;
    }
}