package edu.chs.entrep.model;

import java.util.ArrayList;
import edu.chs.entrep.model.*;
import edu.chs.entrep.model.Character;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by niklasohlsson on 2017-04-27.
 */
public class SpaceEntrepreneurs {

    private ArrayList<Monster> monsterList = new ArrayList<Monster>();
    public Player player;
    public int level;
    public Character spaceship;
    public Cover cover1;
    public Cover cover2;
    public Cover cover3;

    public int score=0;


    //Konstruktor, tar in Player och Level
    public SpaceEntrepreneurs(Player player, int level){
        this.player = player;
        this.level = level;

        spaceship = new Character();
        spaceship.setVelocity(0,0);

        initLevel(level);
    }

    public void initLevel(int level){

        switch (level) {
            case 1:
                initMonsterList(3);

                cover1 = new Cover();
                cover2 = new Cover();
                cover3 = new Cover();

                cover1.setPosition(75, 400);
                cover2.setPosition(225, 400);
                cover3.setPosition(375, 400);

            case 2:
                initMonsterList(4);

                cover1 = new Cover();
                cover3 = new Cover();

                cover1.setPosition(75,400);
                cover3.setPosition(375,400);

            case 3:
                initMonsterList(5);

                cover2 = new Cover();

                cover2.setPosition(225,400);
        }
    }

    public void initMonsterList(int rows){
        for (int i = 0; i < rows; i++){
            for(int j = 0; j < 6; j++){
                Monster monster = new Monster();
                monster.setPosition(30 + j*82, 30 + i*50);
                monsterList.add( monster );
            }
        }
    }

    public void left(){
        if (spaceship.getPositionX() > 10)
            spaceship.addVelocity(-100, 0);
    }

    public void right(){
        if (spaceship.getPositionX() < 452)
            spaceship.addVelocity(100, 0);
    }

    public void shoot(){
        //if (!spaceship.missile.isOnScreen()              //keep the missile from gaining higher speed after every new shot
        spaceship.shoot(spaceship.getPositionX(), spaceship.getPositionY());    //the missile starts from the spaceships position

    }

    public ArrayList<Monster> getMonsterList() {
        return monsterList;
    }
    public void moveMonster(){

        double posR = 0;
        double posL = 512;

        for(Monster monster: monsterList) {

            if (posR < monster.getPositionX())
                posR = monster.getPositionX();

            if (posL > monster.getPositionX())
                posL = monster.getPositionX();
        }

        for(Monster monster: monsterList) {

            if(posR < (512-40) && monster.getVelocityX() >= 0){
                monster.setVelocity(25,0);
            }else if(posL > 0){
                monster.setVelocity(-25,0);
            }else{
                monster.setVelocity(0,0);
            }
        }
    }

    public void collisionCheck (){

        Iterator<Monster> monsterIter = monsterList.iterator();
        while ( monsterIter.hasNext() )
        {
            Monster monster = monsterIter.next();
            if (spaceship.missile.isOnScreen() && spaceship.missile.intersects(monster) )
            {
                monsterIter.remove();
                spaceship.missile.Erasing();
                score += 1;
            }

        }

        //missile out of bound or intersect with wall
        if (spaceship.missile.getPositionY() < 0)
            spaceship.missile.Erasing();

        //missile intersects with cover
        if (spaceship.missile.intersects(cover1) || spaceship.missile.intersects(cover2)|| spaceship.missile.intersects(cover3))
            spaceship.missile.Erasing();
    }

   public int getScore(){
        return score;
   }


}

