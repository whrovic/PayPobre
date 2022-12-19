package pageController;

import account.*;
import db.User_db;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import transactions.Transaction;
import util.Macros;
import static util.Const.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Transfers extends GenericSubPage{
    // General
    private Transaction t;
    private User_db rx_db = new User_db();
    @FXML private Label header;
    @FXML private TableView table;

    // Issue PopUp
    ObservableList<String> transferTypeList = FXCollections.observableArrayList("Instantaneous", "Commercial Transaction");
    @FXML private HBox issueTransactionPopup;
    @FXML private TextField issueAmountField;
    @FXML private TextField issueRxField;
    @FXML private Label logMessage_issue;
    @FXML private ComboBox<String> transferType;
    @FXML private Label issueRxLabel;

    // Pending
    @FXML private HBox pendingTransfersPopup;

    // Cancel Request
    @FXML private HBox cancelRequestPopup;


    @FXML private void backHome(ActionEvent actionEvent) throws IOException {
        goToHome(actionEvent);
    }

    @FXML private void initialize(){
        TableColumn id = new TableColumn("ID");
        TableColumn amount = new TableColumn("Amount");
        TableColumn receiver = new TableColumn("Receiver");
        TableColumn sender = new TableColumn("Sender");
        TableColumn date = new TableColumn("Date");
        TableColumn state = new TableColumn("State");
        table.getColumns().addAll(id, amount, receiver, sender, date, state);
        pendingTransfersPopup.setVisible(false);
        issueTransactionPopup.setVisible(false);
        cancelRequestPopup.setVisible(false);

        logMessage_issue.setText("");
        issueAmountField.addEventFilter(KeyEvent.KEY_TYPED, Macros.numeric_Validation(10));
        issueRxLabel.setText("Rx");
    }

    public void setPage(){
        header.setText(home.user.name);

        if(home.user.type.equals(COMMERCIAL))
            transferType.setItems(FXCollections.observableArrayList("Send Money", "Commercial Transaction"));

        else if(home.user.type.equals(PERSONAL))
            transferType.setItems(FXCollections.observableArrayList("Send Money"));

    }

    // Open PopUps
    @FXML private void pendingTransfersOnAction(ActionEvent actionEvent) {
        pendingTransfersPopup.setVisible(true);
    }

    @FXML private void issueTransactionOnAction(ActionEvent actionEvent) {
        logMessage_issue.setText("");
        issueRxField.clear();
        issueAmountField.clear();
        issueTransactionPopup.setVisible(true);
    }

    @FXML private void cancelRequestOnAction(ActionEvent actionEvent) {
        cancelRequestPopup.setVisible(true);
    }

    // close commands
    @FXML private void closePendingTransfers(ActionEvent actionEvent) {

        pendingTransfersPopup.setVisible(false);
    }

    @FXML private void closeIssueTransaction(ActionEvent actionEvent) {
        logMessage_issue.setText("");
        issueRxField.clear();
        issueAmountField.clear();
        issueTransactionPopup.setVisible(false);
    }

    @FXML private void closeCancelRequest(ActionEvent actionEvent) {
        cancelRequestPopup.setVisible(false);
    }

    // ok commands
    @FXML private void issueOkTrans(ActionEvent actionEvent) {
        String email = issueRxField.getText();
        String type = transferType.getValue();
        Commercial com = new Commercial(home.user);
        User rxUser;

        if(issueRxField.getText().isEmpty() || (transferType.getValue() == null) || issueAmountField.getText().isEmpty()){
            logMessage_issue.setText("Please fulfil every field");
            return;
        }

        double amount = 0.0;
        if(type.isEmpty()) {
            logMessage_issue.setText("Please, tell us what type of\n transfer you want to do?");
            return;
        }

        if(!Macros.emailValidator(email)){
            logMessage_issue.setText("Please, insert a valid email:\n \texample@test.com");
            return;
        }

        rxUser = rx_db.querySQLfromEmail(email);
        if( rxUser == null){
            logMessage_issue.setText("This user does not exist");
            return;
        }

        amount = Double.parseDouble(issueAmountField.getText());
        if(amount == 0){
            logMessage_issue.setText("Specify amount");
            return;
        }
        System.out.println("rx ID: " + rxUser.user_id + " tx ID: " + home.user.user_id);
        issueAmountField.clear();
        issueRxField.clear();
        switch (type){
            case ("Send Money"):
                if(!com.sendMoney(home.user.user_id, rxUser.user_id, amount))
                    logMessage_issue.setText("Something went wrong");
                else logMessage_issue.setText("Money sent successfully");
                break;

            case("Commercial Transaction"):
                if(com.issueTransaction(home.user.user_id, rxUser.user_id, amount) == -1)
                    logMessage_issue.setText("Something went wrong");
                else logMessage_issue.setText("Transaction issued successfully, wait for buyer confirmation");
                break;
        }
    }

    @FXML private void issueUpdateEmailField(ActionEvent actionEvent) {
        String value = transferType.getValue();
        if (value.equals("Send Money")) {
            issueRxLabel.setText("Receiver:");
        } else if (value.equals("Commercial Transaction")) {
            issueRxLabel.setText("Buyer:");
        }
    }
}
