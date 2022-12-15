package db;
import account.Commercial;
import account.User;
import transactions.Transaction;

import java.sql.*;
import java.util.Random;

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
            output_msg = "Opened database successfully";

            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS \"PayPobre\".Transfers (" +
                    "seller_id integer NOT NULL," +
                    "buyer_id text UNIQUE NOT NULL," +
                    "amount money NOT NULL," +
                    "trans_id text UNIQUE NOT NULL" +
                    "done boolean NOT NULL" +
                    "date date NOT NULL;";
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

    public int executeTransactionSQL(int seller_id, int buyer_id, Double amount, Date date){
        try {
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
            Statement stmt = c.createStatement();
            String state = "Waiting";
            Random rand = new Random();
            int upperbound = 999999;
            int trans_id = rand.nextInt(upperbound);
            String sql = "INSERT into \"PayPobre\".Transfers (seller_id, buyer_id, amount, trans_id, done, date)"+
                    "VALUES ('"+ seller_id +"', '"+ buyer_id +"', '"+ amount +"', '"+ trans_id +"', '"+ state + "', '"+ date +"')";
            while(stmt.executeUpdate(sql) == -1){
                trans_id = rand.nextInt(upperbound);
                sql = "INSERT into \"PayPobre\".Transfers (seller_id, buyer_id, amount, trans_id, done, date)"+
                        "VALUES ('"+ seller_id +"', '"+ buyer_id +"', '"+ amount +"', '"+ trans_id +"', '"+ state + "', '"+ date +"')";
            }
            stmt.close();
            c.close();
            return trans_id;

        }catch (Exception e) {
            return -1;
        }
    }
    public boolean updateTransactionSQL(int trans_id, boolean done){
        User_db user_db = new User_db();
        Transfers_db trans_db = new Transfers_db();
        Transaction trans;
        try {
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
            Statement stmt = c.createStatement();
            trans = trans_db.querySQL(trans_id);
            if (done) {
                Commercial seller = new Commercial(user_db.querySQL(trans.seller_id));
                double moneySeller = seller.wallet.money + trans.amount;
                String sql_update_money_seller = "UPDATE \"PayPobre\".users SET money = '" + moneySeller + "' WHERE user = '" + trans.seller_id + "'";
                user_db.updateSQL(sql_update_money_seller);

                User buyer = user_db.querySQL(trans.buyer_id);
                int moneyBuyer = (int) (buyer.wallet.money - trans.amount);
                String sql_update_money_buyer = "UPDATE \"PayPobre\".users SET money = '" + moneyBuyer + "' WHERE user = '" + trans.buyer_id + "'";
                user_db.updateSQL(sql_update_money_buyer);

                String sql_update_transaction = "UPDATE \"PayPobre\".Transfers SET state = Done WHERE trans_id = '" + trans.buyer_id + "'";
                user_db.updateSQL(sql_update_transaction);
                stmt.close();
                c.close();
                return true;
            }
            else {
                String sql_update_transaction = "UPDATE \"PayPobre\".Transfers SET state = Canceled WHERE trans_id = '" + trans.buyer_id + "'";
                user_db.updateSQL(sql_update_transaction);
                stmt.close();
                c.close();
                return true;
            }

        }catch (Exception e) {
            return false;
        }
    }

    public Transaction querySQL(int ID){
        Transaction trans = new Transaction();
        try {
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
            Statement stmt = c.createStatement();
            String query = "SELECT *  FROM \"PayPobre\".Transfers WHERE trans_id = '" + ID + "'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                trans.seller_id = rs.getInt(1);
                trans.buyer_id = rs.getInt(2);
                trans.amount = rs.getDouble(3);
                trans.state = rs.getString(5);
                trans.date = rs.getDate(6);
            }
            trans.trans_id = ID;
            stmt.close();
            c.close();
            return trans;

        }catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }
}