package pageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;

import static util.Const.HEIGHT;
import static util.Const.WIDTH;

public class Profile extends GenericSubPage {

    @FXML private Label header;

    @FXML
    private void backHome(ActionEvent actionEvent) throws IOException {
        goToHome(actionEvent);
    }

    public void setPage() {
        header.setText("Profile - " + home.user.name);
    }

    @FXML private Button upProfile;
    @FXML private Button changeCard;
    @FXML private Button changePass;
    @FXML private HBox upProfilePopup;
    @FXML private HBox changeCardPopup;
    @FXML private HBox changePassPopup;
    @FXML private HBox aboutUsPopup;
    @FXML private HBox helpPopup;

    @FXML private void initialize(){
        upProfilePopup.setVisible(false);
        changeCardPopup.setVisible(false);
        changePassPopup.setVisible(false);
        aboutUsPopup.setVisible(false);
        helpPopup.setVisible(false);
    }

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
    }

    @FXML private void changePassOnAction(ActionEvent actionEvent) {
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
