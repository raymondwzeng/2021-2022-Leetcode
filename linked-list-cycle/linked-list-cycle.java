/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
//O(n) time, O(n) space. 
//Runs through the entire LL, if it finds no cycles, otherwise we'll pick up on that.
public class Solution {
    public boolean hasCycle(ListNode head) {
        HashMap<ListNode, Boolean> map = new HashMap<ListNode, Boolean>();
        while(head != null && head.next != null) {
            if(map.get(head) != null) {
                return true;
            } else {
                map.put(head, true);
            }
            head = head.next;
        }
        return false;
    }
}