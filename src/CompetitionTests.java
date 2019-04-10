import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

/*
 * 1. Justify the choice of the data structures used in CompetitionDijkstra and
	CompetitionFloydWarshall
	
	Answer: CompetitionDijkstra uses a 2d array to form an adjacency matrix. This allowed the algorithm to adapt to the data structure
	and access the nodes easily in order to compute the shortest path for each vertex. CompetitionFloydWarshall uses an array of linked lists to form an adjacency 
	list. Each index of the array contains a linked list, each node in which is an 'Edge' object that is connected to the vertex that is the index of the array. 
	This is an interesting and unique way to represent a graph and the floydWarshall algorithm was able to access the nodes by easily traversing the array 
	of linked lists. 
	
   2. Explain theoretical differences in the performance of Dijkstra and Floyd-Warshall algorithms
	in the given problem. Also explain how would their relative performance be affected by the
	density of the graph. Which would you choose in which set of circumstances and why? 
	
	Answer: Dijkstra's Algorithm uses the graph as reference and returns arrays or a shortest path tree (SPT) of the shortest paths for each node. 
	FloydWarshall algorithm changes the physical graph that is passed into the algorithm. Performance wise, Dijkstra uses single source shortest path 
	while floydWarhshall uses all pairs shortest paths. They array implementation for Dijkstra is optimal for dense graphs, and uses non-negative weights.
	When running the tests it was observed that the more dense the graph was, Dijkstra took longer to run than floydWarshall. FloydWarshall seemed to have the 
	same run time no matter the size of the graph. 
 */

public class CompetitionTests  {
	
    @Test
    public void testDijkstraConstructor() {
	    CompetitionDijkstra cdTiny = new CompetitionDijkstra("tinyEWD.txt", 70, 60, 55);
        int timeTakenTiny = cdTiny.timeRequiredforCompetition();
        assertEquals(34, timeTakenTiny);
    } 

    @Test
    public void testFWConstructor() {
        CompetitionFloydWarshall cfTiny = new CompetitionFloydWarshall("tinyEWD.txt", 70, 60, 55);
        int timeTakenTiny = cfTiny.timeRequiredforCompetition();
        assertEquals(34, timeTakenTiny);
        
        CompetitionFloydWarshall cf1000 = new CompetitionFloydWarshall("1000EWD.txt", 70, 60, 55);
        int timeTaken1000 = cf1000.timeRequiredforCompetition();
        assertEquals(26, timeTaken1000);
    }
    
    @Test
    public void testFindSlowestSpeed() {
	    CompetitionDijkstra cd = new CompetitionDijkstra("tinyEWD.txt", 70, 65, 80);
    	assertEquals(65, cd.getSlowestSpeed(cd.sA, cd.sB, cd.sC));
    	cd.sA = 60;
    	assertEquals(60, cd.getSlowestSpeed(cd.sA, cd.sB, cd.sC));
    	
        CompetitionFloydWarshall cf = new CompetitionFloydWarshall("tinyEWD.txt", 70, 60, 90);
        assertEquals(60, cf.getSlowestSpeed(cf.sA, cf.sB, cf.sC));
        cf.sA = 50;
        assertEquals(50, cf.getSlowestSpeed(cf.sA, cf.sB, cf.sC));
    }
    
    @Test
    public void testInvalidSpeed() {
	    CompetitionDijkstra cd = new CompetitionDijkstra("tinyEWD.txt", 70, 49, 80);
	    assertEquals(-1, cd.timeRequiredforCompetition());
	    
        CompetitionFloydWarshall cf = new CompetitionFloydWarshall("tinyEWD.txt", 70, 49, 90);
	    assertEquals(-1, cf.timeRequiredforCompetition());
    }

    
}