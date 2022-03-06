class Solution {
    
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        
        char[] sArr = word.toCharArray();
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (board[r][c] == sArr[0]) {
                    if (dfs(board, r, c, 1, sArr)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] board, int r, int c, int i, char[] sArr) {
        if (i == sArr.length) {
            return true;
        }
        
        // visited
        char temp = board[r][c];
        board[r][c] = '#';
        
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            
            if (nr < 0 || nr >= board.length || nc < 0 || nc >= board[0].length) {
                continue;
            }
            if (board[nr][nc] == '#') {
                continue;
            }
            if (board[nr][nc] == sArr[i]) {
                if (dfs(board, nr, nc, i + 1, sArr)) {
                    return true;
                }
            }
        }
        
        board[r][c] = temp;
        
        return false;
    }
}