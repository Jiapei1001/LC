class Solution {

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        Map<Integer, Set<Integer>> red = new HashMap<>();
        Map<Integer, Set<Integer>> blue = new HashMap<>();
        
        for (int[] r : red_edges) {
            red.computeIfAbsent(r[0], a -> new HashSet<>()).add(r[1]);
        }
        for (int[] b : blue_edges) {
            blue.computeIfAbsent(b[0], a -> new HashSet<>()).add(b[1]);
        }
        
        int[] res = new int[n];
        Arrays.fill(res, Integer.MAX_VALUE);
        
        // two state
        Queue<int[]> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        // red
        q.offer(new int[]{0, -1});
        visited.add(0 + "-" + -1);
        // blue
        q.offer(new int[]{0, 1});
        visited.add(0 + "-" + 1);
        
        int step = 0;
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int node = curr[0], color = curr[1];
                
                res[node] = Math.min(res[node], step);
                
                // prev as red
                // if (color == -1) {
                    for (int next : color == -1 ? 
                         blue.getOrDefault(node, new HashSet<>()) : red.getOrDefault(node, new HashSet<>())) {
                        String nextColor = next + "-" + (-color);
                        if (!visited.contains(nextColor)) {
                            visited.add(nextColor);
                            q.offer(new int[]{next, -color});
                        }
                    }
                // } 
                // prev as blue
                /*
                else if (color == 1) {
                    for (int next : red.getOrDefault(node, new HashSet<>())) {
                        String nextColor = next + "-" + (-color);
                        if (!visited.contains(nextColor)) {
                            visited.add(nextColor);
                            q.offer(new int[]{next, -color});
                        }
                    }
                }
                */
            }
            step++;
        }
        
        for (int i = 0; i < n; i++) {
            if (res[i] == Integer.MAX_VALUE) res[i] = -1;
        }
        
        return res;
    }
    
    
    /*
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        Map<Integer, Set<Integer>> red = new HashMap<>();
        Map<Integer, Set<Integer>> blue = new HashMap<>();
        
        for (int[] e : red_edges) {
            red.computeIfAbsent(e[0], a -> new HashSet<Integer>()).add(e[1]);
        }
        for (int[] e : blue_edges) {
            blue.computeIfAbsent(e[0], a -> new HashSet<Integer>()).add(e[1]);
        }
        
        int[] res = new int[n];
        // maximum length is 2 * n
        Arrays.fill(res, 2 * n);
        res[0] = 0;
        
        this.bfs(res, red, blue, true);
        this.bfs(res, red, blue, false);
        
        for (int i = 0; i < n; i++) {
            if (res[i] == 2 * n) res[i] = -1;
        }
        
        return res;
    }
    
    private void bfs(int[] res,
                     Map<Integer, Set<Integer>> red,
                     Map<Integer, Set<Integer>> blue,
                     boolean isRed) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> redVisited = new HashSet<>();
        Set<Integer> blueVisited = new HashSet<>();
        q.offer(0);
        redVisited.add(0);
        blueVisited.add(0);
        
        int dist = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                if (isRed) {
                    if (!red.containsKey(curr)) {
                        continue;
                    }
                    for (int next : red.get(curr)) {
                        if (!redVisited.contains(next)) {
                            res[next] = Math.min(res[next], dist);
                            redVisited.add(next);
                            q.offer(next);
                        }
                    }
                } else {
                    if (!blue.containsKey(curr)) {
                        continue;
                    }
                    for (int next : blue.get(curr)) {
                        if (!blueVisited.contains(next)) {
                            res[next] = Math.min(res[next], dist);
                            blueVisited.add(next);
                            q.offer(next);
                        }
                    }
                }
            }
            isRed = !isRed;
            dist++;
        }
    }
    */
}