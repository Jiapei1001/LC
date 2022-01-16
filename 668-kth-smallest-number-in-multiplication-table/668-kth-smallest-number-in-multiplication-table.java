class Solution {
    public int findKthNumber(int m, int n, int k) {
        int l = 0, r = m * n;
        
        // [l, r)
        while (l < r) {
            int mid = l + (r - l) / 2;
            
            int midCnt = getCountLessEqualThan(m, n, mid);
            
            if (midCnt < k) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        
        return l;
    }
    
    private int getCountLessEqualThan(int m, int n, int mid) {
        int res = 0;
        
        for (int r = 1; r <= m; r++) {
            res += Math.min(mid / r, n);
        }
        
        return res;
    }
    
    /*
    private int getCountLessEqualThan(int m, int n, int mid) {
        int res = 0;
        
        for (int r = 1; r <= m; r++)
            for (int c = 1; c <= n; c++) {
                if (r * c <= mid) res++;
                else break;
            }
        
        return res;
    }
    */
}