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
    public TreeNode trimBST(TreeNode root, int low, int high) {
        TreeNode dummy = new TreeNode();
        dummy.right = root;
        
        dfs(root, dummy, low, high);
        
        return dummy.right;
    }
    
    private TreeNode dfs(TreeNode root, TreeNode parent, int low, int high) {
        if (root == null) {
            return root;
        }
        
        TreeNode left = dfs(root.left, root, low, high);
        TreeNode right = dfs(root.right, root, low, high);
        
        
        if (root.val < low || root.val > high) {

            if (parent.left == root) {
                if (left == null && right == null) {
                    parent.left = null;
                    return null;
                } else if (left != null) {
                    parent.left = left;
                    return left;
                } else if (right != null) {
                    parent.left = right;
                    return right;
                }
            } else if (parent.right == root) {
                if (left == null && right == null) {
                    parent.right = null;
                    return null;
                } else if (left != null) {
                    parent.right = left;
                    return left;
                } else if (right != null) {
                    parent.right = right;
                    return right;
                }
            }
        }
        
        
        return root;
    }
}