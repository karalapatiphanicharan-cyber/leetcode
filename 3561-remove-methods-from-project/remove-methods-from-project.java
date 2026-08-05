class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        // Step 1: Build graph
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : invocations) {
            int a = edge[0];
            int b = edge[1];

            graph[a].add(b);
        }

        // Step 2: Find all suspicious methods using DFS
        boolean[] suspicious = new boolean[n];

        dfs(k, graph, suspicious);

        // Step 3: Check if any normal method calls suspicious method
        for (int[] edge : invocations) {
            int from = edge[0];
            int to = edge[1];

            if (!suspicious[from] && suspicious[to]) {
                // Cannot remove suspicious methods
                List<Integer> ans = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    ans.add(i);
                }

                return ans;
            }
        }

        // Step 4: Remove suspicious methods
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }

        return ans;
    }

    private void dfs(int node, List<Integer>[] graph, boolean[] suspicious) {

        if (suspicious[node]) {
            return;
        }

        suspicious[node] = true;

        for (int next : graph[node]) {
            dfs(next, graph, suspicious);
        }
    }
}