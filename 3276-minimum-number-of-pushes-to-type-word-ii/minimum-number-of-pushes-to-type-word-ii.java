class Solution 
{
    public int minimumPushes(String word) 
    {
        int[] freq = new int[26];

        // Count frequency of each character
        for (char ch : word.toCharArray())
        {
            freq[ch - 'a']++;
        }

        // Sort frequencies
        Arrays.sort(freq);

        int pushes = 0;
        int position = 0;

        // Traverse from highest frequency to lowest
        for (int i = 25; i >= 0; i--)
        {
            if (freq[i] == 0)
            {
                break;
            }

            int cost = (position / 8) + 1;
            pushes += freq[i] * cost;
            position++;
        }

        return pushes;
    }
}