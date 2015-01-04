/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package individuals;

import java.util.ArrayList;

/**
 *
 * @author anthony
 */
public class Genotype extends ArrayList<Chromosome>{
	
	   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Creates a new instance of Genotype */
    public Genotype() 
    {
        super();
    }

    
    public Genotype(int i) 
    {
        super(i);
    }

    /**
     * Copy constructor
     * @param g genotyp to copy
     */
    public Genotype(Genotype g) 
    {
        super(g);
    }

    
    /**
     * @param i
     * @param chrom
     */
    public Genotype(int i, Chromosome chrom) 
    {
        super(i);
        this.add(chrom);
    }
	
	
    
}
