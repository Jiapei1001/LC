class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        int n = arr.length;
        
        Map<Integer, Integer> memo = new HashMap<>();
        for (int i : arr) {
            int before = i - difference;
            
            if (memo.containsKey(before)) {
                memo.put(i, memo.get(before) + 1);
            } else {
                memo.put(i, 1);
            }
        }
        
        return Collections.max(memo.values());
    }
    
    
    // DP - TLE
    /*
    public int longestSubsequence(int[] arr, int difference) {
        int n = arr.length;
        
        // f(i) = f(j) + 1, where j < i && arr[i] - arr[j] = difference
        
        int[] dp = new int[n];
        
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] + difference == arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        int res = 0;
        for (int i : dp) res = Math.max(res, i);
        
        return res;
    }
    */
}