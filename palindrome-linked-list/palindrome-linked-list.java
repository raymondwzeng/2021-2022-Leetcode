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
    public ListNode getMiddleOfList(ListNode head) { //This takes O(n/2) time, because we're only iterating through technically half of the values
        ListNode fastPointer = head;
        ListNode slowPointer = head;
        while(fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        return slowPointer;
    }
    
    public ListNode reverseLinkedList(ListNode head) { //This takes O(n) time, because we have to traverse the entire list to reverse them all
        ListNode runner = head;
        ListNode lastNode = null;
        while(runner != null) {
            ListNode temp = runner.next;
            runner.next = lastNode;
            lastNode = runner;
            runner = temp;
        }
        return lastNode;
    }
    
    public boolean isPalindrome(ListNode head) { 
        ListNode headOfSecondHalf = reverseLinkedList(getMiddleOfList(head));
        ListNode forwardRunner = head;
        while(forwardRunner != null && headOfSecondHalf != null) { //This takes O(n/2) time, because we're iterating through both halves at the same time
            if(forwardRunner.val != headOfSecondHalf.val) return false;
            forwardRunner = forwardRunner.next;
            headOfSecondHalf = headOfSecondHalf.next;
        } //In total, we take O(n) + O(n) + O(n/2) = O(3/2n) time, still linear.
        return true;
    }
}