-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 28, 2018 at 10:50 PM
-- Server version: 5.7.14
-- PHP Version: 7.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mysoulmate`
--

-- --------------------------------------------------------

--
-- Table structure for table `accepted_choice`
--

CREATE TABLE `accepted_choice` (
  `answer_id` int(11) NOT NULL,
  `choice_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accepted_choice`
--

INSERT INTO `accepted_choice` (`answer_id`, `choice_id`) VALUES
(38, 24),
(39, 88),
(40, 72),
(40, 73),
(41, 115),
(41, 118),
(42, 94),
(42, 95),
(43, 98),
(43, 99),
(44, 67),
(44, 68),
(44, 69),
(45, 86),
(45, 87),
(46, 46),
(46, 47),
(46, 48),
(46, 49),
(47, 119),
(47, 120),
(48, 18),
(48, 19),
(49, 104),
(49, 105),
(50, 64),
(50, 65),
(51, 33),
(51, 34),
(52, 38),
(52, 39),
(53, 68),
(54, 17),
(54, 18),
(55, 33),
(56, 119),
(56, 120),
(57, 94),
(57, 95),
(58, 104),
(58, 105),
(59, 24),
(59, 26),
(60, 37),
(60, 38),
(61, 88),
(62, 99),
(63, 46),
(63, 48),
(63, 49),
(64, 115),
(64, 116),
(65, 86),
(66, 73),
(67, 64),
(68, 31),
(69, 16),
(70, 33),
(71, 73),
(72, 67),
(72, 68),
(72, 69),
(73, 120),
(74, 104),
(75, 37),
(75, 38),
(76, 99),
(77, 88),
(78, 87),
(79, 64),
(80, 46),
(80, 47),
(81, 116),
(81, 117),
(82, 95),
(83, 24),
(83, 25),
(83, 26),
(84, 114),
(85, 115),
(85, 116),
(86, 73),
(87, 104),
(88, 120),
(89, 17),
(90, 67),
(90, 68),
(90, 69),
(91, 86),
(91, 87),
(92, 24),
(92, 25),
(93, 98),
(93, 99),
(94, 37),
(94, 38),
(94, 39),
(95, 34),
(96, 46),
(96, 47),
(96, 48),
(97, 88),
(97, 89),
(98, 95),
(99, 64),
(99, 65),
(100, 70),
(100, 71),
(101, 121),
(101, 122),
(101, 123),
(102, 64),
(102, 65),
(103, 33),
(103, 34),
(104, 98),
(104, 99),
(105, 16),
(105, 17),
(105, 18),
(105, 19),
(106, 24),
(106, 25),
(106, 26),
(107, 89),
(108, 115),
(108, 116),
(108, 117),
(109, 72),
(109, 73),
(110, 104),
(110, 105),
(111, 46),
(112, 86),
(112, 87),
(113, 37),
(113, 38),
(113, 39),
(114, 94),
(114, 95),
(115, 67),
(115, 68),
(115, 69),
(116, 119),
(116, 120),
(117, 37),
(117, 38),
(118, 72),
(118, 73),
(119, 33),
(119, 34),
(120, 98),
(121, 94),
(121, 95),
(122, 119),
(122, 120),
(123, 115),
(123, 116),
(123, 117),
(124, 104),
(124, 105),
(125, 67),
(125, 68),
(126, 16),
(126, 17),
(126, 18),
(127, 86),
(127, 87),
(128, 88),
(128, 89),
(129, 24),
(129, 26),
(129, 27),
(130, 64),
(130, 65),
(131, 46),
(131, 47),
(131, 48),
(132, 88),
(133, 64),
(133, 65),
(134, 37),
(134, 38),
(135, 46),
(135, 47),
(136, 16),
(136, 17),
(137, 116),
(137, 117),
(138, 72),
(138, 73),
(139, 98),
(140, 67),
(140, 68),
(140, 69),
(141, 119),
(141, 120),
(142, 33),
(142, 34),
(143, 94),
(143, 95),
(144, 24),
(144, 25),
(144, 26),
(145, 86),
(145, 87),
(146, 104);

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `user_id` int(11) NOT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`user_id`, `longitude`, `latitude`, `country`, `city`) VALUES
(12, 10.310501, 36.8964803, 'Tunisie', 'La Marsa'),
(13, 10.2319757, 36.7435003, 'Tunisie', 'Ben Arous'),
(14, 10.1647233, 36.86653669999999, 'Tunisie', 'Ariana'),
(15, 10.1647233, 36.84523469999999, 'Tunisie', 'El Menzah'),
(16, 10.3329494, 36.8625853, 'Tunisie', 'Carthage'),
(17, 10.1815316, 36.8064948, 'Tunisie', 'Tunis'),
(18, 10.2852532, 36.8587639, 'Tunisie', 'Ain Zaghouan'),
(19, 10.0863269, 36.8093284, 'Tunisie', 'Manouba');

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `importance` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`id`, `importance`, `date`, `question_id`, `user_id`) VALUES
(38, 1, '2018-02-28 22:34:15', 11, 12),
(39, 1, '2018-02-28 22:34:19', 34, 12),
(40, 1, '2018-02-28 22:34:23', 27, 12),
(41, 0, '2018-02-28 22:34:33', 46, 12),
(42, 2, '2018-02-28 22:34:42', 37, 12),
(43, 1, '2018-02-28 22:34:50', 39, 12),
(44, 0, '2018-02-28 22:34:55', 25, 12),
(45, 1, '2018-02-28 22:35:02', 33, 12),
(46, 0, '2018-02-28 22:35:06', 19, 12),
(47, 1, '2018-02-28 22:35:15', 47, 12),
(48, 0, '2018-02-28 22:35:24', 8, 12),
(49, 1, '2018-02-28 22:35:33', 41, 12),
(50, 2, '2018-02-28 22:35:41', 24, 12),
(51, 1, '2018-02-28 22:35:48', 14, 12),
(52, 0, '2018-02-28 22:35:52', 16, 12),
(53, 0, '2018-02-28 22:51:18', 25, 13),
(54, 0, '2018-02-28 22:51:22', 8, 13),
(55, 0, '2018-02-28 22:51:25', 14, 13),
(56, 0, '2018-02-28 22:51:28', 47, 13),
(57, 0, '2018-02-28 22:51:30', 37, 13),
(58, 0, '2018-02-28 22:51:33', 41, 13),
(59, 0, '2018-02-28 22:51:36', 11, 13),
(60, 1, '2018-02-28 22:51:40', 16, 13),
(61, 1, '2018-02-28 22:51:43', 34, 13),
(62, 1, '2018-02-28 22:51:46', 39, 13),
(63, 1, '2018-02-28 22:51:50', 19, 13),
(64, 1, '2018-02-28 22:51:54', 46, 13),
(65, 0, '2018-02-28 22:51:56', 33, 13),
(66, 0, '2018-02-28 22:51:59', 27, 13),
(67, 0, '2018-02-28 22:52:02', 24, 13),
(68, 0, '2018-02-28 22:52:11', 13, 13),
(69, 0, '2018-02-28 22:54:38', 8, 14),
(70, 0, '2018-02-28 22:54:42', 14, 14),
(71, 1, '2018-02-28 22:54:47', 27, 14),
(72, 0, '2018-02-28 22:54:51', 25, 14),
(73, 0, '2018-02-28 22:54:55', 47, 14),
(74, 0, '2018-02-28 22:54:57', 41, 14),
(75, 0, '2018-02-28 22:55:01', 16, 14),
(76, 0, '2018-02-28 22:55:04', 39, 14),
(77, 1, '2018-02-28 22:55:07', 34, 14),
(78, 2, '2018-02-28 22:55:10', 33, 14),
(79, 0, '2018-02-28 22:55:13', 24, 14),
(80, 0, '2018-02-28 22:55:15', 19, 14),
(81, 0, '2018-02-28 22:55:18', 46, 14),
(82, 0, '2018-02-28 22:55:20', 37, 14),
(83, 0, '2018-02-28 22:55:23', 11, 14),
(84, 0, '2018-02-28 22:55:25', 45, 14),
(85, 1, '2018-02-28 22:57:12', 46, 15),
(86, 1, '2018-02-28 22:57:16', 27, 15),
(87, 1, '2018-02-28 22:57:18', 41, 15),
(88, 1, '2018-02-28 22:57:24', 47, 15),
(89, 0, '2018-02-28 22:57:26', 8, 15),
(90, 0, '2018-02-28 22:57:29', 25, 15),
(91, 0, '2018-02-28 22:57:32', 33, 15),
(92, 1, '2018-02-28 22:57:36', 11, 15),
(93, 2, '2018-02-28 22:57:40', 39, 15),
(94, 2, '2018-02-28 22:57:47', 16, 15),
(95, 1, '2018-02-28 22:57:50', 14, 15),
(96, 0, '2018-02-28 22:57:54', 19, 15),
(97, 0, '2018-02-28 22:57:56', 34, 15),
(98, 0, '2018-02-28 22:57:59', 37, 15),
(99, 0, '2018-02-28 22:58:02', 24, 15),
(100, 0, '2018-02-28 22:58:05', 26, 15),
(101, 0, '2018-02-28 22:58:08', 48, 15),
(102, 0, '2018-02-28 23:02:54', 24, 16),
(103, 1, '2018-02-28 23:02:58', 14, 16),
(104, 1, '2018-02-28 23:03:03', 39, 16),
(105, 2, '2018-02-28 23:03:08', 8, 16),
(106, 0, '2018-02-28 23:03:11', 11, 16),
(107, 1, '2018-02-28 23:03:14', 34, 16),
(108, 1, '2018-02-28 23:03:18', 46, 16),
(109, 2, '2018-02-28 23:03:22', 27, 16),
(110, 2, '2018-02-28 23:03:26', 41, 16),
(111, 2, '2018-02-28 23:03:29', 19, 16),
(112, 0, '2018-02-28 23:03:33', 33, 16),
(113, 2, '2018-02-28 23:03:38', 16, 16),
(114, 0, '2018-02-28 23:03:41', 37, 16),
(115, 1, '2018-02-28 23:03:47', 25, 16),
(116, 1, '2018-02-28 23:03:52', 47, 16),
(117, 2, '2018-02-28 23:06:17', 16, 17),
(118, 0, '2018-02-28 23:06:20', 27, 17),
(119, 1, '2018-02-28 23:07:52', 14, 17),
(120, 2, '2018-02-28 23:07:57', 39, 17),
(121, 0, '2018-02-28 23:08:00', 37, 17),
(122, 2, '2018-02-28 23:08:04', 47, 17),
(123, 1, '2018-02-28 23:08:09', 46, 17),
(124, 0, '2018-02-28 23:08:13', 41, 17),
(125, 1, '2018-02-28 23:08:17', 25, 17),
(126, 1, '2018-02-28 23:08:21', 8, 17),
(127, 1, '2018-02-28 23:08:25', 33, 17),
(128, 2, '2018-02-28 23:08:28', 34, 17),
(129, 0, '2018-02-28 23:08:32', 11, 17),
(130, 0, '2018-02-28 23:08:36', 24, 17),
(131, 1, '2018-02-28 23:08:42', 19, 17),
(132, 1, '2018-02-28 23:16:47', 34, 18),
(133, 0, '2018-02-28 23:16:50', 24, 18),
(134, 0, '2018-02-28 23:16:52', 16, 18),
(135, 0, '2018-02-28 23:16:56', 19, 18),
(136, 1, '2018-02-28 23:16:59', 8, 18),
(137, 0, '2018-02-28 23:17:01', 46, 18),
(138, 0, '2018-02-28 23:17:04', 27, 18),
(139, 0, '2018-02-28 23:17:06', 39, 18),
(140, 0, '2018-02-28 23:17:08', 25, 18),
(141, 0, '2018-02-28 23:17:12', 47, 18),
(142, 0, '2018-02-28 23:17:15', 14, 18),
(143, 1, '2018-02-28 23:17:18', 37, 18),
(144, 0, '2018-02-28 23:17:21', 11, 18),
(145, 0, '2018-02-28 23:17:25', 33, 18),
(146, 2, '2018-02-28 23:17:29', 41, 18);

-- --------------------------------------------------------

--
-- Table structure for table `answer_choice`
--

CREATE TABLE `answer_choice` (
  `answer_id` int(11) NOT NULL,
  `choice_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer_choice`
--

INSERT INTO `answer_choice` (`answer_id`, `choice_id`) VALUES
(69, 17),
(89, 17),
(105, 17),
(126, 17),
(136, 17),
(48, 19),
(54, 19),
(59, 24),
(129, 24),
(144, 24),
(38, 25),
(83, 25),
(106, 25),
(92, 26),
(68, 30),
(51, 33),
(70, 33),
(95, 33),
(119, 33),
(55, 34),
(103, 34),
(142, 34),
(113, 37),
(52, 38),
(75, 38),
(117, 38),
(60, 39),
(94, 39),
(134, 39),
(63, 47),
(80, 47),
(96, 47),
(111, 47),
(46, 48),
(131, 48),
(135, 49),
(50, 64),
(67, 65),
(79, 65),
(99, 65),
(102, 65),
(130, 65),
(133, 65),
(72, 67),
(140, 67),
(44, 68),
(53, 68),
(90, 68),
(115, 68),
(125, 68),
(100, 70),
(40, 72),
(66, 72),
(71, 72),
(86, 72),
(109, 72),
(138, 72),
(118, 73),
(78, 86),
(91, 86),
(112, 86),
(145, 86),
(45, 87),
(65, 87),
(127, 87),
(97, 88),
(132, 88),
(39, 89),
(61, 89),
(77, 89),
(107, 89),
(128, 89),
(42, 94),
(57, 94),
(82, 94),
(98, 94),
(143, 94),
(114, 95),
(121, 95),
(43, 98),
(62, 98),
(76, 98),
(93, 98),
(104, 98),
(120, 98),
(139, 99),
(49, 104),
(87, 104),
(110, 104),
(124, 104),
(146, 104),
(58, 105),
(74, 105),
(84, 113),
(108, 115),
(81, 116),
(85, 116),
(123, 116),
(64, 117),
(137, 117),
(41, 118),
(47, 119),
(73, 119),
(88, 119),
(122, 119),
(141, 119),
(56, 120),
(116, 120),
(101, 122);

-- --------------------------------------------------------

--
-- Table structure for table `choice`
--

CREATE TABLE `choice` (
  `id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `choice` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `choice`
