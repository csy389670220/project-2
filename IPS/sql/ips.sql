/*
Navicat MySQL Data Transfer

Source Server         : 172.19.222.44_3306_local
Source Server Version : 50620
Source Host           : 172.19.222.44:3306
Source Database       : ips

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2019-12-30 11:16:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for serverplan_ctaf
-- ----------------------------
DROP TABLE IF EXISTS `serverplan_ctaf`;
CREATE TABLE `serverplan_ctaf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT ' 主键',
  `SERVER_USE` varchar(255) DEFAULT NULL COMMENT '服务器用途',
  `DEV` varchar(255) DEFAULT NULL COMMENT 'DEV',
  `ST` varchar(255) DEFAULT NULL COMMENT 'ST',
  `NET_N` varchar(255) DEFAULT NULL COMMENT '内网',
  `NET_W` varchar(255) DEFAULT NULL COMMENT '外网',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for serverplan_cut
-- ----------------------------
DROP TABLE IF EXISTS `serverplan_cut`;
CREATE TABLE `serverplan_cut` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `SERVER_USE` varchar(255) DEFAULT NULL COMMENT '服务器用途',
  `DEV` varchar(255) DEFAULT NULL COMMENT 'DEV',
  `ST_A` varchar(255) DEFAULT NULL COMMENT 'ST-A(闲置）',
  `ST_B` varchar(255) DEFAULT NULL COMMENT 'ST-B',
  `ST_C` varchar(255) DEFAULT NULL COMMENT 'ST-C',
  `UAT_A` varchar(255) DEFAULT NULL COMMENT 'UAT-A(闲置)',
  `UAT_B` varchar(255) DEFAULT NULL COMMENT 'UAT-B',
  `UAT_C_TO_A` varchar(255) DEFAULT NULL COMMENT '原UAT-C更改为UAT-A',
  `MEM1` varchar(255) DEFAULT NULL COMMENT 'MEM1',
  `MEM2` varchar(255) DEFAULT NULL COMMENT 'MEM2',
  `TRAIN` varchar(255) DEFAULT NULL COMMENT '培训',
  `SIMULATION_DATA_MIGRATION_A` varchar(255) DEFAULT NULL COMMENT '模拟数据迁移验证环境-A',
  `SIMULATION_DATA_MIGRATION_B` varchar(255) DEFAULT NULL COMMENT '模拟数据迁移验证环境-B',
  `SIMULATION_RELIABILITY` varchar(255) DEFAULT NULL COMMENT '模拟可靠性验证环境',
  `SIMULATION_A` varchar(255) DEFAULT NULL COMMENT '模拟-A性能测试',
  `SIMULATION_B` varchar(255) DEFAULT NULL COMMENT '模拟-B(废弃)性能测试',
  `PRODUCE` varchar(255) DEFAULT NULL COMMENT '生产',
  `DISASTER_RECOVERY_BEIJINHG` varchar(255) DEFAULT NULL COMMENT '北京灾备',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for serverplan_gitmaintain
-- ----------------------------
DROP TABLE IF EXISTS `serverplan_gitmaintain`;
CREATE TABLE `serverplan_gitmaintain` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DOMAIN` varchar(255) DEFAULT NULL COMMENT '网域',
  `CATEGORY` varchar(255) DEFAULT NULL COMMENT '项目分类',
  `MODULE_NAME` varchar(255) DEFAULT NULL COMMENT '目录/模块名',
  `CONTENT` varchar(255) DEFAULT NULL COMMENT '内容',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '地址',
  `MEMO` varchar(255) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for serverplan_idealnew
-- ----------------------------
DROP TABLE IF EXISTS `serverplan_idealnew`;
CREATE TABLE `serverplan_idealnew` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统ID',
  `SERVER_APPLICATION` varchar(600) NOT NULL COMMENT '服务器用途',
  `DEV` varchar(600) DEFAULT NULL COMMENT '本币DEV环境',
  `ST1` varchar(600) DEFAULT NULL,
  `ST2` varchar(600) DEFAULT NULL,
  `ST3` varchar(600) DEFAULT NULL,
  `ST4` varchar(600) DEFAULT NULL,
  `UAT1` varchar(600) DEFAULT NULL,
  `UAT2` varchar(600) DEFAULT NULL,
  `UAT3` varchar(600) DEFAULT NULL,
  `UAT4` varchar(600) DEFAULT NULL,
  `UAT5` varchar(600) DEFAULT NULL,
  `UAT6` varchar(600) DEFAULT NULL,
  `TRAIN` varchar(600) DEFAULT NULL COMMENT '培训环境',
  `MEM1` varchar(600) DEFAULT '',
  `SIMULATION_DATA_A` varchar(600) DEFAULT '' COMMENT '模拟数据迁移验证环境-A',
  `NATIONAL_SIMULATION_EXERCISE_N` varchar(600) DEFAULT NULL COMMENT '全国模拟演练(new)',
  `SIMULATION_EXERCISE` varchar(600) DEFAULT '' COMMENT '模拟演练环境',
  `SIMULATION_A` varchar(600) DEFAULT '' COMMENT '模拟-A',
  `PRODUCE` varchar(600) DEFAULT NULL COMMENT '生产环境',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for serverplan_itrader
-- ----------------------------
DROP TABLE IF EXISTS `serverplan_itrader`;
CREATE TABLE `serverplan_itrader` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ENV` varchar(125) DEFAULT NULL COMMENT '环境',
  `SERVER_NAME` varchar(255) DEFAULT NULL COMMENT '服务名',
  `MACHINE_NAME` varchar(255) DEFAULT NULL COMMENT '机器名',
  `MACHINE_IP_W` varchar(255) DEFAULT NULL COMMENT '机器IP(外网)',
  `VIRTUAL_IP` varchar(255) DEFAULT NULL COMMENT '虚IP',
  `MAPPING_IP` varchar(255) DEFAULT NULL COMMENT '内外网映射IP',
  `PUBLIC_NET_IP` varchar(255) DEFAULT NULL COMMENT '公网IP',
  `DEP_IP` varchar(255) DEFAULT NULL COMMENT 'DEP组播子网IP',
  `DSP` varchar(255) DEFAULT NULL COMMENT 'DSP组播',
  `MEMO` varchar(255) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL COMMENT '更新时间',
  `CREATE_USER` int(11) DEFAULT NULL COMMENT '创建人',
  `UPDATE_USER` int(11) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for serverplan_loghistory
-- ----------------------------
DROP TABLE IF EXISTS `serverplan_loghistory`;
CREATE TABLE `serverplan_loghistory` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT ' 主键',
  `VERSION` varchar(30) DEFAULT NULL COMMENT '版本',
  `TIME` varchar(30) DEFAULT NULL COMMENT '日期',
  `UPDATER` varchar(30) DEFAULT NULL COMMENT '修改人',
  `CONTENT` varchar(255) DEFAULT NULL COMMENT '内容',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for serverplan_npmmaintain1
-- ----------------------------
DROP TABLE IF EXISTS `serverplan_npmmaintain1`;
CREATE TABLE `serverplan_npmmaintain1` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `STOREHOUSE_USE` varchar(255) DEFAULT NULL COMMENT 'NPM仓库用途',
  `DEVELOP` varchar(255) DEFAULT NULL COMMENT '开发',
  `CONSTRUCT` varchar(255) DEFAULT NULL COMMENT '构建',
  `RELEASE_W` varchar(255) DEFAULT NULL COMMENT '外汇release',
  `RELEASE_B` varchar(255) DEFAULT NULL COMMENT '本币release',
  `MAPPINF_ADDRESS` varchar(255) DEFAULT NULL COMMENT '映射地址',
  `PROXY` varchar(255) DEFAULT NULL COMMENT 'proxy',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for serverplan_npmmaintain2
-- ----------------------------
DROP TABLE IF EXISTS `serverplan_npmmaintain2`;
CREATE TABLE `serverplan_npmmaintain2` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT ' 主键',
  `STOREHOUSE_USE` varchar(255) DEFAULT NULL COMMENT 'NPM仓库用途',
  `RELEASE_TIMELY` varchar(255) DEFAULT NULL COMMENT '即时通讯release',
  `MAPPINF_ADDRESS` varchar(255) DEFAULT NULL COMMENT '映射地址',
  `PROXY_TIMELY` varchar(255) DEFAULT NULL COMMENT '即时通讯proxy',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for serverplan_publicuse
-- ----------------------------
DROP TABLE IF EXISTS `serverplan_publicuse`;
CREATE TABLE `serverplan_publicuse` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `SERVER` varchar(55) DEFAULT NULL COMMENT '服务器',
  `APPLICATION` varchar(155) DEFAULT NULL COMMENT '用途',
  `MEMO` varchar(355) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for serverplan_svnmaintain
-- ----------------------------
DROP TABLE IF EXISTS `serverplan_svnmaintain`;
CREATE TABLE `serverplan_svnmaintain` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DOMAIN` varchar(255) DEFAULT NULL COMMENT '网域',
  `CATEGORY` varchar(255) DEFAULT NULL COMMENT '项目分类',
  `MODULE_NAME` varchar(255) DEFAULT NULL COMMENT '目录/模块名',
  `CONTENT` varchar(255) DEFAULT NULL COMMENT '内容',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '地址',
  `MEMO` varchar(255) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for serverplan_wechat
-- ----------------------------
DROP TABLE IF EXISTS `serverplan_wechat`;
CREATE TABLE `serverplan_wechat` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `SERVER_USE` varchar(255) DEFAULT NULL COMMENT '服务器用途',
  `ST` varchar(255) DEFAULT NULL COMMENT 'ST',
  `UAT` varchar(255) DEFAULT NULL COMMENT 'UAT',
  `SIMULATIONd` varchar(255) DEFAULT NULL COMMENT '模拟环境',
  `PRODUCE` varchar(255) DEFAULT NULL COMMENT '生产',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for servicedep_uatup
-- ----------------------------
DROP TABLE IF EXISTS `servicedep_uatup`;
CREATE TABLE `servicedep_uatup` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `FRONT` varchar(255) DEFAULT NULL COMMENT '战线',
  `VERSION` varchar(255) DEFAULT NULL COMMENT '版本号',
  `UPROUND` varchar(255) DEFAULT NULL COMMENT '升级轮次',
  `ENV` varchar(255) DEFAULT NULL COMMENT '环境',
  `TEST_PHASE` varchar(255) DEFAULT NULL COMMENT '测试',
  `RELEASE_TIME` datetime DEFAULT NULL COMMENT '发版确认时间',
  `SUBMIT_APPLICATION_TIME` datetime DEFAULT NULL COMMENT '提单结束时间',
  `PASS_APPLICATION_TIME` datetime DEFAULT NULL COMMENT '审批结束时间',
  `DEPLOY_START_TIME` datetime DEFAULT NULL COMMENT '开始部署时间',
  `DEPLOY_END_TIME` datetime DEFAULT NULL COMMENT '部署结束时间',
  `SUBMIT_APPLICATION_ELAPSED_TIME` varchar(255) DEFAULT NULL COMMENT '集成组提单耗时\n(参考值10min)',
  `APPROVAL_ELAPSED_TIME` varchar(255) DEFAULT NULL COMMENT '测试组审批耗时(参考值15min)',
  `REPLY_ELAPSED_TIME` varchar(255) DEFAULT NULL COMMENT '配置组响应耗时(参考值30min)',
  `DEPLOY_ELAPSED_TIME` varchar(255) DEFAULT NULL COMMENT '部署耗时(参考值60min)',
  `ALL_ELAPSED_TIME` varchar(255) DEFAULT NULL COMMENT '总耗时(参考值115min)',
  `MEMO` varchar(555) DEFAULT NULL COMMENT '备注',
  `STATUS` varchar(10) DEFAULT NULL COMMENT '状态:1-待提交，2-已提交',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for systemenv_envlist
-- ----------------------------
DROP TABLE IF EXISTS `systemenv_envlist`;
CREATE TABLE `systemenv_envlist` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `SYSTEM` varchar(255) DEFAULT NULL COMMENT '系统',
  `CLASSIFICATION` varchar(255) DEFAULT NULL COMMENT '类别',
  `ENV` varchar(255) DEFAULT NULL COMMENT '环境',
  `BELONGING_FRONT` varchar(255) DEFAULT NULL COMMENT '所属战线',
  `FOREIGN_EXCHANGE` varchar(255) DEFAULT NULL COMMENT '外呼',
  `RMB_NEW` varchar(255) DEFAULT NULL COMMENT '新本币',
  `RMB_OLD` varchar(255) DEFAULT NULL COMMENT '老本币',
  `DEP` varchar(255) DEFAULT NULL,
  `DSP` varchar(255) DEFAULT NULL,
  `I_HUB` varchar(255) DEFAULT NULL,
  `MEMO` varchar(255) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `CREATE_USER` int(11) DEFAULT NULL,
  `UPDATE_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT ' 主键',
  `LOGIN_NAME` varchar(35) NOT NULL COMMENT ' 登录名',
  `PASSWORD` varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
  `STATUS` varchar(5) NOT NULL COMMENT '用户状态：0-锁定，1-正常，2-删除',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL COMMENT '更新时间',
  `CREATE_USER` int(11) DEFAULT NULL COMMENT '创建人',
  `UPDATE_USER` int(11) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
