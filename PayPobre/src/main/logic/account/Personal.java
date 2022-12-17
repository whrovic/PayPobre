package account;
import db.Transfers_db;

import static util.Const.*;

public class Personal extends User{
    public Personal(User user){
        super(user);
    }

    public boolean acceptTransaction(int trans_id){
        String state = DONE;
        Transfers_db transfer = new Transfers_db();
        return transfer.updateTransactionSQL(trans_id, state);
    }

    public boolean cancelTransaction(int trans_id){
        String state = CANCELED;
        Transfers_db transfer = new Transfers_db();
        return transfer.updateTransactionSQL(trans_id, state);
    }
}
