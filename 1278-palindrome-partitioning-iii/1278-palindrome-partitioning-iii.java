class Solution {
    public int palindromePartition(String s, int k) {
        
        int n = s.length();
        
//         f(i, k) := the minimal number of changes to make the d[0:i] palindrome with partition k
            
//         f(i, k)  =  f(j, k - 1) + number of changes(j + 1, i), where j < i
//                  OR f(i, k), no divide
        
        Map<String, Integer> memo = new HashMap<>();
            
        int[][] dp = new int[n][k + 1];
        for (int[] row : dp) Arrays.fill(row, 1000);
        
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
            dp[i][1] = numOfPalindrome(s, 0, i, memo);
        }
        
        for (int m = 2; m <= k; m++)
            for (int i = 0; i < n; i++)
                // [j, i], j's left boundary is 1
                for (int j = i; j >= 1; j--) {
                    int temp = dp[j - 1][m - 1] + numOfPalindrome(s, j, i, memo);
                    
                    dp[i][m] = Math.min(dp[i][m], temp);
                }
        
        return dp[n - 1][k];
    }
    
    private int numOfPalindrome(String s, int l, int r, Map<String, Integer> memo) {
        String curr = l + "-" + r;
        
        if (memo.containsKey(curr)) {
            return memo.get(curr);
        }
        
        int res = 0;
        
        while (l <= r) {
            if (s.charAt(l) != s.charAt(r)) res++;
            l++;
            r--;
        }
        
        memo.put(curr, res);
        
        return res;
    }
}