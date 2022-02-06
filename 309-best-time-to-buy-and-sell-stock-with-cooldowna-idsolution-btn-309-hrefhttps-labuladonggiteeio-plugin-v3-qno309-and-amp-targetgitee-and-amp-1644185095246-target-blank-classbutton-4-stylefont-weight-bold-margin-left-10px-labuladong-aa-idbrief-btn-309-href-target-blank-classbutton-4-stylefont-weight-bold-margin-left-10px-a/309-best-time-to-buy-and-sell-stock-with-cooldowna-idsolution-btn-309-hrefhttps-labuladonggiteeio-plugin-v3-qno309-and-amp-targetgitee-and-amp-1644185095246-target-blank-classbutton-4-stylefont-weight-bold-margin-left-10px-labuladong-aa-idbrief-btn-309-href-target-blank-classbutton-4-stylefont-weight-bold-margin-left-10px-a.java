class Solution {
    public int maxProfit(int[] prices) {
        
        int n = prices.length;
        
        // buy[i] = sell[i - 1] > 0, 10000
        //          Math.max(buy[i - 1], sell[i - 2] - prices[i])
        // sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i])
        
        int[] buy = new int[n + 1];
        int[] sell = new int[n + 1];
        
        buy[1] = -prices[0];
        sell[1] = 0;
        
        for (int i = 2; i <= n; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i - 1]);
            
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i - 1]);
        }
        
        return sell[n];
    }
}