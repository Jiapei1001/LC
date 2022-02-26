class Solution {
    
    char[] ops = {'A', 'C', 'G', 'T'};
    int res;
    
    public int minMutation(String start, String end, String[] bank) {
        if (start == null || start.length() == 0) return -1;
        if (end == null || end.length() == 0) return -1;
        
        Set<String> words = new HashSet<>();
        for (String s : bank) words.add(s);
        
        Set<String> visited = new HashSet<>();
        visited.add(start);
        
        res = Integer.MAX_VALUE;
        dfs(start, end, 0, visited, words);
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    private void dfs(String curr, String end, int step, Set<String> visited, Set<String> words) {
        if (curr.equals(end)) {
            res = Math.min(res, step);
            return;
        }
        
        for (int i = 0; i < curr.length(); i++) {
            // NOTE: here even if curr.charAt(i) != end.charAt(i), it is still possible to form the final result!!
            List<String> options = getValidOptions(curr, end, i, visited, words);

            for (String next : options) {
                visited.add(next);
                dfs(next, end, step + 1, visited, words);
                visited.remove(next);
            }
        }
    }
    
    private List<String> getValidOptions(String curr, String end, int i, Set<String> visited, Set<String> words) {
        List<String> res = new ArrayList<>();
        
        char[] sArr = curr.toCharArray();
        char temp = sArr[i];
        
        for (char c : ops) {
            if (c == sArr[i]) continue;
            
            sArr[i] = c;
            String next = new String(sArr);
            sArr[i] = temp;
            
            if (visited.contains(next) || !words.contains(next)) continue;
            
            res.add(next);
        }
        
        return res;
    }
    
    
    /*
    public int minMutation(String start, String end, String[] bank) {
        if (start == null || start.length() == 0) return -1;
        if (end == null || end.length() == 0) return -1;
        
        Set<String> dict = new HashSet<>();
        for (String s : bank) dict.add(s);
        
        if (!dict.contains(end)) return -1;
        if (start.length() != end.length()) return -1;
        
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        q.offer(start);
        visited.add(start);
        
        char[] options = new char[]{'A', 'C', 'G', 'T'};
        
        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                
                if (curr.equals(end)) {
                    return steps;
                }
                
                char[] currArr = curr.toCharArray();
                
                for (int j = 0; j < currArr.length; j++) {
                    char temp = currArr[j];
                    
                    for (char c : options) {
                        currArr[j] = c;
                        String next = new String(currArr);
                    
                        if (!dict.contains(next)) {
                            continue;
                        }
                        
                        if (!visited.contains(next)) {
                            visited.add(next);
                            q.offer(next);
                        }
                    }
                    currArr[j] = temp;
                }  
            }
            
            steps++;
        }
        
        return -1;
    }
    */
}