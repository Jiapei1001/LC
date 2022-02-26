class Solution {
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // build graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] p : prerequisites) {
            graph.computeIfAbsent(p[1], a -> new HashSet<>()).add(p[0]);
        }
        
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (hasCycle(graph, i, visited, recStack, res)) {
                    return new int[]{};
                }
            }
        }
        
        Collections.reverse(res);
        
        return res.stream().mapToInt(a -> a).toArray();
    }
    
    private boolean hasCycle(Map<Integer, Set<Integer>> graph, int curr, boolean[] visited,
                             boolean[] recStack, List<Integer> res) {
        // NOTE: 这里必须先判断recStack！！再判断visited！！
        if (recStack[curr]) return true;
        
        if (visited[curr]) return false;
        
        visited[curr] = true;
        recStack[curr] = true;
        
        for (int next : graph.getOrDefault(curr, new HashSet<>())) {
            if (hasCycle(graph, next, visited, recStack, res)) {
                return true;
            }
        }
        
        recStack[curr] = false;
        res.add(curr);
        
        return false;
    }
    
    
    
    // DFS
    /*
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
    */
}