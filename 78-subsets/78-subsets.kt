class Solution {
    fun subsets(nums: IntArray): List<List<Int>> {
        //Backtracking problem? -> No clear condition to really choose when to stop
        /*
            Use a queue instead (iterative method)
            Start with a 
            [1, 2, 3]
            
            [] -> [1], [2], [3] (first iteration)
            [1] -> [1, 2], [1, 3]
            [2] -> [2, 3]
            [1, 2] -> [1, 2, 3]
            
            Maintain an index as to where the last element was?
            
            Tree Level Order -> For loop based on layer to pop x number of elements from the queue
            Check to see if the element is the last element that we added -> we don't check all of the elements
            Using sets -> No duplicates, O(1) .contains checking
            
            Create a list of sets
            -> For each set, create a duplicate set (except for the empty set, how do we stop that?)
            -> For each subsequent round, we check if we can add the element into the set, and if so, we add them to a copy.
            
            []
            [], [1] -> Add 1 pass
            [], [1], [2], [1, 2] -> Add 2 pass
            [], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3] -> Add 3 pass
            
            O(2^n) runtime and space
        */
        
        val listOfSets = mutableListOf<List<Int>>(emptyList<Int>())
        
        nums.forEach { num ->
            val newList = mutableListOf<List<Int>>()
            listOfSets.forEach { list ->
                newList.add(list + num)
            }
            newList.forEach {list -> listOfSets.add(list)}
        }
        
        return listOfSets
    }
}