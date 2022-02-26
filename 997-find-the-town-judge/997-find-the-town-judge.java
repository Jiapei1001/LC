class Solution {
    
    public int findJudge(int n, int[][] trust) {
        // indegree is (n - 1)
        // outdegree is 0
        
        int[] indegree = new int[n + 1];
        int[] outdegree = new int[n + 1];
        
        for (int[] t : trust) {
            indegree[t[1]]++;
            outdegree[t[0]]++;
        }
        
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == n - 1 && outdegree[i] == 0) {
                return i;
            }
        }
        
        return -1;
    }
    
    
    
    
    // indegree & outdegree
    /*
    public int findJudge(int n, int[][] trust) {
        int[] indegree = new int[n + 1];
        int[] outdegree = new int[n + 1];
        
        
        for (int[] t : trust) {
            indegree[t[1]]++;
            outdegree[t[0]]--;
        }
        
        
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == n - 1 && outdegree[i] == 0) {
                return i;
            }
        }
        
        return -1;
    }
    */
    
    // what I did, not good
    /*
    public int findJudge(int n, int[][] trust) {
        Map<Integer, Set<Integer>> trustMap = new HashMap<>();
        
        for (int[] t : trust) {
            trustMap.computeIfAbsent(t[1], a -> new HashSet<Integer>()).add(t[0]);
        }
        
        // cnt
        int judge = 0, cnt = 0;
        for (int k : trustMap.keySet()) {
            if (trustMap.get(k).size() == n - 1) {
                judge = k;
                cnt++;
            }
        }
        
        if (cnt != 1) return -1;
        
        for (int[] t : trust) {
            if (t[0] == judge) return -1;
        }
        
        return judge;
    }
    */
}