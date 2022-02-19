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
        TreeNode node;
        int depth;
        
        public Node(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        // depth
        Node res = helper(root, 0);
        
        return res.node;
    }
    
    private Node helper(TreeNode root, int depth) {
        if (root == null) {
            return null;
        }
        
        // leaf
        if (root.left == null && root.right == null) {
            return new Node(root, depth);
        }
        
        Node left = helper(root.left, depth + 1);
        Node right = helper(root.right, depth + 1);
        
        // it is not possible both left and right are null, as that case has been returned as above
        if (left == null) return right;
        else if (right == null) return left;
        
        // 3 conditions
        if (left.depth < right.depth) {
            return right;
        } else if (left.depth > right.depth) {
            return left;
        } else {
            // left.depth == right.depth
            // current node as the common ancestor
            // NOTE: here passed depth should be from the deepest leaf,
            // as leftLeaf.depth == rightLeaf.depth, thus either one is ok!!
            return new Node(root, left.depth);
        }
    }
}