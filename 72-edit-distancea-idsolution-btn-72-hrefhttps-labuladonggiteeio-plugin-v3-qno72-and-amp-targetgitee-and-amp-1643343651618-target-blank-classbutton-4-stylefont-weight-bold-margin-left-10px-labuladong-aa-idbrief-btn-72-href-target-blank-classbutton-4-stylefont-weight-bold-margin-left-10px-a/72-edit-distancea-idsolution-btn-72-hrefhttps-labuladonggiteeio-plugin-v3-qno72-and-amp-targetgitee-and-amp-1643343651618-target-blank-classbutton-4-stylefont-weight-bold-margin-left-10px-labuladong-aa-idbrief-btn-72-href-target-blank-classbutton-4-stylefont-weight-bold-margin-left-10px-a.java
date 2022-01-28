class Solution {
    
        public int minDistance(String word1, String word2) {
        // f(i, j) := the minimum number of operations to make word1 and word2 equal,
        //            for the front i and j number of characters
        // insert
        // f(i, j - 1) + 1
        // delete
        // f(i - 1, j) + 1
        // replace
        // f(i - 1, j - 1) + 1
        
        int n = word1.length();
        int m = word2.length();
        
        int[][] dp = new int[n + 1][m + 1];
        
        // Base case:
        for (int[] row : dp) Arrays.fill(row, 1000);
        
        // NOTE: don't forget the origin point is 0
        // easy to forget after filling above!!
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) dp[i][0] = dp[i - 1][0] + 1;
        for (int j = 1; j <= m; j++) dp[0][j] = dp[0][j - 1] + 1;
        
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // insert
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                    // delete
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                    // replace
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        
        return dp[n][m];
    }
    
    
    // Modular method
    /*
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        int[][] dp = new int[2][m + 1];
        for (int c = 0; c <= m; c++) dp[0][c] = c;
        
        for (int r = 1; r <= n; r++) {
            dp[r % 2][0] = r;
            for (int c = 1; c <= m; c++) {
                // insert dp[r][c - 1] + 1
                // delete dp[r - 1][c] + 1
                dp[r % 2][c] = Math.min(dp[(r - 1) % 2][c], dp[r % 2][c - 1]) + 1;
                // replace dp[r - 1][c - 1]
                if (word1.charAt(r - 1) == word2.charAt(c - 1)) {
                    dp[r % 2][c] = Math.min(dp[r % 2][c], dp[(r - 1) % 2][c- 1]);
                } else {
                    dp[r % 2][c] = Math.min(dp[r % 2][c], dp[(r - 1) % 2][c- 1] + 1);
                }
            }
        }
        return dp[n % 2][m];
    }
    */
}
