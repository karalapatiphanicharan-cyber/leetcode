class Solution {
    public int smallestNumber(int n, int t) {
        while(true)
        {
            int product = digitproduct(n);
            if(product%t==0)
            return n;

            n++;
        }
    }
    private int digitproduct(int nums)
    {
       int  product =1;
        while(nums>0)
        {
            product = product*(nums%10);
            nums = nums/10;
        }
        return product;
    }
}