/* Initial attempt (close)
            //Input -> O(n) solution because n <= 10^5
        /*
            Naive solution -> We start at each and check if it works -> O(n^2) time
            
            Problem: We can't *just* check to see if we can reach a gas station from the previous, because we might have an accumulated amount which can actually pass to the next station
               
            Greedy approach
                -> Start with a linear scan, check to find the jump with the most left over (raw)
                -> Earliest viable option
            
            Suppose each node is a gas station, and each edge is is a road with a weight cost
            
            A -> B -> C -> D -> E -> A
            
            Where A -> B = 3, B -> C = 4, etc.
            
            No topological sort
            
             1, 2, 3, 4, 5
           1 0 -2 -4 -2 -2
           2    0
           3       0
           4          0
           5             0
           Dynamic programming approach would probably take polynomial time
           
           
           gas = [5, 0, 5] cost = [4, 2, 4]
        */
        if(gas.size == 1) return if(gas[0] >= cost[0]) 0 else -1
        
        var start = -1
        for(index in gas.size - 1 downTo 0) {
            if(gas[index] - cost[index] > 0) { //Find the "head" of the longest surplus chain from the back, O(n) time
                println("$index is viable")
                if((index > 0 && gas[index - 1] - cost[index - 1] <= 0) || index == 0) {
                    start = index
                    break
                }
            }
        }
        
        if(start == -1) return -1
        
        val origin = start
        var gasAmount = 0
        while(((start + 1) % gas.size) != origin) {
            // println("Going from $start to ${(start + 1) % gas.size}")
            gasAmount += gas[start] - cost[start]
            // println("Current gas: $gasAmount")
            if(gasAmount < 0) return -1
            start = ((start + 1) % gas.size)
        }
        
        return if(gasAmount + gas[start] - cost[start] >= 0) origin else -1
    }
*/

class Solution {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        //First, check if a cycle is possible. If not, then we shouldn't even bother with finding a viable starting point.
        val cycleSum = gas.foldIndexed(0) { index, accumulator, value -> accumulator + value - cost[index] }
        if(cycleSum < 0) return -1
        
        //Next, check all stations for a viable start. A viable start is characterized by being able to continue to move in the cycle.
        //If a station can NOT move to the next cycle with its fuel, then we move the potential station forward.
        
        /*
            Intuition:
            The reason why this method of finding the starting point works is that:
            1) We KNOW a starting location must exist.
            2) If a starting location exists, it should be able to reach the end (where presumably it wraps around without issue) of array
                2a) If it does not, then since SUM(a, b, c...k) - cost(a, b, c...k) < 0, then k + 1 is the next choice
        */
        var currentFuel = 0
        var start = 0
        gas.forEachIndexed { index, value ->
            if(currentFuel + value - cost[index] < 0) {
                start = index + 1
                currentFuel = 0 //Reset the gas tank to its default state of empty
            } else {
                currentFuel += value - cost[index] //Add the surplus into our current tank
            }
        }
        
        return start
    }
}