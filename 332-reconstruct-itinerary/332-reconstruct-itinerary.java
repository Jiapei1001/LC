class Solution {
    List<String> res;
    int n;
    
    public List<String> findItinerary(List<List<String>> tickets) {
        // build the graph
        // traverse the graph and the total length of the itinerary is the total edges
        res = new ArrayList();
        n = tickets.size();
        
        Map<String, List<String>> graph = new HashMap<>();
        Set<String> seen = new HashSet<>();
        
        for (List<String> t : tickets) {
            graph.computeIfAbsent(t.get(0), a -> new ArrayList<String>()).add(t.get(1));
        }
        
        for (String key : graph.keySet()) {
            Collections.sort(graph.get(key));
        }
        
        List<String> path = new ArrayList<>();
        path.add("JFK");
        int[] cnt = new int[1];
        dfs(graph, cnt, "JFK", 1, path);
        
        return res;
    }
    
    private void dfs(Map<String, List<String>> graph, int[] cnt, String curr, int i, List<String> path) {
        if (cnt[0] == n) {
            res = new ArrayList<>(path);
            return;
        }
        if (!res.isEmpty()) {
            return;
        }
        
        if (!graph.containsKey(curr)) return;
        
        List<String> nextCities = graph.get(curr);
        
        if (nextCities.isEmpty()) return;
        
        for (int j = 0; j < nextCities.size(); j++) {
            String next = nextCities.get(j);
            
            nextCities.remove(j);
            path.add(next);
            cnt[0]++;
            dfs(graph, cnt, next, i + 1, path);
            
            nextCities.add(j, next);
            path.remove(path.size() - 1);
            cnt[0]--;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    public List<String> findItinerary(List<List<String>> tickets) {
        int n = tickets.size();
        
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> edge : tickets) {
            String u = edge.get(0).toUpperCase();
            String v = edge.get(1).toUpperCase();
            graph.computeIfAbsent(u, a -> new PriorityQueue<String>())
                 .add(v);
        }
        
        List<String> path = new ArrayList<>();
        this.dfs(n, graph, "JFK", path);
        
        return path;
    }
    
    private void dfs(int n,
                     Map<String, PriorityQueue<String>> graph,
                     String currNode,
                     List<String> path) {
        if (path.size() == n + 1) {
            return;
        }
        
        PriorityQueue<String> neighbors = graph.get(currNode);
        while (neighbors != null && !neighbors.isEmpty()) {
            String next = neighbors.poll();
            this.dfs(n, graph, next, path);
        }
        path.add(0, currNode);
    }
    */
}