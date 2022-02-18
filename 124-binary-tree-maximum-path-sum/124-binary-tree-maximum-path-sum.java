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
    
    int maxSeen = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        helper(root);
        
        return maxSeen;
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int leftSum = Math.max(helper(root.left), 0);
        int rightSum = Math.max(helper(root.right), 0);
        
        // single side
        maxSeen = Math.max(maxSeen, root.val + Math.max(leftSum, rightSum));
        // both sides
        maxSeen = Math.max(maxSeen, root.val + leftSum + rightSum);
        
        return root.val + Math.max(leftSum, rightSum);
    }
    
    
    /*
    Integer maxSeen = Integer.MIN_VALUE;
    
    public int maxPathSumHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = Math.max(maxPathSumHelper(root.left), 0);
        int right = Math.max(maxPathSumHelper(root.right), 0);
        
        maxSeen = Math.max(maxSeen, root.val + left + right);
        
        return root.val + Math.max(left, right);
    }
    
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        this.maxPathSumHelper(root);
        return maxSeen;
    }
    */
    
    

//     int max_sum = Integer.MIN_VALUE;
    
//     private int maxPathSumHelper(TreeNode root) {
//         if (root == null) return 0;
        
//         int currValue = root.val;
//         int left = Math.max(this.maxPathSumHelper(root.left), 0);
//         int right = Math.max(this.maxPathSumHelper(root.right), 0);
        
//         // update max_sum if there is larger value
//         max_sum = Math.max(max_sum, currValue + left + right);
        
//         // return the current maximum number of one path
//         // Note the explaination notes and in the discussion
//         // My understanding is that a valid path is a "straight line" that connects all the nodes, in other words, it can't "fork".
//         // that's the reason why it didn't 
//         // return currValue + left + right
//         return currValue + Math.max(left, right);
//     }
    
//     public int maxPathSum(TreeNode root) {
//         if (root == null) return 0;
//         this.maxPathSumHelper(root);
//         return max_sum;
//     }
}
