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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int delay = n;
        ListNode fast = head;
        ListNode slow = head;
        ListNode prevSlow = null;
        while(delay > 0 && fast != null) {
            fast = fast.next;
            delay--;
        }
        while(fast != null) {
            prevSlow = slow;
            fast = fast.next;
            slow = slow.next;
        }
        if(prevSlow != null) {
            prevSlow.next = slow.next;
        } else {
            //This only procs if we've moved past the point of a fast null before starting anything.
            head = head.next;
        }
        return head;
    }
}