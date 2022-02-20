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
    public TreeNode deleteNode(TreeNode root, int key) {
        // leaf, one child, two children
        TreeNode dummy = new TreeNode(0);
        dummy.right = root;
        
        helper(dummy, root, key);
        
        return dummy.right;
    }
    
    private TreeNode helper(TreeNode parent, TreeNode root, int key) {
        if (root == null) return null;
        
        if (root.val == key && root.left == null && root.right == null) {
            if (root.val < parent.val) parent.left = null;
            else parent.right = null;
            return null;
        } else if (root.val == key && root.left == null) {
            // root.right
            if (root.val < parent.val) parent.left = root.right;
            else parent.right = root.right;
            return root.right;
        } else if (root.val == key && root.right == null) {
            // root.left
            if (root.val < parent.val) parent.left = root.left;
            else parent.right = root.left;
            return root.left;
        } else if (root.val == key){
            TreeNode greater = root.right;
            while (greater.left != null) {
                greater = greater.left;
            }
            
            root.val = greater.val;
            return helper(root, root.right, greater.val);
        }
        
        if (key < root.val) return helper(root, root.left, key);
        else return helper(root, root.right, key);
    }
}