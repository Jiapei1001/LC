class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int n = intervals.length;
        
        // sort by start, if equal, sort by end in desceading order
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });
        
        int overlap = 0;
        int[] prev = intervals[0];
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= prev[0] && intervals[i][1] <= prev[1]) {
                overlap++;
            } else {
                prev = intervals[i];
            }
        }
        
        return n - overlap;
    }
}