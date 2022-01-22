class Solution {
    public int combinationSum4(int[] nums, int target) {
        
        int n = nums.length;
        
        // f(i) := the number of combinations that sum up to i
        // f(i) = f(i - nums[k]) + 1, where k is an element of nums
        
        int[] dp = new int[target + 1];
        
        // base
        // Arrays.fill(dp, -1);
        for (int i = 0; i < n; i++) {
            if (nums[i] <= target) dp[nums[i]] = 1;
        }
        
        dp[0] = 0;
        
        for (int i = 1; i <= target; i++) {
            for (int k = 0; k < n; k++) {
                if (i >= nums[k] && dp[i - nums[k]] > 0) {
                    // NOTE: 这里不是比较最大值
                    // dp[i] = Math.max(dp[i], dp[i - nums[k]] + 1);
                    
                    // NOTE: 这里是base case为1
                    // 可以通过[1,2,3]，target = 2，去想
                    dp[i] += dp[i - nums[k]];
                }
            }
        }
        
        return dp[target];
    }
}