class Solution {
    
    public int maxProfit(int[] prices) {
        // f(i) := the maximum profit can get for the front i element
        
        int n = prices.length;
        
        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            
            int minV = Integer.MAX_VALUE;
            
            for (int j = i; j >= 1; j--) {
                minV = Math.min(minV, prices[j - 1]);
                
                dp[i] = Math.max(dp[i], dp[j] + prices[i - 1] - minV);
            }
        }
        
        return dp[n];
    }
    
    
    /*
    public int maxProfit(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        
        while ( i < prices.length - 1) {
            while ( i < prices.length - 1 && prices[i] >= prices[i + 1]) 
                i++;
            valley = prices[i];
            
            while ( i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            
            maxprofit += peak - valley;
        }
        
        return maxprofit;
        
    }
    */
}
