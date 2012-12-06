/*
MySQL Data Transfer
Source Host: localhost
Source Database: cursedark
Target Host: localhost
Target Database: cursedark
Date: 2012/12/7 02:14:26
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for account
-- ----------------------------
CREATE TABLE `account` (
  `account_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `account_authority` int(11) DEFAULT '0',
  `is_ban` tinyint(1) DEFAULT '0',
  `birthday` date DEFAULT NULL,
  `host` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_login_date` date DEFAULT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for char_info
-- ----------------------------
CREATE TABLE `char_info` (
  `id` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `account_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `scene_id` int(11) DEFAULT '1',
  `location_x` float DEFAULT NULL,
  `location_y` float DEFAULT NULL,
  `location_z` float DEFAULT NULL,
  `cur_lv` int(11) DEFAULT '1',
  `cur_exp` int(11) DEFAULT '0',
  `cur_hp` int(11) DEFAULT '0',
  `cur_mp` int(11) DEFAULT '0',
  `color_r` float DEFAULT '0.5',
  `color_g` float DEFAULT '0.5',
  `color_b` float DEFAULT '0.5',
  `equipment` varchar(504) COLLATE utf8_unicode_ci DEFAULT NULL,
  `inventory` varchar(4032) COLLATE utf8_unicode_ci DEFAULT NULL,
  `inventory_shortcut` varchar(1008) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bank` varchar(4032) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fame` int(11) unsigned DEFAULT '0',
  `class_id` int(11) NOT NULL,
  `str` int(11) DEFAULT '1',
  `con` int(11) DEFAULT '1',
  `dex` int(11) DEFAULT '1',
  `luck` int(11) DEFAULT '1',
  `wis` int(11) DEFAULT '1',
  `ws` int(11) DEFAULT '1',
  `remain` int(11) DEFAULT '20',
  `guild` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_char_info_class_id` (`class_id`),
  KEY `fk_char_info_guild` (`guild`),
  KEY `fk_char_info_scene` (`scene_id`),
  KEY `fk_char_info_account` (`account_id`),
  CONSTRAINT `fk_char_info_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_char_info_class_id` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_char_info_guild` FOREIGN KEY (`guild`) REFERENCES `guild` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_char_info_scene` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for class
-- ----------------------------
CREATE TABLE `class` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_en` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for event_message
-- ----------------------------
CREATE TABLE `event_message` (
  `id` int(11) NOT NULL,
  `date_from` date DEFAULT NULL,
  `date_to` date DEFAULT NULL,
  `message` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  `scene_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_event_message_scene1` (`scene_id`),
  CONSTRAINT `fk_event_message_scene1` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for guild
-- ----------------------------
CREATE TABLE `guild` (
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `name_en` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lv` int(11) DEFAULT NULL,
  `exp` int(11) DEFAULT NULL,
  `master` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`name`),
  KEY `fk_guild_char_info1` (`master`),
  CONSTRAINT `fk_guild_char_info1` FOREIGN KEY (`master`) REFERENCES `char_info` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for hunt_monster
-- ----------------------------
CREATE TABLE `hunt_monster` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_en` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lv` int(11) DEFAULT NULL,
  `base_exp` int(11) DEFAULT NULL,
  `move_spd` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for hunt_spawn_point
-- ----------------------------
CREATE TABLE `hunt_spawn_point` (
  `id` int(11) NOT NULL,
  `scene_id` int(11) NOT NULL,
  `monster_id` int(11) NOT NULL,
  `max_count` int(11) DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `location_x` float DEFAULT NULL,
  `location_y` float DEFAULT NULL,
  `location_z` float DEFAULT NULL,
  `spawn_radius` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_hunt_spawn_point_scene1` (`scene_id`),
  KEY `fk_hunt_spawn_point_hunt_monster1` (`monster_id`),
  CONSTRAINT `fk_hunt_spawn_point_hunt_monster1` FOREIGN KEY (`monster_id`) REFERENCES `hunt_monster` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hunt_spawn_point_scene1` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for item
-- ----------------------------
CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_en` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `history` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `icon` int(11) DEFAULT NULL,
  `values` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `durability` int(11) DEFAULT NULL,
  `max_durability` int(11) DEFAULT NULL,
  `request_lv` int(11) DEFAULT NULL,
  `request_class` int(11) DEFAULT NULL,
  `request_str` int(11) DEFAULT NULL,
  `request_con` int(11) DEFAULT NULL,
  `request_dex` int(11) DEFAULT NULL,
  `request_movp` int(11) DEFAULT NULL,
  `request_wis` int(11) DEFAULT NULL,
  `request_ws` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for item_function
-- ----------------------------
CREATE TABLE `item_function` (
  `id` int(11) NOT NULL,
  `name` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_en` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lv` int(11) DEFAULT NULL,
  `quality` int(11) DEFAULT NULL,
  `bonus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for npc
-- ----------------------------
CREATE TABLE `npc` (
  `id` int(11) NOT NULL,
  `model` int(11) DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_en` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for npc_exist
-- ----------------------------
CREATE TABLE `npc_exist` (
  `npc_id` int(11) NOT NULL,
  `location_scene` int(11) NOT NULL,
  `location_x` float DEFAULT NULL,
  `location_y` float DEFAULT NULL,
  `location_z` float DEFAULT NULL,
  KEY `npc_id` (`npc_id`),
  KEY `location_scene` (`location_scene`),
  CONSTRAINT `location_scene` FOREIGN KEY (`location_scene`) REFERENCES `scene` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `npc_id` FOREIGN KEY (`npc_id`) REFERENCES `npc` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for portal
-- ----------------------------
CREATE TABLE `portal` (
  `id` int(11) NOT NULL,
  `scene_current` int(11) NOT NULL,
  `scene_to` int(11) NOT NULL,
  `location_x` float DEFAULT NULL,
  `location_y` float DEFAULT NULL,
  `location_z` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_portal_scene_current` (`scene_current`),
  KEY `fk_portal_scene_to` (`scene_to`),
  CONSTRAINT `fk_portal_scene_current` FOREIGN KEY (`scene_current`) REFERENCES `scene` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_portal_scene_to` FOREIGN KEY (`scene_to`) REFERENCES `scene` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for scene
-- ----------------------------
CREATE TABLE `scene` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_en` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for skill
-- ----------------------------
CREATE TABLE `skill` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_en` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for store
-- ----------------------------
CREATE TABLE `store` (
  `id` int(11) NOT NULL,
  `lv` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_en` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `account` VALUES ('111', '111', '0', '0', null, '120.127.32.52', null);
INSERT INTO `account` VALUES ('hair', '1234', '0', '0', null, '118.163.94.4', null);
INSERT INTO `account` VALUES ('hairache', 's255344', '0', '0', null, '118.163.94.4', null);
INSERT INTO `account` VALUES ('ibm', '1234', '0', '0', null, '120.127.36.45', null);
INSERT INTO `char_info` VALUES ('111', '111', '1', '0', '0', '0', '0', '0', '0', '0', '0.248691', '0.42801', '0.172775', null, null, null, null, '0', '1', '0', '0', '0', '0', '0', '0', '0', null);
INSERT INTO `char_info` VALUES ('小黃', 'hair', '1', '0', '0', '0', '0', '0', '0', '0', '0.890052', '0.955497', '0.065445', null, null, null, null, '0', '1', '0', '0', '0', '0', '0', '0', '0', null);
INSERT INTO `char_info` VALUES ('小黑', 'hairache', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', null, null, null, null, '0', '1', '0', '0', '0', '0', '0', '0', '0', null);
INSERT INTO `char_info` VALUES ('我是ibm', 'ibm', '1', '0', '0', '0', '0', '0', '0', '0', '0.251309', '0.5', '0.5', null, null, null, null, '0', '1', '0', '0', '0', '0', '0', '0', '0', null);
INSERT INTO `class` VALUES ('1', 'guitarist', 'guitarist');
INSERT INTO `item` VALUES ('1', '喬巴絨毛', 'ChopperCloth', 'armor', '23', '1', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '128');
INSERT INTO `item` VALUES ('2', '米國皮衣', 'CaptainCloth', 'armor', '23', '4', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('3', '飛行者上裝', 'CaptainCloth', 'armor', '23', '5', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('4', '小火龍皮', 'CaptainCloth', 'armor', '23', '6', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('5', '喬巴手套', 'CaptainCloth', 'armor', '24', '1', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('6', '米國手套', 'CaptainCloth', 'armor', '24', '4', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('7', '飛行者手套', 'ChopperCloth', 'armor', '24', '5', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('8', '小火龍爪', 'CaptainCloth', 'armor', '24', '6', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('9', '學徒帽', 'CaptainCloth', 'armor', '22', '0', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('10', '喬巴帽', 'CaptainCloth', 'armor', '22', '1', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('11', '飛行者風鏡', 'CaptainCloth', 'armor', '22', '5', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('12', '米國頭套', 'CaptainCloth', 'armor', '21', '4', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('13', '小火龍頭套', 'CaptainCloth', 'armor', '21', '6', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('14', '喬巴短褲', 'CaptainCloth', 'armor', '25', '1', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('15', '米國皮褲', 'CaptainCloth', 'armor', '25', '4', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('16', '飛行者短褲', 'CaptainCloth', 'armor', '25', '5', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('17', '小火龍皮褲', 'CaptainCloth', 'armor', '25', '6', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('18', '米國神盾', 'CaptainCloth', 'armor', '27', '4', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('19', '喬巴鞋', 'CaptainCloth', 'armor', '26', '1', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('20', '米國皮鞋', 'CaptainCloth', 'armor', '26', '4', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('21', '飛行者鞋', 'ChopperCloth', 'armor', '26', '5', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('22', '小火龍皮鞋', 'CaptainCloth', 'armor', '26', '6', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('23', '電吉他', 'FashionGuitar', 'weapon', '0', '0', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('24', '木吉他', 'WoodGuitar', 'weapon', '0', '1', '10', '2', '12', '12', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `scene` VALUES ('1', 'city', 'city');
INSERT INTO `skill` VALUES ('0', '123', '456', '9');
