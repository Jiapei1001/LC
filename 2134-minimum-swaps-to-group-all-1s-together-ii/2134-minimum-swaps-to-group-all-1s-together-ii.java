class Solution {
    public int minSwaps(int[] nums) {
        // how to find how many swaps?
        // count how many 1s are there in the nums
        // find the largest continuous subarray of 1s
        // total - largest = minimum swaps
        int n = nums.length;
        
        int ones = 0;
        for (int i : nums) {
            if (i == 1) ones++;
        }
        if (ones == 0) return 0;
        
        // circular array
        int[] nArr = new int[n * 2];
        for (int i = 0; i < n * 2; i++) {
            nArr[i] = nums[i % n];
        }
        
        // a window with a length of ones
        int oneCnt = 0;
        for (int i = 0; i < ones; i++) {
            if (nArr[i] == 1) oneCnt++;
        }
        
        int maxOnes = oneCnt;
        for (int i = ones; i < n + ones; i++) {
            if (nArr[i - ones] == 1) oneCnt--;
            if (nArr[i] == 1) oneCnt++;
            
            maxOnes = Math.max(maxOnes, oneCnt);
        }
        
        return ones - maxOnes;
    }
    
    private int findMinSwaps(int[] nArr, int l, int r, int ones) {
        int acc = 0;
        int max = 0;
        for (int i = l; i <= r; i++) {
            if (nArr[i] == 1) {
                acc++;
                max = Math.max(max, acc);
            } else {
                acc = 0;
            }
        }
        
        return ones - max;
    }
}