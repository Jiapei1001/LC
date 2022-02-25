class Solution {
    
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int n = maze.length;
        int m = maze[0].length;
        
        // Queue<int[]> pq = new PriorityQueue<>((a, b) -> dist(a, destination) - dist(b, destination));
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        Set<Integer> visited = new HashSet<>();
        
        pq.offer(new int[]{start[0], start[1], 0});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0], c = curr[1], d = curr[2];
            
            if (r == destination[0] && c == destination[1]) {
                return d;
            }
            
            visited.add(r * m + c);
            
            for (int[] dir : dirs) {
                int nr = r, nc = c;
                int acc = 0;
                while (nr < n && nr >= 0 && nc < m && nc >= 0 && maze[nr][nc] != 1) {
                    nr += dir[0];
                    nc += dir[1];
                    acc++;
                }
                // out of boundary
                nr -= dir[0];
                nc -= dir[1];
                acc--;
                
                if (!visited.contains(nr * m + nc)) {
                    pq.offer(new int[]{nr, nc, d + acc});
                }
            }
        }
        
        return -1;
    }
    
    private int dist(int[] x, int[] y) {
        return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
    }
}