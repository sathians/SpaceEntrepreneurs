package edu.chs.entrep.viewcontrol;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by josefinesvegborn on 2017-05-17.
 * This class represents the view and control for the scene in which the player inputs its name.
 * It launches the GameView class.
 */

public class NameView {
    Stage theStage;
    String playerName;

    public NameView(Stage theStage) {
        this.theStage = theStage;
        createComponents();
    }

    public void createComponents() {

        String textStyle = "-fx-font-family: Futura; -fx-font-size: 18px; -fx-text-fill: #FFFFFF;";
        String buttonStyle = "-fx-font-family: Futura; -fx-font-size: 16px; -fx-text-fill: #FFFFFF; -fx-background-color: rgb(0,0,0,0.0); -fx-pref-width: 60px;";
        String inputStyle = "-fx-font-family: Futura; -fx-font-size: 18px; -fx-text-fill: #FFFFFF; -fx-background-color: rgb(0,0,0,0.0); -fx-pref-width: 190px;";
        DropShadow highlight = new DropShadow(2, 0, 0, Color.WHITE);

        Label nameLabel = new Label("Before you enter the matrix,\nYou have to tell us your name.");
        nameLabel.setStyle(textStyle);
        nameLabel.setTextAlignment(TextAlignment.CENTER);
        TextField nameInput = new TextField();
        nameInput.setStyle(inputStyle);
        Button nameButton = new Button("OK");
        nameButton.setStyle(buttonStyle);
        nameButton.setStyle(buttonStyle);
        VBox layout3 = new VBox(25);
        layout3.setAlignment(Pos.CENTER);
        HBox nameBox = new HBox();
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setStyle("-fx-border-weight: 0.5px; -fx-border-color: white; -fx-border-radius: 2px; -fx-max-width: 250px;");
        nameBox.getChildren().addAll(nameInput, nameButton);
        layout3.setStyle("-fx-background-image: url(img/Menu-name.png); -fx-padding: 0 0 70 0;");
        layout3.getChildren().addAll(nameLabel, nameBox);
        Scene nameScene = new Scene(layout3, 512, 512);
        theStage.setScene(nameScene);

        nameButton.setOnMousePressed(event -> {
            nameButton.setEffect(null);
        });

        nameButton.setOnMouseReleased(event -> {
            nameButton.setEffect(highlight);
            setName(nameInput, nameLabel);
        });

        nameButton.setOnMouseEntered(event -> {
            nameButton.setEffect(highlight);
        });

        nameButton.setOnMouseExited(event -> {
            nameButton.setEffect(null);
        });

        nameScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if (code == "ENTER")
                        nameButton.setEffect(highlight);
                });

        nameScene.setOnKeyReleased(
                e -> {
                    nameButton.setEffect(null);
                    String code = e.getCode().toString();
                    if (code == "ENTER")
                        setName(nameInput, nameLabel);
                });

    }

    public void setName(TextField nameInput, Label nameLabel) {
        if(nameInput.getText() == null || nameInput.getText().trim().isEmpty()) {
            nameLabel.setText("As we said,\nYou have to tell us you name!");
        }
        else if(nameInput.getText().contains(" ") || nameInput.getText().contains("/")) {
            nameLabel.setText("Sorry, Your name can't\ncontain space or slash!");
        }
        else {
            playerName = nameInput.getText();
            GameView gameView = new GameView(theStage, playerName);
            gameView.showGameStage();
            gameView.startGame();
        }
    }

    public void showNameStage() {
        this.theStage.show();
    }
}
