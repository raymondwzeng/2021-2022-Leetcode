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

/**
        //First thought: Use a stack!
        //We'll push to the stack if it's not the same, pop if it is.
        //If the stack is not empty at the end, then it is not a palindrome.
        //O(n) time, O(n/2) space.
        
        //Edge case 1: Single item LL. We can just say that if the head's next is empty, it's ok.
        if(head.next == null) return true;
        Stack<Integer> values = new Stack<Integer>();
        ListNode runner = head;
        while(runner != null) {
            if(!values.empty() && runner.val == values.peek()) {
                values.pop();
            } else {
                values.push(runner.val);
            }
            runner = runner.next;
        }
        return values.empty();
        
        //This doesn't *quite* work. Example: [1, 0, 1]. None is popped, so it returns false.
*/
class Solution {
    public ListNode findMiddle(ListNode head) { //Finds the second middle value like in the other problem.
        if(head == null) return null;
        ListNode fastRunner = head;
        ListNode slowRunner = head;
        while(fastRunner != null && fastRunner.next != null) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;
        }
        return slowRunner;
    }
    
    public ListNode reverseList(ListNode head) {
        if(head == null) return null;
        ListNode slowRunner = head;
        ListNode fastRunner = head.next;
        ListNode lastAddedNode = null;
        while(slowRunner != null && fastRunner != null) {
            ListNode tempNode = fastRunner.next;
            fastRunner.next = slowRunner;
            slowRunner.next = lastAddedNode; //If this happens to be null, then that's all fine and dandy.
            lastAddedNode = fastRunner; //We need
            if(tempNode != null) fastRunner = tempNode.next;
            slowRunner = tempNode;
        }
        if(slowRunner != null && fastRunner == null) {
            slowRunner.next = lastAddedNode;
            lastAddedNode = slowRunner;
        }
        return lastAddedNode;
    }
    
    public boolean isPalindrome(ListNode head) {
        //Maybe we could use 2 pointers in some way?
        //If we have a fast pointer, of course we'll get to the end faster, but that doesn't give us much...
        //Perhaps another question: Why are the integers limited to 0-9 then?
        //The follow up of O(n) time and O(1) space seems to suggest using a DS could get one solution.
        
        
        //A few days later - use the findMiddle algorithm, then traverse to verify!
        //No null edge case here - the head is guaranteed to exist.
        
        
        //Nick White's solution: Reverse half the list to compare.
        //This produces...O(n/2) for finding middle, O(n/2) for reversing half the list, and O(n/2) for comparisons? So O([3/2]n)?
        ListNode middle = findMiddle(head);
        ListNode newHalfHead = reverseList(middle);
        while(head != null && newHalfHead != null) {
            if(head.val != newHalfHead.val) return false;
            head = head.next;
            newHalfHead = newHalfHead.next;
        }
        return true;
    }
}