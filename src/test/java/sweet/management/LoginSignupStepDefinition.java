package sweet.management;


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
    UserAuthService userAuthService = new UserAuthService();
    boolean loggedInUsingAnotherAccount;
    boolean singedUpUsingAnotherAccount;


    public LoginSignupStepDefinition() {

        currentUser = new User("salam@hawa.com", "222", "store_owner", "Tulkarem");

    }

    @Given("that the user is not logged in")
    public void thatTheUserIsNotLoggedIn() {
        assertFalse(userAuthService.isLoggedIn());
    }

    @When("The information is valid email is {string} and password is {string}")
    public void theInformationIsValidEmailIsAndPasswordIs(String email, String password) {
        assertTrue(userAuthService.login(email, password, DatabaseService.getConnection(true)));

    }

    @Then("User successfully log in")
    public void userSuccessfullyLogIn() {
        assertTrue(userAuthService.isLoggedIn());
    }


    @When("The information is valid email is {string} and password is {string} something went wrong with the sql")
    public void theInformationIsValidEmailIsAndPasswordIsSomethingWentWrongWithTheSql(String email, String password) {
        assertFalse(userAuthService.login(email, password, DatabaseService.getConnection(false)));
    }

    @Then("user failed in log in")
    public void userFailedInLogIn() {
        assertFalse(userAuthService.isLoggedIn());
    }


    @When("the email is invalid email is {string} and password is {string}")
    public void theEmailIsInvalidEmailIsAndPasswordIs(String email, String password) {
        userAuthService.login(email, password, DatabaseService.getConnection(true));

    }


    @When("the password is invalid email is {string} and password is {string}")
    public void thePasswordIsInvalidEmailIsAndPasswordIs(String email, String password) {
        userAuthService.login(email, password, DatabaseService.getConnection(true));
    }

    @When("the information is invalid, email is {string} and password is {string}")
    public void theInformationIsInvalidEmailIsAndPasswordIs(String email, String password) {
        userAuthService.login(email, password, DatabaseService.getConnection(true));
    }

    //----------------------------Sign up------------------------------------------

    @When("the information exists, the email is {string}")
    public void theInformationExistsTheEmailIs(String email) {
        assertFalse(userAuthService.signUp(email, "777", "admin", "Tulkarm", DatabaseService.getConnection(true)));
    }

    @Then("signing up fails")
    public void signingUpFails() {
        assertFalse(userAuthService.isLoggedIn());
    }

    @When("the email {string} format is incorrect")
    public void theEmailFormatIsIncorrect(String email) {
        userAuthService.signUp(email, "777", "admin", "Tulkarm", DatabaseService.getConnection(true));
    }

    @When("the email format is correct and the email {string} does not exist in the database")
    public void theEmailFormatIsCorrectAndTheEmailDoesNotExistInTheDatabase(String email) {
        assertTrue(userAuthService.signUp(email, "777", "admin", "Tulkarm", DatabaseService.getConnection(true)));
    }

    @Then("signing up succeeds")
    public void signingUpSucceeds() {
        assertTrue(userAuthService.isLoggedIn());
        try {
            User.deleteUser(userAuthService.getLoggedInUser().getEmail(), DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @When("the email format is correct and the email {string} does not exist in the database but there is a problem in dataBase connection")
    public void theEmailFormatIsCorrectAndTheEmailDoesNotExistInTheDatabaseButThereIsAProblemInDataBaseConnection(String email) {
        assertFalse(userAuthService.signUp(email, "777", "admin", "Tulkarm", DatabaseService.getConnection(false)));

    }

    @Given("that the user is logged in")
    public void thatTheUserIsLoggedIn() {
        assertTrue(userAuthService.login("momanani2017@gmail.com", "777", DatabaseService.getConnection(true)));
        assertTrue(userAuthService.isLoggedIn());
    }

    @When("The information is  email is {string} and password is {string}")
    public void theInformationIsEmailIsAndPasswordIs(String email, String password) {
        loggedInUsingAnotherAccount = userAuthService.login(email, password, DatabaseService.getConnection(true));

    }

    @Then("user failed in log in using another account")
    public void userFailedInLogInUsingAnotherAccount() {
        assertFalse(loggedInUsingAnotherAccount);
    }

//    @When("the email format is correct and the email {string} does not exist in the database but the user is logged in")
//    public void theEmailFormatIsCorrectAndTheEmailDoesNotExistInTheDatabaseButTheUserIsLoggedIn(String email) {
//        singedUpUsingAnotherAccount = userAuthService.signUp(email, "777", "admin", "Tulkarm", DatabaseService.getConnection(false));
//
//    }
//
//    @Then("signing up with a new account fails")
//    public void signingUpWithANewAccountFails() {
//        assertFalse(singedUpUsingAnotherAccount);
//    }
}
