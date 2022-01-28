class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || s.length() == 0) return false;
        if (p == null || p.length() == 0) return false;
        
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        
        int n = sArr.length;
        int m = pArr.length;
        
        boolean[][] dp = new boolean[n + 1][m + 1];
        
        // f(i, j) := if s and p are maching for the front i and j number of indexes
        // f(i, j)  =  f(i - 1, j - 1), when char j is Character
        //          =  f(i - 1, j - 1), when char j is .  , can be used only once
        //          =  f(i, j - 2), when j char is *  , used 0 time
        //          =  f(i - 1, j), when char i == char (j - 1), char j is *  , used >0 times, eliminating the last Character of i
        
        // base case
        dp[0][0] = true;
        
        // when pattern is all *
        // NOTE: must check dp[0][j - 2], as * interacts with the preceding Character!!
        for (int j = 2; j <= m; j++) dp[0][j] = pArr[j - 1] == '*' ? dp[0][j - 2] : false;
        
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                dp[i][j] = false;
                
                if (sArr[i - 1] == pArr[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
                
                if (pArr[j - 1] == '.') {
                    dp[i][j] |= dp[i - 1][j - 1];
                } else if (pArr[j - 1] == '*') {
                    if (j >= 2) {
                        // when char j is *, used 0 time, eliminate the char (j - 1)
                        dp[i][j] |= dp[i][j - 2];
                        // when char j is *, used multiple times
                        // NOTE: pArr[j - 2] == '.'的情况，可以消灭任何一个char i
                        if (sArr[i - 1] == pArr[j - 2] || pArr[j - 2] == '.') {
                            dp[i][j] |= dp[i - 1][j];
                        }
                    }
                }
            }
        
        return dp[n][m];
    }
}