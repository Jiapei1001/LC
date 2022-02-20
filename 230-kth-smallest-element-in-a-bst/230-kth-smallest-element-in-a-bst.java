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
    public int kthSmallest(TreeNode root, int k) {
        int[] a = new int[1];
        a[0] = k;
        
        return helper(root, a);
    }
    
    private int helper(TreeNode root, int[] a) {
        if (root == null) return -1;
        
        int left = helper(root.left, a);
        if (left != -1) return left;
        
        // process current
        a[0]--;
        if (a[0] == 0) return root.val;
        
        int right = helper(root.right, a);
        if (right != -1) return right;
        
        return -1;
    }
}