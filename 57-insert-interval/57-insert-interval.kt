class Solution {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        //Goal: Insert the new interval such that the resulting intervals covers the entire space, without overlap.
        //Also: Merge as many intervals as possible
        /*
            for each interval in intervals
                -> Check to see if the array can fit within any interval
                    -> Check first the 0th index to see if interval[0] <= newInterval[0]
                    -> If interval[1] >= newInterval[1], just return intervals (complete overlap)
                    -> If inverval[1] < newInterval[1], then we need to start checking the other intervals
                        -> Checking the rest of the intervals, what are we looking for?
                        -> Mainly a upper bound?
                            -Fact: Each interval beyond our initial lower bound will have a start > start (current lower bound)
                            -If that intNext[0] > newInterval[1], then that means that we don't have extension beyond our current interval, therefore increase the bounds, and return.
                            -If intNext[0] == newInterval[1], then the bounds become (interval[0], intNext[1])
                            
                            -How do we remove unnecessary intervals, in that case?
                            We could start off by just copying the values whenever necessary, and ignoring everything else
                            If we go over an interval, add to the array. Once we insert our new interval(s), then add the rest in.
                            Greedy solution, O(n) space and linear(?) runtime
        */
        val intervalList = mutableListOf<IntArray>()
        var index = 0
        var leftIntervalIndex = -1
        var hasInserted = false
        while(index < intervals.size) {
            if(intervals[index][1] >= newInterval[0]) {
                when {
                    intervals[index][0] <= newInterval[0] && intervals[index][1] >= newInterval[1] -> return intervals
                    intervals[index][0] <= newInterval[0] && intervals[index][1] < newInterval[1] && leftIntervalIndex == -1 -> {
                        //Don't add current interval.
                        leftIntervalIndex = index
                    }
                    intervals[index][0] > newInterval[1] && !hasInserted -> {
                        //Add current interval (because it does not overlap)
                        //Set to -1 to avoid adding additional copies in the future
                        if(leftIntervalIndex == -1) {
                            intervalList.add(newInterval)
                        } else {    
                            intervalList.add(intArrayOf(intervals[leftIntervalIndex][0], newInterval[1]))
                        }
                        intervalList.add(intervals[index])
                        hasInserted = true
                    }
                    (intervals[index][0] == newInterval[1] || intervals[index][1] >= newInterval[1]) && !hasInserted -> {
                        val upperBound = if(newInterval[1] > intervals[index][1]) newInterval[1] else intervals[index][1] 
                        //Don't add current interval (need to merge)
                        if(leftIntervalIndex == -1) {
                            intervalList.add(intArrayOf(newInterval[0], upperBound))
                        } else {    
                            intervalList.add(intArrayOf(intervals[leftIntervalIndex][0], upperBound))
                        }
                        hasInserted = true
                    }
                    index == intervals.size - 1 && intervals[index][1] < newInterval[1] -> {
                        val lowerBound = if(leftIntervalIndex != -1 && intervals[leftIntervalIndex][0] < newInterval[0]) intervals[leftIntervalIndex][0] else newInterval[0]
                        intervalList.add(intArrayOf(lowerBound, newInterval[1]))
                        hasInserted = true
                    }
                    hasInserted == true -> {
                            intervalList.add(intervals[index])
                        }
                    }
            } else {
                intervalList.add(intervals[index])
            }
            index++
        }
        if(!hasInserted) {
            if(leftIntervalIndex == -1) intervalList.add(newInterval) else intervalList.add(intArrayOf(intervals[leftIntervalIndex][0], newInterval[1]))
        }
        val returnArray = Array(intervalList.size) { i -> intervalList[i]}
        return returnArray
    }
}