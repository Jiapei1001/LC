class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int[] dp = new int[nums.length];
        int[] counts = new int[nums.length];
        Arrays.fill(counts, 1);
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // check all possible when nums[j] < nums[i]
                if (nums[j] < nums[i]) {
                    // the first time when dp[i] = dp[j] + 1
                    // update the dp
                    // count should be the same as count[j], as the first time met
                    if (dp[j] >= dp[i]) {
                        dp[i] = dp[j] + 1;
                        counts[i] = counts[j];
                    } 
                    // the second or more time met;
                    // mean when j is looping, there were former path to make i the same longest
                    // we should add up to counts[i]
                    // no need to update dp[i], as dp[i] has been formerly updated
                    else if (dp[j] + 1 == dp[i]) {
                        counts[i] += counts[j];
                    }
                }
            }
        }
        int longest = 0, ans = 0;
        for (int length : dp) {
            longest = Math.max(longest, length);
        }
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] == longest) {
                ans += counts[i];
            }
        }
        
        return ans;
    }
}
