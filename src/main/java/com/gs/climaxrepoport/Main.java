package com.gs.climaxrepoport;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/gs/climaxrepoport/repport-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 630, 450);
        stage.setResizable(false);
        stage.setTitle("Rapport Statistique des Clients");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}