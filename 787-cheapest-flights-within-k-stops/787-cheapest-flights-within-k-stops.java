class Solution {
    
    class Pair {
        int key;
        int val;
        
        public Pair(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Dijkstra, with PQ
        // enqueue: relax or stop
        Map<Integer, List<Pair>> graph = new HashMap<>();
        
        for (int[] e : flights) {
            graph.computeIfAbsent(e[0], a -> new ArrayList<>()).add(new Pair(e[1], e[2]));
        }
        
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int[] stops = new int[n];
        Arrays.fill(stops, Integer.MAX_VALUE);
        
        // NOTE: 这里只可以比较dist，不可以按stops排序，否则会错误！！
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        // node, stops, dist
        pq.offer(new int[]{src, 0, 0});
        
        int res = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0], s = curr[1], d = curr[2];
                        
            if (s > k) {
                continue;
            }
            
            for (Pair nei : graph.getOrDefault(node, new ArrayList<>())) {
                int next = nei.key;
                int w = nei.val;
                // relax
                if (d + w < dist[next]) {
                    dist[next] = d + w;
                    stops[next] = s + 1;
                    if (dst == next) {
                        res = Math.min(res, dist[dst]);
                    }
                    pq.offer(new int[]{next, s + 1, dist[next]});
                }
                // stop
                if (s + 1 < stops[next]) {
                    stops[next] = s + 1;
                    pq.offer(new int[]{next, s + 1, d + w});
                }
            }
        }
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    // Option 4 - BFS
    /*
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // build graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            int u = flight[0], v = flight[1], w = flight[2];
            graph.computeIfAbsent(u, a -> new ArrayList<int[]>()).add(new int[]{v, w});
        }
        

        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        
        // bfs, level by level
        Queue<int[]> q = new LinkedList<>();
        // node, cost, dist
        q.offer(new int[]{src, 0, 0});
        
        int stop = 0;
        int res = Integer.MAX_VALUE;
        while (!q.isEmpty() && stop < k + 1) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int currNode = curr[0], currCost = curr[1], currDist = curr[2];
                
                if (!graph.containsKey(currNode)) {
                    continue;
                }
                
                for (int[] next : graph.get(currNode)) {
                    if (stop == k && next[0] != dst) {
                        continue;
                    }
                    
                    if (currCost + next[1] < minCost[next[0]]) {
                        minCost[next[0]] = currCost + next[1];
                        q.offer(new int[]{next[0], currCost + next[1], currDist + 1});
                        
                        if (next[0] == dst) {
                            res = Math.min(res, currCost + next[1]);
                        }
                    }
                }
                
            }
            stop++;
        }
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    */
    
    
    /* Option 1 - Dijkstra
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // single source directed graph
        // djkastra algo
        // build graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            int u = flight[0], v = flight[1], w = flight[2];
            int[] temp = {v, w};
            graph.computeIfAbsent(u, a -> new ArrayList<int[]>()).add(temp);
        }
        
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[src] = 0;
        
        int[] stops = new int[n];
        Arrays.fill(stops, Integer.MAX_VALUE);
        stops[src] = 0;
        
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        // node, cost, dist
        pq.offer(new int[] {src, 0, 0});
        // lazy seen
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currNode = curr[0], currCost = curr[1], currStops = curr[2];
            
            
            if (currNode == dst) {
                return currCost;
            }
            
            if (currStops == k + 1) {
                continue;
            }
            
            if (!graph.containsKey(currNode)) {
                continue;
            }
            
            for (int[] next : graph.get(currNode)) {
                if (currCost + next[1] < minCost[next[0]]) {
                    minCost[next[0]] = curr[1] + next[1];
                    pq.offer(new int[] {next[0], curr[1] + next[1], curr[2] + 1});
                } 
                // Djkastra 变形的地点
                // The thing we need to modify here is that we need to re-consider a node if the distance from the source is shorter than what we have recorded.
                else if (currStops < stops[next[0]]) {
                    pq.offer(new int[] {next[0], curr[1] + next[1], curr[2] + 1});
                }
                stops[next[0]] = currStops;
            }
        }
        
        return minCost[dst] == Integer.MAX_VALUE ? -1 : minCost[dst];
    }
    */
}