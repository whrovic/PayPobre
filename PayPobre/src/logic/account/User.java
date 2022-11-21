package account;

import db.Postgre;

import java.sql.Timestamp;
import java.sql.Date;

public class User {
    public String username;
    public String email;
    private String password;
    public Date date;
    public int user_id;
    private int card;
    Postgre db = new Postgre();
    public String Signup(String username, String email, String password, int card, String type){
        Date date = new Date(System.currentTimeMillis());

        String output_msg = db.executeSQL(username, email, password, card, type, date);
        return output_msg;
    }

    public String Login(String email, String password){
        Date date = new Date(System.currentTimeMillis());
        String output_msg;
        if(db.querySQL(email, password)){
            output_msg = "Log in Successful";
        }
        else output_msg = "Password or email incorrect";
        String sql = "UPDATE \"PayPobre\".users SET last_login = '"+ date +"' WHERE email = '" + email + "'";
        db.updateSQL(sql);
        return output_msg;
    }
}