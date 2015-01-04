package algorithms.alps;

import algorithms.alps.layers.InitializeParams;
import algorithms.alps.system.EvolveGA;
import individuals.populations.Population;

    public interface LayerInterface {

    /**
     * 	
     * @return
     */
	public String toString();
	/**
	 * 
	 * @return
	 */
	public int getId();
	/**
	 * @return gets a count on number of evaluations completed for current layer
	
	public double getEvaluationCounter();
   
     * @param sets number of evaluations completed for current layer
     
	public void setEvaluationCounter(double evaluationCounter);
	*/
	/**
	 * 
	 * @return
	   public void  setMTF(MersenneTwisterFast rng);
	
	   public MersenneTwisterFast getMTF(long seed);
	 * 
	 */
	
    /**
     * s
     * @param count the number of generations for which a layer must evolve
     * this is given as maxCountOfCurrentLayer - MaximumCountOfPreviousLayer
     */
	public void setId(int id);
	/**
	 * 
	 * @param e
	 */
	public void setEvolution(EvolveGA e);
	/**
	 * 
	 * @return
	 */
	public EvolveGA getEvolution();
	/**
	 * 
	 * @param i
	 */
    public void setParameters(InitializeParams i);
	/**
	 * 
	 * @return
	 */
	public InitializeParams getParameters();
	/**
	 * 
	 * @return
	 */
	public boolean getIsActive();
    /**
     * 
     * @param active
     */
	public void setIsActive(boolean active);
    /**
     * 
     * @return
     */
	public boolean getIsBottomLayer();
    /**
     * 
     * @param active
     */
	public void setIsBottomLayer(boolean active);
    /**
     * 
     * @param pop
     */
	public void tryMoveUp(int layer, Population pop);
	/**
	 * 
	 * @return
	 */
	public int getMaxAge();
    /**
     * 
     * @param age
     */
	public void setMaxAgeLayer(int age);
	/**
	 * 
	 * @return
	 */
	public int getGenerationalCount();
    /**
     * 
     * @param count
     */
	public void setGenerationalCount(int count);
	/**
	 * 
	 * @return
	 */
	public int getGenerations();
    /**
     * s
     * @param count the number of generations for which a layer must evolve
     * this is given as maxCountOfCurrentLayer - MaximumCountOfPreviousLayer
     */
	public void setGenerations(int count);
	
	

}
