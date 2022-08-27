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
    fun oddEvenList(head: ListNode?): ListNode? {
        /*
            O(1) space, O(n) time => Two pointers solution
            
            Runner pointer (starts as head.next), regular pointer (starts as head)
            
            If 0, 1 or 2 nodes, return the head (0/1 nodes is no change, 2 nodes is already organized)
            
            3 nodes
            
            1 -> 2 -> 3
            
            Start the runner at the head.next, regular at head (start at head because of ref to the previous)
            - While the runner's next is not null (or maybe while runner itself is not null)
                - Use a temp val to hold reference to runner's next
                - Set regular's next = runner's next
                - Set runner next to temp next
                - Set temp next to evenListHead, which is initialized as runner
                - Move the runner forward and the regular
                
            1r -> 2R -> 3 -> 4 -> 5 where r = regular, R = runner, temp = 3
            1r -> 3 -> 4 -> 5, 2R -> 3 temp = 3
            1r -> 3 -> 4 -> 5, 2R -> 4
            1r -> 3 -> 2R -> 4 -> 5
            (End of first iteration)
            1 -> 3r -> 2 -> 4R -> 5 temp: 5
            1 -> 3r -> 5, 2 -> 4R temp: 5
            1 -> 3r -> 5 -> 4R, we lose 2 -> What if we hold a reference to the "head" of the even list, which is what we keep?
            
            Hold a reference to the evenListHead
            2r -> 1R -> 3 -> 5 -> 6 -> 4 -> 7, temp = 3, eLH = 1
            2 -> 3 -> 5 -> .., 1R -> 3
            2 -> 3 -> 5 -> .., 1R -> 5
            2 -> 3 -> 1 -> 5 -> ..,
            (Round 2)
            2 -> 3r -> 1 -> 5R -> 6 -> 4 -> 7, temp = 6
            2 -> 3r -> 6 -> .., 1 -> 5R
            2 -> 3r -> 6 -> .., 1 -> 5R -> 4
            2 -> 3r -> 6 -> 1 -> 5R -> 4 -> 7
            (Round 3)
            2 -> 3 -> 6r -> 1 -> 5 -> 4R -> 7 temp = 7
            2 -> 3 -> 6r -> 7, 1 -> 5 -> 4R -> 7
            2 -> 3 -> 6r -> 7, 1 -> 5 -> 4R -> NULL
            2 -> 3 -> 6r -> 7 -> 1 -> 5 -> 4R
            
            1r -> 2R -> 3 -> 4 temp = 3
            1r -> 3, 2R -> 4
            1r -> 3, 2R -> 4 (no effect runner.next = temp?.next)
            1r -> 3 -> 2R -> 4
            (Round 2)
            1 -> 3r -> 2 -> 4R temp = null
            1 -> 3r ->  //Probably setting null prematurely
        */
        if(head == null || head.next == null || head.next.next == null) return head
        var runner = head.next
        var regular = head
        val evenListHead = runner
        while(runner != null && runner.next != null) {
            val temp = runner.next
            regular?.next = temp
            runner.next = temp?.next
            temp?.next = evenListHead
            runner = runner.next
            regular = regular?.next
        }
        return head
    }
}