package edu.chs.entrep;

import edu.chs.entrep.view.MenuView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {


        primaryStage.getIcons().add(new Image("img/monster.png"));
        primaryStage.setTitle("SpaceEntrepreneurs");
        MenuView menuView = new MenuView(primaryStage);

        //this makes all stages close and the app exit when the main stage is closed
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        menuView.createComponents();
        menuView.showMenuStage();
    }
}



