/*
        /*
            Naive implementation of pow -> Multiplying x by itself n times
        */
        if(n == 0) return 1.0
        var currentNumber = x
        for(i in 1..Math.abs(n - 1)) {
            if(n > 0) { //Multiply by the amount
                currentNumber *= x
            } else { //Divide by the amount
                currentNumber /= x
            }
        }
        return currentNumber
*/

/* Decent attempt at masking, but not quite there
//If x == 2.0, then we can left shift by n (or right shift)
        /*
            Doubles/Floating --> IEE754
            Sign bit | Value bits | Exponent bits (number - 127) = exp
            
            If we can get the exp bits, then we can simply modify them to the value that we want
        
            - Start by turning the Double into its string representation
            - Mask off the bits that we actually want to manipulate, and add the number of bits that we actually want
        */
        val binaryLong = x.toBits()
        val signMask = 0x800000000000000L
        val exponentMask = 0x7ff0000000000000L
        println(Double.fromBits(binaryLong))
        val exponent = (n + 1023).toLong()
        println(exponent)
        println(binaryLong and exponentMask.inv())
        val newNumber = (binaryLong and exponentMask.inv()) or exponent
        println(Double.fromBits(newNumber))
        return 0.0
*/

class Solution {
    fun myPow(x: Double, n: Int): Double {
        //The CORE idea is that any exponent in base 10 can be expressed as a product of exponents in base 2.
        //i.e, 7^11 == 7^8 * 7^2 * 7^1
        //Thus, we can shift our exponent, and if the base value is a 1, we multiply
        if(n == 0 || x == 1.0) return 1.0
        var currentValue = 1.0
        var powerOfTwo = if(n < 0) 1/x else x
        var exponentLeft = if(n < 0) -n else n
        while(exponentLeft != 0) {
            if(exponentLeft and 1 == 1) {
                currentValue *= powerOfTwo
            }
            powerOfTwo *= powerOfTwo
            exponentLeft = exponentLeft.ushr(1)
        }
        return currentValue
    }
}