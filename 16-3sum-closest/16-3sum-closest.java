class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        
        Arrays.sort(nums);
        
        int minDiff = Integer.MAX_VALUE;
        
        int res = 0;
        for (int i = 0; i < n; i++) {
            int complement = target - nums[i];
            
            // find two nums that add up very close to complement
            int l = i + 1, r = n - 1;
            while (l < r) {
                int tempSum = nums[i] + nums[l] + nums[r];
                
                if (Math.abs(tempSum - target) < Math.abs(minDiff)) {
                    minDiff = target - tempSum;
                }
                
                if (tempSum - target == 0) {
                    return tempSum;
                } else if (tempSum < target) {
                    l++;
                    // while (l < r && nums[l] == nums[l - 1]) l++;
                } else {
                    r--;
                    // while (l < r && nums[r] == nums[r + 1]) r--;
                }
            }
        }
        
        return target - minDiff;
    }
}