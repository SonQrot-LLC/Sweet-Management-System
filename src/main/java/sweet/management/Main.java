package sweet.management;

import sweet.management.entities.*;
import sweet.management.services.DatabaseService;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    private static final String STORE_ID = "Store ID";
    private static final String ORDER_ID = "Order ID";
    private static final String STATUS = "Status";
    private static final String TOTAL_AMOUNT = "Total amount";
    private static final String ORDER_DATE = "Order Date";
    private static final String STORE_NAME = "Store Name";
    private static final String OWNER_EMAIL = "Owner Email";
    private static final String BUSINESS_INFO = "Business Info";
    private static final String DATE_ADDED = "Date Added";
    private static final String PRICE = "Price";
    private static final String STOCK = "Stock";
    private static final String EXPIRY_DATE = "Expiry date";
    private static final String DISCOUNT = "Discount";
    private static final String ITEM_NAME = "Item Name";
    private static final String CONTENT = "Content";
    private static final String DELETED_SUCCESSFULLY = "Deleted successfully!";
    private static final String ENTER_USER_EMAIL = "Enter User Email";
    private static final String NOTIFICATION_ID = "Notification ID";
    static Logger logger;
    static Scanner scanner = new Scanner(System.in);
    static  UserAuthService userAuthService = new UserAuthService();
    private static String search;
    private static Order order;
    private static int whichType = 0;
    public static final int ADMIN_TYPE = 1;
    public static final int STORE_OWNER_TYPE = 2;
    public static final int SUPPLIER_TYPE = 3;
    public static final int BENEFICIARY_USER_TYPE = 4;
    private static final String INVALID_CHOICE_MESSAGE = "Invalid choice! Please try again.";
    private static final String SOMETHING_WENT_WRONG_MESSAGE = "Something went wrong! Please try again.";
    private static final int GET_BY_ID = 1;
    private static final int GET_ALL = 2;
    private static final int GET_BY_SEARCH =3;
    private static final int GET_BY_STORE = 4;
    private static int storeId = 0;
    private static int feedbackProductId = 0;




    static {
        logger = Logger.getLogger(ColoredFormatter.class.getName());
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new ColoredFormatter());
        logger.setUseParentHandlers(false); // Disable default console handler
        logger.addHandler(handler);
    }

    public static void main(String[] args) {


        // Log messages
        logger.info("This is an info message.");
        logger.warning("This is an warning message.");
        userRegister();
    }

    public static void userRegister() {
        int choice;
        while (true) {
            logger.info("""
                    Enter your choice:
                    1. Sign Up
                    2. Login
                    3. Exit""");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1 -> signUp();
                case 2 -> signIn();
                default -> {
                    System.exit(0);
                    return;
                }
            }
            if(userAuthService.isLoggedIn()){
                determineScreenAfterLogin();
            }

        }
    }

    private static void determineScreenAfterLogin() {
        switch (whichType){
            case ADMIN_TYPE:
                adminScreen();
                break;
            case STORE_OWNER_TYPE:
                storeOwnerScreen();
                break;
            case SUPPLIER_TYPE:
                supplierScreen();
                break;
            case BENEFICIARY_USER_TYPE:
                beneficiaryUserScreen();
                break;
            default:
                userRegister();
        }

    }

    private static void beneficiaryUserScreen() {

        logger.info("Beneficiary User Screen");
        logger.info("""
                Please enter your choice:
                1. View products
                2. View Stores
                3. Publish a recipe
                4. Browse recipes
                5. Communication
                6. Account preference
                7. User Profile
                8. My orders
                9. Log out
                """);
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> viewProductsScreen(GET_ALL);
            case 2 -> viewStores();
            case 3 -> publishRecipesScreen();
            case 4 -> browseRecipesScreen();
            case 5 -> communicationScreen();
            case 6 -> accountPreferenceScreen();
            case 7 -> editUserProfileScreen();
            case 8 -> myOrdersScreen();
            case 9-> {
                logOut();
                return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        beneficiaryUserScreen();
    }

    private static void myOrdersScreen() {
        logger.info("""
                Please enter your choice:
                1. Show all orders
                2. Get pending orders
                3. Get order from a store
                4. View Order details
                5. Cancel Order
                6. Confirm Order
                7. Return to the main menu""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        logger.info("\n");
        switch (choice) {
            case 1 -> getOrderScreen(GET_ALL);
            case 2 -> getOrderScreen(GET_BY_SEARCH);
            case 3 -> getOrderScreen(GET_BY_STORE);
            case 4 -> viewOrderDetails();
            case 5 -> setOrderStatus("cancelled");
            case 6 -> setOrderStatus("processed");
            case 7 -> {
                beneficiaryUserScreen();
            return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        myOrdersScreen();
    }

    private static void getOrderScreen(int orderKey) {
        List <Order> orders = null;
        String email = userAuthService.getLoggedInUser().getEmail();
        try {
            if (orderKey == GET_ALL) {
                orders = Order.getOrdersByUserEmail(email,DatabaseService.getConnection(true));
            }
            else if(orderKey == GET_BY_SEARCH){
                orders = Order.getOrdersByUserEmail(email,DatabaseService.getConnection(true));
                orders.removeIf(order -> !order.getOrderStatus().equals("pending"));
            }
            else if(orderKey == GET_BY_STORE){
                logger.info("Enter Store ID");
                int id = scanner.nextInt();
                scanner.nextLine();
                orders = Order.getOrdersByStoreId(id,DatabaseService.getConnection(true));
                orders.removeIf(order -> !order.getUserEmail().equals(email));

            }
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }

        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("%-25s %-25s %-30s %-20s %-40s ",
                    ORDER_ID, STORE_ID, STATUS, TOTAL_AMOUNT, ORDER_DATE));
            assert orders != null;
            for (Order order : orders) {
                logger.info(String.format("%-25s %-25s %-30s %-20s %-40s ",
                        order.getOrderId(),
                        order.getStoreId(),
                        order.getOrderStatus(),
                        order.getTotalAmount(),
                        order.getCreatedAt()
                ));
            }
        }






    }

    private static void editUserProfile(String email) {
        UserProfile userProfile;
        try {
            userProfile = UserProfile.getUserProfileByEmail(email, DatabaseService.getConnection(true));
            if (userProfile == null)
                throw new SQLException(SOMETHING_WENT_WRONG_MESSAGE);
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }

        logger.info("Enter First Name:");
        String firstName = scanner.nextLine();

        logger.info("Enter Last Name:");
        String lastName = scanner.nextLine();

        logger.info("Enter Address");
        String address = scanner.nextLine();

        logger.info("Enter Phone number");
        String phone = scanner.nextLine();

        userProfile.setFirstName(firstName);
        userProfile.setLastName(lastName);
        userProfile.setAddress(address);
        userProfile.setPhone(phone);


        try {
            UserProfile.updateUserProfile(userProfile,DatabaseService.getConnection(true),UserProfile.UPDATE_ADDRESS,userAuthService);
            UserProfile.updateUserProfile(userProfile,DatabaseService.getConnection(true),UserProfile.UPDATE_FIRST_NAME,userAuthService);
            UserProfile.updateUserProfile(userProfile,DatabaseService.getConnection(true),UserProfile.UPDATE_LAST_NAME,userAuthService);
            UserProfile.updateUserProfile(userProfile,DatabaseService.getConnection(true),UserProfile.UPDATE_PHONE,userAuthService);
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }

        if(!userAuthService.getLoggedInUser().isAdmin()){
            userAuthService.getLoggedInUserProfile().setFirstName(firstName);
            userAuthService.getLoggedInUserProfile().setLastName(lastName);
            userAuthService.getLoggedInUserProfile().setAddress(address);
            userAuthService.getLoggedInUserProfile().setPhone(phone);
        }
    }



    private static void editUserProfileScreen() {
        logger.info("First Name: "+userAuthService.getLoggedInUserProfile().getFirstName());
        logger.info("Last Name: "+userAuthService.getLoggedInUserProfile().getLastName());
        logger.info("Address: "+userAuthService.getLoggedInUserProfile().getAddress());
        logger.info("Phone: "+userAuthService.getLoggedInUserProfile().getPhone()+ "\n");
        logger.info("""
                Please enter your choice:
                1. Edit user profile
                2. Return""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> editUserProfile(userAuthService.getLoggedInUser().getEmail().strip());
            case 2 -> logger.info("Returned to the main menu");
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
    }

    private static void browseRecipesScreen() {
        try {
            displayRecipesTable();
            logger.info("Enter any value to return back to the main menu");
            scanner.nextLine();
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
        }
    }

    private static void displayRecipesTable() throws SQLException {
        List<Recipe> recipes;
        recipes = Recipe.getAllRecipes(DatabaseService.getConnection(true));
        // Print table headers
        String headerMessage = String.format("%-10s %-50s %-30s %-40s %-30s %-20s",
                "ID", "User Email", "Recipe Name", "Ingredients", "Instructions", "Allergies");
        // Log the header message only if INFO level is enabled
        if (logger.isLoggable(Level.INFO)) {
            logger.info(headerMessage);
            // Print recipe details
            for (Recipe recipe : recipes) {
                String recipeMessage = String.format("%-10d %-50s %-30s %-40s %-30s %-20s",
                        recipe.getRecipeId(),
                        recipe.getUserEmail(),
                        recipe.getRecipeName(),
                        recipe.getIngredients(),
                        recipe.getInstructions(),
                        recipe.getAllergies());
                logger.info(recipeMessage);
            }
        }
    }

    private static void publishRecipesScreen() {
        logger.info("Publishing recipes \n");
        logger.info("Please enter the recipe name you would like to publish");
        String recipeName = scanner.nextLine();
        logger.info("Please enter the ingredients of the recipe you would like to publish");
        String ingredients = scanner.nextLine();
        logger.info("Please enter the instructions of the recipe you would like to publish");
        String instructions = scanner.nextLine();
        logger.info("Please enter the allergies of the recipe you would like to publish");
        String allergies = scanner.nextLine();
        Recipe recipe = new Recipe(userAuthService.getLoggedInUser().getEmail(), recipeName, ingredients, instructions, allergies);
        try{
            Recipe.createRecipe(recipe,DatabaseService.getConnection(true));
            logger.info("Successfully published the recipe");
        }catch (SQLException e){
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
        }
    }

    private static void viewStores() {
        List<Store> allStores;
        try {
            allStores = Store.getAllStores(DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }

        String headerMessage = String.format("%-10s %-20s %-30s %-25s %-25s",
                STORE_ID, STORE_NAME, OWNER_EMAIL, BUSINESS_INFO, DATE_ADDED);
        if (logger.isLoggable(Level.INFO)) {
            logger.info(headerMessage);

            for (Store store : allStores) {
                String storeMessage = String.format("%-10s %-20s %-30s %-25s %-25s",
                        store.getStoreId(),
                        store.getStoreName(),
                        store.getOwnerEmail(),
                        store.getBusinessInfo(),
                        store.getCreatedAt());
                logger.info(storeMessage);
            }
        }
        logger.info("""
                Please enter the id of the store to show its products if you want to return to the stores list enter 0
                """);
        storeId = scanner.nextInt();
        scanner.nextLine();
        if (storeId == 0){
            return;
        }
        else
            viewProductsScreen(GET_BY_ID);
        viewStores();
    }

    private static void viewProductsScreen(int productKey) {
        listProducts(productKey);
        if (viewProductOptions()) return;
        viewProductsScreen(productKey);
    }

    private static boolean viewProductOptions() {
        logger.info("""
                Please enter your choice:
                1. Purchase a product
                2. View feedbacks of a product
                3. search for a product
                4. Request a special product
                5. Return to the main menu""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> purchaseProductScreen();
            case 2 -> viewFeedbacksOfProduct();
            case 3 -> searchForProduct();
            case 4 -> requestSpecialProduct();
            case 5 -> {
                storeId = 0;
                determineScreenAfterLogin();
                return true;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        return false;
    }

    private static void listProducts(int productKey) {
        List<Product> allProducts;
        try {
            if (productKey == GET_BY_ID)
                allProducts = Product.getProductsByStoreId(storeId,DatabaseService.getConnection(true));
            else if(productKey == GET_ALL)
                allProducts = Product.getAllProducts(DatabaseService.getConnection(true));
            else if (productKey == GET_BY_SEARCH)
                allProducts = Product.searchProducts(search,DatabaseService.getConnection(true));
            else throw new SQLException();
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        String headerMessage = String.format("%-10s %-15s %-20s %-10s %-10s %-25s %-25s %-10s %-15s",
                "ID", "Name", "Description", PRICE, STOCK, "Date added", EXPIRY_DATE, DISCOUNT, STORE_NAME);
        if (logger.isLoggable(Level.INFO)) {
            logger.info(headerMessage);
        }



        for (Product product : allProducts) {
            // Get the Store object using the storeId from the Product
            Store store = null;  // Assuming you have a valid Connection object 'conn'
            try {
                store = Store.getStoreById(product.getStoreId(), DatabaseService.getConnection(true));
            } catch (SQLException e) {
                logger.info(SOMETHING_WENT_WRONG_MESSAGE);
            }
            String storeName = (store != null) ? store.getStoreName() : "Unknown";
            if (logger.isLoggable(Level.INFO)) {
                String productMessage = String.format("%-10s %-15s %-20s %-10s %-10s %-25s %-25s %-10s %-15s",
                        product.getProductId(),
                        product.getProductName(),
                        product.getDescription(),
                        product.getPrice() + "$",
                        product.getStock(),
                        product.getCreatedAt(),
                        product.getExpiryDate(),
                        product.getDiscount(),
                        storeName);

                logger.info(productMessage);
            }

        }
    }

    private static void purchaseProductScreen() {
        logger.info("""
                Please enter the id of the product to purchase:
              """);
        int productID = scanner.nextInt();
        scanner.nextLine();

        logger.info("""
                Please enter the quantity of the product to purchase:
              """);
        int quantity = scanner.nextInt();
        scanner.nextLine();

        try {
            Product tempProduct = Product.getProductById(productID,DatabaseService.getConnection(true));
            assert tempProduct != null;
            if (!tempProduct.isAvailable() || tempProduct.getStock() < quantity){
                logger.info("\nthis Product is not available ("+tempProduct.getStock()+" left in the stock)\n");
                return;
            }
            if (order == null || (order.getStoreId() != tempProduct.getStoreId())) {
                order = new Order(userAuthService.getLoggedInUser().getEmail(), tempProduct.getStoreId(), "pending");
                Order.createOrder(order,DatabaseService.getConnection(true));
            }
            double price = tempProduct.getPrice() * 0.1 + tempProduct.getPrice();
            String formattedPrice = String.format("%.2f", price);
            price = Double.parseDouble(formattedPrice);
            OrderItem tempOrderItem = new OrderItem(order.getOrderId(),productID,quantity,price);
            OrderItem.createOrderItem(tempOrderItem,DatabaseService.getConnection(true));
            logger.info("The item was added successfully to the order");

        } catch (SQLException e) {
            logger.info(SOMETHING_WENT_WRONG_MESSAGE);
        }



    }

    private static void searchForProduct() {
        logger.info("""
                Please enter the value to search abt:
                """);
        search = scanner.nextLine();
        listProducts(GET_BY_SEARCH);
        if (viewProductOptions()) return;
        searchForProduct();
    }

    private static void viewFeedbacksOfProduct() {
        logger.info("""
                  Please enter the id of the product to see the feedbacks:
                """);
        feedbackProductId = scanner.nextInt();
        scanner.nextLine();
        displayFeedback(GET_BY_ID);

        logger.info("""
                Please enter your choice:
                1. Add a Feedback
                2. Return to the products list
             """);
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> addFeedback();
            case 2 -> {
                return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        viewFeedbacksOfProduct();
    }
    private static void requestSpecialProduct() {
        logger.info("Enter the recipient's email:");
        String recipientEmail = scanner.nextLine();

        logger.info("Enter the special request message:");
        String message = scanner.nextLine();

        String senderEmail = userAuthService.getLoggedInUser().getEmail();

        try {
            Notification.insertNotification(Objects.requireNonNull(DatabaseService.getConnection(true)), recipientEmail, senderEmail, message);
        } catch (SQLException e) {
            logger.warning("Something went wrong while sending the notification.");
        }
    }

    private static void displayFeedback(int feedbackKey) {
        List<Feedback> feedbackList;
        try {
            if (feedbackKey == GET_ALL) {
                feedbackList = Feedback.getAllFeedbacks(DatabaseService.getConnection(true));
            } else if(feedbackKey == GET_BY_ID) {
                feedbackList = Feedback.getFeedback(Feedback.QUERY_BY_PRODUCT, String.valueOf(feedbackProductId), DatabaseService.getConnection(true));
            } else if (feedbackKey == GET_BY_SEARCH) {
                feedbackList = Feedback.getFeedback(Feedback.QUERY_BY_EMAIL,search, DatabaseService.getConnection(true));
            } else if (feedbackKey == GET_BY_STORE) {
                feedbackList = Feedback.getFeedback(Feedback.QUERY_BY_STORE,String.valueOf(storeId), DatabaseService.getConnection(true));
            } else throw new SQLException();
        } catch (SQLException e) {
            logger.info(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if (logger.isLoggable(Level.INFO)) {
            String headerMessage = String.format("%-10s %-20s %-10s %-30s %-25s",
                    "ID", "User Email", "Rating", "Comment", "Created At");
            logger.info(headerMessage);

            for (Feedback feedback : feedbackList) {
                String feedbackMessage = String.format("%-10d %-20s %-10d %-30s %-25s",
                        feedback.getFeedbackId(),
                        feedback.getUserEmail(),
                        feedback.getRating(),
                        feedback.getComment(),
                        feedback.getCreatedAt());
                logger.info(feedbackMessage);
            }
        }

    }

    private static void addFeedback() {
        logger.info("""
                  Please enter the id of the product to add the feedback:
                """);
        int productId = scanner.nextInt();
        scanner.nextLine();

        logger.info("""
                  Please enter the rating of the product:
                """);
        int rating = scanner.nextInt();
        scanner.nextLine();

        logger.info("""
                  Please enter the feedback description:
                """);
        String feedbackDescription = scanner.nextLine();
        try {
            Product tempProduct = Product.getProductById(productId,DatabaseService.getConnection(true));
            if(tempProduct != null){
                Feedback tempFeedback = new Feedback(userAuthService.getLoggedInUser().getEmail(),productId,rating,feedbackDescription,DatabaseService.getConnection(true));
                Feedback.createFeedback(tempFeedback,DatabaseService.getConnection(true));
            }
            else
                logger.warning(INVALID_CHOICE_MESSAGE);

        }catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
        }
    }


    private static void supplierScreen() {
        logger.info("Supplier Screen");
        logger.info("""
                Please enter your choice:
                1. Manage Materials
                2. Discount Management
                3. Monitor Sales
                4. Store preference
                5. Communication
                6. Account preference
                7. Order Management
                8. Best selling Item
                9. Check all Notifications
                10. Log out""");
        if (storeOwnerAndSupplierOptions()) return;
        supplierScreen();
    }

    private static boolean storeOwnerAndSupplierOptions() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        logger.info("\n");
        switch (choice) {
            case 1 -> manageProductsScreen();
            case 2 -> discountManagementScreen();
            case 3 -> monitorSalesScreen();
            case 4 -> storePreferencesScreen();
            case 5 -> communicationScreen();
            case 6 -> accountPreferenceScreen();
            case 7 -> orderManagementScreen();
            case 8 -> bestSellingProduct();
            case 9 -> checkNotifications();
            case 10 -> {
                logOut();
                return true;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        return false;
    }

    private static void storeOwnerScreen() {
        logger.info("Store Owner Screen");
        logger.info("""
                Please enter your choice:
                1. Manage Products
                2. Discount Management
                3. Monitor Sales
                4. Store preference
                5. Communication
                6. Account preference
                7. Order Management
                8. Best selling product
                9. Check all Notifications
                10. Log out""");
        if (storeOwnerAndSupplierOptions()) return;
        storeOwnerScreen();
    }


    private static void orderManagementScreen() {
        List <Order> orders;
        String email = userAuthService.getLoggedInStore().getOwnerEmail();
        try {
            Store store = Store.getStoreByOwnerEmail(email,DatabaseService.getConnection(true));
            assert store != null;
            orders = Order.getOrdersByStoreId(store.getStoreId(),DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if (logger.isLoggable(Level.INFO) && !orders.isEmpty()) {
            String headerMessage = String.format("%-25s %-50s %-30s %-20s %-40s ",
                    ORDER_ID, "Customer Email", STATUS, TOTAL_AMOUNT, ORDER_DATE);
            logger.info(headerMessage);

            for (Order order : orders) {
                String orderMessage = String.format("%-25s %-50s %-30s %-20s %-40s ",
                        order.getOrderId(),
                        order.getUserEmail(),
                        order.getOrderStatus(),
                        order.getTotalAmount(),
                        order.getCreatedAt());
                logger.info(orderMessage);
            }
        }
        logger.info("""
                Please enter your choice:
                1. View Order Details
                2. Ship Order
                3. Return""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> viewOrderDetails();
            case 2 -> setOrderStatus("shipped");
            case 3 -> {
                return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        orderManagementScreen();
    }


    public static void setOrderStatus(String status){
        logger.info("Enter Order ID:");
        Order order;
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            order = Order.getOrderById(id,DatabaseService.getConnection(true));
            assert order != null;
            order.setOrderStatus(status);
            Order.updateOrder(order,DatabaseService.getConnection(true),Order.UPDATE_STATUS);
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
        }
    }


    public static void viewOrderDetails(){
        List <OrderItem> orderItems;
        logger.info("Enter Order ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {

            Order order = Order.getOrderById(id,DatabaseService.getConnection(true));
            if (order == null)
                throw new SQLException();
            if (userAuthService.getLoggedInUser().isBeneficiaryUser() && !order.getUserEmail().equals(userAuthService.getLoggedInUser().getEmail()))
                throw new SQLException();
            if((userAuthService.getLoggedInUser().isStoreOwner() || userAuthService.getLoggedInUser().isRawMaterialSupplier()) && order.getStoreId() != userAuthService.getLoggedInStore().getStoreId())
                throw new SQLException();

            orderItems = OrderItem.getOrderItemsByOrder(id,DatabaseService.getConnection(true));
            if (logger.isLoggable(Level.INFO) && !orderItems.isEmpty()) {
                String headerMessage = String.format("%-25s %-30s %-30s %-20s ",
                        "Item ID", ITEM_NAME, "Quantity", "Total Amount");
                logger.info(headerMessage);

                for (OrderItem orderItem : orderItems) {
                    String productName = Objects.requireNonNull(Product.getProductById(orderItem.getProductId(), DatabaseService.getConnection(true))).getProductName();
                    String orderItemMessage = String.format("%-25s %-30s %-30s %-20s ",
                            orderItem.getProductId(),
                            productName,
                            orderItem.getQuantity(),
                            orderItem.getPrice());
                    logger.info(orderItemMessage);
                }
            }
            logger.info("\n");

        } catch (SQLException e) {
            logger.warning("Wrong order ID");
        }

    }


    public static void bestSellingProduct(){
        String email = userAuthService.getLoggedInUser().getEmail();
        ViewService service = new ViewService();
        List<ViewService.Product> products;
        try {
            products = service.getBestSellingProducts(DatabaseService.getConnection(true));
            if (logger.isLoggable(Level.INFO) && !products.isEmpty()) {
                String headerMessage = String.format("%-26s %-30s %-25s %-20s ",
                        "ID", ITEM_NAME, STORE_NAME, "Quantity Sold");
                logger.info(headerMessage);

                for (ViewService.Product product : products) {
                    String storeName = Objects.requireNonNull(Store.getStoreById(product.getStoreId(), DatabaseService.getConnection(true))).getStoreName();
                    String ownerEmail = Objects.requireNonNull(Store.getStoreById(product.getStoreId(), DatabaseService.getConnection(true))).getOwnerEmail();

                    if (ownerEmail.equals(email)) {
                        String productMessage = String.format("%-26s %-30s %-25s %-20s",
                                product.getProductId(),
                                product.getProductName(),
                                storeName,
                                product.getTotalQuantitySold());
                        logger.info(productMessage);
                    }
                }
            }

        }
        catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
        }
    }
    public static void monitorSalesScreen(){
        ViewService service = new ViewService();
        List<ViewService.StoreProfit> profits;
        try {
            String email = userAuthService.getLoggedInUser().getEmail();
            profits = service.getStoreProfits(DatabaseService.getConnection(true));
        if (!profits.isEmpty() && logger.isLoggable(Level.INFO)) {
                String headerMessage = String.format("%-27s %-30s %-20s",
                        STORE_ID, "Name", "Total Profits");
                logger.info(headerMessage);

                for (ViewService.StoreProfit profit : profits) {
                    String storeName = Objects.requireNonNull(Store.getStoreById(profit.getStoreId(), DatabaseService.getConnection(true))).getStoreName();
                    String ownerEmail = Objects.requireNonNull(Store.getStoreById(profit.getStoreId(), DatabaseService.getConnection(true))).getOwnerEmail();

                    if (ownerEmail.equals(email)) {
                        String profitMessage = String.format("%-27s %-30s %-20s",
                                profit.getStoreId(),
                                storeName,
                                profit.getTotalProfit());
                        logger.info(profitMessage);
                    }
                }
            }


        }
        catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
        }
    }
    public static void checkNotifications()
    {
        String ownerEmail = userAuthService.getLoggedInUser().getEmail();
        List<Notification> notifications;
        if (logger.isLoggable(Level.INFO)) {
        try {
            notifications = Notification.getNotificationsByUserEmail(DatabaseService.getConnection(true), ownerEmail);
                logger.info(String.format("Retrieved %d notifications for owner.", notifications.size()));

            for (Notification notification : notifications) {
                logger.info("Notification ID: " + notification.getNotificationId());
                logger.info("Message: " + notification.getMessage());
                logger.info("Read: " + notification.isRead());
                logger.info("Created At: " + notification.getCreatedAt());
            }

            Scanner scanner = new Scanner(System.in);
            logger.info("Enter the ID of the notification to mark as read:");
            int notificationId = scanner.nextInt();
            logger.info("User selected notification ID " + notificationId + " to mark as read.");

            Notification.markNotificationAsRead(Objects.requireNonNull(DatabaseService.getConnection(true)), notificationId);
                logger.info(NOTIFICATION_ID + notificationId + " marked as read.");

        } catch (SQLException e) {
            logger.warning("Error while checking notifications: " + e.getMessage());
        }
        }
    }

    public static void accountPreferenceScreen(){
        logger.info("Password: "+userAuthService.getLoggedInUser().getPassword());
        logger.info("City: " + userAuthService.getLoggedInUser().getCity());
        logger.info("Role: "+ userAuthService.getLoggedInUser().getRole());
        logger.info("\n");
        logger.info("""
                Please enter your choice:
                1. Edit Account Preferences
                2. Return""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> editAccountPreferences(userAuthService.getLoggedInUser().getEmail().strip());
            case 2 -> {
                return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        accountPreferenceScreen();

    }
    public static void editAccountPreferences(String email){
        User user;
        int choice;
        String role;
        try {
            role = Objects.requireNonNull(User.getUserByEmail(email, DatabaseService.getConnection(true))).getRole();
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        try {
            user = User.getUserByEmail(email, DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        logger.info("Enter Password:");
        String password = scanner.nextLine();
        logger.info("Enter City");
        String city = scanner.nextLine();
        try {
            if (!Objects.requireNonNull(User.getUserByEmail(email, DatabaseService.getConnection(true))).isBeneficiaryUser()) {
                logger.info("""
                        Chose role:
                        1. Store Owner
                        2. Raw Material Supplier""");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> role = "store_owner";
                    case 2 -> role = "raw_material_supplier";
                    default -> logger.warning(INVALID_CHOICE_MESSAGE);
                }
            }
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        assert user != null;
        user.setPassword(password);
        user.setCity(city);
        user.setRole(role);
        try {
            User.updateUser(user, DatabaseService.getConnection(true),User.UPDATE_CITY,userAuthService);
            User.updateUser(user, DatabaseService.getConnection(true),User.UPDATE_PASSWORD,userAuthService);
            User.updateUser(user,DatabaseService.getConnection(true),User.UPDATE_ROLE,userAuthService);
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if(!userAuthService.getLoggedInUser().isAdmin()){
            userAuthService.getLoggedInUser().setRole(role);
            userAuthService.getLoggedInUser().setCity(city);
            userAuthService.getLoggedInUser().setPassword(password);
        }

    }

    public static void storePreferencesScreen(){
        Store store;
        try {
            store = Store.getStoreByOwnerEmail(userAuthService.getLoggedInUser().getEmail(),DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        assert store != null;
        logger.info("Store Name: "+store.getStoreName()+"    Business Information: "+store.getBusinessInfo());
        logger.info("\n");
        logger.info("""
                Please enter your choice:
                1. Edit Store Preferences
                2. Return""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> editStorePreferences();
            case 2 -> {
                return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        storePreferencesScreen();
    }

    public static void editStorePreferences(){
        Store store;
        try {
            store = Store.getStoreByOwnerEmail(userAuthService.getLoggedInUser().getEmail(),DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        logger.info("Enter Store Name");
        String storeName = scanner.nextLine();
        logger.info("Enter Store Business Information");
        String businessInfo = scanner.nextLine();
        Objects.requireNonNull(store).setStoreName(storeName);
        store.setBusinessInfo(businessInfo);
        try {
            Store.updateStore(store,DatabaseService.getConnection(true),Store.UPDATE_STORE_NAME,userAuthService);
            Store.updateStore(store,DatabaseService.getConnection(true),Store.UPDATE_BUSINESS_INFO,userAuthService);
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
        }
    }

    public static void communicationScreen(){
        logger.info("""
                Please enter your choice:
                1. Send message
                2. View inbox
                3. View sent messages
                4. View sent messages to specific recipients
                5. Return""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> sendMessage();
            case 2 -> viewInbox();
            case 3 -> viewSentMessages();
            case 4 -> viewSentMessagesToSpecificRecipients();
            case 5 -> {
                return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        communicationScreen();
    }

    public static void viewSentMessagesToSpecificRecipients(){
       logger.info("Enter Recipient email");
       String email = scanner.nextLine();
        List<Message> messages;
        try {
            messages = Message.getMessagesBetweenUsers(DatabaseService.getConnection(true), userAuthService.getLoggedInUser().getEmail(),email);
        }
        catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if (logger.isLoggable(Level.INFO)) {
            String headerMessage = String.format("%-10s %-30s %-10s",
                    "ID", CONTENT, "Date Sent");
            logger.info(headerMessage);

            for (Message message : messages) {
                String messageDetails = String.format("%-10s %-30s %-10s",
                        message.getMessageId(),
                        message.getContent(),
                        message.getCreatedAt());
                logger.info(messageDetails);
            }
        }
    }

    public static void viewSentMessages(){
        List<Message> messages;
        try {
            messages = Message.getMessagesBySenderEmail(DatabaseService.getConnection(true),userAuthService.getLoggedInUser().getEmail());
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if (logger.isLoggable(Level.INFO)) {
            String headerMessage = String.format("%-12s %-25s %-30s %-10s",
                    "ID", "Receiver", CONTENT, "Date Sent");
            logger.info(headerMessage);

            for (Message message : messages) {
                String messageDetails = String.format("%-12s %-25s %-30s %-10s",
                        message.getMessageId(),
                        message.getReceiverEmail(),
                        message.getContent(),
                        message.getCreatedAt());
                logger.info(messageDetails);
            }
        }

    }


    public static void viewInbox(){
        List<Message> messages;
        try {
            messages = Message.getMessagesByReceiverEmail(DatabaseService.getConnection(true),userAuthService.getLoggedInUser().getEmail());
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if (logger.isLoggable(Level.INFO) && !messages.isEmpty()) {
            String headerMessage = String.format("%-10s %-25s %-30s %-10s",
                    "ID", "Sender", CONTENT, "Date Received");
            logger.info(headerMessage);

            for (Message message : messages) {
                String messageDetails = String.format("%-10s %-25s %-30s %-10s",
                        message.getMessageId(),
                        message.getSenderEmail(),
                        message.getContent(),
                        message.getCreatedAt());
                logger.info(messageDetails);
            }
        }

        logger.info("""
                Please enter your choice:
                1. Reply to message
                2. Return""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> replyToMessage();
            case 2 -> {
                return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        viewInbox();
    }

    public static void replyToMessage(){
        int id;
        logger.info("Enter message ID to reply to");
        id = scanner.nextInt();
        scanner.nextLine();
        logger.info("Enter reply");
        String reply = scanner.nextLine();
        try {
            Message.insertMessage(Objects.requireNonNull(DatabaseService.getConnection(true)),userAuthService.getLoggedInUser().getEmail(), Objects.requireNonNull(Message.getMessageById(DatabaseService.getConnection(true), id)).getSenderEmail(),reply);
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        logger.info("Message sent");
    }


    public static void sendMessage(){
        logger.info("Enter recipient mail");
        String recipient = scanner.nextLine();
        logger.info("Enter message to send");
        String message = scanner.nextLine();
        try {
            Message.insertMessage(Objects.requireNonNull(DatabaseService.getConnection(true)),userAuthService.getLoggedInUser().getEmail(),recipient,message);
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        logger.info("Message sent");
    }

    public static void discountManagementScreen(){
        List<Product> products;
        try {
            products = Product.getProductsByUserEmail(userAuthService.getLoggedInUser().getEmail(),DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if (logger.isLoggable(Level.INFO)) {
            String headerMessage = String.format("%-11s %-15s %-10s %-10s %-25s %-10s",
                    "ID", "Name", PRICE, STOCK, EXPIRY_DATE, DISCOUNT);
            logger.info(headerMessage);

            for (Product product : products) {
                String productDetails = String.format("%-11s %-15s %-10s %-10s %-25s %-10s",
                        product.getProductId(),
                        product.getProductName(),
                        product.getPrice() + "$",
                        product.getStock(),
                        product.getExpiryDate(),
                        product.getDiscount());
                logger.info(productDetails);
            }
        }

        logger.info("""
                Please enter your choice:
                1. Edit discount value
                2. Suggest items to discount
                3. Return""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> editDiscountScreen();
            case 2 -> suggestDiscountScreen();
            case 3 -> {
                return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        discountManagementScreen();
    }

    public static void suggestDiscountScreen(){
        logger.info("These Items are expiring soon!: ");
        List<Product> products;
        try {
            products = Product.getProductsExpiringInLessThan120Days(userAuthService.getLoggedInUser().getEmail(),DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("%-10s %-15s %-10s %-10s %-25s %-10s",
                    "ID", "Name", PRICE, STOCK, EXPIRY_DATE, DISCOUNT));

            for (Product product : products) {
                logger.info(String.format("%-10s %-15s %-10s %-10s %-25s %-10s",
                        product.getProductId(),
                        product.getProductName(),
                        product.getPrice() + "$",
                        product.getStock(),
                        product.getExpiryDate(),
                        product.getDiscount()));
            }
        }

        editDiscountScreen();
    }


    public static void editDiscountScreen(){
        int productID;
        Product product;
        boolean updated;
        logger.info("Enter product ID to discount: ");
        productID = scanner.nextInt();
        scanner.nextLine();
        try {
            product = Product.getProductById(productID,DatabaseService.getConnection(true));
            logger.info("Enter discount value: ");
            String discount = scanner.nextLine();
            assert product != null;
            product.setDiscount(Double.parseDouble(discount));
            updated = Product.updateProduct(product,DatabaseService.getConnection(true),Product.UPDATE_DISCOUNT);
    }
        catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if (updated)
            logger.info("Item updated successfully");
        else
            logger.warning("Item not updated successfully");
    }



    public static void manageProductsScreen(){
        List<Product> products;
        try {
            products = Product.getProductsByUserEmail(userAuthService.getLoggedInUser().getEmail(),DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if(!products.isEmpty() && logger.isLoggable(Level.INFO)) {
                logger.info(String.format("%-10s %-15s %-20s %-10s %-10s %-25s %-25s %-10s",
                        "ID", "Name", "Description", PRICE, STOCK, "Date added", EXPIRY_DATE, DISCOUNT));

                for (Product product : products) {
                    logger.info(String.format("%-10s %-15s %-20s %-10s %-10s %-25s %-25s %-10s",
                            product.getProductId(),
                            product.getProductName(),
                            product.getDescription(),
                            product.getPrice() + "$",
                            product.getStock(),
                            product.getCreatedAt(),
                            product.getExpiryDate(),
                            product.getDiscount()));
                }
            }


        if(userAuthService.getLoggedInUser().isStoreOwner()) {
            logger.info("""
                    Please enter your choice:
                    1. Add product
                    2. Edit product
                    3. Delete product
                    4. Return""");
        }else {
            logger.info("""
                    Please enter your choice:
                    1. Add material
                    2. Edit material
                    3. Delete material
                    4. Return""");
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> addProductScreen();
            case 2 -> editProductScreen();
            case 3 -> deleteProductScreen();
            case 4 -> {
                return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        manageProductsScreen();
    }

    public static void addProductScreen() {
        int storeID;
        logger.info("Enter name: ");
        String name = scanner.nextLine();
        logger.info("Enter description: ");
        String description = scanner.nextLine();
        logger.info("Enter price: ");
        String price = scanner.nextLine();
        logger.info("Enter stock: ");
        String stock = scanner.nextLine();
        logger.info("Enter expiry date: ");
        String expiryDate = scanner.nextLine();
        try {
            storeID = Objects.requireNonNull(Store.getStoreByOwnerEmail(userAuthService.getLoggedInUser().getEmail(), DatabaseService.getConnection(true))).getStoreId();
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        Product product = new Product(name,description,price,stock,"0",storeID,expiryDate);
        try {
            Product.createProduct(product,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        logger.info("Added successfully!");
    }

    public static void deleteProductScreen(){
        boolean deleted;
        logger.info("Enter ID: ");
        int productID = scanner.nextInt();
        scanner.nextLine();
        try {
            deleted= Product.deleteProduct(productID,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if (deleted)
            logger.info(DELETED_SUCCESSFULLY);
        else
            logger.warning("Deletion failed!");
    }


    public static void editProductScreen(){
        int productID;
        Product product;
        boolean updated;
        logger.info("Enter ID: ");
        productID = scanner.nextInt();
        scanner.nextLine();
        try {
         product = Product.getProductById(productID,DatabaseService.getConnection(true));
        logger.info("Enter name: ");
        String name = scanner.nextLine();
        assert product != null;
        product.setProductName(name);
        Product.updateProduct(product,DatabaseService.getConnection(true),Product.UPDATE_NAME);
        logger.info("Enter description: ");
        String description = scanner.nextLine();
        product.setDescription(description);
        Product.updateProduct(product,DatabaseService.getConnection(true),Product.UPDATE_DESCRIPTION);
        logger.info("Enter price: ");
        String price = scanner.nextLine();
        product.setPrice(Double.parseDouble(price));
        Product.updateProduct(product,DatabaseService.getConnection(true),Product.UPDATE_PRICE);
        logger.info("Enter stock: ");
        String stock = scanner.nextLine();
        product.setStock(Integer.parseInt(stock));
        Product.updateProduct(product,DatabaseService.getConnection(true),Product.UPDATE_STOCK);
        logger.info("Enter expiry date: ");
        String expiryDate = scanner.nextLine();
        product.setExpiryDate(expiryDate);
        updated = Product.updateProduct(product,DatabaseService.getConnection(true),Product.UPDATE_EXPIRY_DATE);
        }
        catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if (updated)
            logger.info("Updated successfully!");
        else
            logger.warning("Update failed!");

    }

    private static void adminScreen() {
        logger.info("Admin Screen");
        logger.info("""
                Please enter your choice:
                1. Manage Account Preferences
                2. Manage Beneficiary users
                3. Monitor Profits
                4. Return all best selling products in each store
                5. Return statistics on registered users by city
                6. Manage recipes
                7. Manage feedback
                8. Account preference
                9. Log out
                """);
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> manageAccountPreferences();
            case 2 -> manageBeneficiaryUsersScreen();
            case 3 -> monitorProfitsScreen();
            case 4 -> returnAllBestSellingProductsInEachStoreScreen();
            case 5 -> returnStatisticsOnRegisteredUsersByCityScreen();
            case 6 -> manageRecipesScreen();
            case 7 -> manageFeedbackScreen();
            case 8 -> accountPreferenceScreen();
            case 9 -> {
                logOut();
                return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        adminScreen();
    }

    private static void manageFeedbackScreen() {
        displayFeedback(GET_ALL);
        feedbackManagementChoices();
    }

    private static void feedbackManagementChoices() {
        logger.info("""
                Please enter your choice:
                1. Remove Feedback
                2. Get Feedbacks of a product
                3. Get Feedbacks of a user
                4. Get Feedbacks of a store
                5. Return to the main menu
                """);
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> deleteFeedback();
            case 2 -> getFeedbacksScreen(GET_BY_ID);
            case 3 -> getFeedbacksScreen(GET_BY_SEARCH);
            case 4 -> getFeedbacksScreen(GET_BY_STORE);
            case 5 -> adminScreen();
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
    }

    private static void getFeedbacksScreen(int feedbackKey) {
        if (feedbackKey == GET_BY_ID){
            logger.info("Please Enter the id of the product");
            feedbackProductId = scanner.nextInt();
            scanner.nextLine();
        }
        else if (feedbackKey == GET_BY_SEARCH){
            logger.info("Please Enter the search term");
            search = scanner.nextLine();
        }
        else if (feedbackKey == GET_BY_STORE){
            logger.info("Please Enter the id of the store");
            feedbackProductId = scanner.nextInt();
            scanner.nextLine();
        }
        displayFeedback(feedbackKey);
        search = null;
        storeId = 0;
        feedbackProductId = 0;
        feedbackManagementChoices();

    }

    private static void deleteFeedback() {
        logger.info("Please Enter the id of the feedback to delete");
        int feedbackID = scanner.nextInt();
        scanner.nextLine();
        try {
            if (Feedback.deleteFeedback(feedbackID,DatabaseService.getConnection(true)))
                logger.info(DELETED_SUCCESSFULLY);
            else throw new SQLException();
        } catch (SQLException e) {
            logger.info(SOMETHING_WENT_WRONG_MESSAGE);
        }

    }

    public static void monitorProfitsScreen(){
        ViewService service = new ViewService();
        List<ViewService.StoreProfit> profits;
        try {
            profits = service.getStoreProfits(DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("%-25s %-30s %-20s",
                    STORE_ID, STORE_NAME, "Total Profits"));

            for (ViewService.StoreProfit profit : profits) {
                logger.info(String.format("%-25s %-30s %-20s",
                        profit.getStoreId(),
                        profit.getStoreName(),
                        profit.getTotalProfit()));
            }
        }

    }

    private static void manageRecipesScreen() {
        try {
            displayRecipesTable();
            logger.info("""
                    Please enter your choice:
                    1. Add a Recipe
                    2. Update a Recipe
                    3. Delete a Recipe
                    4. return to the main menu
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> publishRecipesScreen();
                case 2 -> updateRecipesScreen();
                case 3 -> deleteRecipesScreen();
                case 4 -> {
                    determineScreenAfterLogin();
                    return;
                }
                default -> logger.warning(INVALID_CHOICE_MESSAGE);
            }
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
        }
        manageRecipesScreen();

    }

    private static void deleteRecipesScreen() {
        logger.info("Please enter the id of the Recipe : \n");
        int recipeID = scanner.nextInt();
        scanner.nextLine();
        Recipe recipe;
        try{
            recipe = Recipe.getRecipeById(recipeID,DatabaseService.getConnection(true));
            if (recipe==null)
                throw new SQLException();
            if (userAuthService.getLoggedInUser().isAdmin() || userAuthService.getLoggedInUser().getEmail().equals(recipe.getUserEmail())){
                if(Recipe.deleteRecipe(recipeID,DatabaseService.getConnection(true)))
                    logger.info("Updated successfully!\n");
                else throw new SQLException();
            }
        }
        catch (SQLException e){
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);

        }





    }

    private static void updateRecipesScreen() {
        logger.info("Please enter the id of the Recipe : \n");
        int recipeID = scanner.nextInt();
        Recipe recipe;
        scanner.nextLine();
        try{
            recipe = Recipe.getRecipeById(recipeID,DatabaseService.getConnection(true));
            if (recipe==null)
                throw new SQLException();
            if (userAuthService.getLoggedInUser().isAdmin() || userAuthService.getLoggedInUser().getEmail().equals(recipe.getUserEmail())){

                logger.info("Please enter the recipe name ");
                String recipeName = scanner.nextLine();
                logger.info("Please enter the ingredients");
                String ingredients = scanner.nextLine();
                logger.info("Please enter the instructions ");
                String instructions = scanner.nextLine();
                logger.info("Please enter the allergies");
                String allergies = scanner.nextLine();

                recipe.setRecipeName(recipeName);
                recipe.setIngredients(ingredients);
                recipe.setInstructions(instructions);
                recipe.setAllergies(allergies);

                Recipe.updateRecipe(recipe,DatabaseService.getConnection(true),Recipe.UPDATE_RECIPE_NAME);
                Recipe.updateRecipe(recipe,DatabaseService.getConnection(true),Recipe.UPDATE_INGREDIENTS);
                Recipe.updateRecipe(recipe,DatabaseService.getConnection(true),Recipe.UPDATE_INSTRUCTIONS);
                Recipe.updateRecipe(recipe,DatabaseService.getConnection(true),Recipe.UPDATE_ALLERGIES);

                logger.info("Updated successfully!\n");
            }

        }
        catch (SQLException e){
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);

        }


    }


    public static void returnAllBestSellingProductsInEachStoreScreen(){
        ViewService service = new ViewService();
        List<ViewService.Product> products;
        try {
            products = service.getBestSellingProducts(DatabaseService.getConnection(true));

            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("%-25s %-30s %-25s %-20s",
                        "ID", ITEM_NAME, STORE_NAME, "Quantity Sold"));

                for (ViewService.Product product : products) {
                    Store store = Store.getStoreById(product.getStoreId(), DatabaseService.getConnection(true));
                    if (logger.isLoggable(Level.INFO)) {
                        assert store != null;
                        logger.info(String.format("%-25s %-30s %-25s %-20s",
                                product.getProductId(),
                                product.getProductName(),
                                store.getStoreName(),
                                product.getTotalQuantitySold()));
                    }
                }
            }

        }
        catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
        }
    }


    public static void manageBeneficiaryUsersScreen(){
        List <User> users;
        try {
            users = User.getUsersByFlag(2,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("%-25s %-15s %-20s",
                    "Email", "Password", "City"));

            for (User user : users) {
                logger.info(String.format("%-25s %-15s %-20s",
                        user.getEmail(),
                        user.getPassword(),
                        user.getCity()));
            }
        }
        logger.info("""
                Please enter your choice:
                1. Edit user
                2. Delete user
                3. Return
                """);
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> adminEditBeneficiaryUser();
            case 2 -> deleteAccount();
            case 3 -> {
                logOut();
                return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        manageBeneficiaryUsersScreen();
        
    }
    public static void adminEditBeneficiaryUser(){
        logger.info(ENTER_USER_EMAIL);
        String email = scanner.nextLine();
        editUserProfile(email);
    }
    
    
    
    
    public static void returnStatisticsOnRegisteredUsersByCityScreen(){
        ViewService service = new ViewService();
        List <ViewService.UserByCity> usersByCity;
        try {
            usersByCity = service.getUsersByCity(DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if(!usersByCity.isEmpty()){
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("%-20s %-15s",
                        "City", "User Count"));
            }
            if (logger.isLoggable(Level.INFO)) {
                for (ViewService.UserByCity user : usersByCity) {
                    logger.info(String.format("%-20s %-15s",
                            user.getCityName(), (user.getUserCount())));
                }
            }
        }
    }

    public static void manageAccountPreferences(){
        List <User> users;
        try {
            users = User.getUsersByFlag(1,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("%-25s %-30s %-15s %-20s",
                    "Email", "Role", "Password", "City"));
            for (User user : users) {
                logger.info(String.format("%-25s %-30s %-15s %-20s",
                        user.getEmail(),
                        user.getRole(),
                        user.getPassword(),
                        user.getCity()));
            }
        }
        logger.info("""
                Please enter your choice:
                1. Edit user
                2. Delete user
                3. Return
                """);
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> adminEditUser();
            case 2 -> deleteAccount();
            case 3 -> {
                return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        manageAccountPreferences();
    }

    public static void adminEditUser(){
        logger.info(ENTER_USER_EMAIL);
        String email = scanner.nextLine();
        editAccountPreferences(email);
    }

    public static void deleteAccount(){
        boolean deleted;
        logger.info(ENTER_USER_EMAIL);
        String email = scanner.nextLine();
        try {
           deleted =  User.deleteUser(email,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if (deleted) logger.info(DELETED_SUCCESSFULLY);
        else logger.warning("Deletion failed!");
    }

    private static void logOut(){
        order = null;
        userAuthService = new UserAuthService();
        userRegister();
    }

    private static void signIn() {

        logger.info("Enter your email: ");
        String email = scanner.nextLine();
        logger.info("Enter your password: ");
        String password = scanner.nextLine();
        try {
            userAuthService.login(email,password, DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning("Login failed!");
            return;
        }
        if (userAuthService.isLoggedIn())
            logger.info("You are logged in!");
        else{
            logger.warning("Invalid email or password.!");
            return;
        }
        if(userAuthService.getLoggedInUser().isAdmin()){whichType = 1;}
        else if (userAuthService.getLoggedInUser().isStoreOwner()) {whichType = 2;}
        else if (userAuthService.getLoggedInUser().isRawMaterialSupplier()) {whichType = 3;}
        else if (userAuthService.getLoggedInUser().isBeneficiaryUser()) {whichType = 4;}
    }

    private static void signUp() {
        int choice = 0;
        logger.info("Enter your email: ");
        String email = scanner.nextLine();
        logger.info("Enter your password: ");
        String password = scanner.nextLine();
        String role;
        logger.info("""
                    Chose your role:
                    1. Admin
                    2. Store Owner
                    3. Raw Material Supplier
                    4. Beneficiary User""");
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            scanner.nextLine();
        }
        whichType = choice;
        switch (choice) {
            case 1 -> role = "admin";
            case 2 -> role = "store_owner";
            case 3 -> role = "raw_material_supplier";
            case 4 -> role = "beneficiary_user";
            default -> {
                System.exit(0);
                whichType = 0;
                logger.warning("Invalid info please sign up again");
                return;
            }
    }
        logger.info("Enter your city: ");
        String city = scanner.nextLine();
        try {
            userAuthService.signUp(email,password,role,city,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
            return;
        }
        if (userAuthService.getLoggedInUser().isBeneficiaryUser()){
                logger.info("Enter your first name: ");
                String firstName = scanner.nextLine();
                logger.info("Enter your last name: ");
                String lastName = scanner.nextLine();
                logger.info("Enter your phone number: ");
                String phoneNumber = scanner.nextLine();
                logger.info("Enter your address: ");
                String address = scanner.nextLine();

                UserProfile userProfile = new UserProfile(email,firstName,lastName,phoneNumber,address);
                try {
                    UserProfile.createUserProfile(userProfile,DatabaseService.getConnection(true));
                } catch (SQLException e) {
                    logger.warning("invalid info");
                }
            }

            else if (userAuthService.getLoggedInUser().isStoreOwner() || userAuthService.getLoggedInUser().isRawMaterialSupplier()){
                logger.info("Enter Store Name: ");
                String storeName = scanner.nextLine();
                logger.info("Enter Business Info: ");
                String storeInfo = scanner.nextLine();
                try {
                    Store store = new Store(email,storeName,storeInfo);
                    Store.createStore(store,DatabaseService.getConnection(true));
                } catch (SQLException e) {
                    logger.warning(SOMETHING_WENT_WRONG_MESSAGE);
                }
            }
    }



    public static class ColoredFormatter extends Formatter {
        // ANSI escape codes for colors
        public static final String RESET = "\u001B[0m";
        public static final String BRIGHT_WHITE = "\u001B[97m";  // Bright white color
        public static final String RED = "\u001B[31m";  // Red color for warnings

        @Override
        public String format(LogRecord logRecord) {
            String color;
            // Apply color based on log level
            if (logRecord.getLevel().intValue() >= Level.WARNING.intValue()) {
                color = RED;  // Use red for WARNING and SEVERE
            } else {
                color = BRIGHT_WHITE;  // Use bright white for other levels
            }
            return color // Set the appropriate color
                    + formatMessage(logRecord) // Log message
                    + RESET // Reset color
                    + System.lineSeparator(); // New line
        }
    }
}