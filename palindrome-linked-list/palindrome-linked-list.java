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
        if(head == null) return null;
        ListNode runner = head;
        ListNode lastNode = null;
        while(runner != null) {
            ListNode temp = runner.next;
            runner.next = lastNode; //Set next to lastNode, then lastNode to the runner to avoid self-cycling I believe.
            lastNode = runner;
            runner = temp;
        }
        return lastNode;
    }
    
    public ListNode findMiddle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    public boolean isPalindrome(ListNode head) {
        //Combination of other algorithms! (yay)
        //We are guaranteed a head in this case, so no worries. Otherwise check & return, idk, true?
        ListNode listMiddle = findMiddle(head);
        ListNode reverseHead = reverseList(listMiddle);
        ListNode runner = head;
        while(runner != null && reverseHead != null) { //Since the other half is now reversed, it has a null point at the end.
            if(runner.val != reverseHead.val) {
                return false;
            }
            runner = runner.next;
            reverseHead = reverseHead.next; //Whoops, forgot to increment the runners!
        }
        return true;
        
        //Runtime: Given m = n/2, O(m) to find middle, O(m) to reverse the list, and O(m) to check. So O(3m), or O([3/2]n), or linear.
    }
}