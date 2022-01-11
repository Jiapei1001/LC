class UnionFind {
    int[] parent;
    int count = 0;
    
    public UnionFind(char[][] grid) {
        int n = grid.length, m = grid[0].length;
        
        this.parent = new int[n * m];
        
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    this.parent[r * m + c] = r * m + c;
                }
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
        int pa = find(a);
        int pb = find(b);
        
        if (pa == pb) return;
        
        if (pa < pb) {
            this.parent[pb] = pa;
        } else {
            this.parent[pa] = pb;
        }
        count--;
    }
    
    public int getCount() {
        return count;
    }
}


class Solution {
    
    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length;
        
        UnionFind uf = new UnionFind(grid);
        
        // r * m + c
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == '1') {
                    int root = uf.find(r * m + c);
                    
                    if ((r - 1) > 0 && grid[r - 1][c] == '1') {
                        int next = (r - 1) * m + c;
                        uf.union(root, next);
                    }
                    
                    if ((r + 1) < n && grid[r + 1][c] == '1') {
                        int next = (r + 1) * m + c;
                        uf.union(root, next);
                    }
                    
                    if ((c - 1) > 0 && grid[r][c - 1] == '1') {
                        int next = r * m + (c - 1);
                        uf.union(root, next);
                    }
                    
                    if ((c + 1) < m && grid[r][c + 1] == '1') {
                        int next = r * m + (c + 1);
                        uf.union(root, next);
                    }
                } 
            }
        
        return uf.getCount();
    }

    
    
    
    /*
    private void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;
        
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        this.dfs(grid, r - 1, c);
        this.dfs(grid, r + 1, c);
        this.dfs(grid, r, c - 1);
        this.dfs(grid, r, c + 1);
    }
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == '1') {
                    num_islands++;
                    this.dfs(grid, r, c);
                }
            }
        }
        
        return num_islands;
    }
    */
}
