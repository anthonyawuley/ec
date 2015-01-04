/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessevaluation.eval;

import individuals.Chromosome;
import individuals.populations.Population;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;

import util.Point;

/**
 *
 * @author anthony
 */
public class TSP extends WS{
        
    public TSP()
    {
	   
    }
 
	
    /**
     * @return 
     * 
     */
    @Override
    public ArrayList<Double> calcGenerationalFitness(Population pop,Properties p)
   	{
    	
   		  double sum = 0;
   		  this.getGenerationFitness().clear(); //added to control population increment
   		 
   	       for( int i=0; i< pop.size(); i++)
   	       {
   	          //this.setIndividual(pop.get(i));
   	          //this.setDouble(sumDistance(this.getIndividual()));
   	    	   
   	          //specify weight from parameter file
   	          //this.setDouble(sumDistanceTSP(this.getIndividual(),p));
              //pop.get(i).setFitness(new BasicFitness());
   	    	   
   	          pop.get(i).getFitness().setDouble(sumDistanceTSP(pop.get(i),p));
	          
   	          //this.generationFitness.add(i, this.getDouble());
   	          this.getGenerationFitness().add(i, pop.get(i).getFitness().getDouble());
	          
   	          //this.totalFitness += this.getDouble();
   	          sum += pop.get(i).getFitness().getDouble();
   	          //System.out.println("Individual#"+i+" "+this.getDouble());
   	       }
   	    this.setTotalFitness(sum); //total fitness
   	    return this.getGenerationFitness();
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
    public double sumDistanceTSP(Chromosome c, Properties p)
    {
     	/*
     	 * node.1.x         = 40.00
         * node.1.y         = 50.00
     	 */
     	String[] cord0 = new String[6];
     	String[] cord1 = new String[6];
     	String[] cord2 = new String[6];
     	
     	DecimalFormat df = new DecimalFormat("#.#####");
     	Point p0, p1 = null, p2 = null;
     	
     	double distance = 0; //initialize distance
     	cord0    = p.getProperty(""+c.getChromosome().get(0)).split("\\s{1,}"); //get first cordinate in chromosome
     	//System.out.println(cord0);
	    p0       =  new Point(Double.parseDouble(cord0[0]),Double.parseDouble(cord0[1]));
     	
 	    /*
 	     * c.getChromosome().size() - 1 because of forward looking condition (i+1)
 	     */
 	     for(int i=0; i<c.getChromosome().size() - 1;i++)
         {
 	    	cord1    = p.getProperty(""+c.getChromosome().get(i)).split("\\s{1,}");
            p1       = new Point(Double.parseDouble(cord1[0]),Double.parseDouble(cord1[1]));
            
 	    	cord2    = p.getProperty(""+c.getChromosome().get(i+1)).split("\\s{1,}");
            p2       = new Point(Double.parseDouble(cord2[0]),Double.parseDouble(cord2[1]));
     	    	
 	    	distance += p1.calculateEuclidianDistance(p2);
 	    	//System.out.print(" "+cord1[0]+"-"+cord2[0]+" ");
          }
 	     /** note that at the end of the above for-loop, p2 will have last coordinate in chromosome */
 	   distance += p0.calculateEuclidianDistance(p2);
 	    
 	  return Double.parseDouble(df.format(distance));
 	  
    }
    
    
    
    
}
