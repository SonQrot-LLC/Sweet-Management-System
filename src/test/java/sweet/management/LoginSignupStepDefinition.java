package sweet.management;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSignupStepDefinition {
    @Given("that the user is not logged in")
    public void thatTheUserIsNotLoggedIn() {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("The information is valid email is {string} and password is {string}")
    public void theInformationIsValidEmailIsAndPasswordIs(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("User successfully log in")
    public void userSuccessfullyLogIn() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("the email is invalid email is {string} and password is {string}")
    public void theEmailIsInvalidEmailIsAndPasswordIs(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("user failed in log in")
    public void userFailedInLogIn() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("the password is invalid email is {string} and password is {string}")
    public void thePasswordIsInvalidEmailIsAndPasswordIs(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("the information is invalid, email is {string} and password is {string}")
    public void theInformationIsInvalidEmailIsAndPasswordIs(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
    }

    //----------------------------Sign up------------------------------------------

    @When("the information exists, the email is {string}")
    public void theInformationExistsTheEmailIs(String string) {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("signing up fails")
    public void signingUpFails() {
        // Write code here that turns the phrase above into concrete actions

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
