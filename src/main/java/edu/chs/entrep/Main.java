package edu.chs.entrep;

import edu.chs.entrep.model.*;
import edu.chs.entrep.service.Images;
import edu.chs.entrep.service.Sound;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;

import java.io.File;
import java.util.ArrayList;


public class Main extends Application {
    public int level = 1;
    public boolean nextLevel = false;
    ArrayList<String> input = new ArrayList<String>();
    Canvas canvas = new Canvas(512, 527);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    Images images = new Images();
    Player player;

    //Instead of a view and control class, this is handled in start()

    public static void main(String[] args) {
        launch(args);
    }

    Scene startScene, highscoreScene, gameScene, nameScene;
    //Init the different game modules

    File highscoreFile = new File("src/main/resources/txt/highscore");

    Highscore highscore = new Highscore(highscoreFile);

    @Override
    public void start(final Stage primaryStage) {


        primaryStage.getIcons().add(new Image("img/monster.png"));
        primaryStage.setTitle("SpaceEntrepreneurs");

        //this makes all stages close and the app exit when the main stage is closed
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });


    //MenuScene
        Label label1 = new Label("Space Entrepreneurs");
        Button highscoreButton = new Button("See highscores");
        Button startButton = new Button("Start");
        VBox layout1 = new VBox(20);
        layout1.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll(label1, startButton, highscoreButton);
        startScene = new Scene(layout1, 512, 512);

        //Highscore Scene
        Label label2 = new Label("Highscore");
        Label highscoreLabel = new Label();
        Button backButton = new Button("Go back");
        VBox layout2 = new VBox(20);
        layout2.setAlignment(Pos.CENTER);
        layout2.getChildren().addAll(label2, highscoreLabel, backButton);
        highscoreScene = new Scene(layout2, 512, 512);

        //Name Scene
        Label nameLabel = new Label("Before you enter the matrix,\nYou have to tell uss your name.");
        TextField nameInput = new TextField();
        nameInput.setMaxWidth(200);
        nameInput.setStyle("-fx-text-inner-color: white; -fx-background-color: black;");
        Button nameButton = new Button("OK");
        VBox layout3 = new VBox(20);
        layout3.setAlignment(Pos.CENTER);
        layout3.getChildren().addAll(nameLabel, nameInput, nameButton);
        nameScene = new Scene(layout3, 512, 512);

        //Game Scene
        Group root = new Group();
        root.getChildren().add(canvas);
        gameScene = new Scene(root);

        primaryStage.setScene(startScene);
        primaryStage.show();


        //Button Handlers

        startButton.setOnAction(e -> {
            primaryStage.setScene(nameScene);
        });

        highscoreButton.setOnAction(e -> {
            highscoreLabel.setText(highscore.readHighscore());
            primaryStage.setScene(highscoreScene);
        });

        backButton.setOnAction(e -> {
            primaryStage.setScene(startScene);
        });

        nameButton.setOnAction(event -> {
            player = new Player(nameInput.getText());
            primaryStage.setScene(gameScene);
            startGame();
        });


        //KeyHandler - set and release

        gameScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if (!input.contains(code))
                        input.add(code);

                    /*if(e.getCode().toString() == "LEFT")
                        spaceEntrepreneurs.left();

                    if(e.getCode().toString() == "RIGHT")
                        spaceEntrepreneurs.right();

                    if(e.getCode().toString() == "SPACE")
                        spaceEntrepreneurs.shoot();
                    */
                });
/*
        gameScene.setOnKeyPressed(this::onKey);
*/
        gameScene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove(code);
                    /*if(e.getCode().toString() == "LEFT")
                        spaceEntrepreneurs.spaceship.setVelocity(0, 0);;
                    if(e.getCode().toString() == "RIGHT")
                        spaceEntrepreneurs.spaceship.setVelocity(0, 0);;
                    if(e.getCode().toString() == "SPACE")
                        spaceEntrepreneurs.shoot();
                    */
                });

    }

    /*public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }*/


    public void startGame() {

        new Sound().bgdSound();

        new AnimationTimer() {
            long lastNanoTime = System.nanoTime();      //Check if this can be removed
            SpaceEntrepreneurs spaceEntrepreneurs = new SpaceEntrepreneurs(player, level, highscore);

            /*Gets all images from the Images class*/
            Image background_img = images.getBackgroundImage(level);
            Image cover_img = images.getCoverImage(level);
            Image monster1_img = images.getMonsterImage(level);

            Image spaceship_img = images.getSpaceshipImage();
            Image missile_img = images.getMissileImage();

            Image gameOver_img = images.getGameOverImage();
            Image life_img = images.getLifeImage();

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


                // collision detection
                spaceEntrepreneurs.collisionCheck();
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
                for (int i = 1; i <= spaceEntrepreneurs.spaceship.getLife(); i++) {
                    gc.drawImage(life_img, 30 + 25 * i, 20);
                }
                gc.fillText(lifeText, 20, 36);
                //gc.strokeText(lifeText, 20, 36);

                if (spaceEntrepreneurs.gameOverCheck()) {
                    spaceEntrepreneurs.checkHighscore();
                    gc.drawImage(gameOver_img, 0, 0);
                    stop();
                }

                if (spaceEntrepreneurs.monsterCheck()) {
                    level = level + 1;
                    nextLevel = true;  //VAD GÖR DENNA?
                    spaceEntrepreneurs = new SpaceEntrepreneurs(player, level, highscore);

                    //Changes images to level3
                    background_img = images.getBackgroundImage(level);
                    cover_img = images.getCoverImage(level);
                    monster1_img = images.getMonsterImage(level);
                }

            }
        }.start();

    }

}
