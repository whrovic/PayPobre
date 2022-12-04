package controllerTabs;

import account.User;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static util.Const.COMMERCIAL;
import static util.Const.PERSONAL;

public class MainController {
    public User u;
    public int accountType; //0 -> Personal 1 -> Commercial
    public Stage stage;
    public Scene scene;
    public Parent root;

    @FXML public HomeController homeController;
    @FXML private WalletController walletController;
    @FXML private TransfersController transfersController;

    public void setUser(User user){
        if(user == null) return;

        if(user.type.equals(PERSONAL)){
            u = new User(user);
            accountType = 0;
        }
        if(user.type.equals(COMMERCIAL)){
            u = new User(user);
            accountType = 1;
        }
    }

    @FXML private void initialize (){
        homeController.injectMainController(this);
        //homeController.setWelcomeText(this.u);
    }
}