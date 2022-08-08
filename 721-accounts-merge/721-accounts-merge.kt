class Solution {
    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
        /*
            The main key: If two lists share a common email, then the two lists should be merged.
            Otherwise, do not merge them.
            
            This is probably not an merge intervals type problem
            
            Hashmap by name, with key-value name-list<set<emails>>
            For each name within the hashmap, if there exists more than 1 list of emails, then we need to check them for a merge.
            *Considering that we need to sort the emails anyways, we might as well do that now. -> O(eloge) where e = # of emails
            
            -> Maybe we could perform a binary search for any intersection? -> O(loge), and since we do it for each email, O(eloge)
            
            We could also consider using a set for this..? -> O(1) retrieval for .contains()
            Assume that all emails within an account are unique
                -> Store each block of emails within a set, rather than a list
                -> Retrieval/checking for matches lowered to O(n * e * a) where n is the number of accounts with the same name
                    -What happens if we do find a match? -> Produce a new set which is the union of both sets, and then remove the individuals
                    -We know that a set maintains an order based upon insertion, we know that if we have a match, then we would likely insert additional items, and that any item that exists before would have been matched already
                -> Produce a new list which is the set .toList(), sorted, and the name of the account holder -> O(n * eloge * a), where a is the number of accounts
                
            Space: 
                Store each account within a hashmap -> O(a)
                Store each email list per account -> O(a * n)
                Store each email within each email list -> O(a * n * e)
        */
        
        val nameMap = mutableMapOf<String, MutableList<Set<String>>>()
        
        for(nameEntry: List<String> in accounts) {
            if(nameMap.get(nameEntry[0]) == null) {
                nameMap.put(nameEntry[0], mutableListOf(nameEntry.drop(1).toSet()))
            } else {
                val nameList = nameMap.get(nameEntry[0]) as MutableList<Set<String>>
                nameList.add(nameEntry.drop(1).toSet())
            }
        }
        
        nameMap.forEach { entry ->
            var index = 0
            while(index < entry.value.size - 1) {
                var innerIndex = index + 1
                var hasFolded = false
                while(innerIndex < entry.value.size && !hasFolded) {
                    val currentEmailBlock = entry.value[index]
                    // println(currentEmailBlock)
                    if(currentEmailBlock.intersect(entry.value[innerIndex]).isNotEmpty()) {
                        // println("Unioning ${currentEmailBlock} and ${entry.value[innerIndex]}")
                        entry.value[innerIndex] = currentEmailBlock.union(entry.value[innerIndex])
                        entry.value.removeAt(index)
                        hasFolded = true
                        index--
                    }
                    innerIndex++
                }
                // println("${entry.value}")
                index++
            }
            // println(entry)
        }
        
        val returnList = mutableListOf<List<String>>()
        
        nameMap.forEach { entry -> 
            entry.value.forEach { emailSet : Set<String> ->
                val listOfEmails = emailSet.toMutableList()
                listOfEmails.sort()
                val listWithEmails = listOf(entry.key) + listOfEmails
                returnList.add(listWithEmails)
            }
        }
        
        return returnList
    }
}