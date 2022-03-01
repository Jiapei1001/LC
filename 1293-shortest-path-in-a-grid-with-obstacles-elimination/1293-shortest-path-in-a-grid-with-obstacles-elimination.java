class Solution {
    
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int n;
    int m;
    
    public int shortestPath(int[][] grid, int k) {
        n = grid.length;
        m = grid[0].length;

        // PQ
        // r, c, k, dist
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            // NOTE: 这个A*不仅仅需要Manhattan Distance，还需要factor in currDist!!
            int x = (n - a[0]) + (m - a[1]) + a[3];
            int y = (n - b[0]) + (m - b[1]) + b[3];
            
            if (x == y) return a[3] - b[3];
            return x - y;
        });

        boolean[][][] visited = new boolean[n][m][k + 1];
        
        pq.offer(new int[]{0, 0, k, 0});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0], c = curr[1], ck = curr[2], d = curr[3];
            
            // NOTE: must avoid the case when ck < 0
            if (ck < 0) continue;
            
            if (r == n - 1 && c == m - 1 && ck >= 0) {
                return d;
            }
            
            if (visited[r][c][ck]) continue;
            
            visited[r][c][ck] = true;
            
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                
                if (grid[nr][nc] == 0) {
                    pq.offer(new int[]{nr, nc, ck, d + 1});
                }
                
                else if (grid[nr][nc] == 1) {
                    if (ck > 0) {
                        pq.offer(new int[]{nr, nc, ck - 1, d + 1});
                    }
                }
            }
        }
        
        return -1;
    }
}