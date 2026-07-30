import java.util.Arrays;

class Solution 
{
    public int minimumPushes(String word) 
    {
        // Step 1: Create frequency array
        int[] freq = new int[26];

        // Step 2: Count frequency of each character
        for (char ch : word.toCharArray())
        {
            freq[ch - 'a']++;
        }

        // Step 3: Sort the frequency array
        Arrays.sort(freq);

        int pushes = 1;
        int count = 0;
        int total = 0;

        // Step 4: Process from highest frequency to lowest
        for (int i = 25; i >= 0; i--)
        {
            if (freq[i] == 0)
            {
                break;
            }

            // Add the cost
            total += freq[i] * pushes;

            count++;

            // After every 8 letters, increase the push count
            if (count % 8 == 0)
            {
                pushes++;
            }
        }

        return total;
    }
}