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
    
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }
    
    private boolean helper(TreeNode root, Integer lower, Integer higher) {
        if (root == null) return true;
        
        if (lower != null && root.val <= lower) return false;
        if (higher != null && root.val >= higher) return false;
        
        return helper(root.left, lower, root.val) && 
               helper(root.right, root.val, higher);
    }
    
    
    /*
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return false;
        
        Deque<TreeNode> stack = new ArrayDeque<>();
        
        TreeNode curr = root;
        TreeNode prev = null;
        
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.offerLast(curr);
                curr = curr.left;
            }
            curr = stack.pollLast();
            
            if (prev != null && curr.val <= prev.val)
                return false;
            
            prev = curr;
            curr = curr.right;
        }
        
        return true;
    }
    */


    /*
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return false;
        
        return this.helper(root, null, null);
    }
    
    private boolean helper(TreeNode curr, Integer lower, Integer upper) {
        if (curr == null)
            return true;
        
        if (lower != null && curr.val <= lower)
            return false;
        if (upper != null && curr.val >= upper)
            return false;
        
        return this.helper(curr.left, lower, curr.val) && 
               this.helper(curr.right, curr.val, upper);
    }
    */
}