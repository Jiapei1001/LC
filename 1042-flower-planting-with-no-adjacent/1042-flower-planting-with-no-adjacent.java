class Solution {
    
    int N;
    boolean isValid;
    
    public int[] gardenNoAdj(int n, int[][] paths) {
        N = n;
        
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        for (int[] p : paths) {
            graph.computeIfAbsent(p[0], a -> new HashSet<>()).add(p[1]);
            graph.computeIfAbsent(p[1], a -> new HashSet<>()).add(p[0]);
        }
        
        int[] color = new int[n + 2];
        
        dfs(graph, 1, color);
        
        // return isValid ? Arrays.copyOfRange(color, 1, n + 1) : null;
        return Arrays.copyOfRange(color, 1, n + 1);
    }
    
    //    /-\    
    // 1-2-3-4
    //  \_/ /
    //   \_/
    
    private boolean dfs(Map<Integer, Set<Integer>> graph, int i, int[] color) {
        if (i > N) {
            return true;
        }
        
        for (int c = 1; c <= 4; c++) {
            if (isValid(graph, i, c, color)) {
                color[i] = c;
                
                if (dfs(graph, i + 1, color)) {
                    return true;
                }
                
                // will never happen
                color[i] = 0;
            }
        }
        return false;
    }
    
    private boolean isValid(Map<Integer, Set<Integer>> graph, int i, int c, int[] color) {
        for (int next : graph.getOrDefault(i, new HashSet<>())) {
            if (color[next] == c) {
                return false;
            }
        }
        
        return true;
    }
}