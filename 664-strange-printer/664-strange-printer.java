class Solution {
    
    public int strangePrinter(String s) {
        
        int n = s.length();
        
        // f(i, j) = f(i, k) + f(k, j), if char(k - 1) != char(k + 1)
        //           f(i, k) + f(k, j) - 1, if char(k - 1) == char(k + 1)
        
        int[][] dp = new int[n][n];
        for (int[] a : dp) Arrays.fill(a, Integer.MAX_VALUE);
        
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            if (i < n - 1) {
                dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 1 : 2;
            }
        }
        
        // [i, j]
        for (int l = 3; l <= n; l++)
            for (int i = 0; i + l - 1 < n; i++) {
                int j = i + l - 1;
                
                for (int k = i; k < j; k++) {
                    int temp = dp[i][k] + dp[k + 1][j];
                    
                    if (s.charAt(i) == s.charAt(j)) temp--;
                    
                    dp[i][j] = Math.min(dp[i][j], temp);
                }
            }
        
        return dp[0][n - 1];
    }
}