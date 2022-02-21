class Solution {
    
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        
        int n = s.length();
        int m = p.length();
        
        if (s == null || s.length() == 0 || n < m) {
            return res;
        }
        
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        
        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        
        int valid = 0;
        int i = 0, j = 0;
        
        while (j < n) {
            char c = s.charAt(j);
            j++;
            
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            
            while (j - i >= m) {
                if (valid == need.size()) {
                    res.add(i);
                }
                
                char l = s.charAt(i);
                i++;
                
                if (need.containsKey(l)) {                    
                    if (window.get(l).equals(need.get(l))) {
                        valid--;
                    }
                    window.put(l, window.get(l) - 1);
                    // if (window.get(l) == 0) {
                    //     window.remove(l);
                    // }
                    window.remove(l, 0);
                }
            }
        }
        
        return res;
    }
    
    
    /*
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        
        int n = s.length();
        int m = p.length();
        
        if (s == null || s.length() == 0 || n < m) {
            return res;
        }
        
        char[] sArr = s.toCharArray();
        int[] target = getAnagram(p);
        
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        while (j <= n) {
            while (j< m) {
                sb.append(sArr[j]);
                j++;
            }
            int[] temp = getAnagram(sb.toString());
            if (isEqual(temp, target)) {
                res.add(i);
            }
            sb.deleteCharAt(0);
            if (j < n) sb.append(sArr[j]);
            i++;
            j++;
        }
        
        return res;
    }
    
    private int[] getAnagram(String s) {
        int[] sArr = new int[26];
        
        for (char c : s.toCharArray()) {
            sArr[c - 'a']++;
        }
        
        return sArr;
    }
    
    private boolean isEqual(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) return false;
        }
        
        return true;
    }
    */
}