--

INSERT INTO `choice` (`id`, `question_id`, `choice`) VALUES
(16, 8, 'Often'),
(17, 8, 'Sometimes'),
(18, 8, 'Rarely'),
(19, 8, 'Never'),
(20, 9, 'Yes'),
(21, 9, 'No'),
(22, 10, 'Kissing in Paris'),
(23, 10, 'Kissing in a tent, in the woods'),
(24, 11, 'Yes, I have a fulltime job'),
(25, 11, 'No'),
(26, 11, 'I have a part-time job'),
(27, 11, 'I\'m a student'),
(28, 12, 'Yes'),
(29, 12, 'No'),
(30, 13, 'es, it\'s not a problem for me'),
(31, 13, 'Yes, if I thought the debt were justified'),
(32, 13, 'No'),
(33, 14, 'Yes'),
(34, 14, 'No'),
(37, 16, 'Yes'),
(38, 16, 'No'),
(39, 16, 'Rarely'),
(40, 17, 'Liberal / Left-wing'),
(41, 17, 'Centrist'),
(42, 17, 'Conservative / Right-wing'),
(43, 17, 'Other'),
(44, 18, 'Yes'),
(45, 18, 'No'),
(46, 19, 'Extremely important'),
(47, 19, 'Somewhat important'),
(48, 19, 'Not very important'),
(49, 19, 'Not important at all'),
(50, 20, 'Carefree'),
(51, 20, 'Intense'),
(52, 21, 'One night'),
(53, 21, 'A few months to a year'),
(54, 21, 'Several years'),
(55, 21, 'The rest of my life'),
(56, 22, 'Yes, usually'),
(57, 22, 'About half the time'),
(58, 22, 'No, usually'),
(59, 22, 'I don\'t know'),
(60, 23, 'Very, very high'),
(61, 23, 'Higher than average'),
(62, 23, 'Average'),
(63, 23, 'Below average'),
(64, 24, 'Yes, absolutely !'),
(65, 24, 'Not really'),
(66, 24, 'No way !'),
(67, 25, 'Very important'),
(68, 25, 'Somewhat important'),
(69, 25, 'Not important at all'),
(70, 26, 'Passion'),
(71, 26, 'Dedication'),
(72, 27, 'Yes'),
(73, 27, 'No'),
(74, 28, 'Yes'),
(75, 28, 'No'),
(76, 29, 'Yes'),
(77, 29, 'No'),
(78, 29, 'Yes, to some extent'),
(79, 30, 'Yes'),
(80, 30, 'No'),
(81, 31, 'Yes'),
(82, 31, 'No'),
(83, 32, 'Logical'),
(84, 32, 'Social'),
(85, 32, 'Artistic'),
(86, 33, 'Yes'),
(87, 33, 'No'),
(88, 34, 'Yes'),
(89, 34, 'No'),
(90, 35, 'Yes'),
(91, 35, 'No'),
(92, 36, 'Yes'),
(93, 36, 'No'),
(94, 37, 'Yes'),
(95, 37, 'No'),
(96, 38, 'Good'),
(97, 38, 'Bad'),
(98, 39, 'Yes'),
(99, 39, 'No'),
(100, 40, 'Always'),
(101, 40, 'Usually'),
(102, 40, 'Rarely'),
(103, 40, 'Never'),
(104, 41, 'Yes'),
(105, 41, 'No'),
(110, 44, 'Always'),
(111, 44, 'Sometimes, but I do my best not to be'),
(112, 44, 'Never'),
(113, 45, 'Yes'),
(114, 45, 'No'),
(115, 46, 'Love'),
(116, 46, 'Wealth'),
(117, 46, 'Expression'),
(118, 46, 'Knowledge'),
(119, 47, 'Yes'),
(120, 47, 'No'),
(121, 48, 'Split the bill'),
(122, 48, 'Pay the whole bill'),
(123, 48, 'Have them pay the whole bill'),
(124, 48, 'It doesn\'t matter to me'),
(129, 50, 'Universal'),
(130, 50, 'Relative'),
(131, 51, 'Yes'),
(132, 51, 'No');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `photo_id` int(11) NOT NULL,
  `content` text NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `conversation`
