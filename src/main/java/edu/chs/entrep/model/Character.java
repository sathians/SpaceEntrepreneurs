package edu.chs.entrep.model;

import javafx.scene.image.Image;

/**
 * Created by josefinesvegborn on 2017-04-03.
 */
public class Character {

    private Image Character;
    private double posX;
    private double posY;
    private double velX;
    private double velY;

    private double width;
    private double height;

    public Character(){
        posX = 0;
        posY= 0;

        Character = new Image( "img/spaceship_a1.png");

    }

}
