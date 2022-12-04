module com.paypobre {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens com.paypobre to javafx.fxml;
    opens controller to javafx.fxml;
    opens controllerTabs to javafx.fxml;

    exports account;
    exports db;
    exports transactions;
    exports wallet;
    exports util;
    exports controllerTabs;

    exports com.paypobre;
}