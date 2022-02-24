class Solution {
    
    public int networkDelayTime(int[][] times, int n, int k) {
        // build the graph
        // dist[], relax -> enqueue
        
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] e : times) {
            graph.computeIfAbsent(e[0], a -> new HashMap<>()).put(e[1], e[2]);
        }
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 10000000);
        dist[k] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{k, 0});
        
        Set<Integer> visited = new HashSet<>();
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int accW = curr[1];
            visited.add(node);
            
            if (visited.size() == n) {
                return accW;
            }
            
            if (!graph.containsKey(node)) {
                continue;
            }
            
            for (int next : graph.get(node).keySet()) {
                int nextW = graph.get(node).get(next);
                if (accW + nextW < dist[next]) {
                    dist[next]= accW + nextW;
                    pq.offer(new int[]{next, dist[next]});
                }
            }
        }
        
        return -1;
    }
    
    
    
    
    
    
    /*
    class Node {
        int label;
        int dist;
        
        public Node(int label, int dist) {
            this.label = label;
            this.dist = dist;
        }
    }
    
    public int networkDelayTime(int[][] times, int n, int k) {
        
        // build graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            int u = time[0], v = time[1], w = time[2];
            int[] temp = {v, w};
            graph.computeIfAbsent(u, a -> new ArrayList<int[]>()).add(temp);
        }
        
        int[] minDis = new int[n + 1];
        Arrays.fill(minDis, Integer.MAX_VALUE);
        
        
        Queue<Node> q = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        Set<Integer> visited = new HashSet<>();
        q.offer(new Node(k, 0));
        // 注意！！这里一定不要加visited，要不然会被pass掉
        // visited.add(k);
        minDis[k] = 0;
        
        int cnt = 0;
        while (!q.isEmpty()) {
            Node curr = q.poll();
            
            if (visited.contains(curr.label)) {
                continue;
            }
            visited.add(curr.label);
            
            // Option 1 - 使用cnt
            if (++cnt == n) {
                return curr.dist;
            }
            
            if (!graph.containsKey(curr.label)) {
                continue;
            }
            
            for (int[] neighbor : graph.get(curr.label)) {
                int v = neighbor[0], w = neighbor[1];
                
                if (!visited.contains(v) && curr.dist + w < minDis[v]) {
                    // 注意不可以在这里加，因为Djkastra，可能会pq重新被visited
                    // visited.add(v);
                    minDis[v] = curr.dist + w;
                    q.offer(new Node(v, curr.dist + w));
                }
            }
        }
        
        return -1;

        // Option 2 - 等待pq结束，找到最大值
//         int res = 0;
//         for (int i = 1; i <= n; i++) {
//             res = Math.max(res, minDis[i]);
//         }
        
//         return visited.size() == n  ?  res : -1;
    }
    */
}