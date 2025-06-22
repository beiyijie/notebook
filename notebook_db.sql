/*
 Navicat Premium Dump SQL

 Source Server         : 1111
 Source Server Type    : MySQL
 Source Server Version : 50730 (5.7.30-log)
 Source Host           : localhost:3306
 Source Schema         : notebook_db

 Target Server Type    : MySQL
 Target Server Version : 50730 (5.7.30-log)
 File Encoding         : 65001

 Date: 22/06/2025 21:12:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类名称',
  `user_id` int(11) NOT NULL COMMENT '所属用户ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_category_user`(`user_id`) USING BTREE,
  CONSTRAINT `fk_category_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES (2, '生活', 1, '2025-05-24 23:13:11');
INSERT INTO `t_category` VALUES (3, '学习', 1, '2025-05-24 23:13:11');
INSERT INTO `t_category` VALUES (4, '工作', 2, '2025-05-24 23:13:11');
INSERT INTO `t_category` VALUES (5, '购物', 2, '2025-05-24 23:13:11');
INSERT INTO `t_category` VALUES (7, '打游戏', 1, '2025-06-06 17:00:18');
INSERT INTO `t_category` VALUES (8, '吃喝', 1, '2025-06-07 10:32:51');
INSERT INTO `t_category` VALUES (9, '休闲', 4, '2025-06-07 10:34:32');

-- ----------------------------
-- Table structure for t_event
-- ----------------------------
DROP TABLE IF EXISTS `t_event`;
CREATE TABLE `t_event`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '事件标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '事件内容',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '分类ID',
  `user_id` int(11) NOT NULL COMMENT '所属用户ID',
  `priority` int(11) NULL DEFAULT 0 COMMENT '优先级：0-低，1-中，2-高',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态：0-未完成，1-已完成',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_event_category`(`category_id`) USING BTREE,
  INDEX `fk_event_user`(`user_id`) USING BTREE,
  CONSTRAINT `fk_event_category` FOREIGN KEY (`category_id`) REFERENCES `t_category` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `fk_event_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '事件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_event
-- ----------------------------
INSERT INTO `t_event` VALUES (2, '购买生日礼物', '为妈妈购买生日礼物', 2, 1, 1, 0, '2025-05-24 23:13:11', '2025-05-26 17:17:57');
INSERT INTO `t_event` VALUES (4, '参加会议', '参加项目启动会议', 4, 2, 2, 0, '2025-05-24 23:13:11', '2025-05-24 23:13:11');
INSERT INTO `t_event` VALUES (5, '购买电脑', '选购一台适合开发的笔记本电脑', 5, 2, 0, 0, '2025-05-24 23:13:11', '2025-05-24 23:13:11');
INSERT INTO `t_event` VALUES (6, '吃饭', '123eqweqweqwe', NULL, 3, 2, 1, '2025-05-25 00:04:31', '2025-05-25 00:05:44');
INSERT INTO `t_event` VALUES (13, '睡觉', '晚上睡觉', NULL, 1, 0, 0, '2025-06-07 10:33:14', '2025-06-18 11:05:07');
INSERT INTO `t_event` VALUES (14, '玩游戏', '123', 9, 4, 2, 0, '2025-06-07 10:34:53', '2025-06-07 10:34:56');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '123456', 'admin@example.com', '2025-05-24 23:13:11');
INSERT INTO `t_user` VALUES (2, 'test', '123456', 'test@example.com', '2025-05-24 23:13:11');
INSERT INTO `t_user` VALUES (3, '1111', '123456', '1205774368@qq.com', '2025-05-25 00:04:02');
INSERT INTO `t_user` VALUES (4, 'user', '123456', 'user@123.com', '2025-06-07 10:34:05');

SET FOREIGN_KEY_CHECKS = 1;
