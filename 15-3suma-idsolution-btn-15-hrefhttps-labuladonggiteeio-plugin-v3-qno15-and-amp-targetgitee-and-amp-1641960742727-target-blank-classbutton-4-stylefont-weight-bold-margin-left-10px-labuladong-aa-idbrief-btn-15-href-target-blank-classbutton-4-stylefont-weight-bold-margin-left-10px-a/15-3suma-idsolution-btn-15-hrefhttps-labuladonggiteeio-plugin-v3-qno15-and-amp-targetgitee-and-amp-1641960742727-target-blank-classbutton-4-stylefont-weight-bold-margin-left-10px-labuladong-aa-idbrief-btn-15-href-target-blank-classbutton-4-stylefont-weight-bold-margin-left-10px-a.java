class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        
        Arrays.sort(nums);
        
        for (int i = 0; i < n - 2; i++) {
            
            // NOTE: avoid duplicated numbers!!
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int l = i + 1, r = n - 1;
            int target = - nums[i];
            
            while (l < r) {
                int left = nums[l], right = nums[r];
                int sum = left + right;
                
                if (sum == target) {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[l], nums[r])));
                    
                    // NOTE: avoid duplicated numbers!!
                    while (l < r && nums[l] == left) l++;
                    while (l < r && nums[r] == right) r--;
                } else if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        
        return res;
    }
}