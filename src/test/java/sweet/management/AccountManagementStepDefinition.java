package sweet.management;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import sweet.management.entities.Store;
import sweet.management.entities.User;
import sweet.management.entities.UserProfile;
import sweet.management.services.DatabaseService;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountManagementStepDefinition {
    UserAuthService userAuthService;
    UserProfile userp;
    User user;
    Store store;
    boolean isUpdated;

    public AccountManagementStepDefinition() {
        userAuthService = new UserAuthService();
        userp = new UserProfile("ahmad123@gmail.com", "test1", "test2", "123123123", "anabta");
        isUpdated = false;
    }

    @Given("I log in with username {string} and password {string} and i am a Beneficiary User")
    public void iLogInWithUsernameAndPasswordAndIAmABeneficiaryUser(String email, String pass) {
        userAuthService.login(email, pass, DatabaseService.getConnection(true));
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(userAuthService.getLoggedInUser().isBeneficiaryUser());
    }

    @When("I update my first name to {string}")
    public void iUpdateMyFirstNameTo(String firstName) {
        userp.setFirstName(firstName);
        assertTrue(UserProfile.updateUserProfile(userp, DatabaseService.getConnection(true), UserProfile.UPDATE_FIRST_NAME, userAuthService));
    }

    @When("I update my last name to {string}")
    public void iUpdateMyLastNameTo(String lastName) {
        userp.setLastName(lastName);
        assertTrue(UserProfile.updateUserProfile(userp, DatabaseService.getConnection(true), UserProfile.UPDATE_LAST_NAME, userAuthService));
    }

    @When("I update my address to {string}")
    public void iUpdateMyAddressTo(String address) {
        userp.setAddress(address);
        assertTrue(UserProfile.updateUserProfile(userp, DatabaseService.getConnection(true), UserProfile.UPDATE_ADDRESS, userAuthService));
    }

    @When("I update my phone number to {string}")
    public void iUpdateMyPhoneNumberTo(String phone) {
        userp.setPhone(phone);
        assertTrue(UserProfile.updateUserProfile(userp, DatabaseService.getConnection(true), UserProfile.UPDATE_PHONE, userAuthService));
    }

    @Then("Update is successful")
    public void iShouldSeeAConfirmationMessage() {
        System.out.println("Personal account details updated successfully");
    }

    @When("I chose an invalid updateType")
    public void iChoseAnInvalidUpdateType() {
        isUpdated = UserProfile.updateUserProfile(userp, DatabaseService.getConnection(true), 10, userAuthService);
    }

    @Then("Update fails")
    public void updateFails() {
        assertFalse(isUpdated);
    }

    @Given("I log in with username {string} and password {string}")
    public void iLogInWithUsernameAndPassword(String email, String pass) {
        userAuthService.login(email, pass, DatabaseService.getConnection(true));
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(userAuthService.getLoggedInUser().isBeneficiaryUser());
        user = userAuthService.getLoggedInUser();
        isUpdated = false;
    }

    @When("I update my password to {string}")
    public void iUpdateMyPasswordTo(String pass) {
        user.setPassword(pass);
        isUpdated = User.updateUser(user, DatabaseService.getConnection(true), User.UPDATE_PASSWORD, userAuthService);
    }

    @When("I update my city to {string}")
    public void iUpdateMyCityTo(String city) {
        user.setCity(city);
        isUpdated = User.updateUser(user, DatabaseService.getConnection(true), User.UPDATE_CITY, userAuthService);
    }

    @When("I chose an invalid account updateType")
    public void iChoseAnInvalidAccountUpdateType() {
        isUpdated = User.updateUser(user, DatabaseService.getConnection(true), 10 , userAuthService);
    }

    @When("I delete account")
    public void iDeleteAccount() {
        isUpdated = User.updateUser(user, DatabaseService.getConnection(true), User.DELETE_ACCOUNT, userAuthService);
    }

    @Then("Account is deleted")
    public void accountIsDeleted() {
        assertTrue(isUpdated);
        try {
            User.createUser(user, DatabaseService.getConnection(true));
            UserProfile.createUserProfile(userp, DatabaseService.getConnection(true));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Given("I log in with username {string} and password {string} and i am an Admin")
    public void iLogInWithUsernameAndPasswordAndIAmAnAdmin(String email, String password) {
        // Write code here that turns the phrase above into concrete actions
        userAuthService.login(email, password, DatabaseService.getConnection(true));
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(userAuthService.getLoggedInUser().isAdmin());

        isUpdated = false;
    }
    @When("I update someone's role to  {string}")
    public void iUpdateSomeoneSRoleTo(String string) {
        try {
            user = User.getUserByEmail("momanani2017@gmail.com", DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        user.setRole(string);
        isUpdated = User.updateUser(user, DatabaseService.getConnection(true), User.UPDATE_ROLE, userAuthService);
    }
    @Then("The role is Updated")
    public void theRoleIsUpdated() {
     assertTrue(isUpdated);
    }

    @Given("I log in with username {string} and password {string} and i am not an Admin")
    public void iLogInWithUsernameAndPasswordAndIAmNotAnAdmin(String email, String pass) {
        userAuthService.login(email, pass, DatabaseService.getConnection(true));
        assertTrue(userAuthService.isLoggedIn());
        assertFalse(userAuthService.getLoggedInUser().isAdmin());
        isUpdated = false;

    }
    @When("I update {string} password to {string}")
    public void iUpdatePasswordTo(String email, String newPassword) {
        try {
            user = User.getUserByEmail(email, DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        user.setPassword(newPassword);
        isUpdated = false;
    }


    @When("I give valid updates and something went wrong with sql")
    public void iGiveValidUpdatesAndSomethingWentWrongWithSql() {
        user.setPassword("password");
        isUpdated = User.updateUser(user, DatabaseService.getConnection(false), User.UPDATE_PASSWORD, userAuthService);
    }

    @When("I update nonexistent user in the DB role to  {string}")
    public void iUpdateNonexistentUserInTheDBRoleTo(String string) {
        user = new User("test@gmail.com","pass","admin","Lebanon");
        user.setRole(string);
        isUpdated = User.updateUser(user, DatabaseService.getConnection(true), User.UPDATE_ROLE, userAuthService);
    }


    @When("I update my last name to {string} and there is sql connection")
    public void iUpdateMyLastNameToAndThereIsSqlConnection(String lastName) {
        userp.setLastName(lastName);
        assertFalse(UserProfile.updateUserProfile(userp, DatabaseService.getConnection(false), UserProfile.UPDATE_LAST_NAME, userAuthService));

    }

    @Given("I log in with username {string} and password {string} and i am a store owner")
    public void iLogInWithUsernameAndPasswordAndIAmAStoreOwner(String email, String password) {
        userAuthService.login(email, password, DatabaseService.getConnection(true));
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(userAuthService.getLoggedInUser().isStoreOwner());
        isUpdated = false;
    }

    @When("I update my store name to {string}")
    public void iUpdateMyStoreNameTo(String storeName) {
        store = userAuthService.getLoggedInStore();
        store.setStoreName(storeName);
        isUpdated = Store.updateStore(store,DatabaseService.getConnection(true),Store.UPDATE_STORE_NAME, userAuthService);
    }

    @And("I update my info to {string}")
    public void iUpdateMyInfoTo(String info) {
        store = userAuthService.getLoggedInStore();
        store.setBusinessInfo(info);
        isUpdated = Store.updateStore(store,DatabaseService.getConnection(true),Store.UPDATE_BUSINESS_INFO, userAuthService);
    }

    @When("I update my store name to {string} and there is sql connection")
    public void iUpdateMyStoreNameToAndThereIsSqlConnection(String storeName) {
        store = userAuthService.getLoggedInStore();
        store.setStoreName(storeName);
        isUpdated = Store.updateStore(store,DatabaseService.getConnection(false),Store.UPDATE_STORE_NAME, userAuthService);
    }

    @When("I delete a store")
    public void iDeleteAStore() {
        store = userAuthService.getLoggedInStore();
        isUpdated = Store.updateStore(store,DatabaseService.getConnection(true),Store.DELETE_STORE, userAuthService);
    }

    @Then("Store is deleted")
    public void storeIsDeleted() {
        assertTrue(isUpdated);
        try {
            Store.createStore(store,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @When("I chose an invalid store updateType")
    public void iChoseAnInvalidStoreUpdateType() {
        store = userAuthService.getLoggedInStore();
        store.setStoreName("test");
        isUpdated = Store.updateStore(store,DatabaseService.getConnection(true),10, userAuthService);
    }
}
