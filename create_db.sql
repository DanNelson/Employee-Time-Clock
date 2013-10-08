-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 08, 2013 at 05:19 AM
-- Server version: 5.5.25
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `db`
--

-- --------------------------------------------------------

--
-- Table structure for table `Employees`
--

CREATE TABLE `Employees` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `First_Name` varchar(254) DEFAULT NULL,
  `Last_Name` varchar(254) DEFAULT NULL,
  `Id_Number` int(10) DEFAULT NULL,
  `Level` varchar(25) DEFAULT NULL,
  `Wage` double DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Time_Clock`
--

CREATE TABLE `Time_Clock` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `Id_Number` int(10) DEFAULT NULL,
  `Time_In` bigint(20) DEFAULT NULL,
  `Time_Out` bigint(20) DEFAULT NULL,
  `Total_Time` bigint(20) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;
