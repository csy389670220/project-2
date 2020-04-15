/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : csy

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-04-15 16:50:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `LOGIN_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `PASSWORD` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `EMAIL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电子邮箱',
  `PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号码',
  `USER_STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户状态：1-有效，0-失效，2-其他',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` int(11) DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `UPDATE_USER` int(11) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('3', '管理员', 'admin', 'b9f912ca2a9aeffde7a0468b7cb660a1', '13188982903@163.com', '13188982903', '1', '2019-07-10 00:00:00', null, '2019-07-15 11:22:39', '4');
INSERT INTO `t_sys_user` VALUES ('4', '程思雨', 'csy', 'b9f912ca2a9aeffde7a0468b7cb660a1', '18922303342@163.com', '18922303342', '1', '2019-07-02 00:00:00', null, '2019-09-06 09:01:26', '3');
INSERT INTO `t_sys_user` VALUES ('5', '张三', 'zhangsan', 'b9f912ca2a9aeffde7a0468b7cb660a1', '18922303342@163.com', '18922303342', '0', '2019-07-03 00:00:00', null, '2019-07-15 11:10:30', '3');
INSERT INTO `t_sys_user` VALUES ('6', '张四', 'zhangsi', 'b9f912ca2a9aeffde7a0468b7cb660a1', '18922303342@163.com', '18922303342', '1', '2019-07-04 00:00:00', null, '2019-07-09 00:00:00', null);
INSERT INTO `t_sys_user` VALUES ('7', '李二', 'lier', 'b9f912ca2a9aeffde7a0468b7cb660a1', '18922303342@163.com', '18922303342', '1', '2019-07-05 00:00:00', null, '2019-07-09 11:22:22', null);
INSERT INTO `t_sys_user` VALUES ('8', '王小', 'wangxiao', 'b9f912ca2a9aeffde7a0468b7cb660a1', '18922303342@163.com', '18922303342', '1', '2019-07-06 00:00:00', null, '2019-07-09 00:00:00', null);
INSERT INTO `t_sys_user` VALUES ('9', '李多', 'duoduo', 'b9f912ca2a9aeffde7a0468b7cb660a1', '18922303342@163.com', '18922303342', '1', '2019-07-09 00:00:00', null, '2019-07-09 00:00:00', null);
INSERT INTO `t_sys_user` VALUES ('11', '杨凯', 'yangl1', 'b9f912ca2a9aeffde7a0468b7cb660a1', '18922303342@163.com', '18922303342', '1', '2019-07-10 00:00:00', null, '2019-07-10 00:00:00', null);
INSERT INTO `t_sys_user` VALUES ('12', '李四光', 'lisidd', 'b9f912ca2a9aeffde7a0468b7cb660a1', '18922303342@163.com', '18922303342', '1', '2019-07-10 00:00:00', null, '2019-07-10 00:00:00', null);
INSERT INTO `t_sys_user` VALUES ('13', '成饿', 'ceer', 'b9f912ca2a9aeffde7a0468b7cb660a1', '18922303342@163.com', '18922303342', '0', '2019-07-10 11:14:10', null, '2019-07-15 09:29:36', '3');
INSERT INTO `t_sys_user` VALUES ('14', '平底', 'ping', 'b9f912ca2a9aeffde7a0468b7cb660a1', '18922303342@163.com', '18922303342', '1', '2019-07-10 11:21:51', null, '2019-07-10 11:21:51', null);
INSERT INTO `t_sys_user` VALUES ('15', '程思多', 'duo111', 'b9f912ca2a9aeffde7a0468b7cb660a1', '18922303342@163.com', '18922303342', '1', '2019-07-10 11:23:05', null, '2019-07-10 11:23:05', null);
INSERT INTO `t_sys_user` VALUES ('17', '李筽', 'liooo', 'b9f912ca2a9aeffde7a0468b7cb660a1', '18922303342@163.com', '18922303342', '1', '2019-07-10 14:28:23', null, '2019-07-10 14:28:23', null);
INSERT INTO `t_sys_user` VALUES ('18', '王大大', 'Daad112', 'b9f912ca2a9aeffde7a0468b7cb660a1', '18922303342@163.com', '18922303342', '1', '2019-07-10 14:29:09', null, '2019-07-10 14:29:09', null);
INSERT INTO `t_sys_user` VALUES ('19', '赵练', 'mizhao1', 'b9f912ca2a9aeffde7a0468b7cb660a1', '18977663902@163.com', '18977663902', '1', '2019-07-10 16:02:39', null, '2019-07-10 16:02:39', null);
INSERT INTO `t_sys_user` VALUES ('20', '何敏', 'mmhe', 'b9f912ca2a9aeffde7a0468b7cb660a1', '13788983322@qq.com', '13788983322', '1', '2019-07-10 16:11:50', null, '2019-07-12 18:20:12', null);
INSERT INTO `t_sys_user` VALUES ('21', '勤圆', 'qingyuan', 'b9f912ca2a9aeffde7a0468b7cb660a1', '17893042234@163.com', '13888888888', '1', '2019-07-12 17:12:23', '3', '2019-09-09 01:18:32', '3');
INSERT INTO `t_sys_user` VALUES ('23', '橙多多', 'dduo1233', 'b9f912ca2a9aeffde7a0468b7cb660a1', '13488990032@163.com', '13488990032', '0', '2019-09-06 08:00:08', '3', '2019-09-09 01:18:27', '3');
