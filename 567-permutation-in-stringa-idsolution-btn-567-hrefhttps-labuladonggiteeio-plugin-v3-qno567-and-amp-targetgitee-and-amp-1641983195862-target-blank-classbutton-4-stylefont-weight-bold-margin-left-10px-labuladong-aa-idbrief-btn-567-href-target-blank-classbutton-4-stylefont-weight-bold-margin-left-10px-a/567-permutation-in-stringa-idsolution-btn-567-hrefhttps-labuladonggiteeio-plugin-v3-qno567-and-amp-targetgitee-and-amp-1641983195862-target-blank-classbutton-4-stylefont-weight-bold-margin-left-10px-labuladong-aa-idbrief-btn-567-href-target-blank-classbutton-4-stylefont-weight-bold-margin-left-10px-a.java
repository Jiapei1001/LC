class Solution {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        for (char c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        
        Map<Character, Integer> curr = new HashMap<>();
        
        int l = 0;
        int valid = 0;
        for (int r = 0; r < s2.length(); r++) {
            char right = s2.charAt(r);
            
            if (need.containsKey(right)) {
                curr.put(right, curr.getOrDefault(right, 0) + 1);
                
                if (curr.get(right).equals(need.get(right))) {
                    valid++;
                }
            }
            
            while (valid == need.size()) {
                
                if (r - l + 1 == s1.length()) {
                    return true;
                }
                
                char left = s2.charAt(l);
                l++;
                
                if (need.containsKey(left)) {
                    if (curr.get(left).equals(need.get(left))) {
                        valid--;
                    }
                    
                    curr.put(left, curr.get(left) - 1);
                    
                    if (curr.get(left) == 0) {
                        curr.remove(left);
                    }
                }
            }
        }
        
        return false;
    }
}