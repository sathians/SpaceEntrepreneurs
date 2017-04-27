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
    private Player player;
    private int level;
    private Character spaceship;


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

                Cover cover1 = new Cover();
                Cover cover2 = new Cover();
                Cover cover3 = new Cover();

                cover1.setPosition(75, 400);
                cover2.setPosition(225, 400);
                cover3.setPosition(375, 400);

            case 2:
                initMonsterList(4);

                Cover cover4 = new Cover();
                Cover cover5 = new Cover();

                cover4.setPosition(75,400);
                cover5.setPosition(375,400);

            case 3:
                initMonsterList(5);

                Cover cover6 = new Cover();

                cover6.setPosition(225,400);
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
        if (!spaceship.missile.isOnScreen(){              //keep the missile from gaining higher speed after every new shot
            spaceship.shoot(spaceship.getPositionX(), spaceship.getPositionY());    //the missile starts from the spaceships position
        }
    }


}

