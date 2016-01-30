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
package ec.individuals;

import java.util.ArrayList;

/**
 *
 * @author anthony
 */
public class Genotype extends ArrayList<Chromosome>{
	
	   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Creates a new instance of Genotype */
    public Genotype() 
    {
        super();
    }

    
    public Genotype(int i) 
    {
        super(i);
    }

    /**
     * Copy constructor
     * @param g genotyp to copy
     */
    public Genotype(Genotype g) 
    {
        super(g);
    }

    
    /**
     * @param i
     * @param chrom
     */
    public Genotype(int i, Chromosome chrom) 
    {
        super(i);
        this.add(chrom);
    }
	
	
    
}
