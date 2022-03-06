/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

class Solution {
    
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        // combine as a single timeline
        // sort by start time, then by end time larger first
        // 1. [0,3], [0,2]
        // 2. [0,3], [1,4]
        // 3, [0,3], [5,6]
        
        List<Interval> list = new ArrayList<>();
        
        if (schedule == null || schedule.size() == 0) {
            return new ArrayList<>();
        }
        
        for (List<Interval> i : schedule) {
            list.addAll(i);
        }
        
        Collections.sort(list, (a, b) -> {
            if (a.start == b.start) {
                return b.end - a.end;
            } else {
                return a.start - b.start;
            }
        });
        
        // [1,3], [1,2], [4, 10] [5,6]
        
        List<Interval> res = new ArrayList<>();
        LinkedList<Interval> merge = new LinkedList<>();
        for (Interval i : list) {
            if (merge.isEmpty()) {
                merge.add(i);
                continue;
            }
            
            if (i.start <= merge.getLast().end) {
                merge.getLast().end = Math.max(i.end, merge.getLast().end);
            } else {
                res.add(new Interval(merge.getLast().end, i.start));
                merge.addLast(i);
            }
        }
        
        return res;
    }
    
    
    /*
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        // merge intervals
        // pq, pop() with peek()
        
        List<Interval> list = new ArrayList<>();
        for (List<Interval> s : schedule) {
            list.addAll(s);
        }
        
        Collections.sort(list, (a, b) -> {return a.start == b.start ? b.end - a.end : a.start - b.start;});
        
        LinkedList<Interval> merged = new LinkedList<>();
        // merge intervals
        for (Interval i : list) {
            if (!merged.isEmpty() && i.start < merged.getLast().end) {
                merged.getLast().end = Math.max(merged.getLast().end, i.end);
            } else {
                merged.add(i);
            }
        }
        
        // already merged
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
        for (Interval i : merged) pq.offer(i);
        
        List<Interval> res = new ArrayList<>();
        
        while (pq.size() > 1) {
            Interval curr = pq.poll();
            if (curr.end < pq.peek().start) {
                res.add(new Interval(curr.end, pq.peek().start));
            }
        }
        
        return res;
    }
    */
    
    
    
    
    
    
    /*
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        Map<Integer, Integer> start = new HashMap<>();
        Map<Integer, Integer> end = new HashMap<>();
        
        for (List<Interval> s : schedule) {
            for (Interval i : s) {
                start.put(i.start, start.getOrDefault(i.start, 0) + 1);
                end.put(i.end, end.getOrDefault(i.end, 0) + 1);
            }
        }
        
        List<Interval> res = new ArrayList<>();
        
        int l = -1, r = -1;
        int valid = 0;
        for (int i = 1; i < 100; i++) {
            if (start.containsKey(i)) {
                valid += start.get(i);
            }
            if (end.containsKey(i)) {
                valid -= end.get(i);
            }
            
            if (valid == 0) {
                if (l == -1) {
                    l = i;
                } else {
                    r = i;
                }
                
                if (r > l) res.add(new Interval(l, r));
            }
        }
        
        return res;
    }
    */
}