package sweet.management;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.management.entities.Store;
import sweet.management.entities.User;
import sweet.management.entities.UserProfile;
import sweet.management.services.DatabaseService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountManagementStepDefinition {
    UserAuthService userAuthService;
    UserProfile userp;
    User user;
    Store store;
    int storeIdToBeDeleted;
    int storeIdToNotBeDeleted;
    Store storeObjectFields;
    List<Store> storesToBeReturned;
    List<User> usersToBeReturned;
    boolean isUpdated;

    public AccountManagementStepDefinition() {
        userAuthService = new UserAuthService();
        userp = new UserProfile("ahmad123@gmail.com", "test1", "test2", "123123123", "anabta");
        isUpdated = false;
    }

    @Given("I log in with username {string} and password {string} and i am a Beneficiary User")
    public void iLogInWithUsernameAndPasswordAndIAmABeneficiaryUser(String email, String pass) throws SQLException {
        Connection conn = DatabaseService.getConnection(true);
        userAuthService.login(email, pass,conn );
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(userAuthService.getLoggedInUser().isBeneficiaryUser());
    }

    @When("I update my first name to {string}")
    public void iUpdateMyFirstNameTo(String firstName) throws SQLException {
        userp.setFirstName(firstName);
        assertTrue(UserProfile.updateUserProfile(userp, DatabaseService.getConnection(true), UserProfile.UPDATE_FIRST_NAME, userAuthService));
    }

    @When("I update my last name to {string}")
    public void iUpdateMyLastNameTo(String lastName) throws SQLException{
        userp.setLastName(lastName);
        assertTrue(UserProfile.updateUserProfile(userp, DatabaseService.getConnection(true), UserProfile.UPDATE_LAST_NAME, userAuthService));
    }

    @When("I update my address to {string}")
    public void iUpdateMyAddressTo(String address) throws SQLException{
        userp.setAddress(address);
        assertTrue(UserProfile.updateUserProfile(userp, DatabaseService.getConnection(true), UserProfile.UPDATE_ADDRESS, userAuthService));
    }

    @When("I update my phone number to {string}")
    public void iUpdateMyPhoneNumberTo(String phone) throws SQLException{
        userp.setPhone(phone);
        assertTrue(UserProfile.updateUserProfile(userp, DatabaseService.getConnection(true), UserProfile.UPDATE_PHONE, userAuthService));
    }

    @Then("Update is successful")
    public void iShouldSeeAConfirmationMessage() {
        System.out.println("Personal account details updated successfully");
    }

    @When("I chose an invalid updateType")
    public void iChoseAnInvalidUpdateType() throws SQLException{
        isUpdated = UserProfile.updateUserProfile(userp, DatabaseService.getConnection(true), 10, userAuthService);
    }

    @Then("Update fails")
    public void updateFails() {
        assertFalse(isUpdated);
    }

    @Given("I log in with username {string} and password {string}")
    public void iLogInWithUsernameAndPassword(String email, String pass)throws SQLException {
        userAuthService.login(email, pass, DatabaseService.getConnection(true));
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(userAuthService.getLoggedInUser().isBeneficiaryUser());
        user = userAuthService.getLoggedInUser();
        isUpdated = false;
    }

    @When("I update my password to {string}")
    public void iUpdateMyPasswordTo(String pass)throws SQLException {
        user.setPassword(pass);
        isUpdated = User.updateUser(user, DatabaseService.getConnection(true), User.UPDATE_PASSWORD, userAuthService);
    }

    @When("I update my city to {string}")
    public void iUpdateMyCityTo(String city) throws SQLException{
        user.setCity(city);
        isUpdated = User.updateUser(user, DatabaseService.getConnection(true), User.UPDATE_CITY, userAuthService);
    }

    @When("I chose an invalid account updateType")
    public void iChoseAnInvalidAccountUpdateType() throws SQLException {
        isUpdated = User.updateUser(user, DatabaseService.getConnection(true), 10 , userAuthService);
    }

    @When("I delete account")
    public void iDeleteAccount() throws SQLException{
        isUpdated = User.updateUser(user, DatabaseService.getConnection(true), User.DELETE_ACCOUNT, userAuthService);
    }

    @Then("Account is deleted")
    public void accountIsDeleted() {
        assertTrue(isUpdated);
        try {
            User.createUser(user, DatabaseService.getConnection(true));
            UserProfile.createUserProfile(userp, DatabaseService.getConnection(true));
        } catch (SQLException e) {
            fail("Account is not deleted");
        }
    }

    @Given("I log in with username {string} and password {string} and i am an Admin")
    public void iLogInWithUsernameAndPasswordAndIAmAnAdmin(String email, String password) throws SQLException{
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
            assert user != null;
            user.setRole(string);
            isUpdated = User.updateUser(user, DatabaseService.getConnection(true), User.UPDATE_ROLE, userAuthService);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Then("The role is Updated")
    public void theRoleIsUpdated() {
     assertTrue(isUpdated);
    }

    @Given("I log in with username {string} and password {string} and i am not an Admin")
    public void iLogInWithUsernameAndPasswordAndIAmNotAnAdmin(String email, String pass)  throws SQLException{
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
        assert user != null;
        user.setPassword(newPassword);
        isUpdated = false;
    }


    @When("I give valid updates and something went wrong with sql")
    public void iGiveValidUpdatesAndSomethingWentWrongWithSql() throws SQLException {
        user.setPassword("password");
        isUpdated = User.updateUser(user, DatabaseService.getConnection(false), User.UPDATE_PASSWORD, userAuthService);
    }

    @When("I update nonexistent user in the DB role to  {string}")
    public void iUpdateNonexistentUserInTheDBRoleTo(String string) throws SQLException {
        user = new User("test@gmail.com","pass","admin","Lebanon");
        user.setRole(string);
        isUpdated = User.updateUser(user, DatabaseService.getConnection(true), User.UPDATE_ROLE, userAuthService);
    }


    @When("I update my last name to {string} and there is sql connection")
    public void iUpdateMyLastNameToAndThereIsSqlConnection(String lastName) throws SQLException {
        userp.setLastName(lastName);
        assertFalse(UserProfile.updateUserProfile(userp, DatabaseService.getConnection(false), UserProfile.UPDATE_LAST_NAME, userAuthService));

    }

    @Given("I log in with username {string} and password {string} and i am a store owner")
    public void iLogInWithUsernameAndPasswordAndIAmAStoreOwner(String email, String password) throws SQLException {
        userAuthService.login(email, password, DatabaseService.getConnection(true));
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(userAuthService.getLoggedInUser().isStoreOwner());
        isUpdated = false;
    }

    @When("I update my store name to {string}")
    public void iUpdateMyStoreNameTo(String storeName) throws SQLException {
        store = userAuthService.getLoggedInStore();
        store.setStoreName(storeName);
        isUpdated = Store.updateStore(store,DatabaseService.getConnection(true),Store.UPDATE_STORE_NAME, userAuthService);
    }

    @And("I update my info to {string}")
    public void iUpdateMyInfoTo(String info) throws SQLException {
        store = userAuthService.getLoggedInStore();
        store.setBusinessInfo(info);
        isUpdated = Store.updateStore(store,DatabaseService.getConnection(true),Store.UPDATE_BUSINESS_INFO, userAuthService);
    }

    @When("I update my store name to {string} and there is sql connection")
    public void iUpdateMyStoreNameToAndThereIsSqlConnection(String storeName) throws SQLException {
        store = userAuthService.getLoggedInStore();
        store.setStoreName(storeName);
        isUpdated = Store.updateStore(store,DatabaseService.getConnection(false),Store.UPDATE_STORE_NAME, userAuthService);
    }

    @When("I delete a store")
    public void iDeleteAStore() throws SQLException {
        store = userAuthService.getLoggedInStore();
        storeIdToBeDeleted = store.getStoreId();
        isUpdated = Store.updateStore(store,DatabaseService.getConnection(true),Store.DELETE_STORE, userAuthService);
    }

    @Then("Store is deleted")
    public void storeIsDeleted() {
        assertTrue(isUpdated);
        try {

            assertNull(Store.getStoreById(storeIdToBeDeleted,DatabaseService.getConnection(true)));
            Store addNewStore = new Store("mahmood@outlook.com","to be deleted", "testing deleting function");
            Store.createStore(addNewStore,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            fail("Store Is Deleted Exception");
        }

    }

    @When("I delete a store and its not connected")
    public void iDeleteAStoreAndItsNotConnected() {
        store = userAuthService.getLoggedInStore();
        storeIdToNotBeDeleted = store.getStoreId();
        isUpdated = Store.updateStore(store,null,Store.DELETE_STORE, userAuthService);
    }
    @Then("Store is not deleted")
    public void storeIsNotDeleted() {
        assertFalse(isUpdated);
        try
        {
            storeObjectFields = Store.getStoreById(storeIdToNotBeDeleted,DatabaseService.getConnection(true));


            assertNull(Store.getStoreById(storeIdToNotBeDeleted,null));
        }
        catch (Exception e)
        {
            System.err.println("Expected SQLException caught while verifying store deletion: " + e.getMessage());
            assertTrue(e.getMessage().contains("No connection"));
        }
    }

    @When("I chose an invalid store updateType")
    public void iChoseAnInvalidStoreUpdateType() throws SQLException {
        store = userAuthService.getLoggedInStore();
        store.setStoreName("test");
        isUpdated = Store.updateStore(store,DatabaseService.getConnection(true),10, userAuthService);
    }

    @Given("I log in with username {string} and password {string} and i am a user")
    public void iLogInWithUsernameAndPasswordAndIAmAUser(String email, String password) throws SQLException{
        userAuthService.login(email, password, DatabaseService.getConnection(true));
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(userAuthService.getLoggedInUser().isBeneficiaryUser());
        isUpdated = false;

    }

    @When("I try to retrieve all stores")
    public void iTryToRetrieveAllStores() {
        try {

            storesToBeReturned = Store.getAllStores(DatabaseService.getConnection(true));
        }
        catch (Exception e)
        {
            fail("Stores are Not Retrieved Exception");
            storesToBeReturned = Collections.emptyList();

        }

    }

    @Then("All stores should be returned")
    public void allStoresShouldBeReturned()
    {
        isUpdated = storesToBeReturned != null && !storesToBeReturned.isEmpty();
        assertTrue(isUpdated);
    }

    @When("I try to retrieve all stores and the connection is null")
    public void iTryToRetrieveAllStoresAndTheConnectionIsNull() {
        try {

            storesToBeReturned = Store.getAllStores(null);
        }
        catch (Exception e)
        {
            System.err.println("Expected SQLException caught: " + e.getMessage());
            assertTrue(e.getMessage().contains("No connection"));
            storesToBeReturned = Collections.emptyList();
        }

    }

    @Then("All stores should not be returned")
    public void allStoresShouldNotBeReturned() {
        isUpdated = storesToBeReturned != null && !storesToBeReturned.isEmpty();
        assertFalse(isUpdated);
    }
    @When("The admin tries to get users with flag {int}")
    public void theAdminTriesToGetUsersWithFlag(int flag) {
        try {
            usersToBeReturned = User.getUsersByFlag(flag, DatabaseService.getConnection(true));
        } catch (SQLException e) {
            fail("Exception occurred while retrieving users: " + e.getMessage());
            usersToBeReturned = Collections.emptyList();
        }
    }

    @Then("Users with roles 'store_owner', 'raw_material_supplier', and 'beneficiary_user' should be shown")
    public void usersWithRolesShouldBeShown() {
        assertNotNull(usersToBeReturned);
        assertFalse(usersToBeReturned.isEmpty());
    }

    @When("The admin tries to get users with flag {int} but there is no connection")
    public void theAdminTriesToGetUsersWithFlagButNoConnection(int flag) {
        try {
            usersToBeReturned = User.getUsersByFlag(flag, null);
        } catch (SQLException e) {
            System.err.println("Expected SQLException due to null connection: " + e.getMessage());
            usersToBeReturned = Collections.emptyList();
        } catch (IllegalArgumentException e) {
            System.err.println("Expected IllegalArgumentException: " + e.getMessage());
            usersToBeReturned = Collections.emptyList();
        }
    }

    @Then("No users should be shown")
    public void noUsersShouldBeShown() {
        assertTrue(usersToBeReturned.isEmpty());
    }

}
