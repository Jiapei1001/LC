class Solution {
    public List<String> generateParenthesis(int n) {
        int l = n, r = n;
        
        List<String> res = new ArrayList<>();
        
        dfs("", l, r, res);
        
        return res;
    }
    
    private void dfs(String s, int l, int r, List<String> res) {
        if (l < 0 || r < 0) {
            return;
        }
        
        if (l == 0 && r == 0) {
            if (isValid(s)) {
                res.add(s);
            }
            return;
        }
        
        dfs(s + '(', l - 1, r, res);
        dfs(s + ')', l, r - 1, res);
    }
    
    private boolean isValid(String s) {
        int n = s.length();
        
        if (n % 2 == 1) return false;
        
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
        
        return true;
    }
}