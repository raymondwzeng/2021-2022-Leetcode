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
    
    fun reverseLinkedList(head: ListNode?): ListNode? {
        if(head == null) return null
        if(head.next == null) return head
        
        var slow = head
        var fast = head.next
        slow.next = null
        while(fast != null) {
            //[1 -> 2 -> 3]
            var temp = fast.next // 3 
            fast.next = slow // 1 -> 2 to 2 -> 1
            slow = fast // 1 to 2
            fast = temp // 2 to 3
        }
        return slow
    }
    
    
    fun middleNodeOfLinkedList(head: ListNode?): ListNode? {
        if(head == null) return null
        
        var slow = head
        var fast = head
        while(fast != null && fast.next != null) {
            slow = slow?.next
            fast = fast.next.next
        }
        return slow
    }
    
    fun isPalindrome(head: ListNode?): Boolean {
        //Use 2 pointers (fast and slow) to find the middle of the linked list
        //Reverse the second half of the linked list
            //So that when we use 2 pointers for the third time, we can check each element, and return false if they differ
        if(head == null || head.next == null) return true
        val middle = middleNodeOfLinkedList(head)
        var regularHead = head
        var reversedHead = reverseLinkedList(middle)
        while(regularHead != null && reversedHead != null) {
            if(regularHead.`val` != reversedHead.`val`) return false
            regularHead = regularHead.next
            reversedHead = reversedHead.next
        }
        return true
    }
}