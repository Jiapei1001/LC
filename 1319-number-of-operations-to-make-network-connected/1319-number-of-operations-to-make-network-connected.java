class Solution {
    
    class UnionFind {
        int[] parent;
        int size;
        
        public UnionFind(int n) {
            this.parent = new int[n];
            this.size = n;
            
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
            
            if (pa == pb) return;
            else {
                this.parent[pa] = pb;
                this.size--;
            }
        }
    }
    
    public int makeConnected(int n, int[][] connections) {
        // if n > total # of connections + 1, then it is -1
        // find how many group
        // group - 1, is the number of times needed
        
        if (n > connections.length + 1) return -1;
        
        UnionFind uf = new UnionFind(n);
        
        // union find how many groups are there
        for (int[] e : connections) {
            uf.union(e[0], e[1]);
        }
        
        // the goal is to have only one group, thus uf.size - 1 is the total times needed
        return uf.size - 1;
    }
    
    
    /*
    class UnionFind {
        int[] parent;
        int[] nodeSize;
        
        public UnionFind(int n) {
            this.parent = new int[n];
            this.nodeSize = new int[n];
            
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.nodeSize[i] = 1;
            }
        }
        
        public int find(int i) {
            if (this.parent[i] == i) {
                return i;
            }
            
            this.parent[i] = this.find(this.parent[i]);
            return this.parent[i];
        }
        
        public boolean union(int a, int b) {
            int aP = this.find(a);
            int bP = this.find(b);
            
            if (aP != bP) {
                this.parent[aP] = bP;
                this.nodeSize[bP] += this.nodeSize[aP];
                return true;
            } else {
                return false;
            }
        }
    }
    
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;
        
        UnionFind uf = new UnionFind(n);
        int components = n;
        for (int[] e : connections) {
            if (uf.union(e[0], e[1])) {
                components--;    
            }
        }
        
        return components - 1;
    }
    */

    
    /* 自己做的，code不够clean
    class UnionFind {
        int[] parent;
        int[] nodeSize;
        
        public UnionFind(int n) {
            this.parent = new int[n];
            this.nodeSize = new int[n];
            
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.nodeSize[i] = 1;
            }
        }
        
        public int find(int i) {
            if (this.parent[i] == i) {
                return i;
            }
            
            this.parent[i] = this.find(this.parent[i]);
            return this.parent[i];
        }
        
        public void union(int a, int b) {
            int aP = this.find(a);
            int bP = this.find(b);
            
            if (aP != bP) {
                this.parent[aP] = bP;
                this.nodeSize[bP] += this.nodeSize[aP];
            }
        }
    }
    
    public int makeConnected(int n, int[][] connections) {
        // undirected graph
        // number of isolated computers <= redundant edges
        
        UnionFind uf = new UnionFind(n);
        for (int[] e : connections) {
            uf.union(e[0], e[1]);
        }
        
        Set<Integer> roots = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            roots.add(root);
        }
        
        int neededEdges = roots.size() - 1;
        
        int[] edges = new int[n];
        for (int[] e : connections) {
            int root = uf.find(e[0]);
            edges[root]++;
        }
        
        // get the number of computers in one connected group
        int redundantEdges = 0;
        for (int root : roots) {
            int temp = edges[root] - (uf.nodeSize[root] - 1);
            redundantEdges += temp > 0 ? temp : 0;
        }
        
        return redundantEdges >= neededEdges ? neededEdges : -1;
    }
    */
}