class Solution {
    public int findMin(int[] nums) {
        
        int l = 0, r = nums.length - 1;
        
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            
            if (nums[mid] > nums[r]) {
                l = mid;
            } else {
                r = mid;
            }
        }
        
        return Math.min(nums[l], nums[r]);
    }
}