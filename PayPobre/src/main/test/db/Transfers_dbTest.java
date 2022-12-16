package db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Transfers_dbTest {

    @Test
    void connect() {
        var trans = new Transfers_db();
        System.out.println(trans.connect());

    }

    @Test
    void createTable() {
        var trans = new Transfers_db();
        System.out.println(trans.CreateTable());
    }

    @Test
    void executeTransactionSQL() {
    }

    @Test
    void updateTransactionSQL() {
    }

    @Test
    void querySQL() {
    }
}