package edu.chs.entrep.model;

import edu.chs.entrep.Sprite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.lang.String.*;
/**
 * Created by josefinesvegborn on 2017-04-03.
 */
public class Monster {
    //This class should describe the monsters.

    private Image monster;
    private double posX;
    private double posY;
    private double velX;
    private double velY;

    private double width;
    private double height;

     ArrayList<Monster> monsterList = new ArrayList<Monster>();

    public Monster() {

       posX = 0;
       posY = 0;
       velX = 0;
       velY = 0;

       width = 10;
       height = 10;

       monster = new Image( "img/monster_a0.png");

    }

    public ArrayList<Monster> multipleMonster (int number){

        for (int i = 0; i < number; i++)
        {
            Monster monster = new Monster();
            monster.posX = 5 + monster.posX;
            monster.posY = monster.posY; //lägg till if raden är full posY +1
            monsterList.add( monster );
        }

        return monsterList;
    }


}
