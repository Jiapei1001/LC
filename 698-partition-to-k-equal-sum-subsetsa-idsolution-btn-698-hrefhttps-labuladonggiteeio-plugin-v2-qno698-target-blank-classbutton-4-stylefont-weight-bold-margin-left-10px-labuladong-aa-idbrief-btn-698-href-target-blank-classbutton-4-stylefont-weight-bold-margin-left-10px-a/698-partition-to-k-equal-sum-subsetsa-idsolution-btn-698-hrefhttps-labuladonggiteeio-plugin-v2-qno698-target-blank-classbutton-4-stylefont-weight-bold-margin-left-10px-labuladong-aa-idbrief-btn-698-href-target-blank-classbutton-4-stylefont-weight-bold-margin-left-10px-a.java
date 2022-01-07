class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        
        int sum = 0; 
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        
        int target = sum / k;
        
        if (target * k != sum) return false;
        
        Arrays.sort(nums);
        
        Set<Integer> visitedPos = new HashSet<>();
        
        boolean[] seen = new boolean[nums.length];
        
        if (dfs(nums, 0, 0, target, seen, k)) {
            return true;
        }
        
        return false;
    }
    
    private boolean dfs(int[] nums,
                     int pos,
                     int curr,
                     int target,
                     boolean[] seen,
                     int k) {        
        if (k == 0) {
            return true;
        }

        if (curr == target) {
            return dfs(nums, 0, 0, target, seen, k - 1);
        }
    
        for (int i = pos; i < nums.length; i++) {
            if (seen[i]) {
                continue;
            }
            
            if (i > 1 && nums[i] == nums[i - 1] && !seen[i - 1]) {
                continue;
            }
            
            if (curr + nums[i] > target) {
                continue;
            }
            
            seen[i] = true;
            
            if (dfs(nums, i, curr + nums[i], target, seen, k)) {
                return true;
            }
            
            seen[i] = false;
        }
        
        
        return false;
    }
}