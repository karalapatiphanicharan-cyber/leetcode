class Solution {
    public boolean sumGame(String num) {

        int n = num.length();

        int leftSum = 0;
        int rightSum = 0;

        int leftQ = 0;
        int rightQ = 0;

        // Left half
        for (int i = 0; i < n / 2; i++) {
            char ch = num.charAt(i);

            if (ch == '?') {
                leftQ++;
            } else {
                leftSum += ch - '0';
            }
        }

        // Right half
        for (int i = n / 2; i < n; i++) {
            char ch = num.charAt(i);

            if (ch == '?') {
                rightQ++;
            } else {
                rightSum += ch - '0';
            }
        }

        // Odd number of '?' -> Alice always wins
        if ((leftQ + rightQ) % 2 == 1) {
            return true;
        }

        // Check whether Bob can make both sums equal
        return leftSum - rightSum
                != 9 * (rightQ - leftQ) / 2;
    }
}