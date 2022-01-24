class Solution {
    
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        
        char[] sArr = s.toCharArray();
        
        int n = s.length();
        
        int num = 0;
        char operator = '+';
        
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(sArr[i])) {
                num = num * 10 + (sArr[i] - '0');
            }
            
            if (sArr[i] == '(') {
                int j = i + 1;
                int brace = 1;
                
                for(; j < n; j++) {
                    if (sArr[j] == '(') brace++;
                    if (sArr[j] == ')') brace--;
                    if (brace == 0) break;
                }
                
                num = calculate(s.substring(i + 1, j));
                i = j;
                // 4 conditions
            }
            
            if ((!Character.isDigit(sArr[i]) && sArr[i] != ' ') || i == n -1) {
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

            // if (sArr[i] == ')') {
            //     // i++;
            //     // 4 conditions
            //     // return
            //     break;
            // }
            
            // i++;
        }
        
        int res = 0;
        Iterator<Integer> it = stack.iterator();
        while (it.hasNext()) {
            res += it.next();
        }
        
        return res;
    }
}