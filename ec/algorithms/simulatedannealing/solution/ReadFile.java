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
package ec.algorithms.simulatedannealing.solution;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Read file 
 * @author Lucia Blondel
 */

public class ReadFile {
        
        private ArrayList<String> cities;
        
        /**
         * Constructor
         */
        public ReadFile(final String fileName) {
                cities = new ArrayList<String>();
                readCitiesFromFile(fileName);
                
        }
        
        
        public void readCitiesFromPropertiesFile(Properties p)
        {
        	
        	
        }
        
        
        
        /**
         * Read the cities that are in the file that we passed to this method
         * @param fileName
         */
        @SuppressWarnings("deprecation")
        public void readCitiesFromFile(final String fileName) {
                final File file = new File(fileName);
                FileInputStream f = null;
                BufferedInputStream buffer = null;
                DataInputStream dataInput = null;
                
                try {
                        f = new FileInputStream(file);
                        buffer = new BufferedInputStream(f);
                        dataInput = new DataInputStream(buffer);
						String line = dataInput.readLine();
                        
                        // until we don't reach the NODE_COORD_SECTION we skip 
                        // the informations that are written in the line

                        while (line.equals("NODE_COORD_SECTION") != true) {
                                line = dataInput.readLine();
                        }
                        
                        line = dataInput.readLine();
                        
                        // until we don't reach the end of file we save all the lines
                        // into our ArrayList<String> cities
                        while (line.equals("EOF") != true) {
                                
                                try {
                                        this.cities.add(line);
                                } catch(Exception e) {}
                                
                                line = dataInput.readLine();
                        }

                        f.close();
                        buffer.close();
                        dataInput.close();
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.exit(0);

                } catch (IOException e) {
                        e.printStackTrace();
                        System.exit(0);

                }       

                return;
        }
        
        /**
         * @return cities 
         */
        public ArrayList<String> getCities() {
                return this.cities;
        }

}

