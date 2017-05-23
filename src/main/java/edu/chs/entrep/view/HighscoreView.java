package edu.chs.entrep.view;

import edu.chs.entrep.model.Highscore;
import edu.chs.entrep.model.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
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
    String welcomeMessage;

    public HighscoreView(Stage theStage) {
        this.theStage = theStage;
        this.highscoreFile = new File("src/main/resources/txt/highscore");
        this.highscore = new Highscore(highscoreFile);
        this.welcomeMessage = "HIGHSCORE";
        createComponents();
    }

    //Alternative highscore. Could be developed to show the view differently if the current player made it to the list.
    public HighscoreView(Stage theStage, Player player) {
        this.theStage = theStage;
        this.player = player;
        this.highscoreFile = new File("src/main/resources/txt/highscore");
        this.highscore = new Highscore(highscoreFile);
        this.welcomeMessage = "Congratulations " + player.getName() + "!\n\nHIGHSCORE";
        createComponents();

    }

    public void createComponents() {

        String headerTextStyle = "-fx-font-family: Futura; -fx-font-size: 25px; -fx-text-fill: #FFFFFF;";
        String textStyle = "-fx-font-family: Futura; -fx-font-size: 20px; -fx-text-fill: #FFFFFF;";
        String buttonStyle = "-fx-font-family: Futura; -fx-font-size: 18px; -fx-text-fill: #FFFFFF; -fx-background-color: rgb(0,0,0,0.0); -fx-border-weight: 0.5px; -fx-border-color: white; -fx-border-radius: 2px;";
        DropShadow highlight = new DropShadow(2, 0, 0, Color.WHITE);


        //Highscore Scene
        Label header = new Label(welcomeMessage);
        header.setStyle(headerTextStyle);
        header.setTextAlignment(TextAlignment.CENTER);
        Label highscoreLabel = new Label();
        highscoreLabel.setStyle(textStyle);
        highscoreLabel.setTextAlignment(TextAlignment.CENTER);
        highscoreLabel.setText(highscore.readHighscore());
        Button menuButton = new Button("MENU");
        menuButton.setStyle(buttonStyle);
        VBox layout2 = new VBox(20);
        layout2.setAlignment(Pos.CENTER);
        layout2.setStyle("-fx-background-image: url(img/background.png);");
        layout2.getChildren().addAll(header, highscoreLabel, menuButton);
        Scene highscoreScene = new Scene(layout2, 512, 512);
        theStage.setScene(highscoreScene);

        menuButton.setOnMousePressed(event -> {
            menuButton.setEffect(null);
            menuButton.setEffect(highlight);
            menuView = new MenuView(theStage);
        });

        menuButton.setOnMouseReleased(event -> {
            menuView.showMenuStage();
        });

        menuButton.setOnMouseEntered(event -> {
            menuButton.setEffect(highlight);
        });

        menuButton.setOnMouseExited(event -> {
            menuButton.setEffect(null);
        });
    }

    public void showHighscoreStage() {
        theStage.show();
    }
}

