class Solution {
    
    // Top Down + Memoization
    public int longestStrChain(String[] words) {
        
        Set<String> dict = new HashSet<>();
        for (String word : words) dict.add(word);
        
        Map<String, Integer> memo = new HashMap<>();
        
        int res = 0;
        for (String word : words) {
            res = Math.max(res, dfs(word, dict, memo));
        }
        
        return res;
    }
    
    private int dfs(String word, Set<String> dict, Map<String, Integer> memo) {
        if (memo.containsKey(word)) {
            return memo.get(word);
        }
        
        // NOTE: here res as 1, this word is inside Recursion, thus it is a valid word inside dict
        // if it is not a valid word, it cannot be inside the Recursion
        int res = 1;
        StringBuilder sb = new StringBuilder(word);
        
        for (int i = 0; i < word.length(); i++) {
            sb.deleteCharAt(i);
            
            String next = sb.toString();
            if (dict.contains(next)) {
                res = Math.max(res, 1 + dfs(next, dict, memo));
            }
            
            // backtracking, add back
            sb.insert(i, word.charAt(i));
        }
        
        memo.put(word, res);
        
        return res;
    }
    
    
    // Bottom Up
    /*
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
        

        // for (int i = 0; i < curr.length(); i++) {
        //     String cand = curr.substring(0, i) + curr.substring(i + 1);
        //     if (cand.equals(prev)) {
        //         return true;
        //     }
        // }

        
        StringBuilder sb = new StringBuilder(curr);
        for (int i = 0; i < sb.length(); i++) {
            sb.deleteCharAt(i);
            if (sb.toString().equals(prev)) {
                return true;
            }
            sb.insert(i, curr.charAt(i));
        }
        
        return false;
    }
    */
}