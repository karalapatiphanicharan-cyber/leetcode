class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int [] result = new int[n];
        int left =0;
        int right = n-1;
        for(int i=n-1;i>=0;i--)
        {
            int leftsquare = nums[left]*nums[left];
            int rightsquare = nums[right]*nums[right];
            if(leftsquare>rightsquare)
            {
                result[i]=leftsquare;
                left++;
            }
            else
            {
                result[i] = rightsquare;
                right--;
            }
        }
        return result;
    }
}