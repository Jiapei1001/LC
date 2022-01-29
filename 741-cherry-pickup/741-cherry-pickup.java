class Solution {
    public int cherryPickup(int[][] grid) {
//         dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
        
//         f(i, j) := the maximum number of cherries can be got
//         f(i, j)  =  f(i - 1, j) + f(i, j - 1) + current cherry, where (i, j) is not blocked
//                  =  0, where (i, j) is blocked
        
        int n = grid.length;
        
        Integer[][][] memo = new Integer[n][n][n];
        
        // NOTE: return 0 if there is no path from (0, 0) to (n - 1, n - 1)
        return Math.max(0, dfs(grid, 0, 0, 0, memo));
    }
    
    private int dfs(int[][] grid, int r1, int c1, int c2, Integer[][][] memo) {
        int n = grid.length;
        
        int r2 = r1 + c1 - c2;
        
        // make sure both people are on the grid
        if (r1 >= n || c1 >= n || r2 >= n || c2 >= n) {
            return Integer.MIN_VALUE;
        }
        
        // make sure both people are not blocked
        if (grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return Integer.MIN_VALUE;
        }
        
        if (memo[r1][c1][c2] != null) {
            return memo[r1][c1][c2];
        }
        
        // NOTE: base case for returning
        // check if we reached the end state (note that if r1,c1 reached the end, this implies that r2,c2 also reached the end)
        if (r1 == n - 1 && c1 == n - 1) {
            return grid[r1][c1];
        }
        
        // avoid duplication on the same grid
        int res = grid[r1][c1];
        
        if (r1 != r2) res += grid[r2][c2];
        
        
        int tempNext = Math.max(dfs(grid, r1 + 1, c1, c2 + 1, memo),        // down, right
                                dfs(grid, r1 + 1, c1, c2, memo));           // down, down
        
        tempNext = Math.max(tempNext, dfs(grid, r1, c1 + 1, c2 + 1, memo)); // right, right
        
        tempNext = Math.max(tempNext, dfs(grid, r1, c1 + 1, c2, memo));     // right, down
        
        res += tempNext;
        
        memo[r1][c1][c2] = res;
        
        return res;
    }
}