class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        
        int n = dungeon.length;
        int m = dungeon[0].length;
        
//         f(i, j) := the maximum negative number in order to stand at (i, j)
//         f(i, j)  = MAX{ d(i, j) + f(i + 1, j), d(i, j) + f(i, j + 1) } 
//         f(i, j)  = 0, if f(i, j) > 0
        
//         return Math.abs(f(0, 0)) + 1;
        
        int[][] dp = new int[n][m];
        
        for (int[] row : dp) Arrays.fill(row, -500000);
        
        dp[n - 1][m - 1] = dungeon[n - 1][m - 1] > 0 ? 0 : dungeon[n - 1][m - 1];
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (i == n - 1 && j == m - 1) continue;
                
                if (i + 1 < n) dp[i][j] = Math.max(dp[i][j], dp[i + 1][j]);
                if (j + 1 < m) dp[i][j] = Math.max(dp[i][j], dp[i][j + 1]);
                
                dp[i][j] += dungeon[i][j];
                
                // dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]) + dungeon[i][j];
                
                if (dp[i][j] > 0) dp[i][j] = 0;
            }
        }
        
        return Math.abs(dp[0][0]) + 1;
    }
}