class Solution {
    public int numDistinct(String s, String t) {
        
        int n = s.length();
        int m = t.length();
        
        if (n < m) return 0;
        
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        
        // f(i, j) = f(i - 1, j - 1) + f(i - 1, j), where s[i] == t[j]
        //           count 拿 + don't count 不拿
        //         = f(i - 1, j), where s[i] != t[j]
        //           don't count 不拿
        
        int[][] dp = new int[n + 1][m + 1];
        
        // NOTE: base, derived from example (b, b)
        dp[0][0] = 1;
        
        // NOTE: 这里的base case非常不好想，最好按照下面的例子进行讨论！！
        // (bb, b) = (b, "") + (b, b), so (b, "") must be 1
        for (int i = 1; i <= n; i++) dp[i][0] = 1;
        
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                if (sArr[i - 1] == tArr[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + // 拿，count
                               dp[i - 1][j];      // 不拿，don't count
                } else {
                    dp[i][j] = dp[i - 1][j];      // 不拿，don't count
                }
            }
        
        return dp[n][m];
    }
}