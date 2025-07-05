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
-- Table structure for table `upload_record_image`
--

DROP TABLE IF EXISTS `upload_record_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `upload_record_image` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `upload_record_id` bigint NOT NULL COMMENT '关联的上报记录ID',
  `image_url` varchar(255) NOT NULL COMMENT '单张图片的URL',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_upload_record_id` (`upload_record_id`),
  CONSTRAINT `fk_image_upload_record` FOREIGN KEY (`upload_record_id`) REFERENCES `upload_records` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='上报记录图片表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upload_record_image`
--

LOCK TABLES `upload_record_image` WRITE;
/*!40000 ALTER TABLE `upload_record_image` DISABLE KEYS */;
INSERT INTO `upload_record_image` VALUES (3,2,'/uploads/37485c9f-f9e0-4192-9c20-eb5bd1d35c23.jpeg','2025-07-04 15:36:24'),(4,2,'/uploads/c6d21458-e86e-48df-98f2-8ad7fb0a7995.jpeg','2025-07-04 15:36:24'),(5,3,'/uploads/8866483f-7f81-40f3-b467-137b74d76a75.jpeg','2025-07-04 15:38:06'),(6,3,'/uploads/d97f0c3b-46ae-4cb7-88e1-fc829e93fa0d.jpg','2025-07-04 15:38:06'),(7,4,'/uploads/58cb33d3-1a3d-4afa-bf84-93c4a31457b1.jpeg','2025-07-04 16:12:12'),(8,4,'/uploads/bc5467dd-d7d2-4234-b3f2-ec05621383b5.jpeg','2025-07-04 16:12:12'),(9,4,'/uploads/33da0349-a587-4695-8fa8-857f918e4717.jpg','2025-07-04 16:12:12'),(12,6,'/uploads/eb290cc9-f31e-4d9a-8767-cffe5306d2ba.jpeg','2025-07-04 18:43:46'),(13,6,'/uploads/11203f61-1df9-4c39-a5ec-b7c9b53e37ff.jpeg','2025-07-04 18:43:46'),(14,6,'/uploads/3e72d81f-1735-47e2-b877-9e014fcec274.jpg','2025-07-04 18:43:46'),(15,5,'/uploads/41077ee5-fd46-4626-ad13-19e8f38ad469.jpeg','2025-07-05 00:55:42'),(16,5,'/uploads/0f548edd-71f1-43ab-83e4-46690c1ffee6.jpeg','2025-07-05 00:55:42'),(17,5,'/uploads/f2592812-35c0-4a45-8456-5ba307d7bb16.jpg','2025-07-05 00:55:42'),(18,1,'/uploads/ab802ae7-a462-4b53-ad0a-73db9d377fb5.jpeg','2025-07-05 00:56:41'),(19,1,'/uploads/2311bb25-0504-428d-b64f-262a52f30d22.jpeg','2025-07-05 00:56:41'),(23,10,'/uploads/620d1ad3-aa75-4073-a36e-36a13e15d55b.jpeg','2025-07-05 01:03:53'),(24,9,'/uploads/f97b7a4a-fb8a-4425-95df-523332c91985.jpeg','2025-07-05 01:07:48'),(25,9,'/uploads/ccca0004-68a8-4986-ada4-e122b70fa6f3.jpg','2025-07-05 01:07:48'),(26,11,'/uploads/d310b8e1-7a56-4e84-bdf9-87df9bab0394.jpg','2025-07-05 10:34:43'),(27,11,'/uploads/10c51b0a-b0b8-4ec4-8e5d-44dbfcce7fd5.jpeg','2025-07-05 10:34:43');
/*!40000 ALTER TABLE `upload_record_image` ENABLE KEYS */;
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
