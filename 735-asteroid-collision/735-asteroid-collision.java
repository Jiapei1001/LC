class Solution {
    
    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        
        LinkedList<Integer> list = new LinkedList<>();
        
        for (int i : asteroids) {
            if (i > 0) {
                list.add(i);
            } else {
                while (!list.isEmpty() && list.getLast() > 0 && list.getLast() < -i) {
                    list.removeLast();
                }
                
                if (!list.isEmpty() && list.getLast() > 0 && list.getLast() > -i) {
                    continue;
                }
                
                if (!list.isEmpty() && list.getLast() > 0 && list.getLast() == -i) {
                    list.removeLast();
                    continue;
                }
                
                list.add(i);
            }
        }
        
        return list.stream().mapToInt(a -> a).toArray();
    }
    
    
    /*
    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        
        if (n == 0) return new int[]{};
        
        // boolean[] mask = new boolean[n];
        
        // [5, 10, -5, 6, -30]
        
        for (int i = 0; i < n - 1; i++) {
            // start
            if (asteroids[i] > 0 && asteroids[i + 1] < 0) {
                int l = i, r = i + 1;
                int lMax = Integer.MIN_VALUE;
                int rMax = Integer.MAX_VALUE;
                
                int lIdx = l, rIdx = r;
                
                while (l >= 0 && asteroids[l] > 0) {
                    if (asteroids[l] > lMax) {
                        lMax = asteroids[l];
                        lIdx = l;
                    }
                    l--;
                }
                while (r < n && asteroids[r] < 0) {
                    if (asteroids[r] < rMax) {
                        rMax = asteroids[r];
                        rIdx = r;
                    }
                    r++;
                }
                
                // in between are down
                if (lMax == -rMax) {
                    l = lIdx;
                    r = rIdx + 1;
                } else if (lMax > -rMax) {
                    l = lIdx + 1;
                    r = rIdx + 1;
                } else if (lMax < -rMax) {
                    l = lIdx;
                    r = rIdx;
                }
                
                int[] left = l >= 0 ? Arrays.copyOfRange(asteroids, 0, l) : new int[]{};
                int[] right = r < n ? Arrays.copyOfRange(asteroids, r, n) : new int[]{};
                int[] res = new int[left.length + right.length];
                
                int idx = 0;
                for (int x : left) res[idx++] = x;
                for (int x : right) res[idx++] = x;

                return asteroidCollision(res);
            }
        }
        
        return asteroids;
    }
    */
}