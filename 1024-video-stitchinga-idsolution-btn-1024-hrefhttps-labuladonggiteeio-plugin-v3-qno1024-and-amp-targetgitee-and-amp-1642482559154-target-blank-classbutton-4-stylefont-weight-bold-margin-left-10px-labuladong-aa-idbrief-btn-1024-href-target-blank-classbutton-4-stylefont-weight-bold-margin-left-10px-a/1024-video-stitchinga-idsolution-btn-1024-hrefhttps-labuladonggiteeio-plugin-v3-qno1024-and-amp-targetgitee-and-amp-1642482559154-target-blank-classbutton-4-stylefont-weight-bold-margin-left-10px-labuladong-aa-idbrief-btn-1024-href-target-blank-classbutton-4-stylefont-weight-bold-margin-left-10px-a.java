class Solution {
    
    public int videoStitching(int[][] clips, int time) {
        
        Arrays.sort(clips, (a, b) -> a[0] - b[0]);
        
        int n = clips.length;
        
        int currEnd = 0, newEnd = 0;
        int res = 0;
        
        int i = 0;
        while (i < n && clips[i][0] <= currEnd) {
            
            while (i < n && clips[i][0] <= currEnd) {
                newEnd = Math.max(newEnd, clips[i][1]);
                i++;
            }
            
            res++;
            
            // NOTE: 加入newEnd，防止Stack Overflow
            currEnd = newEnd;
            
            if (currEnd >= time) {
                return res;
            }
        }
        
        return -1;
    }
    
    
    // 错误！！
    /*
    public int videoStitching(int[][] clips, int time) {
        // total: 0 - time, [0, time]
        
        // f(i, j) = min{f(i, k) + f(k + 1, j)}, i < k < j
        
        int n = time + 1;
        
        int[][] dp = new int[n][n];
        for (int[] r : dp) {
            Arrays.fill(r, 10000);
        }
        
        // dp[i][i] = 0
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        
        for (int[] c : clips) {
            int l = c[0];
            int r = c[1];
            
            for (int i = l; i < r; i++)
                for (int j = i + 1; j <= r; j++) {
                    dp[i][j] = 1;
                }
        }
        
        for (int l = 3; l <= n; l++)
            for (int i = 0; i + l - 1 < n; i++) {
                int j = i + l - 1;
                
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
            }
        
        return dp[0][n - 1] >= time ? -1 : dp[0][n - 1];
    }
    */
}