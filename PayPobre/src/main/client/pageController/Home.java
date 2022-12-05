package pageController;

import account.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static util.Const.HEIGHT;
import static util.Const.WIDTH;

public class Home extends GenericPage {

    @FXML Label accountTypeInfo;
    @FXML Label welcomeText;
    private Wallet wallet;
    private Transfers transfers;
    private Profile profile;

    public void setPage(User user){
        setUserInfo(user);
        welcomeText.setText("Welcome, " + userName);
        accountTypeInfo.setText("You are using a " + accountType + " account");
    }
    public void setPage(Home home){
        setUserInfo(home);
        welcomeText.setText("Welcome, " + home.userName);
        accountTypeInfo.setText("You are using a " + home.accountType + " account");
    }

    @FXML private void initialize(){
    }

    @FXML private void goToWallet(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/pages/wallet.fxml"));
        root = loader.load();
        wallet = loader.getController();
        wallet.injectMain(this);
        wallet.setPage();

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML private void goToTrasnfers(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/pages/transfers.fxml"));
        root = loader.load();
        transfers = loader.getController();
        transfers.injectMain(this);
        transfers.setPage();

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML private void goToProfile(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/pages/profile.fxml"));
        root = loader.load();
        profile = loader.getController();
        profile.injectMain(this);
        profile.setPage();

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML private void logout(ActionEvent actionEvent) {

    }
}
