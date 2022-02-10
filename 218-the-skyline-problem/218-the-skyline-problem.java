class Solution {
    
    public List<List<Integer>> getSkyline(int[][] buildings) {
        
        List<List<Integer>> res = new ArrayList<>();
        
        List<Point> list = new ArrayList<>();
        
        for (int[] b : buildings) {
            // NOTE: for start point insert negative height to be the first when sorted
            list.add(new Point(b[0], -b[2]));
            list.add(new Point(b[1], b[2]));
        }
        
        // NOTE: as enter's x < 0, it makes sure enter's event is processed first
        Collections.sort(list, (a, b) -> a.x != b.x ? a.x - b.x : a.h - b.h);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        
        int n = buildings.length;
        
        int prev = 0;
        // 扫描线，loop through list to get x
        for (Point p : list) {
            if (p.h < 0) {
                pq.offer(Math.abs(p.h));
            } else {
                pq.remove(p.h);
            }
            
            int curr = pq.isEmpty() ? 0 : pq.peek();
            
            if (curr != prev) {
                // 或者 new ArrayList<>(Arrays.asList(p.x, curr)); mutable vs immutable
                List<Integer> temp = new ArrayList<>(List.of(p.x, curr));
                
                res.add(temp);
                prev = curr;
            }
        }
        
        return res;
    }
}

class Point {
    int x;
    int h;
    
    public Point(int x, int h) {
        this.x = x;
        this.h = h;
    }
}