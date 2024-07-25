CREATE TABLE Users (
                       email VARCHAR(100) PRIMARY KEY,
                       password VARCHAR(255) NOT NULL,
                       role ENUM('admin', 'store_owner', 'raw_material_supplier', 'beneficiary_user') NOT NULL,
                       city VARCHAR(50),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE UserProfiles (
                              email VARCHAR(100),
                              first_name VARCHAR(50),
                              last_name VARCHAR(50),
                              phone VARCHAR(20),
                              address TEXT,
                              PRIMARY KEY (email),
                              FOREIGN KEY (email) REFERENCES Users(email) ON DELETE CASCADE
);

CREATE TABLE Stores (
                        store_id INT AUTO_INCREMENT PRIMARY KEY,
                        owner_email VARCHAR(100),
                        store_name VARCHAR(100),
                        business_info TEXT,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (owner_email) REFERENCES Users(email) ON DELETE CASCADE
);

CREATE TABLE Products (
                          product_id INT AUTO_INCREMENT PRIMARY KEY,
                          store_id INT,
                          product_name VARCHAR(100),
                          description TEXT,
                          price DECIMAL(10, 2),
                          stock INT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (store_id) REFERENCES Stores(store_id) ON DELETE CASCADE
);

CREATE TABLE Orders (
                        order_id INT AUTO_INCREMENT PRIMARY KEY,
                        user_email VARCHAR(100),
                        store_id INT,
                        order_status ENUM('pending', 'processed', 'shipped', 'delivered', 'cancelled') DEFAULT 'pending',
                        total_amount DECIMAL(10, 2),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (user_email) REFERENCES Users(email) ON DELETE CASCADE,
                        FOREIGN KEY (store_id) REFERENCES Stores(store_id) ON DELETE CASCADE
);

CREATE TABLE OrderItems (
                            order_item_id INT AUTO_INCREMENT PRIMARY KEY,
                            order_id INT,
                            product_id INT,
                            quantity INT,
                            price DECIMAL(10, 2),
                            FOREIGN KEY (order_id) REFERENCES Orders(order_id) ON DELETE CASCADE,
                            FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE CASCADE
);

CREATE TABLE Feedback (
                          feedback_id INT AUTO_INCREMENT PRIMARY KEY,
                          user_email VARCHAR(100),
                          product_id INT,
                          rating INT CHECK (rating BETWEEN 1 AND 5),
                          comment TEXT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_email) REFERENCES Users(email) ON DELETE CASCADE,
                          FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE CASCADE
);

CREATE TABLE Recipes (
                         recipe_id INT AUTO_INCREMENT PRIMARY KEY,
                         user_email VARCHAR(100),
                         recipe_name VARCHAR(100),
                         ingredients TEXT,
                         instructions TEXT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (user_email) REFERENCES Users(email) ON DELETE CASCADE
);

CREATE TABLE Messages (
                          message_id INT AUTO_INCREMENT PRIMARY KEY,
                          sender_email VARCHAR(100),
                          receiver_email VARCHAR(100),
                          content TEXT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (sender_email) REFERENCES Users(email) ON DELETE CASCADE,
                          FOREIGN KEY (receiver_email) REFERENCES Users(email) ON DELETE CASCADE
);

CREATE TABLE Notifications (
                               notification_id INT AUTO_INCREMENT PRIMARY KEY,
                               user_email VARCHAR(100),
                               message TEXT,
                               is_read BOOLEAN DEFAULT FALSE,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (user_email) REFERENCES Users(email) ON DELETE CASCADE
);

CREATE TABLE Discounts (
                           discount_id INT AUTO_INCREMENT PRIMARY KEY,
                           product_id INT,
                           discount_percentage DECIMAL(5, 2),
                           start_date DATE,
                           end_date DATE,
                           FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE CASCADE
);

CREATE TABLE Reports (
                         report_id INT AUTO_INCREMENT PRIMARY KEY,
                         report_type ENUM('financial', 'sales', 'user_statistics') NOT NULL,
                         content TEXT,
                         generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE VIEW BestSellingProducts AS
SELECT
    p.product_id,
    p.product_name,
    p.store_id,
    SUM(oi.quantity) AS total_quantity_sold
FROM
    OrderItems oi
        JOIN
    Products p ON oi.product_id = p.product_id
GROUP BY
    p.product_id, p.product_name, p.store_id
ORDER BY
    total_quantity_sold DESC;

CREATE VIEW UsersByCity AS
SELECT
    city,
    COUNT(email) AS user_count
FROM
    Users
GROUP BY
    city;

CREATE VIEW StoreProfits AS
SELECT
    s.store_id,
    s.store_name,
    SUM(oi.price * oi.quantity) AS total_profit
FROM
    OrderItems oi
        JOIN
    Orders o ON oi.order_id = o.order_id
        JOIN
    Stores s ON o.store_id = s.store_id
GROUP BY
    s.store_id, s.store_name;
