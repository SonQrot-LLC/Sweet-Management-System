package sweet.management;

import sweet.management.entities.Store;
import sweet.management.entities.UserProfile;
import sweet.management.services.DatabaseService;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    static Logger logger;
    static Scanner scanner = new Scanner(System.in);
    static  UserAuthService userAuthService = new UserAuthService();
    private static int whichType = 0;
    public static final int ADMIN_TYPE = 1;
    public static final int STORE_OWNER_TYPE = 2;
    public static final int SUPPLIER_TYPE = 3;
    public static final int BENEFICIARY_USER_TYPE = 4;
    private static final String INVALID_CHOICE_MESSAGE = "Invalid choice! Please try again.";


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
                2. Publish a recipe
                3. Browse recipes
                4. Communication
                5. Account preference
                6. Exit
                """);
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> viewProductsScreen();
            case 2 -> publichRecipesScreen();
            case 3 -> browseRescipesScreen();
            case 4 -> communicationScreen();
            case 5 -> accountPrefrencesScreen();
            case 6 -> {
                System.exit(0);
                return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        beneficiaryUserScreen();
    }

    private static void viewProductsScreen() {
    }

    private static void supplierScreen() {
        logger.info("Supplier Screen");
        logger.info("""
                Please enter your choice:
                1. Manage Products
                2. Discount Management
                3. Monitor Sales
                4. Store preference
                5. Communication
                6. Account preference
                7. Order Management
                8. Exit""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> manageProductsScreen();
            case 2 -> discountManagementScreen();
            case 3 -> monitorSalesScreen();
            case 4 -> storePrefrencesScreen();
            case 5 -> communicationScreen();
            case 6 -> accountPrefrencesScreen();
            case 7 -> orderManagementScreen();
            case 8 -> {
                System.exit(0);
                return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        supplierScreen();
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
                8. Exit""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> manageProductsScreen();
            case 2 -> discountManagementScreen();
            case 3 -> monitorSalesScreen();
            case 4 -> storePrefrencesScreen();
            case 5 -> communicationScreen();
            case 6 -> accountPrefrencesScreen();
            case 7 -> orderManagementScreen();
            case 8 -> {
                System.exit(0);
                return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        storeOwnerScreen();
    }

    private static void adminScreen() {
        logger.info("Admin Screen");
        logger.info("""
                Please enter your choice:
                1. Manage Store Owners
                2. Manage Suppliers
                3. Manage Beneficiary users
                4. Monitor Profits
                5. Generate a financial report
                6. Return all best selling products in each store
                7. Return statistics on registered users by city
                8. Manage posts and recipes
                9. Manage feedback
                10. Account preference
                11. Exit
                """);
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> manageStoreOwnersScreen();
            case 2 -> manageSupliersScreen();
            case 3 -> manageBeneficiaryUsersScreen();
            case 4 -> monitorProfitsScreen();
            case 5 -> generateReportScreen();
            case 6 -> returnAllBestSellingProductsInEachStoreScreen();
            case 7 -> returnStatisticsOnRegisteredUsersByCityScreen();
            case 8 -> managePostsAndRecipesScreen();
            case 9 -> manageFeedbackScreen();
            case 10 -> accountPreferancesScreen();
            case 11 -> {
                System.exit(0);
                return;
            }
            default -> logger.warning(INVALID_CHOICE_MESSAGE);
        }
        adminScreen();
    }

    private static void signIn() {

        logger.info("Enter your email: ");
        String email = scanner.nextLine();
        logger.info("Enter your password: ");
        String password = scanner.nextLine();
        userAuthService.login(email,password, DatabaseService.getConnection(true));
        if (userAuthService.isLoggedIn())
            logger.info("You are logged in!");
        else{
            logger.warning("Invalid email or password.!");
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
            userAuthService.signUp(email,password,role,city,DatabaseService.getConnection(true));
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
                Store store = new Store(email,storeName,storeInfo);
                try {
                    Store.createStore(store,DatabaseService.getConnection(true));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
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