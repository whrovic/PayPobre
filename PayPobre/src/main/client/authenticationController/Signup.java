package authenticationController;

import account.Commercial;
import account.Personal;
import account.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pageController.Home;
import util.Const;
import util.Macros;

import java.io.IOException;
import java.util.Objects;

import static util.Const.*;

public class Signup {
    ObservableList<String> accTypeList = FXCollections.observableArrayList("Personal", "Commercial");
    @FXML private TextField emailField;
    @FXML private TextField cardField;
    @FXML private TextField nameField;
    @FXML private PasswordField passField;
    @FXML private ComboBox<String> accType;
    @FXML private Label logMessage;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private void initialize (){
        accType.setItems(FXCollections.observableArrayList("Personal", "Commercial"));
    }
    @FXML private void trySignup(ActionEvent actionEvent) throws IOException {
        String name = nameField.getText();
        String email = emailField.getText();
        String pass = passField.getText();
        String type = accType.getValue();
        String card = cardField.getText();


        User user = new User();
        int log = user.Signup(name, email, pass, card, type);
        user = user.setUser(name, email, type);

        switch (log){
            case e_INVALID_CREDIT_CARD -> logMessage.setText("Invalid Credit Card");
            case e_INVALID_EMAIL -> logMessage.setText("Please, insert a valid email:\n \texample@test.com");
            case e_USER_ALREADY_EXISTS -> logMessage.setText("User Already Exists");
            case e_EMPTY_FIELDS -> logMessage.setText("You must fulfill everything");
            case e_SIGNUP_SUCCESSFUL -> login(actionEvent, user);
        }
    }

    @FXML private void backToLogin(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/login.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root, Const.WIDTH, Const.HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    private void login(ActionEvent actionEvent, User user) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/pages/home.fxml"));
        root = loader.load();
        Home homeControl = loader.getController();
        homeControl.setPage(user);

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.show();
    }
}