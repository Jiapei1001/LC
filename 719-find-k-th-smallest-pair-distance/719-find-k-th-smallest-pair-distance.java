class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        // 1, 1, 6
        
        int n = nums.length;
        // total C(n, 2) = n * (n - 1) / 2 number of pairs
        Arrays.sort(nums);
        
        int l = 0, r = nums[n - 1] - nums[0];
        
        while (l <= r) {
            int mid = l + (r - l) / 2;
            
            // get the number of distance that is <= mid
            int midCnt = countPairs(nums, mid);
            
            if (midCnt >= k) {
                r = mid - 1;
            } else if (midCnt < k) {
                l = mid + 1;
            }
        }
        
        return l;
    }
    
    private int countPairs(int[] nums, int target) {
        int res = 0;
        
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            while (j < nums.length && nums[j] - nums[i] <= target) j++;
            
            res += (j - 1) - i;
        }
        
        return res;
    }
}