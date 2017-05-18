package edu.chs.entrep.view;

import edu.chs.entrep.model.Highscore;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


/**
 * Created by josefinesvegborn on 2017-04-03.
 */
public class MenuView {

    private Stage theStage;
    private HighscoreView highscoreView;
    private NameView nameView;

    public MenuView(Stage theStage) {
        this.theStage = theStage;
        createComponents();
    }

    public void createComponents() {


        String buttonStyle = "-fx-font-family: Futura; -fx-font-size: 18px; -fx-text-fill: #FFFFFF; -fx-background-color: rgb(0,0,0,0.0); -fx-border-weight: 0.5px; -fx-border-color: white; -fx-border-radius: 2px;";
        String buttonPressedStyle = "-fx-font-family: Futura; -fx-font-size: 18px; -fx-text-fill: #cccccc; -fx-background-color: rgb(0,0,0,0); -fx-border-weight: 0.5px; -fx-border-color: #cccccc; -fx-border-radius: 2px";

        Label label1 = new Label("Space Entrepreneurs");
        Button highscoreButton = new Button("HIGHSCORES");
        Button startButton = new Button("Start");
        VBox layout1 = new VBox(20);
        layout1.setAlignment(Pos.CENTER);
        startButton.setStyle(buttonStyle);
        highscoreButton.setStyle(buttonStyle);
        layout1.setStyle("-fx-background-image: url(img/background.png);");
        layout1.getChildren().addAll(label1, startButton, highscoreButton);
        Scene startScene = new Scene(layout1, 512, 512);
        theStage.setScene(startScene);

        //Button Handlers
        startButton.setOnAction(e -> {
            startGame();
        });

        highscoreButton.setOnAction(e -> {
            showHighscores();
        });
    }

    public void showMenuStage() {
        this.theStage.show();
    }

    public boolean startGame() {
        nameView = new NameView(theStage);
        nameView.showNameStage();
        return true;
    }

    public boolean showHighscores() {
        highscoreView = new HighscoreView(theStage);
        highscoreView.showHighscoreStage();
        return true;
    }

}

