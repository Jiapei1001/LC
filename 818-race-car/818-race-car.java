class Solution {
    
    public int racecar(int target) {
        if (target == 0) {
            return 0;
        }
        
        // int[] {position, spend}
        
        Queue<int[]> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        int[] origin = new int[]{0, 1};
        q.offer(origin);
        visited.add(0 + "/" + 1);
        
        int level = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                
                if (curr[0] == target) {
                    return level;
                }
                
                String s1 = (curr[0] + curr[1]) + "/" + curr[1] * 2;
                String s2 = curr[0] + "/" + (curr[1] > 0 ? -1 : 1);
                
                if (Math.abs(curr[0] + curr[1] - target) < target && !visited.contains(s1)) {
                    q.offer(new int[]{curr[0] + curr[1], curr[1] * 2});
                    visited.add(s1);
                }
                
                if (Math.abs(curr[0] - target) < target && !visited.contains(s2)) {
                    q.offer(curr[1] > 0 ? new int[]{curr[0], -1} : new int[]{curr[0], 1});
                    visited.add(s2);
                }
            }
            
            level++;
        }
        
        return -1;
    }
}