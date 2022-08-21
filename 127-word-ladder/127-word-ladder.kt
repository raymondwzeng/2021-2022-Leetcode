/*
        //Given that the wordList.size <= 5000, maybe O(nlogn)? Definitely not O(n^2) imo. So...sorting?
        //Is there a greedy solution that could exist? Perhaps using sorted letters, and picking the closest?
        //Problem: We can't determine "off by 1" without a frequencyMap, or maybe KMP? Not sure where that's used yet, should do some research
        //All the words within the wordList have the same length as beginWord, so could use 2P on sorted words to determine #diff
        //Perhaps we could consider all possibilities, and then choose the one that actually is the least? But is that fast enough?
        //Corner case: If !wordList.contains(endWord) return 0
        /*
            Naive Approach
            -> Handle corner cases
            -> Sort all words, including beginWord and endWord -> O(n * slogs)
            -> Start with beginWord, and for each word in wordList, use 2P to compare # diff. If > 1, skip. If == 1, add to queue (with added # changes) -> O(nk), where k = min # of scans until we reach the endWord
                - Probably need a dataclass to hold # changes
            -> Continue emptying the queue until we reach the word?
                -> Maybe a "similarity score" with the endWord? But I could see a scenario where we need to go less similar to a word before going more similar...
        */
        if(!wordList.contains(endWord)) return 0
        
        val usedWords = mutableSetOf<String>(beginWord)
        var currString = beginWord
        var numSwaps = 1
        while(currString != endWord) {
            var hasSwapped = false
            wordList.forEach { word ->
                if(!usedWords.contains(word)) {
                    var numDiff = 0
                    var numDiffEnd = 0
                    println("Comparing $word and $currString")
                    currString.forEachIndexed { index, char ->
                        if(char != word[index]) {
                            // println("$char does not match ${word[index]}")
                            numDiff++
                        }
                    }
                    // println("Numdiff: $numDiff")
                    if(numDiff == 1) {
                        usedWords.add(word)
                        currString = word
                        numSwaps++
                        numDiff = 0
                        hasSwapped = true
                    }
                }
            }
            if(!hasSwapped) return 0
        }
        return numSwaps
    }
*/

class Solution {
    
    data class Node(val word: String, val neighbors: MutableList<Node> = mutableListOf<Node>()) {
        override fun toString(): String {
            return "[${this.word}]"
        }
    }
    
    fun process(word: String, map: MutableMap<String, MutableList<Node>>, existingNodes: MutableMap<String, Node>): Node {
        val newNode = Node(word)
        if(existingNodes.get(word) == null) {
            for(index in 0..word.length - 1) {
                val intermediate = word.toCharArray()
                intermediate[index] = '*'
                val newString = intermediate.joinToString("")
                if(map.get(newString) != null) {
                    val list = map.get(newString) as MutableList<Node>
                    for(index in 0..list.size - 1) {
                        list[index].neighbors.add(newNode)
                        newNode.neighbors.add(list[index])
                    }
                    // newNode.neighbors = list
                    list.add(newNode)
                } else {
                    map.put(newString, mutableListOf<Node>(newNode))
                }
            }
            existingNodes.put(word, newNode)
        }
        return newNode
    }
    
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        //1st question should always be - can I model this as a graph?
        if(!wordList.contains(endWord)) return 0
        val intermediateStates = mutableMapOf<String, MutableList<Node>>()
        val existingNodes = mutableMapOf<String, Node>()
        val root = process(beginWord, intermediateStates, existingNodes)
        wordList.forEach { word -> process(word, intermediateStates, existingNodes) }

        var layers = 1
        val visited = mutableListOf<Node>(root) //We run into a stack overflow issue here if we use a set, so use a list. Slow, but it happens.
        val queue = LinkedList<Node>()
        queue.add(root)
        while(!queue.isEmpty()) {
            for(i in 0..queue.size - 1) {
                val front = queue.poll()
                // println("Visiting $front")
                front.neighbors.forEach { neighbor ->
                    if(neighbor.word == endWord) return layers + 1 //Earliest we find it, is the answer + 1 because we transition to it
                    if(!visited.contains(neighbor)) {
                        // println("Adding $neighbor")
                        queue.add(neighbor)
                        visited.add(neighbor)
                    }
                }
            }
            // println("Increasing layers by 1")
            layers++
        }
        
        return 0
    }
}