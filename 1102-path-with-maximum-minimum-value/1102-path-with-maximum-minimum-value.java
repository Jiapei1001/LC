class Solution {
    
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int n, m;
    
    public int maximumMinimumPath(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        
        // binary search
        // the maximum value that is the lowest value in the path that connects the two
        Set<Integer> visited = new HashSet<>();
        int l = 0, r = (int) 1e9;
        
        while (l < r) {
            int mid = l + (r - l) / 2;
            visited = new HashSet<>();
            if (canFormAPathAsLowestVal(grid, 0, 0, visited, mid)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        
        /*
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            visited = new HashSet<>();
            if (canFormAPathAsLowestVal(grid, 0, 0, visited, mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        
        visited = new HashSet<>();
        if (canFormAPathAsLowestVal(grid, 0, 0, visited, r)) return r;
        visited = new HashSet<>();
        if (canFormAPathAsLowestVal(grid, 0, 0, visited, l)) return l;
        
        return -1;
        */
        
        return l - 1;
    }
    
    private boolean canFormAPathAsLowestVal(int[][] grid, int r, int c, Set<Integer> visited, int val) {
        if (r < 0 || r >= n || c < 0 || c >= m) return false;
        
        int curr = grid[r][c];
        // make sure val is the lowest in the path
        if (curr < val) return false;
        visited.add(r * m + c);
        
        // check val first
        if (r == n - 1 && c == m - 1 && grid[r][c] >= val) return true;

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            
            if (!visited.contains(nr * m + nc) && canFormAPathAsLowestVal(grid, nr, nc, visited, val)) {
                return true;
            }
        }
        return false;
    }
    
    
    
    
    
    
    // Dijkstra
    /*
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int maximumMinimumPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        int[][] dist = new int[n][m];
        for (int[] r : dist) Arrays.fill(r, Integer.MIN_VALUE);
        
        Queue<int[]> pq = new PriorityQueue<>((a, b) ->  b[2] - a[2]);
        // Set<Integer> visited = new HashSet<>();
        
        // NOTE: 不需要Set<Integer> visited，只需要dist来控制是否enqueue
        dist[0][0] = grid[0][0];
        pq.offer(new int[]{0, 0, grid[0][0]});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0], c = curr[1], s = curr[2];
            
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
    */
}