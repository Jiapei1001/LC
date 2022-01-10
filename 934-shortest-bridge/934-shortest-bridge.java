class Solution {
    
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int shortestBridge(int[][] grid) {
        // union find
        // get points that are beside 0, enqueue
        // find 1 that are not in the same group, return step
        
        int n = grid.length, m = grid[0].length;
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        outer:
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 1) {
                    dfs(grid, r, c, q, visited);
                    
                    // NOTE: must break outer!!
                    break outer;
                }
            }
        
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                
                for (int[] dir : dirs) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    
                    if (nr < 0 || nr >= grid.length || nc < 0 || nc >= grid[0].length) {
                        continue;
                    }
                    
                    if (!visited[nr][nc]) {
                        // NOTE: when check == 1, must haven't been visited!!
                        if (grid[nr][nc] == 1) {
                            return step;
                        }
                        
                        q.offer(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
            
            step++;
        }
        
        return -1;
    }
    
    private void dfs(int[][] grid, int r, int c, Queue<int[]> q, boolean[][] visited) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || visited[r][c]){
            return;
        }
        
        // only combine 1
        if (grid[r][c] == 0) {
            return;
        }
        
        q.offer(new int[]{r, c});
        visited[r][c] = true;
        
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            
            dfs(grid, nr, nc, q, visited);
            
            // (grid, r + dir[0], c + dir[1], q, visited);
        }
    }
}