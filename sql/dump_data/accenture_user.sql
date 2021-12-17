-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: accenture.clch7hatfplf.us-east-2.rds.amazonaws.com    Database: accenture
-- ------------------------------------------------------
-- Server version	8.0.23

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `address` varchar(120) DEFAULT NULL,
  `password` char(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin@gmail.com','Janis','Kalnins','Skrundas 8, Broceni','$2a$10$62AAhgEWKwHLzEs1k1dqEuwOVHIM2MgCbI.yzLbx./5m2md9StfAC'),(2,'manager','baiba.skujevsk@gmail.com','Ivo','Lapsa','Skolas 120, Valmiera','$2a$10$TZXYm3FwHbhLlGkmClsIpOnk7jQcOKe2Rt2ByHMUsTNC.u4alo5Gy'),(3,'customer','aprily@inbox.lv','Zane','Otto','Liepu 22, Smiltene','$2a$10$BPFrIEJvcjMY8oV2cQHYS.hqO9g6oAXKBqWOQtcT24GBRtBIw8rCq'),(4,'lycustomer','ly@gmail.com','Monika','Saule','Druvas 4, Saldus','$2a$10$FhOycnbGtDkayoopnou8hOoHf2gWRnruofKMb85RowwFPDW4/Cgoa'),(5,'baiba','baiba@baiba.lv','baiba','Skujevska','Druvas 12, Valmiera','$2a$10$2Rvp3Vyk9i1IXcQ0yfPmOOADZ3ggeYaVi79Wv4AYZk34LcPtaoUCy'),(6,'janis','janis@janis.lv','janis','Krauklis','Krauka iela 12, Valmiera','$2a$10$I5nP3tGUZafUsqPQq.cpWuhjYhEwr3qnUKrRSzJxxRz7JHWHJAzq.'),(7,'edgars','edgars@gmail.com','edgars','kalnins','druvas iela 12 saldus','$2a$10$hdHpeFOaILQLS9zsIxE8Oe1Mg6c8wH7PPfifXZP0liQ8JJTUi8HZK'),(8,'monika','','','','','$2a$10$yE7KwCJCqRscRp94vyS.FOyURgGCKX4Z2PQL7AYPgrXciBVeyPdSG'),(9,'paulina','','','','','$2a$10$f15dQ9U330WNWYfz4EYAluNGY8HKxtl6tdnl6xJ2SPu.NAc9Dm142'),(10,'','','','',NULL,'$2a$10$Jkq7bO.fMs7DQUgYVD6QLegtj1RjIVCod2/693yiGQAg9j0jETMs2'),(11,'Naomi','','','',NULL,'$2a$10$h3m2mlRh/V4UFNOq9NULrOJTQROW9ktzAUkp3Q/FNjPT/fI6kNHRK'),(12,'noni','','','',NULL,'$2a$10$8w5P2q/SR.oX3WaxeqV6kezdf9dcf3M/1NP5aYAGw5RGwVuVetH6i'),(13,'Kaina','','','',NULL,'$2a$10$M1MhiFRmgKjbMqCeaInl7OsTl5jgqZp2095MVzMubE39PH7JMKcnu'),(14,'kainas','atiatia','mama','lialia',NULL,'$2a$10$9e4rT0/CAIC63dqxpro2BuYgGofHsQj/BTqeLAnUgo6DhovpCTDxi'),(15,'adasa','','','',NULL,'$2a$10$kaAx3bNGHnu2t9qRubNeReB/b7YV/SlvH0csNETAxco2bH9j1ZIMe'),(16,'sasc','','sdcsd','',NULL,'$2a$10$VRhZFvtRGxZWG5v2bJkx5ufGzRSiGlPmtIyzZWvGpWoR3Vb9ySY8.'),(17,'kuku','','','',NULL,'$2a$10$8OlEWYhX6gz3M1u8n3weEOu3KaMn0j2k31QAik6I2.G73NOZDK0zS'),(18,'Monika4','monika@gmail.com','Monika','Monika',NULL,'$2a$10$UWfMFxGFunIi4qutu0CJzuyki5IXQVUwM5YhlO157t7/Z7zDDDRrC'),(19,'m','monika@gmail.com','','',NULL,'$2a$10$jvAb9VuY8Xb9M6Cizaz5fujXBzvtw7l9EqmZYQ/.NY.5S57IsR0ue'),(26,'anna','anna@anna.lv','Anna','Marta','Annas 2, Riga','$2a$10$VNwlXHH4qrXSgNrKfSEIU.7l0o3QQVs0aV9n69m9uf31E.nn/DXXC'),(27,'marta','marta@marta.lv','marta','Krumina','Martas iela 23, Riga','$2a$10$.1oicpHm4lZlP5NGXT9ClOqVkKjef/WOYXCFlBgtGmjo.SQKWU3DG'),(30,'d','','','',NULL,'$2a$10$kHiGXreisxuvIyHhMnVo9.l4UcgbhrXHpFVbCcNFT8EG1Nwxp3gVe');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-17  7:46:04
