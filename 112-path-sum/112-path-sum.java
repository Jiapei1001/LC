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
    
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        
        return dfs(root, 0, targetSum);
    }
    
    private boolean dfs(TreeNode root, int curr, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return curr + root.val == targetSum;
        }
        
        return dfs(root.left, curr + root.val, targetSum) || 
               dfs(root.right, curr + root.val, targetSum);
    }
    
    /*
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root, 0, targetSum);
    }
    
    private boolean dfs(TreeNode root, int currSum, int targetSum) {
        if (root == null) {
            return false;
        }
        
        if (root.left == null && root.right == null) {
            currSum += root.val;
            
            return currSum == targetSum ? true : false;
        }
        
        return dfs(root.left, currSum + root.val, targetSum) ||
               dfs(root.right, currSum + root.val, targetSum);
    }
    */
}