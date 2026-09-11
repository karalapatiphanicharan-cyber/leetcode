class Solution {
    public int totalNumbers(int[] digits) {

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < digits.length; i++) {

            // First digit cannot be 0
            if (digits[i] == 0) {
                continue;
            }

            for (int j = 0; j < digits.length; j++) {

                // Cannot use the same index again
                if (j == i) {
                    continue;
                }

                for (int k = 0; k < digits.length; k++) {

                    // Cannot use the same index again
                    if (k == i || k == j) {
                        continue;
                    }

                    // Last digit must be even
                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    // Create the 3-digit number
                    int number = digits[i] * 100
                            + digits[j] * 10
                            + digits[k];

                    // Store only unique numbers
                    set.add(number);
                }
            }
        }

        return set.size();
    }
}