class Solution {
    
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        // check the new edge if it can create a cycle
        Set<Integer> visited = new HashSet<>();
        for (int[] e : edges) {
            visited = new HashSet<>();
            if (createCycle(graph, e[0], e[1], visited)) {
                return e;
            }
            graph.computeIfAbsent(e[0], a -> new ArrayList<>()).add(e[1]);
            graph.computeIfAbsent(e[1], a -> new ArrayList<>()).add(e[0]);
        }
        
        return null;
    }
    
    private boolean createCycle(Map<Integer, List<Integer>> graph, int src, int tar, Set<Integer> visited) {
        visited.add(src);
        
        for (int next : graph.getOrDefault(src, new ArrayList<>())) {
            if (next == tar) return true;
            if (!visited.contains(next)) {
                if (createCycle(graph, next, tar, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    
    // UnionFind
    /*
    class UnionFind {
        int[] parent;
        
        public UnionFind(int n) {
            this.parent = new int[n + 1];
            for (int i = 0; i < n + 1; i++) {
                this.parent[i] = i;
            }
        }
        
        public int find(int i) {
            if (i == this.parent[i]) {
                return i;
            }
            this.parent[i] = find(this.parent[i]);
            
            return this.parent[i];
        }
        
        public boolean union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            
            if (pa == pb) return false;
            
            this.parent[pa] = pb;
            return true;
        }
    }
    
    public int[] findRedundantConnection(int[][] edges) {
        int n = 0;
        for (int[] e : edges) {
            n = Math.max(n, Math.max(e[0], e[1]));
        }
        
        UnionFind uf = new UnionFind(n);
        
        int[] res = null;
        for (int[] e : edges) {
            boolean noCycle = uf.union(e[0], e[1]);
            if (!noCycle) {
                res = e;
            }
        }
        
        return res;
    }
    */
    
    
    /* Option 1 - Disjoint Components - UnionFind
    class UnionFind {
        int[] parent;
        int[] size;
        
        public UnionFind(int n) {
            this.parent = new int[n];
            this.size = new int[n];
            
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
            }
            Arrays.fill(this.size, 1);
        }
        
        public int find(int i) {
            if (this.parent[i] == i) {
                return i;
            }
            this.parent[i] = find(this.parent[i]);
            
            return this.parent[i];
        }
        
        public boolean union(int a, int b) {
            int pA = find(a);
            int pB = find(b);
            
            if (pA == pB) {
                return false;
            } else {
                this.parent[pA] = pB;
                this.size[pB] += this.size[pA];
                return true;
            }
        }
        
        public int getSize(int i) {
            return this.size[i];
        }
    }
    
    public int[] findRedundantConnection(int[][] edges) {
        // connected components
        // union find
        // Note: here n represents the number of nodes
        // uf is used to find connected nodes
        int n = edges.length;
        UnionFind uf = new UnionFind(n);
        
        for (int[] e : edges) {
            // union two nodes
            // if return false, meaning there is already a cycle
            // and this is the last edge in that cycle
            if (!uf.union(e[0] - 1, e[1] - 1)) {
                return e;
            }
        }
        
        throw new IllegalArgumentException();
    }
    */

    
    // Option 2 - DFS to find circle
    /*
    public int[] findRedundantConnection(int[][] edges) {
        // connected components
        
        // build graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            graph.computeIfAbsent(u, a -> new ArrayList<Integer>()).add(v);
            graph.computeIfAbsent(v, a -> new ArrayList<Integer>()).add(u);
        }
        
        Set<Integer> visited = new HashSet<>();
        // for (int[] e : edges) {
        //     visited.clear();
        //     if (graph.containsKey(e[0]) && graph.containsKey(e[1])) {
        //         if (this.dfs(e[0], e[1], e[0], graph, visited)) {
        //             return e;
        //         }
        //     }
        // }
        
        for (int i = edges.length - 1; i >= 0; i--) {
            visited.clear();
            int u = edges[i][0], v = edges[i][1];
            if (graph.containsKey(u) && graph.containsKey(v)) {
                if (this.dfs(u, v, u, graph, visited)) {
                    return edges[i];
                }
            }
        }
        
        throw new IllegalArgumentException();
    }
    
    private boolean dfs(int prev,
                        int curr,
                        int target,
                        Map<Integer, List<Integer>> graph,
                        Set<Integer> visited) {
        if (visited.contains(curr)) {
            return false;
        }
        
        visited.add(curr);
        if (curr == target) return true;
        
        for (int next : graph.get(curr)) {
            if (next == prev) continue;
            if (this.dfs(curr, next, target, graph, visited)) {
                return true;
            }
        }
        
        return false;
    }
    */
}