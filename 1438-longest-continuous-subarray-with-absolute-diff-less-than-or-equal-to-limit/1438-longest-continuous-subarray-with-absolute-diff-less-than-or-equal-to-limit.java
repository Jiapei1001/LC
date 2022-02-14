class Solution {
    public int longestSubarray(int[] nums, int limit) {
        // sliding window
        
        int n = nums.length;
        
        Deque<int[]> max = new ArrayDeque<>();
        Deque<int[]> min = new ArrayDeque<>();
        
        int l = 0, r = 0;
        
        int res = 0;
        for (; r < n; r++) {
            int curr = nums[r];
            
            while (!max.isEmpty() && max.peekLast()[1] < curr) {
                max.pollLast();
            }
            max.offerLast(new int[]{r, curr});
            
            while (!min.isEmpty() && min.peekLast()[1] > curr) {
                min.pollLast();
            }
            min.offerLast(new int[]{r, curr});
            
            // shrink left
            if (max.peekFirst()[1] - min.peekFirst()[1] > limit) {
                if (max.peekFirst()[1] == nums[l]) max.pollFirst();
                if (min.peekFirst()[1] == nums[l]) min.pollFirst();
                
                l++;
            }
            
            res = Math.max(res, r - l + 1);
        }
        
        return res;
    }
}