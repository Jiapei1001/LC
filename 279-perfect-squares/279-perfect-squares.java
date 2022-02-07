class Solution {
    public int numSquares(int n) {
        //f(i) = MIN{ f(j) + 1 }, where (i - j) is perfect square
            
        int[] dp = new int[n + 1];
        
        Arrays.fill(dp, 100000);
        dp[0] = 0;
        
        int max_square_idx = (int) Math.sqrt(n) + 1;
        int[] square = new int[max_square_idx];
        
        for (int i = 1; i < max_square_idx; i++) {
            square[i] = i * i;
        }
        
        
        Map<Integer, Boolean> memo = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < max_square_idx; j++) {
                if (i < square[j]) {
                    break;
                }
                
                dp[i] = Math.min(dp[i], dp[i - square[j]] + 1);
            }
        }
        
        return dp[n];
    }
    
    /*
    private boolean isPerfect(int m, Map<Integer, Boolean> memo) {
        if (memo.containsKey(m)) {
            return memo.get(m);
        }
        
        int root = (int) Math.sqrt(m);
        
        boolean res = root * root == m; 
        memo.put(m, res);
        
        return res;
    }
    */
}