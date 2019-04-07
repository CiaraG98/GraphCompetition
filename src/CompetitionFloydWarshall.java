import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.*;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {

	int sA, sB, sC, N;
	LinkedList<Edge>[] graph;
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     * @throws FileNotFoundException 
     */
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC) throws FileNotFoundException{
    	this.sA = sA;
    	this.sB = sB;
    	this.sC = sC;
    	//read in file
    	Scanner input = new Scanner(new File(filename));
    	this.N = input.nextInt();
    	int S = input.nextInt();
    	
    	//Creates Array of Linked lists, each index is a linked list that contains the vertex that index is connected too and its weight
    	this.graph = new LinkedList[N];
    	for(int i = 0; i < graph.length; i++) {
    		graph[i] =  new LinkedList<Edge>();
    	}
    	while(input.hasNext())
    	{
    		int v1 = input.nextInt();
    		int v2 = input.nextInt();
    		double cost = input.nextDouble();
    		Edge e = new Edge(v2, cost);
    		for(int i = 0; i < graph.length; i++) {
    			if(i == v1) {
    				graph[i].add(e);
    			}
    		}
    	}
    }


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public double timeRequiredforCompetition(){
    	double[][] paths = floydWarshall(graph);
    	double l = paths[0][0];
    	for(int i = 0; i < paths.length; i++) {
        	for(int j = 0; j < paths[i].length; j++) {
        		if(paths[i][j] > l)
        			l = paths[i][j];
        	}
        }
    	int speed = getSlowestSpeed(sA, sB, sC);
    	l *= 1000;
    	double time = l/speed;
    	
        return time;
    }
    
    private int getSlowestSpeed(int sA, int sB, int sC) {
    	if(sA > sB && sA > sC) 
    		return sA;
    	else if(sB > sA && sB > sC)
    		return sB;
    	else
    		return sC;
    }
    
    public double[][] floydWarshall(LinkedList<Edge>[] graph){
    	double[][] dist = new double[N][N];
    	LinkedList<Edge> e;
    	//Edge[][] edgeTo = new Edge[N][N];
    	//fills array with edge weights
    	for(int n = 0; n < N; n++) {
			e = graph[n];
    		for(int m = 0; m < e.size(); m++) {
    			//System.out.println( i + " " + e.get(j).v + " " + e.get(j).weight);
				dist[n][e.get(m).v] = e.get(m).weight;	
				//edgeTo[n][e.get(m).v] = e.get(m);
    		}
    	}
    	for(int c = 0; c < dist.length; c++) {
    		for(int d = 0; d < dist[c].length; d++) {
    			if(dist[c][d] == 0)
    				dist[c][d] = Double.POSITIVE_INFINITY;
    			if(c == d) {
    				dist[c][d] = 0;
    				//edgeTo[c][d] = null;
    			}
    		}
    	}
    	//print
    	/*System.out.println("Before: ");
    	for(int a = 0; a < dist.length; a++) {
    		System.out.println(" ");
    		for(int b = 0; b < dist[a].length; b++) {
    			System.out.print(dist[a][b] + "   ");
    		}
    	}*/
    	//Algorithm
    	for(int k = 0; k < N; k++) {
    		for(int i = 0; i < N; i++) {
    			for(int j = 0; j < N; j++) {
    				if(dist[i][j] > dist[i][k] + dist[k][j]) {
    					dist[i][j] = dist[i][k] + dist[k][j];   
    					//edgeTo[i][j] = edgeTo[k][j];
    				}
    			}
    		}
    	}/*
    	//print again
    	System.out.println(" ");
    	System.out.println(" ");
    	System.out.println("After: ");
    	for(int a = 0; a < dist.length; a++) {
    		System.out.println(" ");
    		for(int b = 0; b < dist[a].length; b++) {
    			System.out.print(dist[a][b] + "   ");
    		}
    	}		
    	/*System.out.println(" ");
    	System.out.println(" ");
    	System.out.println("   0     1   2   3   4   5   6   7 ");
    	System.out.print("------------------------------------");
    	for(int a = 0; a < edgeTo.length; a++) {
    		System.out.println(" ");
    		System.out.print(a + "| ");
    		for(int b = 0; b < edgeTo[a].length; b++) {
    			if(edgeTo[a][b] == null)
    				System.out.print("null  ");
    			else
    				System.out.print(edgeTo[a][b].v + "   ");
    		}
    	}*/
    	return dist;
    }
    
    @Override
   	public String toString(){
   		String graph="";
   		for(int i=0; i<this.graph.length; i++)
   			graph+= "Vertex " + i + " => " + this.graph[i] + "\n";
   		return graph;
   	}

       public static void main(String[] args) throws FileNotFoundException {
       	String test = "1000EWD.txt";
       	CompetitionFloydWarshall cd = new CompetitionFloydWarshall(test, 50, 60, 70);
       	System.out.println(cd.toString());
       	System.out.println(" ");
       	System.out.println(cd.timeRequiredforCompetition());
       }

}