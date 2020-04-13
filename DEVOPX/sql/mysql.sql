/*
Navicat MySQL Data Transfer

Source Server         : 172.19.222.44_3306_local
Source Server Version : 50620
Source Host           : 172.19.222.44:3306
Source Database       : ips

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2020-04-13 16:43:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT ' 主键',
  `LOGIN_NAME` varchar(35) NOT NULL COMMENT ' 登录名',
  `CHINA_NAME` varchar(55) DEFAULT '' COMMENT '中文名',
  `PASSWORD` varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
  `ROLES` varchar(255) DEFAULT NULL COMMENT '角色',
  `SYS_TOKEN` varchar(255) NOT NULL COMMENT '认证令牌',
  `STATUS` varchar(5) NOT NULL COMMENT '用户状态：0-锁定，1-正常，2-删除',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL COMMENT '更新时间',
  `CREATE_USER` int(11) DEFAULT NULL COMMENT '创建人',
  `UPDATE_USER` int(11) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;





/*
Navicat MySQL Data Transfer

Source Server         : 172.19.222.44_3306_local
Source Server Version : 50620
Source Host           : 172.19.222.44:3306
Source Database       : ips

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2020-04-13 16:43:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for push_subscriber
-- ----------------------------
DROP TABLE IF EXISTS `push_subscriber`;
CREATE TABLE `push_subscriber` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `PUSH_GROUP_ID` varchar(20) NOT NULL COMMENT '群组信息ID',
  `NICK_NAME` varchar(255) NOT NULL COMMENT '昵称',
  `OPEN_ID` varchar(255) NOT NULL COMMENT 'openId',
  `CREAT_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;




/*
Navicat MySQL Data Transfer

Source Server         : 172.19.222.44_3306_local
Source Server Version : 50620
Source Host           : 172.19.222.44:3306
Source Database       : ips

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2020-04-13 16:42:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for push_group
-- ----------------------------
DROP TABLE IF EXISTS `push_group`;
CREATE TABLE `push_group` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '群组id',
  `TOPIC_NAME` varchar(255) NOT NULL COMMENT '群组名称',
  `TOPIC_CODE` varchar(255) NOT NULL COMMENT '群组编码',
  `CREATE_USER` int(11) NOT NULL COMMENT '创建人',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `Q_R_CODE` varchar(255) DEFAULT NULL COMMENT '二维码路径',
  `Q_R_CODE_UPDATE_TIME` datetime DEFAULT NULL COMMENT '二维码更新时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `TOPIC_CODE` (`TOPIC_CODE`,`CREATE_USER`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;
