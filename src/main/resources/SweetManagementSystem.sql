-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 13, 2024 at 09:57 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sweetmanagementsystem`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `bestsellingproducts`
-- (See below for the actual view)
--
CREATE TABLE `bestsellingproducts` (
                                       `product_id` int(11)
    ,`product_name` varchar(100)
    ,`store_id` int(11)
    ,`total_quantity_sold` decimal(32,0)
);

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
                            `feedback_id` int(11) NOT NULL,
                            `user_email` varchar(100) DEFAULT NULL,
                            `product_id` int(11) DEFAULT NULL,
                            `rating` int(11) DEFAULT NULL CHECK (`rating` between 1 and 5),
                            `comment` text DEFAULT NULL,
                            `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
                            `message_id` int(11) NOT NULL,
                            `sender_email` varchar(100) DEFAULT NULL,
                            `receiver_email` varchar(100) DEFAULT NULL,
                            `content` text DEFAULT NULL,
                            `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`message_id`, `sender_email`, `receiver_email`, `content`, `created_at`) VALUES
                                                                                                     (1, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-08 19:24:36'),
                                                                                                     (2, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-08 19:24:36'),
                                                                                                     (3, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-08 19:24:36'),
                                                                                                     (4, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-08 19:24:36'),
                                                                                                     (5, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-08 19:24:36'),
                                                                                                     (6, 'order.user@gmail.com', 'salam@hawa.com', 'test1', '2024-08-13 10:19:44'),
                                                                                                     (7, 'supplier@gmail.com', 'salam@hawa.com', 'test2', '2024-08-13 10:20:04'),
                                                                                                     (8, 'salam@hawa.com', 'order.user@gmail.com', 'test3', '2024-08-13 10:20:41'),
                                                                                                     (9, 'salam@hawa.com', 'supplier@gmail.com', 'test4', '2024-08-13 10:36:39'),
                                                                                                     (10, 'salam@hawa.com', 'supplier@gmail.com', 'test5', '2024-08-13 10:38:30'),
                                                                                                     (11, 'supplier@gmail.com', 'salam@hawa.com', 'How can i assist you?', '2024-08-13 12:50:19'),
                                                                                                     (12, 'supplier@gmail.com', 'salam@hawa.com', 'hello?', '2024-08-13 12:51:12'),
                                                                                                     (14, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-13 18:26:33'),
                                                                                                     (15, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-13 18:26:33'),
                                                                                                     (16, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-13 18:26:34'),
                                                                                                     (17, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-13 18:26:34'),
                                                                                                     (18, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-13 18:26:34'),
                                                                                                     (19, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-13 18:27:17'),
                                                                                                     (20, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-13 18:27:17'),
                                                                                                     (21, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-13 18:27:17'),
                                                                                                     (22, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-13 18:27:17'),
                                                                                                     (23, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-13 18:27:17'),
                                                                                                     (24, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-13 18:28:39'),
                                                                                                     (25, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-13 18:28:39'),
                                                                                                     (26, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-13 18:28:39'),
                                                                                                     (27, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-13 18:28:39'),
                                                                                                     (28, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-13 18:28:39'),
                                                                                                     (29, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-13 18:30:05'),
                                                                                                     (30, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-13 18:30:05'),
                                                                                                     (31, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-13 18:30:05'),
                                                                                                     (32, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-13 18:30:05'),
                                                                                                     (33, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-13 18:30:05'),
                                                                                                     (34, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-13 18:32:17'),
                                                                                                     (35, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-13 18:32:17'),
                                                                                                     (36, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-13 18:32:17'),
                                                                                                     (37, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-13 18:32:17'),
                                                                                                     (38, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-13 18:32:17'),
                                                                                                     (39, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-13 18:32:39'),
                                                                                                     (40, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-13 18:32:39'),
                                                                                                     (41, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-13 18:32:39'),
                                                                                                     (42, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-13 18:32:39'),
                                                                                                     (43, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-13 18:32:39'),
                                                                                                     (44, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-13 18:33:11'),
                                                                                                     (45, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-13 18:33:11'),
                                                                                                     (46, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-13 18:33:11'),
                                                                                                     (47, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-13 18:33:11'),
                                                                                                     (48, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-13 18:33:11'),
                                                                                                     (49, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-13 18:33:21'),
                                                                                                     (50, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-13 18:33:21'),
                                                                                                     (51, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-13 18:33:21'),
                                                                                                     (52, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-13 18:33:21'),
                                                                                                     (53, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-13 18:33:21'),
                                                                                                     (54, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-13 18:37:13'),
                                                                                                     (55, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-13 18:37:13'),
                                                                                                     (56, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-13 18:37:13'),
                                                                                                     (57, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-13 18:37:13'),
                                                                                                     (58, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-13 18:37:13'),
                                                                                                     (59, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-13 18:38:05'),
                                                                                                     (60, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-13 18:38:05'),
                                                                                                     (61, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-13 18:38:05'),
                                                                                                     (62, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-13 18:38:05'),
                                                                                                     (63, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-13 18:38:05'),
                                                                                                     (64, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-13 18:39:54'),
                                                                                                     (65, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-13 18:39:54'),
                                                                                                     (66, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-13 18:39:54'),
                                                                                                     (67, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-13 18:39:54'),
                                                                                                     (68, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-13 18:39:54'),
                                                                                                     (69, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-13 18:40:55'),
                                                                                                     (70, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-13 18:40:55'),
                                                                                                     (71, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-13 18:40:55'),
                                                                                                     (72, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-13 18:40:55'),
                                                                                                     (73, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-13 18:40:55'),
                                                                                                     (74, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-13 18:41:06'),
                                                                                                     (75, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-13 18:41:06'),
                                                                                                     (76, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-13 18:41:06'),
                                                                                                     (77, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-13 18:41:06'),
                                                                                                     (78, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-13 18:41:06'),
                                                                                                     (79, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-13 19:03:30'),
                                                                                                     (80, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-13 19:03:30'),
                                                                                                     (81, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-13 19:03:30'),
                                                                                                     (82, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-13 19:03:30'),
                                                                                                     (83, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-13 19:03:30'),
                                                                                                     (84, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-13 19:56:37'),
                                                                                                     (85, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-13 19:56:37'),
                                                                                                     (86, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-13 19:56:37'),
                                                                                                     (87, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-13 19:56:37'),
                                                                                                     (88, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-13 19:56:37');

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
                                 `notification_id` int(11) NOT NULL,
                                 `user_email` varchar(100) DEFAULT NULL,
                                 `message` text DEFAULT NULL,
                                 `is_read` tinyint(1) DEFAULT 0,
                                 `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orderitems`
--

CREATE TABLE `orderitems` (
                              `order_item_id` int(11) NOT NULL,
                              `order_id` int(11) DEFAULT NULL,
                              `product_id` int(11) DEFAULT NULL,
                              `quantity` int(11) DEFAULT NULL,
                              `price` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orderitems`
