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
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        when(list1) {
            null -> return list2 ?: null
        }
        when(list2) {
            null -> return list1 ?: null
        }
        //At this point, we can expect that both list1, list2 are not null (contain at least 1 element)
        var head : ListNode? = null;
        var runnerHead : ListNode? = head
        var runner1 : ListNode? = list1
        var runner2 : ListNode? = list2
        
        while(runner1 != null && runner2 != null) {
            if(runner1.`val` < runner2.`val`) {
                if(head == null) {
                    head = runner1
                    runnerHead = head
                } else {
                    runnerHead!!.next = runner1
                    runnerHead = runnerHead.next
                }
                runner1 = runner1.next
            } else {
                if(head == null) {
                    head = runner2
                    runnerHead = head
                } else {
                    runnerHead!!.next = runner2
                    runnerHead = runnerHead.next
                }
                runner2 = runner2.next  
            }
        } //Combine until one or both have been exhausted
        
        while(runner1 != null && runner2 == null) {
            runnerHead!!.next = runner1
            runner1 = runner1.next
            runnerHead = runnerHead.next
        }
        while(runner2 != null && runner1 == null) {
            runnerHead!!.next = runner2
            runner2 = runner2.next
            runnerHead = runnerHead.next
        }
        
        return head
    }
}