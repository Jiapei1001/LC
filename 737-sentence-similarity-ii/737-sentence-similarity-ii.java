class Solution {
    
    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) return false;
        
        int n = sentence1.length;
        
        UnionFind uf = new UnionFind();
        int id = 1;
        Map<String, Integer> map2Id = new HashMap<>();
        for (List<String> p : similarPairs) {
            for (String s : p) {
                if (!map2Id.containsKey(s)) {
                    map2Id.put(s, id);
                    id++;
                }
            }
            uf.union(map2Id.get(p.get(0)), map2Id.get(p.get(1)));
        }
        
        for (int i = 0; i < n; i++) {
            if (sentence1[i].equals(sentence2[i])) continue;
            
            int ra = uf.find(map2Id.getOrDefault(sentence1[i], 0));
            int rb = uf.find(map2Id.getOrDefault(sentence2[i], 9999));
            
            if (ra != rb) return false;
        }
        
        return true;
    }
    
    
    /*
    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        
        if (sentence1.length != sentence2.length) {
            return false;
        }
        
        UnionFind uf = new UnionFind();
        
        // construct group
        Map<String, Integer> map2Id = new HashMap<>();
        int idx = 0;
        for (List<String> pair : similarPairs) {
            for (String s : pair) {
                if (!map2Id.containsKey(s)) {
                    map2Id.put(s, idx);
                    idx++;
                }
            }
            uf.union(map2Id.get(pair.get(0)), map2Id.get(pair.get(1)));
        }
        
        int n = sentence1.length;
        for (int i = 0; i < n; i++) {
            if (sentence1[i].equals(sentence2[i])) {
                continue;
            }
            
            if (!map2Id.containsKey(sentence1[i]) || !map2Id.containsKey(sentence2[i])) {
                return false;
            }
            
            int rootA = uf.find(map2Id.get(sentence1[i]));
            int rootB = uf.find(map2Id.get(sentence2[i]));
            
            if (rootA != rootB) {
                return false;
            }
        }
        
        return true;
    }
    */
}


class UnionFind {
    int[] parent;
    
    public UnionFind() {
        parent = new int[10000];
        
        for (int i = 0; i < 10000; i++) {
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
        
        if (pA >= pB) {
            parent[pB] = pA;
        } else {
            parent[pA] = pB;
        }
    }

}