package algorithms.tabu;

/**
*
* @author http://voidException.weebly.com
* Tabu List: To prevent the process from cycling in a small set of solutions, some 
* attribute of recently visited solutions is stored in a Tabu List, which prevents their 
* occurrence for a limited period. For our problem, the attribute used is a pair of 
* nodes that have been exchanged recently. A Tabu structure stores the number of 
* iterations for which a given pair of nodes is prohibited from exchange as 
* illustrated in Figure 3.
*/
public class TabuList {
   
   int [][] tabuList ;
   
   public TabuList(int numCities)
   {
       tabuList = new int[numCities][numCities]; //city 0 is not used here, but left for simplicity
   }
   

   public void tabuMove(int city1, int city2) //tabus the swap operation
   { 
       tabuList[city1][city2]+= 30;
       tabuList[city2][city1]+= 30;
       
   }
   
   public void decrementTabu()
   {
       for(int i = 0; i<tabuList.length; i++)
       {
          for(int j = 0; j<tabuList.length; j++)
          {
             tabuList[i][j]-=tabuList[i][j]<=0?0:1;
          } 
       }
   }
   
}

