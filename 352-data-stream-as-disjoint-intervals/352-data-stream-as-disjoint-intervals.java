class SummaryRanges {
    
    // key represents the start of the interval
    TreeMap<Integer, int[]> treeMap;

    public SummaryRanges() {
        this.treeMap = new TreeMap();
    }
    
    public void addNum(int val) {
        if (treeMap.containsKey(val)) {
            return;
        }
        
        Integer l = this.treeMap.lowerKey(val);
        Integer r = this.treeMap.higherKey(val);
        
        if (l != null && treeMap.get(l)[1] == val - 1 && r != null && r == val + 1) {
            // combine the two
            treeMap.get(l)[1] = treeMap.get(r)[1];
            treeMap.remove(r);
        } 
        // NOTE: 这里非常容易出错！！不只是需要 treeMap.get(l)[1] == val，也可以是 >= val 的情况
        else if (l != null && treeMap.get(l)[1] + 1 >= val) {
            treeMap.get(l)[1] = Math.max(treeMap.get(l)[1], val);
        } else if (r != null && r == val + 1) {
            int rr = treeMap.get(r)[1];
            treeMap.put(val, new int[]{val, rr});
            treeMap.remove(r);
        } else {
            treeMap.put(val, new int[]{val, val});
        }
    }
    
    public int[][] getIntervals() {
        List<int[]> list = new ArrayList<>(this.treeMap.values());
        return list.toArray(new int[list.size()][]);
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */