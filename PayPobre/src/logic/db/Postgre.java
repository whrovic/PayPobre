package db;

import account.User;
import util.Macros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class Postgre {
    Connection c;
    String name = "pswt0203";
    String pass = "plataoplomo";
    String db_url = "jdbc:postgresql://db.fe.up.pt:5432/";
    String output_msg = null;
    public String connect() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(db_url, name, pass);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return e.getMessage();
        }
        return "Opened database successfully";
    }

    public String CreateTable(){
        try {
            //Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(db_url, name, pass);
            output_msg = "Opened database successfully";

            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS \"PayPobre\".users (" +
                    "user_id serial PRIMARY KEY," +
                    "username text UNIQUE NOT NULL," +
                    "password tex NOT NULL," +
                    "email text UNIQUE NOT NULL," +
                    "created_on TIMESTAMP NOT NULL," +
                    "last_login TIMESTAMP);";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            //output_msg = output_msg + "\n" + "Table created successfully";
        } catch (Exception e) {
            System.exit(0);
            output_msg = e.getClass().getName()+": "+ e.getMessage();
            return e.getMessage();
        }
        return output_msg;
    }

    public String executeSQL(String sql){
        try {
            c = DriverManager.getConnection(db_url, name, pass);
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            c.close();
            output_msg = "Registration Successful";
            return output_msg;

        }catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public boolean querySQL(String email, String password){
        account.User user = new User();
        try {
            c = DriverManager.getConnection(db_url, name, pass);
            Statement stmt = c.createStatement();
            String query = "SELECT *  FROM \"PayPobre\".users WHERE email = '" + email + "'";
            ResultSet rs = stmt.executeQuery(query);
            String dbPass = null;
            while (rs.next()) {
                user.user_id = rs.getInt(1);
                user.username = rs.getString(2);
                user.email = rs.getString(4);
                dbPass = new String(rs.getString(3));
            }

            assert dbPass != null;
            if (dbPass.compareTo(password) == 0)
                return true;

            c.close();
            return false;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}