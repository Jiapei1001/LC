class Solution {
    
    class Node {
        int node;
        double prob;
        
        public Node(int node, double prob) {
            this.node = node;
            this.prob = prob;
        }
    }
    
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // undirected, positive weighted
        
        // build graph
        Map<Integer, Map<Integer, Double>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            graph.computeIfAbsent(edges[i][0], a -> new HashMap<>()).put(edges[i][1], succProb[i]);
            graph.computeIfAbsent(edges[i][1], a -> new HashMap<>()).put(edges[i][0], succProb[i]);
        }
        
        double[] prob = new double[n];
        prob[start] = 1.0;
        
        Queue<Node> pq = new PriorityQueue<>((a, b) -> Double.compare(b.prob, a.prob));
        pq.offer(new Node(start, 1.0));
        
        // Set<Integer> visited = new HashSet<>();
        
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            
            // check if reaches the end
            if (curr.node == end) {
                return curr.prob;
            }
            
            // 必须要加visited，因为prob可能会不断进行下去，造成TLE
            // if (visited.contains(curr.node)) {
            //     continue;
            // }
            // visited.add(curr.node);
            
            for (int next : graph.getOrDefault(curr.node, new HashMap<>()).keySet()) {
                // relax
                double cand = curr.prob * graph.get(curr.node).get(next);
                if (cand > prob[next]) {
                    pq.offer(new Node(next, cand));
                    prob[next] = cand;
                }
            }
        }
        
        return prob[end];
    }
}