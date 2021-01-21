
-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 04, 2016 at 07:10 AM
-- Server version: 10.0.20-MariaDB
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `u934412539_chdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `chat`
--

CREATE TABLE IF NOT EXISTS `chat` (
  `message` varchar(200) DEFAULT NULL,
  `msg_from` varchar(45) DEFAULT NULL,
  `msg_to` varchar(45) DEFAULT NULL,
  `msg_time` datetime DEFAULT NULL,
  `chatid` int(50) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`chatid`),
  KEY `fk_user_chat1_idx` (`msg_from`),
  KEY `fk_user_chat2_idx` (`msg_to`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=84 ;

--
-- Dumping data for table `chat`
--

INSERT INTO `chat` (`message`, `msg_from`, `msg_to`, `msg_time`, `chatid`) VALUES
('hiiiii', '1', '3', '2016-03-29 03:14:06', 41),
('How R U', '1', '3', '2016-03-31 03:56:09', 43),
('ki re..', '3', '1', '2016-04-08 03:02:14', 51),
(':D', '1', '3', '2016-04-08 03:03:09', 54),
('smily nilo na :p', '1', '3', '2016-04-08 03:03:25', 55),
('na re.. smiley to rakhini..', '3', '1', '2016-04-08 03:04:08', 56),
('bluestak dia', '1', '3', '2016-04-09 01:14:34', 57),
('using from mobile..', '3', '1', '2016-04-09 01:42:10', 58),
('ola', '1', '3', '2016-04-09 01:43:45', 59),
('kola', '3', '1', '2016-04-09 01:43:53', 60),
('hye galo vai.. ', '3', '1', '2016-04-09 01:48:46', 62),
('amra developer hoy gchaiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii\n', '1', '3', '2016-04-09 01:48:48', 63),
('ha...', '3', '1', '2016-04-09 01:49:23', 64),
('lets celebrate..', '3', '1', '2016-04-09 01:49:33', 66),
('yaaahhhhooooo....', '3', '1', '2016-04-09 01:53:03', 67),
('gf', '1', '3', '2016-04-10 07:22:24', 68),
('ty', '1', '3', '2016-04-10 07:22:31', 69),
('emulator thekle msg krlm..\n', '3', '1', '2016-04-13 04:58:43', 70),
('logo ta thik ache?', '1', '3', '2016-04-14 02:30:20', 71),
('ha thik e ache.\n', '3', '1', '2016-04-13 05:01:05', 72),
('add contatct e to purono ta dekhache.', '3', '1', '2016-04-13 05:01:18', 73),
('mobile theke..', '3', '1', '2016-04-14 02:45:21', 74),
('good nite', '1', '3', '2016-04-14 02:47:43', 75),
('yiiieeeppeeiiiii app is running.. haha..', '3', '1', '2016-04-14 02:48:10', 76);

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

CREATE TABLE IF NOT EXISTS `contact` (
  `userid` varchar(45) NOT NULL DEFAULT '',
  `contactid` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`,`contactid`),
  KEY `fk_contact_user2_idx` (`contactid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `contact`
--

INSERT INTO `contact` (`userid`, `contactid`) VALUES
('1', '10'),
('1', '2'),
('1', '3'),
('1', '4'),
('1', '7'),
('1', '9'),
('3', '1'),
('3', '10');

-- --------------------------------------------------------

--
-- Table structure for table `report_abuse`
--

CREATE TABLE IF NOT EXISTS `report_abuse` (
  `report by` varchar(45) NOT NULL,
  `report to` varchar(45) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`report by`),
  KEY `fk_user_report_abuse2_idx` (`report to`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `sharing`
--

CREATE TABLE IF NOT EXISTS `sharing` (
  `fileid` int(11) NOT NULL,
  `sharewith` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`fileid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `upload_file`
--

CREATE TABLE IF NOT EXISTS `upload_file` (
  `fileid` int(11) NOT NULL AUTO_INCREMENT,
  `owner` varchar(45) DEFAULT NULL,
  `file_name` varchar(45) DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`fileid`),
  KEY `fk_upload_file_user_idx` (`owner`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userid` varchar(45) NOT NULL DEFAULT '',
  `name` varchar(45) DEFAULT NULL,
  `picture` longblob,
  `contact` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `emailid` varchar(45) DEFAULT NULL,
  `block` int(11) NOT NULL DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`),
  KEY `userid` (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `name`, `picture`, `contact`, `status`, `emailid`, `block`, `deleted`) VALUES
('1', 'Santanu', NULL, '1', 'Dead and Gone', 'santanu@gmail.com', 0, 0),
('10', 'Neel', NULL, '10', 'Every thing u need nothing u dont..', 'neelroy@gmail.com', 0, 0),
('2', 'Akash', NULL, '2', 'heellllooooo....', 'akashmishra@gmail.com', 0, 0),
('4', 'Ringo', NULL, '4', 'Hiiii all..', 'rockingringo@gmail.com', 0, 0),
('7', 'Sourav', NULL, '7', 'I''m cool..', 'crsouravbanik@gmail.com', 0, 0),
('9', 'Rahul', NULL, '9', 'I''m fine..', 'rahulmithsharma@gmail.com', 0, 0),
('3', 'Tirthankar', NULL, '3', 'Love is the greatest refreshment in life..', 'tirthankar@gmail.com', 0, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
