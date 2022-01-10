class Solution {
    
    public void solveSudoku(char[][] board) {
        int n = board.length, m = board[0].length;
        
        dfs(board, 0, 0);
    }
    
    private boolean dfs(char[][] board, int r, int c) {
        if (r == 9) {
            return true;
        }
        if (c == 9) {
            return dfs(board, r + 1, 0);
        }
        
        if (board[r][c] != '.') {
            return dfs(board, r, c + 1);
        }
        
        for(char x = '1'; x <= '9'; x++) {
            if (isValid(board, r, c, x)) {
                board[r][c] = x;

                if (dfs(board, r, c + 1)) {
                    return true;
                }

                board[r][c] = '.';
            }
        }

        return false;
    }
    
    private boolean isValid(char[][] board, int r, int c, char x){
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == x) return false;
            
            if (board[i][c] == x) return false;
            
            int nr = (r / 3) * 3 + i / 3;
            int nc = (c / 3) * 3 + i % 3;
            if (board[nr][nc] == x) return false;
        }
        
        return true;
    }
    
    
    /*
    int n = 3;
    
    int N = n * n;
    
    int[][] rows = new int[N][N + 1];
    int[][] columns = new int[N][N + 1];
    int[][] boxes = new int[N][N + 1];
    
    char[][] board;
    
    boolean sudokuSolved = false;
    
    public boolean couldPlace(int d, int row, int col) {
        int idx = (row / n) * n + col / n;
        return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
    }
    
    public void placeNumber(int d, int row, int col) {
        int idx = (row / n) * n + col / n;
        rows[row][d]++;
        columns[col][d]++;
        boxes[idx][d]++;
        board[row][col] = (char)(d + '0');
    }
    
    public void removeNumber(int d, int row, int col) {
        int idx = (row / n) * n + col / n;
        rows[row][d]--;
        columns[col][d]--;
        boxes[idx][d]--;
        board[row][col] = '.';
    }
    
    public void placeNextNumbers(int row, int col) {
        if ((col == N - 1) && (row == N - 1)) {
            sudokuSolved = true;
        } else {
            if (col == N - 1) backtrack(row + 1, 0);
            else backtrack(row, col + 1);
        }
    }
    
    public void backtrack(int row, int col) {
        if (board[row][col] == '.') {
            for (int d = 1; d < 10; d++) {
                if (couldPlace(d, row, col)) {
                    placeNumber(d, row, col);
                    placeNextNumbers(row, col);
                    if (!sudokuSolved) removeNumber(d, row, col);
                }
            }
        } else {
            placeNextNumbers(row, col);
        }
    }
    
    public void solveSudoku(char[][] board) {
        this.board = board;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int d = Character.getNumericValue(num);
                    placeNumber(d, i, j);
                }
            }
        }
        backtrack(0, 0);
    }
    */
}
