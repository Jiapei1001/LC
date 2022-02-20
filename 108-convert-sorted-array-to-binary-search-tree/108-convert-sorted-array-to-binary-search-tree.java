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
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        // find root
        int idx = nums.length / 2;
        TreeNode root = new TreeNode(nums[idx]);
        
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, idx));
        root.right = sortedArrayToBST(Arrays.copyOfRange(nums, idx + 1, nums.length));
        
        return root;
    }
}