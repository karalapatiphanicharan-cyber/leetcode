class Solution 
{
    public int[] dailyTemperatures(int[] temperatures) 
    {
        int n = temperatures.length;
        int arr[] = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<n;i++)
        {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()])
            {
                int prevday = stack.pop();
                arr[prevday]=i-prevday;
            }
            stack.push(i);
        }
        return arr;
    }
}