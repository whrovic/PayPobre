package account;

import db.Postgre;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    Postgre db = new Postgre();
    public String SignIn(String username, String email, String password){
        Date date = new Timestamp(System.currentTimeMillis());
        String sql = "INSERT into \"PayPobre\".users (user_id, username, password, email, created_on)"+
                "VALUES (default, '"+ username +"' , '"+ password +"', '"+ email +"', '"+ date +"')";
        String output_msg = db.executeSQL(sql);
        return output_msg;
    }

    public String LogIn(String username, String email, String password){
        String query = "SELECT '"+ username +"', '"+ password +"', '"+ email +"' FROM \"PayPobre\".users";
        db.querySQL(query);

        return "  ";
    }


}
