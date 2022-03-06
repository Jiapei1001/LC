class Solution {
    public int numberOfPatterns(int m, int n) {
        // 1: 9
        // 2: 1 (1,2),(1,4),(1,5),(1,6),(1,8), 5 * 4
        //    2 (2,1),(2,3),(2,4),(2,5),(2,6),(2,7),(2,9), 7 * 4
        //    5 8
        // 20 + 28 + 8 = 56
        // 3: 1 ()
            
        int[][] skip = new int[10][10];
        // skip helps to check if the former dot has been visited before
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
        
        boolean[] visited = new boolean[10];
        
        int res = 0;
        for (int i = m; i <= n; i++) {
            res += helper(1, visited, skip, i - 1) * 4;
            res += helper(2, visited, skip, i - 1) * 4;
            res += helper(5, visited, skip, i - 1);
        }
        
        return res;
    }
    
    private int helper(int curr, boolean[] visited, int[][] skip, int remain) {
        if (remain < 0) return 0;
        if (remain == 0) return 1;
        
        visited[curr] = true;
        int res = 0;
        
        for (int next = 1; next <= 9; next++) {
            int checkDot = skip[curr][next];
            
            if (!visited[next] && (skip[curr][next] == 0 || visited[checkDot])) {
                res += helper(next, visited, skip, remain - 1);
            }
        }
        
        visited[curr] = false;
        return res;
    }
}