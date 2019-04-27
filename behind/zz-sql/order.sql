/*
Navicat MySQL Data Transfer

Source Server         : GraduationProject
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : csom

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-03-04 16:16:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemId` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `color` varchar(30) NOT NULL DEFAULT '',
  `size` int(11) NOT NULL DEFAULT '-1',
  `location` varchar(10) NOT NULL,
  `imgPath` varchar(20) NOT NULL,
  `account` varchar(15) NOT NULL,
  `category` int(11) NOT NULL,
  `address` varchar(100) NOT NULL,
  `receiver` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `orderTime` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
