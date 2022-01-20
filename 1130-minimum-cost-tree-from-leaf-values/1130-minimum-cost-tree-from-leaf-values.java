class Solution {
    
    
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        
        int res = 0;
        for (int a : arr) {
            while (a >= stack.peek()) {
                int curr = stack.pop();
                res += Math.min(a, stack.peek()) * curr;
            }
            
            stack.push(a);
        }
        
        while (stack.size() > 2) {
            int last = stack.pop();
            res += last * stack.peek();
        }
        
        return res;
    }
    
    
    
    
    
    
    
    
    /*
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
    */
}