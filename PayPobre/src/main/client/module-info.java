module com.paypobre {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens application to javafx.fxml;
    opens authenticationController to javafx.fxml;
    opens pageController to javafx.fxml;

    exports account;
    exports db;
    exports transactions;
    exports wallet;
    exports util;

    exports application;
}