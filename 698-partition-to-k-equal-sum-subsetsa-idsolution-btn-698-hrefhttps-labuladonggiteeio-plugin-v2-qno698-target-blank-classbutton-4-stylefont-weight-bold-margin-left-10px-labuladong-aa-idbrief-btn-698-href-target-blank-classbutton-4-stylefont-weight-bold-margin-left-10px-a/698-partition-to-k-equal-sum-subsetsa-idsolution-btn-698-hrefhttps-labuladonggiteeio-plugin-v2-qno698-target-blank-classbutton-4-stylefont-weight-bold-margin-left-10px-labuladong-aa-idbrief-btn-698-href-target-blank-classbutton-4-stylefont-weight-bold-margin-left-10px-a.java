class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        
        int sum = 0; 
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        
        int target = sum / k;
        
        if (target * k != sum) return false;
        
        // Arrays.sort(nums);
        
        Set<Integer> visitedPos = new HashSet<>();
        
        // boolean[] seen = new boolean[nums.length];
        
        if (dfs(nums, 0, 0, target, visitedPos, k)) {
            return true;
        }
        
        return false;
    }
    
    private boolean dfs(int[] nums,
                     int pos,
                     int curr,
                     int target,
                     Set<Integer> visitedPos,
                     int k) {        
        if (k == 0) {
            return true;
        }

        if (curr == target) {
            return dfs(nums, 0, 0, target, visitedPos, k - 1);
        }

        for (int i = pos; i < nums.length; i++) {
            if (visitedPos.contains(i)) {
                continue;
            }
            
            if (i > 1 && nums[i] == nums[i - 1] && !visitedPos.contains(i - 1)) {
                continue;
            }
            
            if (curr + nums[i] > target) {
                continue;
            }
            
            // seen[i] = true;
            visitedPos.add(i);
            
            if (dfs(nums, i, curr + nums[i], target, visitedPos, k)) {
                return true;
            }
            
            // seen[i] = false;
            visitedPos.remove(i);
        }
        
        return false;
    }
}