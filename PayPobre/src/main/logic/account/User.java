package account;

import db.User_db;
import util.Macros;
import wallet.CreditCardValidation;
import wallet.Wallet;

import java.sql.Date;

import static util.Const.*;

public class User {
    public String name;
    public String email;
    public String type;
    public Date last_login;
    public Date created_on;
    public int user_id;
    public int logERROR;
    public Wallet wallet;
    User_db user_db = new User_db();

    public User(){
        this.wallet = new Wallet();
    }

    public User(User user){
        //if(user == null) return;

        user_id = user.user_id;
        type = user.type;
        email = user.email;
        name = user.name;
        wallet = user.wallet;
    }

    public User setUser(String username, String email, String type){
        User user = new User();

        user.type = type;
        user.email = email;
        user.name = username;

        return user;
    }

    public int Signup(String username, String email, String password, String cardStr, String type){
        Date date = new Date(System.currentTimeMillis());

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || type == null || cardStr == null) return e_EMPTY_FIELDS;
        if(!Macros.emailValidator(email)) return e_INVALID_EMAIL;
        if(!CreditCardValidation.validation(cardStr)) return e_INVALID_CREDIT_CARD;

        cardStr = cardStr.replaceAll("[^\\d.]", "");
        return user_db.insertUser(username, email, password, cardStr, type, date, 0.0);
    }

    public User Login(String email, String password){
        Date date = new Date(System.currentTimeMillis());
        User user = user_db.queryLogIn(email, password);

        if(user == null) return null;
        if(user.logERROR == e_WRONG_CREDENTIALS) return user;

        String sql = "UPDATE \"PayPobre\".users SET last_login = '" + date + "' WHERE email = '" + email + "'";
        user_db.updateSQL(sql);
        user.last_login = date;
        return user;
    }

    public User(String email){
        User u = user_db.querySQLfromEmail(email);
    }
}