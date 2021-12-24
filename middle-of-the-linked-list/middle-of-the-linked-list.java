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
Solution 1:
        //The simple solution is simply to have one cycle to go from head to tail
        //Then go a second time to the middle (or the second middle).
        
        //This would produce a runtime of O(n) + O(n/2), where n is the length of the LL.
        
        //Can we do better?
        //Apparent pattern: Fast & slow pointers.
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
*/
class Solution {
    public ListNode middleNode(ListNode head) {
        //Fast and slow pointers
        //Since we have a pointer that moves twice as fast as the slow pointer, it should
        //hit the end as we reach the middle.
        //Question: Do we need to move the slow pointer 1 extra if we have an even number of nodes?
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        while(fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        //Answer: No.
            // 1, 2, 3
            // Fast moves to 3, slow to 2.
            // Fast is not null (but next is), middle is 2. Ok.
            // 1, 2, 3, 4
            // Fast moves to 3, slow to 2.
            // Fast is not null, fast next is not null. Fast moves 2 spaces to null, slow to 3.
            // Actually then, we don't need to do this in either case, because then we'd move slow to 4.
        return slowPointer;
    }
}