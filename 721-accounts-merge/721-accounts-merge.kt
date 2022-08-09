class Solution {
    
    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
        //Union find via hashing the root, and hashing the owner as well
        val rootMap = mutableMapOf<String, String>() //Key: Node name, Value: Parent
        val ownerMap = mutableMapOf<String, String>() //Key: Node name, Value: Account holder
        
        val returnList = mutableListOf<List<String>>()
        val emailChainMap = mutableMapOf<String, MutableSet<String>>() //Remark: We use a set because it allows for duplicate checking natively without additional work on our end.
        
        //First loop, add all of the items into the root and owner maps.
        accounts.forEach { account ->
            account.forEachIndexed { index, emailAddress ->
                if(index > 0) { //Skip the original node, which is the owner.
                    rootMap.put(emailAddress, emailAddress) //Each node in UF starts as its own root
                    ownerMap.put(emailAddress, account[0]) //We know that the 0th index of each account is the owner's name
                }
            }
        }
        
        //Second loop, unions all of the email addresses such that if a root between two emails is the same, then they should be part of the same list.
        accounts.forEach { account ->
            account.forEachIndexed { index, emailAddress ->
                if(index > 0 && index < account.size - 1) {
                    unionRoots(emailAddress, account[index + 1], rootMap)
                }
            }
        }
        
        //Third loop, produces the set of items in order to carry out the last step. This is necessary(?) because there is no guarantee that we will have contiguous emails to add into a list. May not be necessary if we change the data structure to something more optimal.
        accounts.forEach { account ->
            account.forEachIndexed { index, emailAddress ->
                if(index > 0) { //Null check for type inference String? -> String
                    val addressRoot = findRootAndCompress(emailAddress, rootMap)
                    if(addressRoot == emailAddress) { //Create a new set, which will be put into yet another map for quick reference
                        if(emailChainMap.get(addressRoot) == null) emailChainMap.put(emailAddress, mutableSetOf<String>())
                    } else { //Insert the item into the set headed by the root email address, or create one
                        emailChainMap.getOrPut(addressRoot, {mutableSetOf<String>()}).add(emailAddress)
                    }
                }
            }
        }

        //Final loop, produces the list and sorts before adding the owner.
        emailChainMap.forEach { emailChainEntry ->
            //The key is the root, the values is a set of other emails.
            val emailList = emailChainEntry.value.toMutableList()
            emailList.add(emailChainEntry.key)
            emailList.sort()
            if(ownerMap.get(emailChainEntry.key) != null) emailList.add(0, ownerMap.get(emailChainEntry.key) as String) //Multiple checks for Kotlin
            returnList.add(emailList)
        }
        
        return returnList
    }
    
    /**
    *   Finds the root of the emails, and unions them together by setting the parent of one to the other.
    */
    fun unionRoots(firstEmail: String, secondEmail: String, map: MutableMap<String, String>) {
        val firstRoot = findRootAndCompress(firstEmail, map)
        val secondRoot = findRootAndCompress(secondEmail, map)
        if(firstRoot == secondRoot) return //No need to do anything if the roots are the same
        map.put(firstRoot, secondRoot) //Could also consider the rank of each in a future iteration
    }
    
    /**
    *   Takes in a value and returns its root. May be the original node. Compresses everything in between for runtime.
    */
    fun findRootAndCompress(email: String, map: MutableMap<String, String>): String {
        val ancestors = mutableListOf<String>()
        var parent = email
        while(map.get(parent) != parent && map.get(parent) != null) {
            val newParent = map.get(parent) as String //Kotlin doesn't seem to like this
            ancestors.add(newParent) 
            parent = newParent
        }
        ancestors.forEach { ancestor ->
            map.put(ancestor, parent) //Parent should be the root at this point
        }
        return parent //Return the most recent parent, which should be the root as its value in rootMap is itself
    }
}