class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        
        if (n == 1) return 0;
        
        /*
        f(i, 0) = f(i - 1, 0) or f(i - 1, 1) + prices[i];       // don't sell, hold; sell
        f(i, 1) = f(i - 1, 1) or f(i - 1, 0) - prices[i] - fee; // don't buy, hold; buy, initiate a new transaction
        */
        
        int[][] dp = new int[n + 1][2];
        
        // sell is impossible
        dp[1][0] = 0;
        // buy, new transaction
        dp[1][1] = - prices[0] - fee;
        
        for (int i = 2; i <= n; i++) {
            // buy
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i - 1] - fee);
            // sell
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
        }
        
        return dp[n][0] < 0 ? 0 : dp[n][0];
    }
}