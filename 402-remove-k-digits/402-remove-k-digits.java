class Solution {
    public String removeKdigits(String num, int k) {
        // non-strick increasing stack
        // make sure within k operations && all numbers are appended after k operations
        // make sure removing 0s at the front
        int n = num.length();
        
        char[] sArr = num.toCharArray();
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : sArr) {
            // NOTE: 这里不可以是 c <= stack.peek()，一定要是 <
            // 因为如果是112，那么11的情况也要被保留，11比12要更小
            while (!stack.isEmpty() && c < stack.peek() && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        
        // NOTE: if there is still k left, remove the last ones,
        // as the stack is increasing, the front ones are smaller
        while (k > 0) {
            stack.pop();
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        boolean metFirst = false;
        
        for (char c : stack) {
            if (c == '0' && !metFirst) {
                continue;
            } else {
                metFirst = true;
                sb.append(c);
            }
        }
        
        // edge case, as 0
        if (sb.length() == 0) return "0";
        
        return sb.toString();
    }
}