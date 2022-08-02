class Solution {
    class Point(val coordinate : IntArray) {
        val distance get() = Math.sqrt(Math.pow(this.coordinate[0].toDouble(), 2.0) + Math.pow(this.coordinate[1].toDouble(), 2.0))
        
        companion object PointComparator : Comparator<Point> {
            override fun compare(a: Point, b: Point): Int {
                return when {
                    a.distance == b.distance -> 0
                    a.distance < b.distance -> -1
                    else -> 1
                }
            }
        }
    }
    
    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        //The most straightforward approach is a heap/priority queue of some sort
        //Since this is against 0,0, the dist formula is a bit easier
        //Corner case -> If k == points.size return points
        /*
            Create an array of size k
        
            For each point in points -> O(n) operation
                Create a new point in the heap, where the min heap is determined by distance -> O(logn)
            Initial heapify -> O(nlogn)
            
            From 0 to k - 1 -> O(k) operations
                Draw elements from the min heap, and add them to the array -> O(logn)
            Drawing elements -> O(klogn)
            
            Total: O(nlogn) + O(klogn) -> Since k <= n, then O(nlogn)
        */
        
        if(k == points.size) return points
        val closestPoints: Array<IntArray> = Array<IntArray>(k) { IntArray(2) }
        val minHeap = PriorityQueue<Point>(k, Point.PointComparator)
        
        points.forEach { point -> 
            minHeap.add(Point(point))
        }
        
        for(i in 0..k-1) {
            closestPoints[i] = minHeap.poll().coordinate
        }
        
        return closestPoints
    }
}