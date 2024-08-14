package sweet.management;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.management.entities.Message;
import sweet.management.entities.User;
import sweet.management.services.DatabaseService;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class MessagingStepDefinition {
    UserAuthService userAuthService;
    User loggedInUser;
    Message messageToBeDisplayed;


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
        //result test will be found in the When block

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
        //result test will be found in the When block

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
        //result test will be found in the When block

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
        //result test will be found in the When block


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
        //result test will be found in the When block

    }

    @When("The user tries to get a message by id {string}")
    public void theUserTriesToGetAMessageById(String id) {
        try {
            messageToBeDisplayed = Message.getMessageById(DatabaseService.getConnection(true),Integer.parseInt(id));
            assertNotNull(messageToBeDisplayed);
        } catch (SQLException e) {
            fail("Could not get a message with the id " + id);
        }

    }

    @Then("The message is shown")
    public void theMessageIsShown() {
        int expectedMessageId = 1;
        String expectedEmail = loggedInUser.getEmail();
        String expectedReceiver = "owner@gmail.com";
        String expectedContent = "How do you make cake";

        assertEquals(expectedEmail,messageToBeDisplayed.getSenderEmail());
        assertEquals(expectedReceiver,messageToBeDisplayed.getReceiverEmail());
        assertEquals(expectedContent,messageToBeDisplayed.getContent());
        assertEquals(expectedMessageId,messageToBeDisplayed.getMessageId());

        System.out.println("\nThe sender email is "+ messageToBeDisplayed.getSenderEmail());
        System.out.println("\nThe receiver email is "+ messageToBeDisplayed.getReceiverEmail());
        System.out.println("\nThe content is "+ messageToBeDisplayed.getContent());
        System.out.println("\n The message was sent at "+ messageToBeDisplayed.getCreatedAt());


    }

    @When("The user tries to get a message by invalid id {string}")
    public void theUserTriesToGetAMessageByInvalidId(String id) {
        try {
            messageToBeDisplayed = Message.getMessageById(DatabaseService.getConnection(true),Integer.parseInt(id));
        } catch (SQLException e) {
            fail("Could not get a message with the id " + id);
        }
    }

    @Then("The message wont be shown")
    public void theMessageWontBeShown() {
        assertNull("The message does not exist",messageToBeDisplayed);
    }


}
