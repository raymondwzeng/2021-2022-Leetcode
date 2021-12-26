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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //Two pointer approach, and we'll merge list2 into list1. O(n + m) time (list1 length + list2 length), O(1) space.
        ListNode head = null;
        ListNode tail = head;
        ListNode list1Pointer = list1;
        ListNode list2Pointer = list2;
        while(list1Pointer != null && list2Pointer != null) {
            if(list1Pointer.val < list2Pointer.val) {
                if (head == null) {
                    head = list1Pointer;
                    tail = list1Pointer;
                } else {
                    tail.next = list1Pointer;
                    tail = list1Pointer;
                }
                list1Pointer = list1Pointer.next;
            } else {
                if (head == null) {
                    head = list2Pointer;
                    tail = list2Pointer;
                } else {
                    tail.next = list2Pointer; //Whatever our previous tail was, its next is now this.
                    tail = list2Pointer; //Set our new tail now (attached to end)   
                }
                list2Pointer = list2Pointer.next;
            }
        }
        while(list1Pointer != null) {
            if(head == null) {
                head = list1Pointer;
                tail = list1Pointer;
            } else {
                tail.next = list1Pointer;
                tail = list1Pointer;
            }
            list1Pointer = list1Pointer.next;
        }
        while(list2Pointer != null) {
            if(head == null) {
                head = list2Pointer;
                tail = list2Pointer;
            } else {
                tail.next = list2Pointer;
                tail = list2Pointer;
            }
            list2Pointer = list2Pointer.next;
        }
        return head;
    }
}