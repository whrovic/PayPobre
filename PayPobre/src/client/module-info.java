module com.paypobre {
    requires javafx.controls;
    requires javafx.fxml;


    exports controller;
    opens controller to javafx.fxml;
    exports main;
    opens main to javafx.fxml;
}