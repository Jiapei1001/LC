class Solution {
    
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        
        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            
            int currMax = 0;
            int sumMax = 0;
            for (int j = 1; j <= k; j++) {
                if (i - j < 0) {
                    break;
                }
                currMax = Math.max(currMax, arr[i - j]);
                sumMax = Math.max(sumMax, currMax * j + dp[i - j]);
            }
            
            dp[i] = sumMax;
        }
        
        return dp[n];
    }
    
    
    // Top Down
    /*
    public int maxSumAfterPartitioning(int[] arr, int k) {
        // [1,4,1,5,7,3,6,1,9,9,3]
        // [1,7,7,7,7,9,9,9,9,9,9]
        // 1 + 28 + 54 = 29 + 54 = 83
        
        int n = arr.length;
        
        Map<Integer, Integer> memo = new HashMap<>();
        return dfs(arr, 0, k, memo);
    }
    
    private int dfs(int[] arr, int i, int k, Map<Integer, Integer> memo) {
        if (i == arr.length) {
            return 0;
        }
        
        if (memo.containsKey(i)) {
            return memo.get(i);
        }
        
        int currMax = 0;
        int sumMax = 0;
        
        for (int j = 0; j < k; j++) {
            // [i, j]
            int to = i + j;
            
            if (to >= arr.length) {
                break;
            }
            
            currMax = Math.max(currMax, arr[to]);
            // int temp = dp[i][j] * (j - i + 1);
            sumMax = Math.max(sumMax, currMax * (j + 1) + dfs(arr, to + 1, k, memo));
        }
        
        memo.put(i, sumMax);
        return sumMax;
    }
    */
}