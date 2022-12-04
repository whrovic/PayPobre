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
import controllerTabs.*;

import java.io.IOException;
import java.util.Objects;

import static util.Const.*;

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
    protected void tryLogin(ActionEvent actionEvent) throws IOException {
        String email = emailField.getText();
        String pass = passField.getText();

        if(email.isEmpty() || pass.isEmpty()){
            logMessage.setText("You must fulfill everything");
            return;
        }

        User user = new User();
        user = user.Login(emailField.getText(), passField.getText());
        if(user == null){
            logMessage.setText("This user does not exit");
            return;
        }

        assert user.logMessage != null;
        if(user.logMessage.equals(LOGIN_SUCCESSFUL)){
            logMessage.setText(user.logMessage);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/tabs/tabview.fxml"));
            root = loader.load();
            //MainController mainController = loader.getController();
            //mainController.setUser(user);

            //stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else logMessage.setText(user.logMessage);
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