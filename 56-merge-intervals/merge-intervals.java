class Solution 
{
    public int[][] merge(int[][] intervals) 
    {
        Arrays.sort(intervals,(a, b) -> a[0] - b[0]);

        List<int[]> list = new ArrayList<>();

        for (int[] interval : intervals) 
        {
            if (list.isEmpty() || interval[0] > list.get(list.size() - 1)[1])
                list.add(interval);
             else 
            {
                list.get(list.size() - 1)[1] =
                    Math.max(list.get(list.size() - 1)[1], interval[1]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
