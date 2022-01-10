class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        //Objective -> Return a list such that at each given cell, the the path to both the top/left and bottom/right are non-increasing.
        //If the island is 1x1, the solution is always correct, and simply just the cell.
        //The apparent pattern is BFS, or is it DFS?
        //And we can only move in the cardinal directions. No funny diagonal business here.
        //We basically want to return pair coordinates.
        //[0,0] is at the top left, and increases towards bottom-right.
        //What should we prioritize here? Do we want to go deep down one path, or do we want to spread out our search?
        //I think for now, spreading out in all (possible) directions is going to be my choice, since we may be able to spend less time down a wrong path.
        //Since we visit each node, by default we're running an O(m*n) solution for just traversal.
        //Next, since we run a BFS at each node, we could theoretically reach a worst case scenario of O(m*n) internally...right?
        //So our entire algorithm runs in O(mn(mn)) or O((mn)^2) time, I believe.
        //How can we speed this up?
        //Well, we can't just stop a path from being explored simply because of one given direction. That would block off potential options.
        //Maybe we could try doing something dynamic-programming-ish, where we give each node a "canReachPacific" and "canReachAtlantic"?
        //Or maybe DFS is just faster?
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        for(int row = 0; row < heights.length; row++) {
            for(int col = 0; col < heights[row].length; col++) {
                //Do some operation here to investigate in a BFS manner.
                //We also want to keep track of the nodes we have visited already, and not go through them again.
                HashSet<List<Integer>> visited = new HashSet<>();
                Queue<List<Integer>> BFS = new LinkedList<>();
                BFS.add(Arrays.asList(row, col));
                boolean visitedPacific = false;
                boolean visitedAtlantic = false;
                while(!BFS.isEmpty() && (visitedPacific == false || visitedAtlantic == false)) { //While we haven't visited both yet, or until we exhaust list
                    List<Integer> coords = BFS.poll();
                    if(!visited.contains(coords)) {
                        int currHeight = heights[coords.get(0)][coords.get(1)];
                        if(coords.get(0) == 0 || coords.get(1) == 0) {
                            visitedPacific = true;
                        }
                        if(coords.get(0) == heights.length - 1 || coords.get(1) == heights[row].length - 1) {
                            visitedAtlantic = true;
                        }
                        //4 cardinal directions addition
                        if(coords.get(0)-1 >= 0 && heights[coords.get(0)-1][coords.get(1)] <= currHeight) {
                            BFS.add(Arrays.asList(coords.get(0)-1, coords.get(1)));
                        }
                        if(coords.get(0)+1 < heights.length && heights[coords.get(0)+1][coords.get(1)] <= currHeight) {
                            BFS.add(Arrays.asList(coords.get(0)+1, coords.get(1)));
                        }
                        if(coords.get(1)-1 >= 0 && heights[coords.get(0)][coords.get(1)-1] <= currHeight) {
                            BFS.add(Arrays.asList(coords.get(0), coords.get(1)-1));
                        }
                        if(coords.get(1)+1 < heights[coords.get(0)].length && heights[coords.get(0)][coords.get(1)+1] <= currHeight) {
                            BFS.add(Arrays.asList(coords.get(0), coords.get(1)+1));
                        }
                        visited.add(Arrays.asList(coords.get(0), coords.get(1))); //Add to visited list to not backtrack.
                    }
                }
                if(visitedPacific == true && visitedAtlantic == true) {
                    result.add(Arrays.asList(row, col));
                }
            }
        }
        
        return result;
    }
}