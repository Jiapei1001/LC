class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        
        int[] res = new int[n];
        
        Stack<Integer> stack = new Stack<>();
        // keep the stack always increasing
        for (int i = n * 2 - 1; i >= 0; i--) {
            // NOTE: here also must include ==, as the stack.peek() can be replaced by current value
            while (!stack.isEmpty() && nums[i % n] >= stack.peek()) {
                // pop, as it is <= curr i, thus is replaced by larger value
                stack.pop();
            }
            
            res[i % n] = stack.isEmpty() ? -1 : stack.peek();
            
            // add current largest number into the stack
            stack.push(nums[i % n]);
        }
        
        return res;
    }
}