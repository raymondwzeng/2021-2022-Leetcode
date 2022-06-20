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
        //Start by determining a head node by checking the lesser of the two nodes. If both are null, return null.
        if(list1 == null && list2 == null) return null
        lateinit var head : ListNode
        lateinit var headRunner : ListNode
        var runner1 = list1
        var runner2 = list2
        if((list1 != null && list2 == null) || (list1 != null && list1.`val` <= list2!!.`val`)) {
            head = list1!!
            runner1 = runner1!!.next
        } else {
            head = list2!! //We can do this because we already know from above that list2 can't be null.
            runner2 = runner2!!.next
        }
        headRunner = head
        
        //While both of the runners are not null, check both of them and see if one is less than the other. If yes, add that one to the list tail.
        while(runner1 != null && runner2 != null) {
            if(runner1.`val` <= runner2.`val`) {
                headRunner.next = runner1
                runner1 = runner1.next
            } else {
                headRunner.next = runner2
                runner2 = runner2.next
            }
            headRunner = headRunner.next //Go to newly added node as the new "tail"
        }
        //One of em is null at this point, finish with the rest.
        if(runner1 != null) {
            while(runner1 != null) {
                headRunner.next = runner1
                runner1 = runner1.next
                headRunner = headRunner.next
            }
        } else if (runner2 != null) {
            while(runner2 != null) {
                headRunner.next = runner2
                runner2 = runner2.next
                headRunner = headRunner.next
            }
        }
        return head
    }
}