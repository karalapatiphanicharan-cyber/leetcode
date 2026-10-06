class Solution 
{
    public int rob(int[] nums) 
    {
        int n = nums.length;
        if(n==1) return nums[0];
        int case1 = robcase(nums,0,n-2);
        int case2 = robcase(nums,1,n-1);
        return Math.max(case1,case2);
    }
    public int robcase(int nums[],int start , int end)
    {
        int prev2 = 0;
        int prev1=0;
        for(int i=start;i<=end;i++)
        {
            int current = Math.max(prev1,prev2+nums[i]);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }
}