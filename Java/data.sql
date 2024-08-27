-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlybenxe
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `BookingID` int NOT NULL AUTO_INCREMENT,
  `TripID` int DEFAULT NULL,
  `UserID` int DEFAULT NULL,
  `payment_method` varchar(50) DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `bus_id` int NOT NULL,
  `seat_number` varchar(10) NOT NULL,
  `booking_date` datetime NOT NULL,
  `travel_date` datetime NOT NULL,
  `status` varchar(20) NOT NULL,
  `notes` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`BookingID`),
  KEY `TripID` (`TripID`),
  KEY `UserID` (`UserID`),
  KEY `FK_BusId` (`bus_id`),
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`TripID`) REFERENCES `trip` (`TripID`),
  CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `FK_BusId` FOREIGN KEY (`bus_id`) REFERENCES `bus` (`BusID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus`
--

DROP TABLE IF EXISTS `bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus` (
  `BusID` int NOT NULL AUTO_INCREMENT,
  `CompanyID` int DEFAULT NULL,
  `PlateNumber` varchar(20) DEFAULT NULL,
  `Capacity` int NOT NULL,
  `CategoryID` int DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`BusID`),
  UNIQUE KEY `LicensePlate` (`PlateNumber`),
  KEY `CompanyID` (`CompanyID`),
  KEY `FK_CategoryID_Buses` (`CategoryID`),
  CONSTRAINT `bus_ibfk_1` FOREIGN KEY (`CompanyID`) REFERENCES `company` (`CompanyID`),
  CONSTRAINT `FK_CategoryID_Buses` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus`
--

LOCK TABLES `bus` WRITE;
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
INSERT INTO `bus` VALUES (5,25,'51F-12345',50,1,'https://res.cloudinary.com/dnqxawhjq/image/upload/v1717214144/mqpcgaztmdsv5lfy4mee.png',NULL,'2024-06-18 07:05:53','xe số 5'),(6,25,'51F-54321',60,1,'https://res.cloudinary.com/dnqxawhjq/image/upload/v1717385679/f8bp8bkikphaytqtgtqc.jpg',NULL,'2024-06-18 07:05:53','xe số 6'),(7,26,'51F-45678',40,1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1718685862/uhf8tuwqvk7oys6nvcpb.png','2024-05-27 03:45:12','2024-06-18 07:05:53','xe số 7'),(9,25,'51F12389',25,1,'https://res.cloudinary.com/dnqxawhjq/image/upload/v1716871133/obkpid05pe0foubzrwld.png',NULL,'2024-06-18 07:05:53','xe số 9'),(10,8,'51F95648',50,1,'https://res.cloudinary.com/dnqxawhjq/image/upload/v1716872542/leckp9ulmah48ekhmdnx.png',NULL,'2024-06-18 07:05:53','xe số 10'),(11,8,'51F25921',25,2,'https://res.cloudinary.com/dnqxawhjq/image/upload/v1716885352/giq6l1mng8w8bctjyyxp.jpg',NULL,'2024-06-18 07:05:53','xe số 11'),(12,8,'51F25917',2509,3,'https://res.cloudinary.com/dnqxawhjq/image/upload/v1717041793/w0k0ankhzmlfdmhby3p5.jpg',NULL,'2024-06-18 07:05:53','xe số 12'),(13,25,'51G61259',50,11,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1718685847/j8hhhmnnfzoqbjannyu5.jpg',NULL,'2024-06-18 07:05:53','xe số 13'),(14,26,'50F62547',27,3,'https://res.cloudinary.com/dnqxawhjq/image/upload/v1717486840/do61d5fpgdco21mvwhux.png',NULL,'2024-06-18 07:05:53','xe số 14'),(15,25,'50F-64527',25,11,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1718685836/lpzy8f6dysskwgilzda3.jpg',NULL,'2024-06-18 07:05:53','xe số 15'),(16,8,'52F-64527',25,11,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1718685815/mopad62vrc1yerxyc4em.jpg',NULL,'2024-06-18 07:05:53','xe số 16'),(17,8,'50F-76224',50,12,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1718685824/rpjyzziuxcgqeezogzhi.webp',NULL,'2024-06-18 07:05:53','xe số 17'),(18,8,'50F-21276',50,2,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1718685801/kdprmeoxibebhanminox.jpg',NULL,'2024-06-18 07:05:53','xe số 18'),(20,25,'51F15634',45,3,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1718685790/r7kepuhflwyxifmzve73.webp',NULL,'2024-06-18 07:05:53',''),(21,25,'54D-15964',36,3,'https://res.cloudinary.com/dnqxawhjq/image/upload/v1718250196/hum2rcqieqbzraxhmtr0.jpg','2024-06-13 03:42:04','2024-06-18 07:05:53','Xe B'),(22,26,'58D-19956',26,3,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1718685683/efdhgofcxyfeweajnfhy.jpg','2024-06-18 04:33:25','2024-06-18 07:05:53','Xe số 22'),(23,28,'51F-25918',29,3,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1718685766/zbjf0fuabnjc9lcqsyl7.png','2024-06-18 04:42:45','2024-06-18 04:42:45',NULL),(24,NULL,'49D-16348',50,12,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1718782266/fzmerwmlovyfhet3gako.webp','2024-06-19 07:31:06','2024-06-19 07:31:06','Xe số 24');
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `CategoryID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Xe khách giường nằm',NULL,'2024-05-27 03:26:40','2024-05-27 03:26:40'),(2,'Xe khách ghế ngồi ',NULL,'2024-05-27 03:26:40','2024-05-27 03:26:40'),(3,'Xe khách limousine',NULL,'2024-05-27 03:26:40','2024-05-27 03:26:40'),(11,'Xe ghế mềm','Xe trang bị cho quý khách loại ghế được bọc nệm cao cấp, mềm mại và êm ái','2024-06-07 08:09:35',NULL),(12,'Xe limousine','Xe khÃ¡ch cao cáº¥p vá»i Äáº§y Äá»§ tiá»n Ã­ch cáº§n thiáº¿t cho khÃ¡ch hÃ ng',NULL,NULL),(13,'Xe giường nằm 34','Xe trang bị cho quý khách loại giường được bọc nệm cao cấp, mềm mại và êm ái','2024-06-21 10:25:43','2024-06-21 10:25:43');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `CommentID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `CompanyID` int DEFAULT NULL,
  `Rating` int DEFAULT NULL,
  `CommentText` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`CommentID`),
  KEY `UserID` (`UserID`),
  KEY `CompanyID` (`CompanyID`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`CompanyID`) REFERENCES `company` (`CompanyID`),
  CONSTRAINT `comment_chk_1` CHECK (((`Rating` >= 1) and (`Rating` <= 5)))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,6,8,5,'Xe chạy êm, bác tài thân thiện, nhà xe phản hồi nhanh','2024-06-21 04:51:35','2024-06-21 04:51:35'),(2,7,9,4,'Xe chạy êm, bác tài thân thiện, nhà xe phản hồi nhanh','2024-06-21 04:56:02','2024-06-21 04:56:02');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `CompanyID` int NOT NULL AUTO_INCREMENT,
  `CompanyName` varchar(100) NOT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Email` varchar(100) NOT NULL,
  `PhoneNumber` varchar(20) DEFAULT NULL,
  `Avatar` varchar(255) DEFAULT NULL,
  `IsShippingAvailable` tinyint(1) DEFAULT '0',
  `IsActive` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`CompanyID`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (5,'Transport Company B','456 Road, City B','companyB@example.com','+9876543210','avatar_companyB.jpg',1,1,'2024-05-27 03:53:50','2024-05-27 03:53:50'),(7,'Transport Company A','123 Street, City A','companyA@example.com','+1234567890','avatar_companyA.jpg',1,1,'2024-05-27 03:53:50','2024-05-27 03:53:50'),(8,'Bến xe Thành Bưởi','266 – 268 Lê Hồng Phong, Phường 4, Quận 5, Tp. Hồ Chí Minh','thanhbuoi@gmail.com','0918919189','https://res.cloudinary.com/dnqxawhjq/image/upload/v1716886408/ocixfmrmexiqns2lb5rr.jpg',1,0,NULL,'2024-06-18 07:20:17'),(9,'Bến xe Phương Trang','202 - 204 Lê Hồng Phong, Phường 4, Quận 5, TP HCM','phuongtrang@gmail.com','19006067','https://res.cloudinary.com/dnqxawhjq/image/upload/v1716886678/jpxblict2lmtd8jmkie0.jpg',0,0,NULL,'2024-06-18 07:20:17'),(25,'Bến xe Phương Trang','205 Phạm Ngũ Lão , phường Phạm Ngũ Lão Quận 1, TPHCM','phuongtrang1@gmail.com','19006067','https://res.cloudinary.com/dnqxawhjq/image/upload/v1716888244/v03vvxacypkrtfucqjxi.jpg',1,0,NULL,'2024-06-18 07:20:17'),(26,'Bến xe Phương Trang','231 - 233 Lê Hồng Phong, Phường 4, Quận 5, TP HCM','phuongtrang2@gmail.com','19006067','https://res.cloudinary.com/dnqxawhjq/image/upload/v1716888327/fsqqyybzcseuhacn2auf.jpg',0,0,NULL,NULL),(28,'Công ty ABC','123 đường abc, phường 1, quận 1','abc@gmail.com','123456789','https://res.cloudinary.com/dnqxawhjq/image/upload/v1717921190/vgb5dtdqmo3c2ehsgj9t.jpg',1,0,'2024-06-09 08:19:46',NULL),(29,'Bến xe A','a, đường b, quận c','a@gmail.com','0591679354','https://res.cloudinary.com/dnqxawhjq/image/upload/v1718247547/wcpubqyajpv92qk2ncig.jpg',0,0,NULL,NULL);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `trip_id` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `fk_trip_id` (`trip_id`),
  CONSTRAINT `fk_trip_id` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`TripID`),
  CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (1,1,100.00,7,2),(2,1,200.00,8,1),(3,2,100.00,9,2),(4,2,200.00,8,1),(5,3,100.00,7,2),(6,3,200.00,7,1),(7,4,100.00,10,2),(8,4,200.00,8,1),(9,5,100.00,7,2),(10,5,200.00,8,1),(11,6,100.00,10,2),(12,6,200.00,7,1),(13,7,100.00,10,3),(14,7,200.00,7,5);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `order_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,3,'2024-05-19 09:29:25'),(2,3,'2024-02-19 09:38:14'),(3,3,'2024-06-19 09:39:34'),(4,3,'2024-07-19 09:43:52'),(5,3,'2024-06-19 09:46:38'),(6,3,'2024-08-20 07:38:47'),(7,8,'2024-06-20 07:40:38');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revenuestatic`
--

DROP TABLE IF EXISTS `revenuestatic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revenuestatic` (
  `revenue_id` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `total_bookings` int NOT NULL,
  `total_revenue` decimal(10,2) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`revenue_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revenuestatic`
--

LOCK TABLES `revenuestatic` WRITE;
/*!40000 ALTER TABLE `revenuestatic` DISABLE KEYS */;
/*!40000 ALTER TABLE `revenuestatic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route` (
  `RouteID` int NOT NULL AUTO_INCREMENT,
  `StartLocation` varchar(100) NOT NULL,
  `EndLocation` varchar(100) NOT NULL,
  `Distance` decimal(10,2) NOT NULL,
  `estimated_duration` time NOT NULL,
  `description` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) DEFAULT NULL,
  `ticketPrice` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`RouteID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,'TPHCM','Đà Lạt',300.50,'00:00:00',NULL,'2024-05-27 03:37:00','2024-06-21 06:49:11','Chuyến TPHCM-Đà Lạt',300000.00),(2,'TPHCM','Nha Trang',450.75,'00:00:00',NULL,'2024-05-27 03:37:00','2024-06-21 06:49:11','Chuyến TPHCM - Nha Trang',500000.00),(3,'Hà Nội','Đà Nẵng',700.25,'00:00:00',NULL,'2024-05-27 03:37:00','2024-06-21 06:49:11','Chuyến TPHCM - Đà Nẵng',850000.00),(5,'TPHCM','Đà Lạt',315.00,'05:15:00','Xe khởi hành từ TPHCM đi Đà Lạt','2024-06-09 09:59:40','2024-06-21 06:49:11','Chuyến TPHCM - Đà Lạt',300000.00),(6,'TPHCM','Đà Lạt',315.00,'05:20:00','Xe khởi hành từ TPHCM đi Đà Lạt','2024-06-09 10:03:53','2024-06-21 06:49:11','Chuyến TPHCM - Đà Lạt',300000.00),(7,'TPHCM','Đà Lạt',315.00,'05:20:00','Xe khởi hành từ TPHCM đi Đà Lạt','2024-06-09 10:05:50','2024-06-21 06:49:11','Chuyến TPHCM - Đà Lạt',300000.00),(8,'TPHCM','Đà Lạt',315.00,'06:20:00','Xe khởi hành từ TPHCM đi Đà Lạt','2024-06-09 10:06:06','2024-06-21 06:49:11','Chuyến TPHCM - Đà Lạt',300000.00),(9,'Bến xe Phương Trang TPHCM','Bến xe Phương Trang Đà Lạt',300.00,'05:30:00','Xe khởi hành tại bến xe Phương Trang ở TPHCM và đến tại bến xe Phương Trang ở Đà Lạt',NULL,'2024-06-21 06:49:11','Chuyến TPHCM - Đà Lạt',300000.00),(10,'Bến xe Phương Trang TPHCM','Bến xe Phương Trang Đà Lạt',300.00,'05:30:00','Xe khởi hành tại bến xe Phương Trang ở TPHCM và đến tại bến xe Phương Trang ở Đà Lạt',NULL,'2024-06-21 06:49:11','Chuyến TPHCM - Đà Lạt',300000.00),(11,'TPHCM','Đà Lạt',315.00,'17:15:00','Xe khởi hành từ TPHCM đi Đà Lạt','2024-06-21 06:43:58','2024-06-21 06:58:29','Chuyến xe TPHCM - Đà Lạt',300000.00),(12,'TPHCM','Đà Lạt',315.00,'17:15:00','Xe khởi hành từ TPHCM đi Đà Lạt','2024-06-21 06:47:20','2024-06-21 06:58:29','Chuyến xe TPHCM - Đà Lạt',300000.00),(13,'TPHCM','Đà Lạt',315.00,'17:15:00','Xe khởi hành từ TPHCM đi Đà Lạt','2024-06-21 06:48:48','2024-06-21 06:58:29','Chuyến xe TPHCM - Đà Lạt',300000.00),(14,'TPHCM','Đà Lạt',315.00,'17:15:00','Xe khởi hành từ TPHCM đi Đà Lạt','2024-06-21 06:57:01','2024-06-21 06:58:01','Chuyến xe TPHCM - Đà Lạt',325000.00),(15,'TPHCM','Đà Lạt',315.00,'17:15:00','Xe khởi hành từ TPHCM đi Đà Lạt','2024-06-21 06:57:36',NULL,'Chuyến xe TPHCM - Đà Lạt',325000.00);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment`
--

DROP TABLE IF EXISTS `shipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipment` (
  `shipment_id` int NOT NULL AUTO_INCREMENT,
  `sender_name` varchar(255) NOT NULL,
  `receiver_name` varchar(255) NOT NULL,
  `pickup_location` varchar(255) NOT NULL,
  `dropoff_location` varchar(255) NOT NULL,
  `shipment_date` datetime NOT NULL,
  `status` varchar(20) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `SenderPhone` varchar(20) DEFAULT NULL,
  `SenderEmail` varchar(255) DEFAULT NULL,
  `ReceiverPhone` varchar(20) DEFAULT NULL,
  `ReceiverEmail` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`shipment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment`
--

LOCK TABLES `shipment` WRITE;
/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
INSERT INTO `shipment` VALUES (1,'Nguyễn Văn A','Nguyễn Văn B','123 đường số 5, TPHCM','456 đường 8, Đà Lạt','2024-06-20 00:00:00','pending',50000.00,'1234567890','a@gmail.com','0987654321','b@gmail.com','2024-06-21 09:53:58',NULL),(2,'Nguyễn Văn C','Nguyễn Văn D','123 đường số 5, TPHCM','456 đường 8, Đà Lạt','2024-06-20 00:00:00','pending',50000.00,'1234567890','c@gmail.com','0987654321','d@gmail.com','2024-06-21 09:54:43',NULL);
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip` (
  `TripID` int NOT NULL AUTO_INCREMENT,
  `RouteID` int DEFAULT NULL,
  `BusID` int DEFAULT NULL,
  `DepartureTime` datetime NOT NULL,
  `ArrivalTime` datetime NOT NULL,
  `TicketPrice` decimal(10,2) NOT NULL,
  `status` varchar(20) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) DEFAULT NULL,
  `CompanyID` int DEFAULT NULL,
  PRIMARY KEY (`TripID`),
  KEY `RouteID` (`RouteID`),
  KEY `BusID` (`BusID`),
  KEY `fk_company` (`CompanyID`),
  CONSTRAINT `fk_company` FOREIGN KEY (`CompanyID`) REFERENCES `company` (`CompanyID`),
  CONSTRAINT `trip_ibfk_1` FOREIGN KEY (`RouteID`) REFERENCES `route` (`RouteID`),
  CONSTRAINT `trip_ibfk_2` FOREIGN KEY (`BusID`) REFERENCES `bus` (`BusID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` VALUES (7,1,5,'2024-05-21 08:00:00','2024-05-21 12:00:00',25.50,'','2024-05-27 03:55:58','2024-06-13 07:03:41','Chuyến số 7',9),(8,2,6,'2024-05-21 09:30:00','2024-05-21 13:30:00',30.00,'','2024-05-27 03:55:58','2024-06-13 07:03:41','Chuyến số 8',9),(9,3,7,'2024-05-21 11:00:00','2024-05-21 15:00:00',20.75,'','2024-05-27 03:55:58','2024-06-13 07:03:41','Chuyến số 9',9),(10,8,18,'2023-06-10 15:30:00','2023-06-10 21:30:00',250000.00,'Scheduled','2024-06-10 04:42:52','2024-06-13 07:03:41','Chuyến số 10',8),(11,9,21,'2023-06-21 10:30:00','2023-06-21 16:30:00',300000.00,'Scheduled','2024-06-21 03:40:19','2024-06-21 03:41:37','Chuyến số 11',9),(12,2,21,'2023-06-21 10:30:00','2023-06-21 22:30:00',300000.00,'Scheduled','2024-06-21 03:42:42','2024-06-21 03:42:42','Chuyến xe số 12',9);
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_role` varchar(20) DEFAULT NULL,
  `Avatar` varchar(255) DEFAULT NULL,
  `Email` varchar(100) NOT NULL,
  `PhoneNumber` varchar(20) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `Username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','ROLE_ADMIN',NULL,'admin@example.com','0123456789','2024-05-27 03:57:41','2024-06-11 03:37:36',NULL,NULL),(2,'thanhquang1','123456','ROLE_USER',NULL,'thanhquang@gmail.com','1234567890','2024-06-11 04:03:45','2024-06-11 04:29:50','quang','nguyen'),(3,'thanhquang','$2a$10$4p.aDJcduhjTs/.8Pu6mVepWM5jdDf3g2fP2uffbraNs5ebMdZVyO','ROLE_ADMIN','https://res.cloudinary.com/dnqxawhjq/image/upload/v1718092319/vqpmrr6lokge7tqlo6ah.jpg','thanhquang123@gmail.com','0526973468','2024-06-11 07:51:56','2024-06-18 07:43:35','nguyen','quang'),(5,'thanhquang3','$2a$10$zd11719tTdmXN6F3uC14quzoJoN.1t65YP0FgJyNtdMz.IM7ltgRy','ROLE_USER','https://res.cloudinary.com/dnqxawhjq/image/upload/v1718335893/clig7zwhaepaj9njvrl7.jpg','thanhquang3@gmail.com','09468354978','2024-06-14 03:31:34','2024-06-14 03:31:34','nguyen','quang'),(6,'thanhquang4','$2a$10$VVELmwOlf8AqNf93m1mlt./zrOzrBeNCclqhWY2qb9JPFt3RFy00i','ROLE_USER','https://res.cloudinary.com/dnqxawhjq/image/upload/v1718336421/lxsb7dsm5mcj2aeqlymt.jpg','thanhquang4@gmail.com','09468354978','2024-06-14 03:40:22','2024-06-14 03:40:22','nguyen','quang 4'),(7,'thanhquang5','$2a$10$UDBDZV8VrbJGxC7Zst16du0UlF.KmaUi5geOCQPHRhW45TEQFX3fi','ROLE_USER','https://res.cloudinary.com/dnqxawhjq/image/upload/v1718337116/lsavutzw2kk9iiprxmuz.jpg','thanhquang5@gmail.com','09468354978','2024-06-14 03:51:57','2024-06-14 03:51:57','nguyen','quang '),(8,'thanhquang6','$2a$10$VrsYgzJihxosxnxNiuMjN.Glqi.TwpecD2onW1B/hrxjLLbWrvaaq','ROLE_USER','https://res.cloudinary.com/dnqxawhjq/image/upload/v1718349258/qu5gp4l4qwgir0s4bvag.jpg','thanhquang6@gmail.com','09468354978','2024-06-14 07:14:19','2024-06-14 07:14:19','nguyen','quang ');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-21 23:27:42
