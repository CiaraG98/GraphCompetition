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

class Edge{
	int v; 
	double weight;
	public Edge(int v, double w) {
		this.v = v;
		this.weight = w;
	}
	@Override
	public String toString() {
		return "(" + "connected to: " + v + ", with weight: " + weight + ")";
	}
	
}

public class CompetitionDijkstra {

	int sA, sB, sC, N;
	LinkedList<Edge>[] graph;
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
    	int S = input.nextInt();
    	
    	//Create Array of Linked lists, each index is a vertex and contains the vertex with an edge between it plus the weight of that edge
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
    public int timeRequiredforCompetition(){

        //TO DO
        return -1;
    }
    
    public double dijkstra(LinkedList<Edge>[] graph, int source) {
    	
    	return 1;
    }
    
    @Override
	public String toString(){
		String graph="";
		for(int i=0; i<this.graph.length; i++)
			graph+= "Vertex " + i + " => " + this.graph[i] + "\n";
		return graph;
	}

    public static void main(String[] args) throws FileNotFoundException {
    	String test = "tinyEWD.txt";
    	CompetitionDijkstra cd = new CompetitionDijkstra(test, 1, 2, 4);    	
    	System.out.println(cd.toString());
    }
}