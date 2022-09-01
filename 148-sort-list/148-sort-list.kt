/* Good attempt.
        /*
            Naive solution
            - Selection sort with 2 pointers
            O(n^2) time, O(1) space
            
            - Move everything into a list, sort that list, then override each node
            O(nlogn) time, O(n) space
            
            Quicksort type of thing with the list?
            - Choose the first element as a pivot (personal preferrence, may not be optimal)
            - For each element afterwards, either move it as the next item of the head or set it as the new head
            - Recurse on the left and right sides of the list
                -> How do we know which side is which?
                -> Keep track of the array/window ourselves
            O(nlogn) time, O(1) space
            
            HeapSort with 2 pointers
            3 pointers -> Runner, left, right
            while(runner > left || runner > right)
                if the runner > left, swap
                if the runner > right, swap
            Move the runner up, and the left/right according to their index (which we also keep)
                
            We might need a slower reference to the prev of left and right, and maybe runner in order to fix the list.
            
            L4r -> 2l -> 1R -> 3 r = runner, l = left, R = right, L = previousLeft
            2l -> L4r -> 1R -> 3 (Swap the runner and the left side after the nodes swapped)
            2r -> L4l -> 1R -> 3 (Swap the runner with the right side) //If we swap it to the left value, keep, otherwise if we swap out, reset to new
            1R -> 4l -> 2r -> 3 (Make sure that there exists a previous of the left, in order to set up the proper next values)
            1r -> 4l -> 2R -> 3 (Swap the runner and the right pointer again)
            1 -> 4r -> 2l -> 3R
            1 -> 2l -> 4r -> 3R (We actually need a pointer to the previous of the runner)
            
            
            -1r -> 5l -> 3R -> 4 -> 0
            -1 -> 5r -> 3 -> 4l -> 0R
            -1 -> 4 -> 3 -> 5 -> 0
            -1 -> 0r -> 3 -> 5l -> 4R
            -1 -> 0 -> 3r -> 5 -> 4 //If we can't move further than our last element, then we just use the last 2 elements?
            
        */
        if(head == null) return null
        if(head.next == null) return head
        var newHead = head
        var lagCurrent : ListNode? = null
        var current = head
        var lagMin : ListNode? = null
        var runner = current.next
        while(current?.next != null) {
            var min = current
            runner = current?.next
            while(runner != null) {
                println("Runner found ${runner.`val`}")
                if(runner.next != null && runner.next.`val` < (min?.`val` ?: 0)) {
                    lagMin = runner
                    min = runner.next
                }
                runner = runner.next
            }
            println("lagCurrent: ${lagCurrent?.`val`}, current: ${current?.`val`}, Temp: ${current?.next?.`val`}, lagMin: ${lagMin?.`val`}, min: ${min?.`val`}")
            val temp = current?.next
            if(min != current) {
                lagCurrent?.next = min
                lagMin?.next = current
                current?.next = min?.next
                min?.next = temp
                if(current == newHead) newHead = min //Change the min to the new head if needed
            }
            lagCurrent = current
            current = temp
            println("lagCurrent: ${lagCurrent?.`val`}, current: ${current?.`val`}")
        }
        return newHead
*/
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
    fun merge(list1: ListNode?, list2: ListNode?): ListNode? {
        var newHead: ListNode? = null
        var newTail: ListNode? = null
        var runner1 = list1
        var runner2 = list2
        while(runner1 != null && runner2 != null) {
            if(runner1.`val` < runner2.`val`) {
                val temp = runner1?.next
                if(newHead == null) {
                    newHead = runner1
                    newTail = runner1
                } else {
                    newTail?.next = runner1
                    newTail = runner1
                    newTail?.next = null
                }
                runner1 = temp
            } else {
                val temp = runner2?.next
                if(newHead == null) {
                    newHead = runner2
                    newTail = runner2
                } else {
                    newTail?.next = runner2
                    newTail = runner2
                    newTail?.next = null
                }
                runner2 = temp    
            }
        }
        if(runner1 == null && runner2 != null) {
            while(runner2 != null) {
                val temp = runner2?.next
                newTail?.next = runner2
                newTail = runner2
                newTail?.next = null
                runner2 = temp
            }
        } else if (runner1 != null && runner2 == null) {
            while(runner1 != null) {
                val temp = runner1?.next
                newTail?.next = runner1
                newTail = runner1
                newTail?.next = null
                runner1 = temp
            }
        }
        return newHead
    }
    
    fun getMiddleNode(head: ListNode?): ListNode? {
        var root = head
        var runner = head?.next?.next
        while(runner != null) {
            root = root?.next
            runner = runner.next?.next
        }
        return root
    }
    
    /*
        Mergesort makes the most sense for a LinkedList.
    */
    fun sortList(head: ListNode?): ListNode? {
        if(head == null || head.next == null) return head
        // println("Head: ${head.`val`}")
        val middle = getMiddleNode(head)
        // println("Middle: ${middle?.`val`}")
        val middleNext = middle?.next
        middle?.next = null
        val left = sortList(head)
        val right = sortList(middleNext)
        return merge(left, right)
    }
}