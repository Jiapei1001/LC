class Solution {
    
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // connected
        // bfs
        
        int n = image.length, m = image[0].length;
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        q.offer(new int[]{sr, sc});
        visited[sr][sc] = true;
        
        int originColor = image[sr][sc];
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            
            
            image[curr[0]][curr[1]] = newColor;
            
            for (int[] dir : dirs) {
                int nr = curr[0] + dir[0];
                int nc = curr[1] + dir[1];
                
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }
                
                if (image[nr][nc] == originColor && !visited[nr][nc]) {
                    q.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        
        return image;
    }
}