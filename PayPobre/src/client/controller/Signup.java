package controller;

import account.Personal;
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
import util.Const;

import java.io.IOException;
import java.util.Objects;

public class Signup {
    ObservableList<String> accTypeList = FXCollections.observableArrayList("Personal", "Commercial");
    @FXML
    private TextField emailField;
    @FXML
    private TextField cardField;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passField;
    @FXML
    private ComboBox<String> accType;
    @FXML
    private Label logMessage;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize (){
        accType.setItems(FXCollections.observableArrayList("Personal", "Commercial"));
    }
    public void trySignup(ActionEvent actionEvent) {

        if(accType.getValue().compareTo("Personal") == 0){
            Personal p = new Personal();
            logMessage.setText(p.Signup(nameField.getText(), emailField.getText(), passField.getText(), 0));
        }
        else logMessage.setText("To be implemented");
    }

    public void backToLogin(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/login.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root, Const.WIDTH, Const.HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}
