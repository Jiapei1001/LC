class Solution {
    public double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        
//         f(i, j) := the probability of j number of coins facing heads, for the front ith coins
            
//         f(i, j)  = f(i - 1, j - 1) * prob[i], where this ith coin facing up
//                  + f(i - 1, j) * (1 - prob[i]), where this ith coin facing down
        
        // base case
        // f(1, 1) = f(0, 0) * prob[1] + f(0, 1) * (1 - prob[i])
        // thus f(0, 0) = 1
            
        double[][] dp = new double[n + 1][target + 1];
        dp[0][0] = 1.0;
        
        for (int i = 1; i <= n; i++) {
            // NOTE: 这个base case是硬币连续反面，因此是(1 - p)
            dp[i][0] = dp[i - 1][0] * (1 - prob[i - 1]);
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                // NOTE: 这里j一定要从0开始，这样可以满足base case，然后判断j - 1 < 0 or not！！
                dp[i][j] += dp[i - 1][j - 1] * prob[i - 1];
                
                dp[i][j] += dp[i - 1][j] * (1 - prob[i - 1]);
            }
        }
        
        return dp[n][target];
    }
}