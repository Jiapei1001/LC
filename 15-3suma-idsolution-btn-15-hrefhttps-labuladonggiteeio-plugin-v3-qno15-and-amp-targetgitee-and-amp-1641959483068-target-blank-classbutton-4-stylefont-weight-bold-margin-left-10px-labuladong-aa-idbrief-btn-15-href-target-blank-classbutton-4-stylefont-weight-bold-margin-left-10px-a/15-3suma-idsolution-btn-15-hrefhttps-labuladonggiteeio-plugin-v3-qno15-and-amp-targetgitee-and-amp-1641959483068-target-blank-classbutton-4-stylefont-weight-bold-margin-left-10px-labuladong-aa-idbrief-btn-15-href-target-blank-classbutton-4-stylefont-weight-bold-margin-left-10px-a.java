class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        
        Set<List<Integer>> res = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        
        if (nums == null || nums.length < 3) return result;
        
        int n = nums.length;
        
        Arrays.sort(nums);
        
        for (int i = 0; i < n; i++) {
            List<int[]> t = getTwoSum(nums, i, -nums[i]);
            
            if (t.size() == 0) {
                continue;
            }
            
            for (int[] temp : t) {
                res.add(new ArrayList<Integer>(Arrays.asList(nums[i], temp[0], temp[1])));
            }
        }
        
        for (List<Integer> a : res) result.add(a);
        
        return result;
    }
    
    private List<int[]> getTwoSum(int[] nums, int i, int target) {
        
        List<int[]> res = new ArrayList<>();
        
        int m = i + 1, n = nums.length - 1;
        
        while(m < n) {
            if (nums[m] + nums[n] == target) {
                res.add(new int[]{nums[m], nums[n]});
                m++;
            } else if (nums[m] + nums[n] < target) {
                m++;
            } else {
                n--;
            }
        }
        
        return res;
    }
}