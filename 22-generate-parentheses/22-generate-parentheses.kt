class Solution {
    fun generateParenthesis(numOpen: Int, numClosed: Int, currentInput: String, list: MutableList<String>) {
        if(numClosed - numOpen > 0) { //We can add both a open or closed parenthesis if we have the nums
            if(numOpen > 0) {
                val addOpen = StringBuilder(currentInput).append('(').toString()
                generateParenthesis(numOpen - 1, numClosed, addOpen, list)    
            }
            if(numClosed > 0) {
                val addClosed = StringBuilder(currentInput).append(')').toString()
                if(numOpen == 0 && numClosed - 1 == 0) list.add(addClosed) else generateParenthesis(numOpen, numClosed - 1, addClosed, list)
            }
        } else { //numOpen <= numClosed, we can't add a closed paren otherwise it's invalid, so add open only
            val addOpen = StringBuilder(currentInput).append('(').toString()
            //We know that when we add the open paren, we should pair it with a closed paren, so recurse on next
            generateParenthesis(numOpen - 1, numClosed, addOpen, list)
        }
    }
    
    fun generateParenthesis(n: Int): List<String> {
        //Backtracking
        //Or permutations/combinations
        /*
            Given an integer n
            Start with n open parenthesis, n close parenthesis
            In every situation, as long as we have an open parenthesis, we can choose to either open or close the next
            
            [] -> Choose ( only
            [(] -> Chooose (( or ()
            [((] -> Choose ((( or (()
            [()] -> Choose ()( 
            If closed parenthesis - open parenthesis <= 0, then we can only add an open parenthesis
            Otherwise, we can choose to add an open parenthesis, or a closed parenthesis
            Return when all open/close parenthesis have been used
            
            O(2^n) time
        */
        val returnList = mutableListOf<String>()
        generateParenthesis(n, n, "", returnList)
        return returnList
    }
}