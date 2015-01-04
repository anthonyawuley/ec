/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package individuals.fitnesspackage;

import java.util.ArrayList;


/**
 *
 * @author anthony
 */
public interface PopFitnessInterface {
    
	
    /**
     * @return
     */
	public ArrayList<Double> getGenerationFitness();
	/**
	 * @param generationFitness the generationFitness to set
	 */
	public void setGenerationFitness(ArrayList<Double> generationFitness);
	/**
	 * 
	 * @return
	 */
    public double getBestFitness();
    /**
     * Get Average Fitness of generation
     * @return
     */
    public double getAverageFitness();
    /**
     * Get Standard deviation of generational fitness
     * @return
     */
    public double getStandardDeviationFitness();
    /**
     * set Best Fitness value
     * @param f
     */
    public void setBestFitness(double f);
    /**
     * Set Average Fitness value
     * @param f
     */
    public void setAverageFitness(double f);
    /**
     * Set standard deviation of generation
     * @param f
     */
    public void setStandardDeviationFitness(double f);
    
    /**
     * Set the default value of fitness. This can be given to
     * unevaluated individuals, such as newly created or invalids
     */
    //public void setDefault();
    //GET DEFAULT??!!
   
    
}
