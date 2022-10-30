package cli;
import db.*;

public class Main {
    public static void main(String[] args) {

        //connect to DB
        Postgre db = new Postgre();
        System.out.println(db.connect());

        System.out.println(db.CreateTable());
    }
}