class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });
        
        /*
        int res = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                intervals[i][1] = Math.min(intervals[i][1], intervals[i - 1][1]);
                res++;
            }
        }
        */
        int res = 0;
        int prev = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[prev][1]) {
                // as i has end less, thus can delete prev
                // thus leave more room when compare with the next element
                if (intervals[i][1] < intervals[prev][1]) {
                    prev = i;
                }
                res++;
            } else {
                prev = i;
            }
        }
        
        return res;
    }
}