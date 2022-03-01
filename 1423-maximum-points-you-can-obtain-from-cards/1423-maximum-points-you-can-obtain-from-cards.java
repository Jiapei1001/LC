class Solution {
    
    public int maxScore(int[] cardPoints, int k) {
        // begining k -> 0
        // ending 0 -> k
        int n = cardPoints.length;
        
        int[] frontSum = new int[k + 1];
        int[] endSum = new int[k + 1];
        
        for (int i = 1; i <= k; i++) {
            frontSum[i] = frontSum[i - 1] + cardPoints[i - 1];
        }
        
        for (int i = n - 1; i >= n - k; i--) {
            endSum[n - i] = endSum[n - i - 1] + cardPoints[i];
        }
        
        int res = 0;
        for (int i = k; i >= 0; i--) {
            int temp = frontSum[i] + endSum[k - i];
            res = Math.max(res, temp);
        }
        
        return res;
    }
    
    
    // 自己做的错了
    /*
    public int maxScore(int[] cardPoints, int k) {
        // f(i, j) = nums[i] + f(i + 1, j)
                // = nums[j] + f(i, j - 1)
        int n = cardPoints.length;
        
        Map<String, Integer> memo = new HashMap<>();
        
        return dfs(cardPoints, 0, n - 1, k, memo);
    }
    
    private int dfs(int[] cardPoints, int l, int r, int k, Map<String, Integer> memo) {
        if (k <= 0) return 0;
        if (l == r) return cardPoints[l];
        
        String s = l + "_" + r;
        
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        
        int op1 = cardPoints[l] + dfs(cardPoints, l + 1, r, k - 1, memo);
        int op2 = cardPoints[r] + dfs(cardPoints, l, r - 1, k - 1, memo);
        
        int res = Math.max(op1, op2);
        memo.put(s, res);
        
        return res;
    }
    */
    
    
    /*
    public int maxScore(int[] cardPoints, int k) {
        f(i, j) = nums[i] + f(i + 1, j)
                = nums[j] + f(i, j - 1)
        
            
            
        // 1,2,3,4,5,6,1
        // 1,2,3 = 6
        // 1,6,5 = 12
        int n = cardPoints.length;
        
        if (n <= k) {
            int res = 0;
            for (int i : cardPoints) res += i;
            return res;
        }
        
        // there are only 2 options
        int sum1 = cardPoints[0];
        sum1 += getSum(cardPoints, 1, n - 1, k - 1);

        int sum2 = cardPoints[n - 1];
        sum2 += getSum(cardPoints, 0, n - 2, k - 1);
        
        return Math.max(sum1, sum2);
    }
    
    private int getSum(int[] cardPoints, int l, int r, int rest) {
        int res = 0;
        while (l <= r && rest > 0) {
            if (cardPoints[l] > cardPoints[r]) {
                res += cardPoints[l];
                l++;
            } else {
                res += cardPoints[r];
                r--;
            }
            rest--;
        }
        return res;
    }
    */
}