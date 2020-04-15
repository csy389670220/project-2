/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : csy

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-04-15 16:49:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_item
-- ----------------------------
DROP TABLE IF EXISTS `t_item`;
CREATE TABLE `t_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `price` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `img_url` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品图片链接',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人',
  `update_user` int(11) DEFAULT NULL COMMENT '更新人',
  `item_status` int(2) DEFAULT NULL COMMENT '状态：上架，下架，无货。。。',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_item
-- ----------------------------
INSERT INTO `t_item` VALUES ('1', '苹果6s', '5888.15', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3091057954,3025709388&fm=26&gp=0.jpg', '2019-07-19 17:57:12', '2019-07-19 17:57:15', null, null, '1');
INSERT INTO `t_item` VALUES ('2', '华为P30', '3998.00', 'https://res8.vmallres.com/shopdc/pic/d356693d-ec72-4f04-8ca6-3d0a65e9678f.jpg', '2019-07-22 15:15:21', '2019-07-22 15:15:17', null, null, '1');
INSERT INTO `t_item` VALUES ('3', '小米2', '1799.00', 'https://i1.mifile.cn/a1/pms_1550642182.7527088!220x220.jpg', '2019-07-23 16:21:02', '2019-07-23 16:21:05', null, null, '1');
INSERT INTO `t_item` VALUES ('4', 'oppp2s', '2999.00', 'https://dsfs.oppo.com/archives/201905/201905291105315cedf773cdf73.png', '2019-07-23 16:21:47', '2019-07-23 16:21:50', null, null, '1');
INSERT INTO `t_item` VALUES ('5', '锤子2', '999.00', 'https://resource.smartisan.com/resource/84366aa98fd0659d7809e1b9eed62cb4.png', '2019-07-23 16:22:51', '2019-07-23 16:22:54', null, null, '1');
INSERT INTO `t_item` VALUES ('6', '荣耀20', '1233.00', 'https://img1.360buyimg.com/n6/jfs/t1/60028/40/2851/231278/5d11cbf4Eb209e646/e89a1a427b814e45.jpg', null, '2019-07-23 16:23:42', null, null, '1');
