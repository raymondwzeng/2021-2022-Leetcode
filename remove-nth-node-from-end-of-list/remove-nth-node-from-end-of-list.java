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
        //Use a "conductor" fast pointer to manipulate the end of list.
        //This is overall worst case scenario a O(n)
        int delay = n;
        ListNode conductor = head;
        while(delay > 0 && conductor != null) {
            conductor = conductor.next;
            delay--;
        }
        
        ListNode slowRunner = head;
        ListNode previousSlow = null;
        while(conductor != null) {
            previousSlow = slowRunner;
            slowRunner = slowRunner.next;
            conductor = conductor.next;
        }
        
        if(previousSlow != null) {
            previousSlow.next = slowRunner.next;
        } else {
            head = head.next;
        }
        
        return head;
    }
}