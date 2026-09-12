class Solution {
    static class State implements Comparable<State> {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }

        // Mirrors C++ pair<long long, vector<int>>:
        // compare score first, then compare indices lexicographically
        public int compareTo(State other) {
            if (this.score != other.score)
                return Long.compare(this.score, other.score);
            int len = Math.min(this.indices.size(), other.indices.size());
            for (int i = 0; i < len; i++) {
                int cmp = Integer.compare(this.indices.get(i), other.indices.get(i));
                if (cmp != 0) return cmp;
            }
            return Integer.compare(this.indices.size(), other.indices.size());
        }
    }

    int n;
    int[][] intervals;   // sorted, duplicate-free {left, right, weight}
    int[] origIdx;       // origIdx[i] = original index of intervals[i]
    int[] nextIndex;
    State[][] dp;

    public int[] maximumWeight(List<List<Integer>> a) {
        // Remove duplicates, keeping the smallest original index
        Map<String, Integer> originalIndex = new LinkedHashMap<>();
        for (int i = 0; i < a.size(); i++) {
            List<Integer> iv = a.get(i);
            String key = iv.get(0) + "," + iv.get(1) + "," + iv.get(2);
            if (!originalIndex.containsKey(key)) {
                originalIndex.put(key, i);
            }
        }

        List<int[]> withIndex = new ArrayList<>();
        for (Map.Entry<String, Integer> e : originalIndex.entrySet()) {
            String[] parts = e.getKey().split(",");
            withIndex.add(new int[]{
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]),
                e.getValue()
            });
        }

        withIndex.sort((x, y) -> {
            if (x[0] != y[0]) return Integer.compare(x[0], y[0]);
            if (x[1] != y[1]) return Integer.compare(x[1], y[1]);
            return Integer.compare(x[2], y[2]);
        });

        n = withIndex.size();
        intervals = new int[n][3];
        origIdx = new int[n];
        for (int i = 0; i < n; i++) {
            intervals[i][0] = withIndex.get(i)[0];
            intervals[i][1] = withIndex.get(i)[1];
            intervals[i][2] = withIndex.get(i)[2];
            origIdx[i] = withIndex.get(i)[3];
        }

        nextIndex = new int[n];
        for (int i = 0; i < n; i++) {
            int right = intervals[i][1];
            int lo = 0, hi = n;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (intervals[mid][0] > right) hi = mid;
                else lo = mid + 1;
            }
            nextIndex[i] = lo;
        }

        dp = new State[n + 1][5];

        List<Integer> resultList = solve(0, 4).indices;
        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++) result[i] = resultList.get(i);
        return result;
    }

    private State solve(int i, int k) {
        if (i == n || k == 0) return new State(0, new ArrayList<>());
        if (dp[i][k] != null) return dp[i][k];

        State skip = solve(i + 1, k);

        int weight = intervals[i][2];
        State takeNext = solve(nextIndex[i], k - 1);

        long takeScore = takeNext.score - weight;
        List<Integer> takeIndices = new ArrayList<>(takeNext.indices);
        takeIndices.add(origIdx[i]);
        Collections.sort(takeIndices);
        State take = new State(takeScore, takeIndices);

        State best = (skip.compareTo(take) <= 0) ? skip : take;
        dp[i][k] = best;
        return best;
    }
}