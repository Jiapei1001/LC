class Solution {
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
}