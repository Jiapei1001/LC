class Solution {
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int acc = 0;
        
        for (int n : nums) {
            acc += n;
            res = Math.max(res, acc);
            acc = Math.max(acc, 0);
        }
        
        return res;
    }
}