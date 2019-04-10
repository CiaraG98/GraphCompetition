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

class Edge{
	int v; 
	double weight;
	public Edge(int v, double w) {
		this.v = v;
		this.weight = w;
	}	
}

public class CompetitionFloydWarshall {

	int sA, sB, sC, N;
	LinkedList<Edge>[] graph;//adjacency list
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     * @throws FileNotFoundException 
     */
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC) {
    	this.sA = sA;
    	this.sB = sB;
    	this.sC = sC;
    	
    	//read in file
    	Scanner input;
		try {
			input = new Scanner(new File(filename));
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
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (NullPointerException e2) {
			e2.printStackTrace();
		}
    	
    }


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
    	if(graph == null || sA < 50 || sB < 50 || sC < 50 || sA > 100 || sB > 100 || sC > 100 || N == 0)
    		return -1;
    	else {
    		//Algorithm
        	double[][] paths = floydWarshall(graph);
        	
        	//finds longest shortest path
        	double l = -1;
        	for(int i = 0; i < paths.length; i++) {
            	for(int j = 0; j < paths[i].length; j++) {
            		if(paths[i][j] > l && paths[i][j] != Double.POSITIVE_INFINITY)
            			l = paths[i][j];
            		if(paths[i][j] == Double.POSITIVE_INFINITY)
            			return -1;
            	}
            }
        	
        	//calculates time taken 
        	int speed = getSlowestSpeed(sA, sB, sC);
        	l *= 1000;
        	double time = l/speed;
        	time = (int) Math.ceil(time);
            return (int) time;
    	}
    	
    }
    
    /*
     * @return int: slowest of the three speeds of the contestants 
     */
    public int getSlowestSpeed(int sA, int sB, int sC) {
    	if(sA < sB && sA < sC) 
    		return sA;
    	else if(sB < sA && sB < sC)
    		return sB;
    	else
    		return sC;
    }
    
    /*
     * @return double[][]: shortest paths from each node to every other node in the graph
     */
    public double[][] floydWarshall(LinkedList<Edge>[] graph){
    	double[][] dist = new double[N][N];
    	LinkedList<Edge> e;
    	for(int c = 0; c < dist.length; c++) {
    		for(int d = 0; d < dist[c].length; d++) {
    			if(dist[c][d] == 0)
    				dist[c][d] = Double.POSITIVE_INFINITY;
    			if(c == d) {
    				dist[c][d] = 0;
    			}
    		}
    	//fills array with edge weights
    	for(int n = 0; n < N; n++) {
			e = graph[n];
    		for(int m = 0; m < e.size(); m++) {
				dist[n][e.get(m).v] = e.get(m).weight;	
    		}
    	}
    	
    	}
    	//Algorithm
    	for(int k = 0; k < N; k++) {
    		for(int i = 0; i < N; i++) {
    			for(int j = 0; j < N; j++) {
    				if(dist[i][j] > dist[i][k] + dist[k][j]) {
    					dist[i][j] = dist[i][k] + dist[k][j];   
    				}
    			}
    		}
    	}
    	
    	return dist;
    }
}