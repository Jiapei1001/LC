class Solution {
    
    // 扫描线
    public int minMeetingRooms(int[][] intervals) {
        
        int n = intervals.length;
        
        int[] start = new int[n];
        int[] end = new int[n];
        
        int i = 0, j = 0;
        
        for (int[] interval : intervals) {
            start[i++] = interval[0];
            end[j++] = interval[1];
        }
        
        Arrays.sort(start);
        Arrays.sort(end);
        
        int count = 0;
        
        int res = 0;
        
        i = 0;
        j = 0;
        while (i < n && j < n) {
            if (start[i] < end[j]) {
                count++;
                i++;
            } else {
                count--;
                j++;
            }
            
            res = Math.max(res, count);
        }
        
        return res;
    }
    
    
    /*   
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        
        int[] starts = new int[n];
        int[] ends = new int[n];
        
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        
        Arrays.sort(starts);
        Arrays.sort(ends);
        
        // 0, 5, 15
        // 10, 20, 30
        
        int sp = 0, ep = 0;
        int cnt = 0;
        
        while (sp < n && ep < n) {
            if (starts[sp] < ends[ep]) {
                sp++;
                cnt++;
            } else {
                sp++;
                ep++;
            }
        }
        
        return cnt;
    }
    */


    
    
    /*
    class Point {
        int start;
        int end;
        
        public Point(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        
        Queue<Point> pq = new PriorityQueue<>((a, b) -> a.end - b.end);
        
        List<Point> list = new ArrayList<>();
        for (int[] interval : intervals) {
            list.add(new Point(interval[0], interval[1]));
        }
        
        Collections.sort(list, (a, b) -> a.start - b.start);
        
        for (Point p : list) {
            if (!pq.isEmpty() && p.start >= pq.peek().end) {
                pq.poll();
            }
            pq.offer(p);
        }
        
        return pq.size();
    }
    */


    /*
    
    public int minMeetingRooms(int[][] intervals) {
        
        int n = intervals.length;
        Point[] points = new Point[n];
        
        for (int i = 0; i < n; i++) {
            points[i] = new Point(intervals[i][0], intervals[i][1]);
        }
        
        Arrays.sort(points, (a, b) -> a.start - b.start);
        
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> a.end - b.end);
        
        for (Point p : points) {
            if (!pq.isEmpty() && p.start >= pq.peek().end) {
                pq.poll();
                pq.offer(p);
            } else {
                pq.offer(p);
            }
        }
        
        return pq.size();
    }
    */
}