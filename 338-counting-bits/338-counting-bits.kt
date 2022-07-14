import kotlin.math.log2
import kotlin.math.truncate

class Solution {
    fun countBits(n: Int): IntArray {
        val binaryArray = IntArray(n+1)
        var mostRecentPO2 = -1
        for(i in 0..n) {
            when {
                i == 0 -> binaryArray.set(i, 0)
                log2(i.toDouble()) == truncate(log2(i.toDouble())) -> {
                    binaryArray.set(i, 1)
                    mostRecentPO2 = i
                }
                else -> binaryArray.set(i, binaryArray.get(i - mostRecentPO2) + 1)
            }
        }
        return binaryArray
    }
}