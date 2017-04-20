package edu.chs.entrep.model;

import javafx.scene.image.Image;

import java.lang.*;

/**
 * Created by josefinesvegborn on 2017-04-03.
 */
public class Missile {

    private Image Missile;
    private double posX;
    private double posY;
    private double velY;

    private double width;
    private double height;

    public Missile (){

        posX = Character.posX;
        velY = 0;

        Missile = new Image  ("img/Tesla_missile_0.png");


    }
}


