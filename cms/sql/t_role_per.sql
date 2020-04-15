/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : csy

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-04-15 16:50:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_role_per
-- ----------------------------
DROP TABLE IF EXISTS `t_role_per`;
CREATE TABLE `t_role_per` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ROLE_ID` int(11) NOT NULL COMMENT '角色ID',
  `PER_ID` int(11) NOT NULL COMMENT '权限ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_per
-- ----------------------------
INSERT INTO `t_role_per` VALUES ('111', '21', '21', '2019-07-16 11:27:25');
INSERT INTO `t_role_per` VALUES ('112', '21', '22', '2019-07-16 11:27:25');
INSERT INTO `t_role_per` VALUES ('113', '21', '23', '2019-07-16 11:27:25');
INSERT INTO `t_role_per` VALUES ('114', '21', '25', '2019-07-16 11:27:25');
INSERT INTO `t_role_per` VALUES ('115', '21', '24', '2019-07-16 11:27:25');
INSERT INTO `t_role_per` VALUES ('116', '21', '26', '2019-07-16 11:27:25');
INSERT INTO `t_role_per` VALUES ('117', '21', '27', '2019-07-16 11:27:25');
INSERT INTO `t_role_per` VALUES ('118', '21', '28', '2019-07-16 11:27:25');
INSERT INTO `t_role_per` VALUES ('119', '21', '29', '2019-07-16 11:27:25');
INSERT INTO `t_role_per` VALUES ('120', '21', '30', '2019-07-16 11:27:25');
INSERT INTO `t_role_per` VALUES ('121', '21', '31', '2019-07-16 11:27:25');
INSERT INTO `t_role_per` VALUES ('122', '21', '32', '2019-07-16 11:27:25');
INSERT INTO `t_role_per` VALUES ('131', '42', '22', '2019-07-18 14:17:17');
INSERT INTO `t_role_per` VALUES ('132', '42', '27', '2019-07-18 14:17:17');
INSERT INTO `t_role_per` VALUES ('133', '42', '31', '2019-07-18 14:17:17');
INSERT INTO `t_role_per` VALUES ('134', '42', '32', '2019-07-18 14:17:17');
INSERT INTO `t_role_per` VALUES ('135', '44', '22', '2019-08-20 17:58:27');
INSERT INTO `t_role_per` VALUES ('136', '44', '23', '2019-08-20 17:58:27');
INSERT INTO `t_role_per` VALUES ('148', '45', '22', '2019-09-06 09:01:42');
INSERT INTO `t_role_per` VALUES ('149', '45', '23', '2019-09-06 09:01:42');
