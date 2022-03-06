class Solution {

    public List<Integer> diffWaysToCompute(String expression) {
        // 2 * 3, the last operation is *
        List<Integer> res = new ArrayList<>();
        int n = expression.length();
        
        char[] sArr = expression.toCharArray();
        return dfs(0, n - 1, sArr);
    }
    
    private List<Integer> dfs(int l, int r, char[] sArr) {
        if (l == r) {
            return new ArrayList<>(Arrays.asList(sArr[l] - '0'));
        }
        if (l >= r) {
            return null;
        }
        
        List<Integer> level = new ArrayList<>();
        
        for (int i = l; i <= r; i++) {
            if (!Character.isDigit(sArr[i])) {                
                List<Integer> left = dfs(l, i - 1, sArr);
                List<Integer> right = dfs(i + 1, r, sArr);
                
                if (left == null || right == null) {
                    continue;
                }

                for (int x : left) {
                    for (int y : right) {
                        int temp = 0;
                        switch (sArr[i]) {
                            case '+':
                                temp = x + y;
                                break;
                            case '-':
                                temp = x - y;
                                break;
                            case '*':
                                temp = x * y;
                                break;
                            case '/':
                                temp = x / y;
                                break;
                        }
                        level.add(temp);
                    }
                }
            }
        }
        
        if (level.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = l; i <= r; i++) sb.append(sArr[i]);
            level.add(Integer.parseInt(sb.toString()));
        }
        
        return level;
    }
    
    
    /*
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> res = new ArrayList<>();
        
        if (expression == null || expression.length() == 0) {
            return res;
        }
        
        dfs(expression, 0, res, 1);
        
        return res;
    }
    
    private void dfs(String s, int curr, List<Integer> res, int hasNegative) {
        if (onlyDigit(s)) {
            res.add(curr);
            return;
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                char op = s.charAt(i);
                
                StringBuilder left = new StringBuilder();
                int l = i - 1;
                while(l >= 0 && Character.isDigit(s.charAt(l))) {
                    left.append(s.charAt(l));
                    l--;
                }
                // reverse
                left.reverse();
                
                StringBuilder right = new StringBuilder();
                int r = i + 1;
                while (r < s.length() && Character.isDigit(s.charAt(r))) {
                    right.append(s.charAt(r));
                    r++;
                }
                
                String leftRest = s.substring(0, l + 1);
                String rightRest = s.substring(r);

                int leftNum = Integer.parseInt(left.toString());
                int rightNum = Integer.parseInt(right.toString());

                if (op == '+') {
                    int newNum = leftNum + rightNum;
                    dfs(leftRest + newNum + rightRest, newNum, res);
                } else if (op == '-') {
                    int newNum = Math.abs(leftNum - rightNum);
                    dfs(leftRest + newNum + rightRest, newNum, res);
                } else if (op == '*') {
                    int newNum = leftNum * rightNum;
                    dfs(leftRest + newNum + rightRest, newNum, res);
                }
            }
        }
    }
    
    private boolean onlyDigit(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }
    */
}