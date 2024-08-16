package sweet.management;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.management.entities.Message;
import sweet.management.entities.Notification;
import sweet.management.entities.User;
import sweet.management.services.DatabaseService;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class MessagingStepDefinition {
    UserAuthService userAuthService;
    User loggedInUser;
    Message messageToBeDisplayed;
    Notification notificationToBeDisplayed;
    List<Notification> userNotifications;




    public MessagingStepDefinition() {
        userAuthService = new UserAuthService();
    }

    @Given("That Customer with email {string} is logged in")
    public void thatCustomerWithEmailIsLoggedIn(String email) throws SQLException {
        userAuthService.login(email,"321", DatabaseService.getConnection(true));
        loggedInUser = userAuthService.getLoggedInUser();
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(loggedInUser.isBeneficiaryUser());
    }

    @When("The Customer sends a message {string} to owner with email {string}")
    public void theCustomerSendsAMessageToOwnerWithEmail(String message, String receiver) {
        try {
            Message.insertMessage(Objects.requireNonNull(DatabaseService.getConnection(true)),loggedInUser.getEmail(),receiver,message);
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
            Message.insertMessage(Objects.requireNonNull(DatabaseService.getConnection(true)),loggedInUser.getEmail(),receiver,message);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("it's received by the supplier")
    public void itSReceivedByTheSupplier() {
        //result test will be found in the When block

    }


    @Given("The owner with email {string}  is logged in")
    public void theOwnerWithEmailIsLoggedIn(String email) throws SQLException{
        userAuthService.login(email,"123", DatabaseService.getConnection(true));
        loggedInUser = userAuthService.getLoggedInUser();
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(loggedInUser.isStoreOwner());
    }

    @When("the owner reply with {string} to message received from user with email {string}")
    public void theOwnerReplyWithToMessageReceivedFromUserWithEmail(String message, String receiver) {
        try {
            Message.insertMessage(Objects.requireNonNull(DatabaseService.getConnection(true)),loggedInUser.getEmail(),receiver,message);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the reply is sent to user")
    public void theReplyIsSentToUser() {
        //result test will be found in the When block

    }


    @Given("The supplier with email {string}  is logged in")
    public void theSupplierWithEmailIsLoggedIn(String email) throws SQLException{
        userAuthService.login(email,"123", DatabaseService.getConnection(true));
        loggedInUser = userAuthService.getLoggedInUser();
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(loggedInUser.isRawMaterialSupplier());
    }

    @When("the supplier reply with {string} to message received from user with email {string}")
    public void theSupplierReplyWithToMessageReceivedFromUserWithEmail(String message, String receiver) {
        try {
            Message.insertMessage(Objects.requireNonNull(DatabaseService.getConnection(true)),loggedInUser.getEmail(),receiver,message);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @When("the user reply with {string} to message received from owner with email {string}")
    public void theUserReplyWithToMessageReceivedFromOwnerWithEmail(String message, String receiver) {
        try {
            Message.insertMessage(Objects.requireNonNull(DatabaseService.getConnection(true)),loggedInUser.getEmail(),receiver,message);
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

    @Given("That the user with email {string} is logged in")
    public void thatUserWithEmailIsLoggedIn(String email) throws SQLException {
        userAuthService.login(email,"321", DatabaseService.getConnection(true));
        loggedInUser = userAuthService.getLoggedInUser();
        assertTrue(userAuthService.isLoggedIn());
    }

    @When("The user makes a special request to the email {string} and  the message {string}")
    public void theUserMakesASpecialRequestToTheEmailAndTheMessage(String receiverEmail, String message) {
        try {
            Notification.insertNotification(Objects.requireNonNull(DatabaseService.getConnection(true)), receiverEmail, userAuthService.getLoggedInUser().getEmail(), message);
        } catch (SQLException e) {
            System.out.println("Exception happened while sending a notification.");
        }
    }



    @Then("A notification is made")
    public void aNotificationIsMade() {
        try {
            notificationToBeDisplayed = Notification.getNotificationsByUserEmail(
                    DatabaseService.getConnection(true), "order.store@gmail.com").get(0);

            assertNotNull(notificationToBeDisplayed);
            assertEquals("order.store@gmail.com", notificationToBeDisplayed.getUserEmail());
            assertEquals("I want to request a juicer cake", notificationToBeDisplayed.getMessage());
        } catch (SQLException e) {
            fail("Could not verify the notification.");
        }
    }

    @Given("The user with email {string} is logged in")
    public void theUserWithEmailIsLoggedIn(String email) throws SQLException {
        userAuthService.login(email, "567", DatabaseService.getConnection(true));
        loggedInUser = userAuthService.getLoggedInUser();
        assertTrue(userAuthService.isLoggedIn());
    }

    @When("The user checks all their notifications")
    public void theUserChecksAllTheirNotifications() {
        try {
            userNotifications = Notification.getNotificationsByUserEmail(DatabaseService.getConnection(true), loggedInUser.getEmail());
        } catch (SQLException e) {
            System.out.println("Exception caught while checking notifications");
        }
    }

    @Then("All notifications for {string} are displayed")
    public void allNotificationsForAreDisplayed(String email) {
        assertFalse(userNotifications.isEmpty());
        for (Notification notification : userNotifications) {
            assert(notification.getUserEmail().equals(email));
            System.out.println("Notification ID: " + notification.getNotificationId());
            System.out.println("Message: " + notification.getMessage());
            System.out.println("Read: " + notification.isRead());
            System.out.println("Created At: " + notification.getCreatedAt());
        }
    }
    @When("The user checks his notifications")
    public void theUserChecksHisNotifications() {
        try {
            userNotifications = Notification.getNotificationsByUserEmail(DatabaseService.getConnection(true), loggedInUser.getEmail());
            assertNotNull(userNotifications);
            assertFalse(userNotifications.isEmpty());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @When("The user marks the notification with ID {string} as read")
    public void theUserMarksTheNotificationWithIDAsRead(String notificationId) {
        try {
            Notification.markNotificationAsRead(Objects.requireNonNull(DatabaseService.getConnection(true)), Integer.parseInt(notificationId));
            notificationToBeDisplayed = Notification.getNotificationById(DatabaseService.getConnection(true), Integer.parseInt(notificationId));
            assert notificationToBeDisplayed != null;
            assertTrue(notificationToBeDisplayed.isRead());
        } catch (SQLException e) {
            fail("Could not mark the notification with ID " + notificationId + " as read");
        }
    }

    @Then("The notification with ID {string} should be marked as read")
    public void theNotificationWithIDShouldBeMarkedAsRead(String notificationId) {
        assertNotNull(notificationToBeDisplayed);
        assertTrue(notificationToBeDisplayed.isRead());
        System.out.println(notificationId);
    }

    @When("The user deletes the notification with ID {string}")
    public void theUserDeletesTheNotificationWithID(String notificationId) {
        try {
            Notification.deleteNotificationById(Objects.requireNonNull(DatabaseService.getConnection(true)), Integer.parseInt(notificationId));
            notificationToBeDisplayed = Notification.getNotificationById(DatabaseService.getConnection(true), Integer.parseInt(notificationId));
        } catch (SQLException e) {
            fail("Could not delete the notification with ID " + notificationId);
        }
    }

    @Then("The notification with ID {string} should no longer exist")
    public void theNotificationWithIDShouldNoLongerExist(String notificationId) {
        System.out.println(notificationId);
        assertNull(notificationToBeDisplayed);
    }

}
