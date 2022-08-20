class Solution {
       
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        /*
            Kinda feels like union-find -> Finding connected components
            strs.length <= 10^4 -> O(n) algorithm, maybe nlogn, or something in that neighborhood
            
            Node -> Value: String, Parent: Node, Frequency: Map<Char, Int>
            Map<Node, List<Node>> -> Chains
            
            Start by creating a node of all strings in strs -> O(n)
            Then for each node, compare to all other strings -> O(n^2) //Come back to this
                -> If the frequency map matches, then we can chain them together and remove the other element from our map
            Bonus for runtime: If a node does not match with any other node within the list, then we know for sure that it is within its own anagram group
            Rather than comparing against all nodes within any given list, only compare against the roots within Chains
            For each node group within the chain, create a list of the values and return -> O(n)
        */
        if(strs.size == 1) return listOf(listOf(strs[0]))
        val chains = mutableMapOf<Map<Char, Int>, LinkedList<String>>()
        strs.forEach { string: String -> //Build
            val freqMap = mutableMapOf<Char, Int>()
            string.forEach { char ->
                freqMap.put(char, freqMap.getOrPut(char, {0}) + 1) 
            }
            val chainList = chains.getOrPut(freqMap, {LinkedList<String>()})
            chainList.add(string)
        }

       return chains.map {entry -> entry.value}
    }
}