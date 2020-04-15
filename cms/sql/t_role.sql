/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : csy

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-04-15 16:49:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ROLE_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `ROLE_DESC` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `ROLE_STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户状态：1-有效，0-失效，2-其他',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` int(11) DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `UPDATE_USER` int(11) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('21', '管理员', '系统最高权限角色', '1', '2019-07-11 09:23:55', '3', '2019-07-16 11:27:25', '3');
INSERT INTO `t_role` VALUES ('42', '操作员', '进系统级别行业务查询', '1', '2019-07-15 13:59:48', '3', '2019-07-18 14:17:17', '3');
INSERT INTO `t_role` VALUES ('44', '测试角色11', '用于角测试11', '1', '2019-08-20 17:58:27', '3', '2019-08-20 17:58:27', '3');
INSERT INTO `t_role` VALUES ('45', '测试角色11', '用于角测试11', '1', '2019-08-21 15:53:48', '3', '2019-09-06 09:01:42', '4');
INSERT INTO `t_role` VALUES ('50', 'Q1', '1', '0', '2019-09-06 09:10:16', '4', '2019-09-06 09:10:26', '4');
INSERT INTO `t_role` VALUES ('52', 'Q12', '1', '0', '2019-09-06 09:14:35', '3', '2019-09-06 09:14:48', '3');
