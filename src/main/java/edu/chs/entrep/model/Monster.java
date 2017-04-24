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
    /*
    private double posX;
    private double posY;
    private double velX;
    private double velY;
    */

    private double width;
    private double height;

    public ArrayList<Monster> monsterList = new ArrayList<Monster>();

    public Monster() {

       /*posX = 0;
       posY = 0;
       velX = 0;
       velY = 0;*/
       setPosition(0,0);            //is this needed?
       setVelocity(0,0);
       setImage(monster1_img);
       width = 10;
       height = 10;


    }

    public void initMonsterList(int n){
        if (n == 15){                               //Level 1, n = 15monsters.
            for (int i = 0; i < 3; i++){
                for(int j = 0; j < 6; j++){
                    Monster monster = new Monster();
                    //monster.setImage(monster1_img);
                    monster.setPosition(30 + j*82, 30 + i*50);
                    monsterList.add( monster );
                }
            }
        }
    }

    public ArrayList<Monster> getMonsterList() {
        return monsterList;
    }

    public void updateMosterList(ArrayList<Monster> monsterList){      //Or will this happen automatically, when we update the monster objects in the list=?
        this.monsterList = monsterList;
    }





}
