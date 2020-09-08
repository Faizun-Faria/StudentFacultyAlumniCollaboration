-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: sfac
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `alumni_data`
--

DROP TABLE IF EXISTS `alumni_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumni_data` (
  `email_id` char(100) DEFAULT NULL,
  `first_name` char(100) DEFAULT NULL,
  `last_name` char(100) DEFAULT NULL,
  `pass` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumni_data`
--

LOCK TABLES `alumni_data` WRITE;
/*!40000 ALTER TABLE `alumni_data` DISABLE KEYS */;
INSERT INTO `alumni_data` VALUES ('faria@gmail.com','Faria','Faizun','FQWvml7koIMoxaQ8FCYYfw=='),('fariaahmed@gmail.com','Faria','Ahmed','oB1bzTMZ2wchrAeyPedkUQ==');
/*!40000 ALTER TABLE `alumni_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_data`
--

DROP TABLE IF EXISTS `faculty_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty_data` (
  `email_id` char(100) DEFAULT NULL,
  `first_name` char(100) DEFAULT NULL,
  `last_name` char(100) DEFAULT NULL,
  `pass` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_data`
--

LOCK TABLES `faculty_data` WRITE;
/*!40000 ALTER TABLE `faculty_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `faculty_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `organization_name` varchar(100) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `post_time` varchar(50) DEFAULT NULL,
  `post_delete_date` varchar(50) DEFAULT NULL,
  `post_date_time` varchar(100) DEFAULT NULL,
  `author_name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES ('ABC','Software Engineer','Language requirements : java or c++, python, mysql.','20:46:12','2020-09-15','2020-09-08 20:46:12','Faria'),('SAF','Data analyst','ABC DEF','20:46:47','2020-09-15','2020-09-08 20:46:47','Faria');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `previous_passwords`
--

DROP TABLE IF EXISTS `previous_passwords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `previous_passwords` (
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `previous_passwords`
--

LOCK TABLES `previous_passwords` WRITE;
/*!40000 ALTER TABLE `previous_passwords` DISABLE KEYS */;
INSERT INTO `previous_passwords` VALUES ('am@gmail.com','ABSpPnTcTtEaqmMi4/ykRg=='),('faizun@g.bracu.ac.bd','+LaZ0psv4Nvq1OVqMTO1kA=='),('faizunfaria@g.bracu.ac.bd','+LaZ0psv4Nvq1OVqMTO1kA=='),('faizun@g.bracu.ac.bd','+LaZ0psv4Nvq1OVqMTO1kA=='),('ashiq@bracu.ac.bd','SArrQtex45N/6NsSof/m2A=='),('faizun@g.bracu.ac.bd','+LaZ0psv4Nvq1OVqMTO1kA=='),('faria@gmail.com','FQWvml7koIMoxaQ8FCYYfw=='),('fariaahmed@gmail.com','oB1bzTMZ2wchrAeyPedkUQ==');
/*!40000 ALTER TABLE `previous_passwords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_data`
--

DROP TABLE IF EXISTS `student_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_data` (
  `email_id` char(100) DEFAULT NULL,
  `first_name` char(100) DEFAULT NULL,
  `last_name` char(100) DEFAULT NULL,
  `pass` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_data`
--

LOCK TABLES `student_data` WRITE;
/*!40000 ALTER TABLE `student_data` DISABLE KEYS */;
INSERT INTO `student_data` VALUES ('faizun@g.bracu.ac.bd','Faizun','Faria','+LaZ0psv4Nvq1OVqMTO1kA==');
/*!40000 ALTER TABLE `student_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-09  1:58:06
