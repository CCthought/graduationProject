/*
Navicat MySQL Data Transfer

Source Server         : GraduationProject
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : csom

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-03-04 16:16:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemId` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` int(11) NOT NULL,
  `color` varchar(20) NOT NULL DEFAULT '',
  `size` int(11) NOT NULL DEFAULT '-1',
  `location` varchar(10) NOT NULL,
  `count` int(11) NOT NULL,
  `imgPath` varchar(20) NOT NULL,
  `account` varchar(15) NOT NULL,
  `category` int(11) NOT NULL,
  `description` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------
