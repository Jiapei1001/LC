class Solution {
    
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        
        List<Integer> path = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfs(n, visited, path, res);
        
        return res;
    }
    
    private void dfs(int n, Set<Integer> visited, List<Integer> path, List<List<String>> res) {
        if (path.size() == n) {
            List<String> cand = printBoard(path);
            res.add(new ArrayList<String>(cand));
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (visited.contains(i)) {
                continue;
            }
            
            if (isValid(path, i)) {
                visited.add(i);
                path.add(i);
                
                dfs(n, visited, path, res);
                
                visited.remove(i);
                path.remove(path.size() - 1);
            }
        }
    }
    
    private boolean isValid(List<Integer> path, int i) {
        int currRow = path.size();

        for (int r = 0; r < currRow; r++) {
            int prev = path.get(r);
            
            // if (i == prev) return false;
            
            int colDiff = Math.abs(i - prev);
            int rowDiff = Math.abs(currRow - r);
            
            if (colDiff == rowDiff) return false;
        }
        
        return true;
    }
    
    private List<String> printBoard(List<Integer> path) {
        List<String> res = new ArrayList<>();
        int n = path.size();
        
        for (int r = 0; r < n; r++) {
            StringBuilder sb = new StringBuilder();
            
            for (int c = 0; c < n; c++) {
                if (c == path.get(r)) sb.append("Q");
                else sb.append(".");
            }
            
            res.add(sb.toString());
        }
        
        return res;
    }
    
    
    
    
    
    
    /*
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n == 0) return res;
        
        // path ???????????????
        this.dfs(n, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void dfs(int n,
                     List<Integer> path,
                     List<List<String>> res) {
        if (path.size() == n) {
            res.add(this.draw(path));
        }
        for(int c = 0; c < n; c++) {
            if (!this.isValid(path, c)) continue;
            path.add(c);
            this.dfs(n, path, res);
            path.remove(path.size() - 1);
        }
    }
    
    private List<String> draw(List<Integer> path) {
        List<String> cand = new ArrayList<>();
        for (int r = 0; r < path.size(); r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < path.size(); c++) {
                if (path.get(r) == c) sb.append("Q");
                else sb.append(".");
            }
            cand.add(sb.toString());
        }
        return cand;
    }
    
    private boolean isValid(List<Integer> path, int col) {
        // ?????????????????????queue???row
        int row = path.size();
        for (int rowIndex = 0; rowIndex < path.size(); rowIndex++) {
            // path??????????????????????????????????????????????????????
            // ??????????????????????????????
            if (path.get(rowIndex) == col) return false;
            // ???????????????
            if (path.get(rowIndex) + rowIndex == col + row) return false;
            // ???????????????
            if (path.get(rowIndex) - rowIndex == col - row) return false;
        }
        return true;
    }
    */
}