package edu.chs.entrep.viewcontrol;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by josefinesvegborn on 2017-04-03.
 * This class represents the view and control for the menu. It launches name view or highscore view if those buttons are pressed.
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

        String textStyle = "-fx-font-family: Futura; -fx-font-size: 35px; -fx-text-fill: #FFFFFF; -fx-padding: 0 0 10 0;";
        String buttonStyle = "-fx-font-family: Futura; -fx-font-size: 18px; -fx-text-fill: #FFFFFF; -fx-background-color: rgb(0,0,0,0.0); -fx-border-weight: 0.5px; -fx-border-color: white; -fx-border-radius: 2px; -fx-pref-width: 150px;";
        DropShadow highlight = new DropShadow(2, 0, 0, Color.WHITE);

        Label label1 = new Label("Space Entrepreneurs");
        label1.setStyle(textStyle);
        label1.setTextAlignment(TextAlignment.CENTER);
        Button highscoreButton = new Button("HIGHSCORES");
        Button startButton = new Button("START");
        VBox layout1 = new VBox(20);
        layout1.setAlignment(Pos.CENTER);
        startButton.setStyle(buttonStyle);
        highscoreButton.setStyle(buttonStyle);
        layout1.setStyle("-fx-background-image: url(img/Menu-bg.png); -fx-padding: 0 0 80 0;");
        layout1.getChildren().addAll(label1, startButton, highscoreButton);
        Scene startScene = new Scene(layout1, 512, 512);
        theStage.setScene(startScene);

        //Button Handlers
        startButton.setOnMousePressed(event -> {
            startButton.setEffect(null);
        });

        startButton.setOnMouseReleased(event -> {
            startButton.setEffect(highlight);
            startGame();
        });

        startButton.setOnMouseEntered(event -> {
            startButton.setEffect(highlight);
        });

        startButton.setOnMouseExited(event -> {
            startButton.setEffect(null);
        });

        highscoreButton.setOnMousePressed(event -> {
            highscoreButton.setEffect(null);
        });

        highscoreButton.setOnMouseReleased(event -> {
            highscoreButton.setEffect(highlight);
            showHighscores();
        });

        highscoreButton.setOnMouseEntered(event -> {
            highscoreButton.setEffect(highlight);
        });

        highscoreButton.setOnMouseExited(event -> {
            highscoreButton.setEffect(null);
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

