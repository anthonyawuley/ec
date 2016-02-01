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
package ec.graphing;

import ec.individuals.Chromosome;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.Properties;

import ec.parameter.Parameters;
import ec.util.Constants;

/**
 * 
 * @author Anthony Awuley
 *
 */
@SuppressWarnings("serial")
public class Plot extends javax.swing.JFrame{

	private static Chromosome chromosome;
	private  Properties properties;
	protected static String propertiesFilePath;
    protected Parameters initialiser;
	
	public Plot()
	{
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);
		 
		/*
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(10,"1", "x");
		dataset.setValue(20,"1", "x");
		dataset.setValue(30,"1", "x");
		dataset.setValue(40,"1", "x");
		
		JFreeChart chart = ChartFactory.createLineChart("first jchart", "categoryAxisLabel", "valueAxisLabel", dataset,PlotOrientation.HORIZONTAL,true, true,true);
		CategoryPlot p = chart.getCategoryPlot();
		p.setRangeGridlinePaint(Color.black);
		ChartFrame frame = new ChartFrame("whatever line",chart);
		frame.setVisible(true);
		frame.setSize(450,350);
		*/
		
	}
	
	public Plot(Chromosome c,Properties p) 
	{
		//setChromosome(c);
		//setProperties(p);
		chromosome =c;
		properties =p;
		new Plot().setVisible(true);
		//TODO code application logic here
        //new Run(args);
	}
	
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		//convertToChromosome();
		drawRoute(chromosome,properties,g2);
		 
	}
	 /**
	  * 
	  * @param c
	  * @param p
	  * @param g2
	  */
	 private void drawRoute(Chromosome c,Properties p,Graphics2D g2)
     { 
		 
      	/*
      	 * node.1.x         = 40.00
         * node.1.y         = 50.00
      	 */
      	String[] cord0 = new String[6];
      	String[] cord1 = new String[6];
      	String[] cord2 = new String[6];
      	
      	String[] params = new String[6];
      	int j;
      	double sum = 0;
      	int incx = 1;
      	int incy = 1;
      	int addx = 0;
      	int addy = 0;
      	
      	
		int v = this.getHeight()/2;
	    int h = this.getWidth()/2;
	   
      	
     	/*
     	 * for each customer indexes cord are distributed as below
     	 * CUST NO.   XCOORD.    YCOORD.    DEMAND   READY TIME   DUE DATE   SERVICE TIME
     	 *              0          1           2         3            4            5
     	 */
  	   
  	   int vCapacity = Integer.parseInt(p.getProperty(Constants.VEHICLE_CAPACITY));
        
  	    for(int i=0; i<c.getGenes().size();i++)
        {
  	    	
  	    	
  	    	Point p0 = new Point();
  	    	Point p1 = new Point();
  	    	Point p2 = new Point();
  	    	
  	    	cord0    = p.getProperty(Constants.CO_ORDINATES+".1").split("\\s{1,}"); //get starting point
  	    	p0.setLocation((h-Double.parseDouble(cord0[0])), (v-Double.parseDouble(cord0[1])));
  	    	
  	    	//p0.setLocation((h-c.findGene(1).getAlleles()[0]), (v-c.findGene(1).getAlleles()[1]));
  	    	//p0.setLocation(300,300);
  	    	
          	/*
          	 * when genes are exhausted, loop back j to one
          	 * so cycle completes e.g. 2-4-5--1-3-2, 4-2-5-3-1-4 ... etc
          	 */
          	j = (i==c.getGenes().size()-1)?0:i+1;
          	
          	
  	    	//params        = p.getProperty(Constants.CO_ORDINATES+"."+c.getGenes().get(i).getId()).split("\\s{1,}");
  	    	//double demand = Double.parseDouble(params[2]);
  	    	double demand = c.getGenes().get(i).getAlleles()[2];
  	    	/*
  	    	 * keep adding demand until vehicle capacity is full
  	    	 * it its full, start loading an empty truck
  	    	 */
  	    	if( (sum+demand) <= vCapacity)
  	    	{   //TODO
  	    		//cord1    = p.getProperty(Constants.CO_ORDINATES+"."+c.getGenes().get(i).getId()).split("\\s{1,}");
              	p1.setLocation((h-c.getGenes().get(i).getAlleles()[0]) * incx + addx,(v-c.getGenes().get(i).getAlleles()[1])*incy + addy);
  	    		//cord2    = p.getProperty(Constants.CO_ORDINATES+"."+c.getGenes().get(j).getId()).split("\\s{1,}");
      	    	p2.setLocation((h-c.getGenes().get(j).getAlleles()[0]) * incx + addx,(v-c.getGenes().get(j).getAlleles()[1])*incy + addy);
      	    	
  	    		sum+=demand;
  	    		Line2D lin1 = new Line2D.Float(p1,p2);
  	    		g2.draw(lin1);
  	    	}
  	    	else
  	    	{   //TODO
  	    		//cord1    = p.getProperty(Constants.CO_ORDINATES+"."+c.getGenes().get(j).getId()).split("\\s{1,}"); //new beginning point
              	p1.setLocation((h-c.getGenes().get(j).getAlleles()[0])*incx + addx,(v-c.getGenes().get(j).getAlleles()[1])*incy + addy);
  	    		//cord2    = (j==0)?p.getProperty(Constants.CO_ORDINATES+"."+c.getGenes().get(0).getId()).split("\\s{1,}"):
  	    		//	              p.getProperty(Constants.CO_ORDINATES+"."+c.getGenes().get(j-1).getId()).split("\\s{1,}"); //last point before new route
  	    		
  	    		cord2    = (j==0)?c.getGenes().get(0).getStringAlleles():
  	    			c.getGenes().get(j-1).getStringAlleles(); //last point before new route
	
      	    	p2.setLocation((h-Double.parseDouble(cord2[0]))*incx +addx,(v-Double.parseDouble(cord2[1]))*incy + addy);
  	    		sum = demand;
  	    		Line2D lin1 = new Line2D.Float(p0,p2);
  	    		Line2D lin2 = new Line2D.Float(p0,p1);
  	    		g2.draw(lin1);
  	    		g2.draw(lin2);
  	    	}
  	    	
           }
  	   
     }
	

	

}
