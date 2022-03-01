class Solution {
    
    public int numMatchingSubseq(String s, String[] words) {
//         a: a, acd, ace
//         b: bb
            
//         a -> a -> res++
//         c: cd, ce
//         b: bb
        
        Map<Character, LinkedList<String>> map = new HashMap<>();
        for (String w : words) {
            char first = w.charAt(0);
            map.computeIfAbsent(first, a -> new LinkedList<>()).add(w);
        }
        
        int res = 0;
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                continue;
            }
            
            LinkedList<String> vals = new LinkedList<>(map.get(c));
            map.remove(c);
            
            for (String v : vals) {
                if (v.length() == 1) {
                    res++;
                    continue;
                }
                char second = v.charAt(1);
                String next = v.substring(1);
                map.computeIfAbsent(second, a -> new LinkedList<>()).add(next);
            }
        }
        
        return res;
    }
    
    
    
    /*
    public int numMatchingSubseq(String s, String[] words) {
        
        // s = "abcde", words = ["a","bb","acd","ace"]
        
        Map<String, Integer> dict = new HashMap<>();
        
        for (String w : words) {
            dict.put(w, dict.getOrDefault(w, 0) + 1);
        }
        
        Map<String, Integer> cand = new HashMap<>();
        Set<String> visited = new HashSet<>();
        dfs(s, dict, cand, visited);
        
        int res = 0;
        for (int i : cand.values()) res += i;
        
        return res;
    }
    
    private void dfs(String s, 
                     Map<String, Integer> dict, 
                     Map<String, Integer> cand, 
                     Set<String> visited) {
        if (s == null || s.length() == 0) {
            return;
        }
        
        if (dict.containsKey(s) && !visited.contains(s)) {
            cand.put(s, cand.getOrDefault(s, 0) + dict.get(s));
        }
        
        visited.add(s);
        StringBuilder sb = new StringBuilder(s);
        
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            sb.deleteCharAt(i);
            
            dfs(sb.toString(), dict, cand, visited);
            
            sb.insert(i, temp);
        }
    }
    */
}