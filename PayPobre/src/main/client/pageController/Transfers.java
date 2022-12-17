package pageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import transactions.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Transfers extends GenericSubPage{
    private Transaction t;
    List<Button> buttonlist = new ArrayList<>();

    @FXML private Label header;
    @FXML private TableView table;

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
    }

    public void setPage(){
        header.setText("Transfers - " + home.user.name);
    }
}
