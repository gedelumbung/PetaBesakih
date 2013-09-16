-- phpMyAdmin SQL Dump
-- version 2.11.2.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 09, 2011 at 06:59 AM
-- Server version: 5.0.45
-- PHP Version: 5.2.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `databaseku`
--

-- --------------------------------------------------------

--
-- Table structure for table `peta_icon`
--

CREATE TABLE `peta_icon` (
  `nomor` int(5) NOT NULL auto_increment,
  `nama` varchar(100) NOT NULL,
  `jenis` varchar(100) NOT NULL,
  `deskripsi` tinytext NOT NULL,
  `lat` double NOT NULL,
  `lng` double NOT NULL,
  PRIMARY KEY  (`nomor`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `peta_icon`
--

INSERT INTO `peta_icon` (`nomor`, `nama`, `jenis`, `deskripsi`, `lat`, `lng`) VALUES
(1, 'Masjid Agung', 'masjid', 'tempat sholat umat Isalm', -6.23728179931641, 106.332725524902),
(2, 'Restoran Bersama', 'restoran', 'temapt makan', -6.23967002769392, 106.309807206177),
(3, 'Restoran Cepat saja', 'restoran', 'enak nih di sini', -6.22431180265454, 106.318476105713),
(4, 'Bandara Halim', 'airport', 'benarkah tidak ini bandara', -6.24939667082029, 106.34568443396),
(5, 'Bandara ABC', 'airport', 'Ini adalah bandara', -6.22013087470731, 106.331951523804);
