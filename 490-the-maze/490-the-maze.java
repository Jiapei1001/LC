class Solution {
    
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int n = maze.length;
        int m = maze[0].length;
        
        // int[]{r, c}
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        q.offer(new int[]{start[0], start[1]});
        visited[start[0]][start[1]] = true;
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];
            
            if (r == destination[0] && c == destination[1]) {
                return true;
            }
            
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                while (nr >= 0 && nr < n && nc >= 0 && nc < m && maze[nr][nc] != 1) {
                    nr += dir[0];
                    nc += dir[1];
                }
                // return back one step
                nr -= dir[0];
                nc -= dir[1];
                
                if (!visited[nr][nc]) {
                    q.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        
        return false;
    }
}