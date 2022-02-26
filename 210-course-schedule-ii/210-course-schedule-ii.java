class Solution {
    
    boolean hasCycle;
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // build graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] p : prerequisites) {
            graph.computeIfAbsent(p[1], a -> new HashSet<>()).add(p[0]);
        }
        
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        
        int[] color = new int[numCourses];
        hasCycle = false;
        
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            // visited + recStack
            if (!visited[i]) {
                if (hasCycle(graph, i, visited, recStack, res)) {
                    return new int[]{};
                }
            }
            
            // color
            /*
            if (color[i] == 0) {
                dfs(graph, i, color, res);
            }
            */
        }
        
        Collections.reverse(res);
        
        return res.stream().mapToInt(a -> a).toArray();
        // return hasCycle ? new int[]{} : res.stream().mapToInt(a -> a).toArray();
    }
    
    // color
    private void dfs(Map<Integer, Set<Integer>> graph, int curr, int[] color, List<Integer> res) {
        // ongoing
        color[curr] = 1;
        
        for (int next : graph.getOrDefault(curr, new HashSet<>())) {
            if (color[next] == 0) {
                dfs(graph, next, color, res);
            } else if (color[next] == 1) {
                hasCycle = true;
            }
        }
        
        // visited
        color[curr] = 2;
        res.add(curr);
    }
    
    // visited + recStack
    private boolean hasCycle(Map<Integer, Set<Integer>> graph, int curr, boolean[] visited,
                             boolean[] recStack, List<Integer> res) {
        // NOTE: 这里必须先判断recStack！！再判断visited！！
        if (recStack[curr]) return true;
        // 必须在判断recStack之后！！
        if (visited[curr]) return false;
        
        visited[curr] = true;
        recStack[curr] = true;
        
        for (int next : graph.getOrDefault(curr, new HashSet<>())) {
            // NOTE: 这里千万不能加 if (!visited[next]) ！！
            // 上面有bottom case，如果加了，无法进入下一层去找cycle！！
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