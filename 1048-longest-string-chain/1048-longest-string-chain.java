class Solution {
    public int longestStrChain(String[] words) {
        int n = words.length;
        
        // NOTE: must sort the input words!!
        Arrays.sort(words, (a, b) -> {
            if (a.length() == b.length()) {
                return a.compareTo(b);
            } else {
                return a.length() - b.length();
            }
        });
        
        // for (String w : words) System.out.println(w);
        
        // f(i) = f(j) + 1, where words[j] is predecessor of words[i]
            
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        for (int i = 0; i < n; i++)
            for (int j = i - 1; j >= 0; j--) {
                if (isPredessor(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        
        return res;
    }
    
    private boolean isPredessor(String prev, String curr) {
        if (curr.length() == prev.length() || Math.abs(curr.length() - prev.length()) >= 2) 
            return false;
        
        for (int i = 0; i < curr.length(); i++) {
            String cand = curr.substring(0, i) + curr.substring(i + 1);
            if (cand.equals(prev)) {
                return true;
            }
        }
        
        return false;
    }
}