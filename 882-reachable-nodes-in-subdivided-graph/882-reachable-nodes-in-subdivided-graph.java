class Solution {

    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        // edge with weight
        // adjust the weight of the edge, if some nodes are visited
        // BFS or Dijkstra
        // find the one with max available moves
        
        // build graph
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] e : edges) {
            graph.computeIfAbsent(e[0], a -> new HashMap<>()).put(e[1], e[2]);
            graph.computeIfAbsent(e[1], a -> new HashMap<>()).put(e[0], e[2]);
        }
        
        // comparator, most available moves first
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        boolean[] visited = new boolean[n];
        
        pq.offer(new int[]{0, maxMoves});
        // source is already enqueued
        int res = 0;
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0], move = curr[1];
            
            if (visited[node]) continue;
            
            visited[node] = true;
            res += 1;
            
            for (int next : graph.getOrDefault(node, new HashMap<>()).keySet()) {
                // if (visited[next]) continue;
                int edgeWeight = graph.get(node).get(next);
                                
                if (!visited[next] && move > edgeWeight) {
                    pq.offer(new int[]{next, move - (edgeWeight + 1)});
                }
                
                graph.get(next).put(node, graph.get(next).get(node) - Math.min(move, edgeWeight));
                res += Math.min(move, edgeWeight);
            }
        }
        
        return res;
    }
    
    
    
    
    
    
    /*
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        // A -> B as 5
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph.computeIfAbsent(u, a -> new HashMap<Integer, Integer>()).put(v, w);
            graph.computeIfAbsent(v, a -> new HashMap<Integer, Integer>()).put(u, w);
        }
        
        // 要找到最多leftCost，因此是MaxHeap
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        boolean[] visited = new boolean[n];
        
        pq.offer(new int[]{0, maxMoves});
        // lazy add
        
        int res = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currNode = curr[0], leftCost = curr[1];
            
            if (visited[currNode]) {
                continue;
            }
            
            visited[currNode] = true;
            res++;
            
            if (!graph.containsKey(currNode)) {
                continue;
            }
            for (Map.Entry<Integer, Integer> next: graph.get(currNode).entrySet()) {
                int v = next.getKey(), w = next.getValue();
                
                if (!visited[v] && leftCost > w) {
                    pq.offer(new int[]{v, leftCost - w - 1});
                }
                // 随时更新edge的cost
                // 这个可以忽略，因为currNode -> v已经被visited，不可能再被visit。但是加上也无妨。
                // graph.get(currNode).put(v, graph.get(currNode).get(v) - Math.min(leftCost, w));
                graph.get(v).put(currNode, graph.get(v).get(currNode) - Math.min(leftCost, w));
                
                res += Math.min(leftCost, w);
            }
        }
        
        return res;
    }
    */
}