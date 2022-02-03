class Solution {
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
}