/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : csy

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-04-15 16:50:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` int(11) DEFAULT NULL COMMENT '角色ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('39', '3', '21', '2019-07-15 11:22:39');
INSERT INTO `t_user_role` VALUES ('55', null, '45', '2019-09-06 08:00:08');
INSERT INTO `t_user_role` VALUES ('56', '4', '42', '2019-09-06 09:01:26');
INSERT INTO `t_user_role` VALUES ('57', '4', '21', '2019-09-06 09:01:26');
INSERT INTO `t_user_role` VALUES ('60', '23', '45', '2019-09-09 01:18:27');
INSERT INTO `t_user_role` VALUES ('61', '21', '45', '2019-09-09 01:18:32');
