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
package individuals;

import java.io.Serializable;
import java.util.ArrayList;

import util.DeepClone;
import individuals.fitnesspackage.Fitness;

/**
 * @author anthony
 */

public class Individual implements IndividualInterface, Serializable  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** */
    private Fitness f;
    /** */
    private boolean evaluated;
    /** */
    private boolean validId;
    /** */
    private double age;
    /** */
    private int layer;
    /** */
    private ArrayList<Integer> hybrid;
    /** */
    private ArrayList<Double> mopFitness;
    /** */
    private double numberOfEvaluations;
    /** */
    public  boolean parentFlag = false; //when individual is used as a parent
    /** */
    private Chromosome chromosome;
    //private int genControl=0;
    
    /**
     * @deprecated
     * keep count of generation in which the individual was used as a parent;
     * this is used in ALPS
     * this counter is incremented by 1 when its used as a parent
     * and by "age of older parent" + 1 when an offspring is produced.
     */
    public int generationRecord = 100000000;
    
    
    /**
     * @param length
     * @return 
     
    public void createChromosome(Chromosome ch)
    {
       this.chromosome.clear();
       
       for(int i=0;i<ch.getChromosomeSize();i++)
       {  //create chromosome using integer gene
          this.chromosome.add(integerGene(1,ch.getChromosomeSize()*14));//*14 to increase the range
       } 
       //set chromosome in base class
       ch.setChromosome(this.chromosome);
    }
    */
    
    /**
     * @return Individual cloned
     
    public Individual clone() 
    {
        Individual ind = new Individual();
        return ind;
    }
   
    /**
     * @throws CloneNotSupportedException 
     * 
    
    public Individual clone() throws CloneNotSupportedException 
    {
        try 
        {
			return (Individual) super.clone();
		} 
        catch (CloneNotSupportedException e) 
        {
			e.printStackTrace();
		}
        
		return new Individual();
    }
     */
   
    /**
     * 
     */
    public Individual clone() 
    {
        return (Individual) DeepClone.clone(this);
    }
    
    
    @Override
    public Fitness getFitness() 
    {
        return this.f;
    }

    @Override
    public void setFitness(Fitness fitness) 
    {
        this.f = fitness;
    }
    
    @Override
    public void setAge(double idAge) 
    {
        this.age = idAge;
    }

    @Override
    public double getAge() 
    {
        return this.age;
    }
    
    @Override
    public Chromosome getGenotype() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Phenotype getPhenotype() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPhenotypeString(int map) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Chromosome getChromosome() 
    {
        return this.chromosome;
    }


    @Override
    public void setGenotype(Chromosome g) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPhenotype(Phenotype p) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setChromosome(Chromosome c) 
    {
        this.chromosome = c;
    }

    @Override
    public boolean isEvaluated() 
    {
        return evaluated;
    }

    @Override
    public void setEvaluated(boolean b) 
    {
        this.evaluated = b;
    }

    @Override
    public boolean isValid() 
    {
        return this.validId;
    }

    @Override
    public void setValid(boolean b) 
    {
        this.validId = b;
    }

	@Override
	public ArrayList<Integer> getHybrid() 
	{
		return hybrid;
	}


	@Override
	public void setHybrid(ArrayList<Integer> hyb) 
	{
		this.hybrid = hyb;
	}


	@Override
	public ArrayList<Double> getMultipleFitness() 
	{
		return this.mopFitness;
	}


	@Override
	public void setMultipleFitness(ArrayList<Double> mop) 
	{
		this.mopFitness = mop;
	}


	@Override
	public void setLayerId(int layer) 
	{
		this.layer = layer;
	}


	@Override
	public int getLayerId() 
	{
		return this.layer;
	}


	@Override
	public double getBirthEvaluations() 
	{
		return this.numberOfEvaluations;
	}


	@Override
	public void setBirthEvaluations(double numberOfEvaluations) 
	{
		this.numberOfEvaluations = numberOfEvaluations;
	}
   
    
    
}
