class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {

        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];

            graph.get(u).add(new int[]{v, w});
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );

        pq.offer(new int[]{0, k});
        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int d = current[0];
            int node = current[1];

            if (d > dist[node]) {
                continue;
            }

            for (int[] edge : graph.get(node)) {

                int next = edge[0];
                int weight = edge[1];

                int newDist = d + weight;

                if (newDist < dist[next]) {
                    dist[next] = newDist;
                    pq.offer(new int[]{newDist, next});
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= n; i++) {

            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }

            answer = Math.max(answer, dist[i]);
        }

        return answer;
    }
}