class Solution {
    
    int res = 0;
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public int uniquePathsIII(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        int[] start = new int[2];
        int[] end = new int[2];
        int size = 0;
        
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 1) {
                    start[0] = r;
                    start[1] = c;
                }
                if (grid[r][c] == 2) {
                    end[0] = r;
                    end[1] = c;
                }
                if (grid[r][c] == 0) {
                    size++;
                }
            }
        
        this.dfs(grid, start[0], start[1], end[0], end[1], size + 1);
        
        return res;
    }
    
    private void dfs(int[][] grid, int r, int c, int tR, int tC, int size) {
        if (r == tR && c == tC && size == 0) {
            res++;
            return;
        }
        if (size <= 0) {
            return;
        }
        
        grid[r][c] = -1;
        
        for (int[] dir : dirs) {
            int newR = r + dir[0];
            int newC = c + dir[1];
            if (newR < 0 || newR >= grid.length || newC < 0 || newC >= grid[0].length) {
                continue;
            }
            if (grid[newR][newC] == -1) {
                continue;
            }
            this.dfs(grid, newR, newC, tR, tC, size - 1);
        }
        
        grid[r][c] = 0;
    }
}