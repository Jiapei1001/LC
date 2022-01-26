class Solution {
    
    public boolean wordPatternMatch(String pattern, String s) {    
        Map<Character, String> map = new HashMap<>();
        Set<String> visited = new HashSet<>();
        
        return dfs(pattern, s, 0, 0, map, visited);
    }
    
    private boolean dfs(String p, String s, 
                        int pi, int si, 
                        Map<Character, String> map, Set<String> visited) {
        if (pi == p.length() && si == s.length()) {
            return true;
        }
        
        if (pi >= p.length() || si >= s.length()) {
            return false;
        }
        
        char currP = p.charAt(pi);
        
        if (map.containsKey(currP)) {
            // NOTE: must be susbtring
            if (s.substring(si).startsWith(map.get(currP))) {
                return dfs(p, s, pi + 1, si + map.get(currP).length(), map, visited);
            } else {
                return false;
            }
        }
        
        for (int j = si + 1; j <= s.length(); j++) {
            String temp = s.substring(si, j);
            
            // NOTE: visited avoid same string that had been mapped before
            if (visited.contains(temp)) {
                continue;
            }
            
            map.put(currP, temp);
            visited.add(temp);

            if (dfs(p, s, pi + 1, si + temp.length(), map, visited)) {
                return true;
            }

            map.remove(currP);
            visited.remove(temp);
        }

        return false;
    }
    
    
    /*
    public boolean wordPatternMatch(String pattern, String s) {
        
        Map<Character, String> map = new HashMap<>();
        return dfs(pattern, s, 0, 0, map);
    }
    
    private boolean dfs(String p, String s, int pi, int si, Map<Character, String> map) {
        if (pi == p.length() && si == s.length()) {
            return true;
        }
        
        if (pi >= p.length() || si >= s.length()) {
            return false;
        }
        
        char currP = p.chatAt(pi);
        
        if (map.containsKey(currP)) {
            if (s.startsWith(map.get(currP))) {
                return dfs(p, s, pi + 1, si + map.get(currP).length(), map);
            } else {
                return false;
            }
        }
        
        for (int j = si + 1; j <= s.length(); j++) {
            String temp = s.substring(si, j);
            
            map.put(currP, temp);
            
            if (dfs(p, s, pi + 1, si + temp.length(), map)) {
                return true;
            }
            
            map.remove(currP);
        }
        
        return false;
    }
    */
}