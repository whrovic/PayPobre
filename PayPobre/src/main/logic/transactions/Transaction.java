package transactions;

import db.Transfers_db;

import java.sql.Date;

public class Transaction {
    public int seller_id;
    public int buyer_id;
    public double amount;
    public int trans_id;
    public String state;
    public Date date;
    Transfers_db transfers_db = new Transfers_db();
}
