class Solution {
    
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        
        int res = n + 1;
        
        int i = 0;
        int curr = 0;
        for (int j = 0; j < n; j++) {
            curr += nums[j];
            
            while (i <= j && curr >= s) {
                res = Math.min(res, j - i + 1);
                curr -= nums[i];
                i++;
            }
        }
        
        return res == n + 1 ? 0 : res;
    }
    
    
    /* Option 2 - Binary Search
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        
        int[] prefixSum = new int[nums.length + 1];
        
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        
        int left = 1, right = nums.length;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (hasSubarrayWithMidLen(prefixSum, mid, s)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        if (hasSubarrayWithMidLen(prefixSum, left, s))
            return left;
        
        if (hasSubarrayWithMidLen(prefixSum, right, s))
            return right;
        
        return 0;
    }
    
    private boolean hasSubarrayWithMidLen(int[] prefixSum, int len, int target) {
        //    2, 3, 1, 2, 4
        // 0, 2, 5, 6, 8, 12
        for (int i = len; i < prefixSum.length; i++) {
            if (prefixSum[i] - prefixSum[i - len] >= target)
                return true;
        }
        
        return false;
    }
    */

    // Option 1 - without prefixSum[]
    /*
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        
        int res = n + 1;
        
        int i = 0;
        int currSum = 0;
        
        for (int j = 0; j < n; j++) {
            currSum += nums[j];
            
            while (currSum >= s) {
                res = Math.min(res, j - i + 1);
                currSum -= nums[i];
                i++;
            }
        }
        
        return res == n + 1 ? 0 : res;
    }
    */
    
    // Option 2 - with prefixSum[]
    /*
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        
        int[] prefixSum =  new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        
        int res = n + 1;
        int i = 0;
        for (int j = 0; j < prefixSum.length; j++) {
            
            while(prefixSum[j] - prefixSum[i] >= s) {
                res = Math.min(res, j - i);
                i++;    
            }
        }
        
        return res == n + 1 ? 0 : res;
    }
    */


    /* Option 1 - while loop
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        
        int i = 0, j = 0;
        int minLen = Integer.MAX_VALUE;
        
        int currSum = 0;
        while (j < nums.length) {
            currSum += nums[j];
            if (currSum < s) {
                j++;
            } else {
                minLen = Math.min(minLen, j - i + 1);
                currSum -= nums[i];
                currSum -= nums[j];
                i++;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    */ 


    /* Option 1 - For loop 
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        
        int i = 0, j = 0;
        
        int minLen = Integer.MAX_VALUE;
        int currSum = 0;
        for (j = 0; j < nums.length; j++) {
            currSum += nums[j];
            
            while (currSum >= s) {
                minLen = Math.min(minLen, j - i + 1);
                currSum -= nums[i++];
            }
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    */


    /*
    public int minSubArrayLen(int s, int[] nums) {
        if (s <= 0 || nums == null || nums.length == 0) return 0;
        
        int left, right, min_length = Integer.MAX_VALUE;
        
        for (left = 0; left < nums.length; left++) {
            if (nums[left] >= s) return 1;
            int temp = 1, sum = nums[left];
            for (right = left + 1; right < nums.length; right++) {
                temp++;
                sum += nums[right];
                if (sum >= s) break;
            }
            if (sum >= s) min_length = Math.min(min_length, temp);
        }
        
        return min_length > nums.length ? 0 : min_length;
    }
    */
}
