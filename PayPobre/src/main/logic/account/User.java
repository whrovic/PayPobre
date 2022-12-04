package account;

import db.User_db;
import wallet.Wallet;

import java.sql.Date;

import static util.Const.*;

public class User {
    public String username;
    public String email;
    public String type;
    public Date last_login;
    public Date created_on;
    public int user_id;
    public String logMessage;
    public Wallet wallet;
    User_db user_db = new User_db();

    public User(){
        this.wallet = new Wallet();
    }

    public User(User user){
        if(user == null) return;
        if(user.type.compareTo(PERSONAL)!=0) return;

        this.user_id = user.user_id;
        this.type = user.type;
        this.email = user.email;
        this.username = user.username;
        this.wallet = user.wallet;
    }

    public String Signup(String username, String email, String password, int card, String type){
        Date date = new Date(System.currentTimeMillis());
        return user_db.insertUser(username, email, password, card, type, date, 0.0);
    }

    public User Login(String email, String password){
        Date date = new Date(System.currentTimeMillis());
        User user = user_db.queryLogIn(email, password);

        if(user == null) return null;
        if(user.logMessage == null){
            user.logMessage = ERROR;
            return user;
        }
        if(user.logMessage.equals(INCORRECT_PASSWORD)) return user;

        String sql = "UPDATE \"PayPobre\".users SET last_login = '" + date + "' WHERE email = '" + email + "'";
        user_db.updateSQL(sql);
        user.last_login = date;
        return user;
    }
}