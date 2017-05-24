package edu.chs.entrep;

import edu.chs.entrep.model.Highscore;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Sathian on 2017-05-24.
 */ //Tests the writeHighscore() and getHighscoreList() methods.

public class testHighscore {

    @Test
    public void testHighscore(){

        File testFile = new File("src/main/resources/txt/highscore");

        Highscore scoreTest = new Highscore(testFile);
        scoreTest.writeHighscore("Sathian",3000, 1);

        assertEquals(scoreTest.getHighscoreList()[1][0], "Sathian" );
        assertEquals(scoreTest.getHighscoreList()[1][1], "3000" );
    }
}


