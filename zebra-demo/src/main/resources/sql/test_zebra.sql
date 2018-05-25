/*
Navicat MySQL Data Transfer

Source Server         : duan
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : test_zebra

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2018-05-03 18:24:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sign_in`
-- ----------------------------
DROP TABLE IF EXISTS `sign_in`;
CREATE TABLE `sign_in` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` mediumint(10) NOT NULL,
  `DATE` datetime NOT NULL,
  `CURRENT_SIGN_IN_STORE_ID` int(11) DEFAULT NULL,
  `TYPE` int(2) DEFAULT NULL,
  `CREATE_EID` int(11) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_in
-- ----------------------------

-- ----------------------------
-- Table structure for `sign_in0`
-- ----------------------------
DROP TABLE IF EXISTS `sign_in0`;
CREATE TABLE `sign_in0` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` mediumint(10) NOT NULL,
  `DATE` datetime NOT NULL,
  `CURRENT_SIGN_IN_STORE_ID` int(11) DEFAULT NULL,
  `TYPE` int(2) DEFAULT NULL,
  `CREATE_EID` int(11) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_in0
-- ----------------------------

-- ----------------------------
-- Table structure for `sign_in1`
-- ----------------------------
DROP TABLE IF EXISTS `sign_in1`;
CREATE TABLE `sign_in1` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` mediumint(10) NOT NULL,
  `DATE` datetime NOT NULL,
  `CURRENT_SIGN_IN_STORE_ID` int(11) DEFAULT NULL,
  `TYPE` int(2) DEFAULT NULL,
  `CREATE_EID` int(11) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_in1
-- ----------------------------

-- ----------------------------
-- Table structure for `sign_in2`
-- ----------------------------
DROP TABLE IF EXISTS `sign_in2`;
CREATE TABLE `sign_in2` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` mediumint(10) NOT NULL,
  `DATE` datetime NOT NULL,
  `CURRENT_SIGN_IN_STORE_ID` int(11) DEFAULT NULL,
  `TYPE` int(2) DEFAULT NULL,
  `CREATE_EID` int(11) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_in2
-- ----------------------------

-- ----------------------------
-- Table structure for `sign_in3`
-- ----------------------------
DROP TABLE IF EXISTS `sign_in3`;
CREATE TABLE `sign_in3` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` mediumint(10) NOT NULL,
  `DATE` datetime NOT NULL,
  `CURRENT_SIGN_IN_STORE_ID` int(11) DEFAULT NULL,
  `TYPE` int(2) DEFAULT NULL,
  `CREATE_EID` int(11) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_in3
-- ----------------------------

-- ----------------------------
-- Table structure for `sign_in4`
-- ----------------------------
DROP TABLE IF EXISTS `sign_in4`;
CREATE TABLE `sign_in4` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` mediumint(10) NOT NULL,
  `DATE` datetime NOT NULL,
  `CURRENT_SIGN_IN_STORE_ID` int(11) DEFAULT NULL,
  `TYPE` int(2) DEFAULT NULL,
  `CREATE_EID` int(11) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_in4
-- ----------------------------

-- ----------------------------
-- Table structure for `sign_in5`
-- ----------------------------
DROP TABLE IF EXISTS `sign_in5`;
CREATE TABLE `sign_in5` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` mediumint(10) NOT NULL,
  `DATE` datetime NOT NULL,
  `CURRENT_SIGN_IN_STORE_ID` int(11) DEFAULT NULL,
  `TYPE` int(2) DEFAULT NULL,
  `CREATE_EID` int(11) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_in5
-- ----------------------------

-- ----------------------------
-- Table structure for `sign_in6`
-- ----------------------------
DROP TABLE IF EXISTS `sign_in6`;
CREATE TABLE `sign_in6` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` mediumint(10) NOT NULL,
  `DATE` datetime NOT NULL,
  `CURRENT_SIGN_IN_STORE_ID` int(11) DEFAULT NULL,
  `TYPE` int(2) DEFAULT NULL,
  `CREATE_EID` int(11) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_in6
-- ----------------------------
