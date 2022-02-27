class Solution {
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // build graph
        // dfs graph and get all the connected emails
        Map<String, String> map2Name = new HashMap<>();
        
        Map<String, Set<String>> graph = new HashMap<>();
        for (List<String> a : accounts) {
            String name = a.get(0);
            
            // build relationship with the first email
            for (int i = 1; i < a.size(); i++) {
                map2Name.put(a.get(i), name);
                
                graph.computeIfAbsent(a.get(1), x -> new HashSet<>()).add(a.get(i));
                graph.computeIfAbsent(a.get(i), x -> new HashSet<>()).add(a.get(1));
            }
        }
        
        // graph's keySet is all the seen emails
        List<List<String>> res = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (String e : graph.keySet()) {
            if (visited.contains(e)) {
                continue;
            }
            
            Stack<String> stack = new Stack<>();
            Set<String> levelSeen = new HashSet<>();
            
            stack.push(e);
            visited.add(e);
            
            while (!stack.isEmpty()) {
                String curr = stack.pop();
                levelSeen.add(curr);
                
                for (String next : graph.getOrDefault(curr, new HashSet<>())) {
                    if (!visited.contains(next)) {
                        stack.push(next);
                        visited.add(next);
                    }
                }
            }
            
            List<String> level = new ArrayList<>(levelSeen);
            Collections.sort(level);
            level.add(0, map2Name.get(e));
            
            res.add(level);
        }
        
        return res;
    }
    
    /*
    class UnionFind{
        int[] parent;
        
        public UnionFind() {
            this.parent = new int[10000];
            for (int i = 0; i < 10000; i++) {
                this.parent[i] = i;
            }
        }
        
        public int find(int i) {
            if (this.parent[i] == i) {
                return i;
            }
            this.parent[i] = find(this.parent[i]);
            
            return this.parent[i];
        }
        
        public void union(int a, int b) {
            int pa_a = find(a);
            int pa_b = find(b);
            if (pa_a != pa_b) {
                if (pa_a < pa_b) {
                    this.parent[pa_b] = pa_a;
                } else {
                    this.parent[pa_a] = pa_b;
                }
            }
        }
    }
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> email2Name = new HashMap<>();
        Map<String, Integer> email2Id = new HashMap<>();
        
        UnionFind uf = new UnionFind();
        int idx = 0;
        
        for (List<String> account : accounts) {
            String name = account.get(0);
            String initialEmail = account.get(1);
            
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                email2Name.putIfAbsent(email, name);
                
                if (!email2Id.containsKey(email)) {
                    email2Id.put(account.get(i), idx);
                    idx++;
                }
                
                uf.union(email2Id.get(email), email2Id.get(initialEmail));
            }
        }
        
        List<List<String>> res = new ArrayList<>();
        Map<Integer, List<String>> parentList = new HashMap<>();
        
        for (String email : email2Name.keySet()) {
            int parentId = uf.find(email2Id.get(email));
            parentList.computeIfAbsent(parentId, k -> new ArrayList<String>()).add(email);
        }
        
        for (List<String> group : parentList.values()) {
            Collections.sort(group);
            group.add(0, email2Name.get(group.get(0)));
            res.add(group);
        }
        
        return res;
    }
    */
    
    
    /* Option 1 - DFS
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> email2Name = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();
        
        
        // construct graph
        for (List<String> account : accounts) {
            String name = "";
            for (String email : account) {
                if (name.equals("")) {
                    name = email;
                    continue;
                }
                
                email2Name.put(email, name);
                
                graph.putIfAbsent(email, new ArrayList<>());
                graph.get(email).add(account.get(1));
                
                graph.putIfAbsent(account.get(1), new ArrayList<>());
                graph.get(account.get(1)).add(email);
            }
        }
        
        // dfs graph
        List<List<String>> res = new ArrayList<>();
        
        Set<String> seen = new HashSet<>();
        for (String start : graph.keySet()) {
            
            if (seen.contains(start))
                continue;
            
            Stack<String> stack = new Stack<>();
            stack.push(start);
            seen.add(start);
            
            List<String> connectedComponent = new ArrayList<>();
            
            while (!stack.isEmpty()) {
                String curr = stack.pop();
                connectedComponent.add(curr);
                
                for (String neighbor : graph.get(curr)) {
                    if (!seen.contains(neighbor)) {
                        seen.add(neighbor);
                        stack.push(neighbor);
                    }
                }
            }
            
            Collections.sort(connectedComponent);
            connectedComponent.add(0, email2Name.get(start));
            
            res.add(connectedComponent);
        }
        
        return res;
    }
    */

    
    /*
    class UnionFind {
        int[] parent;
        
        public UnionFind() {
            parent = new int[10000];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int x) {
            if (parent[x] == x)
                return x;
            
            parent[x] = find(parent[x]);
            
            return parent[x];
        }
        
        
        public void union(int x, int y) {
            parent[find(y)] = find(x);
        }
        
    }
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        
        UnionFind uf = new UnionFind();
        
        Map<String, String> email2Name = new HashMap<>();
        Map<String, Integer> email2Id = new HashMap<>();
        
        int id = 0;
        
        for (List<String> account : accounts) {
            String name = "";
            for (String email : account) {
                if (name.equals("")) {
                    name = email;
                    continue;
                }
                email2Name.put(email, name);
                if (!email2Id.containsKey(email)) {
                    email2Id.put(email, id++);
                }
                // group id together, with same parent
                uf.union(email2Id.get(account.get(1)), email2Id.get(email));
            }
        }
        
        Map<Integer, List<String>> sameParent = new HashMap<>();
        for (String email : email2Id.keySet()) {
            int parentIdx = uf.find(email2Id.get(email));
            sameParent.putIfAbsent(parentIdx, new ArrayList<>());
            sameParent.get(parentIdx).add(email);
        }
        
        List<List<String>> res = new ArrayList<>();
        for (List<String> sameGroup : sameParent.values()) {
            Collections.sort(sameGroup);
            sameGroup.add(0, email2Name.get(sameGroup.get(0)));
            res.add(sameGroup);
        }
        
        return res;
    }
    */
}