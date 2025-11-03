-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  `price` double DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (2,'Cricket Bat',1,9999,'https://i.ibb.co/xbzjpYf/cricket-bat.jpg','surya123@gmail.com'),(10,'Volleyball',1,999,'https://i.ibb.co/ccGmd2z1/Vollyball.jpg','akkabathulasurya@gmail.com');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_name` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Cricket','Cricket gear and equipment'),(2,'Football','Football and soccer products'),(3,'Basketball','Basketball gear and accessories'),(4,'Baseball','Baseball equipment and gear'),(5,'Hockey','Hockey sticks, pads, and accessories'),(6,'Tennis','Tennis rackets, balls, and accessories'),(7,'Badminton','Badminton rackets and shuttlecocks'),(8,'Boxing','Boxing gloves and protective gear'),(9,'Martial Arts','Martial arts equipment and clothing'),(10,'Running & Jogging','Running shoes, apparel, and accessories'),(11,'Sports Shoes','Various sports footwear'),(12,'Sports Apparel','Jerseys, shorts, and other apparel'),(13,'Protective Gear','Helmets, pads, and guards'),(14,'Sports Bags & Backpacks','Bags for sports equipment and personal use'),(15,'Volleyball','Volleyballs, nets, and accessories');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cod_charges` double DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `order_items` varchar(2000) DEFAULT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `razorpay_order_id` varchar(255) DEFAULT NULL,
  `razorpay_payment_id` varchar(255) DEFAULT NULL,
  `shipping_address` varchar(255) DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,40,'2025-10-25 14:15:18.937711','[{\"itemName\":\"Sun Risers Jersey\",\"quantity\":1,\"price\":499.99,\"imageUrl\":\"https://i.ibb.co/XZf4HDtV/Sunriser-Jersey.jpg\",\"id\":1}]','PLACED','COD','PENDING',NULL,NULL,'Ganesh temple road, Deverapalli',539.99,'surya123@gmail.com','Surya','9573953672'),(2,0,'2025-10-27 12:09:10.598872','[{\"itemName\":\"Basket ball\",\"quantity\":1,\"price\":899.99,\"imageUrl\":\"https://i.ibb.co/hJNhFVY4/basketball.jpg\",\"id\":3}]','PLACED','UPI','PAID','MOCK_ORD_552675CE','MOCK_PAY_F8730527','devarapalli',899.99,'akkabathulasurya@gmail.com','Surya','9573953672'),(3,40,'2025-10-27 12:12:04.377078','[{\"itemName\":\"Sun Risers Jersey\",\"quantity\":1,\"price\":499.99,\"imageUrl\":\"https://i.ibb.co/XZf4HDtV/Sunriser-Jersey.jpg\",\"id\":4},{\"itemName\":\"Cricket Shoes\",\"quantity\":1,\"price\":999.99,\"imageUrl\":\"https://i.ibb.co/Cpwyg277/Cricket-Shoes.jpg\",\"id\":5}]','PLACED','COD','PENDING',NULL,NULL,'devarapalli',1539.98,'akkabathulasurya@gmail.com','Surya','9573953672'),(4,40,'2025-10-27 12:48:14.737558','[{\"itemName\":\"vollyball\",\"quantity\":1,\"price\":999,\"imageUrl\":\"https://i.ibb.co/ccGmd2z1/Vollyball.jpg\",\"id\":6}]','PLACED','COD','PENDING',NULL,NULL,'devarapalli',1039,'akkabathulasurya@gmail.com','Surya','9573953672'),(5,40,'2025-10-27 15:03:19.914670','[{\"itemName\":\"T-shirt\",\"quantity\":1,\"price\":500,\"imageUrl\":\"https://i.ibb.co/vv417V3f/T-shirt.jpg\",\"id\":7}]','PLACED','COD','PENDING',NULL,NULL,'devarapalli',540,'akkabathulasurya@gmail.com','Surya','9573953672'),(6,40,'2025-10-27 18:10:38.419254','[{\"itemName\":\"Vollyball\",\"quantity\":2,\"price\":999,\"imageUrl\":\"https://i.ibb.co/ccGmd2z1/Vollyball.jpg\",\"id\":8}]','PLACED','COD','PENDING',NULL,NULL,'devarapalli',2038,'akkabathulasurya@gmail.com','Surya','9573953672');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_otp`
--

DROP TABLE IF EXISTS `password_reset_otp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_reset_otp` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `otp` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expires_at` datetime NOT NULL,
  `used` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_email_used` (`email`,`used`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- CONSTRAINT `password_reset_otp_ibfk_1` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE CASCADE
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_otp`
--

