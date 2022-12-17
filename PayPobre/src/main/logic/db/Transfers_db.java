package db;
import account.Commercial;
import account.User;
import transactions.Transaction;

import java.sql.*;
import java.util.Random;

import static util.Const.*;

public class Transfers_db {
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
            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS \"PayPobre\".transfers (" +
                    "seller_id integer NOT NULL," +
                    "buyer_id text NOT NULL," +
                    "amount numeric NOT NULL," +
                    "trans_id text UNIQUE NOT NULL PRIMARY KEY," +
                    "state text NOT NULL," +
                    "date text NOT NULL);";

            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            output_msg = output_msg + "\n" + "Table created successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return output_msg;
    }
    public int executeTransactionSQL(int seller_id, int buyer_id, Double amount, String date, String state){
        try {
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
            Statement stmt = c.createStatement();

            Random rand = new Random();
            int upperbound = 999999;
            int trans_id = rand.nextInt(upperbound);
            String sql = "INSERT into \"PayPobre\".transfers (seller_id, buyer_id, amount, trans_id, state, date)"+
                    "VALUES ('"+ seller_id +"', '"+ buyer_id +"', '"+ amount +"', '"+ trans_id +"', '"+ state + "', '"+ date +"')";
            while(stmt.executeUpdate(sql) == -1){
                trans_id = rand.nextInt(upperbound);
                sql = "INSERT into \"PayPobre\".transfers (seller_id, buyer_id, amount, trans_id, state, date)"+
                        "VALUES ('"+ seller_id +"', '"+ buyer_id +"', '"+ amount +"', '"+ trans_id +"', '"+ state + "', '"+ date +"')";
            }
            stmt.close();
            c.close();
            return trans_id;

        }catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public boolean updateTransactionSQL(int trans_id, String state){
        User_db user_db = new User_db();
        Transfers_db trans_db = new Transfers_db();
        Transaction trans;
        try {
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
            Statement stmt = c.createStatement();
            trans = trans_db.querySQL(trans_id);
            if (trans.state.equals(PENDING) || trans.state.equals(INSTANTANEOUS)){
                if (state.equals(DONE) || state.equals(INSTANTANEOUS)) {
                    Commercial seller = new Commercial(user_db.queryUserSQL(trans.seller_id));
                    double moneySeller = seller.wallet.money + trans.amount;
                    String sql_update_money_seller = "UPDATE \"PayPobre\".users SET money = '" + moneySeller + "' WHERE user_id = '" + trans.seller_id + "'";
                    user_db.updateSQL(sql_update_money_seller);

                    User buyer = user_db.queryUserSQL(trans.buyer_id);
                    int moneyBuyer = (int) (buyer.wallet.money - trans.amount);
                    String sql_update_money_buyer = "UPDATE \"PayPobre\".users SET money = '" + moneyBuyer + "' WHERE user_id = '" + trans.buyer_id + "'";
                    user_db.updateSQL(sql_update_money_buyer);

                    if (state.equals(DONE)) {
                        String sql_update_transaction = "UPDATE \"PayPobre\".transfers SET state = '" + state + "' WHERE trans_id = '" + trans_id + "'";
                        trans_db.updateSQL(sql_update_transaction);
                    }
                } else {
                    String sql_update_transaction = "UPDATE \"PayPobre\".transfers SET state = '" + CANCELED + "' WHERE trans_id = '" + trans.buyer_id + "'";
                    trans_db.updateSQL(sql_update_transaction);
                }
                stmt.close();
                c.close();
                return true;
            } else{
                stmt.close();
                c.close();
                return false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Transaction querySQL(int ID){
        Transaction trans = new Transaction();
        try {
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
            Statement stmt = c.createStatement();
            String query = "SELECT *  FROM \"PayPobre\".transfers WHERE trans_id = '" + ID + "'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                trans.seller_id = rs.getInt(1);
                trans.buyer_id = rs.getInt(2);
                trans.amount = rs.getDouble(3);
                trans.trans_id = rs.getInt(4);
                trans.state = rs.getString(5);
                trans.date = rs.getString(6);
            }
            stmt.close();
            c.close();
            return trans;

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Transaction[] queryStateSQL(String state){
        //Transaction[] trans = {new Transaction()};
        try {
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
            Statement stmt = c.createStatement();
            String query = "SELECT *  FROM \"PayPobre\".transfers WHERE state = '" + state + "'";
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            int length = queryLengthStateSQL(state);
            Transaction[] trans = new Transaction[length];

            i = 0;
            while (rs.next()) {
                trans[i] = new Transaction();
                trans[i].seller_id = rs.getInt(1);
                trans[i].buyer_id = rs.getInt(2);
                trans[i].amount = rs.getDouble(3);
                trans[i].trans_id = rs.getInt(4);
                trans[i].state = rs.getString(5);
                trans[i].date = rs.getString(6);
                i++;

            }
            stmt.close();
            c.close();
            return trans;

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public int queryLengthStateSQL(String state){
        try {
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
            Statement stmt = c.createStatement();
            String query = "SELECT *  FROM \"PayPobre\".transfers WHERE state = '" + state + "'";
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                rs.getInt(1);
                i++;
            }
            stmt.close();
            c.close();
            return i;

        }catch (Exception e) {
            e.printStackTrace();
            return -1;
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