class Solution {
    
    public int minScoreTriangulation(int[] values) {
        // n vertices, n edges, n / 2 triangles
        int n = values.length;
        int tn = n / 2;
        
        int[][] dp = new int[n][n];
//         for (int[] a : dp) {
//             Arrays.fill(a, 1000000);
//         }
        
//         for (int i = 0; i < n - 2; i++) {
//             dp[i][i + 2] = values[i] * values[i + 1] * (((i + 2) % n == 0) ? values[0] : values[i + 2]);
//         }
        
        // f(i, j) = min{f(i, k) + f(k, k + 2) + f(k + 2, j)}, i + 2 <= k <= j - 2
        
        for (int l = 3; l <= n; l++)
            for (int i = 0; i + l - 1 < n; i++) {
                int j = i + l - 1;
                
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + values[i] * values[k] * values[j]);
                }
            }
        
        return dp[0][n - 1];
    }
}