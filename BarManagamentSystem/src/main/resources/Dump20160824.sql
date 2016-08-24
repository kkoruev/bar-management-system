CREATE DATABASE  IF NOT EXISTS `bar-management-system` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bar-management-system`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bar-management-system
-- ------------------------------------------------------
-- Server version	5.7.11-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill` (
  `bill_id` int(11) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL,
  `completed_at` datetime DEFAULT NULL,
  `owner_id` int(11) NOT NULL,
  PRIMARY KEY (`bill_id`),
  KEY `owner_id_idx` (`owner_id`),
  CONSTRAINT `owner_id` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,'mm','2016-08-14 14:54:52',NULL,7),(2,'mm','2016-08-14 14:58:59',NULL,7),(3,'mm','2016-08-14 15:01:10',NULL,7),(4,'mm','2016-08-14 15:01:10',NULL,7),(5,'mm','2016-08-14 15:01:11',NULL,7),(6,'mm','2016-08-18 17:59:11',NULL,7);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'food');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `price` decimal(6,2) unsigned NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,4.50,'Fries','mega qkite fries',1),(2,5.00,'baked potatoes','mega tasty',1),(3,7.90,'Pizza Palermo',NULL,1),(4,8.40,'Pizza Toscana',NULL,1),(5,6.80,'Pizza Torino',NULL,1),(6,8.60,'Pizza Italiano',NULL,1),(7,6.80,'Pizza Milano',NULL,1),(8,7.80,'Pizza Verona',NULL,1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) unsigned DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `item_id_idx` (`item_id`),
  KEY `order_id_idx` (`order_id`),
  CONSTRAINT `item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `order_unit` (`order_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_unit`
--

DROP TABLE IF EXISTS `order_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_unit` (
  `order_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `comment` varchar(45) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `completed_at` datetime DEFAULT NULL,
  `bill_id` int(11) DEFAULT NULL,
  `taken_by_id` int(11) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `taken_by_id_idx` (`taken_by_id`),
  KEY `bill_id_idx` (`bill_id`),
  CONSTRAINT `bill_id` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`bill_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `taken_by_id` FOREIGN KEY (`taken_by_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_unit`
--

LOCK TABLES `order_unit` WRITE;
/*!40000 ALTER TABLE `order_unit` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password_hash` char(128) NOT NULL,
  `password_salt` char(24) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'James','Tom','ImJfZEe+GrXt+V71k2KX0PrFxJf1PT4s17tywQBVmiCdctOkuQWW69kXRD7HZe3hptOLELFLcNFjSkFF0qFv6Q==','iQj/q0mO0Tkyv0QyiGmoFQ==','admin'),(2,'t','t','P+vAUWdYP7pYq0NgmI40yLVY7oEuNDzBFjZ8qr2C+DmTCaN9hnBcgtfEQQ2XmAQJryF4UkJXRZt21qXI7nEfNQ==','lz/dZ6EezOZF6vjVbwX0qg==','admin'),(3,'Marina','Marinchika','TxOwAIctDAVAjLKlFYUP2Wh/+/xojSbOJ5k8F+4/D7mbvscTBQSqu5tlbiT4Lo4oj82AEWjZCicf42s9gkGdYQ==','0Rs1JfV1Z4wD+2tDQVENsA==','admin'),(4,'James','Michael','wipr1WKOXzVqT2OzeohOk+F9LVSfqfwgyt/daHejW7kDobUzPiDKUhy1HquN9zbqGfT94pR8ZsVKSNMUvzfDdw==','a1QZ9h4/9VktTYGLuYYEGA==','waiter'),(5,'James','Ken','R7XEj4kr066jGmjel1HH4W0YUQGWLiT/FbVCbu5u6LXW/rSGy6kRkOxIda0jI+uyHcM6UvqgojJluj6qlja5WQ==','hyMWil1YEa6CyaF9WLj3Yg==','WAITER'),(6,'test','123','WBjdpaK4pgVTSOVf1LkLAOpL90e/qhjc6NCZPZpaBrRWGB7PfJcC6Yc5f8b5/oL2zo2Ajd8qBv7etK1qpo367Q==','W/lBOuYFWjsWegyanfo0WA==','WAITER'),(7,'Krisofer','kris','4pOp3suPCMSIZHKRvCzuGgb5a5LQWcHwJeMny4HD1YDl7aXYwzjizQes+NRTn383mM7/nxTY0/bAJMpigmApQg==','VW1x6luprX2p0UgTvv2u1w==','WAITER'),(8,'Kristofer','Chris','KewuMhyrGfshUXhkiWSJS2b0F8/m4MVA/skBh6jWK1Fl59S/W9bvjMm2EWh0kquqFAJq0lXGXHwyCyNvBoxeOw==','VZADs51FV1OUp4XbpXPNvQ==','ADMIN'),(9,'Petyo','pete','hPOUhu/DD+yh+Q0I1mvD7VrK8ND7IBg/hC+fnu5MsArFhcDpBkMbmMbfhaxZiG/Xezu4lifCOC0R+MbWackGWA==','ueoZFXQ2PSTSwQT8Y99XFA==','ADMIN');
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

-- Dump completed on 2016-08-24 15:21:24
