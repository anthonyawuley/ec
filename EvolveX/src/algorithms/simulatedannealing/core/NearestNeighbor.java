package algorithms.simulatedannealing.core;


public class NearestNeighbor {
    
    private int[] path;
    
    private Tool tool = new Tool();
    
    /**
     * Constructor that finds the path and the cost of the nearest neighbor solution
     * @param distanceMatrix
     * @param startCity
     */
    public NearestNeighbor(final double[][] distanceMatrix, final int startCity) {
            
            path = new int[distanceMatrix[0].length];
            
            path[0] = startCity;
            int currentCity = startCity;
            
            /**
             * until there are cities that are not yet been visited
             */
            int i = 1;
            while (i < path.length) {
                    // find next city
                    int nextCity = findMin(distanceMatrix[currentCity]);
                    // if the city is not -1 (meaning if there is a city to be visited
                    if(nextCity != -1) {
                            // add the city to the path
                            path[i] = nextCity;
                            // update currentCity and i
                            currentCity = nextCity;
                            i++;
                    }
            }
    }
    
    /**
     * Find the nearest city that has not yet been visited
     * @param row
     * @return next city to visit
     */
    private int findMin(double[] row) {
            
            int nextCity = -1;
            int i = 0;
            double min = Integer.MAX_VALUE;
            
            while(i < row.length)  {
                    if(tool.isCityInPath(path, i) == false && row[i] < min) {
                            min = row[i];
                            nextCity = i;
                    }
                    i++;
            }
            return nextCity;
    }

    /**
     * @return the array that contains the path 
     */
    public int[] getPath() {
            return path;
    }
}
