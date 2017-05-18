package edu.chs.entrep.view;

import edu.chs.entrep.model.Highscore;
import edu.chs.entrep.model.Monster;
import edu.chs.entrep.model.Player;
import edu.chs.entrep.model.SpaceEntrepreneurs;
import edu.chs.entrep.service.image.ImageFactory;
//import edu.chs.entrep.service.image.Images;
import edu.chs.entrep.service.Sound;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by josefinesvegborn on 2017-04-03.
 */
public class GameView {
    private Player player;
    private Stage theStage;
    private int level;
    private boolean nextLevel;
    private ArrayList<String> input;
    private Canvas canvas;
    private GraphicsContext gc;
    File highscoreFile;
    Highscore highscore;
    HighscoreView highscoreView;


    //Could we reomve highscore from this file??

    public GameView(Stage theStage, Player player) {
        this.player = player;
        this.theStage = theStage;
        this.level = 1;
        this.nextLevel = false;
        this.input = new ArrayList<String>();
        this.canvas = new Canvas(512, 527);
        this.gc = canvas.getGraphicsContext2D();
        //this.images = ImageFactory.getImageService().getImage();
        this.highscoreFile = new File("src/main/resources/txt/highscore");
        this.highscore = new Highscore(highscoreFile);

        createComponents();
    }

