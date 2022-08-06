class Solution {
    
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        /*
            When comparing intervals a and b, there are a few possible cases:
                a[0] < b[0] and a[1] < b[0] -> a < b completely
                a[0] < b[0] and a[1] >= b[0] -> a overlaps with b, result should be a[0], max(a[1], b[1])
                a[0] > b[0] and a[1] < b[1] -> a is covered by b, result should be b[0], b[1] //Same case if we swap a and b around
                b[0] < a[1] and b[1] >= a[1] -> b slightly covers end of a, result should be a[0] and max(a[1], b[1])
                b[0] > a[1] and b[1] > a[1] -> a < b completely
                
            Initial solution:
                -> Run passes through the array until no "covering" flag is raised
                    -> Compare the first item with the next item, and if they overlap (according to our rules above), merge them
                    -> Raise the flag if we do merge
                n intervals, then n - 1 intervals, n - 2, so on...1 -> n(n-1)/2 passes = O(n^2)
                
            ? -> Are the intervals sorted by start time?
                -> We could do something similar to our insert interval, where we basically merge all of the valid intervals together
            
            If yes, we could potentially finish in one pass (O(n))
            If not, we need to first sort, then run the algorithm O(nlogn)
        */
        val intervalsList = mutableListOf<IntArray>()
        intervals.sortBy { it[0] }
        
        var index = 0
        while(index < intervals.size) {
            when {
                (index == intervals.size - 1) || (intervals[index][0] < intervals[index + 1][0] && intervals[index][1] < intervals[index + 1][0]) -> { //No overlap case
                    // println("Left: ${intervals[index][0]}, Right: ${intervals[index][1]}")
                    intervalsList.add(intervals[index])
                }
                intervals[index][0] > intervals[index + 1][0] && intervals[index][1] < intervals[index + 1][1] -> { //Covering case
                    //Don't do anything for now, because the next interval will cover it no matter what
                }
                intervals[index][1] >= intervals[index + 1][0] -> {
                    val left = minOf(intervals[index][0], intervals[index + 1][0])
                    var right = intervals[index][1]
                    var foundLocation = false
                    var i = index + 1
                    while (!foundLocation && i < intervals.size) {
                        if(right < intervals[i][0]) {
                            foundLocation = true
                        } else {
                            index++
                            right = maxOf(right, intervals[i][1])
                        }
                        i++
                    }
                    // println("Left: $left, Right: $right")
                    intervalsList.add(intArrayOf(left, right))
                }
            }
            index++
        }
        
        return Array<IntArray>(intervalsList.size) { index -> IntArray(intervalsList[index].size) { innerIndex ->
                intervalsList[index][innerIndex]
            }
        }
    }
}