package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Login {
    @FXML
    private Label logMessage;
    private Label email;
    private Label pass;

    @FXML
    protected void tryLogin() {

        logMessage.setText("Our services are down :(");
    }
}