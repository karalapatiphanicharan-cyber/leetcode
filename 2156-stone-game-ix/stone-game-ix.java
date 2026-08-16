class Solution {
    public boolean stoneGameIX(int[] stones) {

        int count0 = 0;
        int count1 = 0;
        int count2 = 0;

        // Count stones based on remainder when divided by 3
        for (int stone : stones) {
            int remainder = stone % 3;

            if (remainder == 0) {
                count0++;
            } else if (remainder == 1) {
                count1++;
            } else {
                count2++;
            }
        }

        // If count0 is even
        if (count0 % 2 == 0) {
            return count1 > 0 && count2 > 0;
        }

        // If count0 is odd
        return Math.abs(count1 - count2) > 2;
    }
}