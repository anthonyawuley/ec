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
package ec.fitnessevaluation.eval;

import ec.individuals.Chromosome;
import ec.individuals.populations.Population;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;

import ec.util.Point;

/**
 *
 * @author Anthony Awuley
 */
public class TSP extends WS{
        
    public TSP()
    {
	   
    }
 
	
    @Override
    public ArrayList<Double> calcGenerationalFitness(Population pop,Properties p)
   	{
   		  double sum = 0;
   		  getGenerationFitness().clear(); //added to control population increment
   		 
   	       for( int i=0; i< pop.size(); i++)
   	       {
   	          pop.get(i).getFitness().setDouble(sumDistanceTSP(pop.get(i).getChromosome(),p));
	          
   	          //this.generationFitness.add(i, this.getDouble());
   	          getGenerationFitness().add(i, pop.get(i).getFitness().getDouble());
	          
   	          sum += pop.get(i).getFitness().getDouble();
   	       }
   	    setTotalFitness(sum); //total fitness
   	    return getGenerationFitness();
   	}
    
    
    
    /**
     * 
     * @param c
     * @return
     * 
     * NODE_COORD_SECTION
     * 1 0.00000e+00 0.00000e+00
     * 2 1.11630e+03 1.55520e+03
     * 3 1.35760e+03 1.47900e+03
     * 4 1.14810e+03 1.77110e+03
     * 5 1.18620e+03 1.79650e+03
     */
    
    /**
     * 
     * @param c
     * @return
     * 
     * NODE_COORD_SECTION
     * 1 0.00000e+00 0.00000e+00
     * 2 1.11630e+03 1.55520e+03
     * 3 1.35760e+03 1.47900e+03
     * 4 1.14810e+03 1.77110e+03
     * 5 1.18620e+03 1.79650e+03
     */
    public double sumDistanceTSP(Chromosome c, Properties p)
    {
     	/*
     	 * node.1.x         = 40.00
         * node.1.y         = 50.00
     	 */
     	//String[] cord0 = new String[6];
     	//String[] cord1 = new String[6];
     	//String[] cord2 = new String[6];
     	
     	DecimalFormat df = new DecimalFormat("#.#####");
     	Point p0, p1 = null, p2 = null;
     	
     	double distance = 0; //initialize distance
     	//TODO
     	//cord0    = p.getProperty(""+c.getGenes().get(0).getId()).split("\\s{1,}"); //get first cordinate in chromosome
     	//System.out.println(cord0);
	    p0       =  new Point(c.getGenes().get(0).getAlleles()[0],c.getGenes().get(0).getAlleles()[1]);
     	
 	    /*
 	     * c.getGenes().size() - 1 because of forward looking condition (i+1)
 	     */
 	     for(int i=0; i<c.getGenes().size() - 1;i++)
         {  //TODO
 	    	//cord1    = p.getProperty(""+c.getGenes().get(i).getId()).split("\\s{1,}");
            p1       = new Point(c.getGenes().get(i).getAlleles()[0],c.getGenes().get(i).getAlleles()[1]);
            //TODO
 	    	//cord2    = p.getProperty(""+c.getGenes().get(i+1).getId()).split("\\s{1,}");
            p2       = new Point(c.getGenes().get(i+1).getAlleles()[0],c.getGenes().get(i+1).getAlleles()[1]);
     	    	
 	    	distance += p1.calculateEuclidianDistance(p2);
 	    	//System.out.print(c.getGenes().get(i).getId()+":"+p1.getX()+"-"+p1.getY()+" ");
 	    	//System.out.print(c.getGenes().get(i).getId()+":");
 	    	
          } //System.out.println();
 	     /* note that at the end of the above for-loop, p2 will have last coordinate in chromosome */
 	   distance += p0.calculateEuclidianDistance(p2);
 	    
 	  return Double.parseDouble(df.format(distance));
 	  
    }
    
    
    
    
}
