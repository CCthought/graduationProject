/*
Navicat MySQL Data Transfer

Source Server         : GraduationProject
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : csom

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-03-04 16:16:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(15) NOT NULL,
  `realname` varchar(10) NOT NULL,
  `password` varchar(15) NOT NULL,
  `sex` char(1) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `registerTime` bigint(20) NOT NULL,
  `balance` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
