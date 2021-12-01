-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 160.10.217.6:3306
-- Generation Time: Dec 01, 2021 at 12:28 AM
-- Server version: 8.0.22
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cs3230f21a`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`jechols5`@`%` PROCEDURE `uspCreateRentalTransaction` (IN `transactionId` INT, IN `dueDate` DATE, IN `transactionDate` DATE, IN `customerId` INT, IN `employeeId` INT, IN `rentalItems` TEXT)  NO SQL
BEGIN
DECLARE sql_error tinyint default false;
DECLARE Continue HANDLER FOR SQLEXCEPTION
set sql_error = true;
start transaction;
insert into rental_transaction (transactionId, dueDate, transactionDate, customerId, employeeId) values (transactionId, dueDate, transactionDate, customerId, employeeId);
   Set @sql = CONCAT("INSERT into rental_item (rentalId, furnitureId, quantity) values ", rentalItems);
   PREPARE stmt from @sql;
   EXECUTE stmt;
   UPDATE furniture, rental_item set furniture.quantity = furniture.quantity - rental_item.quantity where rental_item.rentalId = transactionId and furniture.furnitureId = rental_item.furnitureId;
if sql_error = false then
commit;
else
rollback;
end if;
END$$

CREATE DEFINER=`jechols5`@`%` PROCEDURE `uspCreateReturnTransaction` (IN `transactionId` INT, IN `returnDate` DATE, IN `customerId` INT, IN `employeeId` INT, IN `returnItems` TEXT)  NO SQL
BEGIN
DECLARE sql_error tinyint default false;
DECLARE Continue HANDLER FOR SQLEXCEPTION
set sql_error = true;
start transaction;
insert into return_transaction (transactionId, returnDate, customerId, employeeId) values (transactionId, returnDate, customerId, employeeId);
   Set @sql = CONCAT("INSERT into return_item (rentalId, returnId, returnDate, furnitureId, quantity) values ", returnItems);
   PREPARE stmt from @sql;
   EXECUTE stmt;
   Update furniture, return_item set furniture.quantity = furniture.quantity + return_item.quantity where furniture.furnitureId = return_item.furnitureId and return_item.returnId = transactionId;
   update rental_item, return_item set rental_item.quantity = rental_item.quantity - return_item.quantity where rental_item.rentalId = return_item.rentalId and rental_item.furnitureId = return_item.furnitureId and return_item.returnId = transactionId;
if sql_error = false then
commit;
else
rollback;
end if;
 END$$

$$

CREATE DEFINER=`jechols5`@`%` PROCEDURE `uspEditEmployee` (IN `employeeId` INT, IN `firstname` VARCHAR(50), IN `lastname` VARCHAR(50), IN `address` VARCHAR(50), IN `zipcode` VARCHAR(50), IN `state` VARCHAR(50), IN `city` VARCHAR(50), IN `phonenumber` VARCHAR(10), IN `username` VARCHAR(50))  NO SQL
BEGIN
update employee
SET
employee.firstName = firstname,
employee.lastName = lastname,
employee.address1 = address,
employee.zipcode = zipcode,
employee.city = city,
employee.state = state,
employee.phoneNumber = phonenumber,
employee.username = username
where 
employee.employeeId = employeeId;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `adminId` int NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `employee` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adminId`, `username`, `password`, `employee`) VALUES
(1, 'jamiaechols', 'jamiaechols', 1),
(2, 'rasheedjones', 'rasheedjones', 2);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `categoryId` int NOT NULL,
  `label` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryId`, `label`) VALUES
(1, 'Bed'),
(2, 'Dining Table'),
(3, 'Sofa'),
(4, 'Chair'),
(5, 'Desk'),
(6, 'Rug');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `memberID` int NOT NULL,
  `gender` varchar(8) NOT NULL,
  `address1` varchar(50) NOT NULL,
  `phoneNumber` char(10) NOT NULL,
  `birthday` date NOT NULL,
  `registrationDate` date NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `zipcode` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`memberID`, `gender`, `address1`, `phoneNumber`, `birthday`, `registrationDate`, `firstname`, `lastname`, `state`, `city`, `zipcode`) VALUES
(1, 'Female', '1234 main st.', '1111111111', '1998-07-08', '2021-10-01', 'Slam', 'Theman', '30117', 'Carrolton', 'District of Columbia'),
(2, 'male', '31 town rd. Carrollton, Ga', '2222222222', '1967-03-12', '2021-10-11', 'Danny', 'Phantom', '30118', 'Carrollton', 'Georgia'),
(6, 'Male', '123 place drive, Carrolton, 30217', '5556667777', '2021-09-28', '2021-10-22', 'American', 'Dragon', '301180', 'Carrollton', 'Georgia'),
(7, '12323', 'main', '1212349029', '2021-10-20', '2021-10-30', 'Monica', 'browner', 'Female', 'Alaska', '123 main st'),
(8, '12323', 'main', '1212349029', '2021-10-20', '2021-10-30', 'lisa', 'browner', '123 main st', 'Alaska', 'Female'),
(9, 'Male', '111 place street', '1111111112', '1995-11-16', '2021-11-03', 'lisa', 'brown', 'Alabama', 'carrolton', '30117'),
(10, 'Male', '1235 Land O\' Lakes', '1111111111', '1995-11-16', '2021-11-03', 'lisa', 'brown', 'Arkansas', 'Tomatoland', '30117'),
(11, 'Female', '123 place', '1111111111', '2019-11-28', '2021-11-05', 'Elizabeth', 'Brown', '30117', 'Carollton', 'Georgia'),
(12, 'Other', '123 Slambuster Street', '1234567890', '1899-10-30', '2021-11-12', 'Saul', 'Meyers', 'Alabama', 'Tomatoland', '111111'),
(13, 'Other', '123 jordan rd', '1234567890', '2021-11-10', '2021-11-30', 'Test', 'Test', 'District of Columbia', 'city', '123456'),
(14, 'Male', '123 main street', '1111111111', '2021-11-09', '2021-11-30', 'aaron', 'bbron', 'Alaska', 'ca', '30117');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employeeId` int NOT NULL,
  `firstName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address1` varchar(50) NOT NULL,
  `address2` varchar(50) NOT NULL,
  `state` varchar(50) DEFAULT NULL,
  `phoneNumber` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `lastName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `city` varchar(50) DEFAULT NULL,
  `zipcode` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employeeId`, `firstName`, `address1`, `address2`, `state`, `phoneNumber`, `lastName`, `username`, `password`, `city`, `zipcode`) VALUES
