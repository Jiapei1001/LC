class Solution {

    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        
        // f(i, j) := the minimal height for the front i-th books, with width of j
        // current level, looking back, find how many books can be put on the current level
        // f(i, j) = f(k, shelfWidth) + Max Height from book k -> i
            
        
        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            int currBookW = books[i - 1][0];
            int currBookH = books[i - 1][1];

            dp[i] = dp[i - 1] + currBookH;

            // current level, looking back
            int accWidth = currBookW;
            int currMaxHeight = currBookH;
            for (int k = i - 1; k >= 1 && accWidth + books[k - 1][0] <= shelfWidth; k--) {
                currMaxHeight = Math.max(currMaxHeight, books[k - 1][1]);
                accWidth += books[k - 1][0];

                dp[i] = Math.min(dp[i], currMaxHeight + dp[k - 1]);
            }
        }
        
        return dp[n];
    }
    
    // Top Down
    /*
    public int minHeightShelves(int[][] books, int shelfWidth) {
        //Note that at each step of the above process, the order of the books we place is the same order as the given sequence of books.
        // 这一句决定了books的顺序是一个接着一个，不是无序的，因此可以缩小问题的规模，使用DP
        
        int n = books.length;
        
        int[][] memo = new int[n][shelfWidth + 1];
        
        return dfs(books, shelfWidth, 0, 0, 0, memo);
    }
    
    private int dfs(int[][] books, int shelfWidth, int idx, int currW, int currH, int[][] memo) {
        // no book to put
        if (idx == books.length) {
            return currH;
        }
        
        if (idx >= books.length) {
            return Integer.MAX_VALUE;
        }
        
        if (memo[idx][currW] != 0) {
            return memo[idx][currW];
        }
        
        int currBookW = books[idx][0];
        int currBookH = books[idx][1];
        
        // two choices, current level or next level
        // current level
        int cand1 = currW + currBookW > shelfWidth ? 
            Integer.MAX_VALUE : 
            dfs(books, shelfWidth, idx + 1, currW + currBookW, Math.max(currH, currBookH), memo);
        
        // next level
        int cand2 = currH + dfs(books, shelfWidth, idx + 1, currBookW, currBookH, memo);
        
        memo[idx][currW] = Math.min(cand1, cand2);
        
        return memo[idx][currW];
    }
    */
}