-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: twitter
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `follows`
--

DROP TABLE IF EXISTS `follows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follows` (
  `USER_ID` varchar(255) NOT NULL,
  `FOLLOWING_USER_ID` varchar(255) NOT NULL,
  `TIMESTAMP` bigint NOT NULL,
  UNIQUE KEY `USER_ID` (`USER_ID`,`FOLLOWING_USER_ID`),
  KEY `FOLLOWING_USER_ID` (`FOLLOWING_USER_ID`),
  CONSTRAINT `follows_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`),
  CONSTRAINT `follows_ibfk_2` FOREIGN KEY (`FOLLOWING_USER_ID`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follows`
--

LOCK TABLES `follows` WRITE;
/*!40000 ALTER TABLE `follows` DISABLE KEYS */;
INSERT INTO `follows` VALUES ('acsebi','sebi',1648738169434);
/*!40000 ALTER TABLE `follows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(255) NOT NULL,
  `POST_ID` int NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_ID` (`USER_ID`,`POST_ID`),
  KEY `POST_ID` (`POST_ID`),
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`),
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`POST_ID`) REFERENCES `posts` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (5,'sebi',8);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mentions`
--

DROP TABLE IF EXISTS `mentions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mentions` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(255) NOT NULL,
  `POST_ID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `POST_ID` (`POST_ID`),
  CONSTRAINT `mentions_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`),
  CONSTRAINT `mentions_ibfk_2` FOREIGN KEY (`POST_ID`) REFERENCES `posts` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mentions`
--

LOCK TABLES `mentions` WRITE;
/*!40000 ALTER TABLE `mentions` DISABLE KEYS */;
INSERT INTO `mentions` VALUES (1,'acsebi',18),(2,'acsebi',19),(3,'sebi',19),(4,'acsebi',20),(5,'sebi',21),(6,'sebi',22),(7,'sebi',23),(8,'sebi',24),(9,'sebi',25),(10,'sebi',26),(11,'sebi',27),(12,'sebi',28),(13,'sebi',29),(14,'sebi',30),(15,'sebi',39),(16,'sebi',40),(17,'sebi',42),(18,'sebi',44),(19,'sebi',46),(20,'sebi',48),(21,'sebi',50),(22,'sebi',52);
/*!40000 ALTER TABLE `mentions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(255) NOT NULL,
  `MESSAGE` varchar(255) NOT NULL,
  `TIMESTAMP` bigint NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (8,'acsebi','Optional[com.example.twitter.data.entities.Post@f9fe1ef7]',1649794017899),(9,'acsebi','Ce fain!',1649794320300),(11,'sebi','Ce fain!',1649837193480),(12,'sebi','Ce fain!',1649837332975),(13,'sebi','Ce fain!',1649837794897),(14,'sebi','Ce fain!',1649837830337),(15,'sebi','1',1649837846968),(16,'sebi','1',1649838093333),(17,'sebi','123',1649838334113),(18,'sebi','123',1649842608233),(19,'sebi','Ce a vrut sa spuna @acsebi? Dar @sebi?',1649842660023),(20,'sebi','Ce a vrut sa spuna @acsebi????',1649843120756),(21,'acsebi','@sebi Acesta este un reply!',1649852124481),(22,'acsebi','@sebi Acesta este un reply!',1649852464758),(23,'acsebi','@sebi Acesta este un reply!',1649852632035),(24,'acsebi','@sebi Acesta este un reply!',1649852854643),(25,'acsebi','@sebi Acesta este un reply!',1649853438064),(26,'acsebi','@sebi Acesta este un reply!',1649853538886),(27,'acsebi','@sebi Acesta este un reply!',1649853636773),(28,'acsebi','@sebi Acesta este un reply!',1649854043229),(29,'acsebi','@sebi Acesta este un reply!',1649854044730),(30,'acsebi','@sebi Acesta este un reply!',1649854080171),(32,'acsebi','@sebi Acesta este un reply!',1649855616925),(33,'acsebi','@sebi Acesta este un reply!',1649855737255),(34,'acsebi','@sebi Acesta este un reply!',1649855933386),(39,'acsebi','@sebi Acesta este un reply!',1649857596216),(40,'acsebi','@sebi Acesta este un reply!',1649857662124),(42,'acsebi','@sebi Acesta este un reply!',1649857723151),(44,'acsebi','@sebi Acesta este un reply!',1649857736611),(46,'acsebi','@sebi Acesta este un reply!',1649858827005),(48,'acsebi','@sebi Acesta este un reply!',1649859151064),(50,'acsebi','@sebi AAAAcesta este un reply!',1649859175704),(52,'acsebi','@sebi CEEEE? AAAAcesta este un reply!',1649859210462);
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `replies`
--

DROP TABLE IF EXISTS `replies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `replies` (
  `POST_ID` int NOT NULL,
  `PARENT_POST_ID` int NOT NULL,
  `PUBLIC` tinyint(1) NOT NULL,
  PRIMARY KEY (`POST_ID`),
  KEY `PARENT_POST_ID` (`PARENT_POST_ID`),
  CONSTRAINT `replies_ibfk_1` FOREIGN KEY (`POST_ID`) REFERENCES `posts` (`ID`),
  CONSTRAINT `replies_ibfk_2` FOREIGN KEY (`PARENT_POST_ID`) REFERENCES `posts` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `replies`
--

LOCK TABLES `replies` WRITE;
/*!40000 ALTER TABLE `replies` DISABLE KEYS */;
/*!40000 ALTER TABLE `replies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `ID` varchar(255) NOT NULL,
  `FIRST_NAME` varchar(255) NOT NULL,
  `LAST_NAME` varchar(255) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('acsebi','A','B','C','D'),('sebi','A','B','C','D');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-13 20:41:29
