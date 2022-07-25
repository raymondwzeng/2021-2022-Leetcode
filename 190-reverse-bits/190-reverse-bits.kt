class Solution {
    // you need treat n as an unsigned value
    fun reverseBits(n:Int):Int {
        //Turn the value into a bit string, reverse it, and cast it back?
        //Or just use standard library functions lol
        // val baseValue = Integer.reverse(n)
        // return baseValue
        val bitString = Integer.toBinaryString(n)
        var baseValue = 0
        bitString.forEachIndexed { index, char ->
            if(char == '1') {
                baseValue += Math.pow(2.0, index.toDouble()).toInt()
                if(index == 31) baseValue += 1
            }
        }
        for(i in bitString.length..31) {
            baseValue *= 2
        }
        return baseValue
    }
}