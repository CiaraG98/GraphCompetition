import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class CompetitionTests  {
	
    @Test
    public void testDijkstraConstructor() throws FileNotFoundException  {
	    CompetitionDijkstra cdTiny = new CompetitionDijkstra("tinyEWD.txt", 70, 60, 55);
        int timeTakenTiny = cdTiny.timeRequiredforCompetition();
        assertEquals(cdTiny.time, timeTakenTiny, 0);
        
        CompetitionDijkstra cd1000 = new CompetitionDijkstra("1000EWD.txt", 70, 60, 55);
        int timeTaken1000 = cd1000.timeRequiredforCompetition();
        assertEquals(cd1000.time, timeTaken1000, 0);
    } 

    @Test
    public void testFWConstructor() throws FileNotFoundException {
        CompetitionFloydWarshall cfTiny = new CompetitionFloydWarshall("tinyEWD.txt", 70, 60, 55);
        int timeTakenTiny = cfTiny.timeRequiredforCompetition();
        assertEquals(timeTakenTiny, cfTiny.time, 0);
        
        CompetitionFloydWarshall cf1000 = new CompetitionFloydWarshall("1000EWD.txt", 70, 60, 55);
        int timeTaken1000 = cf1000.timeRequiredforCompetition();
        assertEquals(timeTaken1000, cf1000.time, 0);
    }
    
    @Test
    public void testFindSlowestSpeed() throws FileNotFoundException {
	    CompetitionDijkstra cd = new CompetitionDijkstra("tinyEWD.txt", 70, 65, 80);
    	assertEquals(65, cd.getSlowestSpeed(cd.sA, cd.sB, cd.sC));
    	cd.sA = 60;
    	assertEquals(60, cd.getSlowestSpeed(cd.sA, cd.sB, cd.sC));
    	
        CompetitionFloydWarshall cf = new CompetitionFloydWarshall("tinyEWD.txt", 70, 60, 90);
        assertEquals(60, cf.getSlowestSpeed(cf.sA, cf.sB, cf.sC));
        cf.sA = 50;
        assertEquals(50, cf.getSlowestSpeed(cf.sA, cf.sB, cf.sC));

    }

    //TODO - more tests
    
}