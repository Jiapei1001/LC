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
    
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        
        TreeNode left = pruneTree(root.left);
        TreeNode right = pruneTree(root.right);
        
        // edit lower children
        if (left == null) root.left = null;
        if (right == null) root.right = null;
        
        // return
        if (left == null && right == null && root.val == 0) {
            return null;
        }
        
        return root;
    }
    
    
    
    
    
    
    /*
    public TreeNode pruneTree(TreeNode root) {
        TreeNode dummy = new TreeNode();
        dummy.right = root;
        
        dfs(dummy);
        
        return dummy.right;
    }
    
    private boolean dfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        boolean left = dfs(root.left);
        if (left) {
            root.left = null;
        }
        
        boolean right = dfs(root.right);
        if (right) {
            root.right = null;
        }
        
        // 只有当都是 0 的情况，才return true
        if (root.val == 0 && left && right) {
            return true;
        } else {
            return false;
        }
    }
    */
}