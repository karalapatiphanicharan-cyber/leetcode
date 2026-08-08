class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        // last[j] = index in word1 from which
        // word2[j...] can be matched exactly.
        int[] last = new int[m];

        for (int i = 0; i < m; i++) {
            last[i] = -1;
        }

        // Build suffix matching information from right to left
        int i = n - 1;
        int j = m - 1;

        while (i >= 0 && j >= 0) {

            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }

            i--;
        }

        // Answer
        int[] ans = new int[m];

        i = 0;
        j = 0;

        // We are allowed only ONE mismatch
        boolean mismatchUsed = false;

        while (i < n && j < m) {

            // Case 1: Characters match
            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                j++;
            }

            // Case 2: Characters don't match
            else if (!mismatchUsed &&
                    (j == m - 1 || i < last[j + 1])) {

                // Use our one allowed mismatch
                ans[j] = i;
                j++;

                mismatchUsed = true;
            }

            i++;
        }

        // Could not construct the complete sequence
        if (j < m) {
            return new int[0];
        }

        return ans;
    }
}