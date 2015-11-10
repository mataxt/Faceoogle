-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 10, 2015 at 04:35 PM
-- Server version: 5.5.45
-- PHP Version: 5.4.45

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `faceoogle`
--

-- --------------------------------------------------------

--
-- Table structure for table `chat`
--

CREATE TABLE `chat` (
  `Id` int(11) NOT NULL,
  `Chater` varchar(255) NOT NULL,
  `Chatee` varchar(255) NOT NULL,
  `Message` text NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE `friends` (
  `User` varchar(255) NOT NULL,
  `Friend` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `friends`
--

INSERT INTO `friends` (`User`, `Friend`) VALUES
('1', '1'),
('1', '11usrname'),
('1', '3');

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `Id` int(11) NOT NULL,
  `Writer` varchar(255) NOT NULL,
  `Receiver` varchar(255) NOT NULL,
  `Body` text NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `logs`
--

INSERT INTO `logs` (`Id`, `Writer`, `Receiver`, `Body`, `Timestamp`) VALUES
(2, 'rami', 'teasdg', 'geajngeag', '2015-11-06 21:26:00'),
(55, 'geag', 'teasdg', 'teta', '2015-11-08 10:10:45'),
(56, '1', '1', 'yra', '2015-11-08 21:13:06'),
(64, '34', '1', '43531', '2015-11-10 10:54:49'),
(65, '34e4', '1', '43531', '2015-11-10 11:03:04'),
(68, '1', '1', 'gsdeg', '2015-11-10 11:07:21'),
(72, '4grd', '2', '535d', '2015-11-10 11:12:46'),
(73, '4grhtd', '2', '535d', '2015-11-10 11:13:48'),
(74, '1', '1', 'test', '2015-11-10 11:26:29');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Birthdate` date NOT NULL,
  `Gender` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`Username`, `Password`, `Name`, `Birthdate`, `Gender`) VALUES
('1', '1', '1', '1111-11-11', 'Male'),
('11usrname', '11', '11name', '2015-11-03', 'Female'),
('2', '2', '2', '1111-11-11', 'Male'),
('3', '3', '3', '3333-12-12', 'Male'),
('hsrh', 'srh', 'rrsh', '1111-11-11', 'Male'),
('ramiusername', '23', 'rami', '2015-11-03', 'man'),
('shrsr', 'srhsr', 'sdfs', '1111-11-11', 'Male'),
('teasdg', 'er5ye', 'teats', '2222-12-22', 'Female'),
('tgrehasr', 'shsrh', 'reatea', '1111-11-11', 'Female');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chat`
--
ALTER TABLE `chat`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Id` (`Id`);

--
-- Indexes for table `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`User`,`Friend`),
  ADD KEY `User` (`User`),
  ADD KEY `Friend` (`Friend`);

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chat`
--
ALTER TABLE `chat`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=94;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `friends`
--
ALTER TABLE `friends`
  ADD CONSTRAINT `DeleteUser` FOREIGN KEY (`User`) REFERENCES `users` (`Username`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
