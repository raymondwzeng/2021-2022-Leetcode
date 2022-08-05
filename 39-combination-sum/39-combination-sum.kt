class Solution {
    
    fun solve(subList: List<Int>, candidates: IntArray, minCandidateIndex: Int, target: Int): List<List<Int>> {
        var solutionList = mutableListOf<List<Int>>()
        // println(subList)
        val total = if(subList.isNotEmpty()) subList.reduce {sum, element -> sum + element} else 0 //reduceOrNull in Kotlin >= 1.4
        candidates.forEachIndexed { index, candidate -> 
            if(index >= minCandidateIndex) {
                when {
                total + candidate == target -> {
                    // println("Found combo: ${subList + candidate}")
                    solutionList.add(subList + candidate)
                }
                total + candidate < target -> { //Constraint 1: Recurse only on viable candidates
                    val copy = subList.toMutableList()
                    copy.add(candidate)
                    val recursiveSolution = solve(copy, candidates, index, target) //Constraint 2: Don't go back for candidates
                    if(recursiveSolution.isNotEmpty()) {
                        recursiveSolution.forEach {solution ->
                            solutionList.add(solution)
                        }
                    }
                }
            }
            }
        }
        return solutionList
    }
    
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        /*
            [2, 3, 6, 7]
            For each candidate number, we have a choice - take the number, or leave the combination as-is
            @ 2 -> [2], []
            @ 3 -> [2, 2], [2, 3], [3], []
            @ 6 -> [2, 2, 2], [2, 2, 3], [2, 3, 3], [2, 3, 6], [2, 2, 6], [2, 6], [6], []
            
            Exponential blowup? -> O(2^n) runtime
            
            Rather than holding all of these combinations, use a data structure to keep track of compatible sets, and combine them when necessary
            "DP-like approach"
            For all numbers from 1 to the target
            Find the combinations that add up to the number
            If a complementary set exists [0, target] or [1, target - 1], produce the power set of those two.
            
            0: []
            1: []
            2: Power set of anything within 1 and itself, as well as if 2 exists -> [2]
            3: Power set combo of 1 and 2, and 3 if it exists -> [3]
            4: Power set combos of 1 and 3, as well as 2 and 2 -> [2, 2]
            5: 4 and 1, 2 and 3 -> [2, 3]
            6: 5 and 1, 2 and 4, 3 and 3, and 6 if it exists -> [2, 2, 2], [3, 3], [6]
            7: 6 and 1, 5 and 2, 4 and 3, and 7 if it exists -> [2, 3, 2], [3, 2, 2], [7]
            8: 7 and 1, 6 and 2, 5 and 3, 4 and 4 -> [2, 2, 2, 2], [2, 2, 2, 2], [3, 3, 2], [6, 2]
            
            If it is a backtracking problem, what are the components?
            Choice -> On a set
                        We can choose to insert the current item into our set, or not.
            Constraint -> If our set is <= target, continue and recurse to the next. If not, return.
            Converge -> Once we have = target, add to the list.
        */
        val initialList = listOf<Int>()
        return solve(initialList, candidates, 0, target)
    }
}