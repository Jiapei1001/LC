class Solution {
    
    private int MOD = (int) 1e9 + 7;
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        Map<String, Integer> memo = new HashMap<>();
        
        return dfs(m, n, maxMove, startRow, startColumn, memo);
    }
    
    private int dfs(int m, int n, int k, int sr, int sc, Map<String, Integer> memo) {
        if (sr < 0 || sr >= m || sc < 0 || sc >= n) {
            return 1;
        }
        if (k == 0) {
            return 0;
        }
        String key = k + "-" + sr + "-" + sc; 
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        long count = 0;
        for (int[] dir : dirs) {
            int nr = sr + dir[0];
            int nc = sc + dir[1];
            
            count = (count + dfs(m, n, k - 1, nr, nc, memo)) % MOD;
        }
        
        memo.put(key, (int) count);
        return (int) count;
    }
}