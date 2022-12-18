package pageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import transactions.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Transfers extends GenericSubPage{
    private Transaction t;
    List<Button> buttonlist = new ArrayList<>();

    @FXML private Label header;
    @FXML private TableView table;
    @FXML private HBox pendingTransfersPopup;
    @FXML private HBox issueTransactionPopup;
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
    }

    public void setPage(){
        header.setText(home.user.name);
    }

    @FXML private void pendingTransfersOnAction(ActionEvent actionEvent) {
        pendingTransfersPopup.setVisible(true);
    }

    @FXML private void issueTransactionOnAction(ActionEvent actionEvent) {
        issueTransactionPopup.setVisible(true);
    }

    @FXML private void cancelRequestOnAction(ActionEvent actionEvent) {
        cancelRequestPopup.setVisible(true);
    }

    @FXML private void closePendingTransfers(ActionEvent actionEvent) {
        pendingTransfersPopup.setVisible(false);
    }

    @FXML private void closeIssueTransaction(ActionEvent actionEvent) {
        issueTransactionPopup.setVisible(false);
    }

    @FXML private void closeCancelRequest(ActionEvent actionEvent) {
        cancelRequestPopup.setVisible(false);
    }
}
