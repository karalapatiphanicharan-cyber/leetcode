class Solution 
{
    public int maxProduct(int[] nums) 
    {
        int firstmax = 0;
        int secondmax = 0;
        for(int num:nums)
        {
            if(num>firstmax)
            {
                secondmax = firstmax;
                firstmax = num;
            }
            else if(num>secondmax)
            {
                secondmax = num;
            }
        }
        return (firstmax-1)*(secondmax-1);
    }
}