package test.logic.account;

import account.User;
import org.junit.jupiter.api.Test;
import static util.Const.*;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void signup() {
        var user = new User();
        assertEquals("Registration Successful", user.Signup("Agostinho", "gusto@gmail.com", "12345", 1234567890, PERSONAL));
    }

    @Test
    void login() {

    }
}