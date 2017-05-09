package edu.chs.entrep.model;

import java.util.*;

import edu.chs.entrep.model.*;
import edu.chs.entrep.model.Character;
import edu.chs.entrep.service.Sound;
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
import java.util.concurrent.TimeUnit;

import static javafx.util.Duration.millis;

/**
 * Created by niklasohlsson on 2017-04-27.
 * Revised by sathian and nima 2017-05-02.
 */
public class SpaceEntrepreneurs extends Sound{

    private ArrayList<Monster> monsterList = new ArrayList<Monster>();
    public Player player;
    public Highscore highscore;

    public int level;
    public int count=0;

    public Character spaceship;
    public Missile missile;
    public Missile monsterMissile;

    public boolean gameOver;
    public boolean win;
    public boolean nextLevel;


    public Cover cover1;
    public Cover cover2;
    public Cover cover3;


    //Konstruktor, tar in Player och Level
    public SpaceEntrepreneurs(Player player, int level, Highscore highscore){
        this.player = player;
        this.level = level;
        this.highscore = highscore;

        spaceship = new Character();
        spaceship.setVelocity(0,0);

        missile = new Missile();
        missile.setHeight(20);
        missile.setWidth(20);

        monsterMissile = new Missile();
        monsterMissile.setHeight(20);
        monsterMissile.setWidth(20);

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

                break;

            case 2:
                initMonsterList(4);

                cover1 = new Cover();
                cover3 = new Cover();

                cover1.setPosition(75,400);
                cover3.setPosition(375,400);

                break;

            case 3:
                initMonsterList(5);

                cover2 = new Cover();

                cover2.setPosition(225, 400);

                break;
        }
    }

    public void initMonsterList(int rows){
        for (int i = 0; i < rows; i++){
            for(int j = 0; j < 6; j++){
                Monster monster = new Monster();
                monster.setWidth(20);
                monster.setHeight(20);
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

    public void stopSpaceShip(){
        spaceship.setVelocity(0, 0);
    }

   /* public void shoot(){
        //if (!spaceship.missile.isOnScreen()              //keep the missile from gaining higher speed after every new shot
        spaceship.shoot(spaceship.getPositionX(), spaceship.getPositionY());    //the missile starts from the spaceships position

    }
*/
    public void shoot() {
        if (!missile.isOnScreen()) {
            //missile = new Missile();
            missile.setOnScreen(true);
            missile.setPosition(spaceship.getPositionX() + (spaceship.getWidht() / 2), spaceship.getPositionY());

            shootSound();
            missile.setVelocity(0, -300);
        }
    }

    public void monsterShoot(){
        if (!monsterMissile.isOnScreen()) {
            //missile = new Missile();
            monsterMissile.setOnScreen(true);
            Random random= new Random();
            int index = random.nextInt(monsterList.size());
            monsterMissile.setPosition(monsterList.get(index).getPositionX() + (monsterList.get(index).getWidht() / 2), monsterList.get(index).getPositionY());
            monsterMissile.setVelocity(0, 100);
        }


    }

    public ArrayList<Monster> getMonsterList() {
        return monsterList;
    }
    public void moveMonster() {

        double posR = 0;
        double posL = 512;
        //Timer timer = new Timer();
        //long startTime = System.currentTimeMillis(); //fetch starting time

        for (Monster monster : monsterList) {

            if (posR < monster.getPositionX())
                posR = monster.getPositionX();

            if (posL > monster.getPositionX())
                posL = monster.getPositionX();
        }

        for (Monster monster : monsterList) {

            if (posR < (512 - 40) && monster.getVelocityX() >= 0) {
                monster.setVelocity(25, 0);
                count = 2;
            } else if (posL > 0) {
                monster.setVelocity(-25, 0);
                count = 0;
            } else {
                monster.setVelocity(0, 0);
            }

        }
            if (count==2) {
                for ( Monster monster : monsterList) {
                   // monster.setVelocity(0,0);
                    monster.setPosition(monster.getPositionX(), monster.getPositionY() + 0.01);
                }
            }


               /*if(count==2){
                count=0;
                for(int i = 0; i < monsterList.size(); i++) {
                    monster = monsterList.get(i);
                    monster.setVelocity(0, 0);
                    monster.setPosition(monster.getPositionX() + 0, monster.getPositionY() + 0.1);

                }
            }*/



    }




      //test av monster iter
     /*   if (count==2){
            count=0;
        Iterator<Monster> monsterIter = monsterList.iterator();
        while (monsterIter.hasNext()){
            Monster monster = monsterIter.next();
            monster.setVelocity(0, 0);
            monster.setPosition(monster.getPositionX() + 0, monster.getPositionY() + 0.1);
            if (!monsterIter.hasNext()){
                break;
            }

        }}
*/



    public boolean levelCheck(){
        if (monsterList.isEmpty())
         nextLevel=true;
        return nextLevel;
    }

    public boolean gameOverCheck(){
        if (spaceship.getLife() == 0)
            gameOver=true;
        return gameOver;
    }

    public boolean monsterCheck(){
        if (monsterList.isEmpty())
            win=true;
        return win;
    }


    public void collisionCheck () {

        Iterator<Monster> monsterIter = monsterList.iterator();
        while (monsterIter.hasNext()) {
            Monster monster = monsterIter.next();
            if (missile.isOnScreen() && missile.intersects(monster)) {
                monsterIter.remove();
                missile.setOnScreen(false);
                player.updateScore(100);
            }
        }

        if (monsterMissile.isOnScreen() && monsterMissile.intersects(spaceship)) {
            monsterMissile.setOnScreen(false);
            spaceship.decLife();
        }

        //missile out of bound or intersect with wall
        if (missile.getPositionY() < 0)
            missile.setOnScreen(false);

        if (monsterMissile.getPositionY() > 500)
            monsterMissile.setOnScreen(false);


        //missile intersects with cover
        switch (level) {
            case 1:

                if (missile.intersects(cover1) || missile.intersects(cover2) || missile.intersects(cover3))
                    missile.setOnScreen(false);

                if (monsterMissile.intersects(cover1) || monsterMissile.intersects(cover2) || monsterMissile.intersects(cover3))
                    monsterMissile.setOnScreen(false);

                break;
            case 2:

                if (missile.intersects(cover1) || missile.intersects(cover3))
                    missile.setOnScreen(false);
                if (monsterMissile.intersects(cover1) || monsterMissile.intersects(cover3))
                    monsterMissile.setOnScreen(false);
                break;
            case 3:

                if (missile.intersects(cover2))
                    missile.setOnScreen(false);
                if (monsterMissile.intersects(cover2))
                    monsterMissile.setOnScreen(false);
                break;
        }

    }

    /**
     * Checks if the score makes it to highscorelist by reading old list and writing new score to it if applicable.
     * @return newHighscore true if the score made it to the list.
     */

   public boolean checkHighscore() {
       String[][] oldHighscores = highscore.getHighscoreList();
       int oldScore;
       int position;
       boolean newHighscore = false;
       //Decides position in the list by comparing to the old scores
       for(int i = 0; i < oldHighscores.length; i++) {
           oldScore = Integer.parseInt(oldHighscores[i][1]);
           if (player.getScore() > oldScore) {
               position = i;
               highscore.writeHighscore(player.getName(), player.getScore(), position);
               newHighscore = true;
               break;
           }
       }
       return newHighscore;
   }
}

