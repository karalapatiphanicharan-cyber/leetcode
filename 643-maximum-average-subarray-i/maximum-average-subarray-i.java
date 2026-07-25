class Solution 
{
    public double findMaxAverage(int[] nums, int k) 
    {
        int windowsum = 0;
        for(int i=0;i<k;i++)
        {
            windowsum = windowsum+nums[i];
        }
        int maxsum = windowsum;
        for(int i = k;i<nums.length;i++)
        {
            windowsum = windowsum - nums[i-k];
            windowsum = windowsum + nums[i];
            maxsum = Math.max(windowsum,maxsum);
        }
        return (double) maxsum/k;
    }
}