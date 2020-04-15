/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : csy

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-04-15 16:49:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_order_record
-- ----------------------------
DROP TABLE IF EXISTS `t_order_record`;
CREATE TABLE `t_order_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `item_id` int(11) NOT NULL COMMENT '商品ID',
  `total_price` decimal(10,2) NOT NULL COMMENT '总价',
  `numbers` int(9) NOT NULL COMMENT '商品数量',
  `states` varchar(2) NOT NULL COMMENT '状态：0-待付款，1-已支付，2-取消订单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_order_record
-- ----------------------------
