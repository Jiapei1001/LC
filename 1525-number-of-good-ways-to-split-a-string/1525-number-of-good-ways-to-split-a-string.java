class Solution {
    public int numSplits(String s) {
        // how many distinct letters in between i and j
        // [1, n - 2]
        
        int n = s.length();
        Set<Character> left = new HashSet<>();
        Map<Character, Integer> right = new HashMap<>();
        
        for (char c : s.toCharArray()) {
            right.put(c, right.getOrDefault(c, 0) + 1);
        }
        
        int res = 0;
        for (int cut = 1; cut <= n - 1; cut++) {
            
            /*
            String l = s.substring(0, cut);
            String r = s.substring(cut);
            
            if (unique(l) == unique(r)) {
                res++;
            }
            */
            
            char c = s.charAt(cut - 1);
            left.add(c);
            right.put(c, right.get(c) - 1);
            right.remove(c, 0);
            
            if (left.size() == right.keySet().size()) {
                res++;
            }
        }
        
        return res;
    }
    
    private int unique(String s) {
        int[] b = new int[26];
        for (char c : s.toCharArray()) {
            b[c - 'a']++;
        }
        int res = 0;
        for (int i : b) {
            if (i > 0) res++;
        }
        return res;
    }
}