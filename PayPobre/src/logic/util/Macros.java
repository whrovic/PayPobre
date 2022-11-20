package util;

import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Macros {
    public static boolean creditCardValidator(String cardStr, int card) {
        try {
            card = parseInt(cardStr);
        } catch (NumberFormatException ex) {
            card = 0;
            return false;
        }
        return true;
    }

    public static boolean emailValidator(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null   )
            return false;
        return pat.matcher(email).matches();
    }

}
