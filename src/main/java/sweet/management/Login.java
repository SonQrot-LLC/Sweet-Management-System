package sweet.management;

import sweet.management.entities.User;

import java.sql.SQLException;
import java.sql.Timestamp;

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

    public static boolean signUp(String email, String password, String role, String city, Timestamp createdAt) throws SQLException {
        boolean result = false;
        if(!isLoggedIn) {
            if(User.getUserByEmail(email) == null) {
                User.createUser(new User(email, password, role, city, createdAt));
                login(email, password);
                result = true;
            }
        }

        return  result;
    }





    public static boolean isLoggedIn() {return isLoggedIn;}

}