    public void createComponents() {

        Group root = new Group();
        root.getChildren().add(canvas);
        Scene gameScene = new Scene(root);

        Font theFont = Font.font("Futura", FontWeight.LIGHT, 16);
        gc.setFont(theFont);
        gc.setFill(Color.WHITE);
        gc.setLineWidth(1);
        theStage.setScene(gameScene);

        //create event handler for buttons
        gameScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if (!input.contains(code))
                        input.add(code);
                });

        gameScene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove(code);
                });
    }

    public void showGameStage() {
        theStage.show();
    }

    public void startGame() {

        new Sound().bgdSound();

        new AnimationTimer() {
            long lastNanoTime = System.nanoTime();      //Check if this can be removed
            SpaceEntrepreneurs spaceEntrepreneurs = new SpaceEntrepreneurs(player, level, highscore);

            /*Gets all images from the Images class*/
            Image background_img = ImageFactory.getImageService().getImage("background", level);
            Image cover_img = ImageFactory.getImageService().getImage("cover", level);
            Image monster1_img = ImageFactory.getImageService().getImage("monster", level);

            Image spaceship_img = ImageFactory.getImageService().getImage("spaceship");
            Image missile_img = ImageFactory.getImageService().getImage("missile");

            Image gameOver_img = ImageFactory.getImageService().getImage("gameOver");
            Image clearedLevel_img = ImageFactory.getImageService().getImage("levelCleared");
            Image life_img = ImageFactory.getImageService().getImage("life");

            //handles counting blinking image of spaceship when getting hit
            double time = 0;
            boolean blink = false;
            boolean hitImageIsOn = false;

            public void handle(long currentNanoTime) {

                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;

                // game logic
                spaceEntrepreneurs.spaceship.setVelocity(0, 0);          //How do we sett this in logic in spaceEntrepreneurs instead?
                if (input.contains("LEFT")) {
                    spaceEntrepreneurs.left();
                }

                if (input.contains("RIGHT")) {
                    spaceEntrepreneurs.right();
                }

                if (input.contains("SPACE")) {           //även tidigare även && !missile.isOnScreen()
                    spaceEntrepreneurs.shoot();
                }

                // Min tanke här är att med ett visst tids-inervall så skall monstrena hoppa ner ett steg närmare rymdskeppet.
                // if (elapsedTime > 1 && elapsedTime < 2 || elapsedTime > 10 && elapsedTime < 11) {
                //    for(Sprite monster: monsterList)
                //        monster.addPosition(0, 50);
                //}

                //Updates spaceship, missile and monster position

                spaceEntrepreneurs.spaceship.update(elapsedTime);
                spaceEntrepreneurs.missile.update(elapsedTime);
                spaceEntrepreneurs.monsterMissile.update(elapsedTime);

                for (Monster monster : spaceEntrepreneurs.getMonsterList())
                    monster.update(elapsedTime);


                // collision detection - returns true if the spaceship is hit

                if (spaceEntrepreneurs.collisionCheck()) {
                   /* spaceship_img = ImageFactory.getImageService().getImage("spaceshipHit");
                    hitImageIsOn = true;
                    blink = true;*/
                }
/*
                if (blink == true) {
                    time = time + elapsedTime;
                    if (((int) (time * 100) % 10) == 0 && time < 0.5) {
                        if (hitImageIsOn) {
                            spaceship_img = ImageFactory.getImageService().getImage("spaceship");
                            hitImageIsOn = false;
                        } else {
                            spaceship_img = ImageFactory.getImageService().getImage("spaceshipHit");
                            hitImageIsOn = true;
                        }
                    } else if (time > 0.5) {
                        spaceship_img = ImageFactory.getImageService().getImage("spaceship");
                        blink = false;
                        hitImageIsOn = false;
                        time = 0;
                    }
                }
*/
                spaceEntrepreneurs.moveMonster();
                spaceEntrepreneurs.monsterShoot();

                gc.clearRect(0, 0, 512, 512);
                gc.drawImage(background_img, 0, 0);
                gc.drawImage(spaceship_img, spaceEntrepreneurs.spaceship.getPositionX(), spaceEntrepreneurs.spaceship.getPositionY());
                switch (spaceEntrepreneurs.level) {
                    case 1:
                        gc.drawImage(cover_img, spaceEntrepreneurs.cover1.getPositionX(), spaceEntrepreneurs.cover1.getPositionY());
                        gc.drawImage(cover_img, spaceEntrepreneurs.cover2.getPositionX(), spaceEntrepreneurs.cover2.getPositionY());
                        gc.drawImage(cover_img, spaceEntrepreneurs.cover3.getPositionX(), spaceEntrepreneurs.cover3.getPositionY());
                        break;

                    case 2:
                        gc.drawImage(cover_img, spaceEntrepreneurs.cover1.getPositionX(), spaceEntrepreneurs.cover1.getPositionY());
                        gc.drawImage(cover_img, spaceEntrepreneurs.cover3.getPositionX(), spaceEntrepreneurs.cover3.getPositionY());
                        break;

                    case 3:
                        gc.drawImage(cover_img, spaceEntrepreneurs.cover2.getPositionX(), spaceEntrepreneurs.cover2.getPositionY());
                        break;
                }

                if (spaceEntrepreneurs.missile.isOnScreen())
                    gc.drawImage(missile_img, spaceEntrepreneurs.missile.getPositionX(), spaceEntrepreneurs.missile.getPositionY());

                if (spaceEntrepreneurs.monsterMissile.isOnScreen())
                    gc.drawImage(missile_img, spaceEntrepreneurs.monsterMissile.getPositionX(), spaceEntrepreneurs.monsterMissile.getPositionY());

                for (Monster monster : spaceEntrepreneurs.getMonsterList()) {
                    gc.drawImage(monster1_img, monster.getPositionX(), monster.getPositionY());
                    //monster.render( gc );
                }

                String pointsText = "CASH $" + (player.getScore());
                gc.fillText(pointsText, 395, 36);
                //gc.strokeText(pointsText, 360, 36);

                String levelText = "LEVEL " + (level);
                gc.fillText(levelText, 240, 36);


                String lifeText = ("LIFE  ");
                for (int i = 1; i <= spaceEntrepreneurs.player.getLife(); i++) {
                    gc.drawImage(life_img, 30 + 25 * i, 20);
                }
                gc.fillText(lifeText, 20, 36);
                //gc.strokeText(lifeText, 20, 36);

                if (spaceEntrepreneurs.monsterCheck()) {
                    level = level + 1;
                    gc.drawImage(clearedLevel_img, 0, 0);
                    stop();


                    new Timer().schedule(
                            new TimerTask() {
                                @Override
                                public void run() {
                                    start();
                                }
                            },
                            2000
                    );

                    spaceEntrepreneurs = new SpaceEntrepreneurs(player, level, highscore);
                    //Changes images to level3
                    background_img = ImageFactory.getImageService().getImage("background", level);
                    cover_img = ImageFactory.getImageService().getImage("cover", level);
                    monster1_img = ImageFactory.getImageService().getImage("monster", level);
                }

                if (spaceEntrepreneurs.gameOverCheck()) {
                    spaceEntrepreneurs.checkHighscore();

                    gc.drawImage(gameOver_img, 0, 0); //HOW COULD THIS BE SHOWN?

                    stop();

                    highscoreView = new HighscoreView(theStage, player);
                    highscoreView.showHighscoreStage();
                }
            }

        }.start();
    }
}



