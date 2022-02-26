class Solution {

    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] color = new int[n + 1];
        
        // build graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        for (int[] d : dislikes) {
            graph.computeIfAbsent(d[0], a -> new HashSet<>()).add(d[1]);
            graph.computeIfAbsent(d[1], a -> new HashSet<>()).add(d[0]);
        }
        
        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                color[i] = -1;
                if (!bfs(graph, i, color)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean bfs(Map<Integer, Set<Integer>> graph, int i, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        
        while (!q.isEmpty()) {
            int curr = q.poll();
            
            for (int next : graph.getOrDefault(curr, new HashSet<>())) {
                if (color[next] == 0) {
                    color[next] = -color[curr];
                    q.offer(next);
                } else {
                    if (color[next] == color[curr]) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    
    /*
    public boolean possibleBipartition(int n, int[][] dislikes) {
        // build graph
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
        for (int[] d : dislikes) {
            graph[d[0] - 1].add(d[1] - 1);
            graph[d[1] - 1].add(d[0] - 1);
        }
    
        // Coloring + DFS
        // Blue 1
        // no color 0
        // Red -1
        int[] color = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (color[i] == 0 && !isValidColor(graph, color, i, 1)) {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean isValidColor(List<Integer>[] graph, int[] color, int curr, int currColor) {
        if (color[curr] != 0) {
            return color[curr] == currColor;
        }
        
        color[curr] = currColor;
        for (int next : graph[curr]) {
            // if (color[next] == currColor) {
            //     return false;
            // } else if (color[next] == 0 && !isValidColor(graph, color, next, -currColor)) {
            //     return false;
            // }
            if (!isValidColor(graph, color, next, -currColor)) {
                return false;
            }
        }
        
        return true;
    }
    */

    
    /*
    public boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, Integer> findItsCounter = new HashMap<>();
        
        UnionFind uf = new UnionFind(n);
        
        for (int[] d : dislikes) {
            int u = d[0];
            int v = d[1];
            
            findItsCounter.putIfAbsent(u, v);
            findItsCounter.putIfAbsent(v, u);
            
            if (uf.find(u) == uf.find(v)) {
                return false;
            }
            
            uf.union(u, findItsCounter.get(v));
            uf.union(v, findItsCounter.get(u));
        }
        
        return true;
    }
    */
}

class UnionFind {
    
    int[] parent;
    
    public UnionFind(int n) {
        parent = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }
    
    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        
        // path compression
        parent[i] = this.find(parent[i]);
        
        return parent[i];
    }
    
    public void union(int a, int b) {
        int pA = find(a);
        int pB = find(b);
        
        if (pA != pB) {
            parent[pA] = pB;
        }
    }
}