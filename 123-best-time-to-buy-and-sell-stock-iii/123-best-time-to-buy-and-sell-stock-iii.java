class Solution {
    
    public int maxProfit(int[] prices) {
        int n = prices.length;
        
        /*
        f(i, k, 0) := the maximum profit to get, for the front i days, k txns, with a sell
        f(i, k, 1) := buy
        
        f(i, k, 0) = MAX{ f(i - 1, k, 0), f(i - 1, k, 1) + prices[i] }      // don't sell, sell
        f(i, k, 1) = MAX{ f(i - 1, k, 1), f(i - 1, k - 1, 0) - prices[i] }  // don't buy, buy
        */
        
        int k = 2;
        
        int[][][] dp = new int[n + 1][k + 1][2];
        
        // base case
        for (int i = 1; i <= n; i++) {
            dp[i][0][0] = 0;                    // 0
            dp[i][0][1] = Integer.MIN_VALUE;    // cannot possible sell
        }
        
        for (int j = 1; j <= k; j++) {
            dp[0][j][0] = 0;
            dp[0][j][1] = Integer.MIN_VALUE;
        }
        
        for (int i = 1; i <= n; i++)
            for (int j = k; j >= 1; j--) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i - 1]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i - 1]);
            }
        
        return dp[n][k][0];
    }
    
    
    /*
    public int maxProfit(int[] prices) {
        // f(i) := the maximum profit can get for the front i element
        
        int n = prices.length;
        
        int[][] dp = new int[n + 1][3];
        
        for (int j = 1; j < 3; j++) {
            int maxDiffer = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                maxDiffer = Math.max(maxDiffer, dp[i - 1][j - 1] - prices[i - 1]);
                dp[i][j] = Math.max(dp[i - 1][j], maxDiffer + prices[i - 1]);
            }
        }
        
        return dp[n][2];
    }
    */
}