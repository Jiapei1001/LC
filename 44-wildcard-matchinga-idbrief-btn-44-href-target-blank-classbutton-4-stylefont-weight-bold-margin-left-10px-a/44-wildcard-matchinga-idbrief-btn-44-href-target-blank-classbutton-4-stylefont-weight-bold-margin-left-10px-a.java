class Solution {
    public boolean isMatch(String s, String p) {
        Map<String, Boolean> memo = new HashMap<>();
        
        return dfs(s, 0, p, 0, memo);
    }
    
    private boolean dfs(String s, int si, String p, int pi, Map<String, Boolean> memo) {
        int n = s.length();
        int m = p.length();
        
        if (si == n && pi == m) {
            return true;
        }
        // si == n, p is left with only regular expression of *
        if (si == n) {
            for (int j = pi; j < m; j++) {
                if (p.charAt(j) != '*') {
                    return false;
                }
            }
            return true;
        }
        
        // NOTE: 检查过上面所有情况后，这一步是为了防止index out of boundary!!
        if (si >= n || pi >= m) {
            return false;
        }
        
        String curr = si + "-" + pi;
        boolean res = false;
        
        if (memo.containsKey(curr)) {
            return memo.get(curr);
        }
        
        char sc = s.charAt(si);
        char pc = p.charAt(pi);
        
        if (sc == pc || pc == '?') {
            res |= dfs(s, si + 1, p, pi + 1, memo);
        } else if (pc == '*') {
            // * represent empty char
            res |= dfs(s, si, p, pi + 1, memo);
            // * represent >0 char
            res |= dfs(s, si + 1, p, pi, memo);
        }
        
        memo.put(curr, res);
        return res;
    }
}