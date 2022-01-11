class Solution {
    public int mctFromLeafValues(int[] arr) {
        
        
        Stack<Integer> stack = new Stack<>();
        
        int res = 0;
        stack.push(Integer.MAX_VALUE);
        for (int i : arr) {
            while(stack.peek() <= i) {
                int temp = stack.pop();
                res += temp * Math.min(stack.peek(), i);
            }
            stack.push(i);
        }
        
        while(stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        
        return res;
    }
}