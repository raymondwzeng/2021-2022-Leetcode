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
    fun hasCycle(head: ListNode?): Boolean {
        //The standard solution is using Floyd's fast and slow (tortoise and hare) pointers. If we have a pointer moving at double the speed of another, and a cycle exists within the linked list, then there will come a point where the two pointers converge. 
        if(head == null) return false //Edge case: Null head
        var slowPointer = head
        var fastPointer = head.next
        while(slowPointer != null && fastPointer != null) { //If either one of them is null, then that means that a cycle does not exist. The fastPointer should reach null first if LL is acyclic.
            if(slowPointer == fastPointer) return true //Trigger condition: If the fast pointer wraps around and is equal to the slow, a cycle exists.
            slowPointer = slowPointer.next
            fastPointer = fastPointer.next?.next //We don't know if the next is null, so null check there.
        }
        return false
    }
}