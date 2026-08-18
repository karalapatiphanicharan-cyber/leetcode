class Solution {
    public int largestInteger(int[] nums, int k) {

        int n = nums.length;

        // Frequency of each number
        int[] freq = new int[51];

        for (int num : nums) {
            freq[num]++;
        }

        // Case 1: k == 1
        if (k == 1) {
            int answer = -1;

            for (int num : nums) {
                if (freq[num] == 1) {
                    answer = Math.max(answer, num);
                }
            }

            return answer;
        }

        // Case 2: k == n
        if (k == n) {
            int answer = 0;

            for (int num : nums) {
                answer = Math.max(answer, num);
            }

            return answer;
        }

        // Case 3: 1 < k < n
        int answer = -1;

        // Check first element
        if (freq[nums[0]] == 1) {
            answer = Math.max(answer, nums[0]);
        }

        // Check last element
        if (freq[nums[n - 1]] == 1) {
            answer = Math.max(answer, nums[n - 1]);
        }

        return answer;
    }
}