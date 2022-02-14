class Solution {
    public int maxResult(int[] nums, int k) {
//         f(j) = MAX{f(i) + nums[j]}, where j - k <= i <= j - 1
            
//         we want to get the max sum of the available values in the k window
        
//         decresing order, strict, as the newer one is closer to future state
        
        int n = nums.length;
        int[] dp = new int[n];
        
        Deque<int[]> monoq = new ArrayDeque<>();
        dp[0] = nums[0];
        // monoq.offer(new int[]{0, dp[0]});
        
        /*
        for (int i = 1; i < n; i++) {
            // NOTE: 因为是连续的，因此即使之前的dp[prev] < 0，也要相加
            dp[i] = nums[i] + monoq.peekFirst()[1];
            
            // make sure the max distance is k - 1,
            // to make this (k - 1) window available when i becomes i + 1
            while (!monoq.isEmpty() && monoq.peekFirst()[0] + k <= i) {
                monoq.pollFirst();
            }
            while (!monoq.isEmpty() && monoq.peekLast()[1] <= dp[i]) {
                monoq.pollLast();
            }
            
            monoq.offer(new int[]{i, dp[i]});
        }
        */
        
        for (int i = 0; i < n - 1; i++) {            
            // make sure the max distance is k - 1,
            // to make this (k - 1) window available when i becomes i + 1
            while (!monoq.isEmpty() && monoq.peekFirst()[0] + k <= i) {
                monoq.pollFirst();
            }
            while (!monoq.isEmpty() && monoq.peekLast()[1] <= dp[i]) {
                monoq.pollLast();
            }
            // enqueue现在的i和dp[i]
            monoq.offer(new int[]{i, dp[i]});
            
            // 计算下一层的DP
            // NOTE: 因为是连续的，因此即使之前的dp[prev] < 0，也要相加
            dp[i + 1] = nums[i + 1] + monoq.peekFirst()[1];
        }
        
        return dp[n - 1];
    }
}