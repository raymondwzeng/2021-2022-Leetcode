class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        var sourceMap = mutableMapOf<Char, Int>();
        var targetMap = mutableMapOf<Char, Int>();
        s.forEach{element ->
            if(sourceMap.contains(element)) {
                sourceMap.put(element, sourceMap.get(element)!!.plus(1))
            } else {
                sourceMap.put(element, 0)
            }
        }
        t.forEach{element ->
            if(targetMap.contains(element)) {
                targetMap.put(element, targetMap.get(element)!!.plus(1))
            } else {
                targetMap.put(element, 0)
            }
        }
        
        //Iterate through the target map to make sure that nothing is missing.
        targetMap.forEach{element ->
            if(!sourceMap.contains(element.key) || sourceMap.get(element.key) != element.value) return false
        }
        
        //Iterate through the source map to make sure it doesn't have anything extra
        sourceMap.forEach{element ->
            if(!targetMap.contains(element.key) || targetMap.get(element.key) != element.value) return false
        }
        
        return true
    }
}