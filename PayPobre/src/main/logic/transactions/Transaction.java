package transactions;

import db.Transfers_db;

import java.sql.Date;

public class Transaction {
    public int seller_id;
    public int buyer_id;
    public double amount;
    public int trans_id;
    public String state;
    public String date;
    Transfers_db transfers_db = new Transfers_db();

    public Transaction (int seller_id, int buyer_id, double amount, int trans_id, String state, String date){
        this.seller_id = seller_id;
        this.buyer_id = buyer_id;
        this.amount = amount;
        this.trans_id = trans_id;
        this.state = state;
        this.date = date;
    }

    public Transaction(){
    }


}
