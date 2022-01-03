class Solution {
    public boolean equationsPossible(String[] equations) {
        
        UnionFind uf = new UnionFind(26);
        
        for (String s : equations) {
            if (s == null || s.length() != 4)
                continue;
            
            char[] sArr = s.toCharArray();
            int l = sArr[0] - 'a';
            int r = sArr[3] - 'a';
            
            if (sArr[1] == '=') {   
                uf.union(l, r);
            }
        }
        
        for (String s : equations) {
            char[] sArr = s.toCharArray();
            int l = sArr[0] - 'a';
            int r = sArr[3] - 'a';
            
            if (sArr[1] == '!') {
                int rootL = uf.find(l);
                int rootR = uf.find(r);
                
                if (rootL == rootR) {
                    return false;
                }
            }
        }
        
        return true;
    }
}

class UnionFind {
    int[] parent;
    Map<Integer, Set<Integer>> ungroupMap;
    
    public UnionFind(int n) {
        parent = new int[n];
        ungroupMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        
        parent[i] = find(parent[i]);
        return parent[i];
    }
    
    public void union(int a, int b) {
        int pA = find(a);
        int pB = find(b);
        
        if (pA != pB) {
            parent[pA] = pB;
        }
    }
}