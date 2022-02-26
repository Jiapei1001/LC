class Solution {
    
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int minCost(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        // r, c, cost
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        
        int[][] dp = new int[n][m];
        for (int[] r : dp) Arrays.fill(r, Integer.MAX_VALUE);
        
        pq.offer(new int[]{0, 0, 0});
        dp[0][0] = 0;
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0], c = curr[1], cost = curr[2];
            
            if (r == n - 1 && c == m - 1) {
                return cost;
            }
            
            if (dp[r][c] != cost) {
                continue;
            }
            
            for (int i = 1; i <= 4; i++) {
                int nr = r + dirs[i - 1][0];
                int nc = c + dirs[i - 1][1];
                int nCost = cost;
                
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }
                
                if (i != grid[r][c]) {
                    nCost++;
                }
                
                if (nCost < dp[nr][nc]) {
                    pq.offer(new int[]{nr, nc, nCost});
                    dp[nr][nc] = nCost;
                }
                
                
                // two choices
                // if (i == grid[r][c]) {
                //     // no further cost
                //     if (cost < dp[nr][nc]) {
                //         pq.offer(new int[]{nr, nc, cost});
                //         dp[nr][nc] = dp[r][c] + cost;
                //     }
                // } else {
                //     if ((cost + 1) < dp[nr][nc]) {
                //         pq.offer(new int[]{nr, nc, cost + 1});
                //         dp[nr][nc] = cost + 1;
                //     }
                // }
            }
        }
        
        return dp[n - 1][m - 1];
    }
}