class Solution {
    
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