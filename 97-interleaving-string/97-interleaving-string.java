class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int l = s3.length();
        
        if (n + m != l) return false;
        
        if (n == 0) return s2.equals(s3);
        if (m == 0) return s1.equals(s3);
        
        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();
        char[] s3Arr = s3.toCharArray();
        
        // f(i, j) := for the front i & j, if they can match s3[i + j]
        
        // f(i, j) = f(i - 1, j), where s1[i - 1] == s3[i + j]
        //         = f(i, j - 1), where s2[j] == s3[i + j]
        
        boolean[][] dp = new boolean[n + 1][m + 1];
        
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) dp[i][0] = dp[i - 1][0] && s1Arr[i - 1] == s3Arr[i - 1];
        for (int j = 1; j <= m; j++) dp[0][j] = dp[0][j - 1] && s2Arr[j - 1] == s3Arr[j - 1];
        
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                if (s1Arr[i - 1] == s3Arr[i + j - 1]) {
                    dp[i][j] |= dp[i - 1][j];
                }
                // OR condition here, so cannot be if else
                if (s2Arr[j - 1] == s3Arr[i + j - 1]) {
                    dp[i][j] |= dp[i][j - 1];
                }
            }
        
        return dp[n][m];
    }
}