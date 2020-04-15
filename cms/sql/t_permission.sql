/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : csy

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-04-15 16:49:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '父类ID',
  `PER_CODE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限code值',
  `PER_DESC` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `PER_LEV` int(2) NOT NULL COMMENT '资源等级',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('21', '0', 'sys_user', '用户管理', '2019-07-11 09:20:11', '1');
INSERT INTO `t_permission` VALUES ('22', '21', 'sys_user_query', '查询', '2019-07-11 09:21:30', '2');
INSERT INTO `t_permission` VALUES ('23', '21', 'sys_user_add', '新增', '2019-07-11 09:21:58', '2');
INSERT INTO `t_permission` VALUES ('24', '21', 'sys_user_del', '删除', '2019-07-11 09:25:40', '2');
INSERT INTO `t_permission` VALUES ('25', '21', 'sys_user_update', '更新', '2019-07-11 09:23:16', '2');
INSERT INTO `t_permission` VALUES ('26', '0', 'role_per', '角色管理', '2019-07-12 15:01:59', '1');
INSERT INTO `t_permission` VALUES ('27', '26', 'role_per_query', '查询', '2019-07-12 15:01:59', '2');
INSERT INTO `t_permission` VALUES ('28', '26', 'role_per_add', '新增', '2019-07-12 15:01:59', '2');
INSERT INTO `t_permission` VALUES ('29', '26', 'role_per_update', '更新', '2019-07-12 15:01:59', '2');
INSERT INTO `t_permission` VALUES ('30', '26', 'role_per_del', '删除', '2019-07-12 15:01:59', '2');
INSERT INTO `t_permission` VALUES ('31', '0', 'skill', '秒杀管理', '2019-07-16 11:26:38', '1');
INSERT INTO `t_permission` VALUES ('32', '31', 'skill_query', '查询', '2019-07-16 11:27:00', '2');
INSERT INTO `t_permission` VALUES ('33', '0', 'per_test', '测试', '2019-09-03 16:21:03', '1');
