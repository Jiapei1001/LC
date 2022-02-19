/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    public int rob(TreeNode root) {
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }
    
    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        
        // dp[0] don't take, dp[1] take
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        
        int[] dp = new int[2];
        // take
        dp[1] = root.val + left[0] + right[0];
        
        // don't take, left can either take or don't take, which ever one that is larger
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        
        return dp;
    }
    
    
    // Top Down + Recursion
    /*
    public int rob(TreeNode root) {
        Map<TreeNode, Integer> memo = new HashMap<>();
        return helper(root, memo);
    }
    
    private int helper(TreeNode root, Map<TreeNode, Integer> memo) {
        if (root == null) {
            return 0;
        }
        
        if (root.left == null && root.right == null) {
            return root.val;
        }
        
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        
        // don't take
        int left = helper(root.left, memo);
        int right = helper(root.right, memo);
        
        // take
        int leftL = root.left == null ?  0: helper(root.left.left, memo);
        int leftR = root.left == null ? 0 : helper(root.left.right, memo);
        int rightL = root.right == null ? 0 : helper(root.right.left, memo);
        int rightR = root.right == null ? 0 : helper(root.right.right, memo);
        
        int res = Math.max(left + right, root.val + leftL + leftR + rightL + rightR);
        
        memo.put(root, res);
        
        return res;
    }
    */
    
    
    /*
    public int rob(TreeNode root) {
        // dp represents the maximum value can get
        // f(i) = f(i - 1), not include
        //        f(i - 2) + currLevelValue, include
        
        Map<TreeNode, Integer> memo = new HashMap<>();
        return helper(root, memo);
    }
    
    private int helper(TreeNode root, Map<TreeNode, Integer> memo) {
        if (root == null) {
            return 0;
        }
        
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        
        // f(i - 2), grandchildren
        int rob = root.val;
        if (root.left != null) {
            rob += helper(root.left.left, memo) + helper(root.left.right, memo);
        }
        if (root.right != null) {
            rob += helper(root.right.left, memo) + helper(root.right.right, memo);
        }
        
        int notRob = helper(root.left, memo) + helper(root.right, memo);
        
        int res = Math.max(rob, notRob);
        memo.put(root, res);
        
        return res;
    }
    */
}