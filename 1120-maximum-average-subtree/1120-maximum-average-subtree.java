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
    
    class Node {
        double sum;
        int count;
        
        public Node(double sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }
    
    double maxSeen = Double.MIN_VALUE;
    
    public double maximumAverageSubtree(TreeNode root) {
        helper(root);
        
        return maxSeen;
    }
    
    private Node helper(TreeNode root) {
        if (root == null) {
            return new Node(0.0, 0);
        }
        
        Node left = helper(root.left);
        Node right = helper(root.right);
        
        double currSum = root.val + left.sum + right.sum;
        int currCount = 1 + left.count + right.count;
        
        // update global variable
        maxSeen = Math.max(maxSeen, currSum / currCount);
        
        return new Node(currSum, currCount);
    }
}