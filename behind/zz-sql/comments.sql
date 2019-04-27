/*
Navicat MySQL Data Transfer

Source Server         : GraduationProject
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : csom

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-03-04 16:16:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemId` int(11) NOT NULL,
  `message` varchar(300) NOT NULL,
  `account` varchar(15) NOT NULL,
  `category` int(11) NOT NULL,
  `commentTime` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
