package pageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pageController.GenericSubPage;

import java.io.IOException;

public class Wallet extends GenericSubPage {

    @FXML private Label header;

    public void setPage(){
        header.setText("Wallet - " + home.userName);
    }

    @FXML private void backHome(ActionEvent actionEvent) throws IOException {
        goToHome(actionEvent);
    }

    @FXML private void initialize(){

    }
}
