class Solution {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    List<String> ans = new ArrayList<>();
    public List<String> findItinerary(List<List<String>> tickets) {

        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            graph.putIfAbsent(from, new PriorityQueue<>());
            graph.get(from).offer(to);
        }

        dfs("JFK");

        Collections.reverse(ans);
        return ans;
    }

    void dfs(String airport) {

        PriorityQueue<String> pq = graph.get(airport);

        while (pq != null && !pq.isEmpty()) {
            String next = pq.poll();
            dfs(next);
        }
        ans.add(airport);
    }
}