package sweet.management;

import sweet.management.entities.User;
import sweet.management.services.TypeChecker;

import java.sql.SQLException;
import java.sql.Timestamp;

public class Login {
    private  boolean isLoggedIn = false;


    public  boolean login(String email, String password) {
        if (!isLoggedIn){
            User user = null;
            try {
                user = User.getUserByEmail(email);
                if (user != null && user.getPassword().equals(password)){
                    isLoggedIn = true;
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();            }
                return false;
        }
        return false;
    }

    public  boolean signUp(String email, String password, String role, String city)  {
        if(!isLoggedIn) {
            try {
                if(User.getUserByEmail(email) == null && TypeChecker.isValidEmail(email)) {
                    User.createUser(new User(email, password, role, city));
                    login(email, password);
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }





    public  boolean isLoggedIn() {return isLoggedIn;}

}
