class Solution {
    
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int n, m;
    
    public int maxDistance(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 1) {
                    q.offer(new int[]{r, c, 0});
                    visited.add(r * m + c);
                }
            }
        
        int res = -1;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1], d = curr[2];
            
            res = Math.max(res, d);
            
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                
                if (!visited.contains(nr * m + nc) && grid[nr][nc] == 0) {
                    q.offer(new int[]{nr, nc, d + 1});
                    visited.add(nr * m + nc);
                }
            }
        }
        
        return res > 0 ? res : -1;
    }
    
    private int dfs(int[][] grid, int r, int c, Map<Integer, Integer> memo) {
        if (grid[r][c] == 1) {
            return 0;
        }
        
        int currIdx = r * m + c;
        
        if (memo.containsKey(currIdx)) {
            return memo.get(currIdx);
        }
        
        int temp = 0;
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            
            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
            
            temp = Math.max(temp, 1 + dfs(grid, nr, nc, memo));
        }
        
        memo.put(currIdx, temp);
        
        return temp;
    }
    
    /*
    public int maxDistance(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        
        Map<Integer, Integer> memo = new HashMap<>();
        boolean[] visited = new boolean[n * m];
        
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 0 && !visited[r * m + c]) {
                    dfs(grid, r, c, visited, memo);
                }
            }
        
        return Collections.max(new ArrayList<Integer>(memo.values()));
    }
    
    private int dfs(int[][] grid, int r, int c, boolean[] visited, Map<Integer, Integer> memo) {
        if (grid[r][c] == 1) {
            return 0;
        }
        
        int n = grid.length, m = grid[0].length;
        
        if (visited[r * m + c] && memo.containsKey(r * m + c)) {
            return memo.get(r * m + c);
        }
        
        visited[r * m + c] = true;
        
        int res = Integer.MAX_VALUE;
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            
            if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                continue;
            }
            
            int temp = 0;
            
            if (visited[nr * m + c] && memo.containsKey(nr * m + c)) {
                temp = 1 + memo.get(nr * m + c);
            } else {
                temp = 1 + dfs(grid, nr, nc, visited, memo);
            }
            
            res = Math.min(res, temp);
        }
        
        memo.put(r * m + c, res);
        return res;
    }
    */
}