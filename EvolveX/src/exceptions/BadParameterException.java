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
public class BadParameterException extends IllegalArgumentException {
    
    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Creates a new instance of BadParameterException */
    public BadParameterException() 
    {
      super("Parameter missing or invalid");
    }
    
    /**
     * Creates a new instance of BadParameterException
     * @param key parameter
     */
    public BadParameterException(String key) 
    {
        super("Parameter missing or invalid: "+key);
    }

    /**
     * Creates a new instance of BadParameterException
     * @param parameterName Parameter Name
     * @param className Class where parameter is being loaded from
     */
    public BadParameterException(String parameterName, String className) 
    {
      super("["+className+"] Parameter missing or invalid: "+parameterName);
    }
    

    
}
