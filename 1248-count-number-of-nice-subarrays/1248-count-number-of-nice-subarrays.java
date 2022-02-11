class Solution {

    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }
    
    private int atMost(int[] nums, int k) {
        int res = 0;
        
        int oddNum = 0;
        
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] % 2 == 1) oddNum++;
            
            // shrink left side
            while (oddNum > k) {
                if (nums[i] % 2 == 1) oddNum--;
                i++;
            }
            
            res += j - i + 1;
        }
        
        return res;
    }
    
    
    
    
    
    /*
    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }
    
    private int atMost(int[] nums, int k) {
        int n = nums.length;
        
        int i = 0;
        int oddCnt = 0;
        int res = 0;
        
        for (int j = 0; j < n; j++) {
            if (nums[j] % 2 == 1)
                oddCnt++;
            
            while (oddCnt > k) {
                if (nums[i] % 2 == 1) {
                    oddCnt--;
                }
                i++;
            }
            res += j - i + 1;
        }
        
        return res;
    }
    */
}