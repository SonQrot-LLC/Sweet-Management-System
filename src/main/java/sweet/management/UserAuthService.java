package sweet.management;

import sweet.management.entities.Store;
import sweet.management.entities.User;
import sweet.management.entities.UserProfile;
import sweet.management.services.DatabaseService;
import sweet.management.services.TypeChecker;

import java.sql.Connection;
import java.sql.SQLException;

public class UserAuthService {
    private boolean isLoggedIn = false;
    private User loggedInUser;
    private UserProfile loggedInUserProfile;
    private Store loggedInStore;


    public boolean login(String email, String password, Connection conn) {
        if (!isLoggedIn) {
            try {
                User user = User.getUserByEmail(email, conn);
                if (user != null && user.getPassword().equals(password)) {
                    isLoggedIn = true;
                    loggedInUser = user;
                    if (loggedInUser.isBeneficiaryUser())
                        loggedInUserProfile = UserProfile.getUserProfileByEmail(email, conn);
                    else if(loggedInUser.isStoreOwner() || loggedInUser.isRawMaterialSupplier())
                        loggedInStore = Store.getStoreByOwnerEmail(email, conn);
                    return true;
                }
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    public boolean signUp(String email, String password, String role, String city, Connection conn) {
        if (!isLoggedIn) {
            try {
                if (conn == null)
                    throw new SQLException();
                if (User.getUserByEmail(email, DatabaseService.getConnection(true)) == null && TypeChecker.isValidEmail(email)) {
                    User.createUser(new User(email, password, role, city), conn);
                    login(email, password, DatabaseService.getConnection(true));
                    return true;
                }
            } catch (SQLException e) {
                return false;
            }
        }

        return false;
    }

    public User getLoggedInUser() {
            return loggedInUser;
    }

    public UserProfile getLoggedInUserProfile() {
        return loggedInUserProfile;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public Store getLoggedInStore() {
        return loggedInStore;
    }
}
