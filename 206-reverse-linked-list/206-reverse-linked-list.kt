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
    fun reverseList(head: ListNode?): ListNode? {
     /*
        Two pointer solution!
        
        Have a forward runner and a standard runner, then swap them
    */
        if (head == null) return null

        var slowPointer : ListNode? = null
        var fastPointer = head
        
        while(fastPointer != null) {
            var temp = fastPointer.next //We'll lose the ref to this if we don't save it
            fastPointer.next = slowPointer //Reverse the direction of the next
            slowPointer = fastPointer //Move up slow
            fastPointer = temp //Move up fast
        }
        
        return slowPointer
    }
}