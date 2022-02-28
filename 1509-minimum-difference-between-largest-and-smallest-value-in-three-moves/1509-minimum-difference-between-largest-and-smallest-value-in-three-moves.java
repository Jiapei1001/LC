class Solution {
    public int minDifference(int[] nums) {
        // [5,3,2,4] -> [2,2,2,2]
        // find the minimum value in nums
        // make sure the top 3 largest values are change to this min value
        // find the largest value after above operations
        // min differ = largest - min
        
        // edge case
        
        int n = nums.length;
        if (n <= 4) return 0;
        
        // sort the array
        Arrays.sort(nums);
        // [0,1,5,10,14]
        // [2, n - 2]
        // [1, n - 3]
        
        // kill 3 smallest -> nums[n - 1] - nums[3]
        // kill 2 small, 1 large -> nums[n - 2] - nums[2]
        // kill 1 small, 2 larget -> nums[n - 3] - nums[1]
        // kill 3 large -> nums[n - 4] - nums[0]
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            min = Math.min(min, nums[n - (4 - i)] - nums[i]);
        }
        
        return min;
    }
}