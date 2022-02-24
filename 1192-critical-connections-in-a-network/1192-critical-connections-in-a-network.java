class Solution {
    
    int time;
    int N;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // timestamp: discover time, low time
        // (1,1)(2,1)(3,3)(4,1)
        // critical path, discover time < neighbor's low time
        
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        for (List<Integer> e : connections) {
            graph.computeIfAbsent(e.get(0), a -> new ArrayList<>()).add(e.get(1));
            graph.computeIfAbsent(e.get(1), a -> new ArrayList<>()).add(e.get(0));
        }
        
        int[] dis = new int[n];
        int[] low = new int[n];
        Arrays.fill(dis, -1);
        Arrays.fill(low, 1000000);
        
        time = 0;
        N = n;
        
        Set<Integer> visited = new HashSet<>();
        dfs(graph, visited, -1, 0, dis, low, res);
        
        return res;
    }
    
    private void dfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, int parent, int curr, int[] dis, int[] low, List<List<Integer>> res) {
        
        // visited.add(curr);
        dis[curr] = time;
        low[curr] = time;
        time++;
        
        for (int next : graph.getOrDefault(curr, new ArrayList<>())) {
            if (next == parent) continue;
            // if (visited.contains(next)) continue;
            
            if (dis[next] == -1) {
                dfs(graph, visited, curr, next, dis, low, res);
            }
            
            low[curr] = Math.min(low[curr], low[next]);
            
            if (dis[curr] < low[next]) {
                res.add(Arrays.asList(curr, next));
            }
        }
    }
    
    
    
    
    
    
    /*
    // Option 2 - Tarjan Algorithm
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    
        int[] timer = new int[1];
        List<Integer>[] graph = new ArrayList[n];
        
        // build graph
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (List<Integer> edge : connections) {
            int u = edge.get(0), v = edge.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }
        
        List<List<Integer>> res = new ArrayList<>();
        int[] low = new int[n];
        boolean[] visited = new boolean[n];
        // dfs
        this.dfs(0, 0, low, visited, graph, timer, res);
        
        return res;
    }
    
    private void dfs(int currNode,
                     int prevNode,
                     int[] low,
                     boolean[] visited,
                     List<Integer>[] graph,
                     int[] timer,
                     List<List<Integer>> res) {
        low[currNode] = timer[0]++;
        visited[currNode] = true;
        int currTimeStamp = low[currNode];
        
        for (Integer neighbor : graph[currNode]) {
            if (neighbor == prevNode) {
                continue;
            }
            if (!visited[neighbor]) {
                dfs(neighbor, currNode, low, visited, graph, timer, res);
            }
            // core of Tarjan algo
            // 这里和不是parent的所有visited的neighbor全部比较low值
            low[currNode] = Math.min(low[currNode], low[neighbor]);
            // critical connection has identified
            // 注意这里是和low[neighbor]相比较！！
            if (currTimeStamp < low[neighbor]) {
                res.add(new ArrayList<Integer>(Arrays.asList(currNode, neighbor)));
            }
        }
    }
    */

    
    /* Option 1 - DFS + rank
    int N;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {        
        
        N = n;
        
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> rank = new HashMap<>();
        Map<Pair<Integer, Integer>, Boolean> cntComponents = new HashMap<>();
        
        this.buildGraph(connections, graph, rank, cntComponents);
        
        this.dfs(0, 0, graph, rank, cntComponents);
        
        
        List<List<Integer>> res = new ArrayList<>();

        for (Pair<Integer, Integer> pair : cntComponents.keySet()) {
            res.add(new ArrayList<Integer>(Arrays.asList(pair.getKey(), pair.getValue())));
        }
                    
        return res;
    }
                    
    private int dfs(int currNode, 
                    int discoveryRank,
                    Map<Integer, List<Integer>> graph,
                    Map<Integer, Integer> rank,
                    Map<Pair<Integer, Integer>, Boolean> cntComponents) {
        if (rank.get(currNode) != null) {
            return rank.get(currNode);
        }
        
        rank.put(currNode, discoveryRank);
        
        int minRank = discoveryRank + 1;
        for (Integer neighbor : graph.get(currNode)) {
            if (rank.get(neighbor) != null && rank.get(neighbor) == discoveryRank - 1) {
                continue;
            }
            int nextMinRank = this.dfs(neighbor, discoveryRank + 1, graph, rank, cntComponents);
            
            // step 1 : remove this edge in the cycle
            if (nextMinRank <= discoveryRank) {
                int u = Math.min(currNode, neighbor);
                int v = Math.max(currNode, neighbor);
                cntComponents.remove(new Pair<Integer, Integer>(u, v));
            }
            
            // step 2 : update minRank
            // to remove all edges in the cycle
            minRank = Math.min(minRank, nextMinRank);
        }
        
        return minRank;
    }
                    
    
    private void buildGraph(List<List<Integer>> connections,
                            Map<Integer, List<Integer>> graph,
                            Map<Integer, Integer> rank,
                            Map<Pair<Integer, Integer>, Boolean> cntComponents) {
        
        for (int i = 0; i < N; i++) rank.put(i, null);

        for (List<Integer> edge : connections) {
            int u = Math.min(edge.get(0), edge.get(1));
            int v = Math.max(edge.get(0), edge.get(1));
            graph.computeIfAbsent(u, a -> new ArrayList<Integer>()).add(v);
            graph.computeIfAbsent(v, a -> new ArrayList<Integer>()).add(u);
            
            cntComponents.put(new Pair(u, v), true);
        }
    }
    */
}