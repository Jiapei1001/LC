class ExamRoom {
    // 0， 1， 2， 3， 4， 5， 6， 7， 8， 9
    // 0      2，     4                 9
    // 0， 1， 2， 3， 0， 1， 2， 3， 4， 0
    // 4   3  2   1   0   4  3   2   1  0
    
    private Map<Integer, int[]> map2Start;
    private Map<Integer, int[]> map2End;
    private TreeSet<int[]> intervals;
    
    private int N;
    private int count;
    
    public ExamRoom(int n) {
        this.count = 0;
        this.N = n;
        this.map2Start = new HashMap<>();
        this.map2End = new HashMap<>();
        this.intervals = new TreeSet<>((a, b) -> {
            int distA = getDistance(a);
            int distB = getDistance(b);
            
            // reverse order
            if (distA == distB) {
                return b[0] - a[0];
            }
            return distA - distB;
        });
        
        // initiate
        int[] init = new int[]{-1, n};
        this.intervals.add(init);
        this.map2Start.put(-1, init);
        this.map2End.put(n, init);
    }
    
    private int getDistance(int[] i) {
        int s = i[0];
        int e = i[1];
        
        if (s == -1) return e;
        if (e == this.N) return this.N - 1 - s;
        
        return (e - s) / 2;
    }
    
    private void addInterval(int[] i) {
        this.intervals.add(i);
        this.map2Start.put(i[0], i);
        this.map2End.put(i[1], i);
        this.count++;
    }
    
    private void removeInterval(int[] i) {
        this.intervals.remove(i);
        this.map2Start.remove(i[0]);
        this.map2End.remove(i[1]);
        this.count--;
    }
    
    public int seat() {
        this.count++;
        
        int[] largest = this.intervals.last();
        int x = largest[0];
        int y = largest[1];
        
        int newSeat;
        
        if (x == -1) {
            newSeat = 0;
        } else if (y == this.N) {
            newSeat = this.N - 1;
        } else {
            newSeat = (y - x) / 2 + x;
        }
        
        int[] left = new int[]{x, newSeat};
        int[] right = new int[]{newSeat, y};
        
        this.removeInterval(largest);
        this.addInterval(left);
        this.addInterval(right);
        
        return newSeat;
    }
    
    public void leave(int p) {
        int[] right = this.map2Start.get(p);
        int[] left = this.map2End.get(p);
        
        int[] merged = new int[]{left[0], right[1]};
        
        this.removeInterval(left);
        this.removeInterval(right);
        this.addInterval(merged);
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(n);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */