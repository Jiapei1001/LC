class Solution {
    
    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        
        if (m < 1 || n < 1) return "";
        if (s.equals(t)) return s;
        
        Map<Character, Integer> curr = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        
        char[] sArr = s.toCharArray();
        
        int l = 0, r = 0;
        int valid = 0;
        int res = s.length() + 1;
        int resL = 0, resR = 0;
        
        while (r < n) {
            while (r < n && valid < need.size()) {
                char rc = sArr[r];
                if (need.containsKey(rc)) {
                    curr.put(rc, curr.getOrDefault(rc, 0) + 1);
                    if (curr.get(rc).equals(need.get(rc))) {
                        valid++;
                    }
                }
                r++;
            }
            // r reaches the valid index to have the substring matches t
            
            // shrink left
            while (l <= r && valid == need.size()) {
                // inbound, thus + 1
                if (r - l < res) {
                    res = r - l;
                    resL = l;
                    // here resR has already r++ above, thus it already represents the right outbound boundary
                    resR = r;
                }
                
                char lc = sArr[l];
                if (need.containsKey(lc)) {
                    if (curr.get(lc).equals(need.get(lc))) {
                        valid--;
                    }
                    curr.put(lc, curr.get(lc) - 1);
                    // remove key if its value is 0
                    if (curr.get(lc) == 0) curr.remove(lc);
        
                }
                l++;
            }
            // no need to update r, as already updated r above
        }
        
        return res == Integer.MAX_VALUE ? "" : s.substring(resL, resR);
    }
    
    

    
    
    
    /*
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
                
                // PS：使用 Java 的读者要尤其警惕语言特性的陷阱。Java 的 Integer，String 等类型判定相等应该用 equals 方法而不能直接用等号 ==，这是 Java包装类的一个隐晦细节。所以在左移窗口更新数据的时候，不能直接改写为 window.get(d) == need.get(d)，而要用 window.get(d).equals(need.get(d))，之后的题目代码同理。
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
    */
    
    
    
    
    
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
