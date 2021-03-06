class Solution {
    
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        
        for (List<String> p : knowledge) {
            map.put(p.get(0), p.get(1));
        }
        
        return process(s, map);
    }
    
    private String process(String s, Map<String, String> map) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        String res = "";
        
        char[] sArr = s.toCharArray();
        
        int n = s.length();
        int i = 0, j = 0;
        for (; i < n; i++) {
            // (
            if (sArr[i] == '(') {
                j = i + 1;
                // )
                while (j < n && sArr[j] != ')') {
                    j++;
                }
                String key = s.substring(i + 1, j);
                
                res += map.containsKey(key) ? map.get(key) : "?";
                i = j;
                
            } else {
                res += sArr[i];
                // NOTE:这里一定也要更新j！！，否则后面的下一步Recursion会出问题！！
                // j = i;
            }
        }
        
        // res += process(s.substring(j + 1), map, memo);
        
        return res;
    }
}