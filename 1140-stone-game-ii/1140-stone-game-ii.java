class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        // sum := the reverse direction prefixSum from piles[i] to the end
        int[] sum = new int[n];
        
        sum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sum[i] = sum[i + 1] + piles[i];
        }
        
        // (i, m)
        int[][] memo = new int[n][n];
        
        return dfs(piles, 0, 1, sum, memo);
    }
    
    private int dfs(int[] piles, int i, int m, int[] sum, int[][] memo) {
        // all the rest
        if (i < piles.length && 2 * m > piles.length) {
            return sum[i];
        }
        
        if (i >= piles.length) {
            return 0;
        }
        
        if (memo[i][m] != 0) {
            return memo[i][m];
        }
        
        // find the min number next player can get
        int next = Integer.MAX_VALUE;
        
        // NOTE: 因为需要比较j和m，之前的index不考虑，因此这里j必须从1 loop 到 2 * m
        // j 不可以从 i + 1 loop 到 i + 2 * m
        for (int j = 1; j <= 2 * m; j++) {
            next = Math.min(next, dfs(piles, i + j, Math.max(j, m), sum, memo));
        }
        
        // the current sum - min of next player = max of current player
        // the root cast is Alice
        int curr = sum[i] - next;
        
        memo[i][m] = curr;
        return curr;
    }
}