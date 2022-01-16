class Solution {
    
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        
        int res = 0;
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 0) {
                    continue;
                }
                
                res = Math.max(res, dfs(grid, r, c));
            }
        
        return res;
    }
    
    private int dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
            return 0;
        }
        
        if (grid[r][c] == 0) {
            return 0;
        }
        
        grid[r][c] = 0;
        
        return 1 + dfs(grid, r + 1, c) + dfs(grid, r - 1, c) + dfs(grid, r, c + 1) + dfs(grid, r, c - 1);
    }
}