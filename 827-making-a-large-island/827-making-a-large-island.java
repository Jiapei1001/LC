class UnionFind {
    int[] parent;
    int[] size;
    
    public UnionFind(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        parent = new int[n * m];
        size = new int[n * m];
        
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 1) {
                    parent[r * m + c] = r * m + c;
                    size[r * m + c] = 1;
                }
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
        int pa = find(a);
        int pb = find(b);
        
        if (pa == pb) {
            return;
        }
        
        if (size[pa] < size[pb]) {
            parent[pa] = pb;
            size[pb] += size[pa];
        } else {
            parent[pb] = pa;
            size[pa] += size[pb];
        }
    }
}


class Solution {
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int largestIsland(int[][] grid) {
        UnionFind uf = new UnionFind(grid);
        
        int n = grid.length;
        int m = grid[0].length;
        
        
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 1) {
                    int curr = r * m + c;
                    
                    for (int[] dir : dirs) {
                        int nr = r + dir[0];
                        int nc = c + dir[1];
                        
                        if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                            continue;
                        }
                        
                        if (grid[nr][nc] == 0) {
                            continue;
                        }
                        
                        int next = nr * m + nc;
                        
                        uf.union(curr, next);
                    }
                }
            }
        
        int res = 0;
        
        for (int r = 0; r < n; r++)
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 0) {
                    
                    Set<Integer> neighborParents = new HashSet<>();
                    for (int[] dir : dirs) {
                        int nr = r + dir[0];
                        int nc = c + dir[1];
                        
                        if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                            continue;
                        }
                        
                        if (grid[nr][nc] == 0) {
                            continue;
                        }
                        
                        int root = uf.find(nr * m + nc);
                        neighborParents.add(root);
                    }
                    
                    int temp = 1;
                    for (int pa : neighborParents) {
                        temp += uf.size[pa];
                    }
                    
                    res = Math.max(res, temp);
                }
            }
        
        return res == 0 ? n * m : res;
    }
}