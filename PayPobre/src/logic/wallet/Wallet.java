package wallet;

import account.*;
import db.User_db;

public class Wallet{
    public Long card;
    public Double money;

    public boolean Deposit(Double input, User user) {
        User_db user_db = new User_db();
        user.wallet.money = user.wallet.money + input;
        String sql = "UPDATE \"PayPobre\".users SET money = '" + user.wallet.money + "' WHERE email = '" + user.email + "'";
        return user_db.updateSQL(sql);
    }

    public void test(){



    }
}
