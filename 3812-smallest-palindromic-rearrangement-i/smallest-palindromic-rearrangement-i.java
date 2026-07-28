class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        String middle = "";

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                middle = String.valueOf((char)(i + 'a'));
            }

            for (int j = 0; j < freq[i] / 2; j++) {
                left.append((char)(i + 'a'));
            }
        }

        StringBuilder right = new StringBuilder(left).reverse();

        return left.toString() + middle + right.toString();
    }
}