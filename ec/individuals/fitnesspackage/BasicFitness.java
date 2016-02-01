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
package ec.individuals.fitnesspackage;

import ec.individuals.Chromosome;
import ec.individuals.Individual;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Defines basic propertis of an individual fitness
 * 
 * @author Anthony Awuley
 */
public class BasicFitness implements Fitness, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID    = 1L;
	/** */
	public static final double DEFAULT_FITNESS    = 100000000000.0;
	/** */
    private static double Min_Double              = 0.0;
    /** */
    private static double Max_Double              = DEFAULT_FITNESS;
    /** */
    private static int Min_Int                    = 0;
    /** */
    private static int Max_Int                    = (int)DEFAULT_FITNESS;
    /** */
    private static double Best_Fitness            = 0.0;
    /** */
    private double value;
    /** */
    protected ArrayList<Double> generationFitness = new ArrayList<>();
    /** */
    private Individual individual;
    /** */
    private ArrayList<Double> multiObjectiveFitnes= new ArrayList<>();
   
    
    
    /** Creates a new instance of BasicFitness */
    public BasicFitness() 
    {
        this.value = 0;
    }
    /**
     * 
     * @param c
     * @return
     */
    public ArrayList<Double> getMultiObjectiveFitness(Chromosome c)
    {
        return this.multiObjectiveFitnes;
    }
    /**
     * Creates new instance of BasicFitness - overloaded constructor
     * @param f fitness value
     * @param i reference to an individual
     */
    public BasicFitness(double f, Individual i) 
    {
        this.value = f;
        this.individual = i;
    }
    /**
     * create individual with fitness properties -overload constructor
     * @param i 
     */
    public BasicFitness(Individual i) 
    {
        this.individual = i;
    }
    /**
     * 
     */
    @Override
    public double getBestFitness()
    {
      return BasicFitness.Best_Fitness;
    }
    /**
     * 
     */
    @Override
    public double setMaxDoubleFitness(int maxD) {
        
        return BasicFitness.Max_Double;
    }
    /**
     * 
     */
    @Override
    public double getMaxDoubleFitness() {
        return BasicFitness.Max_Double;
    }
    /**
     * 
     */
    @Override
    public double setMinDoubleFitness(int minD) {
        
        return BasicFitness.Min_Double;
    }
    /**
     * 
     */
    @Override
    public double getMinDoubleFitness() 
    {
        return BasicFitness.Min_Double;
    }
    /**
     * 
     * @param maxF
     * @return 
     */
    @Override
    public int setMaxIntFitness(int maxF) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
	public int getMaxIntFitness() 
    {
        return BasicFitness.Max_Int;
    }
    /**
     * 
     * @param minF
     * @return 
     */
    @Override
    public int setMinIntFitness(int minF) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * 
     */
    @Override
	public int getMinIntFitness() 
    {
        return BasicFitness.Min_Int;
    }
    /**
     * 
     */
    @Override
	public void setDefault() 
    {
        value = BasicFitness.DEFAULT_FITNESS;
    }
    /**
     * 
     * @return
     */
    public double getDefaultFitness() 
    {
        return BasicFitness.DEFAULT_FITNESS;
    }
    /**
     * 
     */
    @Override
	public double getDouble() 
    {
        return this.value;
    }
    /**
     * 
     */
    @Override
	public Individual getIndividual() 
    {
        return this.individual;
    }
    /**
     * 
     * @param i 
     */
    @Override
	public void setIndividual(Individual i) 
    {
        this.individual  = i;
    }
    /**
     * 
     */
    @Override
	public void setDouble(double f) 
    {
	  if(Double.isNaN(f) || Double.isInfinite(f)) 
	  {
	    f = BasicFitness.Max_Double;
	  }
        this.value = f;
    }
    /**
     * 
     */
    @Override
	public int getInt() 
    {
        return (int)this.value;
    }
    /**
     * 
     */
    @Override
	public void setInt(int f) 
    {
        this.value = f;
    }
    
}
