class Solution {
    data class Coordinate //Helper data class
    (
        val row : Int,
        val column : Int
    )
    
    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        //The sort of straightforward approach is to simply start at the source, and add to a queue.
        //Basically, BFS
        if(image[sr][sc] == color) return image //No changes are needed as the starting pixel is already the color of choice
        val originalColor = image[sr][sc] //Original color to match with others
        val stackCoords = mutableListOf<Coordinate>(Coordinate(sr, sc))
        while(!stackCoords.isEmpty()) {
            val nextFill = stackCoords.first() //First item on the list
            if(image[nextFill.row][nextFill.column] != color) {
                image[nextFill.row][nextFill.column] = color
            }
            /*
                2 main conditions
                1. Within bounds (obviously)
                2. Same color as original color (touching in 4 directions, and same color)
            */
            if(nextFill.row < image.size - 1 && image[nextFill.row + 1][nextFill.column] == originalColor) { //(Down)
                stackCoords.add(Coordinate(nextFill.row + 1, nextFill.column))
            }
            if(nextFill.row > 0 && image[nextFill.row - 1][nextFill.column] == originalColor) { //(Up)
                stackCoords.add(Coordinate(nextFill.row - 1, nextFill.column))
            }
            if(nextFill.column < image[nextFill.row].size - 1 && image[nextFill.row][nextFill.column + 1] == originalColor) { //(Right)
                stackCoords.add(Coordinate(nextFill.row, nextFill.column + 1))
            }
            if(nextFill.column > 0 && image[nextFill.row][nextFill.column - 1] == originalColor) { //(Left)
                stackCoords.add(Coordinate(nextFill.row, nextFill.column - 1))
            }
             stackCoords.removeAt(0) //Remove the front of the queue
        }
        return image
    }
}