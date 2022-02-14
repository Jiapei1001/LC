class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
//         f(j) = MAX {f(i) + nums[j]}, where j - k <= i <= j
        
//         我们需要找到一个k长度的window，快速得到里面sum的最大值
//         decreasing order
//         如果里面的最大值的距离 > k，那么直接pop()掉
//         如果现在的sum > 后面的candidate，那么这些candidate已经没用了，可以被pop()掉
        
//         因为是之前的k window里的state比较，因此结算是在
        
        int n = nums.length;
        
        // {index i, dp[i]}, where dp[i] represents the max sum for the front i items
        Deque<int[]> monoq = new ArrayDeque<>();
        
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // 这里是为了找到之前state的max与现在nums[i]相加
            int currSum = nums[i];
            // NOTE: 如果之前state的sum < 0，那么没有必要继续延续这个sum，直接取 0 
            currSum += monoq.isEmpty() ? 0 : Math.max(monoq.peekFirst()[1], 0);
            
            // 更新global max
            res = Math.max(res, currSum);
            
            // >= 可以保证这里的window最大值是 k - 1，
            // 那么当i变成下一个state的i + 1时，那么monoq里的candidate与i + 1的距离都<= k
            while (!monoq.isEmpty() && (i - monoq.peekFirst()[0] >= k)) {
                monoq.pollFirst();
            }
            
            while (!monoq.isEmpty() && monoq.peekLast()[1] <= currSum) {
                monoq.pollLast();
            }
            
            monoq.offerLast(new int[]{i, currSum});
        }
        
        return res;
    }
}