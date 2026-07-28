class Solution 
{
    public int[] maxSlidingWindow(int[] nums, int k) 
    {
        int n = nums.length;
        int ans[] = new int[n-k+1];
        Deque<Integer> deque = new ArrayDeque<>();
        int index = 0;
        for(int i=0;i<n;i++)
        {
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) 
            {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) 
            {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) 
            {
                ans[index++] = nums[deque.peekFirst()];
            }
        }
        return ans;
    }
}