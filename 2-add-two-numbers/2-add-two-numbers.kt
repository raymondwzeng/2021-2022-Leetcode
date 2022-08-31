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
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        //No leading zeroes
        //Each node in the linked list represents a digit in a number
        //Return the sum as a linked list as well
        /*
            Straightforward approach 
            - Build up both of the numbers, and return the newly built linked list 
            - Start with 2 pointers at each of the numbers
            - For each node you traverse until the end, multiply existing number by 10 and add digit
            
            O(n) time, and O(n) space, where n is the number of nodes in the longer list
            
            Problem: If we need to carry over the sum to the next digit -> Add a carry amount, and at the end potentially add a new node
            -> Would we only need to add 1 node at most with a sum?
            
            Potentially reduce to O(n) time, and O(1) space by reusing one of the lists
            
            Different approach
            - Start by reversing both of the lists (O(n) time) --> This would also allow us to know which list is longer, and use that list as our output
            - Keep track of a carry amount, and start traversing the lists backwards, setting the longer list's value to the sum of both as well as the carry amount
                -> If carry > 9, then value = carry % 10, carry / 10 else carry = 0
            - Continue until we reach the end
            - If the carry amount != 0 at the end, add a new node with the amount left in carry as a tail -> Entire traversal takes O(n) time
            - Reverse the list one more time -> O(n) time
            
            Less efficient in time vs space compared to the straightforward approach because we need to reverse the list twice O(3n) vs O(2n)
            BUT constant multiples don't matter, and we save space
            
            So we don't need to actually reverse the lists intially (read the problem wrong LOL), we can just reverse the final list at the end
            
            Revised approach
            - Step 0 -> Figure out which of the lists are longer (if either of them are) --> O(n) time
            - Two pointers to add the numbers, do the carry stuff -> New problem, we don't know which list is longer without traversing through them once -> O(n) time
            
            O(2n) time, O(1) space
        */
        if(l1 == null && l2 == null) return null //Edge case, won't happen in LC because of constraints
        var carry = 0
        val originalListHead = l1 ?: l2 //We want to just move the sum to list1, no matter what
        var listHead = originalListHead
        var otherListHead = l2 ?: l1
        
        while(listHead != null) {
            val sum = (listHead?.`val` ?: 0) + (otherListHead?.`val` ?: 0) + carry
            if(sum > 9) {
                listHead?.`val` = sum % 10
                carry = sum / 10
            } else {
                listHead?.`val` = sum
                carry = 0
            }
            if(otherListHead?.next != null && listHead?.next == null) listHead?.next = ListNode(0, null)
            if(otherListHead?.next == null && listHead?.next == null && carry > 0) {
                listHead?.next = ListNode(carry, null)
                carry = 0
            }
            listHead = listHead?.next
            otherListHead = otherListHead?.next
        }
        
        return originalListHead
    }
}