class Solution {
    
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int minimumEffortPath(int[][] heights) {
        // keep track of the maximum absolute difference
        // BFS or prioritize the min one
        
        int n = heights.length;
        int m = heights[0].length;
        
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        Set<Integer> visited = new HashSet<>();
        
        pq.offer(new int[]{0, 0, 0});
        
        int res = Integer.MAX_VALUE;
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0], c = curr[1], d = curr[2];
            
            // the minimum distance guaranteed by PQ
            if (r == n - 1 && c == m - 1) {
                res = Math.min(res, d);
                return res;
            }
            
            visited.add(r * m + c);
            
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }
                
                if (!visited.contains(nr * m + nc)) {
                    int nd = Math.max(d, Math.abs(heights[nr][nc] - heights[r][c]));
                    pq.offer(new int[]{nr, nc, nd});
                }
            }
        }
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}