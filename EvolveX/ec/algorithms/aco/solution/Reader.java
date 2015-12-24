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
package algorithms.aco.solution;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import util.Point;


public class Reader {

  public static void main(String[] args) throws NumberFormatException,
      IOException 
  {
    final BufferedReader br = new BufferedReader(new FileReader(new File(
        "params/files/heidelberg.tsp")));

    final ArrayList<Point> records = new ArrayList<Point>();

    String line;
    while ((line = br.readLine()) != null) 
    {
      String[] split = line.split(" ");
      records.add(new Point(Double.parseDouble(split[0]), Double.parseDouble(split[1])));
    }

    br.close();

    double distance = 0.0d;
    for (int i = 0; i < records.size() - 1; i++) 
    {
      Point r = records.get(i);
      Point h = records.get(i + 1);
      distance += r.calculateEuclidianDistance(h);
    }
 
    /**
     * calculate distance from last node to depot
     */
    Point r = records.get(records.size() - 1);
    Point h = records.get(0);
    distance += r.calculateEuclidianDistance(h);

    System.out.println("Optimal distance is: " + distance);
  }

  /*
  private static final double 
          calculateEuclidianDistance(double x1, double y1,double x2, double y2) 
  {
    double d = (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    System.out.println(x1 + "/" + y1 + " : " + x2 + "/" + y2 + " = " + d);
    return d;
  }
  */

}