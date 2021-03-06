class Solution {
    
    private static int MOD = (int) Math.pow(10, 9) + 7;

    public int numWays(int steps, int arrLen) {
//         f(i, j) := the number of ways at postion j, for the front ith number of steps
        
//         f(i, j) = f(i - 1, j),      stay
//                   f(i - 1, j + 1),  left, <-, where j + 1 <= arrLen - 1
//                   f(i - 1, j - 1),  right, ->, where j - 1 >= 0
        
//         f(0, j) = 1, as initial step, where 0 <= j <= arrLen - 1
//         f(i, 0) = 0
        
        int maxPos = Math.min(steps, arrLen);
        
        long[] dp = new long[maxPos];
        
        // base case: step as 0, at position of 0 (index of 0)
        dp[0] = 1;
        
        for (int i = 1; i <= steps; i++) {
            long[] next = new long[maxPos];
            for (int j = 0; j < maxPos; j++) {                
                next[j] = (dp[j] +
                           ((j + 1) <= maxPos - 1 ? (dp[j + 1]) : 0) +
                           ((j - 1) >= 0 ? (dp[j - 1]) : 0)) % MOD;
            }
            dp = next;
        }
        
        return (int) dp[0];
    }
    
    
    // Recursion 正确，但是会Memory Limit Exceeded！！
    /*
    public int numWays(int steps, int arrLen) {
//         f(i, j) := the number of ways at postion j, for the front ith number of steps
        
//         f(i, j) = f(i - 1, j),      stay
//                   f(i - 1, j + 1),  left, <-, where j + 1 <= arrLen - 1
//                   f(i - 1, j - 1),  right, ->, where j - 1 >= 0
        
//         f(0, j) = 1, as initial step, where 0 <= j <= arrLen - 1
//         f(i, 0) = 0
            
        long[][] dp = new long[steps + 1][arrLen];
        
        // base case: step as 0, at position of 0 (index of 0)
        dp[0][0] = 1;
        
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j < arrLen; j++) {
                // dp[i][j] += (dp[i - 1][j]) % MOD;
                // dp[i][j] += ((j + 1) <= arrLen - 1 ? (dp[i - 1][j + 1]) : 0) % MOD;
                // dp[i][j] += ((j - 1) >= 0 ? (dp[i - 1][j - 1]) : 0) % MOD;
                
                dp[i][j] = (dp[i - 1][j] +
                            ((j + 1) <= arrLen - 1 ? (dp[i - 1][j + 1]) : 0) +
                            ((j - 1) >= 0 ? (dp[i - 1][j - 1]) : 0)) % MOD;
            }
        }
        
        return (int) dp[steps][0];
    }
    */
}