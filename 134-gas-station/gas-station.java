class Solution
{
    public int canCompleteCircuit(int[] gas, int[] cost)
    {
        int totalGas = 0;
        int totalCost = 0;
        int currentTank = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) 
        {
            totalGas += gas[i];
            totalCost += cost[i];
            currentTank += gas[i] - cost[i];
            if (currentTank < 0) 
            {
                start = i + 1;
                currentTank = 0;
            }
        }
        if (totalGas < totalCost) 
            return -1;
        return start;
    }
}
