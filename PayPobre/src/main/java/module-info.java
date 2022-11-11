module com.paypobre {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.paypobre to javafx.fxml;
    exports com.paypobre;
}