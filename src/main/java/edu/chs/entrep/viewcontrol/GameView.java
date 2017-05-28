package edu.chs.entrep.viewcontrol;

import edu.chs.entrep.model.*;
import edu.chs.entrep.service.image.ImageFactory;
import edu.chs.entrep.service.sound.SoundFactory;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by josefinesvegborn on 2017-04-03.
 * This class represents the view and control for the actual game. It initiates the player and a new SpaceEntrepreneurs class for each level.
 * It includes event handlers for controlling the character through key presses.
 * It also contains an animation timer in which all graphics are updated and game status is checked.
 */

public class GameView {
    private Player player;
    private Stage theStage;
    private int level;
    private ArrayList<String> input;
    private Canvas canvas;
    private GraphicsContext gc;

    File highscoreFile;
    Highscore highscore;
    HighscoreView highscoreView;


    public GameView(Stage theStage, String playerName) {
        this.player = new Player(playerName);
        this.theStage = theStage;
        this.level = 1;
        this.input = new ArrayList<String>();
        this.canvas = new Canvas(512, 527);
        this.gc = canvas.getGraphicsContext2D();
        this.highscoreFile = new File("src/main/resources/txt/highscore");
        this.highscore = new Highscore(highscoreFile);

        createComponents();
    }

    public void createComponents() {

        Group root = new Group();
        root.getChildren().add(canvas);
        Scene gameScene = new Scene(root);

        Font theFont = Font.font("Futura", 16);
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

        new AnimationTimer() {
            long lastNanoTime = System.nanoTime();      //Check if this can be removed
            SpaceEntrepreneurs spaceEntrepreneurs = new SpaceEntrepreneurs(player, level, highscore);

            /*Gets all images from the Images class*/
            Image background_img = ImageFactory.getImageService().getImage("background", level);
            Image cover_img = ImageFactory.getImageService().getImage("cover", level);
            Image monster1_img = ImageFactory.getImageService().getImage("monster", level);

            Image spaceship_img = ImageFactory.getImageService().getImage("spaceship");
            Image spaceship_img_blink = ImageFactory.getImageService().getImage("spaceshipHit");
            Image spaceship_img_no_blink = ImageFactory.getImageService().getImage("spaceship");
            Image missile_img = ImageFactory.getImageService().getImage("missile");

            Image gameOver_img = ImageFactory.getImageService().getImage("gameOver");
            Image clearedLevel_img = ImageFactory.getImageService().getImage("levelCleared");
            Image life_img = ImageFactory.getImageService().getImage("life");


            //handles counting blinking image of spaceship when getting hit
            double time = 0;
            boolean blink = false;
            boolean hitImageIsOn = false;

            long levelImgCount;
            long gameOverCount;
            long winCount;

            boolean endGame = false;
            boolean newLevel = false;
            boolean runGame = true;

            boolean startMusic = true;

            public void handle(long currentNanoTime) {

                if(startMusic) {
                    SoundFactory.getSoundService().repeatSound("background", true);
                    startMusic = false;
                }

                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;

                //runGame is set to false when transition images are shown
                if (runGame) {

                    // game logic
                    spaceEntrepreneurs.getSpaceship().setVelocity(0, 0);          //How do we set this in logic in spaceEntrepreneurs instead?
                    if (input.contains("LEFT")) {
                        spaceEntrepreneurs.left();
                    }

                    if (input.contains("RIGHT")) {
                        spaceEntrepreneurs.right();
                    }

                    if (input.contains("SPACE")) {           //även tidigare även && !missile.isOnScreen()
                        spaceEntrepreneurs.shoot();
                    }

                    //Updates spaceship, missile and monster position

                    spaceEntrepreneurs.getSpaceship().update(elapsedTime);
                    spaceEntrepreneurs.getMissile().update(elapsedTime);
                    spaceEntrepreneurs.getMonsterMissile().update(elapsedTime);

                    for (Monster monster : spaceEntrepreneurs.getMonsterList())
                        monster.update(elapsedTime);


                    // collision detection - returns true if the spaceship is hit

                    if (spaceEntrepreneurs.collisionCheck()) {
                        spaceship_img = spaceship_img_blink;
                        hitImageIsOn = true;
                        blink = true;
                    }

                    if (blink == true) {
                        time = time + elapsedTime;
                        if (((int) (time * 100) % 10) == 0 && time < 0.5) {
                            if (hitImageIsOn) {
                                spaceship_img = spaceship_img_no_blink;
                                hitImageIsOn = false;
                            } else {
                                spaceship_img = spaceship_img_blink;
                                hitImageIsOn = true;
                            }
                        } else if (time > 0.5) {
                            spaceship_img = spaceship_img_no_blink;
                            blink = false;
                            hitImageIsOn = false;
                            time = 0;
                        }
                    }

                    spaceEntrepreneurs.moveMonster();
                    spaceEntrepreneurs.monsterShoot();

                }

                gc.clearRect(0, 0, 512, 512);
                gc.drawImage(background_img, 0, 0);
                gc.drawImage(spaceship_img, spaceEntrepreneurs.getSpaceship().getPositionX(), spaceEntrepreneurs.getSpaceship().getPositionY());

                //Draws the covers on the right position, the number of covers in the list depends on level
                for (Cover cover : spaceEntrepreneurs.getCoverList()) {
                    gc.drawImage(cover_img, cover.getPositionX(), cover.getPositionY());
                }

                if (spaceEntrepreneurs.getMissile().isOnScreen())
                    gc.drawImage(missile_img, spaceEntrepreneurs.getMissile().getPositionX(), spaceEntrepreneurs.getMissile().getPositionY());

                if (spaceEntrepreneurs.getMonsterMissile().isOnScreen())
                    gc.drawImage(missile_img, spaceEntrepreneurs.getMonsterMissile().getPositionX(), spaceEntrepreneurs.getMonsterMissile().getPositionY());

                for (Monster monster : spaceEntrepreneurs.getMonsterList()) {
                    gc.drawImage(monster1_img, monster.getPositionX(), monster.getPositionY());
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


                /*
                CHECKING THE GAME STATUS
                 */

                // Checks first if any of the transition images (levelup/gameover/win) are already being shown

                //Level Up Image
                if (currentNanoTime - levelImgCount < 3000000000L) {
                    gc.drawImage(clearedLevel_img, 0, 0);
                }

                //Win Game Image
                else if (currentNanoTime - winCount < 4000000000L) {
                    gc.drawImage(clearedLevel_img, 0, 0);
                    if (currentNanoTime - winCount > 3900000000L) {
                        endGame = true;
                        stop();
                    }
                }

                //Game Over Image
                else if (currentNanoTime - gameOverCount < 3000000000L) {
                    gc.drawImage(gameOver_img, 0, 0);
                    if (currentNanoTime - gameOverCount > 2900000000L) {
                        endGame = true;
                        stop();
                    }
                }

                // Then checks if it is time to change to the next level

                else if (newLevel == true) {
                    level = level + 1;
                    runGame = true;
                    levelImgCount = 0;
                    newLevel = false;

                    spaceEntrepreneurs = new SpaceEntrepreneurs(player, level, highscore);

                    background_img = ImageFactory.getImageService().getImage("background", level);
                    cover_img = ImageFactory.getImageService().getImage("cover", level);
                    monster1_img = ImageFactory.getImageService().getImage("monster", level);
                }

                // If not, the game is running and we can check if there are monsters left

                else if (spaceEntrepreneurs.monsterCheck()) {

                    runGame = false;

                    //New level
                    if (!spaceEntrepreneurs.finishedGameCheck()) {
                        newLevel = true;
                        //Sets starting time for showing of the level up image
                        levelImgCount = currentNanoTime;
                    }

                    //Win game
                    else {
                        winCount = currentNanoTime;
                    }
                }

                // If the game is running and the there are monsters left, check for game over

                else if (spaceEntrepreneurs.gameOverCheck()) {
                    //Sets a reference time for when the game over picture was first shown
                    gameOverCount = currentNanoTime;
                    runGame = false;
                }

                //If game is over, launch a new highscore viewcontrol.

                if (endGame == true) {

                    if(spaceEntrepreneurs.checkHighscore() == true) {
                        highscoreView = new HighscoreView(theStage, player);
                    }
                    else {
                        highscoreView = new HighscoreView(theStage);
                    }

                    SoundFactory.getSoundService().repeatSound("background", false);
                    highscoreView.showHighscoreStage();
                }
            }
        }.start();
    }
}



