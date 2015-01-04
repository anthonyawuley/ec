/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.operations;

import individuals.populations.Population;

/**
 *
 * @author anthony
 */
public class StoppingCondition {
    
	private boolean stopWhenSolved;
    private boolean idealIndividualFound;
    
    
    public StoppingCondition(boolean stopFlagSet)
    {
    }
    
    
    public boolean isSatisfied(Population current) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    /**
     * 
     * @param currentCount
     * @param totalCount
     * @return 
     */
    public boolean generationCount(int currentCount,int totalCount)
    {
       return currentCount==totalCount;
    }
    
    public void setIdealIndividualFound(boolean flag)
    {
       this.idealIndividualFound = flag;
    }
    
    
    public boolean getIdealIndividualFound()
    {
       return this.idealIndividualFound;
    }
    
    public void setStopWhenSolved(boolean flag)
    {
       this.stopWhenSolved = flag;
    }
    
    
    public boolean getStopWhenSolved()
    {
       return this.stopWhenSolved;
    }
    
}
