/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : csy

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-04-15 16:50:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_seckill
-- ----------------------------
DROP TABLE IF EXISTS `t_seckill`;
CREATE TABLE `t_seckill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL COMMENT '商品ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `price` decimal(10,2) NOT NULL COMMENT '秒杀价格',
  PRIMARY KEY (`id`),
  UNIQUE KEY `item_id` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_seckill
-- ----------------------------
INSERT INTO `t_seckill` VALUES ('1', '1', '2019-07-22 15:38:33', '2019-07-26 14:28:00', '2019-11-01 17:00:00', '999.00');
INSERT INTO `t_seckill` VALUES ('2', '2', '2019-07-23 11:27:09', '2019-07-25 11:27:11', '2019-08-01 14:27:24', '888.00');
INSERT INTO `t_seckill` VALUES ('3', '3', '2019-07-23 16:21:16', '2019-07-25 10:19:19', '2019-07-25 19:21:21', '333.00');
INSERT INTO `t_seckill` VALUES ('4', '4', '2019-07-23 16:21:59', '2019-07-25 09:51:04', '2019-08-01 16:22:07', '333.00');
INSERT INTO `t_seckill` VALUES ('5', '5', '2019-07-23 16:23:03', '2019-07-25 11:59:06', '2019-08-02 16:23:09', '333.00');
INSERT INTO `t_seckill` VALUES ('6', '6', '2019-07-23 16:23:53', '2019-07-23 16:23:55', '2019-07-23 16:23:58', '1111.00');
