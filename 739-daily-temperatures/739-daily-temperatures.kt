class Solution {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        //answer[i] == right - left such that temperatures[right] > temperatures[left], and left == i
        //10^5 -> O(n) runtime
        /*
            Naive solution -> Run through the rest of the answers until you reach a higher temp or the end of array
            O(n^2) solution
            
            Last element will always have an answer[i] of 0
            Second to last, we just check the next element to see if it is larger (1), if not, (0).
            What if we stored the element which is larger, within another data structure?
            
            [73, 74, 75, 71, 69, 70, 76, 73]
            [1, 2, 6, 5, 5, 6, 0, 0] -> Indices of the larger temperature
            [1, 1, 4, 2, 1, 1, 0, 0]
            
            
            a,b,c
            [90, 80, 70]
            []
            [, 0, 0]
            
            If b > c and a > b -> a > c --> If we encounter a next where the value in answ == 0, then we are also 0.
            Feels like DP
            Working backwards might work
            
            Start from the back of the answer array.
            Check the next number from the current index.
                -> If less
                    -> If answer[next] == 0, set to 0
                    -> Otherwise, check dp[next] for the subsequent value, and repeat.
                -> If greater
                    -> Set to nextIndex - currentIndex
                    -> dp[currentIndex] = nextIndex
                    
            O(n) runtime
            O(n) space
        */
        if(temperatures.size == 1) return IntArray(1) //Should return an intarray with just 1 0
        val dp = IntArray(temperatures.size)
        val answer = IntArray(temperatures.size)
        
        for(index in answer.size - 2 downTo 0) {
            var nextIndex = index + 1
            var noIndexFound = false
            // println("Current index is: $index")
            while(temperatures[index] >= temperatures[nextIndex]) {
                // println("Next index is: $nextIndex")
                if(answer[nextIndex] == 0) {
                    // println("Answer is 0, we can't go further")
                    answer[index] = 0
                    noIndexFound = true
                    break
                } else {
                    // println("Going to next index, ${dp[nextIndex]}")
                    nextIndex = dp[nextIndex]
                }
            }
            if(!noIndexFound) {
                // println("Next temperature is ${temperatures[nextIndex]} at index $nextIndex")
                answer[index] = nextIndex - index
                dp[index] = nextIndex
            }
        }
        
        return answer
    }
}