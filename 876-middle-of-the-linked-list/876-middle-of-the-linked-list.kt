/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun middleNode(head: ListNode?): ListNode? {
        //Two pointers pattern
        var slowPointer = head
        var fastPointer = head
        
        while(fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer?.next
            fastPointer = fastPointer?.next?.next
        }
        
        return slowPointer
    }
}