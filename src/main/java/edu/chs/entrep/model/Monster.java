package edu.chs.entrep.model;

import edu.chs.entrep.Sprite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.lang.String.*;
/**
 * Created by josefinesvegborn on 2017-04-03.
 */
public class Monster extends Sprite{
    //This class should describe the monsters.

    private final String monster1_img = "img/ufo_0.png";
    private final String monster2_img = "img/monster_a0.png";

    private double posX;
    private double posY;
    private double velX;
    private double velY;


    private double width;
    private double height;

    private final ArrayList<Monster> monsterList = new ArrayList<Monster>();

    public Monster() {

       posX = 0;
       posY = 0;
       velX = 0;
       velY = 0;

       width = 10;
       height = 10;



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
