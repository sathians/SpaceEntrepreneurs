package edu.chs.entrep.model;

import edu.chs.entrep.Sprite;
import javafx.scene.image.Image;

/**
 * Created by josefinesvegborn on 2017-04-03.
 */
public class Character extends Sprite {

    private final String characther_img = "img/spaceship_a1.png";
    private double width  = 50;
    private double height = 50;

    public Character() {

        Image spaceship_img = new Image(characther_img,width, height, true, true );
        setImage(spaceship_img);
        setPosition(256 ,450 );

    }

}
