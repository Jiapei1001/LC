class Solution {
    
    int i = 0;
    public int calculate(String s) {
        int n = s.length();
        
        char[] sArr = s.toCharArray();
        
        Stack<Integer> stack = new Stack<>();
        
        char operator = '+';
        int num = 0;
         
        for (; i < n; i++) {
            if (Character.isDigit(sArr[i])) {
                num = num * 10 + (sArr[i] - '0');
            }
            if (sArr[i] == '(') {
                i = i + 1;
                num = calculate(s);
                System.out.println(num);
                i = i < n - 1 ? i + 1 : i;
            }
            
            if ((sArr[i] != ' ' && !Character.isDigit(sArr[i])) || (i == n - 1)) {
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
            
            if (sArr[i] == ')') {
                break;
            }
        }
        
        
        /*
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(sArr[i])) {
                num = num * 10 + (sArr[i] - '0');
            }
            
            if (sArr[i] == '(') {
                int j = i + 1;
                int p = 1;
                while (j < n) {
                    if (sArr[j] == ')') p--;
                    else if (sArr[j] == '(') p++;
                    
                    if (p == 0) break;
                    j++;
                }
                num = calculate(s.substring(i + 1, j + 1));
                i = j;
            }

            
            if (sArr[i] != ' ' && (!Character.isDigit(sArr[i]) || (i == n - 1))) {
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
        }
        */
        
        
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        
        return res;
    }
    
    /*
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        
        char[] sArr = s.toCharArray();
        
        // 因为是内部Recursion，因此必须限定n的长度！！
        int n = s.length();
        
        int num = 0;
        char operator = '+';
        
        // NOTE: must use for loop, not while with global i
        // 因为这里是利用Recursion，s.substring(i + 1, j)，省去了讨论)的情况，
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
                
                // NOTE: 此时i的位置在(，j的位置在)，这样讨论的是()的内部！！
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
        }
        
        int res = 0;
        Iterator<Integer> it = stack.iterator();
        while (it.hasNext()) {
            res += it.next();
        }
        
        return res;
    }
    */
}