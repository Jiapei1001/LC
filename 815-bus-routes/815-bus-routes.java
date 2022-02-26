class Solution {
    
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        if (routes == null || routes.length == 0) return -1;
        
        // build graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j : routes[i]) {
                graph.computeIfAbsent(j, a -> new HashSet<>()).add(i);
            }
        }
        
        // find stops that contains source
        // BFS from stops to next available stops
        // if identify target, return steps
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        boolean[] buses = new boolean[routes.length];
        
        q.offer(source);
        
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                
                if (curr == target) {
                    return step;
                }
                
                if (visited.contains(curr)) {
                    continue;
                }
                visited.add(curr);
                                
                if (!graph.containsKey(curr)) {
                    continue;
                }
                
                for (int idx : graph.get(curr)) {
                    if (buses[idx]) continue;
                    
                    for (int next : routes[idx]) {
                        if (!visited.contains(next)) {
                            q.offer(next);
                        }
                    }
                    buses[idx] = true;
                }
            }
            step++;
        }
        
        return -1;
    }
    
    
    
    
    
    
    
    
    /*
    public int numBusesToDestination(int[][] routes, int source, int target) {
        // build graph
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                map.computeIfAbsent(routes[i][j], a -> new HashSet<Integer>()).add(i);
            }
        }
        
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        boolean[] buses = new boolean[routes.length];
        
        q.offer(new int[]{source, 0});
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int currStop = curr[0], currDist = curr[1];
            
            if (visited.contains(currStop)) {
                continue;
            }
            visited.add(currStop);
            
            if (currStop == target) {
                return currDist;
            }
            
            if (!map.containsKey(currStop)) {
                continue;
            }
            
            for (int next : map.get(currStop)) {
                if (buses[next]) {
                    continue;
                }
                for (int j = 0; j < routes[next].length; j++) {
                    if (!visited.contains(routes[next][j])) {
                        q.offer(new int[]{routes[next][j], currDist + 1});
                    }
                }
                buses[next] = true;
            }
        }
        
        return -1;
    }
    */
}