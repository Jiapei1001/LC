class Solution {
    
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int maximumMinimumPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        int[][] dist = new int[n][m];
        for (int[] r : dist) Arrays.fill(r, Integer.MIN_VALUE);
        
        Queue<int[]> pq = new PriorityQueue<>((a, b) ->  b[2] - a[2]);
        Set<Integer> visited = new HashSet<>();
        
        dist[0][0] = grid[0][0];
        pq.offer(new int[]{0, 0, grid[0][0]});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0], c = curr[1], s = curr[2];
            
            // if (r == n - 1 && c == m - 1) {
            //     return s;
            // }
        
            // visited.add(r * m + c);
            
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }

                int ns = Math.min(s, grid[nr][nc]);
                
                if (ns > dist[nr][nc]) {
                    dist[nr][nc] = Math.max(dist[nr][nc], ns);
                    pq.offer(new int[]{nr, nc, ns});
                }
            }
        }
        
        return dist[n - 1][m - 1] == Integer.MIN_VALUE ? -1 : dist[n - 1][m - 1];
    }
}