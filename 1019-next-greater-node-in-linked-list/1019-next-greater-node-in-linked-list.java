/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nextLargerNodes(ListNode head) {
        Map<ListNode, Integer> map2Idx = new HashMap<>();
        
        int idx = 0;
        ListNode curr = head;
        while (curr != null) {
            map2Idx.put(curr, idx);
            curr = curr.next;
            idx++;
        }
        
        // idx++, represents the number of elements
        int[] res = new int[idx];
        
        Stack<ListNode> stack = new Stack<>();
        curr = head;
        
        while (curr != null) {
            while (!stack.isEmpty() && curr.val > stack.peek().val) {
                ListNode last = stack.pop();
                int lastIdx = map2Idx.get(last);
                res[lastIdx] = curr.val;
            }
            
            stack.push(curr);
            curr = curr.next;
        }
        
        return res;
    }
}