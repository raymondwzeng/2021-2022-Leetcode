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
    public ListNode removeElements(ListNode head, int val) {
        //A much more intuitive recursive solution.
        //Also runs in O(n) time, with each value either being kept (folding onto the next item), or removed (head is moved to beyond).
        if(head == null) return null;
        if(head.val == val) {
            head = removeElements(head.next, val);
        } else {
            head.next = removeElements(head.next, val);
        }
        return head;
    }
}