package sweet.management;

import sweet.management.entities.User;

import java.sql.SQLException;

public class Login {
    private static boolean isLoggedIn = false;


    public static boolean login(String email, String password) throws SQLException {
        boolean result = false;
        if (!isLoggedIn){
            User user = User.getUserByEmail(email);
            if (user != null && user.getPassword().equals(password)){
                isLoggedIn = true;
                result = true;
            }
        }
        return result;
    }





    public static boolean isLoggedIn() {return isLoggedIn;}

}
