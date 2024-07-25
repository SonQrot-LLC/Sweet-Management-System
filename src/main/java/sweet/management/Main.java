package sweet.management;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(Login.login("toostronkm@gmaiil.com","777"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}