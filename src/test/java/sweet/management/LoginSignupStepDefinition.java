package sweet.management;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.management.entities.User;
import sweet.management.services.DatabaseService;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginSignupStepDefinition {
    User currentUser;
    Login login = new Login();


    public LoginSignupStepDefinition() {

        currentUser = new User("salam@hawa.com", "222", "store_owner", "Tulkarem");

    }

    @Given("that the user is not logged in")
    public void thatTheUserIsNotLoggedIn() {
        assertFalse(login.isLoggedIn());
    }

    @When("The information is valid email is {string} and password is {string}")
    public void theInformationIsValidEmailIsAndPasswordIs(String email, String password) {
        assertTrue(login.login(email, password, DatabaseService.getConnection(true)));

    }

    @Then("User successfully log in")
    public void userSuccessfullyLogIn() {
        assertTrue(login.isLoggedIn());
    }


    @When("The information is valid email is {string} and password is {string} something went wrong with the sql")
    public void theInformationIsValidEmailIsAndPasswordIsSomethingWentWrongWithTheSql(String email, String password) {
        assertFalse(login.login(email, password, DatabaseService.getConnection(false)));
    }

    @Then("user failed in log in")
    public void userFailedInLogIn() {
        assertFalse(login.isLoggedIn());
    }



    @When("the email is invalid email is {string} and password is {string}")
    public void theEmailIsInvalidEmailIsAndPasswordIs(String email, String password) {
        login.login(email, password, DatabaseService.getConnection(true));

    }





    @When("the password is invalid email is {string} and password is {string}")
    public void thePasswordIsInvalidEmailIsAndPasswordIs(String email, String password) {
        login.login(email, password, DatabaseService.getConnection(true));
    }

    @When("the information is invalid, email is {string} and password is {string}")
    public void theInformationIsInvalidEmailIsAndPasswordIs(String email, String password) {
        login.login(email, password, DatabaseService.getConnection(true));
    }

    //----------------------------Sign up------------------------------------------

    @When("the information exists, the email is {string}")
    public void theInformationExistsTheEmailIs(String email) {
        assertFalse(login.signUp(email,"777","admin","Tulkarm", DatabaseService.getConnection(true)));
    }

    @Then("signing up fails")
    public void signingUpFails() {
        assertFalse(login.isLoggedIn());
    }

    @When("the email {string} format is incorrect")
    public void theEmailFormatIsIncorrect(String email) {
        login.signUp(email,"777","admin","Tulkarm", DatabaseService.getConnection(true));
    }

    @When("the email format is correct and the email {string} does not exist in the database")
    public void theEmailFormatIsCorrectAndTheEmailDoesNotExistInTheDatabase(String email) {
        assertTrue(login.signUp(email,"777","admin","Tulkarm", DatabaseService.getConnection(true)));
    }

    @Then("signing up succeeds")
    public void signingUpSucceeds() {
        assertTrue(login.isLoggedIn());
        try {
            User.deleteUser(login.getLoggedInUser().getEmail(),DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
