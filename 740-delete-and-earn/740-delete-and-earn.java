class Solution {
    public int deleteAndEarn(int[] nums) {
        int n = nums.length;
        
        // f(i) := the maximum points can get for the front i elements, where i <=> num
        // f(i)  = MAX { f(i - 1), don't take
        //               f(i - 2) + nums[i], take }
        
        int[] buckets = new int[10001];
        
        for (int num : nums) {
            buckets[num] += num;
        }
        
        int[] dp = new int[10001];
        
        // base case
        dp[0] = buckets[0];
        dp[1] = buckets[1];
        
        for (int i = 2; i <= 10000; i++) {
            dp[i] = Math.max(dp[i - 2] + buckets[i], dp[i - 1]);
        }
        
        return dp[10000];
    }
}