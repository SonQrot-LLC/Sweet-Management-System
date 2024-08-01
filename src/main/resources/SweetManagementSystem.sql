-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 01, 2024 at 04:41 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `SweetManagementSystem`
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
-- Table structure for table `discounts`
--

CREATE TABLE `discounts` (
                             `discount_id` int(11) NOT NULL,
                             `product_id` int(11) DEFAULT NULL,
                             `discount_percentage` decimal(5,2) DEFAULT NULL,
                             `start_date` date DEFAULT NULL,
                             `end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
                                                                                                              (1, 'order.user@gmail.com', 2, 'processed', 0.00, '2024-08-01 14:20:16'),
                                                                                                              (2, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-01 14:20:34'),
                                                                                                              (3, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-01 14:20:55'),
                                                                                                              (4, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-01 14:21:19'),
                                                                                                              (5, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-01 14:21:50'),
                                                                                                              (6, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-01 14:22:46'),
                                                                                                              (7, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-01 14:22:54'),
                                                                                                              (8, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-01 14:25:48'),
                                                                                                              (9, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-01 14:26:35'),
                                                                                                              (10, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-01 14:38:37'),
                                                                                                              (11, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-01 14:38:54'),
                                                                                                              (12, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-01 14:39:44');

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
                            `expiry_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `store_id`, `product_name`, `description`, `price`, `stock`, `created_at`, `expiry_date`) VALUES
                                                                                                                                    (1, 1, 'chocolate cake', 'Dark chocolate cake', 7.00, 25, '2024-07-31 19:17:49', '2025-01-01'),
                                                                                                                                    (2, 1, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-07-31 20:43:51', '2026-10-07'),
                                                                                                                                    (3, 1, 'item1', 'item1', 5.00, 115, '2024-07-31 21:22:11', '2024-12-12'),
                                                                                                                                    (4, 1, 'item2', 'item2', 31.00, 200, '2024-07-31 21:22:11', '2024-09-20'),
                                                                                                                                    (5, 1, 'item3', 'item3', 32.00, 124, '2024-07-31 21:22:11', '2024-09-03'),
                                                                                                                                    (6, 1, 'item4', 'item4', 53.00, 21, '2024-07-31 21:22:51', '2024-08-15'),
                                                                                                                                    (7, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 07:05:42', '2024-12-28'),
                                                                                                                                    (8, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 07:06:16', '2024-12-28'),
                                                                                                                                    (9, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 07:10:34', '2024-12-28'),
                                                                                                                                    (10, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 09:34:56', '2024-12-28'),
                                                                                                                                    (11, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 09:43:49', '2024-12-28'),
                                                                                                                                    (12, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 10:25:02', '2024-12-28'),
                                                                                                                                    (13, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 10:31:18', '2024-12-28'),
                                                                                                                                    (14, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 10:35:54', '2024-12-28'),
                                                                                                                                    (15, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 10:38:55', '2024-12-28'),
                                                                                                                                    (16, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 10:39:56', '2024-12-28'),
                                                                                                                                    (17, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 10:42:06', '2024-12-28'),
                                                                                                                                    (18, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 10:42:37', '2024-12-28'),
                                                                                                                                    (19, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 10:43:36', '2024-12-28'),
                                                                                                                                    (20, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 10:44:15', '2024-12-28'),
                                                                                                                                    (21, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 10:46:58', '2024-12-28'),
                                                                                                                                    (22, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 10:47:02', '2024-12-28'),
                                                                                                                                    (23, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 10:47:53', '2024-12-28'),
                                                                                                                                    (24, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 10:48:29', '2024-12-28'),
                                                                                                                                    (25, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 10:48:35', '2024-12-28'),
                                                                                                                                    (26, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 10:52:46', '2024-12-28'),
                                                                                                                                    (27, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 10:58:09', '2024-12-28'),
                                                                                                                                    (28, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 11:02:12', '2024-12-28'),
                                                                                                                                    (29, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 11:03:15', '2024-12-28'),
                                                                                                                                    (30, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 11:11:23', '2024-12-28'),
                                                                                                                                    (31, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 11:14:37', '2024-12-28'),
                                                                                                                                    (32, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 11:15:35', '2024-12-28'),
                                                                                                                                    (33, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 11:16:18', '2024-12-28'),
                                                                                                                                    (34, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 11:24:02', '2024-12-28'),
                                                                                                                                    (35, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 11:24:43', '2024-12-28'),
                                                                                                                                    (36, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 11:25:28', '2024-12-28'),
                                                                                                                                    (37, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 11:28:29', '2024-12-28'),
                                                                                                                                    (38, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 11:49:50', '2024-12-28'),
                                                                                                                                    (39, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 11:50:32', '2024-12-28'),
                                                                                                                                    (40, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 11:51:08', '2024-12-28'),
                                                                                                                                    (41, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 11:53:37', '2024-12-28'),
                                                                                                                                    (42, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 11:56:11', '2024-12-28'),
                                                                                                                                    (43, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 11:56:25', '2024-12-28'),
                                                                                                                                    (44, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:13:48', '2024-12-28'),
                                                                                                                                    (45, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:19:55', '2024-12-28'),
                                                                                                                                    (46, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:20:47', '2024-12-28'),
                                                                                                                                    (47, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:21:58', '2024-12-28'),
                                                                                                                                    (48, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:29:22', '2024-12-28'),
                                                                                                                                    (49, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:31:14', '2024-12-28'),
                                                                                                                                    (50, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:31:58', '2024-12-28'),
                                                                                                                                    (51, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:32:53', '2024-12-28'),
                                                                                                                                    (52, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:34:14', '2024-12-28'),
                                                                                                                                    (53, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:38:46', '2024-12-28'),
                                                                                                                                    (54, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:40:20', '2024-12-28'),
                                                                                                                                    (55, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:40:48', '2024-12-28'),
                                                                                                                                    (56, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:41:06', '2024-12-28'),
                                                                                                                                    (57, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:41:40', '2024-12-28'),
                                                                                                                                    (58, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:41:57', '2024-12-28'),
                                                                                                                                    (59, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:42:40', '2024-12-28'),
                                                                                                                                    (60, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:43:42', '2024-12-28'),
                                                                                                                                    (61, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:43:55', '2024-12-28'),
                                                                                                                                    (62, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 13:47:45', '2024-12-28'),
                                                                                                                                    (63, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:16:53', '2024-12-28'),
                                                                                                                                    (64, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:17:08', '2024-12-28'),
                                                                                                                                    (65, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:17:56', '2024-12-28'),
                                                                                                                                    (66, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:18:01', '2024-12-28'),
                                                                                                                                    (67, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:18:19', '2024-12-28'),
                                                                                                                                    (68, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:19:30', '2024-12-28'),
                                                                                                                                    (69, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:19:47', '2024-12-28'),
                                                                                                                                    (70, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:19:52', '2024-12-28'),
                                                                                                                                    (71, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:20:16', '2024-12-28'),
                                                                                                                                    (72, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:20:35', '2024-12-28'),
                                                                                                                                    (73, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:20:55', '2024-12-28'),
                                                                                                                                    (74, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:21:19', '2024-12-28'),
                                                                                                                                    (75, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:21:50', '2024-12-28'),
                                                                                                                                    (76, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:22:46', '2024-12-28'),
                                                                                                                                    (77, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:22:54', '2024-12-28'),
                                                                                                                                    (78, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:25:48', '2024-12-28'),
                                                                                                                                    (79, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:26:35', '2024-12-28'),
                                                                                                                                    (80, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:38:37', '2024-12-28'),
                                                                                                                                    (81, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:38:54', '2024-12-28'),
                                                                                                                                    (82, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 14:39:44', '2024-12-28');

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
                           `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
                                                                                                  (1, 'salam@hawa.com', 'Test store', 'Test store for database', '2024-08-01 11:00:14'),
                                                                                                  (2, 'owner@gmail.com', 'Test store', 'Test store for database', '2024-07-31 19:08:31'),
                                                                                                  (3, 'mahmood@outlook.com', 'to be deleted', 'testing deleting function', '2024-08-01 14:39:43');

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
                                                                            ('ahmad123@gmail.com', '777', 'beneficiary_user', 'Japan', '2024-08-01 14:39:43'),
                                                                            ('mahmood@gmail.com', '777', 'admin', 'Jordan', '2024-07-27 09:08:57'),
                                                                            ('mahmood@outlook.com', '777', 'store_owner', 'Khalil', '2024-07-27 16:31:27'),
                                                                            ('momanani2017@gmail.com', '777', 'admin', 'Tulkarm', '2024-07-26 16:51:33'),
                                                                            ('n.hamfallah@gmail.com', '777', 'admin', 'KATEEBEH', '2024-07-25 19:32:07'),
                                                                            ('order.store@gmail.com', '567', 'store_owner', 'Nablus', '2024-08-01 09:41:33'),
                                                                            ('order.user@gmail.com', '321', 'beneficiary_user', 'Hebron', '2024-08-01 09:41:33'),
                                                                            ('owner@gmail.com', '123', 'store_owner', 'Tulkarem', '2024-07-31 19:07:47'),
                                                                            ('salam@hawa.com', '222', 'store_owner', 'Tulkarem', '2024-07-25 19:05:38'),
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

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `sweetmanagementsystem`.`bestsellingproducts`  AS SELECT `p`.`product_id` AS `product_id`, `p`.`product_name` AS `product_name`, `p`.`store_id` AS `store_id`, sum(`oi`.`quantity`) AS `total_quantity_sold` FROM (`sweetmanagementsystem`.`orderitems` `oi` join `sweetmanagementsystem`.`products` `p` on(`oi`.`product_id` = `p`.`product_id`)) GROUP BY `p`.`product_id`, `p`.`product_name`, `p`.`store_id` ORDER BY sum(`oi`.`quantity`) DESC ;

-- --------------------------------------------------------

--
-- Structure for view `storeprofits`
--
DROP TABLE IF EXISTS `storeprofits`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `sweetmanagementsystem`.`storeprofits`  AS SELECT `s`.`store_id` AS `store_id`, `s`.`store_name` AS `store_name`, sum(`oi`.`price` * `oi`.`quantity`) AS `total_profit` FROM ((`sweetmanagementsystem`.`orderitems` `oi` join `sweetmanagementsystem`.`orders` `o` on(`oi`.`order_id` = `o`.`order_id`)) join `sweetmanagementsystem`.`stores` `s` on(`o`.`store_id` = `s`.`store_id`)) GROUP BY `s`.`store_id`, `s`.`store_name` ;

-- --------------------------------------------------------

--
-- Structure for view `usersbycity`
--
DROP TABLE IF EXISTS `usersbycity`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `sweetmanagementsystem`.`usersbycity`  AS SELECT `sweetmanagementsystem`.`users`.`city` AS `city`, count(`sweetmanagementsystem`.`users`.`email`) AS `user_count` FROM `sweetmanagementsystem`.`users` GROUP BY `sweetmanagementsystem`.`users`.`city` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `discounts`
--
ALTER TABLE `discounts`
    ADD PRIMARY KEY (`discount_id`),
    ADD KEY `product_id` (`product_id`);

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
-- AUTO_INCREMENT for table `discounts`
--
ALTER TABLE `discounts`
    MODIFY `discount_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
    MODIFY `feedback_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
    MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
    MODIFY `notification_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orderitems`
--
ALTER TABLE `orderitems`
    MODIFY `order_item_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
    MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

--
-- AUTO_INCREMENT for table `recipes`
--
ALTER TABLE `recipes`
    MODIFY `recipe_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
    MODIFY `report_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `discounts`
--
ALTER TABLE `discounts`
    ADD CONSTRAINT `discounts_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE;

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