--

INSERT INTO `orderitems` (`order_item_id`, `order_id`, `product_id`, `quantity`, `price`) VALUES
                                                                                              (128, 1, 1, 3, 18.00),
                                                                                              (129, 1, 1, 3, 18.00),
                                                                                              (130, NULL, NULL, NULL, NULL),
                                                                                              (131, 1, 1, 3, 18.00);

--
-- Triggers `orderitems`
--
DELIMITER $$
CREATE TRIGGER `trg_order_items_change` AFTER INSERT ON `orderitems` FOR EACH ROW BEGIN
    -- Adjust stock for new item
    UPDATE products
    SET stock = stock - NEW.quantity
    WHERE product_id = NEW.product_id;

    -- Update total amount for the associated order
    UPDATE orders
    SET total_amount = (
        SELECT IFNULL(SUM(price * quantity), 0)
        FROM orderitems
        WHERE order_id = NEW.order_id
    )
    WHERE order_id = NEW.order_id;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_order_items_delete` AFTER DELETE ON `orderitems` FOR EACH ROW BEGIN
    -- Restore stock for deleted item
    UPDATE products
    SET stock = stock + OLD.quantity
    WHERE product_id = OLD.product_id;

    -- Update total amount for the associated order
    UPDATE orders
    SET total_amount = (
        SELECT IFNULL(SUM(price * quantity), 0)
        FROM orderitems
        WHERE order_id = OLD.order_id
    )
    WHERE order_id = OLD.order_id;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_order_items_update` AFTER UPDATE ON `orderitems` FOR EACH ROW BEGIN
    -- Adjust stock for the product: restore old quantity and apply new quantity
    UPDATE products
    SET stock = stock + OLD.quantity - NEW.quantity
    WHERE product_id = OLD.product_id;

    -- Update total amount for the associated order
    UPDATE orders
    SET total_amount = (
        SELECT IFNULL(SUM(price * quantity), 0)
        FROM orderitems
        WHERE order_id = NEW.order_id
    )
    WHERE order_id = NEW.order_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
                          `order_id` int(11) NOT NULL,
                          `user_email` varchar(100) DEFAULT NULL,
                          `store_id` int(11) DEFAULT NULL,
                          `order_status` enum('pending','processed','shipped','delivered','cancelled') DEFAULT 'pending',
                          `total_amount` decimal(10,2) DEFAULT NULL,
                          `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `user_email`, `store_id`, `order_status`, `total_amount`, `created_at`) VALUES
                                                                                                              (1, 'order.user@gmail.com', 2, 'processed', 162.00, '2024-08-01 14:20:16'),
                                                                                                              (2, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-01 14:20:34'),
                                                                                                              (3, 'order.user@gmail.com', 1, 'pending', 12.00, '2024-08-13 17:46:19'),
                                                                                                              (4, 'order.user@gmail.com', 2, 'processed', 0.00, '2024-08-13 18:26:34'),
                                                                                                              (5, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-13 19:03:31'),
                                                                                                              (6, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-13 19:56:37');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
                            `product_id` int(11) NOT NULL,
                            `store_id` int(11) DEFAULT NULL,
                            `product_name` varchar(100) DEFAULT NULL,
                            `description` text DEFAULT NULL,
                            `price` decimal(10,2) DEFAULT NULL,
                            `stock` int(11) DEFAULT NULL,
                            `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
                            `expiry_date` date DEFAULT NULL,
                            `discount` decimal(10,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `store_id`, `product_name`, `description`, `price`, `stock`, `created_at`, `expiry_date`, `discount`) VALUES
                                                                                                                                                (1, 1, 'chocolate cake', 'Dark chocolate cake', 7.00, 25, '2024-07-31 19:17:49', '2025-01-01', 0.00),
                                                                                                                                                (2, 1, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-07-31 20:43:51', '2026-10-07', 0.00),
                                                                                                                                                (3, 1, 'item1', 'item1', 5.00, 115, '2024-07-31 21:22:11', '2024-12-12', 0.00),
                                                                                                                                                (4, 1, 'item2', 'item2', 31.00, 200, '2024-07-31 21:22:11', '2024-09-20', 10.00),
                                                                                                                                                (5, 1, 'item3', 'item3', 32.00, 124, '2024-07-31 21:22:11', '2024-09-03', 0.00),
                                                                                                                                                (6, 1, 'item4', 'item4', 53.00, 21, '2024-07-31 21:22:51', '2024-08-15', 0.00),
                                                                                                                                                (7, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 07:05:42', '2024-12-28', 0.00),
                                                                                                                                                (8, 4, 'Salt', 'Good salt', 4.00, 183, '2024-08-13 12:31:48', '2024-12-12', 0.00),
                                                                                                                                                (9, 4, 'Milk', 'Good milk', 12.00, 78, '2024-08-13 12:37:05', '2024-08-30', 4.00),
                                                                                                                                                (10, 4, 'Sugar', 'Good sugar ', 3.00, 123, '2024-08-13 12:38:11', '2024-09-09', 0.00),
                                                                                                                                                (11, 4, 'Vanilla', 'Good vanilla', 5.00, 231, '2024-08-13 12:38:52', '2024-10-10', 3.00);

-- --------------------------------------------------------

--
-- Table structure for table `recipes`
--

CREATE TABLE `recipes` (
                           `recipe_id` int(11) NOT NULL,
                           `user_email` varchar(100) DEFAULT NULL,
                           `recipe_name` varchar(100) DEFAULT NULL,
                           `ingredients` text DEFAULT NULL,
                           `instructions` text DEFAULT NULL,
                           `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
                           `allergies` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `recipes`
--

INSERT INTO `recipes` (`recipe_id`, `user_email`, `recipe_name`, `ingredients`, `instructions`, `created_at`, `allergies`) VALUES
                                                                                                                               (1, 'order.user@gmail.com', 'Cake', 'Milk and eggs', 'Bake', '2024-08-11 14:11:17', 'Eggs'),
                                                                                                                               (2, 'feedbacktest@gmail.com', 'Cookies', 'Chocolate, eggs and milk', 'Cook', '2024-08-11 14:12:42', 'Eggs'),
                                                                                                                               (3, 'order.user@gmail.com', 'Oreo Donuts', 'Oreo and eggs', 'Cool', '2024-08-11 14:17:07', 'Eggs'),
                                                                                                                               (4, 'feedbacktest@gmail.com', 'Vanilla Donuts', 'Vanilla and Milk', 'Cool', '2024-08-11 14:17:41', 'cows\' milk'),
                                                                                                                               (16, 'admin@gmail.com', 'test', 'test', 'test', '2024-08-13 17:09:35', 'test'),
                                                                                                                               (17, 'feedbacktest@gmail.com', 'Cupcakes', 'Wheat and Laveva', 'Bake for 45 minutes', '2024-08-13 18:33:12', 'wheat');

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports` (
                           `report_id` int(11) NOT NULL,
                           `report_type` enum('financial','sales','user_statistics') NOT NULL,
                           `content` text DEFAULT NULL,
                           `generated_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Stand-in structure for view `storeprofits`
-- (See below for the actual view)
--
CREATE TABLE `storeprofits` (
                                `store_id` int(11)
    ,`store_name` varchar(100)
    ,`total_profit` decimal(42,2)
);

-- --------------------------------------------------------

--
-- Table structure for table `stores`
--

CREATE TABLE `stores` (
                          `store_id` int(11) NOT NULL,
                          `owner_email` varchar(100) DEFAULT NULL,
                          `store_name` varchar(100) DEFAULT NULL,
                          `business_info` text DEFAULT NULL,
                          `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `stores`
--

INSERT INTO `stores` (`store_id`, `owner_email`, `store_name`, `business_info`, `created_at`) VALUES
                                                                                                  (1, 'salam@hawa.com', 'Test', 'Test', '2024-08-01 11:00:14'),
                                                                                                  (2, 'owner@gmail.com', 'Test store', 'Test store for database', '2024-07-31 19:08:31'),
                                                                                                  (4, 'supplier@gmail.com', 'Supplier store test', 'test', '2024-08-13 12:03:48'),
                                                                                                  (5, 'mahmood@outlook.com', 'to be deleted', 'testing deleting function', '2024-08-13 19:56:36');

-- --------------------------------------------------------

--
-- Table structure for table `userprofiles`
--

CREATE TABLE `userprofiles` (
                                `email` varchar(100) NOT NULL,
                                `first_name` varchar(50) DEFAULT NULL,
                                `last_name` varchar(50) DEFAULT NULL,
                                `phone` varchar(20) DEFAULT NULL,
                                `address` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `userprofiles`
--

INSERT INTO `userprofiles` (`email`, `first_name`, `last_name`, `phone`, `address`) VALUES
    ('ahmad123@gmail.com', 'test1', 'test2', '123123123', 'anabta');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
                         `email` varchar(100) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `role` enum('admin','store_owner','raw_material_supplier','beneficiary_user') NOT NULL,
                         `city` varchar(50) DEFAULT NULL,
                         `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`email`, `password`, `role`, `city`, `created_at`) VALUES
                                                                            ('admin@gmail.com', '123', 'admin', 'Jenin', '2024-08-11 15:01:49'),
                                                                            ('ahmad123@gmail.com', '777', 'beneficiary_user', 'Japan', '2024-08-13 19:56:36'),
                                                                            ('feedbacktest@gmail.com', '321', 'beneficiary_user', 'Nablus', '2024-08-09 20:43:41'),
                                                                            ('mahmood@gmail.com', '777', 'admin', 'Jordan', '2024-07-27 09:08:57'),
                                                                            ('mahmood@outlook.com', '777', 'store_owner', 'Khalil', '2024-07-27 16:31:27'),
                                                                            ('momanani20011@gmail.com', '777', 'admin', 'Tulkarm', '2024-08-13 18:37:09'),
                                                                            ('momanani2017@gmail.com', '777', 'admin', 'Tulkarem', '2024-07-26 16:51:33'),
                                                                            ('n.hamfallah@gmail.com', '777', 'admin', 'KATEEBEH', '2024-07-25 19:32:07'),
                                                                            ('order.store@gmail.com', '567', 'store_owner', 'Nablus', '2024-08-01 09:41:33'),
                                                                            ('order.user@gmail.com', '321', 'beneficiary_user', 'Hebron', '2024-08-01 09:41:33'),
                                                                            ('owner@gmail.com', '123', 'store_owner', 'Tulkarem', '2024-07-31 19:07:47'),
                                                                            ('salam@hawa.com', '222', 'store_owner', 'Nablus', '2024-07-25 19:05:38'),
                                                                            ('supplier@gmail.com', '123', 'raw_material_supplier', 'Jenin', '2024-08-07 16:00:31'),
                                                                            ('toostronkm@gmail.com ', '777', 'beneficiary_user', 'jerusalem', '2024-07-25 18:18:13');

-- --------------------------------------------------------

--
-- Stand-in structure for view `usersbycity`
-- (See below for the actual view)
--
CREATE TABLE `usersbycity` (
                               `city` varchar(50)
    ,`user_count` bigint(21)
);

-- --------------------------------------------------------

--
-- Structure for view `bestsellingproducts`
--
DROP TABLE IF EXISTS `bestsellingproducts`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bestsellingproducts`  AS SELECT `p`.`product_id` AS `product_id`, `p`.`product_name` AS `product_name`, `p`.`store_id` AS `store_id`, `t`.`total_quantity_sold` AS `total_quantity_sold` FROM (`products` `p` join (select `p`.`store_id` AS `store_id`,`oi`.`product_id` AS `product_id`,sum(`oi`.`quantity`) AS `total_quantity_sold`,rank() over ( partition by `p`.`store_id` order by sum(`oi`.`quantity`) desc) AS `rank` from (`orderitems` `oi` join `products` `p` on(`oi`.`product_id` = `p`.`product_id`)) group by `p`.`store_id`,`oi`.`product_id`) `t` on(`p`.`product_id` = `t`.`product_id` and `p`.`store_id` = `t`.`store_id`)) WHERE `t`.`rank` = 1 ;

-- --------------------------------------------------------

--
-- Structure for view `storeprofits`
--
DROP TABLE IF EXISTS `storeprofits`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `storeprofits`  AS SELECT `s`.`store_id` AS `store_id`, `s`.`store_name` AS `store_name`, sum(`oi`.`price` * `oi`.`quantity`) AS `total_profit` FROM ((`orderitems` `oi` join `orders` `o` on(`oi`.`order_id` = `o`.`order_id`)) join `stores` `s` on(`o`.`store_id` = `s`.`store_id`)) GROUP BY `s`.`store_id`, `s`.`store_name` ;

-- --------------------------------------------------------

--
-- Structure for view `usersbycity`
--
DROP TABLE IF EXISTS `usersbycity`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `usersbycity`  AS SELECT `users`.`city` AS `city`, count(`users`.`email`) AS `user_count` FROM `users` GROUP BY `users`.`city` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
    ADD PRIMARY KEY (`feedback_id`),
    ADD KEY `user_email` (`user_email`),
    ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
    ADD PRIMARY KEY (`message_id`),
    ADD KEY `sender_email` (`sender_email`),
    ADD KEY `receiver_email` (`receiver_email`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
    ADD PRIMARY KEY (`notification_id`),
    ADD KEY `user_email` (`user_email`);

--
-- Indexes for table `orderitems`
--
ALTER TABLE `orderitems`
    ADD PRIMARY KEY (`order_item_id`),
    ADD KEY `order_id` (`order_id`),
    ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
    ADD PRIMARY KEY (`order_id`),
    ADD KEY `user_email` (`user_email`),
    ADD KEY `store_id` (`store_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
    ADD PRIMARY KEY (`product_id`),
    ADD KEY `store_id` (`store_id`);

--
-- Indexes for table `recipes`
--
ALTER TABLE `recipes`
    ADD PRIMARY KEY (`recipe_id`),
    ADD KEY `user_email` (`user_email`);

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
    ADD PRIMARY KEY (`report_id`);

--
-- Indexes for table `stores`
--
ALTER TABLE `stores`
    ADD PRIMARY KEY (`store_id`),
    ADD UNIQUE KEY `unique_owner_email` (`owner_email`);

--
-- Indexes for table `userprofiles`
--
ALTER TABLE `userprofiles`
    ADD PRIMARY KEY (`email`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
    MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
    MODIFY `notification_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orderitems`
--
ALTER TABLE `orderitems`
    MODIFY `order_item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=133;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
    MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `recipes`
--
ALTER TABLE `recipes`
    MODIFY `recipe_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
    MODIFY `report_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
    ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`user_email`) REFERENCES `users` (`email`) ON DELETE CASCADE,
    ADD CONSTRAINT `feedback_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `messages`
--
ALTER TABLE `messages`
    ADD CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`sender_email`) REFERENCES `users` (`email`) ON DELETE CASCADE,
    ADD CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`receiver_email`) REFERENCES `users` (`email`) ON DELETE CASCADE;

--
-- Constraints for table `notifications`
--
ALTER TABLE `notifications`
    ADD CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`user_email`) REFERENCES `users` (`email`) ON DELETE CASCADE;

--
-- Constraints for table `orderitems`
--
ALTER TABLE `orderitems`
    ADD CONSTRAINT `orderitems_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE,
    ADD CONSTRAINT `orderitems_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
    ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_email`) REFERENCES `users` (`email`) ON DELETE CASCADE,
    ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`) ON DELETE CASCADE;

--
-- Constraints for table `products`
--
ALTER TABLE `products`
    ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`) ON DELETE CASCADE;

--
-- Constraints for table `recipes`
--
ALTER TABLE `recipes`
    ADD CONSTRAINT `recipes_ibfk_1` FOREIGN KEY (`user_email`) REFERENCES `users` (`email`) ON DELETE CASCADE;

--
-- Constraints for table `stores`
--
ALTER TABLE `stores`
    ADD CONSTRAINT `stores_ibfk_1` FOREIGN KEY (`owner_email`) REFERENCES `users` (`email`) ON DELETE CASCADE;

--
-- Constraints for table `userprofiles`
--
ALTER TABLE `userprofiles`
    ADD CONSTRAINT `userprofiles_ibfk_1` FOREIGN KEY (`email`) REFERENCES `users` (`email`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
