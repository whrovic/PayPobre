package controller;

import account.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static util.Const.*;

public abstract class GenericTab {
    public User u;
    public int accountType;
    public Stage stage;
    public Scene scene;
    public Parent root;

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
}
