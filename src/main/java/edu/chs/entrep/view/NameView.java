package edu.chs.entrep.view;


import edu.chs.entrep.model.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sun.rmi.rmic.newrmic.Main;

/**
 * Created by josefinesvegborn on 2017-05-17.
 */



public class NameView {
    Stage theStage;
    Player player;

    public NameView(Stage theStage) {
        this.theStage = theStage;
        createComponents();
    }

    public void createComponents() {
        Label nameLabel = new Label("Before you enter the matrix,\nYou have to tell uss your name.");
        TextField nameInput = new TextField();
        nameInput.setMaxWidth(200);
        nameInput.setStyle("-fx-text-inner-color: white; -fx-background-color: black;");
        Button nameButton = new Button("OK");
        VBox layout3 = new VBox(20);
        layout3.setAlignment(Pos.CENTER);
        layout3.getChildren().addAll(nameLabel, nameInput, nameButton);
        Scene nameScene = new Scene(layout3, 512, 512);
        theStage.setScene(nameScene);

        nameButton.setOnAction(event -> {
            player = new Player(nameInput.getText());
            GameView gameView = new GameView(theStage, player);
            gameView.showGameStage();
            gameView.startGame();
        });

    }

    public void showNameStage() {
        this.theStage.show();
    }
}