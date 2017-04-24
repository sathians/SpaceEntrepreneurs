package edu.chs.entrep.model;

import edu.chs.entrep.Sprite;
import javafx.scene.image.Image;

/**
 * Created by josefinesvegborn on 2017-04-03.
 */
public class Character extends Sprite{

    private Image Character;
    //private double posX;
    //private double posY;
    //private double velX;
    //private double velY;

    private double width;
    private double height;

    public Character(){
        setPosition(0 ,0 );

        Character = new Image( "img/spaceship_a1.png");

    }

}
