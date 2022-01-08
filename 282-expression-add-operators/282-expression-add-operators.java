class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        
        List<String> path = new ArrayList<>();
        
        // pos, prev, curr, sum, 
        dfs(num, 0, 0, 0, 0, target, path, res);
        
        return res;
    }
    
    
    private void dfs(String num, int pos, long prev, long curr, long currSum, int target,
                     List<String> path, 
                     List<String> res) {
        if (pos == num.length()) {
            if (currSum == target && curr == 0) {
                StringBuilder sb = new StringBuilder();
                for (String s : path) sb.append(s);
                // remove first
                sb.deleteCharAt(0);
                res.add(sb.toString());
            }
            return;
        }
        
        // NOTE: 这里如果是Integer.ValueOf()会报错，因为会形成char对应的数字，一定避免！！
        curr = curr * 10 + Character.getNumericValue(num.charAt(pos));
        String currStr = Long.toString(curr);
        
        // continue to process nums
        if (curr > 0) {
            dfs(num, pos + 1, prev, curr, currSum, target, path, res);
        }
        
        // set operand to the nums
        path.add("+");
        path.add(currStr);
        dfs(num, pos + 1, curr, 0, currSum + curr, target, path, res);
        path.remove(path.size() - 1);
        path.remove(path.size() - 1);
        
        if (path.size() > 0) {
            path.add("-");
            path.add(currStr);
            dfs(num, pos + 1, -curr, 0, currSum - curr, target, path, res);
            path.remove(path.size() - 1);
            path.remove(path.size() - 1);
            
            path.add("*");
            path.add(currStr);
            dfs(num, pos + 1, (prev * curr), 0, currSum - prev + (prev * curr), target, path, res);
            path.remove(path.size() - 1);
            path.remove(path.size() - 1);
        }
    }
}