class Solution {
    public int minSwap(int[] nums1, int[] nums2) {
        // f(i, j) := the minimum operations to meet requirements
        // f(i, j)  = 
        //     when nums1[i] <= nums1[i - 1] || nums2[i] <= nums2[i - 1], 
        //         if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]),
        //             f(i, j) = f(i - 1, j - 1) + 1;
        
        int n = nums1.length;
        
        int[][] dp = new int[n][2];
        
        for (int[] r : dp) Arrays.fill(r, 10000);
        dp[0][0] = 0;
        dp[0][1] = 1;
        
        for (int i = 1; i < n; i++) {
            boolean selfIncreasing = nums1[i - 1] < nums1[i] && nums2[i - 1] < nums2[i];
            boolean interwaveIncreasing = nums1[i - 1] < nums2[i] && nums2[i - 1] < nums1[i];
            
            if (selfIncreasing && interwaveIncreasing) {
                // don't swap, 前面swap与否不影响现在
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + 1;
            } else if (selfIncreasing) {
                // 并不interwave，前面don't swap，这个don't swap，
                // 不可能前面swap，这个不swap
                dp[i][0] = dp[i - 1][0];
                // 并不interwave，前面swap，这个也swap，
                // 不可能前面don't swap，这个swap
                dp[i][1] = dp[i - 1][1] + 1;
            } else if (interwaveIncreasing) {
                // 前面swap，这个可以不swap
                dp[i][0] = dp[i - 1][1];
                // 前面不swap，这个必须swap
                dp[i][1] = dp[i - 1][0] + 1;
            }
        }
        
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }
}