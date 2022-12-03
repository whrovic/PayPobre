package db;
import account.Commercial;
import account.User;
import java.sql.*;
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

    public String executeTransactionSQL(int seller_id, int buyer_id, Double amount, int trans_id, boolean done, Date date){
        User_db user_db = new User_db();
        try {
            c = DriverManager.getConnection(db_URL, db_UserName, db_PassWord);
            Statement stmt = c.createStatement();
            String sql = "INSERT into \"PayPobre\".Transfers (seller_id, buyer_id, amount, trans_id, done, date)"+
                    "VALUES (default, '"+ buyer_id +"', '"+ amount +"', '"+ trans_id +"', '"+ done + "', '"+ date +"')";
            stmt.executeUpdate(sql);

            Commercial seller = new Commercial(user_db.querySQL(seller_id));
            Double moneySeller = seller.wallet.money - amount;
            String sql_update_money_seller = "UPDATE \"PayPobre\".users SET money = '" + moneySeller + "' WHERE user = '" + seller_id + "'";
            user_db.updateSQL(sql_update_money_seller);

            User buyer = user_db.querySQL(buyer_id);
            int moneyBuyer = (int) (buyer.wallet.money - amount);
            String sql_update_money_buyer = "UPDATE \"PayPobre\".users SET money = '" + moneyBuyer + "' WHERE user = '" + buyer_id + "'";
            user_db.updateSQL(sql_update_money_buyer);
            stmt.close();
            c.close();

            output_msg = "Transaction Done";
            return output_msg;

        }catch (Exception e) {
            return e.getMessage();
        }
    }
}