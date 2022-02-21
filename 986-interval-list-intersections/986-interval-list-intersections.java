class Solution {

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        // treat both int[] as single array
        // sort by start time
        // PQ sort by end time
        // compare intersection
        
        List<int[]> list = new ArrayList<>();
        // each list of intervals is disjoint and sorted
        // no need to build a long list and sort it
        
        // Collections.sort(list, (a, b) -> a[0] - b[0]);
        int n = firstList.length;
        int m = secondList.length;
        
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (firstList[i][1] < secondList[j][0]) {
                i++;
            } else if (secondList[j][1] < firstList[i][0]) {
                j++;
            }
            // intersection
            else {
                int s = Math.max(firstList[i][0], secondList[j][0]);
                int e = Math.min(firstList[i][1], secondList[j][1]);
                list.add(new int[]{s, e});
                
                // update the one ends early
                if (firstList[i][1] > secondList[j][1]) {
                    j++;
                } else {
                    i++;
                }
            }
        }
        
        return list.toArray(new int[list.size()][]);
    }
    
    
    
    
    
    
    /*
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int n = firstList.length;
        int m = secondList.length;
        
        List<int[]> res = new ArrayList<>();
        
        int i = 0, j = 0;
        
        while (i < n && j < m) {
            // overlap
            // 这两种不相交的cases的相反，就是所有可能相交的情况
            
            // labuladong - firstList[i][1] >= secondList[j][0] && secondList[i][1] >= firstList[i][0]
            
            // 通过计算出的 l <= r 来判断
            if (!(firstList[i][1] < secondList[j][0] || secondList[j][1] < firstList[i][0])) {
                int l = Math.max(firstList[i][0], secondList[j][0]);
                int r = Math.min(firstList[i][1], secondList[j][1]);
                
                res.add(new int[]{l, r});
            }
            
            // update the one that ends early
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        
        return res.toArray(new int[res.size()][]);
    }
    */
}