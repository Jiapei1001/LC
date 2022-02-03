class Solution {
    
    // Top Down
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        
        int[][] memo = new int[n][m];
        
        for (int[] row : memo) Arrays.fill(row, 100000000);
        
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            res = Math.min(res, dfs(matrix, n - 1, j, memo));
        }
        
        return res;
    }
    
    private int dfs(int[][] matrix, int i, int j, int[][] memo) {
        // first row
        if (i == 0 && j >= 0 && j < matrix[0].length) {
            return matrix[i][j];
        }
        
        // out of boundary
        if (i < 0 || j < 0 || j >= matrix[0].length) {
            return Integer.MAX_VALUE;
        }
        
        if (memo[i][j] != 100000000) {
            return memo[i][j];
        }
        
        memo[i][j] = matrix[i][j] + 
            Math.min(dfs(matrix, i - 1, j, memo), 
                     Math.min(dfs(matrix, i - 1, j - 1, memo),
                              dfs(matrix, i - 1, j + 1, memo))
                    );
        
        return memo[i][j];
    }
    
    
    // Bottom Up
    /*
    public int minFallingPathSum(int[][] matrix) {
        
        int n = matrix.length, m = matrix[0].length;
        
        // f(i, j) = matrix[i][j] + MIN{ f(i - 1, j - 1) or f(i - 1, j) or f(i - 1, j + 1) };
        
        int[][] dp = new int[n][m];
        
        for (int j = 0; j < m; j++) dp[0][j] = matrix[0][j];
        
        for (int i = 1; i < n; i++)
            for (int j = 0; j < m; j++) {
                int temp = Math.min(dp[i - 1][j], j >= 1 ? dp[i - 1][j - 1] : Integer.MAX_VALUE);
                temp = Math.min(temp, j < n - 1 ? dp[i - 1][j + 1] : Integer.MAX_VALUE);
                
                dp[i][j] = matrix[i][j] + temp;
            }
        
        int res = Integer.MAX_VALUE;
        
        for (int j = 0; j < m; j++) {
            res = Math.min(res, dp[n - 1][j]);
        }
        
        return res;
    }
    */
}