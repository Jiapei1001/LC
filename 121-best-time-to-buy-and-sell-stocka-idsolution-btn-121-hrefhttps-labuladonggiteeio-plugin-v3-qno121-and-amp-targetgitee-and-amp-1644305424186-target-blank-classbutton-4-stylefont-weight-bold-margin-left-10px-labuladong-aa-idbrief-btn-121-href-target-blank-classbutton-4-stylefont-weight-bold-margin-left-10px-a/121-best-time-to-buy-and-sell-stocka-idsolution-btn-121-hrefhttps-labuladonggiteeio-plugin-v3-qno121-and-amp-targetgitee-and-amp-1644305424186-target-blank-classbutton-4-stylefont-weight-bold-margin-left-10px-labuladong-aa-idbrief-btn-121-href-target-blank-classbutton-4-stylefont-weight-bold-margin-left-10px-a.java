class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        
        int[][] dp = new int[n + 1][2];
        
        // f(i, 1) = Math.max(f(i - 1, 1), f(i - 1, 0) - prices[i])
        // f(i, 0) = Math.max(f(i - 1, 0), f(i - 1, 1) + prices[i])
        
        int dpi_0 = 0;
        int dpi_1 = Integer.MIN_VALUE;
            
        for (int i = 1; i <= n; i++) {
            // NOTE: 这里千万不能是dp[i - 1][0] - prices[i - 1]
            // 因为这样代表着无限可能！！
            // dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i - 1]);
            int temp = dpi_1;
            dpi_1 = Math.max(dpi_1, - prices[i - 1]);
            // dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
            dpi_0 = Math.max(dpi_0, temp + prices[i - 1]);
        }
        
        return dpi_0;
    }
    
    
    /*
    public int maxProfit(int[] prices) {
        int n = prices.length;
        
        int[][] dp = new int[n + 1][2];
        
        // f(i, 1) = Math.max(f(i - 1, 1), f(i - 1, 0) - prices[i])
        // f(i, 0) = Math.max(f(i - 1, 0), f(i - 1, 1) + prices[i])
        
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
            
        for (int i = 1; i <= n; i++) {
            // NOTE: 这里千万不能是dp[i - 1][0] - prices[i - 1]
            // 因为这样代表着无限可能！！
            dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i - 1]);
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
        }
        
        return dp[n][0];
    }
    */
    
    
    /*
    public int maxProfit(int[] prices) {
        int n = prices.length;
        
        int[] leftMin = new int[n];
        leftMin[0] = prices[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], prices[i]);
        }
        
        int[] rightMax = new int[n];
        rightMax[n - 1] = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], prices[i]);
        }
        
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (rightMax[i] > leftMin[i]) {
                res = Math.max(res, rightMax[i] - leftMin[i]);
            }
        }
        
        return res;
    }
    */
}