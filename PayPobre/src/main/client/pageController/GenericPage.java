package pageController;

import account.User;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class GenericPage {
    protected String userName;
    protected String userEmail;
    protected String accountType;

    protected Stage stage;
    protected Scene scene;
    protected Parent root;

    public void setUserInfo(User user){
        userName = user.username;
        userEmail = user.email;
        accountType = user.type;
    }
    public void setUserInfo(Home home){
        userName = home.userName;
        userEmail = home.userEmail;
        accountType = home.accountType;
    }
}
