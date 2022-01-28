class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        
        // cannot be divided into d days, as it must finish one job per day
        if (n < d) return -1;
        
        // f(i, k) := the minimal sum of job difficulty for the front i days, with k partitions
        // f(i, k)  =  f(j, k - 1) + MAX{j + 1, i}, where j < i
        //          OR f(i, k)
        
        int[][] dp = new int[n][d + 1];
        for (int[] row : dp) Arrays.fill(row, 500000);
        
        // base case
        // for the front i days, with only 1 partition
        int maxD = 0;
        for (int i = 0; i < n; i++) {
            // for dp[i][0], k == 0, it is unnecessary, but added here
            dp[i][0] = 0;
            
            maxD = Math.max(maxD, jobDifficulty[i]);
            dp[i][1] = maxD;
        }
        
        // partition 1 has completed during the base case condition
        for (int k = 2; k <= d; k++)
            for (int i = 0; i < n; i++) {
                maxD = jobDifficulty[i];
                
                // j only loop to 1, to avoid index out of boundary below
                for (int j = i; j >= 1; j--) {    
                    maxD = Math.max(maxD, jobDifficulty[j]);
                    
                    // [0, j - 1]  ->  [j, i]
                    dp[i][k] = Math.min(dp[i][k], dp[j - 1][k - 1] + maxD);
                }
            }

        return dp[n - 1][d];
    }
}