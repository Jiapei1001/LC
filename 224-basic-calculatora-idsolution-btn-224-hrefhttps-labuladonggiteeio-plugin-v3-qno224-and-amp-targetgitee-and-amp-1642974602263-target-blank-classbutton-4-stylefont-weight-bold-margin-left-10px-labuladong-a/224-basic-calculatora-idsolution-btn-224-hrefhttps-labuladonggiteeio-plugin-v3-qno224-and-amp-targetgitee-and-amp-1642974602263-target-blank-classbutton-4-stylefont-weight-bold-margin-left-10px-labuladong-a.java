class Solution {

    private int i = 0;
    
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        
        int num = 0;
        int operator = 1;
        
        char[] sArr = s.toCharArray();
        
        while (i <= sArr.length) {
            if (i == sArr.length || sArr[i] == ')') {
                stack.push(operator * num);
                break;
            }
            else if (sArr[i] == '(') {
                i++;
                num = calculate(s);
            }
            else if (Character.isDigit(sArr[i])) {
                num = num * 10 + (sArr[i] - '0');
            }
            else if (!Character.isDigit(sArr[i]) && sArr[i] != ' ') {
                stack.push(operator * num);
                operator = sArr[i] == '+' ? 1 : -1;
                num = 0;
            }
            // 这一步必须加在后面！！否则遇到 ) 就会停止
            i++;
        }
        
        int res = 0;
        Iterator<Integer> it = stack.iterator();
        while(it.hasNext()) {
            res += it.next();
        }
                
        return res;
    }

    // Iterative
    /*
    public int calculate(String s) {
        
        char[] sArr = s.toCharArray();
        
        // 通过accResult来更新括号内部，当前运算的结果
        // 遇到 ( 把accResult和operator存到stack中
        // 遇到 ) 更新accResult，并且通过stack pop与 ( 之前的 result 和 operator 发生关系，来更新accResult
        Deque<Integer> stack = new ArrayDeque<>();
        int currNum = 0;
        int operator = 1;
        int accResult = 0;
        
        int i = 0;
        while (i < sArr.length) {
            if (sArr[i] == ' ') {
                i++;
                continue;
            }
            
            if (Character.isDigit(sArr[i])) {
                currNum = currNum * 10 + (int) (sArr[i] - '0');
            } else if (sArr[i] == '+') {
                
                accResult += operator * currNum;
                operator = 1;
                currNum = 0;
                
            } else if (sArr[i] == '-') {
                
                accResult += operator * currNum;
                operator = -1;
                currNum = 0;
                
            } else if (sArr[i] == '(') {
                stack.push(accResult);
                stack.push(operator);
                
                // 注意这里是因为 ( ，而把accResult清空，不需要currNum = 0、
                // 因为currNum会根据 ( 里的accResult而变化
                // 可以当做一个全新的运算，因为之前的结果已经被包含在stack里，被存了起来
                operator = 1;
                accResult = 0;
                currNum = 0;
                
            } else if (sArr[i] == ')') {
                // 更新括号内部accResult的结果
                accResult += operator * currNum;
                
                // 这里开始和括号 ( 之前的result进行
                
                // operator before (
                accResult *= stack.pop();
                
                // add with accumlated result before (
                accResult += stack.pop();
                
                // operator = 1;
                currNum = 0;
            }
            
            i++;
        }
        
        // 结束前最后更新
        return accResult + (operator * currNum);
    }
    */
    
    
    /* 
    // 自己做的，只对 + 和 () 有效。
    // 当 - 在 () 前，则无效
    public int calculate(String s) {
        
        char[] sArr = s.toCharArray();
        
        Deque<Integer> stack = new ArrayDeque<>();
        int currNum = 0;
        char operator = '+';
        char parenOperator = '+';
        
        int i = 0;
        while (i < sArr.length) {
            if (sArr[i] == ' ') {
                i++;
                continue;
            }
            
            if (Character.isDigit(sArr[i])) {
                currNum = currNum * 10 + (sArr[i] - '0');
            } else if (sArr[i] == '+' || sArr[i] == '-'){
                int prevNum = (stack.isEmpty() || stack.peek() == Integer.MIN_VALUE) ? 
                                0 : stack.pop();
                
                if (operator == '+') {
                    stack.push(prevNum + currNum);
                } else if (operator == '-') {
                    stack.push(prevNum - currNum);
                }
                
                // update operator
                operator = sArr[i];
                currNum = 0;
            } else if (sArr[i] == '(') {
                parenOperator = operator;
                operator = '+';
                currNum = 0;
                stack.push(Integer.MIN_VALUE);
            } else if (sArr[i] == ')') {
                int prevNum = (stack.isEmpty() || stack.peek() == Integer.MIN_VALUE) ? 
                                0 : stack.pop();
                
                if (operator == '+') {
                    stack.push(prevNum + currNum);
                } else if (operator == '-') {
                    stack.push(prevNum - currNum);
                }
                
                
                currNum = stack.pop();
                stack.pop();
                prevNum = stack.isEmpty() ? 0 : stack.pop();
                
                if (parenOperator == '+') {
                    stack.push(prevNum + currNum);
                } else if (parenOperator == '-') {
                    stack.push(prevNum - currNum);
                }
                
                currNum = 0;
            }
            
            i++;
        }
        
        int res = 0;
        int prevNum = stack.pop();
        res = operator == '+' ? prevNum + currNum : prevNum - currNum;
        
        return res;
    }
    */
}