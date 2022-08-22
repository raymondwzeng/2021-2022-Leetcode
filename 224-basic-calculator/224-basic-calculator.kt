/* Good attempt, but stacks are just easier
    enum class Operation {ADD, SUBTRACT}
    open class BaseNode(var left: BaseNode?, var right: BaseNode?)
    class NumberNode(var value: Int) : BaseNode(null, null)
    class OperatorNode(var operation: Operation) : BaseNode(null, null)
    
    fun evaluate(root: BaseNode?): Int {
        if(root == null) return 0
        val left = if(root.left != null) evaluate(root.left) else 0
        val right = if(root.right != null) evaluate(root.right) else 0
        if(root is NumberNode) {
            println("Number with value ${root.value}")
            return root.value
        }
        if(root is OperatorNode) {
            println("Operator with operation ${root.operation}, left of $left and right of $right")
            if(root.operation == Operation.ADD) {
                return left + right
            } else {
                return left - right
            }
        }
       return 0
    }
    
    fun calculate(s: String, index: Int): BaseNode? {
        if(index >= s.length) return null //Return if we have nothing after the blank space.
        var root: BaseNode? = null
        if(s[index] == '(') {
            val root = calculate(s, index + 1)
        } else if (s[index] == ')') {
            return root
        } else if (s[index] == '+') {
            root = OperatorNode(Operation.ADD)
            root.left = calculate(s, index + 1)
            root.right = calculate(s, index + 2)
        } else if (s[index] == '-') {
            root = OperatorNode(Operation.SUBTRACT)
            root.left = calculate(s, index + 1)
            root.right = calculate(s, index + 2)
        } else {
            val curr = NumberNode(Integer.parseInt(s[index].toString()))
            if(index + 2 < s.length) {
                root = calculate(s, index + 1)
                val other = calculate(s, index + 2)
                root?.left = curr
                root?.right = other
            } else {
                root = curr
            }
        }
        return root
    }
    
    fun calculate(s: String): Int {
        //Maybe doing something that first converts the string into RPN?
        /*
            Idea ->
                - Start with an empty binary tree
                - For each number, produce a numerical node
                - For each operator, produce an operator node which has two children
                - If a ( shows up, produce a new tree starting at that point until we hit )
        */
        val string = s.replace(" ", "")
        val root = calculate(string, 0)
        //Next, run postorder traversal on the binary tree, evaluating the expression at each point
        println("Root: $root") 
        return evaluate(root)
    }
*/
class Solution {
    fun calculate(s: String): Int {
        /*
            (1+(4+5+2)-3)
            1+(4+5+2)-3) s: 0, tn: 0, cs: 0
            +(4+5+2)-3) s: 0, tn: 1, cs: 0
            (4+5+2)-3) s: 0, tn: 0, cs: 1, ls: 1
            4+5+2)-3) s: [1, 0], tn: 0, cs: 0, ls: 1
            +5+2)-3) s: [1, 0], tn: 4, cs: 0, ls: 1
            5+2)-3) s: [1, 0], tn: 0, cs: 4, ls: 1
            +2)-3) s: [1, 0], tn: 5, cs: 4, ls: 1
            2)-3) s: [1, 0], tn: 0, cs: 9, ls: 1
            )-3) s: [1, 0], tn: 2, cs: 9, ls: 1
            -3) s: [0], tn: 0, cs: 9 + 1 + 2 = 12, ls: 1
            3) s: [0], tn: 0, cs: 12, ls: -1
            ) s: [0], tn: 3, cs: 12, ls: -1
             s: [], tn: 0, cs: 12 + 0 + (3 * -1) = 9
             
             2-1+2 s: [], tn: 0, cs: 0
             -1+2 s: [], tn: 2, cs: 0
             1+2 s: [], tn: 0, cs: -2, ls: -1
             +2 s: [], tn: 1, cs: -2, ls: -1
             2 s: [], tn: 0, cs: -1, ls: 1
             s: [], tn: 2, cs: -1, ls: 1
        */
        val string = s.replace(" ", "") //Just make it easier on ourselves
        val operandStack = Stack<Int>()
        var tempNumber = 0
        var currentSum = 0
        var lastOperation = 1
        string.forEach { char ->
            // println("Current temp: $tempNumber, with sum $currentSum and lastOp $lastOperation, stack $operandStack")
            when(char) {
                '+' -> {
                    currentSum += tempNumber * lastOperation
                    tempNumber = 0
                    lastOperation = 1
                }
                '-' -> {
                    currentSum += tempNumber * lastOperation
                    tempNumber = 0
                    lastOperation = -1
                }
                '(' -> {
                    //We push our currentSum into stack, as that is what we want to evaluate at the end.
                    operandStack.push(lastOperation)
                    operandStack.push(currentSum)
                    currentSum = 0
                    tempNumber = 0
                    lastOperation = 1
                }
                ')' -> {
                    //We pop off the currentSum from our stack once we're done
                    val previous = operandStack.pop()
                    currentSum += (tempNumber * lastOperation)
                    currentSum *= operandStack.pop() //Pop the last sign so that we're back in the previous context if needed
                    currentSum += previous
                    tempNumber = 0
                }
                else -> tempNumber = (tempNumber * 10) + (char - '0') 
            }
        }
        if(tempNumber != 0) currentSum += (tempNumber * lastOperation)
        return currentSum
    }
}

