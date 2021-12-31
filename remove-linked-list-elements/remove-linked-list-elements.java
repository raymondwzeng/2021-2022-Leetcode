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
        //Given the pervasiveness of a 2pointer approach, that might be the play.
        if(head == null) return null; //We need to account for a null head this time.
        ListNode fast = head.next;
        ListNode slow = head;
        
        while(fast != null) { //While fast is not null, because we need the slow's next to be it.
            if(fast.val == val) {
                slow.next = fast.next; //Just set the next to be the element afterwards.
            }
            
            fast = fast.next;
            if(slow.next != null && slow.next.val != val) { //Slow next is equal to fast. (Error here: You can't use fast as a proxy for slow)
                slow = slow.next;    //ONLY move the slow pointer if we know that the next isn't it.           
            }
        }
        
        if(head.val == val) {
            head = head.next; //Since we have the very small chance that the head starts as the value, we move it to whatever. (such as in the case of example 3)
        }
        return head;
        //This is O(n), since we're using our fast pointer to move from head to end.
    }
}