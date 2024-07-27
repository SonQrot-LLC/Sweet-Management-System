package sweet.management;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.management.entities.Product;
import sweet.management.entities.User;
import sweet.management.services.DatabaseService;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class ProductManagementStepDefinition {
    UserAuthService userAuthService;
    User loggedInUser;
    Boolean isUpdated;

    public ProductManagementStepDefinition() {
        userAuthService = new UserAuthService();
        userAuthService.login("mahmood@outlook.com","777", DatabaseService.getConnection(true));
        loggedInUser = userAuthService.getLoggedInUser();

    }
    @Given("that the user is logged in and user is store owner or raw material supplier")
    public void thatTheUserIsLoggedInAndUserIsStoreOwnerOrRawMaterialSupplier() {
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(loggedInUser.isRawMaterialSupplier() || loggedInUser.isStoreOwner());
        isUpdated = false;

    }

    @When("The user add a new product with full info")
    public void theUserAddANewProductWithFullInfo() {
        Product product = new Product("BIG CAKE"," A big cake that can feed up to 10 persons",55.00,7,userAuthService.getLoggedInStore().getStoreId());
        try {
            isUpdated = Product.createProduct(product,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("The product should be added successfully")
    public void theProductShouldBeAddedSuccessfully() {
    }
}
