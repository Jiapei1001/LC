class Solution {
    
    public int combinationSum4(int[] nums, int target) {
        Map<Integer, Integer> memo = new HashMap<>();
        
        return dfs(nums, target, memo);
    }

    private int dfs(int[] nums, int remain, Map<Integer, Integer> memo) {
        if (remain == 0) {
            return 1;
        }
        
        if (memo.containsKey(remain)) {
            return memo.get(remain);
        }
        
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (remain >= nums[i]) {
                cnt += dfs(nums, remain - nums[i], memo);
            }
        }
        
        memo.put(remain, cnt);
        
        return cnt;
    }
    
    
    // bottom up
    /*
    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        
        // f(i) := the number of combinations that sum up to i
        // f(i) = f(i - nums[k]) + 1, where k is an element of nums
        int[] dp = new int[target + 1];
        
        // base case: 每一个数被counted as 1
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
    */
}