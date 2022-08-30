class Solution {
    fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
        /*
            Integer array, an integer x
                Output: List (size K) of integers which (in ascending order) are closest to x
                
            n (# of elements) <= 10^4 -> O(n) or O(nlogn) solution
            
            Brute force solution:
                - Sort the entire array -> O(nlogn)
                - Find the element within the array (binary search) -> O(logn)
                - 2 pointers approach -> O(k)
                    -> If |arr[left] - x| < |arr[right] - x|
                        - Add left to the list
                        - Move left down 1 index
                        - Continue until you have k elements within the array
                    - Else do the same for the right side, adding and moving up
                Return the list
                
            O(nlogn) + O(logn) + O(k) time, O(1) space
                
            Main question: Do we need to actually sort the entire initial list?
            
            O(n) to find the element -> Let that be the pivot
            Problem with this approach is that we don't really know how close each element is to x, only that it is < or >
            
            Heaps?
            2 heaps
                - Root for both minHeap and maxHeap to be x
                - If the value > x, then add it to the minHeap, else maxHeap -> O(nlogn)
                - Pop both of the roots
                - For 0 until k - 1, compare the tops of each heap, if the abs value < other, then add and poll -> O(klog(n/2))
                
            O(nlogn) + O(klog(n)) time, O(n) space
        */
        arr.sort() //In-place sorting, in O(nlogn) time
        var xIndex = arr.binarySearch(x) //Binary search O(logn) for the element or where it should be
        var left = xIndex - 1
        var right = xIndex
        if(xIndex < 0) {
            xIndex = xIndex.inv() + 1
            left = xIndex - 2
            right = xIndex - 1
        }
        val returnList = LinkedList<Int>()
        if(k == arr.size) return arr.toList() //If we have k closest elements where k == arr.size, we need to include all of them anyways
        while(returnList.size < k) {
            if(left >= 0 && right < arr.size) {
                if(Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                    returnList.offerFirst(arr[left--]) //Covers both < and =, because we want to bias towards the left index
                } else {
                    returnList.offerLast(arr[right++])
                }
            } else if(left >= 0 && right >= arr.size) { //Left is not null
                if(left >= arr.size) left = arr.size - 1 //If x > all elements, then xIndex = arr.size
                while(left >= 0 && returnList.size < k) {
                    returnList.offerFirst(arr[left--])
                }
            } else { //Right is not null
                if(right <= -1) right = 0 //If x < all elements, then xIndex = -1
                while(right < arr.size && returnList.size < k) {
                    returnList.offerLast(arr[right++])
                }
            }
            if(left < 0 && right >= arr.size) return returnList //Circuit breaker in case we don't have enough items
        }
        return returnList
    }
}