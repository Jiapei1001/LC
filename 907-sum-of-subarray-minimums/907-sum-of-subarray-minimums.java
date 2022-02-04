class Solution {
    
    private static final int MOD = 1000000007;

    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        
        // stack, value with continuous indexes
        Stack<Integer> stack = new Stack<>();
        
        int[] leftNextLess = new int[n];
        for (int i = 0; i < n; i++) leftNextLess[i] = i + 1;
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                // meaning this last one in stack cannot be the next less element
                stack.pop();
            }
            leftNextLess[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            
            stack.push(i);
        }
        
        
        stack = new Stack<>();
        
        int[] rightNextLess = new int[n];
        for (int i = 0; i < n; i++) rightNextLess[i] = n - i;
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                // meaning att[i] is the next less element
                int last = stack.pop();
                rightNextLess[last] = i - last;
            }
            
            stack.push(i);
        }
        
        long res = 0;
        for (int i = 0; i < n; i++) {
            res = (res + (long) arr[i] * leftNextLess[i] * rightNextLess[i]) % MOD;
        }
        
        return (int) res;
    }
    
    
    // TLE
    /*
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        
        int res = 0;
        for (int i = 0; i < n; i++) {
            int currMin = arr[i];
            for (int j = i; j < n; j++) {
                currMin = Math.min(currMin, arr[j]);
                
                res = (res + currMin) % MOD;
            }
        }
        
        return res;
    }
    */
}