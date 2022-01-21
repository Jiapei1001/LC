class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        
        int[] prefixSum = new int[n];
        
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        
        Map<Integer, Integer> count = new HashMap<>();
        
        int res = 0;
        for (int i = 0; i < n; i++) {
            
            if (prefixSum[i] == k) {
                res++;
            } {
                int temp1 = prefixSum[i] - k;
                res += count.getOrDefault(temp1, 0);
            }
            
            count.put(prefixSum[i], count.getOrDefault(prefixSum[i], 0) + 1);
        }
        
        
        // [1,1,1]
        // [1,2,3]
        // 2
//         for (int i = 0; i < n; i++) {
//             if (prefixSum[i] == k) {
//                 res++;
//                 continue;
//             }
//             int temp1 = prefixSum[i] + k;
//             res += count.getOrDefault(temp1, 0);
            
// //             int temp2 = prefixSum[i] - k;
// //             res += count.getOrDefault(temp2, 0);
//         }
        
        return res;
    }
}