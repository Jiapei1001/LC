class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        
        if (s == null || s.length() == 0) {
            return res;
        }
        
        int n = s.length();
        boolean[][] memo = new boolean[n][n];
        // base case
        for (int i = 0; i < n; i++) memo[i][i] = true;
        
        List<String> path = new ArrayList<>();
        dfs(s, 0, path, res, memo);
        
        return res;
    }
    
    private void dfs(String s, int i, List<String> path,
                     List<List<String>> res, boolean[][] memo) {
        if (i == s.length()) {
            res.add(new ArrayList<String>(path));
            return;
        }
        
        char[] sArr = s.toCharArray();
        
        // [i, j]
        for (int j = i; j < s.length(); j++) {
           if (j == i || (sArr[i] == sArr[j] && (j == i + 1 || memo[i + 1][j - 1]))) {
               memo[i][j] = true;
               
               String temp = s.substring(i, j + 1);
               path.add(temp);
               dfs(s, j + 1, path, res, memo);
               path.remove(path.size() - 1);
           }
        }
    }
    
    
    // Backtracking
    /*
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
    */
}