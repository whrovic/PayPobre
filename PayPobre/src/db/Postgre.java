package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Postgre {
    String name = "pswt0203";
    String pass = "plataoplomo";
    String db_url = "jdbc:postgresql://db.fe.up.pt:5432/";
    String output_msg = null;
    public String connect() {
        Connection c = null;
        try {
            //Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(db_url, name, pass);
        } catch (Exception e) {
            //e.printStackTrace();
            //System.err.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
            return e.getMessage();
        }
        return "Opened database successfully";
    }

    public String CreateTable(){
        Connection c = null;
        Statement stmt = null;
        try {
            //Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(db_url, name, pass);
            output_msg = "Opened database successfully";

            stmt = c.createStatement();
            String sql = "CREATE TABLE COMPANY " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.exit(0);
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            return e.getMessage();
        }

        output_msg = output_msg + "\n" + "Table created successfully";
        return output_msg;
    }
}
