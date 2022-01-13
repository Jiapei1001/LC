class Solution {
    public int scoreOfParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int res = 0;
        
        char[] sArr = s.toCharArray();
        
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        
        int i = 0;
        while (i < sArr.length) {
            if (sArr[i] == '(') {
                stack.push(0);
                i++;
            } else if (sArr[i] == ')') {
                int curr = stack.pop();
                int prev = stack.pop();
                
                stack.push(prev + Math.max(curr * 2, 1));
                i++;
            }
        }
        
        return stack.pop();
    }
}