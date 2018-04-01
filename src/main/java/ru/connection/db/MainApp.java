package ru.connection.db;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(MainApp.class,args);
    }

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/MainWindow.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Главное окно");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root,850,600));
        primaryStage.setMinWidth(850);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }
}
