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
    
    int move = 0;
    
    public int distributeCoins(TreeNode root) {
        // {subtree # of nodes, # of coins}
        int[] res = helper(root);
        
        return move;
    }
    
    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        
        move += Math.abs(left[0] - left[1]);
        move += Math.abs(right[0] - right[1]);
        
        
        return new int[]{left[0] + right[0] + 1, left[1] + right[1] + root.val};
    }
    
    
    /*
    public int move = 0;
    
    public int distributeCoins(TreeNode root) {
        // 核心！！
        // {subtree nodes, subtree coins}
        
        int[] res = helper(root);
        
        return move;
    }
    
    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        
        move += Math.abs(left[0] - left[1]);
        move += Math.abs(right[0] - right[1]);
        
        return new int[]{left[0] + right[0] + 1, left[1] + right[1] + root.val};
    }
    */
}