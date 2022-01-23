class Solution {
    public int calculate(String s) {
        
        char[] sArr = s.toCharArray();
        
        Stack<Integer> stack = new Stack<>();
        
        int num = 0;
        int operator = '+';
        
        int i = 0;
        
        while (i < sArr.length) {
            if (Character.isDigit(sArr[i])) {
                num = num * 10 + (sArr[i] - '0');
            }
            
            // NOTE: 这里千万别是else if，必须是if
            if ((!Character.isDigit(sArr[i]) && sArr[i] != ' ') || i == sArr.length - 1) {
                int prev;
                switch (operator) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        prev = stack.pop();
                        stack.push(prev * num);
                        break;
                    case '/':
                        prev = stack.pop();
                        stack.push(prev / num);
                        break;
                        
                }
                operator = sArr[i];
                num = 0;
            }
            
            i++;
        }
        
        int res = 0;
        Iterator<Integer> it = stack.iterator();
        while (it.hasNext()) {
            res += it.next();
        }
        
        return res;
    }
    
    /*
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        
        char[] sArr = s.toCharArray();
        
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> comp = new ArrayDeque<>();

        int currNum = 0;
        char operator = '+';
        
        int i = 0;
        while (i < sArr.length) {
            // if (sArr[i] == ' ') {
            //     i++;
            //     continue;
            // }
            
            if (Character.isDigit(sArr[i])) {
                currNum = currNum * 10 + (int) (sArr[i] - '0');
            }
            
            if (!Character.isDigit(sArr[i]) && !Character.isWhitespace(sArr[i]) || i == sArr.length - 1) {
                if (operator == '+') {
                    stack.push(currNum);
                } else if (operator == '-') {
                    stack.push(-currNum);
                } else if (operator == '*') {
                    stack.push(stack.pop() * currNum);
                } else if (operator == '/') {
                    stack.push(stack.pop() / currNum);
                }
                
                operator = sArr[i];
                currNum = 0;
            }
            i++;
        }
        
        
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        
        return res;
    }
    */
}