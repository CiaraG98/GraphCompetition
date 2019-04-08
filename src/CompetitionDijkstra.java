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
 * This class implements the competition using Dijkstra's algorithm
 */
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class CompetitionDijkstra {

	int sA, sB, sC, N;
	double[][] graph;
	double time;
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     * @throws FileNotFoundException 
    */
    CompetitionDijkstra (String filename, int sA, int sB, int sC) throws FileNotFoundException{
    	this.sA = sA;
    	this.sB = sB;
    	this.sC = sC;
    	//read in file
    	Scanner input = new Scanner(new File(filename));
    	this.N = input.nextInt();//number of V
    	int S = input.nextInt(); //number of E
    	this.graph = new double[N][N];
    	while(input.hasNextInt()) {
    		int v1 = input.nextInt();
			int v2 = input.nextInt();
			double cost = input.nextDouble();
    		for(int i = 0; i < N; i++) {
        		for(int j = 0; j < N; j++) {
        			if(v1 == i && v2 == j) {
        				graph[i][j] = cost;
        			}
        			
        		}
    		}
    	}
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			if(graph[i][j] == 0) {
    				graph[i][j] = Double.POSITIVE_INFINITY;
    			}
    		}
    	}
    	
    	this.time = timeRequiredforCompetition();
    }

    public int getSlowestSpeed(int sA, int sB, int sC) {
    	if(sA < sB && sA < sC) 
    		return sA;
    	else if(sB < sA && sB < sC)
    		return sB;
    	else
    		return sC;
    }
    

    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
    	double[] shortestPaths = new double[N];
    	for(int i = 0; i < N; i++) {
        	double[] paths = dijkstra(graph, i);
        	double l = paths[0];
        	//find longest path
        	for(int j = 0; j < N; j++) {
        		if(paths[j] > l)
        			l = paths[j];
        	}
        	//array of longest paths
        	shortestPaths[i] = l;
    	}
    	double p = shortestPaths[0];
    	for(int v = 0; v < N; v++) {
    		if(shortestPaths[v] > p)
    			p = shortestPaths[v];
    	}
    	int speed = getSlowestSpeed(sA, sB, sC);
    	p *= 1000;
    	double time = p/speed;
    	time = (int) Math.ceil(time);
    	
    	System.out.println("Longest Shortest Path: " + p);
    	System.out.println(Arrays.toString(shortestPaths));
    	return (int) time;
       
    }
    
    public int findMinDistance(double[] dist, boolean[] visited) {
    	double min = Double.POSITIVE_INFINITY;
    	int index = 0;
    	for(int i = 0; i < N; i++) {
    		if(visited[i] == false && dist[i] <= min) {
    			min = dist[i];
    			index = i;
    		}
    	}
    	return index;
    }
    public double[] dijkstra(double[][] graph, int source) {
    	double[] dist = new double[N];//shortest distances from the source
    	boolean[] visited = new boolean[N];//true if the vertex i is included in dist
    	for(int i = 0; i < dist.length; i++) {
    		dist[i] = Double.POSITIVE_INFINITY;
    		visited[i] = false;
    	}
    	dist[source] = 0;
    	
    	for(int i = 0; i < N - 1; i++) {
    		int v = findMinDistance(dist, visited);//finds shortest distance
    		visited[v] = true; 
    		for(int j = 0; j < N; j++) {
    			if(visited[j] == false && graph[v][j] != 0) {
    				if(dist[v] != Double.POSITIVE_INFINITY && dist[v] + graph[v][j] < dist[j]) {
    					dist[j] = dist[v] + graph[v][j];
    				}
    			}
    		}
    		
    	}
    	
    	
    	return dist;
    }
    
    /*public void printMatrix(double[][] graph) {
    	for(int i = 0; i < N; i++) {
    		System.out.println(" ");
    		for(int j = 0; j < N; j++) {
    			System.out.print(graph[i][j] + "  ");
    		}
    	}
    }
    public static void main(String[] args) throws FileNotFoundException {
    	String test = "tinyEWD.txt";
    	CompetitionDijkstra cd = new CompetitionDijkstra(test, 55, 60, 70);    
    //	cd.printMatrix(cd.graph);
    	//System.out.println(" ");
    	//System.out.println(" ");
    	System.out.println("Time: " + cd.timeRequiredforCompetition());
    	
    }*/
}