CREATE DATABASE  IF NOT EXISTS `stray_animal_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `stray_animal_db`;
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: stray_animal_db
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `adoption_record`
--

DROP TABLE IF EXISTS `adoption_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adoption_record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `adoption_info_id` bigint NOT NULL COMMENT '被领养动物信息ID',
  `adopter_user_id` bigint NOT NULL COMMENT '领养用户ID',
  `adopter_name` varchar(50) NOT NULL COMMENT '领养人真实姓名',
  `adopter_phone` char(11) NOT NULL COMMENT '领养人联系电话',
  `adopter_id_card` varchar(255) NOT NULL COMMENT '领养人身份证号码（加密存储）',
  `adoption_reason` text COMMENT '领养理由',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_adoption_info_id` (`adoption_info_id`),
  KEY `fk_record_adopter_user` (`adopter_user_id`),
  CONSTRAINT `fk_record_adopter_user` FOREIGN KEY (`adopter_user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_record_adoption_info` FOREIGN KEY (`adoption_info_id`) REFERENCES `adoption_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='领养记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adoption_record`
--

LOCK TABLES `adoption_record` WRITE;
/*!40000 ALTER TABLE `adoption_record` DISABLE KEYS */;
INSERT INTO `adoption_record` VALUES (1,3,3,'ccc','1223','123','2333','2025-07-04 15:39:07','2025-07-04 15:39:07'),(2,1,1,'123','123','123','123','2025-07-04 16:12:40','2025-07-04 16:12:40'),(3,5,1,'123','123456','132','123','2025-07-04 16:26:47','2025-07-04 16:26:47'),(4,6,4,'13','1223','1231','213','2025-07-04 18:45:00','2025-07-04 18:45:00'),(5,4,4,'1233','123','1231','213','2025-07-04 18:46:50','2025-07-04 18:46:50'),(7,9,1,'A领养','123','123456','123','2025-07-05 01:02:12','2025-07-05 01:02:12'),(8,10,2,'b领养','132','123','b','2025-07-05 01:04:38','2025-07-05 01:04:38');
/*!40000 ALTER TABLE `adoption_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-05 12:19:04
