class Solution {
    
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        
        int[] dp = new int[n];
        dp[0] = nums[0];
        
        for (int i = 1; i < n; i++) {
            dp[i] = nums[i] + (dp[i - 1] < 0 ? 0 : dp[i - 1]);
        }
        
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        
        return res;
    }
    
    
    /*
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int acc = 0;
        
        for (int n : nums) {
            acc += n;
            res = Math.max(res, acc);
            acc = Math.max(acc, 0);
        }
        
        return res;
    }
    */
}