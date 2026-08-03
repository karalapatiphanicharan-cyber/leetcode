class Solution {
    public int carFleet(int target, int[] position, int[] speed) 
    {
        int n = position.length;
        int cars[][] = new int [n][2];
        for(int i=0;i<n;i++)
        {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        Arrays.sort(cars, (a, b) -> b[0] - a[0]);
        Stack<Double> stack = new Stack<>();
        for(int i=0;i<n;i++)
        {
            int pos = cars[i][0];
            int spd = cars[i][1];
            double time = (double)(target - pos)/spd;

            if(stack.isEmpty() || time > stack.peek())
            {
                stack.push(time);
            }
        }
        return stack.size();
    }
}