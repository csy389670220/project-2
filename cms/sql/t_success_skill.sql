/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : csy

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-04-15 16:50:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_success_skill
-- ----------------------------
DROP TABLE IF EXISTS `t_success_skill`;
CREATE TABLE `t_success_skill` (
  `skill_id` int(11) NOT NULL COMMENT '秒杀活动ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`skill_id`,`user_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀记录表';

-- ----------------------------
-- Records of t_success_skill
-- ----------------------------
INSERT INTO `t_success_skill` VALUES ('1', '3', '1', '2019-09-06 02:07:50');
