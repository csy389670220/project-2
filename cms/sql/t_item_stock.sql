/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : csy

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-04-15 16:49:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_item_stock
-- ----------------------------
DROP TABLE IF EXISTS `t_item_stock`;
CREATE TABLE `t_item_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_id` int(11) NOT NULL COMMENT '商品ID',
  `stock` int(21) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `1` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_item_stock
-- ----------------------------
INSERT INTO `t_item_stock` VALUES ('1', '1', '658');
INSERT INTO `t_item_stock` VALUES ('2', '2', '297');
INSERT INTO `t_item_stock` VALUES ('3', '3', '107');
INSERT INTO `t_item_stock` VALUES ('4', '4', '109');
INSERT INTO `t_item_stock` VALUES ('5', '5', '110');
INSERT INTO `t_item_stock` VALUES ('6', '6', '111');
