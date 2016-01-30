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
package ec.util.deepclone.cloning;

import java.util.Collection;
import java.util.Map;

/**
 * @author kostantinos.kougios
 *
 * 21 May 2009
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class FastClonerCustomCollection<T extends Collection> implements IFastCloner
{
	public abstract T getInstance(T o);

    @Override
	public Object clone(final Object t, final IDeepCloner cloner, final Map<Object, Object> clones) {
		final T c = getInstance((T) t);
		final T l = (T) t;
		for (final Object o : l)
		{
            final Object clone = cloner.deepClone(o, clones);
            c.add(clone);
		}
		return c;
	}
}
