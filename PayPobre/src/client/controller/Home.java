package controller;

import account.Commercial;
import account.Personal;
import account.User;
import util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.Objects;

import static util.Const.*;

public class Home {
    private Stage stage;
    private TabPane tabPane;
    private Personal userPersonal;
    private Commercial userCommercial;
    public Home(User user){
        if(user.type.equals(PERSONAL)){
            this.userPersonal = new Personal(user);
        }

        if(user.type.equals(COMMERCIAL)){
            this.userCommercial = new Commercial(user);
        }
    }

    public void start(Stage homeStage){
        try{
            this.stage = homeStage;
            stage.setTitle("PayPobre");
            BorderPane root = new BorderPane();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/home.fxml")));

            tabPane = new TabPane();
            root.setCenter(tabPane);
            Tab tab1 = new Tab("Home");
            Tab tab2 = new Tab("Wallet");
            Tab tab3 = new Tab("Transactions");
            Tab tab4 = new Tab("Profile");

            tabPane.getTabs().add(tab1);
            Scene scene = new Scene(root, WIDTH, HEIGHT);
            //scene.getSyslesheets().add(getClass().getResource())
            homeStage.setScene(scene);
            homeStage.show();
        }
        catch (Exception e){

        }
    }
}