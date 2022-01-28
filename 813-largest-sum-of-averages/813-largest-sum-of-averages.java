class Solution {
    public double largestSumOfAverages(int[] nums, int k) {        
        int n = nums.length;
        
        double[] prefixSum = new double[n + 1];
        
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        
        // f(i, k) := represent the maximum sum of average for nums[:i]
        //    NOTE: here nums[i:] starts from i
        // f(i, k) = MAX{ f(i, k) OR f(j, k - 1) + Average(i, j), where 1 <= j < i }
        
        double[][] dp = new double[n + 1][k + 1];
        
        // NOTE: dp[i][k] represents the largest sum starting from i
        // as k is the at most, thus k is the base case, and doesn't matter in this case
        
        // NOTE: here k is 1, as only one segment!!
        // NOTE: here it is average, thus it must be prefixSum[i] / i
        // if it is prefixSum[i] / 1, then it is sum!!
        for (int i = 1; i <= n; i++) dp[i][1] = prefixSum[i] / i;
        
        for (int m = 2; m <= k; m++)
            for (int i = m; i <= n; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    
                    double tempAverage = (double) (prefixSum[i] - prefixSum[j]) / (i - j);
                    
                    // don't divide vs. add another partitoin
                    dp[i][m] = Math.max(dp[i][m],
                                        dp[j][m - 1] + tempAverage);
                }
            }
        
        return dp[n][k];
    }
    
    
    /*
    public double largestSumOfAverages(int[] nums, int k) {        
        int n = nums.length;
        
        double[] prefixSum = new double[n + 1];
        
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        
        // f(i, k) := represent the maximum sum of average for nums[i:]
        //    NOTE: here nums[i:] starts from i
        // f(i, k) = MAX{ f(i, k) OR f(j, k - 1) + Average(i, j), where i + 1 <= j <= n }
        
        double[][] dp = new double[n + 1][k + 1];
        
        // NOTE: dp[i][k] represents the largest sum starting from i
        // as k is the at most, thus k is the base case, and doesn't matter in this case
        
        // NOTE: here k is 1, as only one segment!!
        for (int i = 1; i <= n; i++) dp[i][1] = (prefixSum[n] - prefixSum[(i - 1)]) / (n - (i - 1));
        
        for (int m = 2; m <= k; m++)
            for (int i = 1; i <= n; i++) {
                for (int j = i; j < n; j++) {
                    
                    double tempAverage = (double) (prefixSum[j] - prefixSum[(i - 1)]) / (j - (i - 1));
                    
                    // don't divide vs. add another partitoin
                    dp[i][m] = Math.max(dp[i][m],
                                        dp[j + 1][m - 1] + tempAverage);
                }
            }
        
        return dp[1][k];
    }
    */
    
    
    
    
    
    
    /*
    public double largestSumOfAverages(int[] nums, int k) {
        // f(i, j) represents the max sum of average 
        // for the front i nums, with j partitions
            
        // f(i, j) = f(i - k, j - 1) + average sum from i - k + 1 to i
        // f(0, j) = 0;
        // f(i, 0) = 0;
        
        int n = nums.length;
        double[][] dp = new double[n + 1][k + 1];
        
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= k; j++) {
                
                for (int m >= j + 1; m <= i; m++) {
                    double average = (prefixSum[m] - prefixSum[i - m]) / m;
                    
                    dp[i][j] = Math.max(dp[i][j], dp[m][j - 1] + )
                }
                
            }
    }
    */
}