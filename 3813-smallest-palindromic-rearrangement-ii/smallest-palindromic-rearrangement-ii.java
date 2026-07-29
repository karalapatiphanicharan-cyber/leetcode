import java.util.*;

class Solution {

    long LIMIT = 1_000_000L;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        String mid = "";

        int[] half = new int[26];

        for (int i = 0; i < 26; i++) {

            if ((freq[i] & 1) == 1) {
                mid = String.valueOf((char) ('a' + i));
            }

            half[i] = freq[i] / 2;
        }

        int halfLength = s.length() / 2;

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLength; pos++) {

            boolean found = false;

            for (int ch = 0; ch < 26; ch++) {

                if (half[ch] == 0)
                    continue;

                half[ch]--;

                long ways = countWays(half);

                if (ways >= k) {

                    left.append((char) ('a' + ch));
                    found = true;
                    break;

                } else {

                    k -= ways;
                    half[ch]++;
                }
            }

            if (!found)
                return "";
        }

        String right = left.reverse().toString();

        left.reverse();

        return left.toString() + mid + right;
    }

    private long countWays(int[] half) {

        int total = 0;

        for (int x : half)
            total += x;

        long ans = 1;

        int remain = total;

        for (int cnt : half) {

            if (cnt == 0)
                continue;

            ans *= nCr(remain, cnt);

            if (ans > LIMIT)
                return LIMIT;

            remain -= cnt;
        }

        return Math.min(ans, LIMIT);
    }

    private long nCr(int n, int r) {

        if (r > n)
            return 0;

        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {

            res = res * (n - r + i) / i;

            if (res > LIMIT)
                return LIMIT;
        }

        return res;
    }
}