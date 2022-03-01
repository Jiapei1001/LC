class Solution {
    class Pair {
        int oriLen;
        String repStr;
        
        public Pair(int oriLen, String repStr) {
            this.oriLen = oriLen;
            this.repStr = repStr;
        }
    }
    
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        // [0, (1, "eee")]
        // [2, (2, "ffff")]
        Map<Integer, Pair> map2Replace = new HashMap<>();
        
        int n = indices.length;
        for (int i = 0; i < n; i++) {
            int qi = indices[i];
            String qs = sources[i];
            
            if (s.substring(qi).startsWith(qs)) {
                map2Replace.put(qi, new Pair(qs.length(), targets[i]));
            }
        }
        
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        
        while (idx < s.length()) {
            if (!map2Replace.containsKey(idx)) {
                sb.append(s.charAt(idx++));
            } else {
                Pair temp = map2Replace.get(idx);
                sb.append(temp.repStr);
                idx += temp.oriLen;
            }
        }
        
        return sb.toString();
    }
}