package edu.chs.entrep;

import edu.chs.entrep.model.Character;
import edu.chs.entrep.model.Missile;
import edu.chs.entrep.model.Monster;
import edu.chs.entrep.model.SpaceEntrepreneurs;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.model.EachTestNotifier;

import static org.junit.Assert.assertTrue;

/**
 * Created by Sathian on 2017-05-24.
 */ //Tests the different intersections that could happen during the game.

public class testIntersect {

    Monster monster = null;
    Missile missile = null;
    Missile missile1 = null;
    Character spaceship = null;

    @Before
    public void beforeEachTest() {
     monster = new Monster();
     missile = new Missile();
     missile1 = new Missile();
     spaceship = new Character();
}
    @Test
    public void testMonsterHit(){
        monster.setPosition(10, 10);
        missile.setPosition(10,10);
        assertTrue(missile.intersects(monster));
    }

    @Test
    public void testMissiles(){

        missile.setPosition(missile1.getPositionX(), missile1.getPositionY());
        assertTrue(missile.intersects(missile1));
    }

    @Test
    public void testSpaceshipHit(){

        spaceship.setPosition(missile.getPositionX(), missile.getVelocityY());
        assertTrue(missile.intersects(spaceship));
    }
}
