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
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        /*
            Two pointers-like problem
            
            2 pointers -> Actual pointer, Fast Pointer
            Fast pointer -> Should be n + 1 steps ahead of actual pointer
                i.e if n == 2, then we should have actual.next.next.next == fast
            Actual pointer -> Moving at normal speed, and represents the node before the one that we actually want to remove
            
            O(n) time, O(1) space
        */
        if(head == null) return null
        var fast = head
        var actual = head
        var index = 0
        for(i in 0..n - 1) {
            if(fast?.next != null) {
                fast = fast?.next
                index++
            }
        } //Fast is either at some node in the middle of our list, OR at the end of our list (because of the constraints)
        if(fast?.next == null && index < n) { //We wanted to jump more than we could, basically we want to remove the head element
                return head?.next
        }
        while(fast?.next != null) { //Move until fast is at the end of the list
            fast = fast?.next
            actual = actual?.next
        }
        actual?.next = actual?.next?.next
        return head
    }
}