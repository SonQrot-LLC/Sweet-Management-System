package sweet.management;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.management.entities.User;

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
        login.login(email, password);

    }

    @Then("User successfully log in")
    public void userSuccessfullyLogIn() {
        assertTrue(login.isLoggedIn());
    }

    @When("the email is invalid email is {string} and password is {string}")
    public void theEmailIsInvalidEmailIsAndPasswordIs(String email, String password) {
        login.login(email, password);

    }

    @Then("user failed in log in")
    public void userFailedInLogIn() {
        assertFalse(login.isLoggedIn());
    }

    @When("the password is invalid email is {string} and password is {string}")
    public void thePasswordIsInvalidEmailIsAndPasswordIs(String email, String password) {
        login.login(email, password);
    }

    @When("the information is invalid, email is {string} and password is {string}")
    public void theInformationIsInvalidEmailIsAndPasswordIs(String email, String password) {
        login.login(email, password);
    }

    //----------------------------Sign up------------------------------------------

    @When("the information exists, the email is {string}")
    public void theInformationExistsTheEmailIs(String email) {
        login.signUp(email,"","admin","Tulkarm");
    }

    @Then("signing up fails")
    public void signingUpFails() {
        assertFalse(login.isLoggedIn());
    }

    @When("the email {string} format is incorrect")
    public void theEmailFormatIsIncorrect(String email) {
        login.signUp(email,"","admin","Tulkarm");
    }

    @When("the email format is incorrect")
    public void theEmailFormatIsIncorrect() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("the information exists, the email is not {string}")
    public void theInformationExistsTheEmailIsNot(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("signing up succeeds")
    public void signingUpSucceeds() {
        // Write code here that turns the phrase above into concrete actions
    }


}
