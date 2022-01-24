class Solution {
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
}