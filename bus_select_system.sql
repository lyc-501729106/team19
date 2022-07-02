/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : bus_select_system

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 02/07/2022 12:28:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info`  (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `users` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_info
-- ----------------------------
INSERT INTO `admin_info` VALUES (1, 'admin', '123', '男', '15009339079');

-- ----------------------------
-- Table structure for line_and_station
-- ----------------------------
DROP TABLE IF EXISTS `line_and_station`;
CREATE TABLE `line_and_station`  (
  `line_id` int NOT NULL,
  `station_id` int NOT NULL,
  `sort` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`sort`) USING BTREE,
  INDEX `station_id`(`station_id`) USING BTREE,
  INDEX `line_and_station_ibfk_1`(`line_id`) USING BTREE,
  CONSTRAINT `line_and_station_ibfk_1` FOREIGN KEY (`line_id`) REFERENCES `line_info` (`line_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `line_and_station_ibfk_2` FOREIGN KEY (`station_id`) REFERENCES `station_info` (`station_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of line_and_station
-- ----------------------------
INSERT INTO `line_and_station` VALUES (5, 3, 1);
INSERT INTO `line_and_station` VALUES (5, 4, 2);
INSERT INTO `line_and_station` VALUES (5, 12, 3);
INSERT INTO `line_and_station` VALUES (5, 1, 4);
INSERT INTO `line_and_station` VALUES (6, 2, 5);
INSERT INTO `line_and_station` VALUES (6, 4, 6);
INSERT INTO `line_and_station` VALUES (6, 12, 7);
INSERT INTO `line_and_station` VALUES (7, 3, 8);
INSERT INTO `line_and_station` VALUES (7, 2, 9);
INSERT INTO `line_and_station` VALUES (7, 12, 10);
INSERT INTO `line_and_station` VALUES (10, 4, 13);
INSERT INTO `line_and_station` VALUES (10, 12, 14);
INSERT INTO `line_and_station` VALUES (10, 1, 15);

-- ----------------------------
-- Table structure for line_info
-- ----------------------------
DROP TABLE IF EXISTS `line_info`;
CREATE TABLE `line_info`  (
  `line_id` int NOT NULL AUTO_INCREMENT,
  `line_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cost` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `line_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `departure_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `collection_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `departure_interval` int NULL DEFAULT NULL,
  PRIMARY KEY (`line_id`) USING BTREE,
  INDEX `line_name`(`line_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of line_info
-- ----------------------------
INSERT INTO `line_info` VALUES (5, '105', '1', '注意安全', '13:23', '13:24', 10);
INSERT INTO `line_info` VALUES (6, '106', '1', '注意安全', '13:25', '13:25', 2);
INSERT INTO `line_info` VALUES (7, 'bt1', '1', '注意安全', '13:28', '13:28', 2);
INSERT INTO `line_info` VALUES (10, '111', '1', '22', '08:50', '08:53', 3);

-- ----------------------------
-- Table structure for station_info
-- ----------------------------
DROP TABLE IF EXISTS `station_info`;
CREATE TABLE `station_info`  (
  `station_id` int NOT NULL AUTO_INCREMENT,
  `station_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`station_id`) USING BTREE,
  INDEX `station_name`(`station_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of station_info
-- ----------------------------
INSERT INTO `station_info` VALUES (3, '东港');
INSERT INTO `station_info` VALUES (4, '兰州大学');
INSERT INTO `station_info` VALUES (12, '小西湖');
INSERT INTO `station_info` VALUES (1, '西关');
INSERT INTO `station_info` VALUES (2, '西站十字');

-- ----------------------------
-- Table structure for train_number_info
-- ----------------------------
DROP TABLE IF EXISTS `train_number_info`;
CREATE TABLE `train_number_info`  (
  `train_number_id` int NOT NULL AUTO_INCREMENT,
  `car_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `line_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`train_number_id`) USING BTREE,
  INDEX `line_name`(`line_name`) USING BTREE,
  CONSTRAINT `train_number_info_ibfk_1` FOREIGN KEY (`line_name`) REFERENCES `line_info` (`line_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of train_number_info
-- ----------------------------

-- ----------------------------
-- Triggers structure for table line_info
-- ----------------------------
DROP TRIGGER IF EXISTS `删除线路信息之前，删除lineandstation表的相关信息`;
delimiter ;;
CREATE TRIGGER `删除线路信息之前，删除lineandstation表的相关信息` BEFORE DELETE ON `line_info` FOR EACH ROW delete from line_and_station  where line_id = old.line_id
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
