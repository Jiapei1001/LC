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
    public int goodNodes(TreeNode root) {
        // root.val >= currMax
        return helper(root, Integer.MIN_VALUE);
    }
    
    private int helper(TreeNode root, int currMax) {
        if (root == null) {
            return 0;
        }
        
        if (root.val >= currMax) {
            return 1 + helper(root.left, root.val) + helper(root.right, root.val);
        } else {
            return helper(root.left, currMax) + helper(root.right, currMax);
        }
    }
}