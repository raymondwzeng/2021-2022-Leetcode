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
    fun length(head: ListNode?): Int {
        //O(n) time operation to find the length of the linkedlist
        var length = 0
        var runner = head
        while(runner != null) {
            length++
            runner = runner?.next
        }
        return length
    }
    
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if(head == null) return null
        val listLength = length(head)
        if(k % listLength == 0) return head
        //Number of rotations will be k mod length
        /*
            Basic solution:
                - Initialize k % length references
                - Move them all k % length steps
                - Slowly set the next pointer of each to the head.
                
            There should be a solution where we just pick a specific node, set that next pointer to the head (and set the previous next to null)
            The head of our returned list is length - (k % length)
                - We go until we reach that node
                - Send out a second runner to reach the end of that section
                - Set the tail's next (2nd runner) to the head of the linked list
                - Set the previous from the node next value to null (previous as tail)
                - Return the new head of linked list
                
            O(n) operation
        */
        var numMoves = listLength - (k % listLength)
        var slow: ListNode? = null
        var fast = head
        for(nodeIndex in 0..numMoves - 1) {
            if(fast?.next != null) { //Edge case handling for when the k % length == length - 1, fast -> null, slow -> end, we need a ref to the prev
                fast = fast?.next
                if(slow == null) slow = head else slow = slow?.next
            }
        }
        //Slow is at our new tail, fast is at the start of our new head section
        slow?.next = null
        var runner = fast
        while(runner?.next != null) { //Go to the last node in the chain before we hit null
            runner = runner?.next
        }
        runner?.next = head
        return fast
    }
}