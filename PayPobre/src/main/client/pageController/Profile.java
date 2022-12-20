package pageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import static util.Const.*;

public class Profile extends GenericSubPage {

    @FXML private Label logMessagePass;
    @FXML private PasswordField newPass;
    @FXML private PasswordField oldPass;
    @FXML private PasswordField confirmPass;
    @FXML private Label logMessageCard;
    @FXML private PasswordField passCardField;
    @FXML private TextField cardField;
    @FXML private Label name;
    @FXML private Label email;
    @FXML private Label memberDate;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passProfileField;
    @FXML private Label logMessageProfile;
    @FXML private Label header;

    @FXML private void initialize(){
        upProfilePopup.setVisible(false);
        changeCardPopup.setVisible(false);
        changePassPopup.setVisible(false);
        aboutUsPopup.setVisible(false);
        helpPopup.setVisible(false);

        logMessageCard.setText("");
        logMessagePass.setText("");
        logMessageProfile.setText("");
    }

    @FXML private void backHome(ActionEvent actionEvent) throws IOException {
        goToHome(actionEvent);
    }

    public void setPage() {
        header.setText(home.user.name);
        name.setText(home.user.name);
        email.setText(home.user.email);
        memberDate.setText("Member since: " + home.user.created_on);
    }

    @FXML private Button upProfile;
    @FXML private Button changeCard;
    @FXML private Button changePass;
    @FXML private HBox upProfilePopup;
    @FXML private HBox changeCardPopup;
    @FXML private HBox changePassPopup;
    @FXML private HBox aboutUsPopup;
    @FXML private HBox helpPopup;


    @FXML private void upProfileOnMouseEntered(MouseEvent actionEvent) {
        upProfile.setStyle("-fx-background-color: whitesmoke");
    }

    @FXML private void upProfileOnMouseExited(MouseEvent actionEvent) {
        upProfile.setStyle("-fx-background-color: transparent");
    }

    @FXML private void changeCardOnMouseEntered(MouseEvent actionEvent) {
        changeCard.setStyle("-fx-background-color: whitesmoke");
    }

    @FXML private void changeCardOnMouseExited(MouseEvent actionEvent) {
        changeCard.setStyle("-fx-background-color: transparent");
    }
    @FXML private void changePassOnMouseEntered(MouseEvent actionEvent) {
        changePass.setStyle("-fx-background-color: whitesmoke");
    }

    @FXML private void changePassOnMouseExited(MouseEvent actionEvent) {
        changePass.setStyle("-fx-background-color: transparent");
    }

    @FXML private void upProfileOnAction(ActionEvent actionEvent) {
        upProfilePopup.setVisible(true);
    }

    @FXML private void applyUpProfile(ActionEvent actionEvent) {
    }

    @FXML private void closeUpProfile(ActionEvent actionEvent) {
        upProfilePopup.setVisible(false);
    }

    @FXML private void changeCardOnAction(ActionEvent actionEvent) {
        changeCardPopup.setVisible(true);
    }

    @FXML private void applyChangeCard(ActionEvent actionEvent) {
    }

    @FXML private void closeChangeCard(ActionEvent actionEvent) {
        changeCardPopup.setVisible(false);
    }

    @FXML private void changePassOnAction(ActionEvent actionEvent) {
        changePassPopup.setVisible(true);
        logMessagePass.setText("");
        newPass.clear();
        oldPass.clear();
        confirmPass.clear();
    }

    @FXML private void applyChangePass(ActionEvent actionEvent) {

    }

    @FXML private void closeChangePass(ActionEvent actionEvent) {
        changePassPopup.setVisible(false);
        logMessagePass.setText("");
        newPass.clear();
        oldPass.clear();
        confirmPass.clear();
    }

    @FXML private void aboutUsOnAction(ActionEvent actionEvent) {
        aboutUsPopup.setVisible(true);
    }

    @FXML private void closeAboutUs(ActionEvent actionEvent) {
        aboutUsPopup.setVisible(false);
    }

    @FXML private void helpOnAction(ActionEvent actionEvent) {
        helpPopup.setVisible(true);
    }

    @FXML private void closeHelp(ActionEvent actionEvent) {
        helpPopup.setVisible(false);
    }
}
