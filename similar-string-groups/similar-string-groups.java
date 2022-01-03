class Solution {
    public int numSimilarGroups(String[] strs) {
        // arts rats star tars
        
        // check every node with others
        // if there is a match, union the two, decrease uf size
        
        int n = strs.length;
        
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            String s = strs[i];
            // Set<String> similarGroup = getSimilarGroup(s);
            
            for (int j = i + 1; j < n; j++) {
                // if (similarGroup.contains(strs[j])) {
                //     uf.union(i, j);
                // }
                if (isSimilar(strs[i], strs[j])) {
                    uf.union(i, j);
                }
            }
        }
        
        return uf.size;
    }
                                   
    private boolean isSimilar(String a, String b) {
        if (a.equals(b))
            return true;
        
        int n = a.length();
        
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i))
                cnt++;
        }
        
        return cnt == 2;
    }
    
    private Set<String> getSimilarGroup(String s) {
        Set<String> res = new HashSet<>();
        
        if (s == null || s.length() == 0)
            return res;
        
        // add original
        res.add(s);
        
        int n = s.length();
        char[] sArr = s.toCharArray();
        
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                swap(sArr, i, j);
                res.add(new String(sArr));
                swap(sArr, j, i);
            }
        
        return res;
    }
    
    private void swap(char[] sArr, int i, int j) {
        char temp = sArr[i];
        sArr[i] = sArr[j];
        sArr[j] = temp;
    }
}


class UnionFind {
    int size;
    int[] parent;
    
    public UnionFind(int n) {
        size = n;
        parent = new int[n];
        
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
            size--;
        }
    }
}