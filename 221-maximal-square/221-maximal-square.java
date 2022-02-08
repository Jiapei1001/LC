class Solution {
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        int[] height = new int[m];
        
        int res = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                // build histogram of current level
                height[c] = matrix[r][c] == '1' ? height[c] + 1 : 0;
            }
            
            // calculate maximum area, after the current level's heights have been calculated
            res = Math.max(res, calculateHistogram(height));
        }
        
        return res;
    }
    
    private int calculateHistogram(int[] height) {
        int n = height.length;
        // monostack that always keeps increasing elements
        // intent is to find the left and right limit
        // stack stores index
        Stack<Integer> stack = new Stack<>();
        
        // base case
        stack.push(-1);
        
        int res = 0;
        for (int i = 0; i < n; i++) {
            while (stack.peek() != -1 && height[stack.peek()] >= height[i]) {
                int currHeight = height[stack.pop()];
                int currWidth = i - stack.peek() - 1;
                
                // square's area
                int edge = Math.min(currHeight, currWidth);
                
                res = Math.max(res, edge * edge);
            }
            
            // NOTE: must enqueue index into the stack!!
            stack.push(i);
        }
        
        while (stack.peek() != -1) {
            int currHeight = height[stack.pop()];
            // right limit is n
            int currWidth = n - stack.peek() - 1;
            
            int edge = Math.min(currHeight, currWidth);
            
            res = Math.max(res, edge * edge);
        }
        
        return res;
    }
}