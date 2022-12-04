package account;

import db.Transfers_db;
import java.sql.Date;

public class Commercial extends Personal{
    public String company;
    public Commercial(User user){
        super(user);
    }

    public Commercial(User user, String company){
        super(user);
        this.company = company;
    }

    public int issueTransaction(int seller_id, int buyer_id, Double amount){
        Date date = new Date(System.currentTimeMillis());
        Transfers_db transfer = new Transfers_db();
        return transfer.executeTransactionSQL(seller_id, buyer_id, amount, date);
    }
}
