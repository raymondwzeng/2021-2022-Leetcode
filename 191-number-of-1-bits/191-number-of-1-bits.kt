/* Recursive math way
import kotlin.math.abs

        if(n == 0) return 0
        println(n)
        val numOnes = when {
            n < 0 -> return 32 - hammingWeight(abs(n + 1))
            n % 2 == 1 -> return 1 + hammingWeight(n/2)
            n % 2 == 0 -> return hammingWeight(n/2)
            else -> return 0
        }
*/

class Solution {
    // you need treat n as an unsigned value
    fun hammingWeight(n:Int):Int {
        val binaryString = Integer.toBinaryString(n)
        var numOnes = 0
        binaryString.forEach {char -> if(char == '1') numOnes++}
        return numOnes
    }
}