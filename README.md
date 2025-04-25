# Sweet Management System

Sweet Management System is a Java-based application designed to manage sweet stores, products, orders, feedback, recipes, and user accounts. It supports multiple user roles including Admin, Store Owner, Raw Material Supplier, and Beneficiary User, providing a full suite of features for store management, sales tracking, communication, and more.

## Features

- **User Authentication & Roles:**

  - Admin, Store Owner, Raw Material Supplier, Beneficiary User
  - Sign up, login, and role-based access

- **Store & Product Management:**

  - Add, edit, delete products/materials
  - Manage store information and preferences
  - Discount management and suggestions for expiring products

- **Order Management:**

  - Place, view, update, and cancel orders
  - Track order status and order details

- **Feedback & Recipes:**

  - Add/view feedback for products
  - Publish and browse recipes
  - Admin management of feedback and recipes

- **Communication & Notifications:**

  - Send and receive messages between users
  - Notification system for special requests and updates

- **Sales & Profits Monitoring:**
  - View best-selling products per store
  - Monitor store profits and user statistics by city

## Technologies Used

- Java (JDK 17+ recommended)
- JDBC for database connectivity
- MySQL/MariaDB (see SQL schema)
- JUnit & Cucumber for testing
- JavaMail for notifications (optional)
- Logging via `java.util.logging`

## Project Structure

```
src/
  main/
    java/sweet/management/         # Main application logic
    java/sweet/management/entities # Entity classes (Product, Store, User, etc.)
    java/sweet/management/services # Database and view services
    resources/                     # SQL schema and resources
  test/
    java/sweet/management/         # Cucumber step definitions and tests
```

## Database Setup

1. **Install MySQL/MariaDB** and create a database named `sweetmanagementsystem`.
2. **Import the schema:**

   - Use the provided [`src/main/resources/SweetManagementSystem.sql`](src/main/resources/SweetManagementSystem.sql) file to create tables, views, triggers, and insert sample data.
   - Example:
     ```
     mysql -u youruser -p sweetmanagementsystem < src/main/resources/SweetManagementSystem.sql
     ```

3. **Configure Database Connection:**
   - Update your database credentials in `DatabaseService.java` if needed.

## Running the Application

1. **Build the project** using your preferred IDE or with Maven/Gradle.
2. **Run the Main class:**
   - Entry point: `sweet.management.Main`
   - The application is console-based and will prompt for user actions.

## Usage

- **Sign Up / Login:**  
  Start the application and follow the prompts to sign up or log in as a user.
- **Role-Based Menus:**  
  After login, menus and options are shown based on your user role.
- **Store/Product Management:**  
  Store owners and suppliers can manage products/materials, discounts, and view sales.
- **Order & Feedback:**  
  Beneficiary users can place orders, view products, and leave feedback.
- **Admin Functions:**  
  Admins can manage users, monitor profits, and oversee recipes and feedback.

## Testing

- **Unit and Integration Tests:**  
  Cucumber step definitions and JUnit assertions are provided in `src/test/java/sweet/management/`.
- **Run tests** using your IDE or build tool.

## Extending & Customizing

- **Add new features** by extending entity and service classes.
- **Modify SQL schema** as needed for new requirements.
- **UI/UX:**  
  The current version is console-based; you can build a GUI or web interface on top of the existing logic.

## Contributing

Pull requests and suggestions are welcome! Please open an issue to discuss changes or enhancements.

## License

This project is provided for educational purposes.

---
