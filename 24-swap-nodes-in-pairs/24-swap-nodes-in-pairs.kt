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
    fun swapPairs(head: ListNode?): ListNode? {
        //2 pointers approach
        /*
         1 -> 2 -> 3 -> 4 (Swap 1.next and 2.next)
         1 -> 3 -> 4 -> null, 2 cycles itself (if swapped next is itself, instead set next to original head, then set node as head?)
         2 -> 1 -> 3 -> 4 -> null, move pointers up twice, and again (swap 3.next and 4.next)
         2 -> 1 -> 3 -> null, 4 cycles itself (set next to original pointer) "head", keep a reference to the previous tail?
         2 -> 1 -> 4 -> 3
         
         It seems like what we really want is to reverse every pair of nodes
         
         Have a faster pointer, and a regular pointer
         While faster pointer is not null
            First, set a value for the "head" of the next pair of nodes
            Set the faster pointer's next to regular
            Set the regular pointer's next to the old ref
            If regular pointer happened to be head, then set head to faster pointer
            Regular pointer = regular.next
            Faster pointer = regular.next.next
            
        1 -> 2 -> 3 -> 4 -> n, r = 1, f = 2, c = 3, s = null
        2 -> 1 -> 3 -> 4 -> n, r = 3, f = 4, c = null, s = 1
        2 -> 1 -> 4 -> 3 -> n
        
        Runs in O(n) time, O(1) space where n = # of nodes in our list
        */
        if(head == null) return null
        
        var newHead = head
        var regular = head
        var fast = head?.next
        // var slow: ListNode? = null //Slow is the tail of our previous pair, we need this so that we can set the correct next reference to the head of the next pair.
        
        while(fast != null) {
            val coldReference = fast?.next
            fast?.next = regular //Reverse the list on a pair level
            regular?.next = if(coldReference != null && coldReference.next == null) coldReference else coldReference?.next
            // if(slow != null) slow.next = fast
            if(regular == head) newHead = fast //Update the head if needed, should only happen once
            // slow = regular
            regular = coldReference //Update the pointers
            fast = coldReference?.next
        }
        
        return newHead
    }
}