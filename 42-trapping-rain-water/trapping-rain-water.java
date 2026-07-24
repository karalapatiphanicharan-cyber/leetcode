class Solution 
{
    public int trap(int[] height) 
    {
        int left = 0;
        int right = height.length-1;
        int leftmax = 0;
        int rightmax = 0;
        int totalwater = 0;
        while(left<right)
        {
            if(height[left]<height[right])
            {
                if(height[left]>=leftmax)
                {
                    leftmax = height[left];
                }
                else
                {
                    totalwater = totalwater+leftmax-height[left];
                }
                left++;
            }
            else
            {
                    if(height[right]>=rightmax)
                    {
                        rightmax = height[right];
                    }
                    else
                    {
                        totalwater = totalwater+rightmax-height[right];
                    }
                right--;
            }
        }
        return totalwater;
    }
}