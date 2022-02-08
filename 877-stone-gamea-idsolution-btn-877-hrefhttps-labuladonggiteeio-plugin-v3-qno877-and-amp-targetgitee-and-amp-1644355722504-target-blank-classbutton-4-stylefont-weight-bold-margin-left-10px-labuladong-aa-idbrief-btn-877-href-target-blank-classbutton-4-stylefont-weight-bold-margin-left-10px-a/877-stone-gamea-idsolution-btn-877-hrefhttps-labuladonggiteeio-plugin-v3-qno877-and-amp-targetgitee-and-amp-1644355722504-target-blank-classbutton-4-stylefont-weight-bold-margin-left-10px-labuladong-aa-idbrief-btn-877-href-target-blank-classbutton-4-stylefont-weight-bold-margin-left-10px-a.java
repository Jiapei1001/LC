class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        
        int sum = 0;
        for (int i : piles) sum += i;
        
        // f(i, j) = piles[i] + 
        //                 if piles[i + 1] > piles[j], + f(i + 2, j)
        //                 else                      , + f(i + 1, j - 1)
        //           piles[j] + f(i, j - 1)
        //                 if piles[i] > piles[j - 1], + f(i + 1, j - 1)
        //                 else                      , + f(i, j - 2)
        
        int[][][] dp = new int[n][n][2];
        
        // f(i, j, 0) the first one
        // f(i, j, 1) the second one
        
        for (int i = 0; i < n; i++) {
            // get all
            dp[i][i][0] = piles[i];
            // get nothing
            dp[i][i][1] = 0;
        }
        
        for (int l = 2; l <= n; l++)
            for (int i = 0; i + l - 1 < n; i++) {
                int j = i + l - 1;
                
                // first, after take, becomes second
                int takeLeft = piles[i] + dp[i + 1][j][1];
                int takeRight = piles[j] + dp[i][j - 1][1];
                
                if (takeLeft >= takeRight) {
                    dp[i][j][0] = takeLeft;
                    // second becomes first, in shrinked range
                    dp[i][j][1] = dp[i + 1][j][0];
                } else {
                    dp[i][j][0] = takeRight;
                    dp[i][j][1] = dp[i][j - 1][0];
                }
            }
        
        // compare first and second
        return dp[0][n - 1][0] > dp[0][n - 1][1];
    }
}