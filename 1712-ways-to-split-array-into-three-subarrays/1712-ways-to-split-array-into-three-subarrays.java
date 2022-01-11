class Solution {
    
    private final int MOD = (int) (1e9 + 7);
    
    public int waysToSplit(int[] nums) {
        int n = nums.length;
        
        int[] prefixSum = new int[n];
        
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        
        // 1, 2, 2, 2, 5, 0
        // 1, 3, 5, 7, 12, 12
        // ( ]
        
        int res = 0;
        for (int i = 0; i < n - 2; i++) {
            if (prefixSum[i] > (prefixSum[n - 1] - prefixSum[i]) / 2) {
                break;
            }
            int l = bs(prefixSum, prefixSum[i], i, true);
            int r = bs(prefixSum, prefixSum[i], i, false);
            
            if (l == -1 || r == -1) continue;
            
            res = (res + (r - l + 1) % MOD) % MOD;
        }
        
        return res;
    }
    
    private int bs(int[] prefixSum, int leftSum, int leftIndex, boolean isLeft) {
        int n = prefixSum.length;
        int l = leftIndex + 1;
        int r = n - 1;
        
        int res = -1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            int midSum = prefixSum[mid] - leftSum;
            int rightSum = prefixSum[n - 1] - prefixSum[mid];
            
            if (leftSum <= midSum && midSum <= rightSum) {
                res = mid;
                if (isLeft) r = mid;
                else l = mid;
            } else if (leftSum > midSum) {
                l = mid;
            } else {
                r = mid;
            }
        }
        
        if (leftSum <= (prefixSum[l] - leftSum) && (prefixSum[l] - leftSum) <= (prefixSum[n - 1] - prefixSum[l])) {
            return l;
        }
        if (leftSum <= (prefixSum[r] - leftSum) && (prefixSum[r] - leftSum) <= (prefixSum[n - 1] - prefixSum[r])) {
            return r;
        }
        
        return -1;
    }
}