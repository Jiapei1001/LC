class Solution {
    
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        
        int[][] res = new int[n][m];
        
        // Map<int[], Integer> memo = new HashMap<>();
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (mat[r][c] == 0) {
                    q.offer(new int[]{r, c});
                    visited[r][c] = true;
                }
                
                // res[r][c] = bfs(mat, r, c);
                // res[r][c] = dfs(mat, r, c, memo);
            }
        
        int level = 0;
        
        while(!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                
                // edit nearest distance
                if (mat[curr[0]][curr[1]] == 1) {
                    res[curr[0]][curr[1]] = level;
                }
                
                for (int[] dir : dirs) {
                    int newR = curr[0] + dir[0];
                    int newC = curr[1] + dir[1];
                    
                    if (newR < 0 || newR >= n || newC < 0 || newC >= m) {
                        continue;
                    }
                    
                    if (!visited[newR][newC]) {
                        q.offer(new int[]{newR, newC});
                        visited[newR][newC] = true;
                    }
                }
            }
            
            // NOTE: must put here below
            level++;
        }
        
        return res;
    }
    
    /*
    private int dfs(int[][] mat, int r, int c, Map<int[], Integer> memo) {
        int[] curr = new int[]{r, c};
        
        int n = mat.length, m = mat[0].length;
        
        if (memo.containsKey(curr)) {
            return memo.get(curr);
        }
        
        if (mat[r][c] == 0) {
            return 0;
        }
        
        int res = Integer.MAX_VALUE;
        
        for (int[] dir : dirs) {
            int newR = curr[0] + dir[0];
            int newC = curr[1] + dir[1];
                    
            if (newR < 0 || newR >= n || newC < 0 || newC >= m) {
                continue;
            }
            
            res = Math.min(res, 1 + dfs(mat, newR, newC, memo));
        }
        
        memo.put(curr, res);
        
        return res;
    }
    */
    
    private int bfs(int[][] mat, int r, int c) {
        int res = 0;
        int n = mat.length, m = mat[0].length;
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        q.offer(new int[]{r, c});
        visited[r][c] = true;
        
        while(!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                
                if (mat[curr[0]][curr[1]] == 0) {
                    return res;
                }
                visited[curr[0]][curr[1]] = true;
                
                for (int[] dir : dirs) {
                    int newR = curr[0] + dir[0];
                    int newC = curr[1] + dir[1];
                    
                    if (newR < 0 || newR >= n || newC < 0 || newC >= m) {
                        continue;
                    }
                    
                    if (!visited[newR][newC]) {
                        q.offer(new int[]{newR, newC});
                    }
                }
            }
            
            // NOTE: must put here below
            res++;
        }
        
        return -1;
    }
}