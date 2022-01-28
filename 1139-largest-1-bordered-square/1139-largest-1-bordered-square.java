class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        
        int n = grid.length, m = grid[0].length;
        
        int[][] h = new int[n][m];
        int[][] v = new int[n][m];
        
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (c == 0) h[r][c] = grid[r][c];
                else h[r][c] = grid[r][c] == 0 ? 0 : h[r][c - 1] + 1;
                
                if (r == 0) v[r][c] = grid[r][c];
                else v[r][c] = grid[r][c] == 0 ? 0 : v[r - 1][c] + 1;
            }
        
        int res = 0;
        
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (h[r][c] > 0 && v[r][c] > 0) {
                    
                    // NOTE: here get the min value
                    int temp = Math.min(h[r][c], v[r][c]);
                    
                    // NOTE: here must add additional loop, from temp -> 1
                    // without this loop will trigger error!!
                    // 因为不一定要只取最大的temp，比temp小的数也可能形成Square
                    for (int k = temp; k >= 1; k--) {
                        if (r >= k - 1 && h[r - k + 1][c] >= k &&
                            c >= k - 1 && v[r][c - k + 1] >= k) {
                            res = Math.max(res, k);
                            break;
                        }
                    }
                }
            }
        
        return res * res;
    }
}