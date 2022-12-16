package account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommercialTest {

    @Test
    void issueTransaction() {
        assertNotEquals(-1, Commercial.issueTransaction(68, 70, 12.0));
    }
}