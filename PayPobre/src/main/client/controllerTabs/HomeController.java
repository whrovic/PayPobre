package controllerTabs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class HomeController{

    @FXML private Label welcomeText;
    @FXML private AnchorPane homeTab;
    private MainController mainController;

    public void injectMainController(MainController mainController){
        this.mainController = mainController;
    }

    @FXML private void initialize(){
        welcomeText.setText("Welcome " );
    }
}
