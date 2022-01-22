class Solution {
    public int coinChange(int[] coins, int amount) {
        
        int n = coins.length;
        
        // dp(i) = min {dp(i - A[k]) + 1}, where k is one of the coins
        
        int[] dp = new int[amount + 1];
        
        // base case
        Arrays.fill(dp, 100000000);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int k = 0; k < n; k++) {
                if (i >= coins[k]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[k]] + 1);
                }
            }
        }
        
        return dp[amount] == 100000000 ? -1 : dp[amount];
    }
}