LOCK TABLES `password_reset_otp` WRITE;
/*!40000 ALTER TABLE `password_reset_otp` DISABLE KEYS */;
INSERT INTO `password_reset_otp` VALUES (12,'akkabathulasurya@gmail.com','877853','2025-10-25 20:36:14','2025-10-25 20:46:14',1);
/*!40000 ALTER TABLE `password_reset_otp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
  -- If you want, you can also leave this:
  -- KEY `fk_category` (`category_id`)
  -- but make sure it's either LEFT IN as a real line (with comma above) or completely removed if not needed
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Ms Dhoni signed bat','Cricket Bat',9999,'https://i.ibb.co/xbzjpYf/cricket-bat.jpg',1),(3,'','Volleyball',999,'https://i.ibb.co/ccGmd2z1/Vollyball.jpg',15),(4,'','Basket ball',899.99,'https://i.ibb.co/hJNhFVY4/basketball.jpg',3),(7,'','T-shirt',500,'https://i.ibb.co/vv417V3f/T-shirt.jpg',12),(8,'','Hockey Stick',899.99,'https://i.ibb.co/7tkdvb0c/Hockey-Stick.png',5),(9,'','Sun Risers Jersey',499.99,'https://i.ibb.co/XZf4HDtV/Sunriser-Jersey.jpg',12),(10,'','Cricket Shoes',999.99,'https://i.ibb.co/Cpwyg277/Cricket-Shoes.jpg',11),(11,'','Football Jersey ',750,'https://i.ibb.co/XfbpCFTp/Football-Jersey.jpg',12),(12,'','Hockey Ball',500,'https://i.ibb.co/M5yF8YPF/Hockey-Ball.jpg',5),(13,'','Cricket Gloves',499,'https://i.ibb.co/yc9GQsV7/Cricket-Gloves.jpg',1),(14,'','Football Net',799,'https://i.ibb.co/Kxv3404f/Football-Net.jpg',2),(15,'','Cricket Pads',1200,'https://i.ibb.co/NzWVws3/Cricket-Pads.jpg',13),(16,'','Cricket Leather Ball',450,'https://i.ibb.co/1fddGM3z/Cricket-Leather-Ball.jpg',1),(17,'','Football Shoes',799,'https://i.ibb.co/ttpBbnv/Football-Shoes.jpg',11),(18,'','Cricket Wicketkeeper Gloves',499,'https://i.ibb.co/rXhQPnm/Cricket-Wicket-Keeping-Gloves.jpg',13),(19,'','Cricket Helmate',699,'https://i.ibb.co/WvrNQchr/Cricket-Helmate.jpg',13),(20,'','Football ',899,'https://i.ibb.co/hxj2ghp5/Football.jpg',2),(21,'','Hockey Shoes',499,'https://i.ibb.co/0VZgH3D5/Hockey-Shoes.jpg',11),(22,'','Cricket Hand Pads',400,'https://i.ibb.co/m5dhFZv2/Cricket-Hand-Pads.jpg',13),(23,'','Hockey Pads',499,'https://i.ibb.co/JwNwst2L/Hockey-Pads.jpg',13),(24,'','Cricket Rubber Ball',70,'https://i.ibb.co/4gC2KbBR/Cricket-Rubber-Ball.jpg',1),(25,'','Hockey Gloves',400,'https://i.ibb.co/dwfV4WpP/Hockey-Gloves.jpg',13),(26,'','Goalkeeping Gloves',599,'https://i.ibb.co/pFFX5pX/Football-Gloves.jpg',13),(27,'','Abdominal Guards',350,'https://i.ibb.co/JWMfj7y1/Cricket-Guards.jpg',13),(28,'','Sports Bag Pack',599,'https://i.ibb.co/S7V1GNt7/bagpacks.jpg',14),(29,'','Sports Bag ',699,'https://i.ibb.co/zLc5r7m/sports-bag.jpg',14),(30,'','Sports Sleeveless T-Shirt',299,'https://i.ibb.co/dJ4Fc7Dg/sleevless.jpg',12),(31,'','Sports Shots',499,'https://i.ibb.co/4w69G8kv/shorts-2.jpg',12),(32,'','Sleeveless T-Shirt',399,'https://i.ibb.co/LXGpp9jS/sleeveless-2.jpg',12),(33,'','Sports Shorts',599,'https://i.ibb.co/bTxTxsC/shorts-1.jpg',12),(34,'','Hockey Helmate',1500,'https://i.ibb.co/BHrTB56j/Hockey-Helmate.jpg',5),(35,'','Arm Sleeves',250,'https://i.ibb.co/W4xPY761/vollyball-arm-sleeves.jpg',13),(36,'','Volleyball Net',1000,'https://i.ibb.co/wrhQNyv8/vollyball-net.jpg',15),(37,'','Volleyball Shoes',799,'https://i.ibb.co/r24DRP2G/v-shoes.jpg',11),(38,'','Sports Track Pant',650,'https://i.ibb.co/PvH4n6jv/track1.jpg',12),(39,'','Joggers',799,'https://i.ibb.co/ZpcpkvDX/jogger-pants.jpg',12),(40,'','Volleyball Jersey',499,'https://i.ibb.co/fz60LSSK/vollyball-Jersey.jpg',15),(41,'','Knee Pads',299,'https://i.ibb.co/hkyrSsV/vollyball-knee-pads.jpg',15),(43,'','Basketball Shoes',999,'https://i.ibb.co/XHbwVnq/B-shoes.jpg',3),(44,'','Martial Arts Belts',399,'https://i.ibb.co/9k2qkJpH/mar-belts.jpg',9),(45,'','Running Shoes',699,'https://i.ibb.co/99V66Rt1/Running-shoes.jpg',10),(46,'','Tennis Net',599,'https://i.ibb.co/2YpQHyH9/ten-net.jpg',6),(47,'','Tennis Shoes',799,'https://i.ibb.co/Y42xcXtL/t-shoes.jpg',6),(48,'','Boxing Gloves',899,'https://i.ibb.co/9HQkhPGL/boxing-gloves.jpg',8),(49,'','Boxing Head',1100,'https://i.ibb.co/MykHZYyn/box-head.jpg',13),(50,'','Badminton Net',999,'https://i.ibb.co/YBWdYdbc/bat-net.jpg',7),(51,'','Tennis Jersey',799,'https://i.ibb.co/pBqcQ0xx/tennis-jersey-set.jpg',12),(52,'','Boxing Guard',799,'https://i.ibb.co/spfrMvrv/box-guard.jpg',13),(53,'','Protective Gearset Baseball',1800,'https://i.ibb.co/d0mdP503/b-protective.jpg',13),(54,'','Badminton Jersey',900,'https://i.ibb.co/mF6Q1DH7/batmenton-jersey.jpg',7),(55,'','Tennis Ball',399,'https://i.ibb.co/5hQqbpSd/tennis-ball.jpg',6),(56,'','Badminton Shoes',699,'https://i.ibb.co/kggQHSK5/bat-shoes.jpg',7),(57,'','Shuttlecock Plastic',150,'https://i.ibb.co/fVyg2tYM/shuttle-cock-plastic.jpg',7),(58,'','Indian Jersey',1000,'https://i.ibb.co/q3WkqbYc/india.jpg',1),(59,'','Baseball Gloves',1500,'https://i.ibb.co/H3jTdds/b-gloves.jpg',4),(60,'','Karate Uniform',1200,'https://i.ibb.co/LhRkjz00/karate.jpg',9),(61,'','Baseball Bat',1300,'https://i.ibb.co/N6BYgds7/base-bat.jpg',4),(62,'','Feather Shuttlecock',200,'https://i.ibb.co/5Pj3R7S/shuttle-cock.jpg',7),(63,'','Telugu Titans Jersey',600,'https://i.ibb.co/GvhtnqQj/tt-jersey.jpg',12),(64,'','Tennis Racket',1400,'https://i.ibb.co/jPddGFrQ/t-racket.jpg',6),(65,'','Badminton Racket',899,'https://i.ibb.co/rGVJsZRq/b-racket.jpg',7),(66,'','Basketball Ring',799,'https://i.ibb.co/DHp1V4r2/b-ring.jpg',3),(67,'','Baseball',1200,'https://i.ibb.co/Tx9RPnbY/baseball.jpg',4),(68,'','Taekwondo Uniform',1200,'https://i.ibb.co/prkB1bdX/taekwondo-uniform.jpg',9),(69,'','Baseball Jersey',1300,'https://i.ibb.co/PGq5vKV5/base-jersey.jpg',4),(70,'','Ring Board',1700,'https://i.ibb.co/FL9kryN9/b-ring-board.jpg',3),(71,'','Basketball Jersey',1100,'https://i.ibb.co/PzwHFpHH/b-jersey.jpg',3),(72,'','Chennai Super Kings',800,'https://i.ibb.co/zhbBxGCF/chennai.jpg',1),(73,'','Mumbai Indians',800,'https://i.ibb.co/dJH5Myrc/mumbai.jpg',1),(74,'','RCB Jersey',800,'https://i.ibb.co/3yJ6cwn2/rcb.jpg',1),(75,'','SRH 2.O',900,'https://i.ibb.co/XQ19B1Z/srh-new.jpg',1),(76,'','Rajasthan Royals',800,'https://i.ibb.co/HT36F7rf/rr.jpg',1),(77,'','Punjab Kings',800,'https://i.ibb.co/zHTSDd7j/punjab.jpg',1),(78,'','Delhi Capitals',800,'https://i.ibb.co/RTnmyYW1/delhi.jpg',1),(79,'','Lucknow Super Giants',800,'https://i.ibb.co/wh4n7Fv9/lucknow.jpg',1),(80,'','Gujarath Titans',800,'https://i.ibb.co/5WjJRqkm/gujarath.jpg',1),(81,'','Kolkata Knight Riders',800,'https://i.ibb.co/WWX0M6qT/kolkatha.jpg',1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contact_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'6301944759','gunaguna1747@gmail.com','$2a$10$80Z.i358cNCybdzf7DvyluoRfo4gCMWB2j9SBgw7x.3eVy0cqPir6','admin','Guna'),(5,'9573953672','akkabathulasurya@gmail.com','$2a$10$4hsG.1Xj/8c1a5YEFzTkOuyy.OuG3D5Bmn5Z6Q0fSJXYI4K3neM2C','user','Surya');
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

-- Dump completed on 2025-11-02 14:57:50
