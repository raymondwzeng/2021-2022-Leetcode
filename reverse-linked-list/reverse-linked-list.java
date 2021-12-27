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
    public ListNode reverseList(ListNode head) {
        //2 pointer might work here.
        if(head == null) return null; //Edge case 2, both null ptrs.
        ListNode laggingPtr = head;
        ListNode normalPtr = head.next;
        ListNode lastAppendedNode = null;
        if(normalPtr == null) return laggingPtr; //Edge case, we only have 1 node.
        while(normalPtr != null && laggingPtr != null) {
            ListNode tempNextLag = normalPtr.next;
            normalPtr.next = laggingPtr;
            laggingPtr.next = lastAppendedNode;
            lastAppendedNode = normalPtr; //So in the next cycle, we append the 2nd node to the normal this time.
            laggingPtr = tempNextLag;
            if(laggingPtr != null) {
                normalPtr = tempNextLag.next;
            }
        }
        if(laggingPtr != null) { //Odd elements in list, so we need to do one last append.
            laggingPtr.next = lastAppendedNode;
            lastAppendedNode = laggingPtr;
        }
        return lastAppendedNode;
    }
}