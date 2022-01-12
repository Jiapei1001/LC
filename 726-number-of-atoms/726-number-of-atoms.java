class Solution {
    
    public String countOfAtoms(String formula) {
        int n = formula.length();
        char[] sArr = formula.toCharArray();
        
        Stack<Map<String, Integer>> stack = new Stack<>();
        stack.push(new HashMap<String, Integer>());
        
        for (int i = 0; i < n; i++) {
            if (sArr[i] == '(') {
                stack.push(new HashMap<String, Integer>());
            } else if (sArr[i] == ')') {
                i++;
                
                int cnt = 0;
                while (i < n && Character.isDigit(sArr[i])) {
                    cnt = cnt * 10 + (sArr[i] - '0');
                    i++;
                }
                // NOTE: for loop
                i--;
                
                cnt = cnt == 0 ? 1 : cnt;
                
                Map<String, Integer> curr = stack.pop();
                Map<String, Integer> prev = stack.pop();
                
                Map<String, Integer> newLevel = new HashMap<>();
                
                for (String s : curr.keySet()) {
                    newLevel.put(s, curr.get(s) * cnt);
                }
                
                for (String s : prev.keySet()) {
                    newLevel.put(s, prev.get(s) + newLevel.getOrDefault(s, 0));
                }
                
                stack.push(newLevel);
                
                
            } else {
                String curr = sArr[i] + "";
                i++;
                
                while(i < n && Character.isLetter(sArr[i]) && sArr[i] >= 'a' && sArr[i] <= 'z') {
                    curr += sArr[i];
                    i++;
                }
                
                int cnt = 0;
                while(i < n && Character.isDigit(sArr[i])) {
                    cnt = cnt * 10 + (sArr[i] - '0');
                    i++;
                }
                
                // NOTE: for loop
                i--;
                cnt = cnt == 0 ? 1 : cnt;
                
                Map<String, Integer> last = stack.pop();
                last.put(curr, last.getOrDefault(curr, 0) + cnt);
                
                stack.push(last);
            }
        }
        
        Map<String, Integer> last = stack.pop();
        StringBuilder sb = new StringBuilder();
        
        List<String> keys = new ArrayList<>(last.keySet());
        Collections.sort(keys);
        
        for (String s : keys) {
            sb.append(s).append(last.get(s) > 1 ? last.get(s) : "");
        }
        
        return sb.toString();
    }
    
    
    
    
    
    
    /*
    public String countOfAtoms(String formula) {
        // state
        Stack<Map<String, Integer>> stack = new Stack<>();
        char[] sArr = formula.toCharArray();
        
        // initial level
        stack.push(new HashMap<>());
        
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] == '(') {
                stack.push(new HashMap<String, Integer>());
            } else if (sArr[i] == ')') {
                Map<String, Integer> curr = stack.pop();
                Map<String, Integer> prev = stack.isEmpty() ? new HashMap<String, Integer>() : stack.pop();
                
                // getNum
                i++;
                int cnt = 0;
                while(i < sArr.length && Character.isDigit(sArr[i])) {
                    cnt = cnt * 10 + (sArr[i] - '0');
                    i++;
                }
                cnt = cnt == 0 ? 1 : cnt;
                
                // combine curr and prev
                Map<String, Integer> newLevel = new HashMap<>();
                
                for (String key : curr.keySet()) {
                    newLevel.put(key, curr.get(key) * cnt);
                }
                for (String key : prev.keySet()) {
                    newLevel.put(key, newLevel.getOrDefault(key, 0) + prev.get(key));
                }
                
                stack.push(newLevel);
                
                // 注意往回退一位
                i--;
                
            } else {
                // upper letter
                StringBuilder sb = new StringBuilder();
                int cnt = 0;
                sb.append(sArr[i++]);
                
                while (i < sArr.length && Character.isLetter(sArr[i]) && sArr[i] >= 'a' && sArr[i] <= 'z') {
                    sb.append(sArr[i]);
                    i++;
                }
                // check count
                while (i < sArr.length && Character.isDigit(sArr[i])) {
                    cnt = cnt * 10 + (sArr[i] - '0');
                    i++;
                }
                String name = sb.toString();
                cnt = cnt == 0 ? 1 : cnt;
                stack.peek().put(name, stack.peek().getOrDefault(name, 0) + cnt);
                
                // 注意往回退一位
                i--;
            }
        }
        
        Map<String, Integer> last = stack.pop();
        List<String> list = new ArrayList<>(last.keySet());
        Collections.sort(list);
        
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append(last.get(s) > 1 ? last.get(s) : "");
        }
        
        return sb.toString();
    }
    */
    
    
    /* 自己做的，没成功
    class Atom {
        String name;
        int cnt;
        
        public Atom(String name, int cnt) {
            this.name = name;
            this.cnt = cnt;
        }
    }
    
    public String countOfAtoms(String formula) {
        // count
        // toString
        
        char[] fArr = formula.toCharArray();
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        Stack<Atom> stack = new Stack<>();
        
        int i = 0;

        for (; i < fArr.length; i++) {
            if (fArr[i] >= 'A' && fArr[i] <= 'Z') {
                if (i == 0) {
                    sb.append(fArr[i]);
                } else if (i > 0 && fArr[i - 1] >= 'A' && fArr[i - 1] <= 'Z') {
                    stack.push(new Atom(sb.toString(), 1));
                    sb = new StringBuilder();
                    cnt = 0;
                } else {
                    stack.push(new Atom(sb.toString(), cnt));
                    sb = new StringBuilder();
                    sb.append(fArr[i]);
                    cnt = 0;                    
                }
            } else if (fArr[i] >= 'a' && fArr[i] <= 'z') {
                sb.append(fArr[i]);
            } else if (fArr[i] >= '0' && fArr[i] <= '9') {
                cnt = cnt * 10 + (fArr[i] - '0');
            } else if (fArr[i] == '(') {
                stack.push(null);
            } else if (fArr[i] == ')') {
                stack.push(new Atom(sb.toString(), cnt));
                sb = new StringBuilder();
                cnt = 0;
                // get number
                int j = i + 1;
                for (; j < fArr.length; j++) {
                    if (fArr[j] >= '0' && fArr[j] <='9') {
                        cnt = cnt * 10 + (fArr[j] - '0');    
                    }
                }
                // update stack
                List<Atom> temp = new ArrayList<>();
                while (stack.peek() != null) {
                    temp.add(stack.pop());
                }
                
                for (Atom a : temp) {
                    stack.push(new Atom(a.name, a.cnt * cnt));
                }
                // pop null
                stack.pop();
                
                i = j;
            }
        }
        
        // stack.push(new Atom(sb.toString(), cnt));
        if (i > 0 && fArr[fArr.length - 1] >= 'A' && fArr[fArr.length - 1] <= 'Z') {
            stack.push(new Atom(sb.toString(), 1));
            sb = new StringBuilder();
            cnt = 0;
        }
        
        
        List<Atom> list = new ArrayList<>();
        while (!stack.isEmpty()) list.add(stack.pop());
        
        Collections.sort(list, (a, b) -> a.name.compareTo(b.name));
        
        StringBuilder res = new StringBuilder();
        for (i = 0; i < list.size(); i++) {
            Atom temp = list.get(i);
            res.append(temp.name);
            if (temp.cnt != 1) res.append(temp.cnt);
        }
        
        return res.toString();
    }
    */
}