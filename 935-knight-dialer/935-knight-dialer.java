class Solution {
    
    private int MOD = (int) 1e9 + 7;
    
    private int[][] dirs = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, 
                            {2, 1}, {-2, 1}, {2, -1}, {-2, -1}};
    
    public int knightDialer(int n) {
        int[][] dp = getBoard();
        
        for (int k = 0; k < n - 1; k++) {
            // NOTE: 这里不可以是getBoard()起始状态，因为会多增加1
            // 直接initiate为0即可
            int[][] temp = new int[4][3];
            
            for (int r = 0; r < 4; r++)
                for (int c = 0; c < 3; c++) {
                    if (r == 3 && c == 0) continue;
                    if (r == 3 && c == 2) continue;
                    
                    for(int[] dir : dirs) {
                        int pr = r - dir[0];
                        int pc = c - dir[1];
                        
                        if (pr < 0 || pr >= 4 || pc < 0 || pc >= 3) {
                            continue;
                        }
                        if (pr == 3 && pc == 0) continue;
                        if (pr == 3 && pc == 2) continue;
                        
                        temp[r][c] = (temp[r][c] + dp[pr][pc]) % MOD;
                    }
                }
            
            dp = temp;
        }
        
        int res = 0;
        for (int r = 0; r < 4; r++)
            for (int c = 0; c < 3; c++) {
                res = (res + dp[r][c]) % MOD;
            }
        
        return res;
    }
    
    private int[][] getBoard() {
        int[][] board = new int[4][3];
        for (int[] b : board) {
            Arrays.fill(b, 1);
        }
        
        board[3][0] = 0;
        board[3][2] = 0;
        
        return board;
    }
}