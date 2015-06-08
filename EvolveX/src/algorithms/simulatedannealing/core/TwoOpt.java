/*******************************************************************************
 * Copyright (c) 2014, 2015 
 * Anthony Awuley - Brock University Computer Science Department
 * All rights reserved. This program and the accompanying materials
 * are made available under the Academic Free License version 3.0
 * which accompanies this distribution, and is available at
 * https://aawuley@bitbucket.org/aawuley/evolvex.git
 *
 * Contributors:
 *     ECJ                     MersenneTwister & MersenneTwisterFast (https://cs.gmu.edu/~eclab/projects/ecj)
 *     voidException      Tabu Search (http://voidException.weebly.com)
 *     Lucia Blondel       Simulated Anealing 
 *     
 *
 *        
 *******************************************************************************/
package algorithms.simulatedannealing.core;


/**
 * TwoOpt class
 * @author Lucia Blondel
 */

public class TwoOpt {

        private int[] path;
        private double[][] distanceMatrix;
        
        private Tool tool = new Tool();
        
        /**
         * Constructor
         * @param matrix that contains the distances between every cities 
         */
        public TwoOpt(double[][] distanceMatrix) {
                this.distanceMatrix = distanceMatrix;
        }
        
        /**
         * Constructor of a path that it's better (in cost-sense) than the given one
         * @param path
         * @param distanceMatrix
         */
        public void twoOpt(int[] path, final boolean firstImprovement) {
                this.path = path;
                
                double bestGain = Integer.MAX_VALUE;
                int bestI = Integer.MAX_VALUE;
                int bestJ = Integer.MAX_VALUE;

                while(bestGain > 0) {
                        bestGain = 0;
                        // since we want j to be greater than i then we choose the index:
                        // - 0 <= i < length of path - 1
                        // - i+1 <= j < length of path
                        for(int i = 0; i < path.length - 1; i++) {
                                for(int j = i+1; j < path.length; j++) { // i+1
                                        if(i!=j) {
                                                double gain = computeGain(i, j);
                                                // if we find a good gain we set all the value 
                                                if(gain < bestGain) {
                                                        bestGain = gain;
                                                        bestI = i;
                                                        bestJ = j;
                                                        if(firstImprovement == true) {
                                                                break;
                                                        } 
                                                        // exchange bestI and bestJ
                                                        exchange(bestI, bestJ);
                                                }
                                        }
                                }
                                if(firstImprovement == true) {
                                        break;
                                }
                        }
                        // exchange bestI and bestJ
                        if(firstImprovement == true && bestI != Integer.MAX_VALUE && bestJ != Integer.MAX_VALUE) {
                                exchange(bestI, bestJ);
                        }
                }
        }
        
        /**
         * Compute the gain if we exchange edge (path[cityIndex1],path[cityIndex1]+1) and 
         * (path[cityIndex2],path[cityIndex2]+1) with
         * (path[cityIndex1]+1,path[cityIndex2]+1) and (path[cityIndex1],path[cityIndex2])
         * @param cityIndex1
         * @param cityIndex2
         * @return the gain of the change
         */
        public double computeGain(final int cityIndex1, final int cityIndex2) {
                
                int src1 = path[cityIndex1];
                int src2 = path[cityIndex2];
                
                int dest1 = tool.getDestination(path, cityIndex1);
                int dest2 = tool.getDestination(path, cityIndex2);
                
                return ((distanceMatrix[src1][src2] + distanceMatrix[dest1][dest2]) - (distanceMatrix[src1][dest1] + distanceMatrix[src2][dest2]));
        }
        
        /**
         * Make the change (path[cityIndex1],path[cityIndex1]+1) and 
         * (path[cityIndex2],path[cityIndex2]+1) with
         * (path[cityIndex1]+1,path[cityIndex2]+1) and (path[cityIndex1],path[cityIndex2])
         * @param cityIndex1
         * @param cityIndex2
         */
        public void exchange(final int cityIndex1, final int cityIndex2) {
                
                int indexDest1 = tool.getIndexOfDestination(path, cityIndex1);
                int indexDest2 = tool.getIndexOfDestination(path, cityIndex2);

                int[] pathNew = new int[path.length];
                int indexOfPathNew = 0;
                
                // construct the new path
                int i = 0;
                while(i <= cityIndex1) {
                        if(tool.isCityInPath(pathNew, path[i]) == false) {
                                pathNew[indexOfPathNew] = path[i];
                                indexOfPathNew++;
                        }
                        i++;
                }
                
                i = cityIndex2;
                while(i >= indexDest1) {
                        if(tool.isCityInPath(pathNew, path[i]) == false) {
                                pathNew[indexOfPathNew] = path[i];
                                indexOfPathNew++;
                        }
                        i--;
                }
                
                i = indexDest2;
                while(i < path.length) {
                        if(tool.isCityInPath(pathNew, path[i]) == false) {
                                pathNew[indexOfPathNew] = path[i];
                                indexOfPathNew++;
                        }
                        i++;
                }
                
                // copy the new path into the old one
                for(int k = 0; k < pathNew.length; k++) {
                        path[k] = pathNew[k];
                }
                
        }
        
        /**
         * @return path after one TwoOpt
         */
        public int[] getPath() {
                return path;
        }
        
        /**
         * @param set the path
         */
        public void setPath(final int[] path) {
                this.path = path;
        }
                                                  
}
