/*
Navicat MySQL Data Transfer

Source Server         : gp
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : graduation_project

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2020-03-04 16:40:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for child_classes
-- ----------------------------
DROP TABLE IF EXISTS `child_classes`;
CREATE TABLE `child_classes` (
  `child_classes_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `child_classes_name` varchar(20) DEFAULT NULL,
  `classes_id` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`child_classes_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of child_classes
-- ----------------------------
INSERT INTO `child_classes` VALUES ('1', '后端开发', '1');
INSERT INTO `child_classes` VALUES ('2', '移动开发', '1');
INSERT INTO `child_classes` VALUES ('3', '测试', '1');
INSERT INTO `child_classes` VALUES ('4', '运维/技术支持', '1');
INSERT INTO `child_classes` VALUES ('5', '数据', '1');
INSERT INTO `child_classes` VALUES ('6', '项目管理', '1');
INSERT INTO `child_classes` VALUES ('7', '硬件开发', '1');
INSERT INTO `child_classes` VALUES ('8', '前端开发', '1');
INSERT INTO `child_classes` VALUES ('9', '产品经理', '2');
INSERT INTO `child_classes` VALUES ('10', '高端产品职位', '2');
INSERT INTO `child_classes` VALUES ('11', '其他产品职位', '2');

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `classes_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `classes_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`classes_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES ('1', '技术');
INSERT INTO `classes` VALUES ('2', '产品');

-- ----------------------------
-- Table structure for job_classes
-- ----------------------------
DROP TABLE IF EXISTS `job_classes`;
CREATE TABLE `job_classes` (
  `job_classes_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `job_classes_name` varchar(20) DEFAULT NULL,
  `job_classes_url` varchar(255) DEFAULT NULL,
  `child_classes_id` tinyint(4) DEFAULT NULL,
  `isRecommend` tinyint(4) DEFAULT '0' COMMENT '0为非推荐职位分类，1为推荐职位分类',
  `isHot` tinyint(4) DEFAULT '0' COMMENT '0为非热门职位分类，1为热门职位分类',
  PRIMARY KEY (`job_classes_id`)
) ENGINE=MyISAM AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_classes
-- ----------------------------
INSERT INTO `job_classes` VALUES ('1', 'UE4', 'tempurl', '2', '0', '0');
INSERT INTO `job_classes` VALUES ('2', '移动开发', 'tempurl', '2', '0', '0');
INSERT INTO `job_classes` VALUES ('3', 'HTML5', 'tempurl', '2', '0', '0');
INSERT INTO `job_classes` VALUES ('4', 'Android', 'tempurl', '2', '0', '0');
INSERT INTO `job_classes` VALUES ('5', 'iOS', 'tempurl', '2', '0', '0');
INSERT INTO `job_classes` VALUES ('6', 'WP', 'tempurl', '2', '0', '0');
INSERT INTO `job_classes` VALUES ('7', '移动web前端', 'tempurl', '2', '0', '0');
INSERT INTO `job_classes` VALUES ('8', 'Flash开发', 'tempurl', '2', '0', '0');
INSERT INTO `job_classes` VALUES ('9', 'JavaScript', 'tempurl', '2', '0', '0');
INSERT INTO `job_classes` VALUES ('10', 'U3D', 'tempurl', '2', '0', '0');
INSERT INTO `job_classes` VALUES ('11', 'COCOS2DX', 'tempurl', '2', '0', '0');
INSERT INTO `job_classes` VALUES ('12', '后端开发', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('13', 'Java', 'tempurl', '1', '1', '1');
INSERT INTO `job_classes` VALUES ('14', 'C++', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('15', 'PHP', 'tempurl', '1', '1', '0');
INSERT INTO `job_classes` VALUES ('16', '数据挖掘', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('17', 'C', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('18', 'C#', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('19', '.NET', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('20', 'Hadoop', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('21', 'Python', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('22', 'Delphi', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('23', 'VB', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('24', 'Perl', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('25', 'Ruby', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('26', 'Node.js', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('27', '搜索算法', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('28', 'Golang', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('29', '推荐算法', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('30', 'Erlang', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('31', '算法工程师', 'tempurl', '1', '1', '0');
INSERT INTO `job_classes` VALUES ('32', '语音/视频/图形开发', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('33', '数据采集', 'tempurl', '1', '0', '0');
INSERT INTO `job_classes` VALUES ('34', '测试工程师', 'tempurl', '3', '0', '0');
INSERT INTO `job_classes` VALUES ('35', '自动化测试', 'tempurl', '3', '0', '0');
INSERT INTO `job_classes` VALUES ('36', '功能测试', 'tempurl', '3', '0', '0');
INSERT INTO `job_classes` VALUES ('37', '性能测试', 'tempurl', '3', '0', '0');
INSERT INTO `job_classes` VALUES ('38', '测试开发', 'tempurl', '3', '0', '0');
INSERT INTO `job_classes` VALUES ('39', '移动端测试', 'tempurl', '3', '0', '0');
INSERT INTO `job_classes` VALUES ('40', '游戏测试', 'tempurl', '3', '0', '0');
INSERT INTO `job_classes` VALUES ('41', '硬件测试', 'tempurl', '3', '0', '0');
INSERT INTO `job_classes` VALUES ('42', '软件测试', 'tempurl', '3', '0', '0');
INSERT INTO `job_classes` VALUES ('43', '运维工程师', 'tempurl', '4', '0', '0');
INSERT INTO `job_classes` VALUES ('44', '运维开发工程师', 'tempurl', '4', '0', '0');
INSERT INTO `job_classes` VALUES ('45', '网络工程师', 'tempurl', '4', '0', '0');
INSERT INTO `job_classes` VALUES ('46', '系统工程师', 'tempurl', '4', '0', '0');
INSERT INTO `job_classes` VALUES ('47', 'IT技术支持', 'tempurl', '4', '0', '0');
INSERT INTO `job_classes` VALUES ('48', '系统管理员', 'tempurl', '4', '0', '0');
INSERT INTO `job_classes` VALUES ('49', '网络安全', 'tempurl', '4', '0', '0');
INSERT INTO `job_classes` VALUES ('50', '系统安全', 'tempurl', '4', '0', '0');
INSERT INTO `job_classes` VALUES ('51', 'DBA', 'tempurl', '4', '0', '0');
INSERT INTO `job_classes` VALUES ('52', '数据', 'tempurl', '5', '0', '0');
INSERT INTO `job_classes` VALUES ('53', 'ETL工程师', 'tempurl', '5', '0', '0');
INSERT INTO `job_classes` VALUES ('54', '数据仓库', 'tempurl', '5', '0', '0');
INSERT INTO `job_classes` VALUES ('55', '数据开发', 'tempurl', '5', '0', '0');
INSERT INTO `job_classes` VALUES ('56', '数据挖掘', 'tempurl', '5', '0', '0');
INSERT INTO `job_classes` VALUES ('57', '数据分析师', 'tempurl', '5', '0', '0');
INSERT INTO `job_classes` VALUES ('58', '数据架构师', 'tempurl', '5', '0', '0');
INSERT INTO `job_classes` VALUES ('59', '算法研究员', 'tempurl', '5', '0', '0');
INSERT INTO `job_classes` VALUES ('60', '项目经理', 'tempurl', '6', '0', '0');
INSERT INTO `job_classes` VALUES ('61', '项目主管', 'tempurl', '6', '0', '0');
INSERT INTO `job_classes` VALUES ('62', '项目助理', 'tempurl', '6', '0', '0');
INSERT INTO `job_classes` VALUES ('63', '项目专员', 'tempurl', '6', '0', '0');
INSERT INTO `job_classes` VALUES ('64', '实施顾问', 'tempurl', '6', '0', '0');
INSERT INTO `job_classes` VALUES ('65', '实施工程师', 'tempurl', '6', '0', '0');
INSERT INTO `job_classes` VALUES ('66', '需求分析工程师', 'tempurl', '6', '0', '0');
INSERT INTO `job_classes` VALUES ('67', '硬件', 'tempurl', '7', '0', '0');
INSERT INTO `job_classes` VALUES ('68', '嵌入式', 'tempurl', '7', '0', '0');
INSERT INTO `job_classes` VALUES ('69', '自动化', 'tempurl', '7', '0', '0');
INSERT INTO `job_classes` VALUES ('70', '单片机', 'tempurl', '7', '0', '0');
INSERT INTO `job_classes` VALUES ('71', '电路设计', 'tempurl', '7', '0', '0');
INSERT INTO `job_classes` VALUES ('72', '驱动开发', 'tempurl', '7', '0', '0');
INSERT INTO `job_classes` VALUES ('73', '系统集成', 'tempurl', '7', '0', '0');
INSERT INTO `job_classes` VALUES ('74', 'FPGA开发', 'tempurl', '7', '0', '0');
INSERT INTO `job_classes` VALUES ('75', 'DSP开发', 'tempurl', '7', '0', '0');
INSERT INTO `job_classes` VALUES ('76', 'ARM开发', 'tempurl', '7', '0', '0');
INSERT INTO `job_classes` VALUES ('77', 'PCB工艺', 'tempurl', '7', '0', '0');
INSERT INTO `job_classes` VALUES ('78', '射频工程师', 'tempurl', '7', '0', '0');
INSERT INTO `job_classes` VALUES ('79', '前端开发', 'tempurl', '8', '0', '0');
INSERT INTO `job_classes` VALUES ('80', 'web前端', 'tempurl', '8', '1', '0');
INSERT INTO `job_classes` VALUES ('81', 'JavaScript', 'tempurl', '8', '0', '0');
INSERT INTO `job_classes` VALUES ('82', 'Flash开发', 'tempurl', '8', '0', '0');
INSERT INTO `job_classes` VALUES ('83', 'HTML5', 'tempurl', '8', '0', '0');
INSERT INTO `job_classes` VALUES ('84', '硬件产品经理', 'tempurl', '9', '0', '0');
INSERT INTO `job_classes` VALUES ('85', '产品经理', 'tempurl', '9', '1', '0');
INSERT INTO `job_classes` VALUES ('86', '网页产品经理', 'tempurl', '9', '0', '0');
INSERT INTO `job_classes` VALUES ('87', '移动产品经理', 'tempurl', '9', '0', '0');
INSERT INTO `job_classes` VALUES ('88', '产品助理', 'tempurl', '9', '0', '0');
INSERT INTO `job_classes` VALUES ('89', '数据产品经理', 'tempurl', '9', '1', '0');
INSERT INTO `job_classes` VALUES ('90', '电商产品经理', 'tempurl', '9', '0', '0');
INSERT INTO `job_classes` VALUES ('91', '游戏策划', 'tempurl', '9', '0', '0');
INSERT INTO `job_classes` VALUES ('92', '产品专员', 'tempurl', '9', '0', '0');
INSERT INTO `job_classes` VALUES ('93', '高端产品职位', 'tempurl', '10', '0', '0');
INSERT INTO `job_classes` VALUES ('94', '产品总监', 'tempurl', '10', '1', '0');
INSERT INTO `job_classes` VALUES ('95', '游戏制作人', 'tempurl', '10', '0', '0');
INSERT INTO `job_classes` VALUES ('96', '产品VP', 'tempurl', '10', '0', '0');
INSERT INTO `job_classes` VALUES ('97', '其他产品职位', 'tempurl', '11', '0', '0');
