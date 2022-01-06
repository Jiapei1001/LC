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
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        TreeNode dummy = new TreeNode();
        
        dummy.right = root;
        
        dfs(root, dummy, target);
        
        return dummy.right;
    }
    
    private TreeNode dfs(TreeNode root, TreeNode parent, int target) {
        if (root == null) {
            return root;
        }
        
        if (root.left == null && root.right == null && root.val == target) {
            if (parent.left == root) {
                parent.left = null;
            } else if (parent.right == root) {
                parent.right = null;
            }
            return null;
        }
        
        root.left = dfs(root.left, root, target);
        root.right = dfs(root.right, root, target);

        if (root.left == null && root.right == null && root.val == target) {
            if (parent.left == root) {
                parent.left = null;
            } else if (parent.right == root) {
                parent.right = null;
            }
            return null;
        }
        
        return root;
    }
}