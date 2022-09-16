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
    /*
        Reverses the linked list. When called on a half, splits the list in half.
    */
    fun reverseLinkedList(head: ListNode): ListNode? {
        var current: ListNode? = head
        var slow: ListNode? = null
        while(current != null) {
            val tempNext = current.next
            current.next = slow
            if(slow == null) slow = head else slow = current
            current = tempNext
        }
        return slow
    }
    
    /*
        Finds the middle of the linked list to reverse from
    */
    fun middleLinkedList(head: ListNode): ListNode {
        var slow = head
        var fast: ListNode? = head
        while(fast != null && fast.next != null) {
            slow = slow.next
            fast = fast.next?.next
        }
        return slow
    }
    
    fun reorderList(head: ListNode?): Unit {
        /*
            LinkedList -> Two Pointer Approach?
            
            1 -> 2 -> 3 -> 4 [1 stays in-place]
            
            The problem with SLL is that there isn't an easy solution
            
            1L -> 2 -> 3R -> 4 //Skip the head of the list
            1 -> 2L -> 3 -> 4R //Swap 2 and 4
            1 -> 4 -> 3L -> 2 (R=null), Swap the last 2 elements?
            1 -> 4 -> 2 -> 3
            
            1 -> 2 -> 3 -> 4 -> 5 //Split the linked list in 2, reverse the other side
            
            //2 pointer approach to recombine in-place
            
            1 -> 2 -> 3 <- 4 <- 5
            
            Keep temp pointer on the next of the reverse head, and temp pointer on the next of the original head (2, 4)
            Given 2 heads, we start by setting the next of the original to the head of the reverse (1 -> 5)
            Set the head of the reverse's next to the original temp next (5 -> 2)
            Set head to the original temp next (head: 2)
            Set head of the reverse to the reverse temp next (reverse: 4)
            
            2 -> 4
            4 -> 3
            head: 3
            reverse: 3 //While head != reverse
            
            1 -> 2 -> 3 <- 4
            
            1 -> 4
            4 -> 2
            Head: 2
            Reverse: 3
            
            2 -> 3
            3 -> 3 //While head.next != reverse (not a big deal)
            Head: 3
            Reverse: 3
            
            1 -> 5 -> 2 -> 4 -> 3
            
            
            Result: 1 -> 5 -> 2 -> 4 -> 3 //1st element moves 0, 2nd element moves 1, 3rd element moves 2 times
            Middle of the linked list? => O(n) time
            Reverse the linked list => O(n) time
            Moving the pointers around => O(n) time
            
            Takes O(n) time, O(1) space
        */
        if(head == null || head.next == null || head.next.next == null) return
        var originalHead = head //ListNode? looks like Kotlin still says nullable
        var reverseHead = reverseLinkedList(middleLinkedList(head)) //ListNode?
        while(originalHead != reverseHead) {
            val tempOriginalNext = originalHead?.next
            val tempReverseNext = reverseHead?.next
            originalHead?.next = reverseHead
            reverseHead?.next = tempOriginalNext
            originalHead = tempOriginalNext
            reverseHead = tempReverseNext
        }
    }
}