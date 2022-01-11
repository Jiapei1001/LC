class Solution {
    
    private int[] dirs = {-1, 1};
    
    public int openLock(String[] deadends, String target) {
        // up or down
        Set<String> dead = new HashSet<>();
        for (String s : deadends) dead.add(s);
        
        if (dead.contains("0000") || dead.contains(target)) {
            return -1;
        }
        
        if (target.equals("0000")) {
            return 0;
        }
        
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        q.offer("0000");
        visited.add("0000");
        
        int level = 1;
        while(!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                
                for (int j = 0; j < 4; j++) {
                    // +1 or -1
                    char currChar = curr.charAt(j);
                    int intChar = currChar - '0';
                    
                    for (int dir : dirs) {
                        
                        char temp = (char)(((intChar + dir + 10) % 10) + '0'); 
                        String next = curr.substring(0, j) + temp + curr.substring(j + 1);
                        
                        if (next.equals(target)) {
                            return level;
                        }
                        
                        if (visited.contains(next)) {
                            continue;
                        }
                        
                        if (dead.contains(next)) {
                            continue;
                        }
                        
                        visited.add(next);
                        q.offer(next);
                    }
                }
            }
            
            level++;
        }
        
        return -1;
    }
}