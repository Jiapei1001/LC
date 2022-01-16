class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        
        boolean[] visited = new boolean[n];
        
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                res++;
            }
        }
        
        return res;
    }
    
    private void dfs(int[][] isConnected, boolean[] visited, int i) {
        if (visited[i]) {
            return;
        }
        
        visited[i] = true;
        
        for (int j = 0; j < isConnected[i].length; j++) {
            if (isConnected[i][j] == 1) {
                dfs(isConnected, visited, j);
            }
        }
    }
}