-- ----------------------------
-- Table structure for forum_post
-- ----------------------------
DROP TABLE IF EXISTS `forum_post`;
CREATE TABLE `forum_post` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` VARCHAR(255) NOT NULL COMMENT '帖子标题',
  `content` TEXT COMMENT '帖子内容',
  `author_name` VARCHAR(50) COMMENT '作者昵称',
  `user_id` BIGINT NOT NULL COMMENT '发帖用户ID（为未来用户系统预留）',
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='帖子表';

-- ----------------------------
-- Table structure for forum_comment
-- ----------------------------
DROP TABLE IF EXISTS `forum_comment`;
CREATE TABLE `forum_comment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `post_id` BIGINT NOT NULL COMMENT '关联的帖子ID',
  `author_name` VARCHAR(50) COMMENT '评论者昵称',
  `user_id` BIGINT NOT NULL COMMENT '评论用户ID（为未来用户系统预留）',
  `content` TEXT COMMENT '评论内容',
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表'; 


-- 下面是测试数据

-- 插入两条帖子
INSERT INTO `forum_post` (`title`, `content`, `author_name`, `user_id`) VALUES
('如何领养一只可爱的小猫？', '大家好，我是一个新手，想问问领养小猫的具体流程和注意事项。', '爱猫新手', 1),
('流浪狗狗的冬季保暖小技巧', '天气变冷了，分享一些帮助流浪狗狗过冬的方法。', '汪汪守护者', 2);

-- 为第一篇帖子插入两条评论
INSERT INTO `forum_comment` (`post_id`, `content`, `author_name`, `user_id`) VALUES
(1, '首先要确保自己有稳定的住所和收入哦，这是对小生命负责的基础。', '资深铲屎官', 1),
(1, '可以去本地的动物救助站看看，他们会提供很专业的指导！', '救助站义工', 1);

-- 为第二篇帖子插入一条评论
INSERT INTO `forum_comment` (`post_id`, `content`, `author_name`, `user_id`) VALUES
(2, '可以用旧衣服和纸箱给它们搭建一个简易的避风港。', '热心市民张女士', 1);