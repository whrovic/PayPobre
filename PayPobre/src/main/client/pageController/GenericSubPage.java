package pageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static util.Const.HEIGHT;
import static util.Const.WIDTH;

public abstract class GenericSubPage extends GenericPage{
    protected Home home;

    public void injectMain(Home home){
        this.home = home;
    }

    protected void goToHome(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/pages/home.fxml"));
        root = loader.load();
        Home homeControl = loader.getController();
        homeControl.setPage(home);

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.show();
    }
}
