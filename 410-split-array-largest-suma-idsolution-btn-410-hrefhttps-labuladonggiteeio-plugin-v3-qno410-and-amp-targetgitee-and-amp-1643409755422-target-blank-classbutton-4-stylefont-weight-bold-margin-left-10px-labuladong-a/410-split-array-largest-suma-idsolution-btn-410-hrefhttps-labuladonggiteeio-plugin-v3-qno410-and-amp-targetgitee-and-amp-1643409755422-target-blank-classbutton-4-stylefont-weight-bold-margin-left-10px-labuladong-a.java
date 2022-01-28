class Solution {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        
        int[] prefixSum = new int[n];
        
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        
        // f(i, k) := the minimal number of largest sum for the front i numbers, with k partitions
        // f(i, k)  =  MAX{ f(j - 1, k - 1), SUM[j, i] }, where j <= i
        
        int[][] memo = new int[n][m + 1];
        for (int[] row : memo) Arrays.fill(row, -1);
        
        return dfs(nums, 0, m, prefixSum, memo);
    }
    
    private int dfs(int[] nums, int i, int k, int[] prefixSum, int[][] memo) {
        if (i == nums.length && k == 0) {
            return 0;
        }
        
        if (i >= nums.length || k == 0) {
            return Integer.MAX_VALUE;
        }
        
        if (memo[i][k] != -1) {
            return memo[i][k];
        }
        
        int res = Integer.MAX_VALUE;
        
        for (int j = i; j < nums.length - k + 1; j++) {
            // [i, j]
            int tempSum = i > 0 ? prefixSum[j] - prefixSum[i - 1] : prefixSum[j];
            
            int next = dfs(nums, j + 1, k - 1, prefixSum, memo);
            
            // NOTE: 因为cand需要和res比较谁最小，因此必须initiated为Integer.MAX_VALUE！！
            int cand = Integer.MAX_VALUE;
            
            // check if this divide is valid
            if (next != Integer.MAX_VALUE) {
                // if valid, get the largest sum from this valid partion
                // curr partition vs. next part
                cand = Math.max(tempSum, next);
            }
            
            // update res
            res = Math.min(res, cand);
        }
        
        memo[i][k] = res;
        
        return res;
    }
}