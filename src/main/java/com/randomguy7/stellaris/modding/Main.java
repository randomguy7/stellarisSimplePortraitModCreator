package com.randomguy7.stellaris.modding;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        primaryStage.setTitle("Stellaris Species Portrait Mod Generator");
        primaryStage.setScene(new Scene(root, 600, 768));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
