package db;

import java.sql.*;

public class transfers_db {
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
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
            output_msg = "Opened database successfully";

            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS \"PayPobre\".Transfers (" +
                    "seller_id integer NOT NULL," +
                    "buyer_id text UNIQUE NOT NULL," +
                    "amount money NOT NULL," +
                    "trans_id text UNIQUE NOT NULL" +
                    "done boolean NOT NULL;";
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

    public String executeTransactionSQL(int seller_id, int buyer_id, long amount, int trans_id, boolean done, Date date){
        try {
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
            Statement stmt = c.createStatement();
            String sql = "INSERT into \"PayPobre\".Transfers (seller_id, buyer_id, amount, trans_id, done, date)"+
                    "VALUES (default, '"+ seller_id +"' , '"+ buyer_id +"', '"+ amount +"', '"+ date +"', '"+ trans_id +"', '"+ done + "', '"+ date +"')";
            stmt.executeUpdate(sql);
            c.close();
            output_msg = "Registration Successful";
            return output_msg;

        }catch (Exception e) {
            try {
                Statement stmt = c.createStatement();
                String query = "SELECT *  FROM \"PayPobre\".users WHERE email = '" + email + "'";
                ResultSet rs = stmt.executeQuery(query);
                //System.out.println(rs);
                //e.printStackTrace();
                return "Email already exists";
            } catch (SQLException ex) {
                return e.getMessage();
            }
        }
    }




}
