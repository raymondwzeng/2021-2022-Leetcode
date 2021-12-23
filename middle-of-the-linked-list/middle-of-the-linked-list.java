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
    public ListNode middleNode(ListNode head) {
        //The simple solution is simply to have one cycle to go from head to tail
        //Then go a second time to the middle (or the second middle).
        
        //This would produce a runtime of O(n) + O(n/2), where n is the length of the LL.
        
        //Can we do better?
        int length = 0;
        ListNode runner = head;
        while(runner.next != null) {
            length++;
            runner = runner.next;
        }
        int middleFloor = Math.round(length/2f);
        runner = head;
        while(middleFloor > 0) {
            runner = runner.next;
            middleFloor--;
        }
        return runner;
    }
}