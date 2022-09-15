/*
/*
            Greedy approach
            - Start by sorting by the 1st digit
            - Continue by sorting by the 2nd digit
            
            First digit (0-9) will produce the largest number regardless
            
            95 vs 98
            9895 > 9598, so sort by the 2nd digit in the case that there are ties.
            
            Algorithm
            - Sort by the 1st digit -> Convert to string, then compare the first number as a character
                -> Because ASCII goes 0-9 in increasing order, then '9' > '0'
            -> Bucketing numbers with the same 1st character -> Hashmap <Char, List<String>>
            Have 2 buckets - 1st digit and 2nd digit, then build up final string from those characters
            
            123 vs 124
            
            124123 vs 123124
            
            Hashmap <Char, Array<String>(9)> //Each array represents that particular digit, 0-indexed
            
            Utilize getOrNull -> count null as 0, all comparisons will be of length l, where l is the largest number (digits)
            
            How do we apply this logic across all values without it slowing down?
            
            We want to sort the 2nd digit among all numbers where the first digit is the same
            
            O(nlogn) --> Sorting by 1st digit
            O(nlogn) --> Sort by 2nd digit (assuming all start with same digit) [999, 998, 997]
            O(l * nlogn) where l = number of digits in the longest number
            
            If there are no matches (all of the numbers with a shared digit are done)
            
            10^9 has 10 digits -> If we had 9 starting buckets, then 9 in each... 9^10 buckets...yike.
        */
        val digitMap = mutableMapOf<Char, MutableList<String>>()
        nums.forEach { num -> //Partition into first digit
            val numAsString = num.toString()
            if(digitMap.get(numAsString[0]) != null) {
                val list = digitMap.get(numAsString[0]) as MutableList<String>
                list.add(numAsString)
            } else {
                digitMap.put(numAsString[0], mutableListOf<String>(numAsString))
            }
        }
        digitMap.forEach { entry -> //Sort by second digit
            entry.value.sortDescending()
        }
        println(digitMap)
        val returnString = StringBuilder()
        for(number in '9' downTo '0') { //Creating the string from each major bucket
            if(digitMap.get(number) != null) {
                val list = digitMap.get(number) as List<String>
                list.forEach { item -> returnString.append(item) }
            }
        }
        return returnString.toString()
*/

class Solution {
    
    class CompareConcat(): Comparator<String> {
        override fun compare(first: String, second: String): Int {
            return (second + first).compareTo(first + second)
        }
    }
    
    fun largestNumber(nums: IntArray): String {
        //Way too many buckets for a sort that way. Better answer - concatenate and compare the strings.
        if(nums.reduce {sum, number -> sum + number} == 0) return "0"
        val stringList = Array<String>(nums.size) { index -> nums[index].toString() }.sortedWith(CompareConcat()) 
        return stringList.reduce { accumulator, item -> accumulator + item }
    }
}