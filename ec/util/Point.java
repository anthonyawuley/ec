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
package ec.util;

import java.io.Serializable;

/**
 * 
 * @author Anthony Awuley
 *
 */
public class Point implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** */
	private double x;
	/** */
	private double y;
	/**
	 * 
	 * @param xCord
	 * @param yCord
	 */
	public Point(double xCord, double yCord) 
	{
		this.x = xCord;
		this.y = yCord;
	}
	/**
	 * 
	 * @return
	 */
	public double getX()
	{
		return this.x;
	}
	/**
	 * 
	 * @return
	 */
	public double getY()
	{
		return this.y;
	}
	 
	@Override
	public Point clone() throws CloneNotSupportedException
	{
	        return (Point) super.clone();
    }
	/**
	 * 
	 * @param p2
	 * @return
	 */
	public double calculateEuclidianDistance(Point p2)
	{
		return Math.sqrt( Math.pow(this.x-p2.x,2) + Math.pow(this.y-p2.y,2) ); 
	}
	/**
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public double calculateEuclidianDistance(Point p1, Point p2)
	{
		return Math.sqrt( Math.pow(p1.x-p2.x,2) + Math.pow(p1.y-p2.y,2) ); 
	}

}
