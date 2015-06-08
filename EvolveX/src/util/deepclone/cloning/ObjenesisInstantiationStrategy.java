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

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;


/**
 * @author kostantinos.kougios
 *
 * 17 Jul 2012
 */
public class ObjenesisInstantiationStrategy implements IInstantiationStrategy
{
	private final Objenesis	objenesis	= new ObjenesisStd();

	@SuppressWarnings("unchecked")
	public <T> T newInstance(Class<T> c)
	{
		return (T) objenesis.newInstance(c);
	}

	private static ObjenesisInstantiationStrategy	instance	= new ObjenesisInstantiationStrategy();

	public static ObjenesisInstantiationStrategy getInstance()
	{
		return instance;
	}
}
