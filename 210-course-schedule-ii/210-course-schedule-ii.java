class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        for (int i = 0; i < numCourses; i++) {
            graph.putIfAbsent(i, new HashSet<Integer>());
        }
        
        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);
        }
        
        Set<Integer> visited = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        
        boolean[] path = new boolean[numCourses];
        
        // DFS
        for (int i = 0; i < numCourses; i++) {
            if (!visited.contains(i)) {
                if (dfs(graph, i, path, visited, res)) {
                    return new int[]{};
                }
            }
        }
        
        Collections.reverse(res);
        
        return res.stream().mapToInt(i -> i).toArray();
    }
    
    
    private boolean dfs(Map<Integer, Set<Integer>> graph,
                        int curr,
                        boolean[] path,
                        Set<Integer> visited,
                        List<Integer> res) {
        
        if (visited.contains(curr)) {
            return false;
        }
        
        if (path[curr]) {
            return true;
        }
        
        path[curr] = true;
        
        boolean temp = false;
        for (int next : graph.get(curr)) {
            temp = dfs(graph, next, path, visited, res);
            
            if (temp) {
                break;
            }
        }
        
        path[curr] = false;
        
        
        visited.add(curr);
        
        res.add(curr);
        
        return temp;
    }
}