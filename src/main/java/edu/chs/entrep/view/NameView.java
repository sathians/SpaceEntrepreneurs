package edu.chs.entrep.view;


import edu.chs.entrep.model.Player;
import edu.chs.entrep.service.image.ImageFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by josefinesvegborn on 2017-05-17.
 */

public class NameView {
    Stage theStage;
    Player player;
    Image background_img;

    public NameView(Stage theStage) {
        this.theStage = theStage;
        createComponents();
    }

    public void createComponents() {

        String textStyle = "-fx-font-family: Futura; -fx-font-size: 20px; -fx-text-fill: #FFFFFF;";
        String buttonStyle = "-fx-font-family: Futura; -fx-font-size: 18px; -fx-text-fill: #FFFFFF; -fx-background-color: rgb(0,0,0,0.0); -fx-border-weight: 0.5px; -fx-border-color: white; -fx-border-radius: 2px;";
        DropShadow highlight = new DropShadow(2, 0, 0, Color.WHITE);

        Label nameLabel = new Label("Before you enter the matrix,\nYou have to tell uss your name.");
        nameLabel.setStyle(textStyle);
        nameLabel.setTextAlignment(TextAlignment.CENTER);
        TextField nameInput = new TextField();
        nameInput.setMaxWidth(200);
        nameInput.setStyle(buttonStyle);
        Button nameButton = new Button("OK");
        nameButton.setStyle(buttonStyle);
        VBox layout3 = new VBox(20);
        layout3.setAlignment(Pos.CENTER);
        layout3.setStyle("-fx-background-image: url(img/background.png);");
        layout3.getChildren().addAll(nameLabel, nameInput, nameButton);
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
            player = new Player(nameInput.getText());
            GameView gameView = new GameView(theStage, player);
            gameView.showGameStage();
            gameView.startGame();
        }
    }

    public void showNameStage() {
        this.theStage.show();
    }
}
