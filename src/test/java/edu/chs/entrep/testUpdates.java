package edu.chs.entrep;

import edu.chs.entrep.model.Highscore;
import edu.chs.entrep.model.Monster;
import edu.chs.entrep.model.Player;
import edu.chs.entrep.model.SpaceEntrepreneurs;
import edu.chs.entrep.view.GameView;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXParseException;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Sathian on 2017-05-24.
 */
public class testUpdates {

    Player player = null;
    File testFile = null;
    Highscore highscore = null;
    Monster monster = null;
    SpaceEntrepreneurs testSpace = null;

    @Before
    public void beforeEachTest() {
        player = new Player("Nima");
        testFile = new File("src/main/resources/txt/highscore");
        highscore = new Highscore(testFile);
        testSpace = new SpaceEntrepreneurs(player, 1, highscore);
        monster = new Monster();
    }

    @Test
    public void testNextLevel(){

        monster.initMonsterList(5);
        testSpace.getMonsterList().clear();
        assertTrue(testSpace.monsterCheck());
    }

    @Test
    public void testDecLife(){

        int currentLife = player.getLife();
        player.decLife();
        assertTrue(player.getLife() != currentLife);
    }




}
