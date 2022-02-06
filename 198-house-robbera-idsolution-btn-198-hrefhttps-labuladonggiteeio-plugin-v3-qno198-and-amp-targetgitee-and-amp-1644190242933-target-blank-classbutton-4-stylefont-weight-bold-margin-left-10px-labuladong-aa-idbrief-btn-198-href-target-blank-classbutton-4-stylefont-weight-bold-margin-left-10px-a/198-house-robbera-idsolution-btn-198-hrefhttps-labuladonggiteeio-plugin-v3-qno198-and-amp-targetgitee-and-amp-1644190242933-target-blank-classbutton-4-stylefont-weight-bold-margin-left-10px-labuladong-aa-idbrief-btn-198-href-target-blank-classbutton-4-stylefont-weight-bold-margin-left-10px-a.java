class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        
        return dfs(nums, memo, 1);
    }
    
    private int dfs(int[] nums, int[] memo, int s) {
        if (s > nums.length) {
            return 0;
        }
        
        if (memo[s] != -1) {
            return memo[s];
        }
        
        int res = 0;
        res = Math.max(dfs(nums, memo, s + 1),                   // don't take
                       dfs(nums, memo, s + 2) + nums[s - 1]);    // take
        
        memo[s] = res;
        return res;
    }
}