class Solution {
    
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        
        // sliding window
        int n = s.length();
        
        char[] sArr = s.toCharArray();
        
        Map<Character, Integer> map2Idx = new HashMap<>();
        
        int l = 0, r = 0;
        int res = 1;
        
        for (; r < n; r++) {
            if (map2Idx.containsKey(sArr[r])) {
                // NOTE：这里l不可以跳回之前的位置，必须是不断往右！！
                l = Math.max(l, map2Idx.get(sArr[r]) + 1);
            }
            
            map2Idx.put(sArr[r], r);
            
            if (l <= r) {
                res = Math.max(res, r - l + 1);
            }
        }
        
        return res;
    }
    
    
    /*
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Map<Character, Integer> map2Index = new HashMap<>();
        
        char[] sArr = s.toCharArray();
        
        int res = 1;
        
        for (int left = 0, right = 0; right < sArr.length; right++) {
            // update left pointer
            if (map2Index.containsKey(sArr[right])) {
                // Note: left must check max!!
                left = Math.max(left, map2Index.get(sArr[right]) + 1);
            }
            
            res = Math.max(res, right - left + 1);            
            map2Index.put(sArr[right], right);            
        }
        
        return res;
    }
    */
}