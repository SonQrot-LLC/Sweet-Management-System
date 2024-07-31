package sweet.management;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.management.entities.Product;
import sweet.management.entities.User;
import sweet.management.services.DatabaseService;

import javax.xml.crypto.Data;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ProductManagementStepDefinition {
    UserAuthService userAuthService;
    User loggedInUser;
    Boolean isUpdated;
    Product productTest;
    Product productDeleteTest;

    public ProductManagementStepDefinition() {
        userAuthService = new UserAuthService();
        userAuthService.login("owner@gmail.com","123", DatabaseService.getConnection(true));
        loggedInUser = userAuthService.getLoggedInUser();
        productDeleteTest = new Product("delete_test","delete_test","1","1",82,"2024-11-11");
        try {
            Product.resetIdCounter(DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Given("that the user is logged in and user is store owner or raw material supplier")
    public void thatTheUserIsLoggedInAndUserIsStoreOwnerOrRawMaterialSupplier() {
        assertTrue(userAuthService.isLoggedIn());
        assertTrue(loggedInUser.isRawMaterialSupplier() || loggedInUser.isStoreOwner());
        isUpdated = false;
    }

//    @When("The user add a new product with full info")
//    public void theUserAddANewProductWithFullInfo() {
//        Product product = new Product("BIG CAKE"," A big cake that can feed up to 10 persons",55.00,7,userAuthService.getLoggedInStore().getStoreId());
//        try {
//            isUpdated = Product.createProduct(product,DatabaseService.getConnection(true));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Then("The product should be added successfully")
    public void theProductShouldBeAddedSuccessfully() {
        assertTrue(isUpdated);
    }

    @When("The user add a new product with name {string} And description {string} And price {string} And stock {string} And expiry date {string} Then The product should be added successfully")
    public void theUserAddANewProductWithNameAndDescriptionAndPriceAndStockAndExpiryDateThenTheProductShouldBeAddedSuccessfully(String name, String description, String price, String stock, String expiryDate) {
        Product product = new Product(name,description,price,stock,userAuthService.getLoggedInStore().getStoreId(),expiryDate);
        try {
            isUpdated = Product.createProduct(product,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @When("The user edits a product with ID {string}")
    public void theUserEditsAProductWithID(String id) {
        try {
            productTest = Product.getProductById(Integer.parseInt(id),DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @When("sets product name to {string}")
    public void setsProductNameTo(String name) {
        try {
            productTest.setProductName(name);
            isUpdated = Product.updateProduct(productTest,DatabaseService.getConnection(true),Product.UPDATE_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @When("sets product description to {string}")
    public void setsProductDescriptionTo(String description) {
        try {
            productTest.setDescription(description);
            isUpdated = Product.updateProduct(productTest,DatabaseService.getConnection(true),Product.UPDATE_DESCRIPTION);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @When("sets product price to {string}")
    public void setsProductPriceTo(String price) {
        try {
            productTest.setPrice(Double.parseDouble(price));
            isUpdated = Product.updateProduct(productTest,DatabaseService.getConnection(true),Product.UPDATE_PRICE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @When("sets product stock to {string}")
    public void setsProductStockTo(String stock) {
        try {
            productTest.setPrice(Integer.parseInt(stock));
            isUpdated = Product.updateProduct(productTest,DatabaseService.getConnection(true),Product.UPDATE_STOCK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @When("sets product expiry date to {string}")
    public void setsProductExpiryDateTo(String expDate) {
        try {
            productTest.setExpiryDate(expDate);
            isUpdated = Product.updateProduct(productTest,DatabaseService.getConnection(true),Product.UPDATE_EXPIRY_DATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Then("The product should be updated successfully")
    public void theProductShouldBeUpdatedSuccessfully() {
        assertTrue(isUpdated);

    }

    @When("The user deletes product with ID {string}")
    public void theUserDeletesProductWithID(String string) {
        try {
            Product.createProduct(productDeleteTest,DatabaseService.getConnection(true));
            Product.setId(4000,productDeleteTest,DatabaseService.getConnection(true));
            isUpdated = Product.deleteProduct(4000,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("The product should be deleted successfully")
    public void theProductShouldBeDeletedSuccessfully() {
        assertTrue(isUpdated);
    }

    @When("The user chooses invalid updateType")
    public void theUserChoosesInvalidUpdateType() {
        try {
            productTest.setProductName("testName");
            isUpdated = Product.updateProduct(productTest,DatabaseService.getConnection(true),9);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Then("The product update should fail")
    public void theProductUpdateShouldFail() {
        assertFalse(isUpdated);
    }


    @When("The user adds an already existing product")
    public void theUserAddsAnAlreadyExistingProduct() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("The product shouldn't be added")
    public void theProductShouldnTBeAdded() {
        // Write code here that turns the phrase above into concrete actions

    }


    @When("the user checks the best selling product in store")
    public void theUserChecksTheBestSellingProductInStore() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the product with the most sales should appear")
    public void theProductWithTheMostSalesShouldAppear() {
        // Write code here that turns the phrase above into concrete actions

    }


    @When("the user asks for discount suggestion")
    public void theUserAsksForDiscountSuggestion() {
        try {
            Product.getProductsExpiringInLessThan120Days(DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Then("the products close expiry dates should appear")
    public void theProductsCloseExpiryDatesShouldAppear() {

    }


}
