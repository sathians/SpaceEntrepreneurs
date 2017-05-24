package edu.chs.entrep.model;

/**
 * Created by josefinesvegborn on 2017-04-03.
 * This class represents the character, usually a spaceship.
 * It inherits methods from methods for managing position etc from the class Sprite
 */
public class Character extends Sprite {


    public Character() {
        setPosition(256 ,470 );
        setHeight(50);
        setWidth(50);
        setVelocity(0, 0);
    }

}
