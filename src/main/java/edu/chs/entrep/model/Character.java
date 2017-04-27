package edu.chs.entrep.model;

import edu.chs.entrep.Sprite;
import javafx.scene.image.Image;

/**
 * Created by josefinesvegborn on 2017-04-03.
 */
public class Character extends Sprite {

  //  private final String characther_img = "img/spaceship_a1.png";
    private double width;
    private double height;


    public Character() {

       // Image spaceship_img = new Image(characther_img,width, height, true, true );
        //setImage(spaceship_img);
        setPosition(256 ,450 );
        setHeight(50);
        setWidth(50);
    }

    public void shoot(int x, int y){
        Missile missile = new Missile();
        missile.setOnScreen(true);
        missile.setPosition(x + getWidht()/2, y);
        missile.setVelocity(0,-250);
    }

}
