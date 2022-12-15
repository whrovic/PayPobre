package pageController;

import account.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.DataOutput;
import java.io.IOException;
import java.text.NumberFormat;

public class Wallet extends GenericSubPage {
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    String balance;
    @FXML private Label logMessage;
    @FXML private TextField withdrawAmount;
    @FXML private TextField depositAmount;
    @FXML private Label cardBalance;
    @FXML private Label cardNumber;
    @FXML private AnchorPane cardBackground;
    @FXML private Label header;

    public void setPage(){
        logMessage.setText("");
        balance = formatter.format(home.user.wallet.money);
        header.setText("Wallet - " + home.user.name);
        cardBalance.setText("Balance: " + balance);
        cardNumber.setText("**** **** **** " + home.user.wallet.card);
    }

    @FXML private void backHome(ActionEvent actionEvent) throws IOException {
        goToHome(actionEvent);
    }

    @FXML private void initialize(){

    }

    @FXML private void deposit(ActionEvent actionEvent) {
        if (depositAmount.getText().isEmpty()) {
            logMessage.setText("This field is empty, please insert an amount");
            logMessage.setTextFill(Color.RED);
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(depositAmount.getText());
        } catch (NumberFormatException e) {
            logMessage.setText("Please insert a valid amount to deposit");
            logMessage.setTextFill(Color.RED);
            return;
        }

        if(!home.user.wallet.deposit(amount, home.user)){
            logMessage.setText("Something went wrong");
            logMessage.setTextFill(Color.RED);
            return;
        }
        logMessage.setText("Deposit Successful");
        logMessage.setTextFill(Color.GREEN);
        balance = formatter.format(home.user.wallet.money);

        cardBalance.setText("Balance: " + balance);
    }

    @FXML private void withdraw(ActionEvent actionEvent) {
        if (withdrawAmount.getText().isEmpty()) {
            logMessage.setText("This field is empty, please insert an amount");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(withdrawAmount.getText());

        } catch (NumberFormatException e) {
            logMessage.setText("Please insert a valid amount to withdraw");
            logMessage.setTextFill(Color.RED);
            return;
        }

        if(!home.user.wallet.withdraw(amount, home.user)){
            logMessage.setText("You don´t have enough money in your\n account to perform this operation");
            logMessage.setTextFill(Color.RED);
            return;
        }

        logMessage.setTextFill(Color.GREEN);
        logMessage.setText("Withdraw Successful");
        balance = formatter.format(home.user.wallet.money);
        cardBalance.setText("Balance: " + balance);
    }

    @FXML private void goToProfile(ActionEvent actionEvent) {
        logMessage.setText("This feature isn´t implemented yet");
    }
}
