class Solution {
    public List<Integer> splitIntoFibonacci(String num) {
        // List<Integer> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        
        if (num == null || num.length() == 0) return path;
        
        dfs(num, 0, path);
        
        return path;
    }
    
    private boolean dfs(String num, int pos, List<Integer> path) {
        // already found
        // if (res.size() > 0) {
        //     return;
        // }
        
        // bottom case
        if (pos == num.length() && path.size() >= 3) {
            return true;
        }
        
        for (int i = pos + 1; i <= num.length(); i++) {
            String str = num.substring(pos, i);
            
            if (str.length() > 1 && str.startsWith("0")) {
                return false;
            }
            
            long cand = Long.parseLong(str);
            
            if (cand > Integer.MAX_VALUE) {
                return false;
            }
            
            if (isFibonacci((int) cand, path)) {
                path.add((int) cand);
                if (dfs(num, i, path)) {
                    return true;
                }
                path.remove(path.size() - 1);
            }
        }
        
        return false;
    }
    
    private boolean isFibonacci(int curr, List<Integer> path) {
        
        int n = path.size();
        
        // if (n >= 2 && curr > path.get(n - 1) + path.get(n - 2)) {
        //     return false;
        // }
        
        return n <= 1 || curr == (path.get(n - 1) + path.get(n - 2));
    }
}