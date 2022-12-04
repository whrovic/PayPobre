package controller;

import util.Const.*;
import account.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class HomeTab extends GenericTab {

    @FXML
    Label welcomeTex;
    public void init(User user){
        setUser(user);
        if(accountType == 0)
            welcomeTex.setText("Welcome: " + u.username);

        if(accountType == 1)
            welcomeTex.setText("Welcome: " + u.username);
    }
    @FXML
    public void walletTab(MouseEvent event) {

    }
}
