class Solution {
    
    public int maxArea(int[] height) {
        int n = height.length;
        
        int i = 0, j = n - 1;
        int res = 0;
        while (i < j) {
            int tempArea = (j - i) * (Math.min(height[i], height[j]));
            res = Math.max(res, tempArea);
            
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        
        return res;
    }
    
    /*
    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
            
        int n = height.length;
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, n - 1});
        
        int res = 0;
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            
            if (curr[0] >= curr[1]) {
                continue;
            }
            
            // update area
            res = Math.max(res, 
                           Math.min(height[curr[0]], height[curr[1]]) * Math.abs(curr[0] - curr[1]));
            
            q.offer(new int[]{curr[0] + 1, curr[1]});
            q.offer(new int[]{curr[0], curr[1] - 1});
        }
        
        return res;
    }
    */
}