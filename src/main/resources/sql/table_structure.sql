/*
 Navicat Premium Data Transfer

 Source Server         : battlehawk233.cn-test
 Source Server Type    : MySQL
 Source Server Version : 100604
 Source Host           : battlehawk233.cn:10036
 Source Schema         : barberplatform

 Target Server Type    : MySQL
 Target Server Version : 100604
 File Encoding         : 65001

 Date: 13/11/2021 19:59:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins`  (
                           `adminid` int NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
                           `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '管理员姓名',
                           `passwd` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '密码',
                           `salt` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '密码盐',
                           `gender` bit(1) NULL DEFAULT NULL COMMENT '性别',
                           `phone` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '手机号',
                           `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
                           `reg_time` datetime NOT NULL COMMENT '注册时间',
                           `last_order` int NULL DEFAULT NULL COMMENT '上一单ID',
                           `openid` int NULL DEFAULT NULL COMMENT '微信OpenID',
                           `is_superadmin` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否为超级用户',
                           PRIMARY KEY (`adminid`) USING BTREE,
                           INDEX `admintoken`(`name`, `phone`, `passwd`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
                                 `id` int NOT NULL COMMENT '公告ID',
                                 `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '公告标题',
                                 `desc` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '公告正文',
                                 `create_time` datetime NOT NULL COMMENT '创建时间',
                                 `author` int NOT NULL COMMENT '作者',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for barber_item
-- ----------------------------
DROP TABLE IF EXISTS `barber_item`;
CREATE TABLE `barber_item`  (
                                `itemid` int NOT NULL AUTO_INCREMENT COMMENT '项目ID',
                                `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '项目名称',
                                `price` decimal(10, 2) NOT NULL COMMENT '项目价格',
                                `add_time` datetime NOT NULL COMMENT '创建时间',
                                `img` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '图片路径',
                                `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
                                PRIMARY KEY (`itemid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_items
-- ----------------------------
DROP TABLE IF EXISTS `order_items`;
CREATE TABLE `order_items`  (
                                `orderid` int NOT NULL COMMENT '订单ID',
                                `itemid` int NOT NULL COMMENT '订单项目ID',
                                `sponsor` int NOT NULL COMMENT '添加人',
                                `amount` int NOT NULL DEFAULT 1 COMMENT '项目数量',
                                `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
                                `add_time` datetime NOT NULL COMMENT '添加时间',
                                PRIMARY KEY (`orderid`, `itemid`) USING BTREE,
                                INDEX `order_itemid`(`itemid`) USING BTREE,
                                INDEX `order_sponsorid`(`sponsor`) USING BTREE,
                                INDEX `order_items_oid`(`orderid`) USING BTREE,
                                CONSTRAINT `order_itemid` FOREIGN KEY (`itemid`) REFERENCES `barber_item` (`itemid`) ON DELETE RESTRICT ON UPDATE CASCADE,
                                CONSTRAINT `order_orderid` FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`) ON DELETE CASCADE ON UPDATE CASCADE,
                                CONSTRAINT `order_sponsorid` FOREIGN KEY (`sponsor`) REFERENCES `admins` (`adminid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
                           `orderid` int NOT NULL AUTO_INCREMENT COMMENT '订单ID',
                           `sponsorid` int NULL DEFAULT NULL COMMENT '订单发起者ID',
                           `ownerid` int NOT NULL COMMENT '订单所有者ID',
                           `state` int NOT NULL COMMENT '订单状态',
                           `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
                           `pay_method` int NULL DEFAULT NULL COMMENT '支付方式',
                           `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
                           `discount` decimal(10, 2) NULL DEFAULT NULL COMMENT '折扣',
                           `actual_payment` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际支付',
                           `create_time` datetime NOT NULL COMMENT '创建时间',
                           `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
                           PRIMARY KEY (`orderid`) USING BTREE,
                           INDEX `order_owner`(`ownerid`) USING BTREE,
                           INDEX `order-sponsor`(`sponsorid`) USING BTREE,
                           CONSTRAINT `order-sponsor` FOREIGN KEY (`sponsorid`) REFERENCES `admins` (`adminid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                           CONSTRAINT `order_owner` FOREIGN KEY (`ownerid`) REFERENCES `users` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reservations
-- ----------------------------
DROP TABLE IF EXISTS `reservations`;
CREATE TABLE `reservations`  (
                                 `reservationid` int NOT NULL AUTO_INCREMENT COMMENT '预订订单ID',
                                 `userid` int NOT NULL COMMENT '预订用户ID',
                                 `due_time` datetime NOT NULL COMMENT '预订时间',
                                 `create_time` datetime NOT NULL COMMENT '订单创建时间',
                                 `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
                                 `isfinished` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已完成',
                                 `targetbarber` int NOT NULL COMMENT '约定理发师',
                                 PRIMARY KEY (`reservationid`) USING BTREE,
                                 INDEX `targetbarber`(`targetbarber`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sessions
-- ----------------------------
DROP TABLE IF EXISTS `sessions`;
CREATE TABLE `sessions`  (
                             `access_token` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                             `adminid` int NOT NULL,
                             `login_time` datetime NOT NULL,
                             `last_access` datetime NOT NULL,
                             PRIMARY KEY (`access_token`) USING HASH,
                             INDEX `access_token`(`access_token`) USING HASH
) ENGINE = MEMORY CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for settings
-- ----------------------------
DROP TABLE IF EXISTS `settings`;
CREATE TABLE `settings`  (
                             `key` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                             `value` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
                             PRIMARY KEY (`key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for storedvalue_log
-- ----------------------------
DROP TABLE IF EXISTS `storedvalue_log`;
CREATE TABLE `storedvalue_log`  (
                                    `rechargeid` int NOT NULL AUTO_INCREMENT COMMENT '记录ID',
                                    `userid` int NOT NULL COMMENT '用户ID',
                                    `recharge_amount` decimal(10, 2) NOT NULL COMMENT '储值金额',
                                    `recharge_time` datetime NOT NULL COMMENT '储值时间',
                                    `type` int NOT NULL COMMENT '储值类型(0:扣款 1:充值)',
                                    `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
                                    PRIMARY KEY (`rechargeid`) USING BTREE,
                                    INDEX `storedvalue_log_userid`(`userid`) USING BTREE,
                                    CONSTRAINT `storedvalue_log_userid` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1999 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
                          `userid` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                          `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
                          `passwd` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '消费密码',
                          `salt` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码盐',
                          `birthdate` datetime NULL DEFAULT NULL COMMENT '生日',
                          `phone` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '手机号',
                          `gender` bit(1) NULL DEFAULT NULL COMMENT '性别',
                          `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
                          `balance` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '余额',
                          `openid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '微信OpenID(预留)',
                          `lastconsume_time` datetime NULL DEFAULT NULL COMMENT '上次消费时间',
                          `reg_time` datetime NOT NULL COMMENT '注册时间',
                          PRIMARY KEY (`userid`) USING BTREE,
                          UNIQUE INDEX `user_unique`(`username`, `phone`) USING BTREE,
                          INDEX `user`(`username`, `passwd`, `phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for v_admins
-- ----------------------------
DROP VIEW IF EXISTS `v_admins`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_admins` AS select `admins`.`adminid` AS `adminid`,`admins`.`name` AS `name`,`admins`.`phone` AS `phone`,`admins`.`remark` AS `remark`,`admins`.`reg_time` AS `reg_time`,`admins`.`last_order` AS `last_order`,`admins`.`openid` AS `openid`,`admins`.`is_superadmin` AS `is_superadmin` from `admins`;

-- ----------------------------
-- View structure for v_admin_passwd
-- ----------------------------
DROP VIEW IF EXISTS `v_admin_passwd`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_admin_passwd` AS select `admins`.`adminid` AS `adminid`,`admins`.`passwd` AS `passwd`,`admins`.`name` AS `name`,`admins`.`salt` AS `salt`,`admins`.`phone` AS `phone` from `admins`;

-- ----------------------------
-- View structure for v_orders
-- ----------------------------
DROP VIEW IF EXISTS `v_orders`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_orders` AS select `orders`.`orderid` AS `oid`,`users`.`username` AS `username`,`admins`.`name` AS `sponsor`,`orders`.`state` AS `state`,`orders`.`pay_time` AS `pay_time`,`orders`.`pay_method` AS `pay_method`,`orders`.`price` AS `price`,`orders`.`discount` AS `discount`,`orders`.`actual_payment` AS `actual_payment`,`orders`.`create_time` AS `create_time`,`orders`.`remark` AS `remark`,`orders`.`sponsorid` AS `sponsorid`,`orders`.`ownerid` AS `ownerid` from ((`orders` join `users` on(`orders`.`ownerid` = `users`.`userid`)) join `admins` on(`orders`.`sponsorid` = `admins`.`adminid`));

-- ----------------------------
-- View structure for v_order_items
-- ----------------------------
DROP VIEW IF EXISTS `v_order_items`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_order_items` AS select `order_items`.`orderid` AS `oid`,`barber_item`.`name` AS `item_name`,`order_items`.`amount` AS `amount`,`admins`.`name` AS `sponsor`,`order_items`.`remark` AS `remark`,`order_items`.`add_time` AS `add_time`,`order_items`.`itemid` AS `itemid`,`order_items`.`sponsor` AS `sponsorid` from (((`order_items` join `admins` on(`order_items`.`sponsor` = `admins`.`adminid`)) join `orders` on(`orders`.`sponsorid` = `admins`.`adminid` and `order_items`.`orderid` = `orders`.`orderid`)) join `barber_item` on(`order_items`.`itemid` = `barber_item`.`itemid`));

-- ----------------------------
-- View structure for v_storedvalue_log
-- ----------------------------
DROP VIEW IF EXISTS `v_storedvalue_log`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_storedvalue_log` AS select `storedvalue_log`.`rechargeid` AS `rechargeid`,`users`.`username` AS `username`,`storedvalue_log`.`recharge_amount` AS `recharge_amount`,`storedvalue_log`.`recharge_time` AS `recharge_time`,`storedvalue_log`.`type` AS `type`,`storedvalue_log`.`remark` AS `remark`,`storedvalue_log`.`userid` AS `userid` from (`storedvalue_log` join `users` on(`storedvalue_log`.`userid` = `users`.`userid`));

-- ----------------------------
-- View structure for v_user
-- ----------------------------
DROP VIEW IF EXISTS `v_user`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_user` AS select `users`.`userid` AS `userid`,`users`.`username` AS `username`,`users`.`birthdate` AS `birthdate`,`users`.`phone` AS `phone`,`users`.`gender` AS `gender`,`users`.`remark` AS `remark`,`users`.`balance` AS `balance`,`users`.`openid` AS `openid`,`users`.`lastconsume_time` AS `lastconsume_time`,`users`.`reg_time` AS `reg_time` from `users`;

-- ----------------------------
-- View structure for v_userpasswd
-- ----------------------------
DROP VIEW IF EXISTS `v_userpasswd`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_userpasswd` AS select `users`.`userid` AS `userid`,`users`.`passwd` AS `passwd`,`users`.`salt` AS `salt` from `users`;

SET FOREIGN_KEY_CHECKS = 1;
