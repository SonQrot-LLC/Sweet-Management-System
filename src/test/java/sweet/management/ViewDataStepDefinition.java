package sweet.management;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.management.entities.ViewService;
import sweet.management.services.DatabaseService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ViewDataStepDefinition {

    private Connection conn;
    private ViewService viewService;
    private List<ViewService.Product> bestSellingProducts;
    private List<ViewService.StoreProfit> storeProfits;
    private List<ViewService.UserByCity> usersByCity;
    private boolean retrievalFailed;

    @Given("the database connection is established")
    public void theDatabaseConnectionIsEstablished() {
        conn = DatabaseService.getConnection(true);
        viewService = new ViewService();
    }

    @Given("the database connection is established with a bad connection")
    public void theDatabaseConnectionIsEstablishedWithABadConnection() {
        conn = DatabaseService.getConnection(false);  // Simulate a bad connection
        viewService = new ViewService();
    }

    @When("I retrieve the best-selling products")
    public void iRetrieveTheBestSellingProducts() {
        try {
            bestSellingProducts = viewService.getBestSellingProducts(conn);
            retrievalFailed = false;
        } catch (SQLException e) {
            retrievalFailed = true;
        }
    }

    @When("I retrieve the store profits")
    public void iRetrieveTheStoreProfits() {
        try {
            storeProfits = viewService.getStoreProfits(conn);
            retrievalFailed = false;
        } catch (SQLException e) {
            retrievalFailed = true;
        }
    }

    @When("I retrieve users by city")
    public void iRetrieveUsersByCity() {
        try {
            usersByCity = viewService.getUsersByCity(conn);
            retrievalFailed = false;
        } catch (SQLException e) {
            retrievalFailed = true;
        }
    }

    @Then("the best-selling products should be displayed successfully")
    public void theBestSellingProductsShouldBeDisplayedSuccessfully() {
        assertNotNull(bestSellingProducts);
        assertFalse(bestSellingProducts.isEmpty());

        for (ViewService.Product product : bestSellingProducts) {
            assertNotNull(product.getProductName());
            assertTrue(product.getProductId() > 0);
            assertTrue(product.getStoreId() > 0);
            assertTrue(product.getTotalQuantitySold() >= 0);
        }
    }

    @Then("the store profits should be displayed successfully")
    public void theStoreProfitsShouldBeDisplayedSuccessfully() {
        assertNotNull(storeProfits);
        assertFalse(storeProfits.isEmpty());

        for (ViewService.StoreProfit storeProfit : storeProfits) {
            assertNotNull(storeProfit.getStoreName());
            assertTrue(storeProfit.getStoreId() > 0);
            assertTrue(storeProfit.getTotalProfit() >= 0);
        }
    }

    @Then("the users by city should be displayed successfully")
    public void theUsersByCityShouldBeDisplayedSuccessfully() {
        assertNotNull(usersByCity);
        assertFalse(usersByCity.isEmpty());

        for (ViewService.UserByCity userByCity : usersByCity) {
            assertNotNull(userByCity.getCityName());
            assertTrue(userByCity.getUserCount() >= 0);
        }
    }

    @Then("the retrieval should fail")
    public void theRetrievalShouldFail() {
        assertTrue(retrievalFailed);
    }
}
