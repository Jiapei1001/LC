class Solution {
    
    // Bottom Up DP
    /*
    public int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        int[][] dp = new int[n][m];
        
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (matrix[r][c] == 1) {
                    cntMap.put(1, cntMap.getOrDefault(1, 0) + 1);
                }
                
                if (r == 0) dp[r][c] = matrix[r][c];
                else if (c == 0) dp[r][c] = matrix[r][c];
                else {
                    if (matrix[r][c] == 1) {                        
                        dp[r][c] = 1 + Math.min(dp[r - 1][c - 1], Math.min(dp[r - 1][c], dp[r][c - 1]));
                        
                        // if max length is 3, it must contain 2 & 1
                        // 1 was added to cntMap before to cover base cases
                        if (dp[r][c] > 1) {
                            cntMap.put(dp[r][c], cntMap.getOrDefault(dp[r][c], 0) + dp[r][c] - 1);
                        }
                    }
                }
            }
        
        int res = 0;
        for (int i : cntMap.keySet()) {
            res += cntMap.get(i);
        }
        
        return res;
    }
    */

    
    public int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        int[][] memo = new int[n][m];
        for (int[] r : memo) Arrays.fill(r, -1);
        
        int res = 0;
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (matrix[r][c] == 1) {
                    res += dfs(matrix, r, c, memo);
                }
            }
        
        return res;
    }
    
    private int dfs(int[][] matrix, int r, int c, int[][] memo) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        if (r < 0 || r >= n || c < 0 || c >= m || matrix[r][c] == 0) {
            return 0;
        }
        
        if (memo[r][c] != -1) {
            return memo[r][c];
        }
        
        int temp = 0;
        temp = 1 + Math.min(dfs(matrix, r + 1, c + 1, memo),
                            Math.min(dfs(matrix, r + 1, c, memo), 
                                     dfs(matrix, r, c + 1, memo))
                           );
        
        memo[r][c] = temp;
        return temp;
    }
}