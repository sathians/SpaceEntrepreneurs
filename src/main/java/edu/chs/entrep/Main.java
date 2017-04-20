package edu.chs.entrep;

import edu.chs.entrep.model.Missile;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Main extends Application {

    //Instead of a view and control class, this is handled in start()

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage)
    {
        theStage.setTitle( "SpaceEntrepreneurs- the endless game!" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 512, 512 );
        root.getChildren().add( canvas );

        final ArrayList<String> input = new ArrayList<String>();


        //KeyHandler - set and release
        theScene.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        if ( !input.contains(code) )
                            input.add( code );
                    }
                }
        );

        theScene.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove( code );
                    }
                });

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.DARKRED );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);

        final Image spaceship_img = new Image( "img/spaceship_a1.png",50, 50, true, true );
        final Sprite spaceship = new Sprite();
        spaceship.setImage(spaceship_img); //"img/spaceship_a1.png"
        spaceship.setPosition(200, 450);

        //final Image missile_img = new Image("img/Tesla_missile_0.png", 20,20,false, true);
        //final Sprite missile = new Sprite();
        //missile.setImage(missile_img);

        final Missile missile = new Missile();
        final ArrayList<Sprite> monsterList = new ArrayList<Sprite>();

        for (int i = 0; i < 15; i++)
        {
            Sprite monster = new Sprite();
            monster.setImage("img/ufo_0.png");
            double py;                              //Tried out a path
            double px;

            if (i < 5){
                py = 50;
                px = 50 + i*412/5;
            }else if(i >= 5 || i < 10){
                py = 100;
                px = 50 + (i-5)*412/5;
            }else if(i >= 10 || i < 15){
                py = 150;
                px = 50 + (i-10)*412/5;
            }else{
                py = 200;
                px = 50 + (i-15)*412/5;
            }

            monster.setPosition(px,py);
            monsterList.add( monster );
        }

        final Image background_img = new Image( "img/background.png", 512,512,false,true);
        final Image wall_1 = new Image( "img/Firewall_a0.png",70, 50, true, true );
        final Image wall_2 = new Image( "img/Firewall_a0.png",70, 50, true, true );
        final Image wall_3 = new Image( "img/Firewall_a0.png",70, 50, true, true );


        final LongValue lastNanoTime = new LongValue( System.nanoTime() );

        final IntValue score = new IntValue(0);

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;

                // game logic

                spaceship.setVelocity(0,0);
                if (input.contains("LEFT"))
                    spaceship.addVelocity(-100,0);
                if (input.contains("RIGHT"))
                    spaceship.addVelocity(100,0);
                if (input.contains("UP"))
                    spaceship.addVelocity(0,-100);
                if (input.contains("DOWN"))
                    spaceship.addVelocity(0,100);

                if (missile.getPositionY() < - 20)              //keep the missile from gaining higher speed after every new shot
                    missile.setVelocity(0, 0);

                if (input.contains("SPACE"))
                    missile.setPosition(spaceship.getPositionX(), spaceship.getPositionY());    //the missile starts from the spaceships position
                    missile.addVelocity(0, -4);

                //Updates spaceship position
                spaceship.update(elapsedTime);
                missile.update(elapsedTime);

                // collision detection

                Iterator<Sprite> monsterIter = monsterList.iterator();
                while ( monsterIter.hasNext() )
                {
                    Sprite monster = monsterIter.next();
                    if ( missile.intersects(monster) )
                    {
                        monsterIter.remove();
                        score.value++;
                    }
                }

                // render

                gc.clearRect(0, 0, 512,512);
                gc.drawImage( background_img, 0, 0 );
                spaceship.render( gc );
                missile.render( gc );

                for (Sprite monster : monsterList )
                    monster.render( gc );

                String pointsText = "Cash: $" + (100 * score.value);
                gc.fillText( pointsText, 360, 36 );
                gc.strokeText( pointsText, 360, 36 );
                gc.drawImage( wall_1, 50, 400 );
                gc.drawImage( wall_2, 200, 400 );
                gc.drawImage( wall_3, 350, 400 );
            }
        }.start();



        theStage.show();
    }
}
