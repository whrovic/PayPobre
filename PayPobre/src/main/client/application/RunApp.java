package application;

import db.User_db;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import util.Const;

import java.io.IOException;
import java.util.Objects;

public class RunApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/login.fxml")));
        Scene scene = new Scene(root, Const.WIDTH, Const.HEIGHT);

        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/images/icon/icon.png")).toString()));
        stage.setTitle("PayPobre");
        stage.setScene(scene);
        stage.show();

        try{
            //database
            User_db db = new User_db();
            db.connect();
        }
        catch (Exception e){
            System.out.println("We couldn't connect to our database :(");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}