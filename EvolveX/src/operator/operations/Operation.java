/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.operations;

import individuals.IndividualInterface;
import java.util.List;

/**
 *
 * @author anthony
 */
public interface Operation {
    
    public void doOperation(List<IndividualInterface> operands);

    /**
     * Performs the operation on an operand
     * @param operand operand to perform operation on
     */
    public void doOperation(IndividualInterface operand);   
    
}
