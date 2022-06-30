class MyQueue() {
    /*
        Why do we need 2 stacks?
        
        Push -> When we push, we want the "front" to be the oldest element still, even with the stack.
        Pop -> When we pop, we want the oldest item.
        ..
        
        
        We could use 2 stacks - when we push, we "shuffle" the items by pushing them all to the other stack.
        Thus our LIFO transforms to FIFO
        Suppose 3 is the oldest element in the stack.
        Ex: [1 (TOP), 2, 3] pop and pushes to [3 (TOP), 2, 1] because the 3 is the last to pop.
        What if we add another element?
        [4 (TOP), 3, 2, 1] BUT 4 is newest: transform again
        [1 (TOP), 2, 3, 4] -> We can't transform it like this.
        ...What if we do this: first transform, then push, then transform again?
        [3, 2, 1] -> [1, 2, 3] -> [4, 1, 2, 3] -> [3, 2, 1, 4] (Works!)
        
        When popping, however, the order remains the same as before and we do not need to transform. 
    */
    val stack1 = mutableListOf<Int>()
    val stack2 = mutableListOf<Int>()

    fun push(x: Int) {
        if(stack1.isEmpty() && stack2.isEmpty()) {
            //Empty, just push onto stack 2 (our "main stack")
            stack2.add(0, x)
        } else {
            while(!stack2.isEmpty()) {
                stack1.add(0, stack2[0]) //Remove first element from stack 2 and add it to stack 1
                stack2.removeAt(0)
            }
            stack1.add(0, x) //Added to stack 1
            while(!stack1.isEmpty()) { //Transform back to stack 2 for a queue
                stack2.add(0, stack1[0])
                stack1.removeAt(0)
            }
        }
    }

    fun pop(): Int {
        var top : Int
        if(!stack2.isEmpty()) {
            top = stack2[0]
            stack2.removeAt(0)
            return top
        } else {
            return -1 //"Error" condition, probably could be handled better with like an exception
        }
    }

    fun peek(): Int {
        if(!stack2.isEmpty()) {
            return stack2[0]
        } else {
            return -1 //Again, error condition
        }
    }

    fun empty(): Boolean {
        return stack2.isEmpty()
    }

}

/**
 * Your MyQueue object will be instantiated and called as such:
 * var obj = MyQueue()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.empty()
 */