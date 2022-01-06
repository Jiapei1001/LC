class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        for (int i = 0; i < numCourses; i++) {
            graph.putIfAbsent(i, new HashSet<Integer>());
        }
        
        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);
        }
        
        // Set<Integer> visited = new HashSet<>();
        Map<Integer, Boolean> memo = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        
        boolean[] path = new boolean[numCourses];
        
        // DFS
        for (int i = 0; i < numCourses; i++) {
            if (!memo.containsKey(i)) {
                if (isCycle(graph, i, path, memo, res)) {
                    return new int[]{};
                }
            }
        }
        
        Collections.reverse(res);
        
        return res.stream().mapToInt(i -> i).toArray();
    }
    
    
    private boolean isCycle(Map<Integer, Set<Integer>> graph,
                            int curr,
                            boolean[] path,
                            Map<Integer, Boolean> memo,
                            List<Integer> res) {
        
        if (memo.containsKey(curr)) {
            return memo.get(curr);
        }
        
        if (path[curr]) {
            return true;
        }
        
        path[curr] = true;
        
        boolean temp = false;
        for (int next : graph.get(curr)) {
            temp = isCycle(graph, next, path, memo, res);
            
            if (temp) {
                break;
            }
        }
        
        path[curr] = false;
        
        memo.put(curr, temp);
        
        res.add(curr);
        
        return temp;
    }
}