class Solution {
    
    int N;
    
    public int numTrees(int n) {
        N = n;
        
        // f(i) = f(0, i - 1) * f(i + 1, n)
        // base case return 1
        
        Map<String, Integer> memo = new HashMap<>();
        
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res += getNumbers(1, i - 1, memo) * getNumbers(i + 1, n, memo);
        }
        
        return res;
    }
    
    private int getNumbers(int left, int right, Map<String, Integer> memo) {
        if (left >= right) {
            return 1;
        }
        
        String temp = left + "-" + right;
        if (memo.containsKey(temp)) {
            return memo.get(temp);
        }
        
        int res = 0;
        for (int i = left; i <= right; i++) {
            res += getNumbers(left, i - 1, memo) * getNumbers(i + 1, right, memo);
        }
        
        memo.put(temp, res);
        
        return res;
    }
}