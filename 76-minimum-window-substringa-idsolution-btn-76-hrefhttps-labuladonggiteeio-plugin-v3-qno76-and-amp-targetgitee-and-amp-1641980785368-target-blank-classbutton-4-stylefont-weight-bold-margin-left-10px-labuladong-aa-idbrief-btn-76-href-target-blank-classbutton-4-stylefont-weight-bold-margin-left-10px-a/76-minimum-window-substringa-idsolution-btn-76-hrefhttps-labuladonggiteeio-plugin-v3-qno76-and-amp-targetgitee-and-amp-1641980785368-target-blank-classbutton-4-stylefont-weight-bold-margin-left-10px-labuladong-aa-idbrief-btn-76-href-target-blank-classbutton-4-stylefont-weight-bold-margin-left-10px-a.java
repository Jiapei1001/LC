class Solution {

    public String minWindow(String s, String t) {
        
        int start = -1;
        int len = Integer.MAX_VALUE;
        
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        
        int require = need.keySet().size();
        
        Map<Character, Integer> curr = new HashMap<>();
        
        int l = 0;
        int valid = 0;
        
        for (int r = 0; r < s.length(); r++) {
            char right = s.charAt(r);
            
            if (need.containsKey(right)) {
                curr.put(right, curr.getOrDefault(right, 0) + 1);
                
                if (curr.get(right).equals(need.get(right))) {
                    valid++;
                }
            }
            
            while (valid == require) {
                
                // update
                if (len > r - l + 1) {
                    len = r - l + 1;
                    start = l;
                }
                
                char left = s.charAt(l);
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
        
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
    
    
    
    
    
    
    /*
    public String minWindow(String s, String t) {
        if (s.length() == 0 || s.length() < t.length())
            return "";
        
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        
        Map<Character, Integer> tMap = new HashMap<>();
        for (char c: tArray) tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        

        int counter = tArray.length, start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE;
        
        while (end < sArray.length) {
            if (tMap.containsKey(sArray[end])) {
                if (tMap.get(sArray[end]) > 0)
                    counter--;
                tMap.put(sArray[end], tMap.get(sArray[end]) - 1);
            }
            while (counter == 0) {
                if (minLen > end - start) {
                    minStart = start;
                    minLen = end - start + 1;
                }

                if (tMap.containsKey(sArray[start])) {
                    tMap.put(sArray[start], tMap.get(sArray[start]) + 1);
                    if (tMap.get(sArray[start]) > 0) {
                        counter++;
                    }
                }

                start++;
            }
            
            end++;
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
    */
}
