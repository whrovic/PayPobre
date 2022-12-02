package main;

import db.user_db;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import util.Const;

import java.io.IOException;
import java.util.Objects;

public class app extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        try{
            //Start Login
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/login.fxml")));
            Scene scene = new Scene(root, Const.WIDTH, Const.HEIGHT);
            Image icon = new Image(Objects.requireNonNull(getClass().getResource("/images/icon/icon.png")).toString());
            stage.getIcons().add(icon);
            stage.setTitle("PayPobre - Welcome");
            stage.setScene(scene);
            stage.show();

            //database
            user_db db = new user_db();
            db.connect();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}