--

CREATE TABLE `conversation` (
  `id` int(11) NOT NULL,
  `person1_id` int(11) DEFAULT NULL,
  `person2_id` int(11) DEFAULT NULL,
  `label` varchar(100) DEFAULT NULL,
  `seen` tinyint(1) DEFAULT NULL,
  `seen_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `content` text,
  `state` tinyint(1) DEFAULT NULL,
  `sender_id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `content` text,
  `seen` tinyint(1) DEFAULT NULL,
  `seen_date` datetime DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `content` text,
  `date` datetime DEFAULT NULL,
  `icon` text,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `photo_id` int(11) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `seen` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`id`, `content`, `date`, `icon`, `sender_id`, `receiver_id`, `photo_id`, `post_id`, `type`, `seen`) VALUES
(20, 'Elyes has liked your profile.', '2018-02-28 23:09:04', NULL, 17, 15, 0, 0, 1, 0),
(21, 'Elyes has liked your profile.', '2018-02-28 23:10:31', NULL, 17, 14, 0, 0, 1, 0),
(22, 'Hamdi has liked your profile.', '2018-02-28 23:20:04', NULL, 18, 13, 0, 0, 1, 0),
(23, 'Hamdi has liked your profile.', '2018-02-28 23:26:33', NULL, 18, 15, 0, 0, 1, 0),
(24, 'Hamdi has liked your profile.', '2018-02-28 23:48:25', NULL, 18, 15, 0, 0, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `photo`
--

CREATE TABLE `photo` (
  `id` int(11) NOT NULL,
  `url` text,
  `user_id` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `photo`
--

INSERT INTO `photo` (`id`, `url`, `user_id`, `date`, `type`) VALUES
(18, 'img_5a972277aa3b2.png', 12, '2018-02-28 22:43:20', 1),
(19, 'img_5a9722b250bf7.png', 12, '2018-02-28 22:44:18', 2),
(20, 'img_5a97249cd7e86.png', 13, '2018-02-28 22:52:29', 1),
(21, 'img_5a9725775654a.png', 14, '2018-02-28 22:56:07', 1),
(22, 'img_5a97275e586f9.png', 16, '2018-02-28 23:04:14', 1),
(23, 'img_5a97276f1979e.png', 16, '2018-02-28 23:04:31', 0),
(24, 'img_5a97289f60832.png', 17, '2018-02-28 23:09:35', 1),
(25, 'img_5a9728b94c8eb.png', 17, '2018-02-28 23:10:01', 2),
(27, 'img_5a972ae63925c.png', 18, '2018-02-28 23:19:18', 1),
(28, 'img_5a972bc2a17bd.png', 15, '2018-02-28 23:22:59', 1),
(29, 'img_5a972bd5bf3c6.png', 15, '2018-02-28 23:23:18', 0);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `content` text,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `post_reaction`
--

CREATE TABLE `post_reaction` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) DEFAULT NULL,
  `photo_id` int(11) DEFAULT NULL,
  `answer_id` int(11) DEFAULT NULL,
  `reaction` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `prefered_relation`
--

CREATE TABLE `prefered_relation` (
  `user_id` int(11) NOT NULL,
  `relation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prefered_relation`
--

INSERT INTO `prefered_relation` (`user_id`, `relation`) VALUES
(12, 0),
(12, 1),
(13, 0),
(13, 1),
(14, 0),
(14, 1),
(15, 0),
(15, 1),
(15, 2),
(16, 0),
(16, 1),
(16, 2),
(17, 0),
(17, 1),
(17, 2),
(18, 1),
(18, 2),
(19, 0),
(19, 1);

-- --------------------------------------------------------

--
-- Table structure for table `prefered_status`
--

CREATE TABLE `prefered_status` (
  `user_id` int(11) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prefered_status`
--

INSERT INTO `prefered_status` (`user_id`, `status`) VALUES
(12, 0),
(12, 2),
(13, 0),
(13, 1),
(14, 0),
(14, 1),
(14, 2),
(15, 0),
(16, 1),
(16, 2),
(17, 0),
(18, 0),
(18, 1),
(19, 0),
(19, 2);

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `question` text NOT NULL,
  `topic` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `question`, `topic`) VALUES
(8, 'How frequently do you drink alcohol?', 5),
(9, 'Could you date someone who was really messy?', 1),
(10, 'Choose the better romantic activity', 1),
(11, 'Are you currently employed?', 5),
(12, 'Is jealousy healthy in a relationship?', 1),
(13, 'Would you date someone who was in considerable debt?', 1),
(14, 'Would you strongly prefer to date someone of your own skin color / racial background?', 5),
(16, 'Do you smoke?', 5),
(17, 'Which best describes your political beliefs?', 4),
(18, 'Do you enjoy discussing politics?', 4),
(19, 'How important is religion/God in your life?', 5),
(20, 'Which word describes you better?', 4),
(21, 'About how long do you want your next relationship to last?', 1),
(22, 'Can you tell from someone’s online dating profile whether or not you’ll get along in real life?', 1),
(23, 'Rate your self-confidence:', 4),
(24, 'Are pets a neccessity in your life ?', 5),
(25, 'How important is money/wealth for you in a match?', 5),
(26, 'Which makes for a better relationship?', 1),
(27, 'Do you enjoy intense intellectual conversations?', 5),
(28, 'Do you follow movies/shows ?', 4),
(29, 'Would you consider yourself a feminist?', 4),
(30, 'Do spelling mistakes annoy you?', 4),
(31, 'Do you often find yourself worrying about things that you have no control over?', 4),
(32, 'Which of the following types of intelligence do you value most?', 1),
(33, 'Do you take drugs ?', 5),
(34, 'Are you either vegetarian or vegan?', 5),
(35, 'Should religion be taught is schools ?', 2),
(36, 'Do you believe in the woman\'s right for abortion ?', 2),
(37, 'Do you ok with sex before marriage ?', 5),
(38, 'If you don\'t do anything at all for an entire day, how does that make you feel?', 4),
(39, 'Are you looking for marriage ?', 5),
(40, 'How often are you open with your feelings?', 4),
(41, 'Could you date someone who was really quiet?', 5),
(44, 'Are you usually late for meetings ?', 4),
(45, 'Are you a morning person ?', 4),
(46, 'If you had to name your greatest motivation in life thus far, what would it be?', 5),
(47, 'Do you think homosexuality is a sin?', 5),
(48, 'It’s your first date. Do you ...', 1),
(50, 'Do you believe morality is universal, or relative?', 2),
(51, 'Do you read books ?', 4);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `pseudo` varchar(100) DEFAULT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `height` float DEFAULT NULL,
  `body_type` int(11) DEFAULT NULL,
  `children_number` int(11) DEFAULT NULL,
  `relegion` int(11) DEFAULT NULL,
  `relegion_importance` int(11) DEFAULT NULL,
  `smoker` tinyint(1) DEFAULT NULL,
  `drinker` tinyint(1) DEFAULT NULL,
  `min_age` int(11) DEFAULT NULL,
  `max_age` int(11) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `locked` smallint(6) DEFAULT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `role` tinyint(1) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `about` text,
  `civil_status` int(11) DEFAULT NULL,
  `connected` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `pseudo`, `firstname`, `lastname`, `email`, `password`, `birth_date`, `gender`, `height`, `body_type`, `children_number`, `relegion`, `relegion_importance`, `smoker`, `drinker`, `min_age`, `max_age`, `phone`, `last_login`, `locked`, `ip`, `port`, `role`, `created_at`, `updated_at`, `about`, `civil_status`, `connected`) VALUES
(10, 'Admin', 'Malek', 'Guirat', 'mysoulmatePI@gmail.com', 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(12, 'Bochra', 'Bochra', 'Khamessi', 'chebbaelyes@gmail.com', 'bochra', '1992-02-11', 0, 1.75, 4, 0, 0, 2, 1, 0, 30, 22, 53057885, '2018-02-28 22:49:15', 0, NULL, 0, 1, '2018-02-28 21:43:17', '2018-02-28 22:49:15', 'I\'m a simple girl.', 0, 0),
(13, 'Eya', 'Eya', 'Mernissi', 'chebbaelyes@gmail.com', 'eya', '1996-05-15', 0, 1.8, 3, 0, 0, 0, 0, 1, 25, 20, 53057144, '2018-02-28 22:53:17', 0, NULL, 0, 1, '2018-02-28 21:48:23', '2018-02-28 22:53:17', 'I\'m a passionate girl that loves watching romantic movies and drinking hot chocolate .', 0, 0),
(14, 'Ferdaous', 'Ferdaous', 'Zribi', 'chebbaelyes@gmail.com', 'ferdaous', '1975-09-08', 0, 1.7, 1, 1, 1, 1, 0, 0, 55, 40, 20582632, '2018-02-28 22:56:19', 0, NULL, 0, 1, '2018-02-28 21:52:01', '2018-02-28 22:56:19', 'I\'m a free spirit that loves being in music fests ,  I hate drama , and will love to stay up until 3 am talking about my favorite movies .', 1, 0),
(15, 'Hanene', 'Hanene', 'Ben Fraj', 'chebbaelyes@gmail.com', 'hanene', '1990-06-12', 0, 1.75, 2, 0, 0, 2, 0, 0, 35, 30, 42758965, '2018-02-28 23:24:13', 0, NULL, 0, 1, '2018-02-28 21:55:39', '2018-02-28 23:24:13', 'I have mostly guy friends because I don’t get along with girls. Will love to dive into deep talks . Nerd by choice .', 0, 0),
(16, 'Ines', 'Ines', 'El Khemiri', 'chebbaelyes@gmail.com', 'ines', '1985-09-24', 0, 1.87, 3, 0, 0, 0, 0, 1, 40, 30, 23658745, '2018-02-28 23:05:54', 0, NULL, 0, 1, '2018-02-28 21:58:48', '2018-02-28 23:05:54', 'I\'m the women that everybody takes for too perfect to reach , and everyone believes that i have a boyfriend so no one bothers flirting me , that\'s why i\'m on MySoulMate !', 1, 0),
(17, 'Elyes', 'Elyes', 'Tlili', 'chebbaelyes@gmail.com', 'elyes', '1993-02-03', 1, 1.85, 5, 0, 0, 2, 1, 1, 25, 20, 25417215, '2018-02-28 23:14:02', 0, NULL, 0, 1, '2018-02-28 22:02:45', '2018-02-28 23:14:02', 'Loves video games and seeking to meet a girl that shares the same passion . Our perfect date will be spending a night with our Guild fighting bosses !', 0, 0),
(18, 'Hamdi', 'Hamdi', 'Wessleti', 'chebbaelyes@gmail.com', 'hamdi', '1983-02-16', 1, 1.84, 2, 0, 0, 1, 0, 0, 35, 25, 52568471, '2018-02-28 23:49:41', 0, NULL, 0, 1, '2018-02-28 22:06:31', '2018-02-28 23:49:41', 'Here for the laughs , and mostly to meet new people from different entourages that i\'ve been stuck with my whole life .', 2, 0),
(19, 'Ahmed', 'Ahmed', 'Ferchichi', 'chebbaelyes@gmail.com', 'ahmed', '1997-02-25', 1, 1.9, 5, 0, 0, 0, 1, 0, 23, 18, 26532547, NULL, 0, NULL, 0, 1, '2018-02-28 22:09:27', '2018-02-28 22:09:27', 'Heard about this amazing app so just checking it out . As for myself i like any girl that ain\'t a drama queen .', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_block`
--

CREATE TABLE `user_block` (
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_like`
--

CREATE TABLE `user_like` (
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_like`
--

INSERT INTO `user_like` (`sender_id`, `receiver_id`, `date`) VALUES
(18, 15, '2018-02-28 23:48:25');

-- --------------------------------------------------------

--
-- Table structure for table `user_signal`
--

CREATE TABLE `user_signal` (
  `id` int(11) NOT NULL,
  `reason` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `content` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accepted_choice`
--
ALTER TABLE `accepted_choice`
  ADD PRIMARY KEY (`choice_id`,`answer_id`),
  ADD KEY `answer_id` (`answer_id`);

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `question_id` (`question_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `answer_choice`
--
ALTER TABLE `answer_choice`
  ADD PRIMARY KEY (`answer_id`,`choice_id`),
  ADD KEY `choice_id` (`choice_id`);

--
-- Indexes for table `choice`
--
ALTER TABLE `choice`
  ADD PRIMARY KEY (`id`,`question_id`),
  ADD KEY `question_choice` (`question_id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sender_id` (`sender_id`),
  ADD KEY `receiver_id` (`receiver_id`),
  ADD KEY `post_id` (`post_id`),
  ADD KEY `photo_id` (`photo_id`);

--
-- Indexes for table `conversation`
--
ALTER TABLE `conversation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `person1_id` (`person1_id`),
  ADD KEY `person2_id` (`person2_id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sender_id` (`sender_id`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sender_id` (`sender_id`),
  ADD KEY `receiver_id` (`receiver_id`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sender_id` (`sender_id`),
  ADD KEY `receiver_id` (`receiver_id`),
  ADD KEY `photo_id` (`photo_id`,`post_id`),
  ADD KEY `answer_notification` (`post_id`);

--
-- Indexes for table `photo`
--
ALTER TABLE `photo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `post_reaction`
--
ALTER TABLE `post_reaction`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `post_reaction` (`post_id`),
  ADD KEY `fk_user` (`user_id`);

--
-- Indexes for table `prefered_relation`
--
ALTER TABLE `prefered_relation`
  ADD PRIMARY KEY (`user_id`,`relation`);

--
-- Indexes for table `prefered_status`
--
ALTER TABLE `prefered_status`
  ADD PRIMARY KEY (`user_id`,`status`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `pseudo` (`pseudo`);

--
-- Indexes for table `user_block`
--
ALTER TABLE `user_block`
  ADD PRIMARY KEY (`sender_id`,`receiver_id`),
  ADD KEY `user_block_receiver` (`receiver_id`);

--
-- Indexes for table `user_like`
--
ALTER TABLE `user_like`
  ADD PRIMARY KEY (`sender_id`,`receiver_id`),
  ADD KEY `user_like_receiver` (`receiver_id`);

--
-- Indexes for table `user_signal`
--
ALTER TABLE `user_signal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sender_id` (`sender_id`),
  ADD KEY `receiver_id` (`receiver_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=147;
--
-- AUTO_INCREMENT for table `choice`
--
ALTER TABLE `choice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=133;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `conversation`
--
ALTER TABLE `conversation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT for table `photo`
--
ALTER TABLE `photo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `post_reaction`
--
ALTER TABLE `post_reaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `user_signal`
--
ALTER TABLE `user_signal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `accepted_choice`
--
ALTER TABLE `accepted_choice`
  ADD CONSTRAINT `accept_choice_answer` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `accept_choice_choice` FOREIGN KEY (`choice_id`) REFERENCES `choice` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `user_address` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `answer_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_answer` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `answer_choice`
--
ALTER TABLE `answer_choice`
  ADD CONSTRAINT `answer_id` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `choice_id` FOREIGN KEY (`choice_id`) REFERENCES `choice` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `choice`
--
ALTER TABLE `choice`
  ADD CONSTRAINT `question_choice` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `fk_receiver` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_sender` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `conversation`
--
ALTER TABLE `conversation`
  ADD CONSTRAINT `person1_user` FOREIGN KEY (`person1_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `person2_user` FOREIGN KEY (`person2_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_sender` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `message_receiver` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `message_sender` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `receiver_notification` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `sender_notification` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `photo`
--
ALTER TABLE `photo`
  ADD CONSTRAINT `user_photo` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `user_post` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `post_reaction`
--
ALTER TABLE `post_reaction`
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `prefered_relation`
--
ALTER TABLE `prefered_relation`
  ADD CONSTRAINT `prefered_relation_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `prefered_status`
--
ALTER TABLE `prefered_status`
  ADD CONSTRAINT `prefered_status_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `user_block`
--
ALTER TABLE `user_block`
  ADD CONSTRAINT `user_block_receiver` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_block_sender` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `user_like`
--
ALTER TABLE `user_like`
  ADD CONSTRAINT `user_like_receiver` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_like_sender` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `user_signal`
--
ALTER TABLE `user_signal`
  ADD CONSTRAINT `signal_receiver` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `signal_sender` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
