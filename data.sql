/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 100425
 Source Host           : localhost:3306
 Source Schema         : gymsystem

 Target Server Type    : MySQL
 Target Server Version : 100425
 File Encoding         : 65001

 Date: 12/11/2022 10:31:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `account_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role` int NULL DEFAULT NULL,
  `type_account` int NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`account_id`) USING BTREE,
  UNIQUE INDEX `UK_gex1lmaqpg0ir5g1f5eftyaa1`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
INSERT INTO `account` (`account_id`, `email`, `enable`, `password`, `phone`, `role`, `type_account`, `username`) VALUES (1, 'tronghandsome1111@gmail.com', b'0', '$2a$10$PmzPEBAheF9.M75m8qTUpO9jxBttX1lPnU4zdweWS6RTKgOTUI5hO', '123123', 0, 0, 'admin3'), (2, 'tronghandsome1111@gmail.com', b'0', '$2a$10$cxAqi0fkcRUNCT3AnKmuku0.HU.CRrXEe848zVMQiNJhvKhFbf9hC', '123123', 0, 0, 'user1'), (3, 'tronghandsome1111@gmail.com', b'1', '$2a$10$.CXDcGMPDAFgB3BmP4oTCOxmlXkds.XXerVXRxRIECb2PM8rZK7KO', '123123', 1, 0, 'pt1'), (7, 'tronghandsome1111@gmail.com', b'1', '$2a$10$7TDlar980o4IOuCqmVctrurTP9pNg5jbQl.Q8bCLrctOirbeq3C/O', '123123', 1, 0, 'pt2');
COMMIT;

