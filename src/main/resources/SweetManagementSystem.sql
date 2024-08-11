-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 11, 2024 at 09:06 PM
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
                                                                                                     (180, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 16:59:42'),
                                                                                                     (181, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 20:42:20'),
                                                                                                     (182, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 20:42:20'),
                                                                                                     (183, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 20:42:20'),
                                                                                                     (184, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 20:42:20'),
                                                                                                     (185, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 20:42:20'),
                                                                                                     (186, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 20:42:47'),
                                                                                                     (187, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 20:42:47'),
                                                                                                     (188, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 20:42:47'),
                                                                                                     (189, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 20:42:47'),
                                                                                                     (190, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 20:42:47'),
                                                                                                     (191, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 20:43:53'),
                                                                                                     (192, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 20:43:53'),
                                                                                                     (193, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 20:43:53'),
                                                                                                     (194, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 20:43:53'),
                                                                                                     (195, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 20:43:53'),
                                                                                                     (196, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 21:39:58'),
                                                                                                     (197, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 21:39:58'),
                                                                                                     (198, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 21:39:58'),
                                                                                                     (199, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 21:39:58'),
                                                                                                     (200, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 21:39:58'),
                                                                                                     (201, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 21:43:09'),
                                                                                                     (202, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 21:43:09'),
                                                                                                     (203, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 21:43:09'),
                                                                                                     (204, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 21:43:09'),
                                                                                                     (205, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 21:43:09'),
                                                                                                     (206, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 21:50:16'),
                                                                                                     (207, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 21:50:16'),
                                                                                                     (208, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 21:50:16'),
                                                                                                     (209, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 21:50:16'),
                                                                                                     (210, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 21:50:16'),
                                                                                                     (211, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 21:51:23'),
                                                                                                     (212, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 21:51:23'),
                                                                                                     (213, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 21:51:23'),
                                                                                                     (214, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 21:51:23'),
                                                                                                     (215, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 21:51:24'),
                                                                                                     (216, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 21:52:40'),
                                                                                                     (217, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 21:52:40'),
                                                                                                     (218, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 21:52:40'),
                                                                                                     (219, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 21:52:40'),
                                                                                                     (220, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 21:52:40'),
                                                                                                     (221, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 21:53:40'),
                                                                                                     (222, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 21:53:40'),
                                                                                                     (223, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 21:53:40'),
                                                                                                     (224, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 21:53:40'),
                                                                                                     (225, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 21:53:40'),
                                                                                                     (226, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 21:56:22'),
                                                                                                     (227, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 21:56:22'),
                                                                                                     (228, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 21:56:22'),
                                                                                                     (229, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 21:56:22'),
                                                                                                     (230, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 21:56:22'),
                                                                                                     (231, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 22:08:32'),
                                                                                                     (232, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 22:08:32'),
                                                                                                     (233, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 22:08:32'),
                                                                                                     (234, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 22:08:32'),
                                                                                                     (235, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 22:08:32'),
                                                                                                     (236, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 22:09:16'),
                                                                                                     (237, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 22:09:16'),
                                                                                                     (238, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 22:09:16'),
                                                                                                     (239, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 22:09:16'),
                                                                                                     (240, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 22:09:16'),
                                                                                                     (241, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 22:11:03'),
                                                                                                     (242, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 22:11:03'),
                                                                                                     (243, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 22:11:03'),
                                                                                                     (244, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 22:11:03'),
                                                                                                     (245, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 22:11:03'),
                                                                                                     (246, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 22:11:40'),
                                                                                                     (247, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 22:11:40'),
                                                                                                     (248, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 22:11:40'),
                                                                                                     (249, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 22:11:40'),
                                                                                                     (250, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 22:11:40'),
                                                                                                     (251, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 22:12:13'),
                                                                                                     (252, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 22:12:13'),
                                                                                                     (253, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 22:12:13'),
                                                                                                     (254, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 22:12:13'),
                                                                                                     (255, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 22:12:13'),
                                                                                                     (256, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 22:13:18'),
                                                                                                     (257, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 22:13:18'),
                                                                                                     (258, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 22:13:18'),
                                                                                                     (259, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 22:13:18'),
                                                                                                     (260, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 22:13:18'),
                                                                                                     (261, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 22:14:09'),
                                                                                                     (262, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 22:14:09'),
                                                                                                     (263, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 22:14:09'),
                                                                                                     (264, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 22:14:09'),
                                                                                                     (265, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 22:14:09'),
                                                                                                     (266, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 22:18:13'),
                                                                                                     (267, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 22:18:13'),
                                                                                                     (268, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 22:18:13'),
                                                                                                     (269, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 22:18:13'),
                                                                                                     (270, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 22:18:13'),
                                                                                                     (271, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 22:20:01'),
                                                                                                     (272, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 22:20:01'),
                                                                                                     (273, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 22:20:01'),
                                                                                                     (274, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 22:20:01'),
                                                                                                     (275, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 22:20:01'),
                                                                                                     (276, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 22:21:21'),
                                                                                                     (277, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 22:21:22'),
                                                                                                     (278, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 22:21:22'),
                                                                                                     (279, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 22:21:22'),
                                                                                                     (280, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 22:21:22'),
                                                                                                     (281, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-09 22:22:53'),
                                                                                                     (282, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-09 22:22:53'),
                                                                                                     (283, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-09 22:22:53'),
                                                                                                     (284, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-09 22:22:53'),
                                                                                                     (285, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-09 22:22:53'),
                                                                                                     (286, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 13:32:55'),
                                                                                                     (287, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 13:32:55'),
                                                                                                     (288, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 13:32:55'),
                                                                                                     (289, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 13:32:55'),
                                                                                                     (290, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 13:32:55'),
                                                                                                     (291, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 13:33:24'),
                                                                                                     (292, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 13:33:24'),
                                                                                                     (293, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 13:33:24'),
                                                                                                     (294, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 13:33:24'),
                                                                                                     (295, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 13:33:24'),
                                                                                                     (296, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 13:46:39'),
                                                                                                     (297, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 13:46:39'),
                                                                                                     (298, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 13:46:39'),
                                                                                                     (299, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 13:46:39'),
                                                                                                     (300, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 13:46:39'),
                                                                                                     (301, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 14:00:46'),
                                                                                                     (302, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 14:00:46'),
                                                                                                     (303, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 14:00:46'),
                                                                                                     (304, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 14:00:46'),
                                                                                                     (305, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 14:00:46'),
                                                                                                     (306, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 14:01:51'),
                                                                                                     (307, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 14:01:51'),
                                                                                                     (308, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 14:01:51'),
                                                                                                     (309, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 14:01:51'),
                                                                                                     (310, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 14:01:51'),
                                                                                                     (311, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 14:02:41'),
                                                                                                     (312, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 14:02:41'),
                                                                                                     (313, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 14:02:41'),
                                                                                                     (314, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 14:02:41'),
                                                                                                     (315, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 14:02:41'),
                                                                                                     (316, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 14:18:52'),
                                                                                                     (317, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 14:18:52'),
                                                                                                     (318, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 14:18:52'),
                                                                                                     (319, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 14:18:52'),
                                                                                                     (320, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 14:18:52'),
                                                                                                     (321, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 14:19:39'),
                                                                                                     (322, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 14:19:39'),
                                                                                                     (323, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 14:19:39'),
                                                                                                     (324, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 14:19:39'),
                                                                                                     (325, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 14:19:39'),
                                                                                                     (326, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 14:28:21'),
                                                                                                     (327, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 14:28:22'),
                                                                                                     (328, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 14:28:22'),
                                                                                                     (329, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 14:28:22'),
                                                                                                     (330, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 14:28:22'),
                                                                                                     (331, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 14:32:32'),
                                                                                                     (332, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 14:32:33'),
                                                                                                     (333, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 14:32:33'),
                                                                                                     (334, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 14:32:33'),
                                                                                                     (335, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 14:32:33'),
                                                                                                     (336, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 14:33:50'),
                                                                                                     (337, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 14:33:50'),
                                                                                                     (338, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 14:33:50'),
                                                                                                     (339, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 14:33:50'),
                                                                                                     (340, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 14:33:50'),
                                                                                                     (341, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 14:35:01'),
                                                                                                     (342, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 14:35:01'),
                                                                                                     (343, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 14:35:01'),
                                                                                                     (344, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 14:35:01'),
                                                                                                     (345, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 14:35:01'),
                                                                                                     (346, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 14:39:59'),
                                                                                                     (347, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 14:39:59'),
                                                                                                     (348, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 14:39:59'),
                                                                                                     (349, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 14:39:59'),
                                                                                                     (350, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 14:39:59'),
                                                                                                     (351, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 14:45:02'),
                                                                                                     (352, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 14:45:02'),
                                                                                                     (353, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 14:45:02'),
                                                                                                     (354, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 14:45:02'),
                                                                                                     (355, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 14:45:02'),
                                                                                                     (356, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 14:48:24'),
                                                                                                     (357, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 14:48:24'),
                                                                                                     (358, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 14:48:24'),
                                                                                                     (359, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 14:48:24'),
                                                                                                     (360, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 14:48:24'),
                                                                                                     (361, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 15:01:21'),
                                                                                                     (362, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 15:01:21'),
                                                                                                     (363, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 15:01:21'),
                                                                                                     (364, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 15:01:21'),
                                                                                                     (365, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 15:01:21'),
                                                                                                     (366, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 15:02:25'),
                                                                                                     (367, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 15:02:25'),
                                                                                                     (368, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 15:02:25'),
                                                                                                     (369, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 15:02:25'),
                                                                                                     (370, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 15:02:25'),
                                                                                                     (371, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 16:08:35'),
                                                                                                     (372, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 16:08:35'),
                                                                                                     (373, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 16:08:35'),
                                                                                                     (374, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 16:08:35'),
                                                                                                     (375, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 16:08:35'),
                                                                                                     (376, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 16:10:20'),
                                                                                                     (377, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 16:10:20'),
                                                                                                     (378, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 16:10:20'),
                                                                                                     (379, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 16:10:20'),
                                                                                                     (380, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 16:10:20'),
                                                                                                     (381, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 16:12:04'),
                                                                                                     (382, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 16:12:04'),
                                                                                                     (383, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 16:12:04'),
                                                                                                     (384, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 16:12:04'),
                                                                                                     (385, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 16:12:04'),
                                                                                                     (386, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 16:13:43'),
                                                                                                     (387, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 16:13:43'),
                                                                                                     (388, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 16:13:43'),
                                                                                                     (389, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 16:13:43'),
                                                                                                     (390, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 16:13:43'),
                                                                                                     (391, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 16:17:25'),
                                                                                                     (392, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 16:17:25'),
                                                                                                     (393, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 16:17:25'),
                                                                                                     (394, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 16:17:25'),
                                                                                                     (395, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 16:17:25'),
                                                                                                     (396, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 16:19:36'),
                                                                                                     (397, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 16:19:36'),
                                                                                                     (398, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 16:19:36'),
                                                                                                     (399, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 16:19:36'),
                                                                                                     (400, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 16:19:36'),
                                                                                                     (401, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 16:27:48'),
                                                                                                     (402, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 16:27:48'),
                                                                                                     (403, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 16:27:48'),
                                                                                                     (404, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 16:27:48'),
                                                                                                     (405, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 16:27:48'),
                                                                                                     (406, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 16:28:14'),
                                                                                                     (407, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 16:28:14'),
                                                                                                     (408, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 16:28:14'),
                                                                                                     (409, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 16:28:14'),
                                                                                                     (410, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 16:28:14'),
                                                                                                     (411, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 16:43:35'),
                                                                                                     (412, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 16:43:35'),
                                                                                                     (413, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 16:43:36'),
                                                                                                     (414, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 16:43:36'),
                                                                                                     (415, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 16:43:36'),
                                                                                                     (416, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 16:47:34'),
                                                                                                     (417, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 16:47:34'),
                                                                                                     (418, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 16:47:34'),
                                                                                                     (419, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 16:47:34'),
                                                                                                     (420, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 16:47:34'),
                                                                                                     (421, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 16:47:52'),
                                                                                                     (422, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 16:47:52'),
                                                                                                     (423, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 16:47:52'),
                                                                                                     (424, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 16:47:52'),
                                                                                                     (425, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 16:47:52'),
                                                                                                     (426, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 16:50:24'),
                                                                                                     (427, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 16:50:24'),
                                                                                                     (428, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 16:50:24'),
                                                                                                     (429, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 16:50:24'),
                                                                                                     (430, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 16:50:24'),
                                                                                                     (431, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 17:03:52'),
                                                                                                     (432, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 17:03:52'),
                                                                                                     (433, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 17:03:52'),
                                                                                                     (434, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 17:03:52'),
                                                                                                     (435, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 17:03:52'),
                                                                                                     (436, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 17:13:06'),
                                                                                                     (437, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 17:13:06'),
                                                                                                     (438, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 17:13:06'),
                                                                                                     (439, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 17:13:06'),
                                                                                                     (440, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 17:13:06'),
                                                                                                     (441, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 17:16:10'),
                                                                                                     (442, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 17:16:10'),
                                                                                                     (443, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 17:16:10'),
                                                                                                     (444, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 17:16:10'),
                                                                                                     (445, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 17:16:10'),
                                                                                                     (446, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 17:17:53'),
                                                                                                     (447, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 17:17:54'),
                                                                                                     (448, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 17:17:54'),
                                                                                                     (449, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 17:17:54'),
                                                                                                     (450, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 17:17:54'),
                                                                                                     (451, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 17:23:01'),
                                                                                                     (452, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 17:23:01'),
                                                                                                     (453, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 17:23:01'),
                                                                                                     (454, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 17:23:01'),
                                                                                                     (455, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 17:23:01'),
                                                                                                     (456, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 17:23:52'),
                                                                                                     (457, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 17:23:52'),
                                                                                                     (458, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 17:23:52'),
                                                                                                     (459, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 17:23:52'),
                                                                                                     (460, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 17:23:52'),
                                                                                                     (461, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 17:26:51'),
                                                                                                     (462, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 17:26:51'),
                                                                                                     (463, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 17:26:51'),
                                                                                                     (464, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 17:26:51'),
                                                                                                     (465, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 17:26:51'),
                                                                                                     (466, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 17:29:16'),
                                                                                                     (467, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 17:29:16'),
                                                                                                     (468, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 17:29:16'),
                                                                                                     (469, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 17:29:16'),
                                                                                                     (470, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 17:29:16'),
                                                                                                     (471, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 17:32:16'),
                                                                                                     (472, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 17:32:17'),
                                                                                                     (473, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 17:32:17'),
                                                                                                     (474, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 17:32:17'),
                                                                                                     (475, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 17:32:17'),
                                                                                                     (476, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 17:33:30'),
                                                                                                     (477, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 17:33:30'),
                                                                                                     (478, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 17:33:30'),
                                                                                                     (479, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 17:33:30'),
                                                                                                     (480, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 17:33:30'),
                                                                                                     (481, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 17:33:54'),
                                                                                                     (482, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 17:33:54'),
                                                                                                     (483, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 17:33:54'),
                                                                                                     (484, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 17:33:54'),
                                                                                                     (485, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 17:33:54'),
                                                                                                     (486, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 17:34:40'),
                                                                                                     (487, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 17:34:40'),
                                                                                                     (488, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 17:34:40'),
                                                                                                     (489, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 17:34:40'),
                                                                                                     (490, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 17:34:40'),
                                                                                                     (491, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 17:36:04'),
                                                                                                     (492, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 17:36:04'),
                                                                                                     (493, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 17:36:04'),
                                                                                                     (494, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 17:36:04'),
                                                                                                     (495, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 17:36:04'),
                                                                                                     (496, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 17:55:42'),
                                                                                                     (497, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 17:55:42'),
                                                                                                     (498, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 17:55:42'),
                                                                                                     (499, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 17:55:43'),
                                                                                                     (500, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 17:55:43'),
                                                                                                     (501, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 18:02:56'),
                                                                                                     (502, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 18:02:56'),
                                                                                                     (503, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 18:02:56'),
                                                                                                     (504, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 18:02:56'),
                                                                                                     (505, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 18:02:56'),
                                                                                                     (506, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 18:03:37'),
                                                                                                     (507, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 18:03:37'),
                                                                                                     (508, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 18:03:37'),
                                                                                                     (509, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 18:03:37'),
                                                                                                     (510, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 18:03:37'),
                                                                                                     (511, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 18:04:17'),
                                                                                                     (512, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 18:04:17'),
                                                                                                     (513, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 18:04:17'),
                                                                                                     (514, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 18:04:17'),
                                                                                                     (515, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 18:04:17'),
                                                                                                     (516, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 18:06:14'),
                                                                                                     (517, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 18:06:14'),
                                                                                                     (518, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 18:06:14'),
                                                                                                     (519, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 18:06:14'),
                                                                                                     (520, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 18:06:14'),
                                                                                                     (521, 'order.user@gmail.com', 'owner@gmail.com', 'How do you make cake', '2024-08-11 18:12:20'),
                                                                                                     (522, 'order.user@gmail.com', 'supplier@gmail.com', 'How do you grow sugar', '2024-08-11 18:12:20'),
                                                                                                     (523, 'owner@gmail.com', 'order.user@gmail.com', 'Here is how I do it', '2024-08-11 18:12:20'),
                                                                                                     (524, 'supplier@gmail.com', 'order.user@gmail.com', 'this is how I do it', '2024-08-11 18:12:20'),
                                                                                                     (525, 'order.user@gmail.com', 'owner@gmail.com', 'Ok', '2024-08-11 18:12:20');

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
                                                                                              (8, 1, 1, 3, 18.00),
                                                                                              (9, 1, 1, 3, 18.00),
                                                                                              (10, 1, 1, 3, 18.00),
                                                                                              (11, 1, 1, 3, 18.00),
                                                                                              (12, 1, 1, 3, 18.00),
                                                                                              (13, 1, 1, 3, 18.00),
                                                                                              (14, 1, 1, 3, 18.00),
                                                                                              (15, 1, 1, 3, 18.00),
                                                                                              (16, 1, 1, 3, 18.00),
                                                                                              (17, 1, 1, 3, 18.00),
                                                                                              (18, 1, 1, 3, 18.00),
                                                                                              (19, 1, 1, 3, 18.00),
                                                                                              (20, 1, 1, 3, 18.00),
                                                                                              (21, 1, 1, 3, 18.00),
                                                                                              (22, 1, 1, 3, 18.00),
                                                                                              (23, 1, 1, 3, 18.00),
                                                                                              (24, 1, 1, 3, 18.00),
                                                                                              (25, 1, 1, 3, 18.00),
                                                                                              (26, 1, 1, 3, 18.00),
                                                                                              (27, 1, 1, 3, 18.00),
                                                                                              (28, 1, 1, 3, 18.00),
                                                                                              (29, 1, 1, 3, 18.00),
                                                                                              (30, 1, 1, 3, 18.00),
                                                                                              (31, 1, 1, 3, 18.00),
                                                                                              (32, 1, 1, 3, 18.00),
                                                                                              (33, 1, 1, 3, 18.00),
                                                                                              (34, 1, 1, 3, 18.00),
                                                                                              (35, 1, 1, 3, 18.00),
                                                                                              (36, 1, 1, 3, 18.00),
                                                                                              (37, 1, 1, 3, 18.00),
                                                                                              (38, 1, 1, 3, 18.00),
                                                                                              (39, 1, 1, 3, 18.00),
                                                                                              (40, 1, 1, 3, 18.00),
                                                                                              (41, 1, 1, 3, 18.00),
                                                                                              (42, 1, 1, 3, 18.00),
                                                                                              (43, 1, 1, 3, 18.00),
                                                                                              (44, 1, 1, 3, 18.00),
                                                                                              (45, 1, 1, 3, 18.00),
                                                                                              (46, 1, 1, 3, 18.00),
                                                                                              (47, 1, 1, 3, 18.00),
                                                                                              (48, 1, 1, 3, 18.00),
                                                                                              (49, 1, 1, 3, 18.00),
                                                                                              (50, 1, 1, 3, 18.00),
                                                                                              (51, 1, 1, 3, 18.00),
                                                                                              (52, 1, 1, 3, 18.00),
                                                                                              (53, 1, 1, 3, 18.00),
                                                                                              (54, 1, 1, 3, 18.00),
                                                                                              (55, 1, 1, 3, 18.00),
                                                                                              (56, 1, 1, 3, 18.00),
                                                                                              (57, 1, 1, 3, 18.00),
                                                                                              (58, 1, 1, 3, 18.00),
                                                                                              (59, 1, 1, 3, 18.00),
                                                                                              (60, 1, 1, 3, 18.00),
                                                                                              (61, 1, 1, 3, 18.00),
                                                                                              (62, 1, 1, 3, 18.00),
                                                                                              (63, 1, 1, 3, 18.00),
                                                                                              (64, 1, 1, 3, 18.00),
                                                                                              (65, 1, 1, 3, 18.00),
                                                                                              (66, 1, 1, 3, 18.00),
                                                                                              (67, 1, 1, 3, 18.00),
                                                                                              (68, 1, 1, 3, 18.00),
                                                                                              (69, 1, 1, 3, 18.00),
                                                                                              (70, 1, 1, 3, 18.00),
                                                                                              (71, 1, 1, 3, 18.00),
                                                                                              (72, 1, 1, 3, 18.00),
                                                                                              (73, 1, 1, 3, 18.00),
                                                                                              (74, 1, 1, 3, 18.00),
                                                                                              (75, 1, 1, 3, 18.00),
                                                                                              (76, 1, 1, 3, 18.00),
                                                                                              (77, 1, 1, 3, 18.00);

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
                                                                                                              (63, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 16:59:43'),
                                                                                                              (64, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 20:42:20'),
                                                                                                              (65, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 20:42:47'),
                                                                                                              (66, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 20:43:54'),
                                                                                                              (67, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 21:39:58'),
                                                                                                              (68, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 21:43:09'),
                                                                                                              (69, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 21:50:17'),
                                                                                                              (70, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 21:51:24'),
                                                                                                              (71, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 21:52:40'),
                                                                                                              (72, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 21:53:40'),
                                                                                                              (73, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 21:56:22'),
                                                                                                              (74, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 22:08:33'),
                                                                                                              (75, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 22:09:16'),
                                                                                                              (76, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 22:11:03'),
                                                                                                              (77, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 22:11:40'),
                                                                                                              (78, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 22:12:13'),
                                                                                                              (79, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 22:13:18'),
                                                                                                              (80, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 22:14:09'),
                                                                                                              (81, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 22:18:13'),
                                                                                                              (82, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 22:20:01'),
                                                                                                              (83, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 22:21:22'),
                                                                                                              (84, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-09 22:22:53'),
                                                                                                              (85, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 13:32:55'),
                                                                                                              (86, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 13:33:25'),
                                                                                                              (87, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 13:46:39'),
                                                                                                              (88, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 14:00:46'),
                                                                                                              (89, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 14:01:51'),
                                                                                                              (90, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 14:02:41'),
                                                                                                              (91, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 14:18:52'),
                                                                                                              (92, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 14:19:39'),
                                                                                                              (93, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 14:28:22'),
                                                                                                              (94, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 14:32:33'),
                                                                                                              (95, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 14:33:50'),
                                                                                                              (96, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 14:35:01'),
                                                                                                              (97, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 14:39:59'),
                                                                                                              (98, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 14:45:02'),
                                                                                                              (99, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 14:48:24'),
                                                                                                              (100, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 15:01:21'),
                                                                                                              (101, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 15:02:25'),
                                                                                                              (102, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 16:08:35'),
                                                                                                              (103, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 16:10:20'),
                                                                                                              (104, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 16:12:04'),
                                                                                                              (105, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 16:13:43'),
                                                                                                              (106, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 16:17:25'),
                                                                                                              (107, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 16:19:37'),
                                                                                                              (108, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 16:27:48'),
                                                                                                              (109, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 16:28:14'),
                                                                                                              (110, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 16:43:36'),
                                                                                                              (111, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 16:47:34'),
                                                                                                              (112, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 16:47:52'),
                                                                                                              (113, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 16:50:24'),
                                                                                                              (114, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 17:03:52'),
                                                                                                              (115, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 17:13:06'),
                                                                                                              (116, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 17:16:11'),
                                                                                                              (117, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 17:17:54'),
                                                                                                              (118, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 17:23:01'),
                                                                                                              (119, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 17:23:52'),
                                                                                                              (120, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 17:26:51'),
                                                                                                              (121, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 17:29:16'),
                                                                                                              (122, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 17:32:17'),
                                                                                                              (123, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 17:33:30'),
                                                                                                              (124, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 17:33:54'),
                                                                                                              (125, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 17:34:40'),
                                                                                                              (126, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 17:36:04'),
                                                                                                              (127, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 17:55:43'),
                                                                                                              (128, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 18:02:56'),
                                                                                                              (129, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 18:03:37'),
                                                                                                              (130, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 18:04:17'),
                                                                                                              (131, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 18:06:14'),
                                                                                                              (132, 'order.user@gmail.com', 2, 'pending', 0.00, '2024-08-11 18:12:20');

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
                                                                                                                               (4, 'feedbacktest@gmail.com', 'Vanilla Donuts', 'Vanilla and Milk', 'Cool', '2024-08-11 14:17:41', 'cows\' milk');

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
                                                                                                  (3, 'mahmood@outlook.com', 'to be deleted', 'testing deleting function', '2024-08-11 18:12:19');

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
                                                                            ('admin@gmail.com', '123', 'admin', 'Nablus', '2024-08-11 15:01:49'),
                                                                            ('ahmad123@gmail.com', '777', 'beneficiary_user', 'Japan', '2024-08-11 18:12:19'),
                                                                            ('feedbacktest@gmail.com', '321', 'beneficiary_user', 'Nablus', '2024-08-09 20:43:41'),
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
    MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=526;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
    MODIFY `notification_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orderitems`
--
ALTER TABLE `orderitems`
    MODIFY `order_item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
    MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `recipes`
--
ALTER TABLE `recipes`
    MODIFY `recipe_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
