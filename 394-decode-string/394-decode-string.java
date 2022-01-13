class Solution {
    
    private int i = 0;
    
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        
        int n = s.length();
        
        char[] sArr = s.toCharArray();
        
        while (i < n && sArr[i] != ']') {
            if (Character.isLetter(sArr[i])) {
                sb.append(sArr[i]);
                i++;
                
            } else if (Character.isDigit(sArr[i])) {
                int cnt = 0;
                
                while (i < n && Character.isDigit(sArr[i])) {
                    cnt = cnt * 10 + (sArr[i] - '0');
                    i++;
                }
                
                cnt = cnt == 0 ? 1 : cnt;
                
                // NOTE: next one after [
                i++;
                
                String next = decodeString(s);
                
                // NOTE: next one after ]
                i++;
                
                while (cnt-- > 0) {
                    sb.append(next);
                }
            } 
        }
        
        return sb.toString();
    }
    
    
    /*
    public String decodeString(String s) {
        
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        
        char[] sArr = s.toCharArray();
        
        StringBuilder curr = new StringBuilder();
        int cnt = 0;
        
        for (int i = 0; i < sArr.length; i++) {
            if (Character.isDigit(sArr[i])) {
                cnt = cnt * 10 + (sArr[i] - '0');
            } else if (sArr[i] == '[') {
                // new level
                numStack.push(cnt);
                strStack.push(curr);
                
                // update for next level
                cnt = 0;
                curr = new StringBuilder();
            } else if (sArr[i] == ']') {
                
                int k = numStack.pop();
                StringBuilder prev = strStack.isEmpty() ? new StringBuilder() : strStack.pop();
                String currLevelStr = curr.toString();
                
                for (int j = 0; j < k; j++) {
                    prev.append(currLevelStr);
                }
                
                curr = prev;
            } else {
                curr.append(sArr[i]);
            }
        }
        
        return curr.toString();
    }
    */
    
    
    /* 自己做的错了
    public String decodeString(String s) {
        // stack for num
        // stack for string
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        
        char[] sArr = s.toCharArray();
        
        for (int i = 0; i < sArr.length; i++) {
            if (Character.isDigit(sArr[i])) {
                int cnt = 0;
                
                while (i < sArr.length && Character.isDigit(sArr[i])) {
                    cnt = cnt * 10 + (sArr[i] - '0');
                    i++;
                }
                
                numStack.push(cnt);
                
                i--;
                
            } else if (sArr[i] == '[') {
                StringBuilder sb = new StringBuilder();
                
                i = i + 1;
                while (i < sArr.length && Character.isLetter(sArr[i])) {
                    sb.append(sArr[i]);
                    i++;
                }
                strStack.push(sb.toString());
                
                i--;
            } else if (sArr[i] == ']') {
                int num = numStack.pop();
                String curr = strStack.pop();
                String prev = strStack.isEmpty() ? "" : strStack.pop();
                
                StringBuilder sb = new StringBuilder();
                sb.append(prev);
                
                for (int j = 0; j < num; j++) {
                    sb.append(curr);
                }
                
                strStack.push(sb.toString());
            } else if (Character.isLetter(sArr[i])) {
                StringBuilder sb = new StringBuilder();
                
                while (i < sArr.length && Character.isLetter(sArr[i])) {
                    sb.append(sArr[i]);
                    i++;
                }
                strStack.push(sb.toString());
                
                i--;
            }
        }
        
        List<String> list = new ArrayList<>();
        while (!strStack.isEmpty()) {
            list.add(0, strStack.pop());
        }
        
        StringBuilder sb = new StringBuilder();
        for (String str : list) sb.append(str);
        
        return sb.toString();
    }
    */
}