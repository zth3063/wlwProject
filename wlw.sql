/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : wlw

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 29/12/2023 19:08:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for devices
-- ----------------------------
DROP TABLE IF EXISTS `devices`;
CREATE TABLE `devices`  (
  `deviceId` int(0) NOT NULL,
  `faultId` int(0) NULL DEFAULT NULL,
  `repairId` int(0) NULL DEFAULT NULL,
  `maintenanceId` int(0) NULL DEFAULT NULL,
  `deviceName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `deviceType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `deviceStatus` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `effciency` int(0) NULL DEFAULT NULL,
  `manufacturer` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `Specification` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `Life` int(0) NULL DEFAULT NULL,
  `techId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`deviceId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of devices
-- ----------------------------
INSERT INTO `devices` VALUES (2001, 3001, 4001, 5001, '风力发电机', '发电机', '运行中', 95, 'ABC Wind Corp', 'WG-100', '1000KW, 1500RPM', 10, '3001');
INSERT INTO `devices` VALUES (2002, 3002, 4002, 5002, '变速器', '变速器', '故障', NULL, 'XYZ Gear Works', 'GEAR-200', '500KW, 1200RPM', 8, '3002');
INSERT INTO `devices` VALUES (2003, 3003, 4003, 5003, '风轮', '风轮', '正常', NULL, 'DEF Blades Ltd', 'BLADE-50', '50 meters', 12, '3003');
INSERT INTO `devices` VALUES (2004, 3004, 4004, 5004, '控制系统', '控制系统', '运行中', NULL, 'SmartControl Inc', 'CTRL-500', 'Advanced Control', 15, '3004');
INSERT INTO `devices` VALUES (2005, 1, 1, 1, '1', '1', '1', 1, '1', '1', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for faults
-- ----------------------------
DROP TABLE IF EXISTS `faults`;
CREATE TABLE `faults`  (
  `faultId` int(0) NOT NULL,
  `deviceId` int(0) NULL DEFAULT NULL,
  `faultType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `faultDate` date NULL DEFAULT NULL,
  `reporter` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `faultPart` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`faultId`) USING BTREE,
  INDEX `device_id`(`deviceId`) USING BTREE,
  CONSTRAINT `faults_ibfk_1` FOREIGN KEY (`deviceId`) REFERENCES `devices` (`deviceId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of faults
-- ----------------------------
INSERT INTO `faults` VALUES (1, 2001, '电气故障', '2023-01-15', '维护团队', '发电机');
INSERT INTO `faults` VALUES (2, 2002, '电气故障', '2023-02-20', '操作员1', '发电机');
INSERT INTO `faults` VALUES (3, 2003, '电气故障', '2023-03-10', '技术人员2', '发电机');
INSERT INTO `faults` VALUES (4, 2004, '电气故障', '2023-04-05', '工程师3', '发电机');
INSERT INTO `faults` VALUES (5, 2002, '电气故障', '2023-05-12', '操作员2', '发电机');
INSERT INTO `faults` VALUES (6, 2001, '电气故障', '2023-01-05', '张三', '发电机');
INSERT INTO `faults` VALUES (7, 2001, '电气故障', '2023-02-10', '李四', '冷却系统');
INSERT INTO `faults` VALUES (8, 2003, '电气故障', '2023-03-15', '王五', '冷却系统');
INSERT INTO `faults` VALUES (9, 2004, '电气故障', '2023-04-20', '赵六', '冷却系统');
INSERT INTO `faults` VALUES (10, 2004, '电气故障', '2023-05-25', '刘七', '冷却系统');
INSERT INTO `faults` VALUES (11, 2004, '电气故障', '2023-06-30', '陈八', '冷却系统');
INSERT INTO `faults` VALUES (12, 2003, '电气故障', '2023-07-05', '钱九', '冷却系统');
INSERT INTO `faults` VALUES (13, 2002, '电气故障', '2023-08-10', '孙十', '冷却系统');
INSERT INTO `faults` VALUES (14, 2001, '电气故障', '2023-09-15', '周十一', '冷却系统');
INSERT INTO `faults` VALUES (15, 2004, '电气故障', '2023-10-20', '吴十二', '冷却系统');
INSERT INTO `faults` VALUES (16, 2001, '电气故障', '2023-11-25', '郑十三', '传感器系统');
INSERT INTO `faults` VALUES (17, 2004, '电气故障', '2023-12-30', '姜十四', '传感器系统');
INSERT INTO `faults` VALUES (18, 2002, '电气故障', '2024-01-05', '朱十五', '传感器系统');
INSERT INTO `faults` VALUES (19, 2003, '电气故障', '2024-02-10', '石十六', '传感器系统');
INSERT INTO `faults` VALUES (20, 2002, '电气故障', '2024-03-15', '严十七', '齿轮箱');
INSERT INTO `faults` VALUES (21, 2001, '电气故障', '2024-04-20', '华十八', '齿轮箱');
INSERT INTO `faults` VALUES (22, 2001, '电气故障', '2024-05-25', '谭十九', '发电机');
INSERT INTO `faults` VALUES (23, 2004, '系统故障', '2024-06-30', '梁二十', '冷却系统');
INSERT INTO `faults` VALUES (24, 2002, '通信丢失', '2024-07-05', '许二十一', '控制单元');
INSERT INTO `faults` VALUES (25, 2004, '传感器错误', '2024-08-10', '何二十二', '传感器系统');
INSERT INTO `faults` VALUES (26, 2001, '电源故障', '2023-01-05', '张三', '电源模块');
INSERT INTO `faults` VALUES (27, 2002, '电源故障', '2023-02-15', '李四', '电源线路');
INSERT INTO `faults` VALUES (28, 2003, '电源故障', '2023-03-20', '王五', '电源调节器');
INSERT INTO `faults` VALUES (29, 2004, '电源故障', '2023-04-10', '赵六', '电源线路');
INSERT INTO `faults` VALUES (30, 2001, '电源故障', '2023-05-12', '刘七', '电源变压器');
INSERT INTO `faults` VALUES (31, 2002, '电源故障', '2023-06-08', '陈八', '电源调节器');
INSERT INTO `faults` VALUES (32, 2002, '电源故障', '2023-07-14', '钱九', '电源电容器');
INSERT INTO `faults` VALUES (33, 2002, '电源故障', '2023-08-22', '孙十', '电源线路');
INSERT INTO `faults` VALUES (34, 2002, '电源故障', '2023-09-18', '周十一', '电源电容器');
INSERT INTO `faults` VALUES (35, 2002, '电源故障', '2023-10-25', '吴十二', '电源调节器');
INSERT INTO `faults` VALUES (36, 2001, '传感器故障', '2023-01-05', '张三', '传感器模块');
INSERT INTO `faults` VALUES (37, 2001, '传感器故障', '2023-02-15', '李四', '传感器电路');
INSERT INTO `faults` VALUES (38, 2002, '传感器故障', '2023-03-20', '王五', '传感器探头');
INSERT INTO `faults` VALUES (39, 2001, '传感器故障', '2023-04-10', '赵六', '传感器线缆');
INSERT INTO `faults` VALUES (40, 2003, '传感器故障', '2023-05-12', '刘七', '传感器芯片');
INSERT INTO `faults` VALUES (41, 2002, '传感器故障', '2023-06-08', '陈八', '传感器连接器');
INSERT INTO `faults` VALUES (42, 2001, '传感器故障', '2023-07-14', '钱九', '传感器连接器');
INSERT INTO `faults` VALUES (43, 2003, '传感器故障', '2023-08-22', '孙十', '传感器连接器');
INSERT INTO `faults` VALUES (44, 2001, '传感器故障', '2023-09-18', '周十一', '传感器芯片');
INSERT INTO `faults` VALUES (45, 2002, '传感器故障', '2023-10-25', '吴十二', '传感器电路');

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs`  (
  `logid` int(0) NOT NULL,
  `deviceId` int(0) NULL DEFAULT NULL,
  `logDate` date NULL DEFAULT NULL,
  `Content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `Recorder` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`logid`) USING BTREE,
  INDEX `deviceId`(`deviceId`) USING BTREE,
  CONSTRAINT `logs_ibfk_1` FOREIGN KEY (`deviceId`) REFERENCES `devices` (`deviceId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of logs
-- ----------------------------
INSERT INTO `logs` VALUES (20001, 2001, '2023-01-10', '设备正常运行', '张四');
INSERT INTO `logs` VALUES (20002, 2002, '2023-02-15', '发现设备温度异常，进行维护', '李四');
INSERT INTO `logs` VALUES (20003, 2003, '2023-03-20', '更新设备软件，增强性能', '王五');
INSERT INTO `logs` VALUES (20004, 2004, '2023-04-25', '设备停机维修，更换损坏零件', '赵六');

-- ----------------------------
-- Table structure for maintenance
-- ----------------------------
DROP TABLE IF EXISTS `maintenance`;
CREATE TABLE `maintenance`  (
  `maintenance_id` int(0) NOT NULL,
  `device_id` int(0) NULL DEFAULT NULL,
  `maintenance_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `maintenance_date` date NULL DEFAULT NULL,
  `maintenance_person` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`maintenance_id`) USING BTREE,
  INDEX `fk_device_maintenance`(`device_id`) USING BTREE,
  CONSTRAINT `fk_device_maintenance` FOREIGN KEY (`device_id`) REFERENCES `devices` (`deviceId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of maintenance
-- ----------------------------
INSERT INTO `maintenance` VALUES (1, 2002, '定期检查', '2023-12-28', 'Alice Johnson');
INSERT INTO `maintenance` VALUES (2, 2003, '故障修复', '2023-12-27', 'Bob Smith');
INSERT INTO `maintenance` VALUES (3, 2004, '预防性维护', '2023-12-26', 'Charlie Brown');

-- ----------------------------
-- Table structure for notices
-- ----------------------------
DROP TABLE IF EXISTS `notices`;
CREATE TABLE `notices`  (
  `noticeDate` date NULL DEFAULT NULL,
  `noticeContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `noticeState` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notices
-- ----------------------------
INSERT INTO `notices` VALUES ('2018-08-17', '【系统通知】该系统将于次日凌晨2点到5点进行升级维护', '0');
INSERT INTO `notices` VALUES ('2018-08-17', '【天气预报】明日成都龙泉驿区将会出现罕见高温', '1');
INSERT INTO `notices` VALUES ('2023-12-29', '【天气预报】123', '1');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `oid` int(0) NOT NULL,
  `startDate` date NULL DEFAULT NULL,
  `deliveryDate` date NULL DEFAULT NULL,
  `planid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`oid`) USING BTREE,
  INDEX `planid`(`planid`) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`planid`) REFERENCES `productions` (`planid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1001, '2023-01-02', '2023-02-15', 10001);
INSERT INTO `orders` VALUES (1002, '2023-02-10', '2023-03-20', 10002);
INSERT INTO `orders` VALUES (1003, '2023-03-05', '2023-04-10', 10003);
INSERT INTO `orders` VALUES (1004, '2023-12-12', '2023-12-31', 10004);
INSERT INTO `orders` VALUES (1005, '2023-12-12', '2023-12-31', NULL);

-- ----------------------------
-- Table structure for productions
-- ----------------------------
DROP TABLE IF EXISTS `productions`;
CREATE TABLE `productions`  (
  `planid` int(0) NOT NULL,
  `devicId` int(0) NOT NULL,
  `startDate` date NULL DEFAULT NULL,
  `endDate` date NULL DEFAULT NULL,
  PRIMARY KEY (`planid`, `devicId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of productions
-- ----------------------------
INSERT INTO `productions` VALUES (10001, 2001, '2023-01-10', '2023-02-05');
INSERT INTO `productions` VALUES (10002, 2002, '2023-02-15', '2023-03-10');
INSERT INTO `productions` VALUES (10003, 2003, '2023-03-20', '2023-04-15');
INSERT INTO `productions` VALUES (10004, 2004, '2023-04-25', '2023-05-20');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair`  (
  `repair_id` int(0) NOT NULL,
  `device_id` int(0) NULL DEFAULT NULL,
  `cost` decimal(10, 2) NULL DEFAULT NULL,
  `consumables` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `repair_date` date NULL DEFAULT NULL,
  `repair_person` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`repair_id`) USING BTREE,
  INDEX `fk_device_repair`(`device_id`) USING BTREE,
  CONSTRAINT `fk_device_repair` FOREIGN KEY (`device_id`) REFERENCES `devices` (`deviceId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES (1, 2002, 150.00, '更换零件', '2023-12-28', 'John Doe');
INSERT INTO `repair` VALUES (2, 2003, 200.00, '修理电路板', '2023-12-27', 'Jane Smith');
INSERT INTO `repair` VALUES (3, 2004, 120.00, '维修电源供应器', '2023-12-26', 'Bob Johnson');

-- ----------------------------
-- Table structure for sets
-- ----------------------------
DROP TABLE IF EXISTS `sets`;
CREATE TABLE `sets`  (
  `position` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `rights` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  PRIMARY KEY (`position`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sets
-- ----------------------------
INSERT INTO `sets` VALUES ('厂商', '{\"devices\":\"查询\",\"logs\":\"无\",\"orders\":\"查询\",\"productions\":\"查询\",\"sets\":\"无\",\"technologies\":\"更改\",\"user\":\"无\"}');
INSERT INTO `sets` VALUES ('工程师', '{\"devices\":\"更改\",\"logs\":\"查询\",\"orders\":\"查询\",\"productions\":\"查询\",\"sets\":\"无\",\"technologies\":\"查询\",\"user\":\"无\"}');
INSERT INTO `sets` VALUES ('技术员', '{\"devices\":\"查询\",\"logs\":\"更改\",\"orders\":\"查询\",\"productions\":\"查询\",\"sets\":\"无\",\"technologies\":\"更改\",\"user\":\"无\"}');
INSERT INTO `sets` VALUES ('管理员', '{\"devices\":\"更改\",\"logs\":\"更改\",\"orders\":\"更改\",\"productions\":\"更改\",\"sets\":\"更改\",\"technologies\":\"更改\",\"user\":\"更改\"}');
INSERT INTO `sets` VALUES ('经理', '{\"devices\":\"更改\",\"logs\":\"无\",\"orders\":\"更改\",\"productions\":\"更改\",\"sets\":\"查询\",\"technologies\":\"更改\",\"user\":\"更改\"}');

-- ----------------------------
-- Table structure for technologies
-- ----------------------------
DROP TABLE IF EXISTS `technologies`;
CREATE TABLE `technologies`  (
  `techId` int(0) NOT NULL,
  `manual` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `deviceDrawing` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `assembDrawing` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `sparePartsList` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  PRIMARY KEY (`techId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of technologies
-- ----------------------------
INSERT INTO `technologies` VALUES (3001, '设备A说明书', '0x...', '0x...', '螺丝、螺母、传感器等');
INSERT INTO `technologies` VALUES (3002, '设备B详细操作手册', '0x...', '0x...', '滤网、阀门、电缆等');
INSERT INTO `technologies` VALUES (3003, '设备C安装指南', '0x...', '0x...', '泵、阀、管道等');
INSERT INTO `technologies` VALUES (3004, '设备D维护手册', '0x...', '0x...', '电机、润滑油、传动带等');
INSERT INTO `technologies` VALUES (3005, '设备N维护手册', NULL, '0x...', '啊吧啊吧');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(0) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `upassword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `uposition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'demo', 'demo', 'demo');
INSERT INTO `user` VALUES (2, '小明', '9a440f1f071dad3043b6c8ba93bb761c', '工程师');
INSERT INTO `user` VALUES (3, '梅狸猫', 'ea8753722c0e8ecde195d6adb8ba7c0d', '技术员');
INSERT INTO `user` VALUES (4, '迪迦', '8af57c2ead460b609e042819827657a8', '经理');
INSERT INTO `user` VALUES (6, '我小法师', '2ba2712bf78d92aaa99502bd840424fe', '技术员');
INSERT INTO `user` VALUES (7, '小法师', '8c684820ea8f396252d02fcf32df25ba', '');
INSERT INTO `user` VALUES (8, '苏现飞', '4c797f0acb616d6a8894dc5bb8a337d3', '');
INSERT INTO `user` VALUES (9, '奥特曼', '7822b9f261889df1e3df69d83992f22d', '管理员');

SET FOREIGN_KEY_CHECKS = 1;
