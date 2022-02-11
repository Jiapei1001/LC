class Solution {
    public char[][] rotateTheBox(char[][] box) {
        
        int n = box.length;
        int m = box[0].length;
        
        char[][] newBox = new char[m][n];
        
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                newBox[c][n - r - 1] = box[r][c];
            }
//         (2, 0) -> (0, 0)
//         (2, 1) -> (1, 0)
//         (2, 2) -> (2, 0)
        
//         nr = c
//         nc = r - 2
        
//         (2, 4)  -> (4, 0)
//         (0, 0)  -> (0, 2)
        
//         (0, 1)  -> (1, 2)
//         (0, 5) -> (5, 2)
            
//         (2, 1) -> (1, 0)
        
        // (2, 2) -> (2, 0)
        // (0, 2) -> (2, 2)
        // (2, 2) -> (0, 2)
        // for (int r = 0; r < m; r++) {
        //     for (int c = 0; c < n; c++) {
        //         newBox[r][c] = box[(c + n - 1) % (n - 1)][r];
        //         System.out.print(newBox[r][c]);
        //     }
        //     System.out.println();
        // }
        
        for (int c = 0; c < n; c++) {
            int r = m - 1, w = m - 1;
            while (r >= 0) {
                if (newBox[r][c] == '#') {
                    newBox[r][c] = '.';
                    newBox[w][c] = '#';
                    r--;
                    w--;
                } else if (newBox[r][c] == '.') {
                    r--;
                } else if (newBox[r][c] == '*') {
                    r--;
                    w = r;
                }
            }
        }
        
        return newBox;
    }
}