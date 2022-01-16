class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length, m = matrix[0].length;
        
        // 答案集上Binary Search
        int l = matrix[0][0], r = matrix[n - 1][m - 1];
        
        while (l < r) {
            int mid = l + (r - l) / 2;
            
            int[] adjacentLessLarger = new int[]{matrix[0][0], matrix[n - 1][m - 1]};
            int midCnt = countNumsLessEqualThan(matrix, mid, adjacentLessLarger);
            
            if (midCnt == k) {
                return adjacentLessLarger[0];
            } else if (midCnt < k) {
                l = adjacentLessLarger[1];
            } else {
                r = adjacentLessLarger[0];
            }
        }
        
        return l;
    }
    
    private int countNumsLessEqualThan(int[][] matrix, int mid, int[] adjacentLessLarger) {
        int n = matrix.length;
        
        int r = n - 1, c = 0;
        
        int count = 0;
        while (r >= 0 && c <= n - 1) {
            if (matrix[r][c] > mid) {
                adjacentLessLarger[1] = Math.min(adjacentLessLarger[1], matrix[r][c]);
                r--;
            } else {
                adjacentLessLarger[0] = Math.max(adjacentLessLarger[0], matrix[r][c]);
                c++;
                
                // count upper left part
                count += r + 1;
            }
        }
        
        return count;
    }
}