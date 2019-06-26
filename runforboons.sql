-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 26, 2019 at 08:08 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 5.6.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `boons`
--

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `id` int(11) UNSIGNED NOT NULL,
  `Com_name` varchar(50) DEFAULT NULL,
  `Com_Icon` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`id`, `Com_name`, `Com_Icon`) VALUES
(1, 'My.com', NULL),
(2, 'SG.com', NULL),
(3, 'SHOPEE.com', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `friendship`
--

CREATE TABLE `friendship` (
  `id` int(11) UNSIGNED NOT NULL,
  `Fren_id` int(11) DEFAULT NULL,
  `User_ID` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `id` int(11) UNSIGNED NOT NULL,
  `His_date` date DEFAULT NULL,
  `User_id` int(11) DEFAULT NULL,
  `Prom_ID` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`id`, `His_date`, `User_id`, `Prom_ID`) VALUES
(17, '2019-05-30', 18, 3),
(18, '2019-06-24', 25, 3),
(19, '2019-06-24', 24, 3),
(20, '2019-06-24', 27, 3);

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

CREATE TABLE `promotion` (
  `id` int(11) UNSIGNED NOT NULL,
  `Promo_name` varchar(50) DEFAULT NULL,
  `Promo_descrip` varchar(99) DEFAULT NULL,
  `Promo_term_corn` varchar(99) DEFAULT NULL,
  `Promo_price` varchar(50) DEFAULT NULL,
  `Promo_code` varchar(50) DEFAULT NULL,
  `Promo_end` date DEFAULT NULL,
  `verify` varchar(10) NOT NULL,
  `Com_ID` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `promotion`
--

INSERT INTO `promotion` (`id`, `Promo_name`, `Promo_descrip`, `Promo_term_corn`, `Promo_price`, `Promo_code`, `Promo_end`, `verify`, `Com_ID`) VALUES
(1, '10% Discount', '10% discount on selected item on My.com', '80000 steps.', '10%', '1123', '2019-05-31', '1', 1),
(2, '15% Discount', '15% discount on bus ticket on SG.com', '6000 steps in a week', '15%', '1112', '2019-05-31', '1', 2),
(3, '15% On Shopee', 'buy sellected Innergie Malaysia Official Store item to get 15% offer', '1 steps and selected item only', '15%', 'RYIN153', '2019-05-31', '1', 3);

-- --------------------------------------------------------

--
-- Table structure for table `quest`
--

CREATE TABLE `quest` (
  `id` int(11) UNSIGNED NOT NULL,
  `Quest_name` varchar(50) DEFAULT NULL,
  `Quest_descrip` varchar(99) DEFAULT NULL,
  `Quest_require` varchar(10) NOT NULL,
  `Quest_timestart` date DEFAULT NULL,
  `Quest_timeend` date DEFAULT NULL,
  `verify` varchar(10) NOT NULL,
  `Prom_ID` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `quest`
--

INSERT INTO `quest` (`id`, `Quest_name`, `Quest_descrip`, `Quest_require`, `Quest_timestart`, `Quest_timeend`, `verify`, `Prom_ID`) VALUES
(3, 'Get 15% discount on Bus ticket on SG.com', 'Run for 6000 steps in a week.', '20', '2019-05-24', '2019-05-31', '1', 2),
(4, '15% off for SHOPEE', 'run for 1 steps to redeem the 15% off on SHOPEE.com for Innergie Malaysia selected item only', '1', '2019-05-30', '2019-05-31', '1', 3);

-- --------------------------------------------------------

--
-- Table structure for table `score`
--

CREATE TABLE `score` (
  `id` int(11) UNSIGNED NOT NULL,
  `Score_day` varchar(100) DEFAULT NULL,
  `Score_month` varchar(100) DEFAULT NULL,
  `Score_year` varchar(100) DEFAULT NULL,
  `User_ID` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `score`
--

INSERT INTO `score` (`id`, `Score_day`, `Score_month`, `Score_year`, `User_ID`) VALUES
(48, '9', NULL, NULL, 27),
(49, '16', NULL, NULL, 27);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) UNSIGNED NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `userpassword` int(20) DEFAULT NULL,
  `user_icon` varchar(50) DEFAULT NULL,
  `user_joindate` date DEFAULT NULL,
  `User_totalscore` int(99) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `userpassword`, `user_icon`, `user_joindate`, `User_totalscore`) VALUES
(27, 'gan', 12, NULL, NULL, 25);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `friendship`
--
ALTER TABLE `friendship`
  ADD PRIMARY KEY (`id`),
  ADD KEY `group with` (`User_ID`);

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Redeem` (`Prom_ID`);

--
-- Indexes for table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `company By` (`Com_ID`);

--
-- Indexes for table `quest`
--
ALTER TABLE `quest`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Prom_ID` (`Prom_ID`);

--
-- Indexes for table `score`
--
ALTER TABLE `score`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Score of` (`User_ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `friendship`
--
ALTER TABLE `friendship`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `history`
--
ALTER TABLE `history`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `quest`
--
ALTER TABLE `quest`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `score`
--
ALTER TABLE `score`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `friendship`
--
ALTER TABLE `friendship`
  ADD CONSTRAINT `group with` FOREIGN KEY (`User_ID`) REFERENCES `user` (`id`);

--
-- Constraints for table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `Redeem` FOREIGN KEY (`Prom_ID`) REFERENCES `promotion` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `company By` FOREIGN KEY (`Com_ID`) REFERENCES `company` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `quest`
--
ALTER TABLE `quest`
  ADD CONSTRAINT `quest_ibfk_1` FOREIGN KEY (`Prom_ID`) REFERENCES `promotion` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `score`
--
ALTER TABLE `score`
  ADD CONSTRAINT `Score of` FOREIGN KEY (`User_ID`) REFERENCES `user` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
