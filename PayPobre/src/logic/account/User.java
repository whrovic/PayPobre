package account;

import db.user_db;

import java.sql.Date;

import static util.Const.*;

public class User {
    public String username;
    public String email;
    public String type;
    private String password;
    public Date date;
    public int user_id;
    public int card;
    public long money;
    public String logMessage;
    user_db user_db = new user_db();
    public String Signup(String username, String email, String password, int card, String type){
        Date date = new Date(System.currentTimeMillis());
        money = 0;
        String output_msg = user_db.insertUser(username, email, password, card, type, date, money);
        return output_msg;
    }

    public User Login(String email, String password){

        Date date = new Date(System.currentTimeMillis());
        User user = user_db.queryLogIn(email, password);
        if (user.type == COMMERCIAL){

        }
        if (user.type == PERSONAL){

        }
        user = user_db.queryLogIn(email, password);
        if(user == null) {
            user.logMessage = "ERROR";
            return user;
        }
        String sql = "UPDATE \"PayPobre\".users SET last_login = '" + date + "' WHERE email = '" + email + "'";
        user_db.updateSQL(sql);
        return user;
    }
}