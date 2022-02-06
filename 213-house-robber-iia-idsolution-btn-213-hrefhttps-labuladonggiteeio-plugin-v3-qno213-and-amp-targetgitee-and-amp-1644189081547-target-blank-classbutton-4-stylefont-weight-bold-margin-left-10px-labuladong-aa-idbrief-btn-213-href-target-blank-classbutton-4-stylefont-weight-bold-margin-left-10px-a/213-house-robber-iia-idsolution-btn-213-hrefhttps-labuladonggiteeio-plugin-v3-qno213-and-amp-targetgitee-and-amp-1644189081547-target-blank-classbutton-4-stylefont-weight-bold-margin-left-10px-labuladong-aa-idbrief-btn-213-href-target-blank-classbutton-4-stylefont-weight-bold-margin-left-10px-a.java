class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        
        int[] dp0 = new int[n + 1];
        int[] dp1 = new int[n + 1];
        
        // f(i) = MAX{ f(i - 1), f(i - 2) + nums[i] }, don't take or take
        
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                dp0[1] = 0;
                dp1[1] = nums[0];
                continue;
            }
            dp0[i] = Math.max(dp0[i - 1], dp0[i - 2] + nums[i - 1]);
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i - 1]);
        }
        
        return Math.max(dp0[n], dp1[n - 1]);
    }
}