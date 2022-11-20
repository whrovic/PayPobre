package controller;

import account.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Objects;

import static util.Const.HEIGHT;
import static util.Const.WIDTH;

public class Login {
    @FXML
    public TextField emailField;
    @FXML
    private PasswordField passField;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label logMessage;

    @FXML
    protected void tryLogin() {
        //logMessage.setText("Our services are down :(");
        String email = emailField.getText();
        String pass = passField.getText();

        if(email.isEmpty() || pass.isEmpty()){
            logMessage.setText("You must fulfill everything");
            return;
        }

        User u = new User();
        logMessage.setText(u.Login(emailField.getText(), passField.getText()));
    }
    public void SignupPage(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/signup.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    public void goForgotPass(ActionEvent actionEvent) {
        logMessage.setText("Sorry, this feature is not implemented yet");
    }
}