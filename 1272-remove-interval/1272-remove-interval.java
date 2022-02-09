class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        // three segments
        
        List<List<Integer>> res = new ArrayList<>();
        
        int n = intervals.length;
        for (int[] i : intervals) {
            if (i[1] < toBeRemoved[0] || i[0] >= toBeRemoved[1]) {
                res.add(Arrays.asList(i[0], i[1]));
            } else {
                // overlap, even if there are intersection or container conditions
                // still the same
                if (i[0] < toBeRemoved[0]) {
                    res.add(Arrays.asList(i[0], toBeRemoved[0]));
                }
                // in parallel
                if (i[1] > toBeRemoved[1]) {
                    res.add(Arrays.asList(toBeRemoved[1], i[1]));
                }
            }
        }

        return res;
    }
}