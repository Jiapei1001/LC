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
    
    public void recoverTree(TreeNode root) {
        // find the first wired node
        // find the last node that is <= the first wired node
        // swap
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        
        TreeNode prev = null;
        TreeNode left = null, right = null;
        
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            
            curr = stack.pop();
            if (prev == null) {
                prev = curr;
                curr = curr.right;
                continue;
            }
            
            // first find
            if (prev.val >= curr.val) {
                if (left == null) {
                    left = prev;
                    right = curr;
                }
            }
            
            // update right node, find the right most wired node that is <= left.val
            if (left != null && curr.val <= left.val) {
                right = curr;
            }
            
            prev = curr;
            curr = curr.right;
        }
        
        // swap
        int temp = left.val;
        left.val = right.val;
        right.val = temp;
    }
    
    
    
    
    /*
    TreeNode x = null, y = null, prev = null;
    
    private void findTwoNodes(TreeNode curr) {
        if (curr == null)
            return;
        
        this.findTwoNodes(curr.left);
        
        if (prev != null && prev.val > curr.val) {
            y = curr;
            if (x == null) x = prev;
            else
                return;
        }
        prev = curr;
        
        this.findTwoNodes(curr.right);
    }
    
    private void swap(TreeNode x, TreeNode y){
        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }
    
    public void recoverTree(TreeNode root) {
        this.findTwoNodes(root);
        this.swap(x, y);
    }
    */
    
    
    /* Option 1 - Iterative
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode prev = null, curr = root;
        
        TreeNode x = null, y = null;
        
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            
            curr = stack.pop();
            
            if (prev != null && prev.val > curr.val) {
                y = curr;
                if (x == null) 
                    x = prev;
                else
                    break;
            }
            prev = curr;
            curr = curr.right;
        }
        
        this.swap(x, y);
        
        return;
    }
    
    private void swap(TreeNode x, TreeNode y){
        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }
    */

    
    /*
    public void swap(TreeNode x, TreeNode y) {
        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }
    
    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        TreeNode prev = null;
        
        TreeNode x = null, y = null;
        
        while (curr != null || !stack.isEmpty()) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (prev != null && curr.val < prev.val) {
                y = curr;
                if (x == null) x = prev;
                else
                    break;
            }
            prev = curr;
            curr = curr.right;
        }
        this.swap(x, y);
    }
    */
}
