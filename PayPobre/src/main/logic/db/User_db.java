package db;

import account.*;

import java.sql.*;

import static util.Const.*;

public class User_db {
    Connection c;
    String db_UserName = "pswt0203";
    String db_PassWord = "plataoplomo";
    String db_URL = "jdbc:postgresql://db.fe.up.pt:5432/";
    String output_msg = null;
    public String connect() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
        } catch (Exception e) {
            //e.printStackTrace();
            //System.err.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
            return e.getMessage();
        }
        return "Opened database successfully";
    }

    public String CreateTable(){
        try {
            //Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
            output_msg = "Opened database successfully";

            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS \"PayPobre\".users (" +
                    "user_id serial PRIMARY KEY," +
                    "username text UNIQUE NOT NULL," +
                    "password text NOT NULL," +
                    "email text UNIQUE NOT NULL," +
                    "created_on TIMESTAMP NOT NULL," +
                    "last_login TIMESTAMP," +
                    "card integer UNIQUE NOT NULL)," +
                    "type text NOT NULL;";
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

    public String insertUser(String username, String email, String password, int card, String type, Date date, Double money){
        try {
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
            Statement stmt = c.createStatement();
            String sql = "INSERT into \"PayPobre\".users (user_id, username, password, email, created_on, card, type, money)"+
                    "VALUES (default, '"+ username +"' , '"+ password +"', '"+ email +"', '"+ date +"', '"+ card +"', '"+ type + "', '"+ money +"')";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            output_msg = "Registration Successful";
            return output_msg;

        }catch (Exception e) {
            try {
                Statement stmt = c.createStatement();
                String query = "SELECT *  FROM \"PayPobre\".users WHERE email = '" + email + "'";
                // ResultSet rs =
                stmt.executeQuery(query);
                //System.out.println(rs);
                //e.printStackTrace();
                return "Email already exists";
            } catch (SQLException ex) {
                return e.getMessage();
            }
        }
    }

    public User queryLogIn(String email, String password){
        User user = new User();
        try {
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
            Statement stmt = c.createStatement();
            String query = "SELECT *  FROM \"PayPobre\".users WHERE email = '" + email + "'";
            ResultSet rs = stmt.executeQuery(query);
            String userPass = null;

            while (rs.next()) {
                user.user_id = rs.getInt(1);
                user.username = rs.getString(2);
                userPass = rs.getString(3);
                user.email = rs.getString(4);
                user.created_on = rs.getDate(5);
                user.last_login = rs.getDate(6);
                user.wallet.card = rs.getLong(7);
                user.type = rs.getString(8);
                user.wallet.money = rs.getDouble(9);
            }

            assert userPass != null;
            if (userPass.compareTo(password) == 0){
                user.logMessage = LOGIN_SUCCESSFUL;
                return user;
            }

            user.logMessage = INCORRECT_PASSWORD;
            stmt.close();
            c.close();
            return user;

        }catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    public User querySQL(int ID){
        User user = new User();
        try {
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
            Statement stmt = c.createStatement();
            String query = "SELECT *  FROM \"PayPobre\".users WHERE user_id = '" + ID + "'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user.user_id = rs.getInt(1);
                user.username = rs.getString(2);
                user.email = rs.getString(4);
                user.created_on = rs.getDate(5);
                user.last_login = rs.getDate(6);
                user.wallet.card = rs.getLong(7);
                user.type = rs.getString(8);
                user.wallet.money = rs.getDouble(9);
            }
            stmt.close();
            c.close();
            return user;

        }catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    public boolean updateSQL(String sql){
        try {
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}