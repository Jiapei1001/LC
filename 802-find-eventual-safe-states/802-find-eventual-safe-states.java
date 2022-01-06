class Solution {
    
    public List<Integer> eventualSafeNodes(int[][] graph) {
        // DFS + detect cycle
        int n = graph.length;
        
        boolean[] path = new boolean[n];
        Map<Integer, Boolean> memo = new HashMap<>();
        
        List<Integer> res = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (!path[i] && !isCycle(graph, i, path, memo)) {
                res.add(i);
            }
        }
        
        return res;
    }
    
    private boolean isCycle(int[][] graph, int curr, boolean[] path, Map<Integer, Boolean> memo) {
        if (memo.containsKey(curr)) {
            return memo.get(curr);
        }
        
        if (path[curr]) {
            return true;
        }
        
        path[curr] = true;
        boolean temp = false;
        
        for (int next : graph[curr]) {
            temp = isCycle(graph, next, path, memo);
            
            if (temp) {
                break;
            }
        }
        
        path[curr] = false;
        memo.put(curr, temp);
        
        return temp;
    }
    

    
    // WRONG implementation
    // Core is to detect cycle
    /*
    public List<Integer> eventualSafeNodes(int[][] graph) {
        // outdegree = 0
        
        Set<Integer> terminalNodes = new HashSet<>();
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].length == 0) {
                terminalNodes.add(i);
            }
        }
        
        List<Integer> res = new ArrayList<>();
        
        for (int i = 0; i < graph.length; i++) {
            boolean isSafe = true;
            
            // NOTE: here j must loop through graph[i]
            // if (j = 0; j < graph[i].length; j), then j represents index
            // NOT the actual connected node from i
            for (int j : graph[i]) {
                if (!terminalNodes.contains(j)) {
                    isSafe = false;
                    break;
                }
            }
            
            if (isSafe) res.add(i);
        }
        
        return res;
    }
    */
}