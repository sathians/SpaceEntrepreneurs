package edu.chs.entrep;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    //Should be controlled via view class, this is just for prototyping

    public void start(Stage theStage) throws Exception {

        theStage.getIcons().add(new Image("img/purple_monster.png"));
        theStage.setTitle("SpaceEntrepreneurs");


        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(500, 600);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image background = new Image("img/background.png");
        Image monster = new Image("img/purple_monster.png");

        gc.drawImage(background, 0, 0);
        gc.drawImage(monster, 180, 230);

        theStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
