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

import java.io.*;

/**
 * Demonstrates a technique (a hack) to create a "deep clone"
 * in a Java application.
 * @author Alvin Alexander, <a href="http://alvinalexander.com" title="http://alvinalexander.com">http://alvinalexander.com</a>
 */

public class DeepClone {

	public DeepClone() 
	{
		
	}
	
	
	 /**
	   * This method makes a "deep clone" of any object it is given.
	   */
	  public static Object clone(Object object) 
	  {
	    try 
	    {
	      ByteArrayOutputStream baos = new ByteArrayOutputStream();
	      ObjectOutputStream oos = new ObjectOutputStream(baos);
	      oos.writeObject(object);
	      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	      ObjectInputStream ois = new ObjectInputStream(bais);
	      return ois.readObject();
	    }
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	      return null;
	    }
	  }
	  
	  

}
