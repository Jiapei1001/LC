class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // get the first row >= target
        // binary search the row before it
        
        int n = matrix.length, m = matrix[0].length;
        
        // [l, r)
        int l = 0, r = (n - 1) + 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            
            if (matrix[mid][0] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        
        int row = l - 1;
        
        if (row < 0 || row >= n) return false;
        
        // 找某一个数
        // [l, r]
        l = 0;
        r = (m - 1);
        while (l <= r) {
            int mid = l + (r - l) / 2;
            
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] < target) {
                l = mid + 1;
            } else if (matrix[row][mid] > target) {
                r = mid - 1;
            }
        }
        
        return false;
    }
}