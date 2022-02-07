class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        
        // f(i) = f(j) + 1, where 1 <= j < i && nums[j] < nums[i]
        
        int[] dp = new int[n];
        
        dp[0] = 1;
        
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        
        return res;
    }
}