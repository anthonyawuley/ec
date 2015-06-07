/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.random;

import individuals.Gene;

import java.util.ArrayList;

import util.Constants;

/**
 *
 * @author anthony
 */
public class GenerateMask extends RandomGenerator{
    
    private static String mask;
    private static int maskLength;
    
    /**
     * RANDOMLY GENERATE MASK: to be used in crossover
     * @param length 
     */
    private static String generateMask()
    {
         mask = "";
         for(int i=0;i<maskLength;i++)
         {
            mask += getRandomNumberBetween(0,1);
         }
         return mask;
    }
    
     
    /**
     * 
     * @param c1
     * @param value
     * @return 
     */
    public static boolean isExistIndex(ArrayList<Integer> c1, String value)
    { 
       for (int i = 0; i < c1.size(); i++)
       {
           if (value.equals(c1.get(i)+""))
           {  
               return true;
           }
       } 
      return false;
    }
    
    /**
     * make exhaustive search for existence of value in c1
     * @param c1
     * @param value
     * @return 
     */
    public static boolean isExistIndexPareto(ArrayList<Double> c1, Double value)
    { boolean b = true;
        /*
         * comparator used to be value<=c1.get(i) however does not martch ross data
         * found on https://www.cosc.brocku.ca/Offerings/5P71/misc/Notes_MOP.pdf
         * <= matches same ranks (rank1,rank2),(rank1,rank2) correctly unlike "<"
         */
       for (int i = 0; i < c1.size(); i++)
       {  
         b = b && (value<=c1.get(i));
       } 
      return b;
    }
    
    
    /**
     * 
     * @param arrayList
     * @param gene
     * @return 
     */
    public static int returnIndex(ArrayList<Gene> arrayList, Gene gene)
    {
       for (int i = 0; i < arrayList.size(); i++)
       {
           if (gene.equals(arrayList.get(i)))
           {  
               return i;
           }
       } 
      return -1;
    }
    
     /**
     * 
     * @param c1
     * @param value
     * @return 
     */
    public static int returnIndexD(ArrayList<Double> c1, String value)
    {
       for (int i = 0; i < c1.size(); i++)
       {
           if (value.equals(c1.get(i)+""))
           {  
               return i;
           }
       } 
      return -1;
    }
    
    /**
     * 
     * @param c1
     * @param value
     * @return 
     */
    public static int returnIndexS(ArrayList<Double> parent,ArrayList<String> c1, String value)
    {
       for (int i = 0; i < c1.size(); i++)
       {
           if (value.equals(c1.get(i)) && (parent.get(i)== (double)Constants.DEFAULT_WORSE_FITNESS) ) //ensure index doesnt already exist
           {  
               return i;
           }
       }  
      return -1;
    }
    
    /**
     * 
     * @param c1
     * @param value
     * @return 
     */
    public static int returnIndexS(ArrayList<String> c1, String value)
    {
       for (int i = 0; i < c1.size(); i++)
       {
           if (value.equals(c1.get(i)))
           {  
               return i;
           }
       } 
      return -1;
    }
    
    /**
     * 
     * @param length
     * @return 
     */
    public static String getMask(int length)
    {
       maskLength = length;
       return generateMask();
    }
    
}
