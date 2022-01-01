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
        ListNode head = null;
        ListNode tail = null;
        ListNode list1Runner = list1;
        ListNode list2Runner = list2;
        while(list1Runner != null && list2Runner != null) { //We do this because we don't know which one is shorter, and it would take too much to figure out.
            if(list1Runner.val < list2Runner.val) {
                if(head == null) {
                    head = list1Runner;
                    tail = list1Runner;
                } else {
                    tail.next = list1Runner;
                    tail = list1Runner;
                }
                list1Runner = list1Runner.next;
            } else {
                if(head == null) {
                    head = list2Runner;
                    tail = list2Runner;
                } else {
                    tail.next = list2Runner;
                    tail = list2Runner;
                }
                list2Runner = list2Runner.next;
            }
        }
        if(list1Runner == null) {
            while(list2Runner != null) {
                if(head == null) {
                    head = list2Runner;
                    tail = list2Runner;
                } else {
                    tail.next = list2Runner;
                    tail = list2Runner;
                }
                list2Runner = list2Runner.next;
            }
        } else {
            while(list1Runner != null) {
                if(head == null) {
                    head = list1Runner;
                    tail = list1Runner;
                } else {
                    tail.next = list1Runner;
                    tail = list1Runner;
                }
                list1Runner = list1Runner.next;
            }
        }
        return head;
    }
}