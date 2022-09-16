class Solution {
    
    data class CandidateCity(val city: Int, val price: Int, val stops: Int)
    
    class CandidateCityComparator: Comparator<CandidateCity> {
        override fun compare(first: CandidateCity, second: CandidateCity): Int {
            return first.price.compareTo(second.price)
        }
    }
    
    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        /*
            First application of Dijkstra's Algorithm (exciting!)
            
            - Hashmap of cities that we can traverse to, given a source city -> MutableMap<Integer, List<IntArray>> //IntArray[0] is city, IntArray[1] is price
            - Set of visited cities ("cloud")
            - Heap of flight data -> Current city, current price, paths traversed so far
            - Start by adding our start city
            - Add all of the cities (assuming they are not in the cloud) to our heap, organized by price
                -> If the current city number of stops is already k, then ignore the city entirely
            - Add our current city into the cloud
            - Repeat until we exhaust our heap, or until we've reached the destination city.
            
            TODO: Take a double check at the runtime (and space complexity)
        */
        val destinationPriceMap = mutableMapOf<Int, MutableList<IntArray>>()
        val visitedCities = mutableSetOf<Int>()
        val candidateCities = PriorityQueue<CandidateCity>(CandidateCityComparator())
        candidateCities.add(CandidateCity(src, 0, 0))
        
        var lowestPrice = Integer.MAX_VALUE
        
        flights.forEach { flight -> //Populates the destinations given a source city, with its accompanying price
            if(destinationPriceMap.get(flight[0]) == null) {
                destinationPriceMap.put(flight[0], mutableListOf<IntArray>(intArrayOf(flight[1], flight[2])))
            } else {
                val list = destinationPriceMap.get(flight[0]) as MutableList<IntArray>
                list.add(intArrayOf(flight[1], flight[2]))
            }
        }
        
        while(candidateCities.isNotEmpty()) { //Run Dijkstra's on the candidate cities.
            val currentCity = candidateCities.poll()
                if(destinationPriceMap.get(currentCity.city) != null) {
                    destinationPriceMap.get(currentCity.city)!!.forEach { destinationWithPrice ->
                        if(!visitedCities.contains(destinationWithPrice[0] as Int)) {
                            if(destinationWithPrice[0] == dst) {
                                lowestPrice = minOf(currentCity.price + destinationWithPrice[1], lowestPrice)
                            }
                            if(currentCity.stops < k) {
                                candidateCities.add(CandidateCity(destinationWithPrice[0], currentCity.price + (destinationWithPrice[1]), currentCity.stops + 1))
                                visitedCities.add(currentCity.city)
                            }
                        }
                }
            } 
        }
        
        return if(lowestPrice < Integer.MAX_VALUE) lowestPrice else -1
    }
}