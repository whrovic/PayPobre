package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
                    "username VARCHAR ( 50 ) UNIQUE NOT NULL," +
                    "password VARCHAR ( 50 ) NOT NULL," +
                    "email VARCHAR ( 255 ) UNIQUE NOT NULL," +
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
            return output_msg;

        }catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String querySQL(String sql){
        try {
            c = DriverManager.getConnection(db_url, name, pass);
            Statement stmt = c.createStatement();
            stmt.executeQuery(sql);
            return output_msg;

        }catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}