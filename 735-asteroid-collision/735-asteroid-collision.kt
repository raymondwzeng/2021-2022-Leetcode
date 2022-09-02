/* Asteroid Collision
        //10^4 -> O(n) runtime
        /*
            - => Left
            + => Right
            Numbers represent size
            
            Output: The final state of the asteroids after collision
            
            If two asteroids move in the same direction, they will never meet
            Likewise, if two asteroids move in the opposite direction, one or both will be removed
            
            [-5, 5, -5]
            First two will never collide because they are moving in opposite directions
            Second two will collide, and both are removed
            
            [-5]
            
            [10, 2, -5] No interaction between 10 and 2
            [10, -5] 2 collides with -5, -5 stays
            [10] 10 collides with -5, 10 stays
            
            Is it possible for a collision to happen left -> right?
            
            What if we used like 2 pointers?
            
            [5L, -5, 10, -10R]
            While left < right
                For the left side, compare to see if we need to remove some item
                For the right side, compare its next value to see if we need to remove one or the other
                Move the left and right closer to each other? -> Only if we have a collision
                
            O(n) runtime, O(n) space -> Convert IntArray to LinkedList, then back to final IntArray
            
            At what point do we add values? -> Set unused values to 0, and then go back at the end of the iteration?
            
            [5L, 5, 10, -100R]
            [5, 5, 10L (overriden to -100R)]
            
            Maybe 1 pass from the left and 1 pass from the right, then 1 final pass to move to array?
            
            [1, -5, -10]
            [5, 10, -1]
            
            [10, 2, -5]
            [10, 2, -5] 10 and 2 don't collide
            [10, 0, -5] 2 and -5 leave -5
            [10, 0, 0] 10 and -5 leave 10
            
            [-2, 1, -2, 1]
            [-2, 1, -2, 1] First -2 and 1 don't do anything
            [-2, -2, 1] 1 and -2 leave -2
            
            
            [7,-1,2,-3,-6,-6,-6,4,10,2]
            [0,7,2,-3,-6,-6,-6,4,10,2]
            [0,7,0,-3,-6,-6,-6,4,10,2]
        */
        var index = 0
        while(index < asteroids.size - 1) { //Left to Right Resolution
            val current = asteroids[index]
            val next = asteroids[index + 1]
            if(current > 0 && next < 0) { //Current moves to the right, next moves to the left
                asteroids[index] = 0 //0 will be our "ignore" value
                if(current == Math.abs(next)) {
                    asteroids[index + 1] = 0
                    index++
                } else {
                    asteroids[index + 1] = maxOf(current, Math.abs(next))
                    if(asteroids[index + 1] == Math.abs(next)) asteroids[index + 1] = -asteroids[index + 1]
                }
            }
            index++
        }
        index = asteroids.size - 1
        var innerIndex = index - 1
        while(index > 0) { //Right to Left Resolution
            val current = asteroids[index]
            innerIndex = minOf(index - 1, innerIndex)
            while(asteroids[innerIndex] == 0 && innerIndex > 0) innerIndex--
            if(innerIndex == 0 && asteroids[innerIndex] == 0) break
            val next = asteroids[innerIndex]
            if(current < 0 && next > 0) {
                asteroids[index] = 0
                if(next == Math.abs(current)) {
                    asteroids[innerIndex] = 0
                    index--
                } else {
                    asteroids[innerIndex] = maxOf(Math.abs(current), next)
                    if(asteroids[innerIndex] == Math.abs(current)) asteroids[innerIndex] = -asteroids[innerIndex]
                }
            }
            index--
        }
        var returnList = mutableListOf<Int>()
        asteroids.forEach { num ->
            if(num != 0) returnList.add(num)
        }
        return returnList.toIntArray()
*/

class Solution {
    fun asteroidCollision(asteroids: IntArray): IntArray {
        //Our original insight into one way resolution was half correct.
        //Use a stack, and slowly build up from there.
        val asteroidStack = Stack<Int>()
        asteroids.forEach { asteroid ->
            if(asteroidStack.isEmpty()) {
                asteroidStack.push(asteroid)
            } else {
                var currAsteroid = asteroid
                while(currAsteroid < 0 && (!asteroidStack.isEmpty() && asteroidStack.peek() > 0)) { //Resolve all collisions as we add each in
                    val top = asteroidStack.pop()
                    val result = maxOf(Math.abs(currAsteroid), top)
                    currAsteroid = when {
                        (currAsteroid + top == 0) -> 0
                        (result == Math.abs(currAsteroid)) -> -result
                        else -> maxOf(Math.abs(currAsteroid), top)
                    }
                }
                if(currAsteroid != 0) asteroidStack.push(currAsteroid)
            }
        }
        val result = IntArray(asteroidStack.size){0}
        var index = result.size - 1
        while(!asteroidStack.isEmpty()) {
            result[index--] = asteroidStack.pop()
        }
        return result
    }
}