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
-- Table structure for table `adoption_info`
--

DROP TABLE IF EXISTS `adoption_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adoption_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `upload_record_id` bigint NOT NULL COMMENT '关联的上报记录ID',
  `name` varchar(50) NOT NULL COMMENT '动物名字',
  `species` varchar(50) NOT NULL COMMENT '物种',
  `gender` enum('母','公') NOT NULL COMMENT '性别',
  `health_status` varchar(100) DEFAULT NULL COMMENT '健康状态',
  `sterilization_status` enum('未绝育','已绝育','不详') NOT NULL COMMENT '绝育状态',
  `description` text COMMENT '补充描述',
  `adoption_status` enum('AVAILABLE','ADOPTED') NOT NULL DEFAULT 'AVAILABLE' COMMENT '领养状态: AVAILABLE-可领养, ADOPTED-已被领养',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_upload_record_id` (`upload_record_id`),
  CONSTRAINT `fk_info_upload_record` FOREIGN KEY (`upload_record_id`) REFERENCES `upload_records` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='待领养动物信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adoption_info`
--

LOCK TABLES `adoption_info` WRITE;
/*!40000 ALTER TABLE `adoption_info` DISABLE KEYS */;
INSERT INTO `adoption_info` VALUES (1,1,'测试动物名字','猫','公','123','不详','1232','ADOPTED','2025-07-04 15:35:36','2025-07-04 15:35:36'),(2,2,'123','猫','公','123','不详','133','AVAILABLE','2025-07-04 15:36:24','2025-07-04 15:36:24'),(3,3,'123','猫','公','123','不详','123','ADOPTED','2025-07-04 15:38:06','2025-07-04 15:38:06'),(4,4,'412','猫','公','123','不详','2131','ADOPTED','2025-07-04 16:12:12','2025-07-04 16:12:12'),(5,5,'123update','猫','公','1233','不详','123','ADOPTED','2025-07-04 16:26:18','2025-07-04 16:26:18'),(6,6,'123','猫','公','132','不详','1233','ADOPTED','2025-07-04 18:43:46','2025-07-04 18:43:46'),(9,9,'123update','猫','公','123','不详','123','ADOPTED','2025-07-05 01:01:21','2025-07-05 01:01:21'),(10,10,'123','猫','公','a上报一个','不详','123','ADOPTED','2025-07-05 01:03:53','2025-07-05 01:03:53'),(11,11,'testuserA上报的动物','猫','公','123','不详','23','AVAILABLE','2025-07-05 10:34:43','2025-07-05 10:34:43');
/*!40000 ALTER TABLE `adoption_info` ENABLE KEYS */;
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
