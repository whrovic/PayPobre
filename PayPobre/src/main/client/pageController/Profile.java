package pageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pageController.GenericSubPage;

import java.io.IOException;

public class Profile extends GenericSubPage {

    public Label header;

    @FXML private void backHome(ActionEvent actionEvent) throws IOException {
        goToHome(actionEvent);
    }

    public void setPage(){
        header.setText("Profile - " + home.userName);
    }
}
