class Solution {
    
    class UnionFind {
        int[] parent;
        
        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
            }
        }
        
        public int find(int i) {
            if (i == this.parent[i]) {
                return i;
            }
            this.parent[i] = find(this.parent[i]);
            return this.parent[i];
        }
        
        public void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            
            this.parent[pa] = pb;
        }
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        
        UnionFind uf = new UnionFind(n);
        
        // swap with options
        for (List<Integer> p : pairs) {
            uf.union(p.get(0), p.get(1));
        }
        
        Map<Integer, PriorityQueue<Character>> map2Options = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            // offer i (represents that char) to the root's options
            map2Options.computeIfAbsent(root, a -> new PriorityQueue<>()).offer(s.charAt(i));
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            sb.append(map2Options.get(root).poll());
        }
        
        return sb.toString();
    }
    
    
    /*
    String res = "zzzzzzzzzzzzzzz";
    
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        
        Set<String> visited = new HashSet<>();
        
        visited.add(s);
        dfs(s, 0, visited, pairs);
        
        return res;
    }
    
    private void dfs(String s, int pos, Set<String> visited, List<List<Integer>> pairs) {
        if (pos == s.length()) {
            if (res.compareTo(s) > 0) {
                res = s;
            }
            
            return;
        }
        
        // don't
        dfs(s, pos + 1, visited, pairs);
        
        // take or don't take
        for (int i = 0; i < pairs.size(); i++) {
            int l = pairs.get(i).get(0);
            int r = pairs.get(i).get(1);
            
            String cand = s.substring(0, l) + s.charAt(r) + s.substring(l + 1, r) + s.charAt(l) + s.substring(r + 1);
            
            if (!visited.contains(cand)) {
                visited.add(cand);
                dfs(s.substring(0, l) + s.charAt(r) + s.substring(l + 1, r) + s.charAt(l) + s.substring(r + 1), 
                    i + 1, 
                    visited, 
                    pairs);
            }
        }
    }
    */
}