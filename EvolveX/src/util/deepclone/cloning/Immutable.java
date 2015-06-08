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
package util.deepclone.cloning;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * marks the specific class as immutable and the cloner avoids cloning it
 * 
 * @author kostantinos.kougios
 *
 * 24 Mar 2011
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Immutable
{
	/**
	 * by default all subclasses of the @Immutable class are not immutable. This can override it.
	 * @return		true for subclasses of @Immutable class to be regarded as immutable from the cloner
	 */
	boolean subClass() default false;
}
