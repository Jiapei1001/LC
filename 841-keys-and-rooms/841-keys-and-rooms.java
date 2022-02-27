class Solution {
    
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        
        dfs(rooms, 0, visited);
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) return false;
        }
        
        return true;
    }
    
    private void dfs(List<List<Integer>> rooms, int i, boolean[] visited) {
        visited[i] = true;
        
        for (int j : rooms.get(i)) {
            if (!visited[j]) dfs(rooms, j, visited);
        }
    }
    
    
    
    
    
    
    /*
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() == 0) return true;
        
        int n = rooms.size();
        
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        return dfs(rooms, 0, visited);
    }
    
    private boolean dfs(List<List<Integer>> rooms, int curr, Set<Integer> visited) {
        if (visited.size() == rooms.size()) {
            return true;
        }
        
        for (int next : rooms.get(curr)) {
            if (visited.contains(next)) {
                continue;
            }
            
            visited.add(next);
            if (dfs(rooms, next, visited)) {
                return true;
            }
            // no need to remove
        }
        
        return false;
    }
    */
    
    
    
    
    
    
    
    /*
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(rooms, i, visited);
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) return false;
        }
        
        return true;
    }
    
    private void dfs(List<List<Integer>> rooms, int curr, boolean[] visited) {
        if (visited[curr]) {
            return;
        }
        
        visited[curr] = true;
        for (int next : rooms.get(curr)) {
            dfs(rooms, next, visited);
        }
    }
    */
}