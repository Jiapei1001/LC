class NumMatrix {
    int n, m;
    int[][] dp;
    
    public NumMatrix(int[][] matrix) {
        n = matrix.length;
        m = matrix[0].length;
        
        dp = new int[n + 1][m + 1];
        
        for (int r = 1; r <= n; r++)
            for (int c = 1; c <= m; c++) {
                dp[r][c] = matrix[r - 1][c - 1] + 
                           dp[r - 1][c]         + 
                           dp[r][c - 1]         - 
                           dp[r - 1][c - 1];
            }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        // NOTE: 注意 i，j 代表的是什么，然后 +1 或者 -1
        // (row1 - 1) + 1, (col1 - 1) + 1
        return dp[row2 + 1][col2 + 1] - 
               dp[row1][col2 + 1]     - 
               dp[row2 + 1][col1]     + 
               dp[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */