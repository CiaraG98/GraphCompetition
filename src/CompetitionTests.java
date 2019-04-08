import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;


public class CompetitionTests  {
	
    @Test
    public void testDijkstraConstructor() {
	    CompetitionDijkstra cdTiny = new CompetitionDijkstra("tinyEWD.txt", 70, 60, 55);
        int timeTakenTiny = cdTiny.timeRequiredforCompetition();
        assertEquals(timeTakenTiny, 34);
        
        CompetitionDijkstra cd1000 = new CompetitionDijkstra("1000EWD.txt", 70, 60, 55);
        int timeTaken1000 = cd1000.timeRequiredforCompetition();
       // assertEquals(timeTaken1000, );
    } 

    @Test
    public void testFWConstructor() {
        CompetitionFloydWarshall cfTiny = new CompetitionFloydWarshall("tinyEWD.txt", 70, 60, 55);
        int timeTakenTiny = cfTiny.timeRequiredforCompetition();
        assertEquals(timeTakenTiny, cfTiny.time, 0);
        
        CompetitionFloydWarshall cf1000 = new CompetitionFloydWarshall("1000EWD.txt", 70, 60, 55);
        int timeTaken1000 = cf1000.timeRequiredforCompetition();
        assertEquals(timeTaken1000, cf1000.time, 0);
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
    
    /*@Test
    public void testTimeForCompetition() {
	    CompetitionDijkstra cd = new CompetitionDijkstra("", 70, 65, 80);
	    int result = cd.timeRequiredforCompetition();
	    assertEquals(cd.time, result, 0);
	    
        CompetitionFloydWarshall cf = new CompetitionFloydWarshall("", 70, 60, 55);
        assertEquals(cf.time, cf.timeRequiredforCompetition(), 0);
    }*/

    
}