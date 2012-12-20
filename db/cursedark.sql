CREATE DATABASE  IF NOT EXISTS `cursedark` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `cursedark`;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
DROP TABLE IF EXISTS `hunt_spawn_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hunt_spawn_point` (
  `id` int(11) NOT NULL,
  `monster_id` int(11) NOT NULL,
  `scene_id` int(11) NOT NULL,
  `location_x` float DEFAULT NULL,
  `location_y` float DEFAULT NULL,
  `location_z` float DEFAULT NULL,
  `revive_frequency` int(11) DEFAULT NULL,
  `ai_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_hunt_spawn_point_scene1` (`scene_id`),
  KEY `fk_hunt_spawn_point_hunt_monster1` (`monster_id`),
  CONSTRAINT `fk_hunt_spawn_point_hunt_monster1` FOREIGN KEY (`monster_id`) REFERENCES `hunt_monster` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hunt_spawn_point_scene1` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `hunt_spawn_point` DISABLE KEYS */;
INSERT INTO `hunt_spawn_point` (`id`, `monster_id`, `scene_id`, `location_x`, `location_y`, `location_z`, `revive_frequency`, `ai_type`) VALUES (1,2,2,-7,2.515,-7,3,0),(2,3,2,-7,2.515,-4,3,0),(3,4,2,-5,2.515,-4,3,0),(4,5,2,-3,2.515,-7,3,0),(5,6,2,7,2.515,3,3,0),(6,7,2,6.5,2.515,-2,3,0),(7,8,2,6,2.515,-7,3,0),(8,5,1,46,-0.5,40,3,0),(9,5,1,50,-0.5,37,3,0),(10,5,1,56,-0.5,43,3,0);
/*!40000 ALTER TABLE `hunt_spawn_point` ENABLE KEYS */;
DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
DROP TABLE IF EXISTS `guild`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `guild` DISABLE KEYS */;
/*!40000 ALTER TABLE `guild` ENABLE KEYS */;
DROP TABLE IF EXISTS `item_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_function` (
  `id` int(11) NOT NULL,
  `name` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_en` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lv` int(11) DEFAULT NULL,
  `quality` int(11) DEFAULT NULL,
  `bonus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `item_function` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_function` ENABLE KEYS */;
DROP TABLE IF EXISTS `event_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `event_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_message` ENABLE KEYS */;
DROP TABLE IF EXISTS `char_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `remain` int(11) DEFAULT NULL,
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
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `char_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `char_info` ENABLE KEYS */;
DROP TABLE IF EXISTS `npc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `npc` (
  `id` int(11) NOT NULL,
  `model` int(11) DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_en` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `npc` DISABLE KEYS */;
/*!40000 ALTER TABLE `npc` ENABLE KEYS */;
DROP TABLE IF EXISTS `scene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scene` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_en` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `spawn_x` float DEFAULT NULL,
  `spawn_y` float DEFAULT NULL,
  `spawn_z` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `scene` DISABLE KEYS */;
INSERT INTO `scene` (`id`, `name`, `name_en`, `spawn_x`, `spawn_y`, `spawn_z`) VALUES (1,'city','city',51,-0.3,45),(2,'castle','castle',-7,2.6,7);
/*!40000 ALTER TABLE `scene` ENABLE KEYS */;
DROP TABLE IF EXISTS `npc_exist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `npc_exist` DISABLE KEYS */;
/*!40000 ALTER TABLE `npc_exist` ENABLE KEYS */;
DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` (`id`, `name`, `name_en`, `history`, `type`, `icon`, `values`, `weight`, `durability`, `max_durability`, `request_lv`, `request_class`, `request_str`, `request_con`, `request_dex`, `request_movp`, `request_wis`, `request_ws`) VALUES (1,'喬巴絨毛','ChopperCloth','armor',23,1,10,2,12,12,1,1,1,1,1,1,1,128),(2,'米國皮衣','CaptainCloth','armor',23,4,10,2,12,12,1,1,1,1,1,1,1,1),(3,'飛行者上裝','CaptainCloth','armor',23,5,10,2,12,12,1,1,1,1,1,1,1,1),(4,'小火龍皮','CaptainCloth','armor',23,6,10,2,12,12,1,1,1,1,1,1,1,1),(5,'喬巴手套','CaptainCloth','armor',24,1,10,2,12,12,1,1,1,1,1,1,1,1),(6,'米國手套','CaptainCloth','armor',24,4,10,2,12,12,1,1,1,1,1,1,1,1),(7,'飛行者手套','ChopperCloth','armor',24,5,10,2,12,12,1,1,1,1,1,1,1,1),(8,'小火龍爪','CaptainCloth','armor',24,6,10,2,12,12,1,1,1,1,1,1,1,1),(9,'學徒帽','CaptainCloth','armor',22,0,10,2,12,12,1,1,1,1,1,1,1,1),(10,'喬巴帽','CaptainCloth','armor',22,1,10,2,12,12,1,1,1,1,1,1,1,1),(11,'飛行者風鏡','CaptainCloth','armor',22,5,10,2,12,12,1,1,1,1,1,1,1,1),(12,'米國頭套','CaptainCloth','armor',21,4,10,2,12,12,1,1,1,1,1,1,1,1),(13,'小火龍頭套','CaptainCloth','armor',21,6,10,2,12,12,1,1,1,1,1,1,1,1),(14,'喬巴短褲','CaptainCloth','armor',25,1,10,2,12,12,1,1,1,1,1,1,1,1),(15,'米國皮褲','CaptainCloth','armor',25,4,10,2,12,12,1,1,1,1,1,1,1,1),(16,'飛行者短褲','CaptainCloth','armor',25,5,10,2,12,12,1,1,1,1,1,1,1,1),(17,'小火龍皮褲','CaptainCloth','armor',25,6,10,2,12,12,1,1,1,1,1,1,1,1),(18,'米國神盾','CaptainCloth','armor',27,4,10,2,12,12,1,1,1,1,1,1,1,1),(19,'喬巴鞋','CaptainCloth','armor',26,1,10,2,12,12,1,1,1,1,1,1,1,1),(20,'米國皮鞋','CaptainCloth','armor',26,4,10,2,12,12,1,1,1,1,1,1,1,1),(21,'飛行者鞋','ChopperCloth','armor',26,5,10,2,12,12,1,1,1,1,1,1,1,1),(22,'小火龍皮鞋','CaptainCloth','armor',26,6,10,2,12,12,1,1,1,1,1,1,1,1),(23,'電吉他','FashionGuitar','weapon',0,0,10,2,12,12,1,1,1,1,1,1,1,1),(24,'木吉他','WoodGuitar','weapon',0,1,10,2,12,12,1,1,1,1,1,1,1,1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
DROP TABLE IF EXISTS `hunt_monster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hunt_monster` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_en` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lv` int(11) DEFAULT NULL,
  `base_exp` int(11) DEFAULT NULL,
  `move_spd` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `hunt_monster` DISABLE KEYS */;
INSERT INTO `hunt_monster` (`id`, `name`, `name_en`, `lv`, `base_exp`, `move_spd`) VALUES (1,'皇鏡','Queen',20,60,3),(2,'哈乎齁','Piano',1,6,2),(3,'貝可熊','Bear',5,14,2),(4,'麥克幽幽','Mic',7,18,2),(5,'黃金甲','Golden',10,22,2),(6,'獨眼啪擦','Cyclous',15,34,2),(7,'帕奇尼',NULL,12,26,1),(8,'啪啦啪',NULL,18,45,1);
/*!40000 ALTER TABLE `hunt_monster` ENABLE KEYS */;
DROP TABLE IF EXISTS `portal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `portal` (
  `id` int(11) NOT NULL,
  `scene_current` int(11) NOT NULL,
  `scene_to` int(11) NOT NULL,
  `loc_curx` float DEFAULT '0',
  `loc_cury` float DEFAULT '0',
  `loc_curz` float DEFAULT '0',
  `loc_tox` float DEFAULT '0',
  `loc_toy` float DEFAULT '0',
  `loc_toz` float DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_portal_scene_current` (`scene_current`),
  KEY `fk_portal_scene_to` (`scene_to`),
  CONSTRAINT `fk_portal_scene_current` FOREIGN KEY (`scene_current`) REFERENCES `scene` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_portal_scene_to` FOREIGN KEY (`scene_to`) REFERENCES `scene` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `portal` DISABLE KEYS */;
INSERT INTO `portal` (`id`, `scene_current`, `scene_to`, `loc_curx`, `loc_cury`, `loc_curz`, `loc_tox`, `loc_toy`, `loc_toz`) VALUES (0,1,1,50,-0.5,44,51,-0.5,38),(2,1,2,53,-0.5,44,-7,2.515,7),(3,2,1,-7,2.515,4,51,0,50);
/*!40000 ALTER TABLE `portal` ENABLE KEYS */;
DROP TABLE IF EXISTS `skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skill` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_en` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `skill` DISABLE KEYS */;
INSERT INTO `skill` (`id`, `name`, `name_en`, `type`) VALUES (0,'123','456',9);
/*!40000 ALTER TABLE `skill` ENABLE KEYS */;
DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_en` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` (`id`, `name`, `name_en`) VALUES (0,'戰士','Warrior'),(1,'巫師','Wizard'),(2,'槍手','Sniper');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store` (
  `id` int(11) NOT NULL,
  `lv` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_en` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `store` DISABLE KEYS */;
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

