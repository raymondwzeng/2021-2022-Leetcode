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
        //The main issue when it comes to this problem is that with a ListNode, we don't have the ability to check
        //which node is the kth from the end.
        //Let's start simple. Let's start by removing the last element in the list.
        //In this case, we will navigate two pointers - one lagging behind the other, until the fast pointer's next is null.
        //Then, we will simply move the next pointer's next to null
        //That is for n = 1. Of course, special considerations must be accounted for if there is only 1 node.
        
        //Could we somehow build some data structure such that we can account for this? e.g. if we leave the head or have a gap, we make
        //a new pointer and start moving it?
        //But moving all of those pointers would be a pain in the ass.
        //What if we create a train of pointers? That still seems reasonable. We'll create n "conductors", such that when we have the last
        //node encounter the end, then we'll manipulate the actual node.
        //This would take up...O(n) space at the worst (if we're moving the head, I guess?). We'd need n conductors, the lagging pointer, and the replacement to null.
        //What if we have fast and slow pointers? As in first moves n - 0, n - 1, ... n-(n-1)?
        //Maybe we could have them move .next.next a number of times corresponding to n?
        //Hint 1: Maintain delay -> What's the pattern behind this? 
        //Storage: O(1)
        //Runtime: O(n)
        int delay = n;
        ListNode slow = head;
        ListNode fast = head;
        ListNode slowPrev = null;
        while(delay > 0 && fast != null) {
            fast = fast.next; //We don't mind if fast.next is equal to null.
            delay--;
        }
        while(fast != null) {
            slowPrev = slow;
            slow = slow.next;
            fast = fast.next;
        } //Moves slow and fast to the proper positions
        if(slowPrev != null) {
            slowPrev.next = slow.next;
        } else if(slow == head) {
            head = head.next;
        }
        return head;
    }
}