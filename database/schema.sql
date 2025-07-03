-- ----------------------------
-- Table structure for forum_post
-- ----------------------------
DROP TABLE IF EXISTS `forum_post`;
CREATE TABLE `forum_post` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '帖子标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '帖子内容',
  `image_url` varchar(255) DEFAULT NULL COMMENT '帖子配图URL',
  `likes_count` int DEFAULT '0' COMMENT '点赞数',
  `user_id` bigint DEFAULT NULL COMMENT '发布用户ID',
  `author_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '作者昵称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='论坛帖子表';

-- ----------------------------
-- Table structure for user_post_like
-- ----------------------------
DROP TABLE IF EXISTS `user_post_like`;
CREATE TABLE `user_post_like` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `post_id` bigint NOT NULL COMMENT '帖子ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_post` (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户点赞帖子关联表';

-- ----------------------------
-- Table structure for forum_comment
-- ----------------------------
DROP TABLE IF EXISTS `forum_comment`;
CREATE TABLE `forum_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `post_id` bigint NOT NULL COMMENT '所属帖子ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `author_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论者昵称',
  `user_id` bigint DEFAULT NULL COMMENT '关联用户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_post_id` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='论坛评论表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `role` varchar(20) NOT NULL DEFAULT 'USER' COMMENT '角色 (USER, ADMIN)',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Table structure for upload_records
-- ----------------------------
DROP TABLE IF EXISTS `upload_records`;
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
  `image_url` text COMMENT '图片URL列表，逗号分隔',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `fk_upload_user` (`user_id`),
  CONSTRAINT `fk_upload_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='动物上报记录表';

-- ----------------------------
-- Table structure for adoption_infos
-- ----------------------------
DROP TABLE IF EXISTS `adoption_infos`;
CREATE TABLE `adoption_infos` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '发布领养信息的用户ID',
  `upload_record_id` bigint DEFAULT NULL COMMENT '关联的上报记录ID',
  `name` varchar(50) NOT NULL COMMENT '动物名字',
  `species` varchar(50) NOT NULL COMMENT '物种',
  `gender` enum('母','公') NOT NULL COMMENT '性别',
  `health_status` varchar(100) DEFAULT NULL COMMENT '健康状态',
  `sterilization_status` enum('未绝育','已绝育','不详') NOT NULL COMMENT '绝育状态',
  `base_name` varchar(100) DEFAULT '暂无' COMMENT '所在基地',
  `discovery_time` date NOT NULL COMMENT '发现时间',
  `discovery_place` varchar(200) NOT NULL COMMENT '发现地点',
  `description` text COMMENT '补充描述',
  `image_url` text COMMENT '图片URL列表，逗号分隔',
  `adoption_status` enum('可领养','已被领养') DEFAULT '可领养' COMMENT '领养状态',
  `adopted_time` datetime DEFAULT NULL COMMENT '领养时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_adoption_info` (`name`,`species`,`discovery_time`),
  KEY `fk_adoption_info_user` (`user_id`),
  KEY `fk_adoption_info_upload` (`upload_record_id`),
  CONSTRAINT `fk_adoption_info_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_adoption_info_upload` FOREIGN KEY (`upload_record_id`) REFERENCES `upload_records` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='领养信息表';

-- ----------------------------
-- Table structure for adoption_records
-- ----------------------------
DROP TABLE IF EXISTS `adoption_records`;
CREATE TABLE `adoption_records` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '领养用户ID',
  `adoption_info_id` bigint NOT NULL COMMENT '被领养动物信息ID',
  `pet_name` varchar(50) NOT NULL COMMENT '被领养动物名字',
  `adopter_name` varchar(50) NOT NULL COMMENT '领养人真实姓名',
  `adopter_gender` enum('女','男') NOT NULL COMMENT '领养人性别',
  `age` tinyint unsigned NOT NULL COMMENT '领养人年龄',
  `id_card` char(18) NOT NULL COMMENT '领养人身份证号码',
  `address` varchar(200) NOT NULL COMMENT '领养人住址',
  `contact_phone` char(11) NOT NULL COMMENT '领养人联系电话',
  `adoption_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '领养时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_adoption_unique` (`adoption_info_id`),
  KEY `fk_adoption_record_user` (`user_id`),
  CONSTRAINT `fk_adoption_record_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_adoption_records_info` FOREIGN KEY (`adoption_info_id`) REFERENCES `adoption_infos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='领养记录表';

