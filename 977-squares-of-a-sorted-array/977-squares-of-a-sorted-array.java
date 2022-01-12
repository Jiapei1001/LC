class Solution {
    
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        
        int[] res = new int[n];
        int l = 0, r = nums.length - 1;
        
        for (int i = n - 1; i >= 0; i--) {
            int base;
            
            if (Math.abs(nums[l]) <= Math.abs(nums[r])) {
                base = Math.abs(nums[r]);
                r--;
            } else {
                base = Math.abs(nums[l]);
                l++;
            }
            
            res[i] = base * base;
        }
        
        return res;
    }
    
    
    /*
    public int[] sortedSquares(int[] nums) {
        return divide(nums, 0, nums.length - 1);
    }
    
    private int[] divide(int[] nums, int l, int r) {
        if (l == r) {
            return new int[]{nums[l] * nums[l]};
        }
        
        int mid = l + (r - l) / 2;
        int[] left = divide(nums, l, mid);
        int[] right = divide(nums, mid + 1, r);
        
        int[] res = merge(left, right);
        
        return res;
    }
    
    private int[] merge(int[] left, int[] right) {
        if (left == null || left.length == 0) return right;
        if (right == null || right.length == 0) return left;
        
        int n = left.length, m = right.length;
        
        int[] res = new int[n + m];
        int l = 0, r = 0;
        int i = 0;
        while(l < n && r < m) {
            if (left[l] <= right[r]) res[i++] = left[l++];
            else res[i++] = right[r++];
        }
        
        while(l < n) {
            res[i++] = left[l++];
        }
        
        while(r < m) {
            res[i++] = right[r++];
        }
        
        return res;
    }
    */
}