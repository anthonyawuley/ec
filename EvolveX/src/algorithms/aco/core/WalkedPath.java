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
package algorithms.aco.core;

public class WalkedPath {

	 private int[] way;
	 private double distance;

	  public WalkedPath(int[] way, double distance) 
	  {
	    super();
	    this.setWay(way);
	    this.setDistance(distance);
	  }

	/**
	 * @return the distance
	 */
	public double getDistance() 
	{
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) 
	{
		this.distance = distance;
	}

	/**
	 * @return the way
	 */
	public int[] getWay() 
	{
		return way;
	}

	/**
	 * @param way the way to set
	 */
	public void setWay(int[] way) 
	{
		this.way = way;
	}
}
