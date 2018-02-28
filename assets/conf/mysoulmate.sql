-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 28, 2018 at 02:52 PM
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
(2, 1),
(2, 2),
(3, 4),
(4, 7),
(7, 1),
(7, 2),
(8, 4),
(8, 5),
(16, 7),
(18, 1);

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
(1, 11.1108, 35.2337, 'Tunisia', 'Chebba'),
(2, 10.2852532, 36.9173042, 'Tunisie', 'Gammarth'),
(3, 10.3329494, 36.8625853, 'Tunisie', 'Carthage'),
(4, 0, 0, 'dd', 'dd'),
(5, 0, 0, 'dd', 'dd'),
(7, 0, 0, 'dh', 'ds'),
(8, 10.310501, 36.8964803, 'Tunisie', 'La Marsa');

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
(2, 2, NULL, 3, 1),
(3, 2, '2018-02-01 00:00:00', 4, 1),
(4, 2, NULL, 5, 1),
(7, 2, NULL, 3, 2),
(8, 2, '2018-02-01 00:00:00', 4, 2),
(10, 2, '2018-02-07 00:00:00', 4, 3),
(16, 0, '2018-02-26 20:03:57', 5, 2),
(18, 1, '2018-02-27 13:28:01', 2, 2);

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
(2, 1),
(7, 2),
(3, 4),
(18, 4),
(8, 5),
(4, 7),
(10, 7),
(16, 8);

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
(1, 2, 'hhh'),
(2, 3, 'lll'),
(3, 3, 'iii'),
(4, 2, 'kkk'),
(5, 4, 'ggg'),
(6, 4, 'yyy'),
(7, 5, 'uuu'),
(8, 5, 'ttt'),
(9, 5, 'rrr');

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

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `sender_id`, `receiver_id`, `post_id`, `photo_id`, `content`, `date`) VALUES
(1, 1, 3, 1, 0, 'nice post!', '2018-02-24 00:00:00'),
(2, 3, 1, 1, 0, 'thanks !', '2018-02-24 00:00:01'),
(17, 1, 3, 10, 0, 'nice one :*', '2018-02-24 21:29:23'),
(18, 3, 1, 10, 0, '@John thank baby :kisses:', '2018-02-24 21:29:45'),
(21, 3, 1, 10, 0, '@John gotcha :D', '2018-02-24 22:31:31'),
(24, 3, 1, 10, 0, '@John fgfgd', '2018-02-25 12:58:09'),
(25, 3, 1, 11, 0, 'lollll', '2018-02-25 13:12:20'),
(26, 1, 3, 11, 0, '@Sara that\'s nice of you :D', '2018-02-25 14:44:42');

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

--
-- Dumping data for table `conversation`
--

INSERT INTO `conversation` (`id`, `person1_id`, `person2_id`, `label`, `seen`, `seen_date`) VALUES
(1, 2, 3, 'Sara', 0, '2018-02-28 12:20:30'),
(2, 1, 2, 'New conversation', 0, '2018-02-27 16:26:54'),
(3, 3, 1, 'New conversation', 0, '2018-02-27 16:28:58');

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

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `content`, `state`, `sender_id`, `date`) VALUES
(3, 'Helloo', 1, 2, '2018-02-28 13:29:27');

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

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`id`, `content`, `seen`, `seen_date`, `date`, `sender_id`, `receiver_id`) VALUES
(1, 'hello', 1, '2018-02-28 12:22:55', '2018-02-19 00:00:00', 2, 3),
(2, 'what\'s up?', 1, '2018-02-28 12:22:55', '2018-02-21 00:00:00', 3, 2),
(3, 'Badis: fghf', 1, '2018-02-28 12:22:55', '2018-02-21 14:15:27', 2, 3),
(4, 'kqsdlkjqsdq', 1, '2018-02-27 21:42:26', '2018-02-01 00:00:00', 1, 2),
(5, 'hello', 1, '2018-02-28 12:22:55', '2018-02-27 16:25:42', 2, 3),
(6, 'yoo', 1, '2018-02-27 21:42:26', '2018-02-27 16:26:54', 2, 1),
(7, 'helloooooooooooo mADAAAFAAKA', 1, '2018-02-28 12:19:38', '2018-02-27 16:28:58', 3, 1),
(8, 'YOOO', 1, '2018-02-28 12:22:55', '2018-02-28 12:20:30', 2, 3);

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
(19, 'Pofper has liked your profile.', '2018-02-27 17:50:11', NULL, 2, 3, 0, 0, 1, 0),
(20, 'ss has liked your profile.', '2018-02-28 15:43:36', NULL, 8, 2, 0, 0, 1, 0);

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
(10, 'img_5a944f60c3f07.png', 2, '2018-02-26 19:18:09', 1),
(12, 'img_5a9462e90b3bc.png', 2, '2018-02-26 20:41:29', 2),
(15, 'img_5a954c5ee4cd2.png', 3, '2018-02-27 13:17:35', 1),
(16, 'img_5a954c5ee4cd2.png', 1, '2018-02-08 00:00:00', 1);

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

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `date`, `content`, `user_id`) VALUES
(11, '2018-02-24 21:34:51', 'i\'m here', 1),
(12, '2018-02-25 16:02:49', 'lalala', 1);

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

