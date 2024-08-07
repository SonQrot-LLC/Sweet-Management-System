package sweet.management;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.management.entities.Message;
import sweet.management.entities.Product;
import sweet.management.entities.User;
import sweet.management.services.DatabaseService;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;

public class MessagingStepDefinition {
    UserAuthService userAuthService;
    User loggedInUser;

    public MessagingStepDefinition() {
        userAuthService = new UserAuthService();
    }

    @Given("That Customer with email {string} is logged in")
    public void thatCustomerWithEmailIsLoggedIn(String email) {
        userAuthService.login(email,"321", DatabaseService.getConnection(true));
        loggedInUser = userAuthService.getLoggedInUser();
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(loggedInUser.isBeneficiaryUser());
    }

    @When("The Customer sends a message {string} to owner with email {string}")
    public void theCustomerSendsAMessageToOwnerWithEmail(String message, String receiver) {
        try {
            Message.insertMessage(DatabaseService.getConnection(true),loggedInUser.getEmail(),receiver,message);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("it's received by the owner")
    public void itSReceivedByTheOwner() {

    }

    @When("The Customer sends a message {string} to supplier with email {string}")
    public void theCustomerSendsAMessageToSupplierWithEmail(String message, String receiver) {
        try {
            Message.insertMessage(DatabaseService.getConnection(true),loggedInUser.getEmail(),receiver,message);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("it's received by the supplier")
    public void itSReceivedByTheSupplier() {

    }


    @Given("The owner with email {string}  is logged in")
    public void theOwnerWithEmailIsLoggedIn(String email) {
        userAuthService.login(email,"123", DatabaseService.getConnection(true));
        loggedInUser = userAuthService.getLoggedInUser();
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(loggedInUser.isStoreOwner());
    }

    @When("the owner reply with {string} to message received from user with email {string}")
    public void theOwnerReplyWithToMessageReceivedFromUserWithEmail(String message, String receiver) {
        try {
            Message.insertMessage(DatabaseService.getConnection(true),loggedInUser.getEmail(),receiver,message);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the reply is sent to user")
    public void theReplyIsSentToUser() {

    }


    @Given("The supplier with email {string}  is logged in")
    public void theSupplierWithEmailIsLoggedIn(String email) {
        userAuthService.login(email,"123", DatabaseService.getConnection(true));
        loggedInUser = userAuthService.getLoggedInUser();
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(loggedInUser.isRawMaterialSupplier());
    }

    @When("the supplier reply with {string} to message received from user with email {string}")
    public void theSupplierReplyWithToMessageReceivedFromUserWithEmail(String message, String receiver) {
        try {
            Message.insertMessage(DatabaseService.getConnection(true),loggedInUser.getEmail(),receiver,message);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @When("the user reply with {string} to message received from owner with email {string}")
    public void theUserReplyWithToMessageReceivedFromOwnerWithEmail(String message, String receiver) {
        try {
            Message.insertMessage(DatabaseService.getConnection(true),loggedInUser.getEmail(),receiver,message);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the reply is sent to owner")
    public void theReplyIsSentToOwner() {

    }


    @When("The user chooses the show all sent messages option")
    public void theUserChoosesTheShowAllSentMessagesOption() {
        try {
            Message.getMessagesBySenderEmail(DatabaseService.getConnection(true),loggedInUser.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @When("The user chooses the show all received messages option")
    public void theUserChoosesTheShowAllReceivedMessagesOption() {
        try {
            Message.getMessagesByReceiverEmail(DatabaseService.getConnection(true),loggedInUser.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @When("The user chooses user with email {string} to see message history")
    public void theUserChoosesUserWithEmailToSeeMessageHistory(String email) {
        try {
            Message.getMessagesBetweenUsers(DatabaseService.getConnection(true),loggedInUser.getEmail(),email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("The messages are shown")
    public void theMessagesAreShown() {

    }

}
