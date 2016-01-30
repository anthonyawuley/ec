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
package ec.fitnessevaluation.eval;

import ec.individuals.Chromosome;
import ec.individuals.Individual;
import ec.individuals.populations.Population;

import java.util.ArrayList;
import java.util.Properties;

import ec.util.Constants;
import ec.util.Point;
import ec.util.random.RandomGenerator;

public class CVRP extends WS{

	public ArrayList<Integer> hybrid = new ArrayList<>();
	
	public CVRP() 
	{
		// TODO Auto-generated constructor stub
	}
	
	
   
    
   /**
    * 
    * @param c chromosome
    * @param p propertties file
    * @return
    * CHROMOSOME: numbering from 2: see hybrid.TSP
    * 
    * NAME : att48
    * COMMENT : (Rinaldi,Yarrow/Araque)
    * TYPE : CVRP
    * DIMENSION : 48
    * EDGE_WEIGHT_TYPE : ATT
    * CAPACITY : 15
    * NODE_COORD_SECTION DEMAND_SECTION
    * 1	6823	4674	1
    * 2	7692	2247	1
    * 3	9135	6748	1
    * 4	7721	3451	1
    * 5	8304	8580	1
    * -----------
    */
    @SuppressWarnings({ "unchecked", "unused" })
	public ArrayList<Double> CVRPEvaluations(Chromosome c,Properties p)
    {

    	
    	long startTime = getCurrentTime();
    	
    	
    	/*
    	 * node.1.x         = 40.00
         * node.1.y         = 50.00
    	 */
    	String[] cord0,cordz,corda,cordi0,cordi1= new String[6];
    	String[] params = new String[6];
    	ArrayList<Double>  vrptw = new ArrayList<>();
    	ArrayList<Integer>  addedSofar= new ArrayList<>();
    	ArrayList<Integer> chrom = (ArrayList<Integer>) c.convertToInt().clone();
    	double totalDistance = 0;
     	int vCount = 1;
     	this.hybrid.clear();
     	
     	this.hybrid.add(1); // constructing hybrid array-- add depot
    	
 	    int vCapacity = Integer.parseInt(p.getProperty(Constants.VEHICLE_CAPACITY));
 	    cord0    = p.getProperty(Constants.CO_ORDINATES+".1").split("\\s{1,}"); //get depo cordinates
   	    Point p0 = new Point(Double.parseDouble(cord0[0]),Double.parseDouble(cord0[1]));
   	    Point pi1 = p0;  //initialize end pi1 to start point
   	    
   	 
   	    
 	    while(chrom.size()>0) //enter loop while there exists customers
 	    {
 	    	double sumOfDemand   = 0;
 	    	double distanceTime  = 0;
 	    	int    skippedCount  = 0;
 	    	double demand        = 0; //initialize demand of deport to 0
 	 	    int j;
 	 	    
 	    	/**
 	 	     * sort chromosome by
 	 	     * chrom = sortChromosomeByDistance(chrom,p);
 	 	     * gene 1 is not included in chromosome
 	 	     */
 	 	    
 	    	//cordinate for first point in chromosome
 	    	corda          = p.getProperty(Constants.CO_ORDINATES+"."+chrom.get(0)).split("\\s{1,}"); 
 	   	    Point pa       = new Point(Double.parseDouble(corda[0]),Double.parseDouble(corda[1]));
 	   	    demand        += Double.parseDouble(corda[2]); 
 	     	totalDistance += p0.calculateEuclidianDistance(pa); //distance from depot to first coordinate
 	     	addedSofar.add(chrom.get(0));
 	     	
 	 	    for(int i=1; i<chrom.size();i++)
 	        {  
 	            //get last point added so far
 	 	    	cordi0     = p.getProperty(Constants.CO_ORDINATES+"."+addedSofar.get(addedSofar.size()-1)).split("\\s{1,}"); //get current cordinate
	   	        Point pi0  = new Point(Double.parseDouble(cordi0[0]),Double.parseDouble(cordi0[1]));
 	 	    	
	   	        //only for look ahead purposes: used in if(condition)
	   	        cordi1      = p.getProperty(Constants.CO_ORDINATES+"."+chrom.get(i)).split("\\s{1,}"); //get to be visited point
 	           
 	            /*
 	             * customers are added only when they rightfully fall within the given time window. 
 	             * (current time + service time is less than closing time)
 	             * (distanceTime+Double.parseDouble(cordi0[5]))
 	             */
 	            if( (demand+ Double.parseDouble(cordi1[2])) <= vCapacity ) //compare demand to capacity
 	    	    { 	
 	                //get coordinates for feasible point
 	                pi1 = new Point(Double.parseDouble(cordi1[0]),Double.parseDouble(cordi1[1]));
 	 	            totalDistance += pi0.calculateEuclidianDistance(pi1);
 	 	            
 	 	            demand += Double.parseDouble(cordi1[2]); //add demand of next point
 	 	            addedSofar.add(chrom.get(i));
 	 	          //System.out.println("Demand: "+demand);
 	            	
 		        }
 	             
 	          } 
 	 	    
 	 	    //remove all previously added items from chromosome
 	 	    for(int ad : addedSofar)
 	 	    {
 	 	    	this.hybrid.add(ad);
 	 	    	chrom.remove((Object)ad);
 	 	    }
 	 	    this.hybrid.add(1); //end a route
 	 	    //System.out.println("BIG CHROME"+ chrom.size());
 	 	    addedSofar.clear(); //clear content
 	 	    
 	 	    /*
        	 * get last coordinate in previous route
        	 * This will be used to complete total distance traveled by previous route
        	 * by adding distance from this point to depot
        	 */
 	 	     totalDistance += p0.calculateEuclidianDistance(pi1); // depot to last node in previous route
          
 	 	     vCount += (chrom.size()>0)?1:0;
 	 	//System.out.println("VCONT "+ vCount); 	
 	    }
 	   
 	    
 	    vrptw.add(0,(double) vCount);
 	    vrptw.add(1,totalDistance);
 	   
 	  return vrptw;
 	  
    }
    
    
      @SuppressWarnings("unused")
	  private ArrayList<Integer> swapRandomIndex(ArrayList<Integer> c)
      {
    	  int val0 = c.get(0);
    	  int index = RandomGenerator.getRandomNumberBetween(1,c.size()-1);
    	
    	  c.set(0,c.get(index));
    	  c.set(index, val0);
    	
      	 return c;
      }
    

    
       /**
        * huge weight on vehicle... its the only object of interest
        * 
        * However distance is included to make it MOP and to reduce chances of numerous individuals
        * having the same fitness
        * 
        * @return
        */
       @Override
	public ArrayList<Double> calcGenerationalFitness(Population pop,Properties p)
      	{
    	      double vehicleWeight = 100; 
    	      double routeWeight   = 0.001;
      		  double sum = 0;
      		  this.getGenerationFitness().clear(); //added to control population increment
      	       
      	       for( int i=0; i< pop.size(); i++)
      	       {
      	          //this.setIndividual(pop.get(i));
      	          //this.setDouble(sumDistance(this.getIndividual()));
      	          //specify weight from parameter file
     	          
      	          //this.setDouble(weightedFitness(vehicleWeight,routeWeight,p));
                  //pop.get(i).setFitness(new BasicFitness());
     	          pop.get(i).getFitness().setDouble(weightedFitness(pop.get(i),vehicleWeight,routeWeight,p));
      	          
      	          // note that hybrid is set after calling weightedFitness -- which calls CVRPEvaluations
      	          pop.get(i).setHybrid(this.hybrid);
      	          
      	          //this.generationFitness.add(i, this.getDouble());
      	          this.getGenerationFitness().add(i, pop.get(i).getFitness().getDouble());
      	          
      	          //sum += this.getDouble();
      	          sum += pop.get(i).getFitness().getDouble();
      	          //System.out.println("Individual#"+i+" "+this.getDouble());
      	       }
      	       
      	     this.setTotalFitness(sum); //total fitness
      	     
      	   return this.getGenerationFitness();
      	}


       @Override
	public double weightedFitness(Individual i,double alpha, double beta,Properties p)
       {
    	   
    	   ArrayList<Double> vrp = CVRPEvaluations(i.getChromosome(),p);
    	   //set individual fitness values
    	   //in future pass to fitness object. and set fitness property for indiviaul
    	   i.setMultipleFitness(vrp);
    	   
    	   return (alpha*vrp.get(0) + beta*vrp.get(1));
    	   
    	   //return (alpha*sumCars(this.getIndividual(),this.getProperties()) + beta*sumDistanceVRP(this.getIndividual(),this.getProperties()));
    	   
       }
    
    
	

}
