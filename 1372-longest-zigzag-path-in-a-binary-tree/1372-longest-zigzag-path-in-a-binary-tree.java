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
    int maxNodes = Integer.MIN_VALUE;
    
    public int longestZigZag(TreeNode root) {
        // -1 as left, 1 as right
        helper(root, true, 0);
        helper(root, false, 0);
        
        return maxNodes;
    }
    
    private void helper(TreeNode root, boolean prevIsLeft, int step) {
        if (root == null) {
            return;
        }
        
        maxNodes = Math.max(maxNodes, step);
        
        // prev
        if (prevIsLeft) {
            helper(root.right, false, step + 1);
            // not continue
            helper(root.left, true, 1);
        } else {
            helper(root.left, true, step + 1);
            // not continue
            helper(root.right, false, 1);
        }
    }
    
    
    
    
    
    
    
    
    
    
    /*
    int res = 0;
    
    private void dfs(TreeNode root, int dir, int step) {
        if (root == null)
            return;
        
        res = Math.max(res, step);
        if (dir == 1) {
            // continue going left
            this.dfs(root.left, -1, step + 1);
            // the other possibility is going right, reset step as 1
            this.dfs(root.right, 1, 1);
        } else if (dir == -1) {
            // continue going right
            this.dfs(root.right, 1, step + 1);
            // the other possibility is going left, reset step as 1
            this.dfs(root.left, -1, 1);
        }
    }
    
    public int longestZigZag(TreeNode root) {
        if (root == null)
            return 0;
        
        this.dfs(root, 1, 0);
        this.dfs(root, -1, 0);
        
        return res;
    }
    */
}