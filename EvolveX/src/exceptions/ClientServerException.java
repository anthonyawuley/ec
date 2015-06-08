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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;


/**
 *
 * @author anthony
 */

public class ClientServerException extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -3173765161509875057L;

	/** Creates a new instance of InitializationException */
    public ClientServerException() 
    {
        System.out.println("Server-Client InitializationException \n "
        		+ "Please ensure that all three parameters(host,backlog,port) are provided for each host ");
    }
    
  
    
    
}
