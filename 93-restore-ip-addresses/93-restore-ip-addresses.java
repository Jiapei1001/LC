class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        
        if (s == null || s.length() < 4) return res;
        
        dfs(s, "", 0, 0, res);
        
        return res;
    }
    
    private void dfs(String s, String curr, int pos, int seg, List<String> res) {
        if (pos == s.length() && seg == 4) {
            // remove final . at the end
            res.add(curr);
            return;
        }
        
        if (pos > s.length() || seg >= 4) {
            return;
        }
        
        for (int i = pos + 1; i <= s.length(); i++) {
            String cand = s.substring(pos, i);
            
            // check valid
            if (!isValid(cand)) {
                continue;
            }
            if (pos > 0) {
                dfs(s, curr + "." + cand, i, seg + 1, res);
            } else {
                dfs(s, cand, i, seg + 1, res);
            }
        }
    }
    
    private boolean isValid(String cand) {
        if (cand.length() > 1 && cand.startsWith("0")) {
            return false;
        }
        
        if (cand.length() > 3) {
            return false;
        }
        
        int n = Integer.parseInt(cand);
        
        if (n >= 256) {
            return false;
        }
        
        return true;
    }
}