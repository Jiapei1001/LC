class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        char[] sArr = word1.toCharArray();
        char[] pArr = word2.toCharArray();
        
        // f(i, j) = MIN{ f(i - 1, j) + 1, f(i, j - 1) + 1 }, where word1[i] != word2[j]
        //         = f(i - 1, j - 1), where word1[i] == word2[j]
            
        int[][] dp = new int[n + 1][m + 1];
        
        // base case:
        for (int[] row : dp) Arrays.fill(row, 1000);
        
        dp[0][0] = 0;
        
        for (int i = 1; i <= n; i++) dp[i][0] = i;
        for (int j = 1; j <= m; j++) dp[0][j] = j;
        
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                if (sArr[i - 1] == pArr[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                }
            }
        
        return dp[n][m];
    }
}