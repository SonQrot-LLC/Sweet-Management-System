-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 09, 2024 at 07:34 PM
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

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`message_id`, `sender_email`, `receiver_email`, `content`, `created_at`) VALUES
                                                                                                     (126, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-08 19:24:36'),
                                                                                                     (127, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-08 19:24:36'),
                                                                                                     (128, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-08 19:24:36'),
                                                                                                     (129, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-08 19:24:36'),
                                                                                                     (130, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-08 19:24:36'),
                                                                                                     (131, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-08 22:08:25'),
                                                                                                     (132, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-08 22:08:25'),
                                                                                                     (133, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-08 22:08:25'),
                                                                                                     (134, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-08 22:08:25'),
                                                                                                     (135, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-08 22:08:25'),
                                                                                                     (136, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 13:03:38'),
                                                                                                     (137, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 13:03:38'),
                                                                                                     (138, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 13:03:38'),
                                                                                                     (139, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 13:03:38'),
                                                                                                     (140, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 13:03:38'),
                                                                                                     (141, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 16:29:49'),
                                                                                                     (142, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 16:29:49'),
                                                                                                     (143, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 16:29:49'),
                                                                                                     (144, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 16:29:49'),
                                                                                                     (145, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 16:29:49'),
                                                                                                     (146, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 16:36:17'),
                                                                                                     (147, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 16:36:17'),
                                                                                                     (148, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 16:36:17'),
                                                                                                     (149, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 16:36:17'),
                                                                                                     (150, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 16:36:17'),
                                                                                                     (151, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 16:38:40'),
                                                                                                     (152, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 16:38:40'),
                                                                                                     (153, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 16:38:40'),
                                                                                                     (154, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 16:38:40'),
                                                                                                     (155, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 16:38:40'),
                                                                                                     (156, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 16:47:51'),
                                                                                                     (157, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 16:47:51'),
                                                                                                     (158, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 16:47:51'),
                                                                                                     (159, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 16:47:51'),
                                                                                                     (160, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 16:47:51'),
                                                                                                     (161, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 16:49:22'),
                                                                                                     (162, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 16:49:22'),
                                                                                                     (163, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 16:49:22'),
                                                                                                     (164, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 16:49:22'),
                                                                                                     (165, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 16:49:23'),
                                                                                                     (166, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 16:54:20'),
                                                                                                     (167, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 16:54:20'),
                                                                                                     (168, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 16:54:20'),
                                                                                                     (169, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 16:54:20'),
                                                                                                     (170, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 16:54:20'),
                                                                                                     (171, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 16:55:57'),
                                                                                                     (172, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 16:55:57'),
                                                                                                     (173, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 16:55:57'),
                                                                                                     (174, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 16:55:57'),
                                                                                                     (175, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 16:55:57'),
                                                                                                     (176, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 16:59:42'),
                                                                                                     (177, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 16:59:42'),
                                                                                                     (178, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 16:59:42'),
                                                                                                     (179, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 16:59:42'),
                                                                                                     (180, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 16:59:42');

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
                                                                                              (1, 1, 1, 3, 18.00),
                                                                                              (2, 1, 1, 3, 18.00),
                                                                                              (3, 1, 1, 3, 18.00),
                                                                                              (4, 1, 1, 3, 18.00),
                                                                                              (5, 1, 1, 3, 18.00),
                                                                                              (6, 1, 1, 3, 18.00),
                                                                                              (7, 1, 1, 3, 18.00),
                                                                                              (8, 1, 1, 3, 18.00);

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
                                                                                                              (12, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-01 14:39:44'),
                                                                                                              (13, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 15:05:11'),
                                                                                                              (14, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 16:17:33'),
                                                                                                              (15, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 16:17:53'),
                                                                                                              (16, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 16:18:26'),
                                                                                                              (17, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 16:18:44'),
                                                                                                              (18, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 16:20:00'),
                                                                                                              (19, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 16:20:44'),
                                                                                                              (20, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 16:37:07'),
                                                                                                              (21, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 16:38:22'),
                                                                                                              (22, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 16:38:46'),
                                                                                                              (23, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 16:41:00'),
                                                                                                              (24, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 16:42:31'),
                                                                                                              (25, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 16:59:46'),
                                                                                                              (26, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:08:12'),
                                                                                                              (27, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:09:34'),
                                                                                                              (28, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:10:24'),
                                                                                                              (29, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:11:01'),
                                                                                                              (30, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:13:19'),
                                                                                                              (31, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:15:08'),
                                                                                                              (32, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:20:39'),
                                                                                                              (33, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:21:35'),
                                                                                                              (34, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:22:04'),
                                                                                                              (35, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:22:35'),
                                                                                                              (36, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:23:53'),
                                                                                                              (37, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:24:28'),
                                                                                                              (38, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:25:12'),
                                                                                                              (39, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:30:16'),
                                                                                                              (40, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:33:02'),
                                                                                                              (41, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:33:57'),
                                                                                                              (42, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:36:24'),
                                                                                                              (43, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:38:26'),
                                                                                                              (44, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:39:55'),
                                                                                                              (45, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:40:18'),
                                                                                                              (46, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:43:14'),
                                                                                                              (47, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:44:14'),
                                                                                                              (48, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:49:20'),
                                                                                                              (49, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:50:27'),
                                                                                                              (50, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:52:26'),
                                                                                                              (51, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 17:54:47'),
                                                                                                              (52, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-07 18:15:29'),
                                                                                                              (53, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-08 19:24:36'),
                                                                                                              (54, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-08 22:08:25'),
                                                                                                              (55, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 13:03:38'),
                                                                                                              (56, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 16:29:49'),
                                                                                                              (57, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 16:36:17'),
                                                                                                              (58, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 16:38:40'),
                                                                                                              (59, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 16:47:51'),
                                                                                                              (60, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 16:49:23'),
                                                                                                              (61, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 16:54:20'),
                                                                                                              (62, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 16:55:57'),
                                                                                                              (63, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 16:59:43');

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
                                                                                                                                                (4, 1, 'item2', 'item2', 31.00, 200, '2024-07-31 21:22:11', '2024-09-20', 0.00),
                                                                                                                                                (5, 1, 'item3', 'item3', 32.00, 124, '2024-07-31 21:22:11', '2024-09-03', 0.00),
                                                                                                                                                (6, 1, 'item4', 'item4', 53.00, 21, '2024-07-31 21:22:51', '2024-08-15', 0.00),
                                                                                                                                                (7, 2, 'cheesecake', 'Big juicy cake', 6.00, 30, '2024-08-01 07:05:42', '2024-12-28', 0.00);

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
                                                                                                  (3, 'mahmood@outlook.com', 'to be deleted', 'testing deleting function', '2024-08-09 16:59:42');

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
                                                                            ('ahmad123@gmail.com', '777', 'beneficiary_user', 'Japan', '2024-08-09 16:59:42'),
                                                                            ('mahmood@gmail.com', '777', 'admin', 'Jordan', '2024-07-27 09:08:57'),
                                                                            ('mahmood@outlook.com', '777', 'store_owner', 'Khalil', '2024-07-27 16:31:27'),
                                                                            ('momanani2017@gmail.com', '777', 'admin', 'Tulkarm', '2024-07-26 16:51:33'),
                                                                            ('momkasat2017@gmail.com', '777', 'admin', 'Tulkarm', '2024-08-09 16:47:52'),
                                                                            ('n.hamfallah@gmail.com', '777', 'admin', 'KATEEBEH', '2024-07-25 19:32:07'),
                                                                            ('order.store@gmail.com', '567', 'store_owner', 'Nablus', '2024-08-01 09:41:33'),
                                                                            ('order.user@gmail.com', '321', 'beneficiary_user', 'Hebron', '2024-08-01 09:41:33'),
                                                                            ('owner@gmail.com', '123', 'store_owner', 'Tulkarem', '2024-07-31 19:07:47'),
                                                                            ('salam@hawa.com', '222', 'store_owner', 'Tulkarem', '2024-07-25 19:05:38'),
                                                                            ('supplier@gmail.com', '123', 'raw_material_supplier', 'Tulkarem', '2024-08-07 16:00:31'),
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

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bestsellingproducts`  AS SELECT `p`.`product_id` AS `product_id`, `p`.`product_name` AS `product_name`, `p`.`store_id` AS `store_id`, sum(`oi`.`quantity`) AS `total_quantity_sold` FROM (`orderitems` `oi` join `products` `p` on(`oi`.`product_id` = `p`.`product_id`)) GROUP BY `p`.`product_id`, `p`.`product_name`, `p`.`store_id` ORDER BY sum(`oi`.`quantity`) DESC ;

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
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
    MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=181;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
    MODIFY `notification_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orderitems`
--
ALTER TABLE `orderitems`
    MODIFY `order_item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
    MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

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
