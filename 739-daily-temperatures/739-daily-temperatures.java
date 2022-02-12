class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        
        int[] res = new int[n];
        
        // decreasing stack
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int last = stack.pop();
                // ( ], thus no need to + 1
                res[last] = i - last;
            }
            
            stack.push(i);
        }
        
        while (!stack.isEmpty()) {
            int last = stack.pop();
            res[last] = 0;
        }
        
        return res;
    }
}