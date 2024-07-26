package sweet.management;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.management.entities.User;
import sweet.management.entities.UserProfile;
import sweet.management.services.DatabaseService;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountManagementStepDefinition {
    Login login;
    UserProfile userp;
    User user;
    boolean isUpdated;

    public AccountManagementStepDefinition() {
        login = new Login();
        userp = new UserProfile("ahmad123@gmail.com", "test1", "test2", "123123123", "anabta");
        isUpdated = false;
    }

    @Given("I log in with username {string} and password {string} and i am a Beneficiary User")
    public void iLogInWithUsernameAndPasswordAndIAmABeneficiaryUser(String email, String pass) {
        login.login(email, pass, DatabaseService.getConnection(true));
        assertTrue(login.isLoggedIn());
        assertTrue(login.getLoggedInUser().isBeneficiaryUser());
    }

    @When("I update my first name to {string}")
    public void iUpdateMyFirstNameTo(String name) throws SQLException {
        assertTrue(UserProfile.updateUserProfile(userp, DatabaseService.getConnection(true), UserProfile.UPDATE_FIRST_NAME));
    }

    @When("I update my last name to {string}")
    public void iUpdateMyLastNameTo(String string) {
        assertTrue(UserProfile.updateUserProfile(userp, DatabaseService.getConnection(true), UserProfile.UPDATE_LAST_NAME));
    }

    @When("I update my address to {string}")
    public void iUpdateMyAddressTo(String string) {
        assertTrue(UserProfile.updateUserProfile(userp, DatabaseService.getConnection(true), UserProfile.UPDATE_ADDRESS));
    }

    @When("I update my phone number to {string}")
    public void iUpdateMyPhoneNumberTo(String string) {
        assertTrue(UserProfile.updateUserProfile(userp, DatabaseService.getConnection(true), UserProfile.UPDATE_PHONE));
    }

    @Then("Update is successful")
    public void iShouldSeeAConfirmationMessage() {
        System.out.println("Personal account details updated successfully");
    }

    @When("I chose an invalid updateType")
    public void iChoseAnInvalidUpdateType() {
        isUpdated = UserProfile.updateUserProfile(userp, DatabaseService.getConnection(true), 10);
    }

    @Then("Update fails")
    public void updateFails() {
        assertFalse(isUpdated);
    }

    @Given("I log in with username {string} and password {string}")
    public void iLogInWithUsernameAndPassword(String email, String pass) {
        login.login(email, pass, DatabaseService.getConnection(true));
        assertTrue(login.isLoggedIn());
        assertTrue(login.getLoggedInUser().isBeneficiaryUser());
        user = login.getLoggedInUser();
        isUpdated = false;
    }

    @When("I update my password to {string}")
    public void iUpdateMyPasswordTo(String pass) {
        user.setPassword(pass);
        isUpdated = User.updateUser(user, DatabaseService.getConnection(true), User.UPDATE_PASSWORD);
    }

    @When("I update my city to {string}")
    public void iUpdateMyCityTo(String city) {
        user.setCity(city);
        isUpdated = User.updateUser(user, DatabaseService.getConnection(true), User.UPDATE_CITY);
    }

    @When("I chose an invalid account updateType")
    public void iChoseAnInvalidAccountUpdateType() {
        isUpdated = User.updateUser(user, DatabaseService.getConnection(true), 10);
    }

    @When("I delete account")
    public void iDeleteAccount() {
        isUpdated = User.updateUser(user, DatabaseService.getConnection(true), User.DELETE_ACCOUNT);
    }

    @Then("Account is deleted")
    public void accountIsDeleted() {
       assertTrue(isUpdated);
        try {
            User.createUser(user,DatabaseService.getConnection(true));
            UserProfile.createUserProfile(userp,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
