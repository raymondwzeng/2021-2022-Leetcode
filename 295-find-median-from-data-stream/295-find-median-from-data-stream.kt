class MedianFinder() {
    
    /*
        First LC problem done that incorporates the 2 heaps pattern!
        
        The core idea: If we have two heaps, one min and one max, then we know for sure what the median is because we have the maxHeap for max of LHS, and minHeap for min of RHS. Calculate the median, and if we need to insert, use that as our metric to determine the side we insert into.
    */
    
    class MaxHeapComparator: Comparator<Int> {
        override fun compare(a:Int, b: Int): Int {
            return when { //Determined by a - b, but since we want maxHeap, reverse the result
                a == b -> 0
                a < b -> 1
                else -> -1
            }
        }   
    }
    
    val maxHeap = PriorityQueue<Int>(MaxHeapComparator())
    val minHeap = PriorityQueue<Int>()

    fun addNum(num: Int) {
        if((maxHeap.size == 0 && minHeap.size == 0) || num >= findMedian()) {
            if(minHeap.size <= maxHeap.size) {
                minHeap.add(num)
            } else {
                maxHeap.add(minHeap.poll())
                minHeap.add(num)
            }
        } else {
            if(maxHeap.size <= minHeap.size) {
                maxHeap.add(num)
            } else {
                minHeap.add(maxHeap.poll())
                maxHeap.add(num)
            }
        }
    }

    fun findMedian(): Double {
        if(maxHeap.size == minHeap.size) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0
        } else if(minHeap.size > maxHeap.size) {
            return minHeap.peek().toDouble()
        } else {
            return maxHeap.peek().toDouble()
        }
    }

}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */