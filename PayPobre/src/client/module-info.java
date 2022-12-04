module PayPobre {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires org.junit.jupiter.api;


    exports controller;
    opens controller to javafx.fxml;
    exports main;
    opens main to javafx.fxml;
    exports account;
    exports db;
    exports transactions;
    exports wallet;
    exports util;
    exports controllerTabs;
    opens controllerTabs to javafx.fxml;
}