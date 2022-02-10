class Solution {

    public int longestSubstring(String s, int k) {
        if (s == null || s.length() < k) return 0;
        
        int n = s.length();
        char[] sArr = s.toCharArray();
        
        int[] chars = new int[26];
        for (char c : sArr) chars[c - 'a']++;
        int uniqueCnt = 0;
        for (int i : chars) if (i > 0) uniqueCnt++;
        
        
        int res = 0;
        for (int currCnt = 1; currCnt <= uniqueCnt; currCnt++) {
            
            Map<Character, Integer> curr = new HashMap<>();
            int l = 0, r = 0;
            int valid = 0;
            for (; r < n; r++) {
                // here as <= , to make the right boundary as far as possible
                char rc = sArr[r];
                curr.put(rc, curr.getOrDefault(rc, 0) + 1);

                if (curr.get(rc).equals(k)) {
                    valid++;
                }

                // shrink
                while (l < r && curr.size() > currCnt) {
                    char lc = sArr[l];

                    if (curr.get(lc) == k) {
                        valid--;
                    }
                    
                    curr.put(lc, curr.get(lc) - 1);
                    curr.remove(lc, 0);
                    l++;
                }
                
                // meet currCnt level requirement
                // all the chars in the map are valid
                if (valid == currCnt && valid == curr.size()) {
                    res = Math.max(res, r - l + 1);
                }
            }
        }
        
        return res;
    }
    
    
    
    
    
    
    /*
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0)
            return 0;
        
        int n = s.length();
        char[] sArr = s.toCharArray();
        
        int uniqueMax = getUniqueChars(sArr);
        
        int[] countMap = new int[26];
        int res = 0;
        for (int currUnique = 1; currUnique <= uniqueMax; currUnique++) {
            
            int i = 0, j = 0, unique = 0, kOrMore = 0;
            
            Arrays.fill(countMap, 0);
            
            while (j < n) {
                if (unique <= currUnique) {
                    int idx = sArr[j] - 'a';
                    if (countMap[idx] == 0) {
                        unique++;
                    }
                    countMap[idx]++;
                    if (countMap[idx] == k) {
                        kOrMore++;
                    }
                    j++;
                } else {
                    int idx = sArr[i] - 'a';
                    if (countMap[idx] == k) {
                        kOrMore--;
                    }
                    countMap[idx]--;
                    if (countMap[idx] == 0) {
                        unique--;
                    }
                    i++;
                }
                
                if (unique == currUnique && unique == kOrMore) {
                    // 因为前面 j 已经 ++ ，因此这里不需要是 j - i + 1
                    res = Math.max(res, j - i);
                }
            }
        }
        
        return res;
    }
    
    private int getUniqueChars(char[] sArr) {
        int uniqueChars = 0;
        boolean[] map = new boolean[26];
        
        for (char c : sArr) {
            if (!map[c - 'a']) {
                uniqueChars++;
                map[c - 'a'] = true;
            }
        }
        
        return uniqueChars;
    }
    */
}