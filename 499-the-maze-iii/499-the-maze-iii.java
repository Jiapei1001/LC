class Solution {
    class State {
        int r;
        int c;
        int d;
        String steps;
        
        public State(int r, int c, int d, String steps) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.steps = steps;
        }
    }
    
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    char[] dirsStr = {'r', 'l', 'd', 'u'};
    
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int n = maze.length;
        int m = maze[0].length;
        
        PriorityQueue<State> pq = new PriorityQueue<>(
            (a, b) -> a.d == b.d ? a.steps.compareTo(b.steps) : a.d - b.d);
        
        boolean[][] visited = new boolean[n][m];
        Set<String> seen = new HashSet<>();
        
        pq.offer(new State(ball[0], ball[1], 0, ""));
        
        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int r = curr.r, c = curr.c;
            
            // String s = curr.r + "->" + curr.c + "->" + curr.d;
            // if (seen.contains(s)) {
            //     continue;
            // }
            // seen.add(s);
            
            if (r == hole[0] && c == hole[1]) {
                return curr.steps;
            }
            
            if (visited[curr.r][curr.c]) {
                continue;
            }
            visited[curr.r][curr.c] = true;
            
            for (int i = 0; i < 4; i++) {
                State next = getNext(maze, curr, i, hole);
                if (next != null) {
                    pq.offer(next);
                }
            }
        }
        
        return "impossible";
    }
    
    private State getNext(int[][] maze, State curr, int i, int[] hole) {
        int n = maze.length;
        int m = maze[0].length;
        
        int nr = curr.r;
        int nc = curr.c;
        int nd = curr.d;
        String newSteps = curr.steps + dirsStr[i];

        while (nr >= 0 && nr < n && nc >= 0 && nc < m && maze[nr][nc] != 1) {
            nr += dirs[i][0];
            nc += dirs[i][1];
            nd++;

            if (nr == hole[0] && nc == hole[1]) {
                return new State(nr, nc, nd, newSteps);
            }
        }

        // didn't move
        if (nr == curr.r && nc == curr.c) {
            return null;
        }

        // go back one step
        nr -= dirs[i][0];
        nc -= dirs[i][1];
        nd--;
        
        return new State(nr, nc, nd, newSteps);
    }
}