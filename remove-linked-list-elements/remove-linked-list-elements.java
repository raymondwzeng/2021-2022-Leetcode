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
    public ListNode removeElements(ListNode head, int val) {
        //Okay, so they want us to remove ALL.
        //We could probably use a lagging pointer in this case.
        ListNode runner = head;
        ListNode laggingRunner = null;
        while(runner != null) {
            if(runner.val == val) {
              if(runner == head) { //Case: Head is the value
                 head = runner.next;
              } else {
                  ListNode forwardRunner = runner.next;
                  while(forwardRunner != null && forwardRunner.val == val) {
                      forwardRunner = forwardRunner.next;
                  }
                 laggingRunner.next = forwardRunner; //We send the previous next to the next next, dropping reference of current runner. 
              }
            } 
            laggingRunner = runner;
            runner = runner.next;
        }
        return head;
        
        /**
        Simulation: [7, 7, 7, 7], val is 7.
        
        Check value: 7 @ head. laggingRunner is null, so we set the head to next.
        New value: [_, 7, 7, 7], with head @ 1, runner @ 1, laggingRunner @ 0. -> Question: How do we make laggingRunner not 0?
        
        Check value: 7 @ runner. laggingRunner is not null at this point, so we set its next to 2.
        New value: [_, _, 7, 7], with head @ 2, runner @ 2, laggingRunner @ 1.
        
        Check value: 7 @ runner. laggingRunner @ 1 != null, so laggingRunner.next = 3;
        New value: [_, _, _, 7], with head @ 3, runner @ 3, laggingRunner @ 2.
        
        Check value: 7 @ runner. laggingRunner @ 2 != null, so laggingRunner.next = null;
        New value: [_, _, _, _], with head @ null, runner @ null, laggingRunner @ 3.
        
        //Failed attempt 1: [1, 2, 2, 1] val 2, since we don't account for the fact that the new next could also be a value.
        //What if we made a forward runner?
            -> Create a forward runner. If it becomes null, then oh well, that means that we've either reached the end, or all were the val.
        Failed attempt 2: [5, 4, 3, 2, 1, 1] val 1. Seems like we forgot one?
            -> Runner @ 1, then laggingRunner @ 2. Then runner == 1, so we set forwardRunner to its next (1).
            -> forwardRunner is not null, but forwardRunner.next is. Drop that clause?
        */
    }
}