package edu.chs.entrep;


import org.junit.Test;
import static org.junit.Assert.*;

import javafx.scene.image.Image;

/**
 * Created by josefinesvegborn on 2017-04-27.
 */
public class testSprite {

    @Test
    public void testIntersect(){
        Sprite firstSprite = new Sprite();
        Sprite secondSprite = new Sprite();

        Image ufo = new Image("img/ufo_0.png", 20, 20 , false, true);
        Image missile = new Image("img/Tesla_missile_0.png", 20, 20, false, true);

        firstSprite.setImage(ufo);
        secondSprite.setImage(missile);

        boolean intersects = firstSprite.intersects(secondSprite);

        assertTrue(intersects == true);
    }

}
