-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 05, 2024 at 02:14 PM
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
-- Database: `zus_1`
--

-- --------------------------------------------------------

--
-- Table structure for table `istrazivaci`
--

CREATE TABLE `istrazivaci` (
  `id_istrazivaci` int(11) NOT NULL,
  `ime` varchar(10) NOT NULL,
  `prezime` varchar(10) NOT NULL,
  `godina_usnuca` int(4) NOT NULL,
  `id_planete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `istrazivaci`
--

INSERT INTO `istrazivaci` (`id_istrazivaci`, `ime`, `prezime`, `godina_usnuca`, `id_planete`) VALUES
(900, 'Nikola', 'Jovanović', 2105, 1),
(901, 'Ana', 'Petrović', 2110, 1),
(902, 'Marko', 'Nikolić', 2120, 2),
(903, 'Ivana', 'Miljković', 2135, 2),
(904, 'Milan', 'Pavlović', 2145, 1),
(905, 'Jelena', 'Stanković', 2150, 1);

-- --------------------------------------------------------

--
-- Table structure for table `korisnici`
--

CREATE TABLE `korisnici` (
  `id_korisnika` int(11) NOT NULL,
  `korisnicko_ime` varchar(20) NOT NULL,
  `lozinka` varchar(15) NOT NULL,
  `ime` varchar(20) NOT NULL,
  `prezime` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `korisnici`
--

INSERT INTO `korisnici` (`id_korisnika`, `korisnicko_ime`, `lozinka`, `ime`, `prezime`) VALUES
(100, 'Dovlica', '1', 'Vlada', 'Marković'),
(101, 'Mićko', '2', 'Miroslav', 'Petrović'),
(102, 'Luka', '3', 'Luka', 'Jovanović'),
(103, 'Pavle', '4', 'Pavle', 'Nikolić'),
(104, 'Matija', '5', 'Matija', 'Ilić'),
(105, 'gagi', '123', 'Dragan', 'Urosevic');

-- --------------------------------------------------------

--
-- Table structure for table `misije`
--

CREATE TABLE `misije` (
  `id_misije` int(11) NOT NULL,
  `naziv` varchar(20) NOT NULL,
  `id_planete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `misije`
--

INSERT INTO `misije` (`id_misije`, `naziv`, `id_planete`) VALUES
(1, 'Misija Elon Musk 1', 1),
(2, 'Misija Mihajlo Pupin', 2),
(3, 'Povratak u buducnost', 3),
(4, 'Spasavanje Vukija', 4),
(5, 'Nova nada', 5),
(6, 'Misija Elon Musk 2', 1);

-- --------------------------------------------------------

--
-- Table structure for table `planete`
--

CREATE TABLE `planete` (
  `id_planete` int(11) NOT NULL,
  `naziv` varchar(20) NOT NULL,
  `udaljenost` int(10) DEFAULT NULL,
  `min_temp` int(5) DEFAULT NULL,
  `max_temp` int(5) DEFAULT NULL,
  `kiseonik` decimal(4,2) DEFAULT NULL,
  `rastvarac` decimal(4,2) DEFAULT NULL,
  `prag_gravitacije` int(5) DEFAULT NULL,
  `brzina_orb` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `planete`
--

INSERT INTO `planete` (`id_planete`, `naziv`, `udaljenost`, `min_temp`, `max_temp`, `kiseonik`, `rastvarac`, `prag_gravitacije`, `brzina_orb`) VALUES
(1, 'Mars', 150000000, 200, 320, 20.00, 75.00, 1500, 30),
(2, 'Aurora', 170000000, 220, 330, 18.00, 76.00, 1200, 28),
(3, 'Jupiter', 300000000, 100, 400, 10.00, 80.00, 500, 40),
(4, 'Tatuin', 250000000, 50, 450, 5.00, 70.00, 300, 50),
(5, 'Kašik', 50000000, 400, 600, 2.00, 50.00, 100, 20),
(6, 'Ksilem', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `putovanja`
--

CREATE TABLE `putovanja` (
  `id_putovanja` int(11) NOT NULL,
  `prevoz` varchar(20) NOT NULL,
  `datum_i_vreme` datetime NOT NULL,
  `ime_putnika` varchar(20) NOT NULL,
  `prezime_putnika` varchar(30) NOT NULL,
  `id_korisnika` int(11) NOT NULL,
  `id_objekta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `putovanja`
--

INSERT INTO `putovanja` (`id_putovanja`, `prevoz`, `datum_i_vreme`, `ime_putnika`, `prezime_putnika`, `id_korisnika`, `id_objekta`) VALUES
(500, 'Spejs šatl', '2110-05-14 10:00:00', 'Vlada', 'Marković', 100, 300),
(501, 'Falkon', '2120-07-21 15:30:00', 'Miroslav', 'Petrović', 101, 301),
(502, 'TIE borac', '2130-11-03 08:45:00', 'Luka', 'Jovanović', 102, 302),
(503, 'Nabu kruzer', '2140-01-15 13:00:00', 'Pavle', 'Nikolić', 103, 303),
(504, 'Zvezda smrti', '2150-06-30 09:00:00', 'Matija', 'Ilić', 104, 304),
(505, 'Spejs šatl', '2160-03-25 16:15:00', 'Vlada', 'Marković', 100, 305),
(506, 'TIE borac', '2170-08-19 11:30:00', 'Miroslav', 'Petrović', 101, 306),
(507, 'Falkon', '2180-12-10 14:45:00', 'Luka', 'Jovanović', 102, 307);

-- --------------------------------------------------------

--
-- Table structure for table `st_objekti`
--

CREATE TABLE `st_objekti` (
  `id_objekta` int(11) NOT NULL,
  `naziv` varchar(20) NOT NULL,
  `kapacitet` int(2) NOT NULL,
  `id_planete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `st_objekti`
--

INSERT INTO `st_objekti` (`id_objekta`, `naziv`, `kapacitet`, `id_planete`) VALUES
(300, 'Duplex', 6, 1),
(301, 'Garsonjera', 3, 1),
(302, 'Kuca', 5, 2),
(303, 'Stan', 2, 2),
(304, 'Garsonjera', 3, 2),
(305, 'Duplex 2', 6, 1),
(306, 'Garsonjera 2', 3, 1),
(307, 'Kuca 2', 5, 2),
(308, 'Stan 2', 3, 2),
(309, 'Garsonjera 2', 3, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `istrazivaci`
--
ALTER TABLE `istrazivaci`
  ADD PRIMARY KEY (`id_istrazivaci`),
  ADD KEY `id_planete` (`id_planete`);

--
-- Indexes for table `korisnici`
--
ALTER TABLE `korisnici`
  ADD PRIMARY KEY (`id_korisnika`);

--
-- Indexes for table `misije`
--
ALTER TABLE `misije`
  ADD PRIMARY KEY (`id_misije`),
  ADD KEY `id_planete` (`id_planete`);

--
-- Indexes for table `planete`
--
ALTER TABLE `planete`
  ADD PRIMARY KEY (`id_planete`);

--
-- Indexes for table `putovanja`
--
ALTER TABLE `putovanja`
  ADD PRIMARY KEY (`id_putovanja`),
  ADD KEY `id_korisnika` (`id_korisnika`),
  ADD KEY `id_objekta` (`id_objekta`);

--
-- Indexes for table `st_objekti`
--
ALTER TABLE `st_objekti`
  ADD PRIMARY KEY (`id_objekta`),
  ADD KEY `id_planete` (`id_planete`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `istrazivaci`
--
ALTER TABLE `istrazivaci`
  MODIFY `id_istrazivaci` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=906;

--
-- AUTO_INCREMENT for table `korisnici`
--
ALTER TABLE `korisnici`
  MODIFY `id_korisnika` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=110;

--
-- AUTO_INCREMENT for table `misije`
--
ALTER TABLE `misije`
  MODIFY `id_misije` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `planete`
--
ALTER TABLE `planete`
  MODIFY `id_planete` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `putovanja`
--
ALTER TABLE `putovanja`
  MODIFY `id_putovanja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=533;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `istrazivaci`
--
ALTER TABLE `istrazivaci`
  ADD CONSTRAINT `istrazivaci_ibfk_1` FOREIGN KEY (`id_planete`) REFERENCES `planete` (`id_planete`);

--
-- Constraints for table `misije`
--
ALTER TABLE `misije`
  ADD CONSTRAINT `misije_ibfk_1` FOREIGN KEY (`id_planete`) REFERENCES `planete` (`id_planete`);

--
-- Constraints for table `putovanja`
--
ALTER TABLE `putovanja`
  ADD CONSTRAINT `putovanja_ibfk_1` FOREIGN KEY (`id_korisnika`) REFERENCES `korisnici` (`id_korisnika`),
  ADD CONSTRAINT `putovanja_ibfk_3` FOREIGN KEY (`id_objekta`) REFERENCES `st_objekti` (`id_objekta`);

--
-- Constraints for table `st_objekti`
--
ALTER TABLE `st_objekti`
  ADD CONSTRAINT `st_objekti_ibfk_1` FOREIGN KEY (`id_planete`) REFERENCES `planete` (`id_planete`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
