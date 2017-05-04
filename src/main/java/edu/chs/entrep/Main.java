package edu.chs.entrep;

import edu.chs.entrep.model.*;
import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;


public class Main extends Application {
    public int level=1;

    //Instead of a view and control class, this is handled in start()

    public static void main(String[] args)
    {
        launch(args);
    }

    Scene startScene, highscoreScene, gameScene;

    @Override
    public void start(final Stage primaryStage)
    {

        primaryStage.setTitle("SpaceEntrepreneurs, the endless Game!");
        final String[] highscoreString = {""};


        primaryStage.setTitle("SpaceEntrepreneurs");

//Menu Scene
        Label label1= new Label("Space Entrepreneurs");
        Button highscoreButton = new Button("See highscores");
        Button startButton = new Button("Start");
        highscoreButton.setOnAction(e ->  primaryStage.setScene(highscoreScene));
        startButton.setOnAction(e ->  primaryStage.setScene(gameScene));
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, startButton, highscoreButton);
        startScene = new Scene(layout1, 512, 512);

//Highscore Scene
        Label label2= new Label("Highscore");
        Label highscores = new Label("highscores");
        Button backButton= new Button("Go back");
        backButton.setOnAction(e -> primaryStage.setScene(startScene));
        VBox layout2= new VBox(20);
        layout2.getChildren().addAll(label2, highscores, backButton);
        highscoreScene = new Scene(layout2,300,250);

//Game Scene
        Group root = new Group();
        Canvas canvas = new Canvas( 512, 512 );
        root.getChildren().add( canvas );
        gameScene = new Scene(root);

        primaryStage.setScene(startScene);
        primaryStage.show();

        //KeyHandler - set and release
        final ArrayList<String> input = new ArrayList<String>();

        gameScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                }
        );

/*
        gameScene.setOnKeyPressed(this::onKey);
*/
        gameScene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                });

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.DARKRED );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);

        //Init the different game modules

        Player player = new Player("Ni");

        final SpaceEntrepreneurs spaceEntrepreneurs = new SpaceEntrepreneurs(player, level);

        //Gammal kod från innan uppdelning av main in i SpaceEntrepreneurs och main(View,Controller)
        /*
        final Character spaceship = new Character();
        //final Missile missile = new Missile();

        final Monster monster = new Monster();
        monster.initMonsterList(15);

        final ArrayList<Sprite> monsterList = new ArrayList<Sprite>();
        //Tried out a path, Niklas
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 6; j++){
                Sprite monster = new Sprite();
                monster.setImage("img/ufo_0.png");
                monster.setPosition(30 + j*82, 30 + i*50);
                monsterList.add( monster );
            }
        }

        // init three covers
        final Cover cover1 = new Cover();
        final Cover cover2 = new Cover();
        final Cover cover3 = new Cover();

        cover1.setPosition(75,400);
        cover2.setPosition(225,400);
        cover3.setPosition(375,400);
        */

        //Lägg i en annan klass så man slipper se det

        final Image background_img = new Image("img/background.png", 512, 512, false, true);
        final Image cover_img = new Image("img/Firewall_a0.png", 70, 50, false, true);
        final Image spaceship_img = new Image("img/spaceship_a1.png", 50, 50, false, true);
        final Image monster1_img = new Image("img/ufo_0.png", 40, 40, false, true);
        final Image missile_img = new Image("img/Tesla_missile_0.png", 20, 20, false, true);
        final Image gameOver_img = new Image("img/gameOver.png",512,512,false,true);

        final LongValue lastNanoTime = new LongValue(System.nanoTime());      //Check if this can be removed

        final IntValue score = new IntValue(0);


        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;

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


                //Monster Path implementation, count out the position farest to the right/left
                /*double posR = 0;
                double posL = 512;

                for(Monster monster: monster.getMonsterList()) {

                    if (posR < monster.getPositionX())
                        posR = monster.getPositionX();

                    if (posL > monster.getPositionX())
                        posL = monster.getPositionX();
                }

                for(Monster monster: monster.getMonsterList()) {

                    if(posR < (512-40) && monster.getVelocityX() >= 0){
                        monster.setVelocity(25,0);
                    }else if(posL > 0){
                        monster.setVelocity(-25,0);
                    }else{
                        monster.setVelocity(0,0);
                    }
                }
                */
                /*
                for(Sprite monster: monsterList) {

                    if (posR < monster.getPositionX())
                        posR = monster.getPositionX();

                    if (posL > monster.getPositionX())
                        posL = monster.getPositionX();
                }

                for(Sprite monster: monsterList) {

                    if(posR < (512-40) && monster.getVelocityX() >= 0){
                        monster.setVelocity(25,0);
                    }else if(posL > 0){
                        monster.setVelocity(-25,0);
                    }else{
                        monster.setVelocity(0,0);
                    }
                }
                */

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


              /*  if (spaceEntrepreneurs.monsterCheck()) {
                    stop();
                    level = level + 1;
                    start();
                }

                */

                /*Iterator<Monster> monsterIter = monster.getMonsterList().iterator();
                while ( monsterIter.hasNext() )
                {
                    Sprite monster = monsterIter.next();
                    if (missile.isOnScreen() && missile.intersects(monster) )
                    {
                        monsterIter.remove();
                        missile.Erasing();
                        score.value++;
                    }

                }

                //missile out of bound or intersect with wall
                if (missile.getPositionY() < 0)
                    missile.Erasing();

                //missile intersects with cover
                if (missile.intersects(cover1) || missile.intersects(cover2)|| missile.intersects(cover3))
                    missile.Erasing();
                */

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
                /*
                spaceEntrepreneurs.spaceship.render( gc );
                spaceEntrepreneurs.cover1.render( gc );
                spaceEntrepreneurs.cover2.render( gc );
                spaceEntrepreneurs.cover3.render( gc );
                */

                if (spaceEntrepreneurs.missile.isOnScreen())
                    gc.drawImage(missile_img, spaceEntrepreneurs.missile.getPositionX(), spaceEntrepreneurs.missile.getPositionY());

                if (spaceEntrepreneurs.monsterMissile.isOnScreen())
                    gc.drawImage(missile_img, spaceEntrepreneurs.monsterMissile.getPositionX(), spaceEntrepreneurs.monsterMissile.getPositionY());

                for (Monster monster : spaceEntrepreneurs.getMonsterList()) {
                    gc.drawImage(monster1_img, monster.getPositionX(), monster.getPositionY());
                    //monster.render( gc );
                }

                if (spaceEntrepreneurs.gameOverCheck()) {
                    gc.drawImage(gameOver_img, 0, 0);
                    spaceEntrepreneurs.checkHighscore();
                    stop();
                }

                String pointsText = "Cash: $" + (100 * spaceEntrepreneurs.getScore());
                gc.fillText(pointsText, 360, 36);
                gc.strokeText(pointsText, 360, 36);

                String lifeText = "Life <3: " + ( spaceEntrepreneurs.spaceship.getLife());
                gc.fillText(lifeText, 20, 36);
                gc.strokeText(lifeText, 20, 36);

            }
        }.start();

    }
/*
    private void onKey(KeyEvent keyEvent) {
    }
    */

    /*public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }*/

}
