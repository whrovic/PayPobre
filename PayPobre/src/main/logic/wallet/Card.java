package wallet;

import util.*;

public class Card {
    private String cardNumber;

    public void setCard(double card){

    }

    public static String formatCard(String cardNumber) {
        if (cardNumber == null) return null;
        char delimiter = ' ';
        return cardNumber.replaceAll(".{4}(?!$)", "$0" + delimiter);
    }
}

