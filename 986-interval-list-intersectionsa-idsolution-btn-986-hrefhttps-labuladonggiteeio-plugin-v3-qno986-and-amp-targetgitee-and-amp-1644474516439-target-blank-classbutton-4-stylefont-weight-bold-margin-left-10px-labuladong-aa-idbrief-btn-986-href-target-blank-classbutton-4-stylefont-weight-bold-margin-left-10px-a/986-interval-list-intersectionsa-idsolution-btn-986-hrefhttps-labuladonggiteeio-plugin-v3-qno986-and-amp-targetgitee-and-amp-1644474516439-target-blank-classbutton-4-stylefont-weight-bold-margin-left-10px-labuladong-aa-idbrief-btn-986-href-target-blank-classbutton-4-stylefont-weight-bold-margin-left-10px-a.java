class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int n = firstList.length;
        int m = secondList.length;
        
        List<int[]> res = new ArrayList<>();
        
        int i = 0, j = 0;
        
        while (i < n && j < m) {
            // overlap
//             if (firstList[i][1] >= secondList[j][0] || secondList[j][1] >= firstList[i][0] || 
//                 ) {
//                 int l = Math.max(firstList[i][0], secondList[j][0]);
//                 int r = Math.min(firstList[i][1], secondList[j][1]);
                
//                 res.add(new int[]{l, r});
//             }
            
            // overlap
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
}