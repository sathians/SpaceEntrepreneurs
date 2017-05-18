package edu.chs.entrep.view;

import edu.chs.entrep.model.Highscore;
import edu.chs.entrep.model.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;


/**
 * Created by josefinesvegborn on 2017-05-17.
 */
public class HighscoreView {
    Stage theStage;
    Player player;
    MenuView menuView;
    File highscoreFile;
    Highscore highscore;

    public HighscoreView(Stage theStage) {
        this.theStage = theStage;
        this.highscoreFile = new File("src/main/resources/txt/highscore");
        this.highscore = new Highscore(highscoreFile);
        createComponents();
    }

    //Alternative highscore
    public HighscoreView(Stage theStage, Player player) {
        this.theStage = theStage;
        this.player = player;
        this.highscoreFile = new File("src/main/resources/txt/highscore");
        this.highscore = new Highscore(highscoreFile);
        createComponents();
    }

    public void createComponents() {
        //Highscore Scene
        Label label2 = new Label("Highscore");
        Label highscoreLabel = new Label();
        highscoreLabel.setText(highscore.readHighscore());
        Button backButton = new Button("Go back");
        VBox layout2 = new VBox(20);
        layout2.setAlignment(Pos.CENTER);
        layout2.getChildren().addAll(label2, highscoreLabel, backButton);
        Scene highscoreScene = new Scene(layout2, 512, 512);
        theStage.setScene(highscoreScene);


        backButton.setOnAction(e -> {
            menuView = new MenuView(theStage);
            menuView.showMenuStage();

        });

    }

    public void showHighscoreStage() {
        theStage.show();
    }
}

