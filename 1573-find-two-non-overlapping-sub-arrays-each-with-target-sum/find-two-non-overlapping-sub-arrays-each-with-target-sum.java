class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;

        int[] best = new int[n];

        int INF = n + 1;
        int answer = INF;

        int left = 0;
        int sum = 0;
        int previousBest = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {

                int currentLength = right - left + 1;

                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(
                        answer,
                        currentLength + best[left - 1]
                    );
                }

                previousBest = Math.min(
                    previousBest,
                    currentLength
                );
            }

            best[right] = previousBest;
        }

        return answer == INF ? -1 : answer;
    }
}