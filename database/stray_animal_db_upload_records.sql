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
-- Table structure for table `upload_records`
--

DROP TABLE IF EXISTS `upload_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `upload_records` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '上报用户ID',
  `reporter` varchar(50) NOT NULL COMMENT '上报人姓名 (冗余字段，可从user表获取)',
  `reporter_phone` char(11) NOT NULL COMMENT '联系电话 (冗余字段，可从user表获取)',
  `name` varchar(50) NOT NULL COMMENT '动物名字',
  `species` varchar(50) NOT NULL COMMENT '物种',
  `gender` enum('母','公') NOT NULL COMMENT '性别',
  `health_status` varchar(100) DEFAULT NULL COMMENT '健康状态',
  `sterilization_status` enum('未绝育','已绝育','不详') NOT NULL COMMENT '绝育状态',
  `discovery_time` date NOT NULL COMMENT '发现时间',
  `discovery_place` varchar(200) NOT NULL COMMENT '发现地点',
  `description` text COMMENT '补充描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `fk_upload_user` (`user_id`),
  CONSTRAINT `fk_upload_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='动物上报记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upload_records`
--

LOCK TABLES `upload_records` WRITE;
/*!40000 ALTER TABLE `upload_records` DISABLE KEYS */;
INSERT INTO `upload_records` VALUES (1,1,'aaa','123','测试动物名字','猫','公','123','不详','2025-07-01','123','1232','2025-07-04 15:35:36','2025-07-04 15:35:36'),(2,1,'aaa','123','123','猫','公','123','不详','2025-07-02','123','133','2025-07-04 15:36:24','2025-07-04 15:36:24'),(3,2,'bbb','123','123','猫','公','123','不详','2025-07-02','123','123','2025-07-04 15:38:06','2025-07-04 15:38:06'),(4,1,'aaa','1223','412','猫','公','123','不详','2025-07-11','123','2131','2025-07-04 16:12:12','2025-07-04 16:12:12'),(5,1,'更新后的名字','123456','123update','猫','公','1233','不详','2025-07-02','123','123','2025-07-04 16:26:18','2025-07-04 16:26:18'),(6,4,'ddd','123','123','猫','公','132','不详','2025-07-05','13','1233','2025-07-04 18:43:46','2025-07-04 18:43:46'),(9,2,'bupdate','123','123update','猫','公','123','不详','2025-06-30','123','123','2025-07-05 01:01:21','2025-07-05 01:01:21'),(10,1,'a上报一个','123','123','猫','公','a上报一个','不详','2025-07-01','123','123','2025-07-05 01:03:53','2025-07-05 01:03:53'),(11,1,'testuserA','456','testuserA上报的动物','猫','公','123','不详','2025-07-05','123','23','2025-07-05 10:34:43','2025-07-05 10:34:43');
/*!40000 ALTER TABLE `upload_records` ENABLE KEYS */;
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