--
-- Dumping data for table `post_reaction`
--

INSERT INTO `post_reaction` (`id`, `user_id`, `post_id`, `photo_id`, `answer_id`, `reaction`) VALUES
(21, 1, 2, 0, 0, 3),
(23, 1, 1, 0, 0, 2),
(24, 1, 0, 7, 0, 2),
(25, 1, 0, 8, 0, 2),
(26, 1, 10, 0, 0, 4),
(27, 3, 11, 0, 0, 4);

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
(2, 0),
(3, 1),
(3, 2),
(4, 0),
(4, 2),
(5, 0),
(5, 1),
(7, 0),
(7, 1),
(8, 0),
(8, 1);

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
(2, 1),
(2, 2),
(2, 3),
(3, 0),
(3, 1),
(4, 0),
(4, 1),
(4, 2),
(5, 0),
(5, 1),
(7, 0),
(7, 1),
(7, 2),
(8, 0),
(8, 1);

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
(2, 'Your favorite pet?', 4),
(3, 'Quest1', 1),
(4, 'Quest2', 2),
(5, 'Quest3', 5);

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
  `about` text NOT NULL,
  `civil_status` int(11) NOT NULL,
  `connected` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `pseudo`, `firstname`, `lastname`, `email`, `password`, `birth_date`, `gender`, `height`, `body_type`, `children_number`, `relegion`, `relegion_importance`, `smoker`, `drinker`, `min_age`, `max_age`, `phone`, `last_login`, `locked`, `ip`, `port`, `role`, `created_at`, `updated_at`, `about`, `civil_status`, `connected`) VALUES
(1, 'John', 'John', 'Ackley', 'elyes.mansour@esprit.tn', 'll', '1993-12-12', 1, 1.78, 2, 0, 0, 1, 0, 0, 18, 32, 0, '2018-02-15 00:00:00', 0, '129.125.10.2', 0, 0, '2018-02-14 00:00:00', '2018-02-21 14:01:24', 'hello baby', 0, 0),
(2, 'Pofper', 'Seif', 'Abdennadher', 'seif.abdennadher@esprit.tn', '123456', '1997-01-05', 1, 1.75, 4, 0, 0, 1, 0, 0, 20, 25, 53057885, '2018-02-28 13:29:34', 0, '127.0.0.1', 80, 1, '2018-02-14 00:00:00', '2018-02-28 15:51:17', 'I\'m so beautiful lalalala', 0, 1),
(3, 'Zeineb', 'Zeineb', 'Guermazi', 'zeineb.guermazi@esprit.tn', '123457', '2000-02-06', 0, 1.8, 6, 0, 1, 2, 0, 1, 18, 23, 26983424, '2018-02-27 17:43:14', 0, '127.0.0.1', 80, 1, '2018-02-01 00:00:00', '2018-02-28 12:19:21', '', 2, 1),
(4, 'sss', 'sss', 'ss', 'ss.dj@gm.com', '123458', '2018-02-01', 0, 1.75, 0, 0, 1, 0, 0, 0, 23, 18, 26053885, NULL, 0, NULL, 0, 1, '2018-02-28 14:55:24', '2018-02-28 15:36:54', 'jkhskjdhqsdkjhqk', 0, 1),
(5, 'ggg', 'gg', 'gg', 'gg.c@gk.com', '1234', '2018-02-01', 0, 1.75, 1, 0, 0, 0, 0, 0, 23, 18, 26352325, NULL, 0, NULL, 0, 1, '2018-02-28 14:58:52', '2018-02-28 14:58:52', 'dqsdqsddss dfss', 0, 0),
(7, 'uuu', 'uu', 'uu', 'uu.gh@gm.com', '1235', '2018-02-01', 0, 1.75, 1, 0, 0, 0, 0, 0, 23, 18, 26358236, NULL, 0, NULL, 0, 1, '2018-02-28 15:05:48', '2018-02-28 15:05:48', 'gfhjgg', 0, 0),
(8, 'ss', 'ss', 'ss', 'ss@sj.com', 'ss', '2018-02-01', 0, 1.75, 1, 0, 2, 1, 0, 0, 23, 18, 25368325, '2018-02-28 15:44:33', 0, NULL, 0, 1, '2018-02-28 15:24:58', '2018-02-28 15:44:33', 'ggg', 1, 1);

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
(2, 3, '2018-02-27 17:50:11'),
(8, 2, '2018-02-28 15:43:35');

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
-- Dumping data for table `user_signal`
--

INSERT INTO `user_signal` (`id`, `reason`, `date`, `state`, `sender_id`, `receiver_id`, `content`) VALUES
(1, 0, '2018-02-02 00:00:00', 1, 2, 3, 'exp');

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
  ADD UNIQUE KEY `pseudo` (`pseudo`),
  ADD UNIQUE KEY `email` (`email`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `choice`
--
ALTER TABLE `choice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `conversation`
--
ALTER TABLE `conversation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `photo`
--
ALTER TABLE `photo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `post_reaction`
--
ALTER TABLE `post_reaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `user_signal`
--
ALTER TABLE `user_signal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
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
