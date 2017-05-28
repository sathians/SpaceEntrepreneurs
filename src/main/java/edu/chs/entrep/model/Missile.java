package edu.chs.entrep.model;

/**
 * Created by josefinesvegborn on 2017-04-03.
 * This class represents the missiles.
 * It inherits methods for managing position etc from the class Sprite.
 */
public class Missile extends Sprite {



    public boolean OnScreen =false;

    public Missile (){

        setVelocity(0,0);
        setPosition(0,0);
        setWidth(20);
        setHeight(20);

    }

    public boolean isOnScreen() {
        return OnScreen;
    }

    public void setOnScreen(boolean x){
        OnScreen = x;
    }
    
}


