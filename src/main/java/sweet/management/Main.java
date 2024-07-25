package sweet.management;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

//            System.out.println(Login.login("toostronkm@gmaiil.com","777"));
            System.out.println(Login.signUp("salam@hawa.com","222", "store_owner", "Tulkarem", currentTimestamp));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}