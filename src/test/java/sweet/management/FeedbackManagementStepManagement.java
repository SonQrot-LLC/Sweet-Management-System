package sweet.management;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import sweet.management.entities.Feedback;
import sweet.management.services.DatabaseService;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FeedbackManagementStepManagement {
    UserAuthService userAuthService;
    Feedback feedback;
    boolean isCreated;
    boolean isDeleted;
    List<Feedback> allFeedbacks;
    public FeedbackManagementStepManagement(){
        userAuthService = new UserAuthService();

    }



    @Given("that a user is logged in with email {string} and password {string}")
    public void thatAUserIsLoggedInWithEmailAndPassword(String email, String password) {
        Assert.assertTrue(userAuthService.login(email,password, DatabaseService.getConnection(true)));
        Assert.assertTrue(userAuthService.isLoggedIn());
        isCreated = false;
        isDeleted = false;
    }
    @When("the user create a new feedback with the correct data")
    public void theUserCreateANewFeedbackWithTheCorrectData() {
        feedback = new Feedback(userAuthService.getLoggedInUser().getEmail(),1,5,"GOOD PRODUCT");
        try {
            isCreated = Feedback.createFeedback(feedback,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Then("the feedback should created successfully")
    public void theFeedbackShouldCreatedSuccessfully() {
        assertTrue(isCreated);

    }

    @When("The user deletes a feedback with id {string}")
    public void theUserDeletesAFeedbackWithId(String feedbackId) {
        try {
            isDeleted = Feedback.deleteFeedback(Integer.parseInt(feedbackId),DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Then("the feedback is deleted successfully")
    public void theFeedbackIsDeletedSuccessfully() {
        assertTrue(isDeleted);
    }

    @When("the user requests feedback by email {string}")
    public void theUserRequestsFeedbackByEmail(String email) {
        try {
            List<Feedback> feedbacks = Feedback.getFeedback(Feedback.QUERY_BY_EMAIL,email,DatabaseService.getConnection(true));
            assertNotNull(feedbacks);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the feedback associated should be retrieved successfully")
    public void theFeedbackAssociatedShouldBeRetrievedSuccessfully() {
        //result test will be found in the When block
    }

    @When("the user requests feedback for store ID {string}")
    public void theUserRequestsFeedbackForStoreID(String storeId) throws SQLException {
            List<Feedback> feedbacks = Feedback.getFeedback(Feedback.QUERY_BY_STORE,storeId,DatabaseService.getConnection(true));
            assertNotNull(feedbacks);

    }

    @When("the user requests feedback for product ID {string}")
    public void theUserRequestsFeedbackForProductID(String productId) throws SQLException {
        List<Feedback> feedbacks = Feedback.getFeedback(Feedback.QUERY_BY_PRODUCT,productId,DatabaseService.getConnection(true));
        assertNotNull(feedbacks);

    }

    @When("the user requests feedback for {string} number {string}")
    public void theUserRequestsFeedbackForNumber(String typeString, String phone) {
        try {
            List<Feedback> feedbacks = Feedback.getFeedback(typeString, phone, DatabaseService.getConnection(true));
            assertNotNull(feedbacks);
            fail("Expected SQLException was not thrown");
        } catch (IllegalArgumentException | SQLException e) {
            assertTrue(e.getMessage().contains("Invalid query type:"));
        }
    }

    @Then("it wont return feedback list and it will fail")
    public void itWontReturnFeedbackListAndItWillFail() {
        //result test will be found in the When block
    }

    @Given("that the admin is logged in with email {string} and password {string}")
    public void thatTheAdminIsLoggedInWithEmailAndPassword(String email, String password) {
        userAuthService = new UserAuthService();
        userAuthService.login(email,password, DatabaseService.getConnection(true));
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(userAuthService.getLoggedInUser().isAdmin());
    }

    @When("The admin tries to get all feedbacks")
    public void theAdminTriesToGetAllFeedbacks() {
        try {
            allFeedbacks = Feedback.getAllFeedbacks(DatabaseService.getConnection(true));
        } catch (SQLException e) {
            fail("Could not get all feedbacks");
        }
    }

    @Then("All feedbacks should be shown")
    public void allFeedbacksShouldBeShown() {
        assertNotNull(allFeedbacks);
    }

    @When("The admin tries to get all feedbacks but there is no connection")
    public void theAdminTriesToGetAllFeedbacksButThereIsNoConnection() {
        try {
            allFeedbacks = Feedback.getAllFeedbacks(DatabaseService.getConnection(false));
        } catch (SQLException e) {
            System.out.println("There is no connection");
        }
    }


    @Then("All feedbacks should not be shown")
    public void allFeedbacksShouldNotBeShown() {
        assertNull(allFeedbacks);

    }
}
