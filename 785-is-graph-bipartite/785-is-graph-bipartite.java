class Solution {
    
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                if (!dfs(graph, i, -1, color)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean dfs(int[][] graph, int i, int currColor, int[] color) {
        if (color[i] != 0 && color[i] != currColor) return false;
        color[i] = currColor;
        
        for (int next : graph[i]) {
            if (color[next] == 0) {
                if (!dfs(graph, next, -currColor, color)) {
                    return false;
                }
            } else if (color[next] != -currColor) {
                return false;
            }
        }
        
        return true;
    }
    
    
    /*
    public boolean isBipartite(int[][] graph) {
        // coloring + DFS
        
        int n = graph.length;
        int[] color = new int[n];
        // mark as uncolored
        Arrays.fill(color, -1);
        
        for (int i = 0; i < n; i++) {
            // uncolor
            if (color[i] == -1) {
                color[i] = 0;
                
                Stack<Integer> stack = new Stack<>();
                stack.push(i);
                
                while (!stack.isEmpty()) {
                    int curr = stack.pop();
                    
                    for (int next : graph[curr]) {
                        if (color[next] == -1) {
                            // 重点 OR
                            color[next] = color[curr] ^ 1;
                            stack.push(next);
                        } else if (color[next] == color[curr]) {
                            return false;
                        }
                    }
                }
            }
        }
        
        return true;
    }
    */
}