class Solution {
    
    public int[][] merge(int[][] intervals) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        for (int[] i : intervals) {
            if (!pq.isEmpty() && i[0] <= pq.peek()[1]) {
                int[] last = pq.poll();
                int l = Math.min(last[0], i[0]);
                int r = Math.max(last[1], i[1]);
                pq.offer(new int[]{l, r});
                continue;
            }
            pq.offer(i);
        }
        
        List<int[]> res = new ArrayList<>();
        while(!pq.isEmpty()) res.add(pq.poll());
        Collections.reverse(res);
        
        return res.toArray(new int[res.size()][]);
    }
    
    
    /*
    class Point {
        int s;
        int e;
        
        public Point (int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    
    public int[][] merge(int[][] intervals) {
        
        
        int n = intervals.length;
        Point[] points = new Point[n];
        
        for (int i = 0; i < n; i++) {
            points[i] = new Point(intervals[i][0], intervals[i][1]);
        }
        
        Arrays.sort(points, (a, b) -> a.s - b.s);
        
        Queue<Point> pq = new PriorityQueue<>((a, b) -> b.e - a.e);
        
        for (Point p : points) {
            if (!pq.isEmpty() && p.s <= pq.peek().e) {
                Point prev = pq.poll();
                pq.offer(new Point(prev.s, prev.e >= p.e ? prev.e : p.e));
                // pq.peek().e = Math.max(pq.peek().e, p.e);
            } else {
                pq.offer(p);
            }
        }
        
        int[][] res = new int[pq.size()][2];
        
        int index = 0;
        while(!pq.isEmpty()) {
            Point p = pq.poll();
            res[index][0] = p.s;
            res[index][1] = p.e;
            index++;
        }
        
        return res;
    }
    */
}