-- ----------------------------
-- Table structure for bill_gym
-- ----------------------------
DROP TABLE IF EXISTS `bill_gym`;
CREATE TABLE `bill_gym`  (
  `bill_gym_id` int NOT NULL AUTO_INCREMENT,
  `day_end` datetime NULL DEFAULT NULL,
  `day_start` datetime NULL DEFAULT NULL,
  `combo_id` int NOT NULL,
  `gym_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`bill_gym_id`) USING BTREE,
  INDEX `FK7ny7jglhynmnn761u9ye8nx0o`(`combo_id` ASC) USING BTREE,
  INDEX `FKeb7662q8g720wua5rmyunl3a1`(`gym_id` ASC) USING BTREE,
  INDEX `FK4pc9oi8rhl20o7bk0glrl11te`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FK4pc9oi8rhl20o7bk0glrl11te` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK7ny7jglhynmnn761u9ye8nx0o` FOREIGN KEY (`combo_id`) REFERENCES `combo` (`combo_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKeb7662q8g720wua5rmyunl3a1` FOREIGN KEY (`gym_id`) REFERENCES `gym` (`gym_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of bill_gym
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bill_pt
-- ----------------------------
DROP TABLE IF EXISTS `bill_pt`;
CREATE TABLE `bill_pt`  (
  `bill_pt_id` int NOT NULL AUTO_INCREMENT,
  `day_end` datetime NULL DEFAULT NULL,
  `day_start` datetime NULL DEFAULT NULL,
  `pt_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`bill_pt_id`) USING BTREE,
  INDEX `FKauv2ksfs86fx15e9qgr06p8yu`(`pt_id` ASC) USING BTREE,
  INDEX `FK2rvy5ed55og20n02uhw6w11qw`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FK2rvy5ed55og20n02uhw6w11qw` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKauv2ksfs86fx15e9qgr06p8yu` FOREIGN KEY (`pt_id`) REFERENCES `personal_trainer` (`pt_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of bill_pt
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for combo
-- ----------------------------
DROP TABLE IF EXISTS `combo`;
CREATE TABLE `combo`  (
  `combo_id` int NOT NULL AUTO_INCREMENT,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` int NOT NULL,
  `gym_id` int NOT NULL,
  PRIMARY KEY (`combo_id`) USING BTREE,
  INDEX `FKbi0fbhxi3v2eit37uylp961e5`(`gym_id` ASC) USING BTREE,
  CONSTRAINT `FKbi0fbhxi3v2eit37uylp961e5` FOREIGN KEY (`gym_id`) REFERENCES `gym` (`gym_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of combo
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for discount
-- ----------------------------
DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount`  (
  `discount_id` int NOT NULL AUTO_INCREMENT,
  `end` datetime NULL DEFAULT NULL,
  `start` datetime NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `percent` int NOT NULL,
  `gym_id` int NOT NULL,
  PRIMARY KEY (`discount_id`) USING BTREE,
  INDEX `FK5veekx2hmvimdol5ahf0uyo5a`(`gym_id` ASC) USING BTREE,
  CONSTRAINT `FK5veekx2hmvimdol5ahf0uyo5a` FOREIGN KEY (`gym_id`) REFERENCES `gym` (`gym_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of discount
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for gym
-- ----------------------------
DROP TABLE IF EXISTS `gym`;
CREATE TABLE `gym`  (
  `gym_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`gym_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of gym
-- ----------------------------
BEGIN;
INSERT INTO `gym` (`gym_id`, `address`, `avatar`, `email`, `enable`, `name`, `phone`) VALUES (1, 'TP.HCM', 'http://res.cloudinary.com/dzjmvy2ty/image/upload/v1668223011/ppf0ynwgbic2qlwtf7ap.jpg', 'demo@gmail.com', b'1', 'demo', '123123'), (2, 'TP.HCM', 'http://res.cloudinary.com/dzjmvy2ty/image/upload/v1668223005/bqnttqv3sjljfohjtd3y.jpg', 'demo@gmail.com', b'1', 'demo', '123123'), (3, 'TP.HCM', NULL, 'demo@gmail.com', b'1', 'demo', '123123');
COMMIT;

-- ----------------------------
-- Table structure for gym_rate
-- ----------------------------
DROP TABLE IF EXISTS `gym_rate`;
CREATE TABLE `gym_rate`  (
  `gym_rate_id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `vote` float NOT NULL,
  `gym_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`gym_rate_id`) USING BTREE,
  INDEX `FKs9l42kpokdo9m6jtmrsrux5lv`(`gym_id` ASC) USING BTREE,
  INDEX `FK8t6yggvrk1leghlw8rvc77ojh`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FK8t6yggvrk1leghlw8rvc77ojh` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKs9l42kpokdo9m6jtmrsrux5lv` FOREIGN KEY (`gym_id`) REFERENCES `gym` (`gym_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of gym_rate
-- ----------------------------
BEGIN;
INSERT INTO `gym_rate` (`gym_rate_id`, `content`, `vote`, `gym_id`, `user_id`) VALUES (1, 'tốt', 5, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for personal_trainer
-- ----------------------------
DROP TABLE IF EXISTS `personal_trainer`;
CREATE TABLE `personal_trainer`  (
  `pt_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` int NOT NULL,
  `account_id` int NULL DEFAULT NULL,
  `gym_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`pt_id`) USING BTREE,
  INDEX `FK7cba201tku98wavwuabcq3b9t`(`account_id` ASC) USING BTREE,
  INDEX `FK34j44de4ea2iwsba5iikvf5ya`(`gym_id` ASC) USING BTREE,
  CONSTRAINT `FK34j44de4ea2iwsba5iikvf5ya` FOREIGN KEY (`gym_id`) REFERENCES `gym` (`gym_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK7cba201tku98wavwuabcq3b9t` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of personal_trainer
-- ----------------------------
BEGIN;
INSERT INTO `personal_trainer` (`pt_id`, `address`, `avatar`, `name`, `price`, `account_id`, `gym_id`) VALUES (1, 'hcm', '', 'văn tèo', 10000000, 7, 1);
COMMIT;

-- ----------------------------
-- Table structure for pic_gym
-- ----------------------------
DROP TABLE IF EXISTS `pic_gym`;
CREATE TABLE `pic_gym`  (
  `pic_gym_id` int NOT NULL AUTO_INCREMENT,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gym_id` int NOT NULL,
  PRIMARY KEY (`pic_gym_id`) USING BTREE,
  INDEX `FKpvc91ik7uwa68yghun52ps9ql`(`gym_id` ASC) USING BTREE,
  CONSTRAINT `FKpvc91ik7uwa68yghun52ps9ql` FOREIGN KEY (`gym_id`) REFERENCES `gym` (`gym_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of pic_gym
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pic_pt
-- ----------------------------
DROP TABLE IF EXISTS `pic_pt`;
CREATE TABLE `pic_pt`  (
  `pic_pt_id` int NOT NULL AUTO_INCREMENT,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pt_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`pic_pt_id`) USING BTREE,
  INDEX `FK7p7csq2nxq8gcvsc3v5l4xl85`(`pt_id` ASC) USING BTREE,
  CONSTRAINT `FK7p7csq2nxq8gcvsc3v5l4xl85` FOREIGN KEY (`pt_id`) REFERENCES `personal_trainer` (`pt_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of pic_pt
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pt_rate
-- ----------------------------
DROP TABLE IF EXISTS `pt_rate`;
CREATE TABLE `pt_rate`  (
  `pt_rate_id` int NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `vote` float NOT NULL,
  `pt_id` int NULL DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`pt_rate_id`) USING BTREE,
  INDEX `FKcod1b8vqfydn0y5dw8thabxqt`(`pt_id` ASC) USING BTREE,
  INDEX `FKpp3gg5wvt6bxibvojlwcjj4ma`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FKcod1b8vqfydn0y5dw8thabxqt` FOREIGN KEY (`pt_id`) REFERENCES `personal_trainer` (`pt_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKpp3gg5wvt6bxibvojlwcjj4ma` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of pt_rate
-- ----------------------------
BEGIN;
INSERT INTO `pt_rate` (`pt_rate_id`, `comment`, `vote`, `pt_id`, `user_id`) VALUES (1, 'tốt', 5, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `token_id` int NOT NULL AUTO_INCREMENT,
  `confirm_at` datetime NULL DEFAULT NULL,
  `create_at` datetime NOT NULL,
  `expiry_at` datetime NOT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `token_type` int NULL DEFAULT NULL,
  `account_id` int NOT NULL,
  PRIMARY KEY (`token_id`) USING BTREE,
  INDEX `FKftkstvcfb74ogw02bo5261kno`(`account_id` ASC) USING BTREE,
  CONSTRAINT `FKftkstvcfb74ogw02bo5261kno` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `account_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `FKc3b4xfbq6rbkkrddsdum8t5f0`(`account_id` ASC) USING BTREE,
  CONSTRAINT `FKc3b4xfbq6rbkkrddsdum8t5f0` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`user_id`, `address`, `avatar`, `name`, `account_id`) VALUES (1, 'hcm', NULL, 'admin', 1), (2, 'hcm', NULL, 'admin', 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
