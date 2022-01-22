class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        
        int sum = 0;
        for (int i : nums) sum += i;
        
        if (sum % 2 == 1) return false;
        
        sum /= 2;
        
        // f(i, j) = f(i - 1, j - nums[k])
        
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = true;
        
        for (int i = 1; i <= n; i++) {
           for (int j = 1; j <= sum; j++) {
               if (j < nums[i - 1]) {
                   dp[i][j] = dp[i - 1][j];
               } else {
                   dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
               }
           } 
        }
        
        return dp[n][sum];
    }
}