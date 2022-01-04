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
        ListNode list1Runner = list1;
        ListNode list2Runner = list2;
        ListNode newHead = null;
        ListNode newTail = null;
        while(list1Runner != null && list2Runner != null) {
            if(list1Runner.val < list2Runner.val) {
                if(newHead == null) {
                    newHead = list1Runner; 
                    newTail = list1Runner;
                } else {
                    newTail.next = list1Runner;
                    newTail = list1Runner;
                }
                list1Runner = list1Runner.next;
            } else {
                if(newHead == null) {
                    newHead = list2Runner; 
                    newTail = list2Runner;
                } else {
                    newTail.next = list2Runner;
                    newTail = list2Runner;
                }
                list2Runner = list2Runner.next;
            }
        }
        if(list1Runner == null && list2Runner != null) {
            while(list2Runner != null) {
                if(newHead == null) {
                    newHead = list2Runner; 
                    newTail = list2Runner;
                } else {
                    newTail.next = list2Runner;
                    newTail = list2Runner;
                }
                list2Runner = list2Runner.next;
            }
        }
        if(list1Runner != null && list2Runner == null) {
            while(list1Runner != null) {
                if(newHead == null) {
                    newHead = list1Runner; 
                    newTail = list1Runner;
                } else {
                    newTail.next = list1Runner;
                    newTail = list1Runner;
                }
                list1Runner = list1Runner.next;
            }
        }
        return newHead;
    }
}