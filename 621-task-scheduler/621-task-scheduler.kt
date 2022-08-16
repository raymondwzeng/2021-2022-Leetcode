class Solution {
    class IntCompareReverse(): Comparator<Int> {
        override fun compare(a: Int, b: Int): Int {
            return when {
                a == b -> 0
                a > b -> -1 
                else -> 1
            }
        }
    }
    
    fun leastInterval(tasks: CharArray, n: Int): Int {
        /*
            Corner case: If n == 0, return tasks.size
            
            Task length -> 10^4 -> O(t) time, where t is the number of tasks in the array
            
            Would a greedy solution work?
                - Split the input array into arrays of individual tasks, separated by character
                - Try to balance the tasks by always picking task of larger amount, followed by different task?
            
            [a, a, a, a, a, a, b, c, d, e, f, g]
            a -> b -> a -> c (doesn't work)
            *We have to take the cooldown regardless
            
            If we only have upper-case english letters, could probably store the frequency in an array -> O(1) space
            Fill the array -> O(t) time
            Iterate over the array -> Pick the task with the most items, that will be the first.
            Then pick the second most... (is there a way that we could speed this up with a heap or sorting?)
            
            Assuming that we pick one from each bucket for each iteration, the item with the most tasks will always remain so
            Continue until we run out?
            And if we iterate through the entire array without doing anything, do an empty task/idle
            
            If we hit n, then everything is okay to take again
            If we do not hit n (from the most frequent element), then we need to idle until n, no matter what (if we're at the end of the array)
            Problem 2: How we make sure we grab the most frequent element each time? -> Heap -> Store the heap inside a random set, then re-heapify each time? -> O(t)
        */
        if(n == 0) return tasks.size
        val frequencyArray = IntArray(26)
        val frequencyHeap = PriorityQueue<Int>(IntCompareReverse())
        val frequencyList = mutableListOf<Int>() //List to keep a hold of items that we have gone through
        
        tasks.forEach { task -> //Build the FrequencyArray, so that we can keep track of which char it belongs to before heapify
            frequencyArray[task - 'A']++
        }
        
        frequencyArray.forEach { item -> if(item > 0) frequencyHeap.add(item) }
        
        var taskLength = 0
        
        while(!frequencyHeap.isEmpty() || frequencyList.isNotEmpty()) {
            if(frequencyHeap.isEmpty() || taskLength % (n + 1) == 0) { //Heap is empty -> We've used up all of our items
                // println("Reached n, or heap is empty")
                while(taskLength % (n + 1) != 0) {
                    // println("Time elapsed since first frequent item: $taskLength")
                    taskLength++ //Idle until cleared
                }
                frequencyList.forEach { item -> frequencyHeap.add(item) } //Re-add all of the items, because they are ready to be used
                frequencyList.clear()
            }
            val top = frequencyHeap.poll()
            // println("Top frequent element: $top, with size ${frequencyHeap.size}")
            if(top - 1 > 0) frequencyList.add(top - 1) //If there are still items of this type, add them to the set
            taskLength++
        }
        
        return taskLength
    }
}