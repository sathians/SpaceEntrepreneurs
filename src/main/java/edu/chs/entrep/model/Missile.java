package edu.chs.entrep.model;

import edu.chs.entrep.Sprite;
import javafx.scene.image.Image;

import java.lang.*;

/**
 * Created by josefinesvegborn on 2017-04-03.
 */
public class Missile extends Sprite {

    //private Image Missile;
    //private double posX;
    //private double posY;
    //private double velY;

    //private double width;
    //private double height;

  // private final String missileImg = "img/Tesla_missile_0.png";

    public boolean OnScreen =false;

    public Missile (){
       // Image missile = new Image(missileImg,  20,20,false, true);
        //setImage(missile);

        setVelocity(0,0);
        setPosition(0,0);

    }

    /*public Missile (int x, int y){

        initMissile(x,y);
    }*/

    public boolean isOnScreen() {
        return OnScreen;
    }

    public void setOnScreen(boolean x){
        OnScreen = x;
    }

    /*public void initMissile(int x, int y){

        Image missile = new Image(missileImg);
        setImage(missile);
        setPosition(x,y);
    }*/
}


