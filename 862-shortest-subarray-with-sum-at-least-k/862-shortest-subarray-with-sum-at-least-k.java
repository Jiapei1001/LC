class Solution {

    public int shortestSubarray(int[] nums, int k) {
        // subarray as continuous
        // monotonic queue, increasing order
        // front largest, end smallest
        
        // subarray + sum => prefixSum
        int n = nums.length;
        
        long[] psum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            psum[i] = psum[i - 1] + nums[i - 1];
        }
        
        Deque<Integer> monoq = new ArrayDeque<>();
        
        int res = n + 2;
        // loop through prefix sum
        
        // 因为也要保证从头开始的subarray，因此需要从0开始loop到n
        for (int i = 0; i < n + 1; i++) {
            long currVal = psum[i];
            
            // 找到candidate里的最小值，计算是否可以距离更短，同时sum diff >= k
            while (!monoq.isEmpty() && currVal - psum[monoq.peekFirst()] >= k) {
                // 因为segment sum是从[monoq.pollFirst() + 1，i]连续，因此相减已经是segment的长度
                res = Math.min(res, i - monoq.pollFirst());
            }
            
            // 如果现在的val更小，那么它可以替换前面的candidates，因为它的值更小，也比前面的candidate离后面更近
            while (!monoq.isEmpty() && currVal <= psum[monoq.peekLast()]) {
                monoq.pollLast();
            }
            
            // enqueue to the last!!
            monoq.offerLast(i);
        }
        
        return res == n + 2 ? -1 : res;
    }
    
    
    
    
    
    
    
    
    /*
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        
        // monoq is the sliding window
        // it contains all the candidates 
        Deque<Integer> monoq = new ArrayDeque<>();
        
        int res = n + 1;
        for (int j = 0; j < prefixSum.length; j++) {
            // check start/left
            while (!monoq.isEmpty() && prefixSum[j] - prefixSum[monoq.peekFirst()] >= k) {
                // 1-index, the segment length is (j - i)
                res = Math.min(res, j - monoq.pollFirst());
            }
            // check end/right
            while (!monoq.isEmpty() && prefixSum[j] <= prefixSum[monoq.peekLast()]) {
                monoq.pollLast();
            }
            
            monoq.offer(j);
        }
        
        return res == n + 1 ? -1 : res;
    }
    */

    
    
    
    
    /*
    public int shortestSubarray(int[] nums, int k) {
        
        int n = nums.length;
        
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        
        int minLen = Integer.MAX_VALUE;
        
        // monoq stores the candidates of starting points
        // the ending point is j
        // <- start, cand1, cand2, cand3, end ->
        Deque<Integer> monoq = new ArrayDeque<>();
        
        for (int j = 0; j < prefixSum.length; j++) {
            // check monoq's start/left
            // the candidates behind it can be better options
            while (!monoq.isEmpty() && prefixSum[j] - prefixSum[monoq.peekFirst()] >= k) {
                minLen = Math.min(minLen, j - monoq.pollFirst());
            }
            // check monoq's end/right
            // the last candidite is not good,
            // as the current j is more towards the end, thus a better option
            while (!monoq.isEmpty() && prefixSum[j] <= prefixSum[monoq.peekLast()]) {
                monoq.pollLast();
            }
            
            monoq.offerLast(j);
        }
        
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }
    */
}