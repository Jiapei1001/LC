class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        
        Set<String> dict = new HashSet<>(wordDict);
        
        boolean[] dp = new boolean[n + 1];
        // base case
        dp[0] = true;
        
        for (int i = 1; i <= n; i++) {
            for (int j = i; j >= 1; j--) {
                String cand = s.substring(j - 1, i - 1 + 1);
                
                if (wordDict.contains(cand) && dp[j - 1]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
}