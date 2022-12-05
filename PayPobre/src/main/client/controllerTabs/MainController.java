package controllerTabs;

import account.User;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static util.Const.COMMERCIAL;
import static util.Const.PERSONAL;

public class MainController {
    public String userName;
    public String userEmail;
    public int accountType; //0 -> Personal 1 -> Commercial
    public Stage stage;
    public Scene scene;
    public Parent root;

    @FXML private HomeController homeController;
    @FXML private WalletController walletController;
    @FXML private TransfersController transfersController;

    public void setUserInfo(User user){
        userName = user.username;
        userEmail = user.email;
        if(user.type.equals(PERSONAL)){
            accountType = 0;
        }

        if(user.type.equals(COMMERCIAL)){
            accountType = 1;
        }

    }

    @FXML private void initialize (){
        //System.out.println("Username: " + this.u.username);
        homeController = new HomeController();
        homeController.injectMainController(this);
        //System.out.println("Username = " + userName);
        //homeController.setWelcomeText(this.u);
    }
}