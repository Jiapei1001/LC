class Solution {
    
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    String[] dirStr = {"R", "L", "D", "T"}; 
    int n, m;
    
    public int numDistinctIslands(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        
        Set<String> unique = new HashSet<>();
        
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 1) {
                    unique.add(dfs(grid, r, c, "O"));
                }
            }
        
        return unique.size();
    }
    
    private String dfs(int[][] grid, int r, int c, String curr) {
        if (grid[r][c] == 0) return "";
        
        // mark as visited
        grid[r][c] = 0;
        
        // StringBuilder sb = new StringBuilder(curr);
        String temp = curr;
        
        for (int i = 0; i < 4; i++) {
            int nr = r + dirs[i][0];
            int nc = c + dirs[i][1];
            
            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
            // if (grid[nr][nc] != 1) continue;
            
            temp += dfs(grid, nr, nc, dirStr[i]);
        }
        
        temp += "*";
        
        return temp;
    }
    
    
    /*
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int n = grid.length;
        int m = grid[0].length;
        
        boolean[][] visited = new boolean[n][m];
        Set<String> res = new HashSet<>();
        
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 1 && !visited[r][c]) {
                    visited[r][c] = true;
                    String temp = this.dfs(grid, visited, r, c, "S");
                    res.add(temp);
                }
            }
        
        return res.size();
    }
    
    private String dfs(int[][] grid,
                     boolean[][] visited,
                     int r,
                     int c,
                     String curr) {
        String s = curr;
        
        for (int i = 0; i < 4; i++) {
            int newR = r + dirs[i][0];
            int newC = c + dirs[i][1];
            
            if (newR < 0 || newR >= grid.length || newC < 0 || newC >= grid[0].length)
                continue;
            
            if (grid[newR][newC] != 1)
                continue;
            
            if (visited[newR][newC])
                continue;
            
            visited[newR][newC] = true;
            s += this.dfs(grid, visited, newR, newC, dirStr[i]);
        }
        
        s += "S";
        
        return s;
    }
    */
}