package ru.eltex;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;


public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(new File("src/main/resources/main.fxml").toURL());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Users List");
        stage.show();
    }
}
