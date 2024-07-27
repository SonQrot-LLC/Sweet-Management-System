-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 27, 2024 at 11:55 PM
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
-- Table structure for table `Discounts`
--

CREATE TABLE `Discounts` (
  `discount_id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `discount_percentage` decimal(5,2) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Feedback`
--

CREATE TABLE `Feedback` (
  `feedback_id` int(11) NOT NULL,
  `user_email` varchar(100) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL CHECK (`rating` between 1 and 5),
  `comment` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Messages`
--

CREATE TABLE `Messages` (
  `message_id` int(11) NOT NULL,
  `sender_email` varchar(100) DEFAULT NULL,
  `receiver_email` varchar(100) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Notifications`
--

CREATE TABLE `Notifications` (
  `notification_id` int(11) NOT NULL,
  `user_email` varchar(100) DEFAULT NULL,
  `message` text DEFAULT NULL,
  `is_read` tinyint(1) DEFAULT 0,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `OrderItems`
--

CREATE TABLE `OrderItems` (
  `order_item_id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Orders`
--

CREATE TABLE `Orders` (
  `order_id` int(11) NOT NULL,
  `user_email` varchar(100) DEFAULT NULL,
  `store_id` int(11) DEFAULT NULL,
  `order_status` enum('pending','processed','shipped','delivered','cancelled') DEFAULT 'pending',
  `total_amount` decimal(10,2) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Products`
--

CREATE TABLE `Products` (
  `product_id` int(11) NOT NULL,
  `store_id` int(11) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Products`
--

INSERT INTO `Products` (`product_id`, `store_id`, `product_name`, `description`, `price`, `stock`, `created_at`) VALUES
(2, 13, 'BIG CAKE', ' A big cake that can feed up to 10 persons', 55.00, 7, '2024-07-27 21:54:14');

-- --------------------------------------------------------

--
-- Table structure for table `Recipes`
--

CREATE TABLE `Recipes` (
  `recipe_id` int(11) NOT NULL,
  `user_email` varchar(100) DEFAULT NULL,
  `recipe_name` varchar(100) DEFAULT NULL,
  `ingredients` text DEFAULT NULL,
  `instructions` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Reports`
--

CREATE TABLE `Reports` (
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
-- Table structure for table `Stores`
--

CREATE TABLE `Stores` (
  `store_id` int(11) NOT NULL,
  `owner_email` varchar(100) DEFAULT NULL,
  `store_name` varchar(100) DEFAULT NULL,
  `business_info` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Stores`
--

INSERT INTO `Stores` (`store_id`, `owner_email`, `store_name`, `business_info`, `created_at`) VALUES
(13, 'mahmood@outlook.com', 'Mr.Cake', 'The Cake Shop is a Cake Studio specializing in Wedding cakes, Custom Cakes, and Dessert Bars. We also offer a variety of bite sized treats. Everything is made from scratch in house and with locally sourced ingredients when possible.', '2024-07-27 21:54:13');

-- --------------------------------------------------------

--
-- Table structure for table `UserProfiles`
--

CREATE TABLE `UserProfiles` (
  `email` varchar(100) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `UserProfiles`
--

INSERT INTO `UserProfiles` (`email`, `first_name`, `last_name`, `phone`, `address`) VALUES
('ahmad123@gmail.com', 'test1', 'test2', '123123123', 'anabta');

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('admin','store_owner','raw_material_supplier','beneficiary_user') NOT NULL,
  `city` varchar(50) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`email`, `password`, `role`, `city`, `created_at`) VALUES
('ahmad123@gmail.com', '777', 'beneficiary_user', 'Japan', '2024-07-27 21:54:13'),
('mahmood@gmail.com', '777', 'admin', 'Jordan', '2024-07-27 09:08:57'),
('mahmood@outlook.com', '777', 'store_owner', 'Khalil', '2024-07-27 16:31:27'),
('momanani2017@gmail.com', '777', 'admin', 'Tulkarm', '2024-07-26 16:51:33'),
('n.hamfallah@gmail.com', '777', 'admin', 'KATEEBEH', '2024-07-25 19:32:07'),
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
-- Indexes for table `Discounts`
--
ALTER TABLE `Discounts`
  ADD PRIMARY KEY (`discount_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `Feedback`
--
ALTER TABLE `Feedback`
  ADD PRIMARY KEY (`feedback_id`),
  ADD KEY `user_email` (`user_email`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `Messages`
--
ALTER TABLE `Messages`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `sender_email` (`sender_email`),
  ADD KEY `receiver_email` (`receiver_email`);

--
-- Indexes for table `Notifications`
--
ALTER TABLE `Notifications`
  ADD PRIMARY KEY (`notification_id`),
  ADD KEY `user_email` (`user_email`);

--
-- Indexes for table `OrderItems`
--
ALTER TABLE `OrderItems`
  ADD PRIMARY KEY (`order_item_id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `Orders`
--
ALTER TABLE `Orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `user_email` (`user_email`),
  ADD KEY `store_id` (`store_id`);

--
-- Indexes for table `Products`
--
ALTER TABLE `Products`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `store_id` (`store_id`);

--
-- Indexes for table `Recipes`
--
ALTER TABLE `Recipes`
  ADD PRIMARY KEY (`recipe_id`),
  ADD KEY `user_email` (`user_email`);

--
-- Indexes for table `Reports`
--
ALTER TABLE `Reports`
  ADD PRIMARY KEY (`report_id`);

--
-- Indexes for table `Stores`
--
ALTER TABLE `Stores`
  ADD PRIMARY KEY (`store_id`),
  ADD UNIQUE KEY `unique_owner_email` (`owner_email`);

--
-- Indexes for table `UserProfiles`
--
ALTER TABLE `UserProfiles`
  ADD PRIMARY KEY (`email`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Discounts`
--
ALTER TABLE `Discounts`
  MODIFY `discount_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Feedback`
--
ALTER TABLE `Feedback`
  MODIFY `feedback_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Messages`
--
ALTER TABLE `Messages`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Notifications`
--
ALTER TABLE `Notifications`
  MODIFY `notification_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `OrderItems`
--
ALTER TABLE `OrderItems`
  MODIFY `order_item_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Orders`
--
ALTER TABLE `Orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Products`
--
ALTER TABLE `Products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `Recipes`
--
ALTER TABLE `Recipes`
  MODIFY `recipe_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Reports`
--
ALTER TABLE `Reports`
  MODIFY `report_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Stores`
--
ALTER TABLE `Stores`
  MODIFY `store_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Discounts`
--
ALTER TABLE `Discounts`
  ADD CONSTRAINT `discounts_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `Products` (`product_id`) ON DELETE CASCADE;

--
-- Constraints for table `Feedback`
--
ALTER TABLE `Feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`user_email`) REFERENCES `Users` (`email`) ON DELETE CASCADE,
  ADD CONSTRAINT `feedback_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `Products` (`product_id`) ON DELETE CASCADE;

--
-- Constraints for table `Messages`
--
ALTER TABLE `Messages`
  ADD CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`sender_email`) REFERENCES `Users` (`email`) ON DELETE CASCADE,
  ADD CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`receiver_email`) REFERENCES `Users` (`email`) ON DELETE CASCADE;

--
-- Constraints for table `Notifications`
--
ALTER TABLE `Notifications`
  ADD CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`user_email`) REFERENCES `Users` (`email`) ON DELETE CASCADE;

--
-- Constraints for table `OrderItems`
--
ALTER TABLE `OrderItems`
  ADD CONSTRAINT `orderitems_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `Orders` (`order_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `orderitems_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `Products` (`product_id`) ON DELETE CASCADE;

--
-- Constraints for table `Orders`
--
ALTER TABLE `Orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_email`) REFERENCES `Users` (`email`) ON DELETE CASCADE,
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`store_id`) REFERENCES `Stores` (`store_id`) ON DELETE CASCADE;

--
-- Constraints for table `Products`
--
ALTER TABLE `Products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`store_id`) REFERENCES `Stores` (`store_id`) ON DELETE CASCADE;

--
-- Constraints for table `Recipes`
--
ALTER TABLE `Recipes`
  ADD CONSTRAINT `recipes_ibfk_1` FOREIGN KEY (`user_email`) REFERENCES `Users` (`email`) ON DELETE CASCADE;

--
-- Constraints for table `Stores`
--
ALTER TABLE `Stores`
  ADD CONSTRAINT `stores_ibfk_1` FOREIGN KEY (`owner_email`) REFERENCES `Users` (`email`) ON DELETE CASCADE;

--
-- Constraints for table `UserProfiles`
--
ALTER TABLE `UserProfiles`
  ADD CONSTRAINT `userprofiles_ibfk_1` FOREIGN KEY (`email`) REFERENCES `Users` (`email`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
