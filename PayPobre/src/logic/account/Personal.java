package account;
import static util.Const.*;
import wallet.*;
import db.Transfers_db;
import db.User_db;
import java.sql.Date;

public class Personal extends User{
    public Personal(User user){
        super(user);
    }

    public boolean acceptTransaction(boolean accept, int trans_id){
        Transfers_db transfer = new Transfers_db();
        return transfer.updateTransactionSQL(trans_id, accept);
    }
}
