class Solution {
    
    // Monotonic Stack
    public int trap(int[] height) {
        // Monotonic Stack - decreasing - as accumulating water
        // if find next larger, pop up last elements in the stack
        // the popped element functions as last height
        
        int n = height.length;
        
        // stores index, not height!!
        Deque<Integer> deque = new ArrayDeque<>();
        
        deque.offerLast(1000000);
        
        int res = 0;
        int prevHeight = 0;
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.peekLast() != 1000000 &&  height[deque.peekLast()] < height[i]) {
                int last = deque.pollLast();
                
                // NOTE: here must break, to avoid continuing next step and triggering out of index exception
                if (deque.peekLast() == 1000000) {
                    break;
                }
                
                prevHeight = height[last];
                // (peek, i)
                int width = (i - deque.peekLast() - 1);
                int h = Math.min(height[deque.peekLast()], height[i]) - prevHeight;
                
                res += width * h;
            }
            
            deque.offerLast(i);
        }
        
        // reach n, there might be still remaining places for water
//         while (stack.size() > 1) {
//             int last = deque.popLast();
            
//         }
        
        return res;
    }
    
    
    // Two Pointers
    /*
    public int trap(int[] height) {
        int n = height.length;
        
        int[] left = new int[n];
        int[] right = new int[n];
        
        // find the max height boundary in the left
        left[0] = height[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        
        right[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }
        
        int res = 0;
        
        for (int i = 0; i < n; i++) {
            // Single block
            int tempHeight = Math.min(left[i], right[i]) - height[i];
            int tempArea = tempHeight > 0 ? tempHeight : 0;
            res += tempArea;
        }
        
        return res;
    }
    */
    
    
    
    
    
    
    /*
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        
        int res = 0;
        for (int j = 0; j < height.length; j++) {
            while (!stack.isEmpty() && height[stack.peek()] <= height[j]) {
                int prevHeight = height[stack.pop()];
                
                if (stack.isEmpty()) {
                    break;
                }
                
                int width = j - stack.peek() - 1;
                int heightDiffer = Math.min(height[stack.peek()], height[j]) - prevHeight;
                
                res += heightDiffer * width;
            }
            
            stack.push(j);
        }
        
        return res;
    }
    */
    
    
    
    
    
    
    /*
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int prevTop = height[stack.pop()];
                
                if (stack.isEmpty()) {
                    break;
                }
                
                int width = i - stack.peek() - 1;
                int heightDiffer = Math.min(height[i], height[stack.peek()]) - prevTop;
                
                res += width * heightDiffer;
            }
            
            stack.push(i);
        }
        
        return res;
    }
    */
}