package pageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class Transfers extends GenericSubPage{

    @FXML private Label header;

    @FXML private void backHome(ActionEvent actionEvent) throws IOException {
        goToHome(actionEvent);
    }

    public void setPage(){
        header.setText("Transfers - " + home.userName);
    }
}
