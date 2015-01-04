/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package parameter;

import java.util.Properties;

import exceptions.InitializationException;

/**
 *
 * @author anthony
 */
public interface ParameterInterface {

    /**
     * Set properties
     * @param p object containing properties
     */
    public void setProperties(Properties p);
    public Properties setup() throws InitializationException;
    
    
}