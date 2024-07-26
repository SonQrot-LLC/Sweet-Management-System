package sweet.management;

import sweet.management.entities.User;
import sweet.management.services.DatabaseService;
import sweet.management.services.TypeChecker;

import java.sql.Connection;
import java.sql.SQLException;

public class Login {
    private  boolean isLoggedIn = false;
    private User loggedInUser;


    public  boolean login(String email, String password, Connection conn) {
        if (!isLoggedIn){
            User user = null;
            try {
                user = User.getUserByEmail(email, conn);
                if (user != null && user.getPassword().equals(password)){
                    isLoggedIn = true;
                    loggedInUser = user;
                    return true;
                }
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    public  boolean signUp(String email, String password, String role, String city, Connection conn)  {
        if(!isLoggedIn) {

            try {
                if(User.getUserByEmail(email, DatabaseService.getConnection(true)) == null && TypeChecker.isValidEmail(email)) {
                    User.createUser(new User(email, password, role, city), conn);
                    login(email, password, DatabaseService.getConnection(true) );
                    return true;
                }
            } catch (SQLException e) {
                return false;
            }
        }

        return false;
    }

    public User getLoggedInUser() {
        if(isLoggedIn)return loggedInUser;
        return null;
    }

    public  boolean isLoggedIn() {return isLoggedIn;}


}
