class Solution {
    public boolean validTree(int n, int[][] edges) {
        // undirected graph
        // DFS with parent node
        // UnionFind
        
        // build graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] e : edges) {
            graph.computeIfAbsent(e[0], a -> new HashSet<>()).add(e[1]);
            graph.computeIfAbsent(e[1], a -> new HashSet<>()).add(e[0]);
        }
        
        boolean[] visited = new boolean[n];
        Map<Integer, Integer> parent = new HashMap<>();

        if (hasCycle(graph, 0, -1, visited)) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) return false;
        }
        
        return true;
    }
    
    private boolean hasCycle(Map<Integer, Set<Integer>> graph, int curr, int parent, boolean[] visited) {
        // mark as visited
        visited[curr] = true;
        
        for (int next : graph.getOrDefault(curr, new HashSet<>())) {
            if (next == parent) continue;
            
            if (!visited[next]) {
                if (hasCycle(graph, next, curr, visited)) {
                    return true;
                }
            } else {
                return true;
            }
        }
        
        return false;
    }
}