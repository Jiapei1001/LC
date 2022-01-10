class Solution {
    
    
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        
        if (s == null || s.length() == 0) return res;
        
        // get how many ( or ) are there in s
        int l = 0, r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
            } else if (c == ')') {
                if (l > 0) l--;
                else r++;
            }
        }
        
        Set<String> cand = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        
        dfs(s, 0, l, r, sb, cand);
        
        return new ArrayList<String>(cand);
    }
    
    private void dfs(String s, int i, int l, int r, StringBuilder sb, Set<String> cand) {
        if (l < 0 || r < 0) {
            return;
        }
        
        if (i == s.length() && l == 0 && r == 0) {
            if (isValid(sb.toString())) {
                cand.add(sb.toString());
            }
            
            return;
        }
        
        if (i >= s.length()) {
            return;
        }
        
        char curr = s.charAt(i);
        int tempLen = sb.length();
        
        if (curr == '(') {
            dfs(s, i + 1, l - 1, r, sb, cand); // not use
            dfs(s, i + 1, l, r, sb.append(curr), cand); // use
        } else if (curr == ')') {
            dfs(s, i + 1, l, r - 1, sb, cand); // not use
            dfs(s, i + 1, l, r, sb.append(curr), cand); // use
        } else {
            dfs(s, i + 1, l, r, sb.append(curr), cand);
        }
        
        // NOTE: must backtrack
        sb.setLength(tempLen);
    }
    
    private boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int l = 0, r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
            } else if (c == ')') {
                l--;
                if (l < 0) {
                    return false;
                }
            }
        }
        
        return l == 0;
    }
    
    // 自己做的，非常close
    /*
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        
        if (s == null || s.length() == 0) return res;
        
        // get how many ( or ) are there in s
        int l = 0, r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
            } else if (c == ')') {
                if (l > 0) l--;
                else r++;
            }
        }
        
        Set<String> cand = new HashSet<>();
        dfs(s, l, r, cand);
        
        return new ArrayList<String>(cand);
    }
    
    private void dfs(String s, int l, int r, Set<String> cand) {
        if (l == 0 && r == 0) {
            if (isValid(s)) {
                cand.add(s);
            }
            
            return;
        }
        
        if (l < 0 || r < 0) {
            return;
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (l > 0 && s.charAt(i) == '(') {
                dfs(s.substring(0, i) + s.substring(i + 1), l - 1, r, cand);
            }
            if (r > 0 && s.charAt(i) == ')') {
                dfs(s.substring(0, i) + s.substring(i + 1), l, r - 1, cand);
            }
        }
    }
    
    private boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int l = 0, r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
            } else if (c == ')') {
                l--;
                if (l < 0) {
                    return false;
                }
            }
        }
        
        return l == 0;
    }
    */
}