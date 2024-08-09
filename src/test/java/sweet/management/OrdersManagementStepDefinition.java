package sweet.management;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.management.entities.Order;
import sweet.management.entities.OrderItem;
import sweet.management.entities.Product;
import sweet.management.services.DatabaseService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

public class OrdersManagementStepDefinition {

    UserAuthService userAuthService;
    boolean isUpdatedOrder;
    Order orderToBeUpdated;
    Order orderToBeDeleted;
    List<Order> ordersList;

    Product product;
    Order order;
    boolean isUpdatedOrderItem;
    OrderItem orderItemToBeUpdated;
    OrderItem orderItemToBeDeleted;
    List<OrderItem> orderItemsList;



    public OrdersManagementStepDefinition() {
       userAuthService = new UserAuthService();


    }


    @Given("User is logged in with email {string} and password {string}")
    public void userIsLoggedInWithEmailAndPassword(String email, String password) {
        assertTrue(userAuthService.login(email,password, DatabaseService.getConnection(true)));
        assertTrue(userAuthService.isLoggedIn());
    }
    @When("The user make a new order email {string} and orderStatus {string}")
    public void theUserMakeANewOrderEmailAndOrderStatus(String userEmail, String orderStatus) {
        Order order = new Order(userEmail,2,orderStatus);
        try {
            isUpdatedOrder = Order.createOrder(order,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Then("The order should be successfully created")
    public void theOrderShouldBeSuccessfullyCreated() {
        assertTrue(isUpdatedOrder);
    }


    @When("The store owner edits an order with id {string}")
    public void theStoreOwnerEditsAnOrderWithId(String id) {
        try {
            orderToBeUpdated = Order.getOrderById(Integer.parseInt(id),DatabaseService.getConnection(true));
            assertNotNull(orderToBeUpdated);
            isUpdatedOrder = false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @And("updates the status to {string}")
    public void updatesTheStatusTo(String status) {
        orderToBeUpdated.setOrderStatus(status);
        try {
            assertTrue(Order.updateOrder(orderToBeUpdated,DatabaseService.getConnection(true), Order.UPDATE_STATUS));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @And("updates total amount to {string}")
    public void updatesTotalAmountTo(String Total) {
        orderToBeUpdated.setTotalAmount(Double.parseDouble(Total));
        try {
            isUpdatedOrder = Order.updateOrder(orderToBeUpdated,DatabaseService.getConnection(true), Order.UPDATE_TOTAL_AMOUNT);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @When("The user deletes order with ID {string}")
    public void theUserDeletesOrderWithID(String id) {
        try {
            orderToBeDeleted = new Order(4000, "order.user@gmail.com", 2, "pending", 0.1, new Timestamp(System.currentTimeMillis()));
            assertTrue(Order.createOrder(orderToBeDeleted,DatabaseService.getConnection(true)));
            isUpdatedOrder  = Order.updateOrder(orderToBeDeleted,DatabaseService.getConnection(true),Order.DELETE_ORDER);
            assertTrue(isUpdatedOrder);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("The order should be deleted successfully")
    public void theOrderShouldBeDeletedSuccessfully() {
        assertTrue(isUpdatedOrder);
    }

    @When("The user edits an order with ID {string}")
    public void theUserEditsAnOrderWithID(String id) {
        try {
            orderToBeUpdated = Order.getOrderById(Integer.parseInt(id),DatabaseService.getConnection(true));
            isUpdatedOrder = false;
            assertNotNull(orderToBeUpdated);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @And("The user chooses invalid order updateType")
    public void theUserChoosesInvalidOrderUpdateType() {
        orderToBeUpdated.setTotalAmount(5.00);
        try {
            isUpdatedOrder = Order.updateOrder(orderToBeUpdated,DatabaseService.getConnection(true),10);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("The order update should fail")
    public void theOrderUpdateShouldFail() {
        assertFalse(isUpdatedOrder);
    }

    @When("The user try to  get orders by store id {string}")
    public void theUserTryToGetOrdersByStoreId(String id) throws SQLException {
        ordersList = null;
        ordersList = Order.getOrdersByStoreId(Integer.parseInt(id),DatabaseService.getConnection(true));
    }

    @Then("it should return the orders without exceptions")
    public void itShouldReturnTheOrdersWithoutExceptions() {
        assertNotNull(ordersList);
    }

    @When("The user try to  get orders by user email {string}")
    public void theUserTryToGetOrdersByUserEmail(String email) throws SQLException {
        ordersList = null;
        ordersList = Order.getOrdersByUserEmail(email, DatabaseService.getConnection(true));
    }

    @Then("order is updated successfully")
    public void orderIsUpdatedSuccessfully()
    {
        assertTrue(isUpdatedOrder);
    }


    @Given("The order_id {string} is made to purchase the product_id {string} and the product and Order exists in the database")
    public void theOrder_idIsMadeToPurchaseTheProduct_idAndTheProductAndOrderExistsInTheDatabase(String orderId, String productId) {
        try {
            product = Product.getProductById(Integer.parseInt(productId), DatabaseService.getConnection(true));
            order = Order.getOrderById(Integer.parseInt(orderId), DatabaseService.getConnection(true));
            assertNotNull("Product should be retrieved successfully", product);
            assertNotNull("Product should be retrieved successfully", order);
        } catch (SQLException e) {
            fail("SQLException should not have occurred: " + e.getMessage());
        }
    }

    @When("The quantity is {string} and the price is {string}")
    public void theQuantityIsAndThePriceIs(String quantity, String totalPrice) {
        try {
        OrderItem orderItem = new OrderItem(
                order.getOrderId(),product.getProductId(),Integer.parseInt(quantity),Double.parseDouble(totalPrice)
        );
            isUpdatedOrderItem = OrderItem.createOrderItem(orderItem,DatabaseService.getConnection(true));
        assertNotNull("Product should be retrieved successfully", orderItem);
        } catch (SQLException e) {
            fail("SQLException should not have occurred: " + e.getMessage());
        }
    }


    @Then("The orderItem should be successfully created")
    public void theOrderItemShouldBeSuccessfullyCreated() {assertTrue(isUpdatedOrderItem);
    }


//    @When("The user try to  get orders by store id {string} and the there is sql connection problem")
//    public void theUserTryToGetOrdersByStoreIdAndTheThereIsSqlConnectionProblem(String id) {
//        ordersList = null;
//        try {
//            ordersList = Order.getOrdersByStoreId(Integer.parseInt(id),DatabaseService.getConnection(false));
//        } catch (SQLException e) {
//            System.out.println("failed to get orders by user id");
//        }
//    }
//
//    @When("The user try to  get orders by user email {string} and the there is sql connection problem")
//    public void theUserTryToGetOrdersByUserEmailAndTheThereIsSqlConnectionProblem(String email) {
//
//        ordersList = null;
//        try {
//            ordersList = Order.getOrdersByUserEmail(email,DatabaseService.getConnection(true));
//        } catch (SQLException e) {
//            System.out.println("failed to get orders by user email");
//        }
//    }
//
//    @Then("it should fail")
//    public void itShouldFail() {
//        assertNull(ordersList);
//    }
}
