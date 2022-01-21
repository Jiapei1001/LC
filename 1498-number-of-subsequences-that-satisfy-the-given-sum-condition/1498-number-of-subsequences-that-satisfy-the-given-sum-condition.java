class Solution {
    
    private int MOD = (int) 1e9 + 7;
    
    public int numSubseq(int[] nums, int target) {
        // [1,2,3] 3 + 2 + 1
        // [1,2,3,4] 4 + 3 + 2 + 1
        // n numbers, n * (n + 1) / 2 subsequence
        
        int n = nums.length;
        Arrays.sort(nums);
        
        // NOTE: 因为已经排序，因此并不需要max和min
        // int[] max = new int[n];
        // int[] min = new int[n];
        
//         max[n - 1] = nums[n - 1];
//         for (int i = n - 2; i >= 0; i--) {
//             max[i] = Math.max(max[i + 1], nums[i]);
//         }
        
//         min[0] = nums[0];
//         for (int i = 1; i < n; i++) {
//             min[i] = Math.min(min[i - 1], nums[i]);
//         }
        
        int[] pows = new int[n];
        pows[0] = 1;
        
        for (int i = 1; i < n; i++) {
            pows[i] = pows[i - 1] * 2 % MOD;
        }
        
        long res = 0;
        
        int l = 0, r = n - 1;
        while (l <= r) {
            int temp = nums[l] + nums[r];
            
            if (temp <= target) {
                // res = (res + m * (m + 1) / 2) % MOD;
                
                // NOTE：拿与不拿，因此是 2 ^ (j - i)
                // int m = r - l;
                // long subArrayCount = (long) Math.pow(2, m);
                
                int subArrayCount = pows[r - l];
                res = (res + subArrayCount) % MOD;
                
                l++;
            } else if (temp > target) {
                r = r - 1;
            }
        }
        
        return (int) res % MOD;
    }
}