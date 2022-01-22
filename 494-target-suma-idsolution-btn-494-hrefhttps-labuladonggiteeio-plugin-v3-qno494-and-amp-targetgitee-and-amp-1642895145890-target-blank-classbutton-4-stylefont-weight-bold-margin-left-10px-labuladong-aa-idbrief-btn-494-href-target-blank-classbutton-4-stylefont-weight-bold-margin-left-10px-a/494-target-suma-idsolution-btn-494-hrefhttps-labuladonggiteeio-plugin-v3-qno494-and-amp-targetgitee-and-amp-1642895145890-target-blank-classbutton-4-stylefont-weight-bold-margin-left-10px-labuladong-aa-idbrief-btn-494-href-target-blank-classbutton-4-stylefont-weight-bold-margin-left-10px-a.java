class Solution {
    
    public int findTargetSumWays(int[] nums, int target) {
        Map<String, Integer> memo = new HashMap<>();
        
        return dfs(nums, 0, target, memo);
    }
    
    private int dfs(int[] nums, int i, int remain, Map<String, Integer> memo) {
        if (i == nums.length && remain == 0) {
            return 1;
        }
        if (i >= nums.length) {
            return 0;
        }
        
        if (memo.containsKey(i + "-" + remain)) {
            return memo.get(i + "-" + remain);
        }
        
        int count = 0;
        count += dfs(nums, i + 1, remain + nums[i], memo);
        count += dfs(nums, i + 1, remain - nums[i], memo);
        
        memo.put(i + "-" + remain, count);
        return count;
    }
}