(1, 'Jamia', '123 main st', '', 'Georgia', '1111111111', 'Echols', 'jamiaechols', 'WazBc0u2yXZFvmdw39WDTQ==', 'carrollton', '30118'),
(2, 'Rasheed', '321 town rd. Carrollton, Ga', '', NULL, '2222222222', 'Jones', 'rasheedjones', 'jni1LO0vIc51SQqywAR37Q==', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `furniture`
--

CREATE TABLE `furniture` (
  `furnitureId` int NOT NULL,
  `daily_rental_rate` decimal(10,2) NOT NULL,
  `categoryId` int DEFAULT NULL,
  `styleId` int DEFAULT NULL,
  `quantity` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `furniture`
--

INSERT INTO `furniture` (`furnitureId`, `daily_rental_rate`, `categoryId`, `styleId`, `quantity`) VALUES
(1, '149.99', 1, 1, 7),
(2, '69.99', 2, 3, 2),
(3, '12.99', 1, 1, 1),
(4, '1234.55', 2, 2, 4),
(5, '10.99', 3, 1, 0),
(6, '11.11', 3, 2, 4),
(7, '19.00', 6, 1, 1),
(8, '249.99', 6, 2, 5);

-- --------------------------------------------------------

--
-- Table structure for table `rental_item`
--

CREATE TABLE `rental_item` (
  `rentalId` int NOT NULL,
  `furnitureId` int NOT NULL,
  `quantity` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `rental_item`
--

INSERT INTO `rental_item` (`rentalId`, `furnitureId`, `quantity`) VALUES
(1, 2, 1),
(2, 1, 2),
(7, 1, 0),
(13, 1, 1),
(13, 2, 0),
(14, 2, 0),
(15, 2, 1),
(16, 1, 1),
(16, 2, 1),
(17, 1, 2),
(17, 2, 0),
(18, 3, 1),
(19, 3, 1),
(20, 4, 1),
(21, 5, 2),
(22, 7, 2),
(23, 2, 2),
(24, 2, 2),
(24, 3, 1),
(25, 2, 1),
(25, 5, 1),
(25, 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `rental_transaction`
--

CREATE TABLE `rental_transaction` (
  `transactionId` int NOT NULL,
  `dueDate` date NOT NULL,
  `transactionDate` date NOT NULL,
  `customerId` int NOT NULL,
  `employeeId` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `rental_transaction`
--

INSERT INTO `rental_transaction` (`transactionId`, `dueDate`, `transactionDate`, `customerId`, `employeeId`) VALUES
(1, '2021-12-31', '2021-10-19', 1, 1),
(2, '2022-02-28', '2021-10-19', 2, 2),
(3, '2022-01-02', '2021-11-03', 1, 1),
(4, '2022-01-02', '2021-11-03', 2, 1),
(5, '2022-01-02', '2021-11-03', 2, 1),
(6, '2022-01-02', '2021-11-03', 10, 1),
(7, '2022-01-02', '2021-11-03', 7, 1),
(8, '2022-01-04', '2021-11-05', 7, 2),
(9, '2022-01-04', '2021-11-05', 7, 2),
(10, '2022-01-16', '2021-11-17', 12, 1),
(11, '2022-01-16', '2021-11-17', 12, 1),
(12, '2022-01-16', '2021-11-17', 12, 1),
(13, '2022-01-16', '2021-11-17', 12, 1),
(14, '2022-01-17', '2021-11-18', 9, 2),
(15, '2022-01-17', '2021-11-18', 9, 2),
(16, '2022-01-17', '2021-11-18', 9, 2),
(17, '2022-01-17', '2021-11-18', 9, 2),
(18, '2022-01-20', '2021-11-21', 1, 1),
(19, '2022-01-20', '2021-11-21', 1, 1),
(20, '2022-01-20', '2021-11-21', 12, 1),
(21, '2022-01-20', '2021-11-21', 6, 1),
(22, '2022-01-20', '2021-11-21', 2, 1),
(23, '2021-12-10', '2021-11-25', 2, 1),
(24, '2021-12-11', '2021-11-25', 11, 1),
(25, '2021-12-11', '2021-11-30', 13, 1);

-- --------------------------------------------------------

--
-- Table structure for table `return_item`
--

CREATE TABLE `return_item` (
  `returnId` int NOT NULL,
  `returnDate` date NOT NULL,
  `furnitureId` int NOT NULL,
  `quantity` int NOT NULL,
  `rentalId` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `return_item`
--

INSERT INTO `return_item` (`returnId`, `returnDate`, `furnitureId`, `quantity`, `rentalId`) VALUES
(1, '2021-11-17', 1, 1, 1),
(17, '2021-11-17', 1, 1, 13),
(20, '2021-11-21', 1, 1, 7),
(22, '2021-11-26', 1, 1, 16),
(2, '2021-10-22', 2, 1, 2),
(18, '2021-11-17', 2, 1, 13),
(22, '2021-11-26', 2, 1, 14),
(22, '2021-11-26', 2, 1, 17);

-- --------------------------------------------------------

--
-- Table structure for table `return_transaction`
--

CREATE TABLE `return_transaction` (
  `transactionId` int NOT NULL,
  `returnDate` date NOT NULL,
  `customerId` int NOT NULL,
  `employeeId` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `return_transaction`
--

INSERT INTO `return_transaction` (`transactionId`, `returnDate`, `customerId`, `employeeId`) VALUES
(1, '2021-10-19', 1, 2),
(2, '2021-10-14', 2, 1),
(3, '2021-11-17', 12, 1),
(4, '2021-11-17', 12, 1),
(5, '2021-11-17', 12, 1),
(6, '2021-11-17', 12, 1),
(7, '2021-11-17', 12, 1),
(8, '2021-11-17', 12, 1),
(9, '2021-11-17', 12, 1),
(10, '2021-11-17', 12, 1),
(11, '2021-11-17', 12, 1),
(12, '2021-11-17', 12, 1),
(13, '2021-11-17', 12, 1),
(14, '2021-11-17', 12, 1),
(15, '2021-11-17', 12, 1),
(16, '2021-11-17', 12, 1),
(17, '2021-11-17', 12, 1),
(18, '2021-11-17', 12, 1),
(19, '2021-11-18', 9, 2),
(20, '2021-11-21', 7, 1),
(21, '2021-11-26', 12, 1),
(22, '2021-11-26', 9, 1);

-- --------------------------------------------------------

--
-- Table structure for table `style`
--

CREATE TABLE `style` (
  `styleId` int NOT NULL,
  `label` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `style`
--

INSERT INTO `style` (`styleId`, `label`) VALUES
(1, 'Modern'),
(2, 'Traditional'),
(3, 'Contemporary'),
(4, 'Eclectic'),
(5, 'Industrial');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminId`),
  ADD KEY `employee` (`employee`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryId`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`memberID`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employeeId`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `password` (`password`);

--
-- Indexes for table `furniture`
--
ALTER TABLE `furniture`
  ADD PRIMARY KEY (`furnitureId`),
  ADD KEY `categoryId` (`categoryId`),
  ADD KEY `fk_style_id` (`styleId`);

--
-- Indexes for table `rental_item`
--
ALTER TABLE `rental_item`
  ADD PRIMARY KEY (`furnitureId`,`rentalId`,`quantity`),
  ADD KEY `rentalId` (`rentalId`);

--
-- Indexes for table `rental_transaction`
--
ALTER TABLE `rental_transaction`
  ADD PRIMARY KEY (`transactionId`),
  ADD KEY `customerId` (`customerId`),
  ADD KEY `employeeId` (`employeeId`);

--
-- Indexes for table `return_item`
--
ALTER TABLE `return_item`
  ADD PRIMARY KEY (`furnitureId`,`returnId`,`rentalId`),
  ADD KEY `furnitureId` (`furnitureId`),
  ADD KEY `rentalId` (`rentalId`);

--
-- Indexes for table `return_transaction`
--
ALTER TABLE `return_transaction`
  ADD PRIMARY KEY (`transactionId`),
  ADD KEY `customerId` (`customerId`),
  ADD KEY `employeeId` (`employeeId`);

--
-- Indexes for table `style`
--
ALTER TABLE `style`
  ADD PRIMARY KEY (`styleId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `memberID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employeeId` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `furniture`
--
ALTER TABLE `furniture`
  MODIFY `furnitureId` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`employee`) REFERENCES `employee` (`employeeId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `furniture`
--
ALTER TABLE `furniture`
  ADD CONSTRAINT `fk_style_id` FOREIGN KEY (`styleId`) REFERENCES `style` (`styleId`),
  ADD CONSTRAINT `furniture_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `furniture` (`furnitureId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rental_item`
--
ALTER TABLE `rental_item`
  ADD CONSTRAINT `rental_item_ibfk_1` FOREIGN KEY (`furnitureId`) REFERENCES `furniture` (`furnitureId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rental_item_ibfk_3` FOREIGN KEY (`rentalId`) REFERENCES `rental_transaction` (`transactionId`);

--
-- Constraints for table `rental_transaction`
--
ALTER TABLE `rental_transaction`
  ADD CONSTRAINT `rental_transaction_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customer` (`memberID`),
  ADD CONSTRAINT `rental_transaction_ibfk_2` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`);

--
-- Constraints for table `return_item`
--
ALTER TABLE `return_item`
  ADD CONSTRAINT `return_item_ibfk_2` FOREIGN KEY (`furnitureId`) REFERENCES `furniture` (`furnitureId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `return_item_ibfk_3` FOREIGN KEY (`rentalId`) REFERENCES `rental_transaction` (`transactionId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `return_transaction`
--
ALTER TABLE `return_transaction`
  ADD CONSTRAINT `return_transaction_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customer` (`memberID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `return_transaction_ibfk_2` FOREIGN KEY (`customerId`) REFERENCES `customer` (`memberID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `return_transaction_ibfk_3` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
