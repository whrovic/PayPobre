package account;

import db.Postgre;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    public String username;
    public String email;
    private String password;
    public Date date;
    public int user_id;
    private int card;
    Postgre db = new Postgre();
    public String Signup(String username, String email, String password, int card, String type){
        Date date = new Timestamp(System.currentTimeMillis());
        String sql = "INSERT into \"PayPobre\".users (user_id, username, password, email, created_on, card)"+
                "VALUES (default, '"+ username +"' , '"+ password +"', '"+ email +"', '"+ date +"', '"+ card +"')";
        String output_msg = db.executeSQL(sql);
        return output_msg;
    }

    public String Login(String email, String password){
        Date date = new Timestamp(System.currentTimeMillis());
        String output_msg;
        if(db.querySQL(email, password)){
            output_msg = "Log in Successful";
        }
        else output_msg = "Password or email incorrect";
        String sql = "UPDATE \"PayPobre\".users SET last_login = ";
        return output_msg;
    }
}
