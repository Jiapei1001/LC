class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        
        if (s == null || s.length() == 0) return res;
        
        // check if it is palindrome
        // reach the end of the string
        
        List<String> path = new ArrayList<>();
        
        dfs(s, 0, path, res);
        
        return res;
    }
    
    private void dfs(String s, int pos, List<String> path, List<List<String>> res) {
        if (pos == s.length()) {
            res.add(new ArrayList<String>(path));
            return;
        }
        
        // check next
        for (int i = pos + 1; i <= s.length(); i++) {
            String cand = s.substring(pos, i);
            
            if (!isPalindrome(cand)) {
                continue;
            }
            
            path.add(cand);
            dfs(s, i, path, res);
            path.remove(path.size() - 1);
        }
    }
    
    private boolean isPalindrome(String cand) {
        if (cand == null || cand.length() == 0) {
            return false;
        }
        
        int l = 0, r = cand.length() - 1;
        while (l <= r) {
            if (cand.charAt(l) != cand.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        
        return true;
    }
}