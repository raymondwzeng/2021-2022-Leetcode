class MinStack() {
    
    val stack = mutableListOf<StackNode>()
    var currentMin : StackNode? = null //If we have a currentMin with a value more than a new value, we move the currentMin to there, and move its nextMin to our currentMin.
    
    data class StackNode(val `val`: Int, val nextMin: StackNode? = null){}

    fun push(`val`: Int) {
        if(currentMin == null) {
            currentMin = StackNode(`val`)
            stack.add(currentMin!!)
        } else {
            if(currentMin!!.`val` >= `val`) {
                val temp = currentMin!!
                currentMin = StackNode(`val`, temp)
                stack.add(currentMin!!)
            } else {
                stack.add(StackNode(`val`))
            }
        }
    }

    fun pop() {
        //Remove from the "top", which is our right end
        if(stack[stack.size - 1] == currentMin) { //If we encounter our current min, move to the next.
            currentMin = currentMin?.nextMin
        }
        stack.removeAt(stack.size - 1)
    }

    fun top(): Int {
        return stack[stack.size - 1].`val`
    }

    fun getMin(): Int {
        return currentMin?.`val` ?: 0
    }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = MinStack()
 * obj.push(`val`)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */
