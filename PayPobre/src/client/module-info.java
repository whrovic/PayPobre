module com.paypobre {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;


    exports controller;
    opens controller to javafx.fxml;
    exports main;
    opens main to javafx.fxml;
}