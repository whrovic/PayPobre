package controller;

import account.Commercial;
import account.Personal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.postgresql.jdbc.SslMode;
import util.Const;
import util.Macros;

import javax.crypto.Mac;
import java.io.IOException;
import java.util.Objects;

public class Signup {
    ObservableList<String> accTypeList = FXCollections.observableArrayList("Personal", "Commercial");
    @FXML
    private TextField emailField;
    @FXML
    private TextField cardField;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passField;
    @FXML
    private ComboBox<String> accType;
    @FXML
    private Label logMessage;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize (){
        accType.setItems(FXCollections.observableArrayList("Personal", "Commercial"));
    }
    public void trySignup(ActionEvent actionEvent) {
        String name = nameField.getText();
        String email = emailField.getText();
        String pass = passField.getText();
        String type = accType.getValue();
        String cardStr = cardField.getText();
        int card = 0;

        if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || cardStr.isEmpty() || type == null) {
            logMessage.setText("You must fulfill everything");
            return;
        }

        if(!Macros.emailValidator(email)){
            logMessage.setText("Please, insert a valid email:\n \texample@test.com");
            return;
        }

        if( (card = Macros.creditCardValidator(cardStr)) == 0 ){
            logMessage.setText("Please, insert a valid card number.");
            return;
        }

        if(type.compareTo(Const.PERSONAL) == 0){
            Personal p = new Personal(null);
            logMessage.setText(p.Signup(name, email, pass, card, type));
        }

        if(type.compareTo(Const.COMMERCIAL) == 0){
            Commercial c = new Commercial(null);
            logMessage.setText(c.Signup(name, email, pass, card, type));

        }
    }

    public void backToLogin(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/login.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root, Const.WIDTH, Const.HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}