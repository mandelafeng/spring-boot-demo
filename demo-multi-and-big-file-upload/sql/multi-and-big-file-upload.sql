/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 5.7.38 : Database - spring-boot-demo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`spring-boot-demo` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `spring-boot-demo`;

/*Table structure for table `file_chunk` */

DROP TABLE IF EXISTS `file_chunk`;

CREATE TABLE `file_chunk` (
  `id` bigint(20) unsigned NOT NULL,
  `identifier` varchar(45) DEFAULT NULL COMMENT '文件标识',
  `chunk_size` float DEFAULT NULL COMMENT '分片大小',
  `current_chunk_size` float DEFAULT NULL COMMENT '当前分片大小',
  `chunk_number` int(11) DEFAULT NULL COMMENT '当前分片，从1开始',
  `total_chunk` int(11) DEFAULT NULL COMMENT '总分片数',
  `status` tinyint(1) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `file_type` varchar(45) DEFAULT NULL COMMENT '文件类型',
  `relative_path` varchar(255) DEFAULT NULL,
  `version` tinyint(1) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `file_chunk` */

insert  into `file_chunk`(`id`,`identifier`,`chunk_size`,`current_chunk_size`,`chunk_number`,`total_chunk`,`status`,`file_name`,`file_type`,`relative_path`,`version`,`update_time`,`add_time`) values 
(799698214084157440,'fe0397adc32aea4341b9ffc5dd7a690a',104858000,104858000,3,5,1,'ideaIC-2021.3.3.exe',NULL,'ideaIC-2021.3.3.exe',NULL,NULL,'2022-12-12 15:16:18'),
(799698686094352384,'fe0397adc32aea4341b9ffc5dd7a690a',104858000,104858000,1,5,1,'ideaIC-2021.3.3.exe',NULL,'ideaIC-2021.3.3.exe',NULL,NULL,'2022-12-12 15:18:10'),
(799698686434091008,'fe0397adc32aea4341b9ffc5dd7a690a',104858000,104858000,2,5,1,'ideaIC-2021.3.3.exe',NULL,'ideaIC-2021.3.3.exe',NULL,NULL,'2022-12-12 15:18:10'),
(799698878952644608,'fe0397adc32aea4341b9ffc5dd7a690a',104858000,104858000,4,5,1,'ideaIC-2021.3.3.exe',NULL,'ideaIC-2021.3.3.exe',NULL,NULL,'2022-12-12 15:18:56'),
(799698988264595456,'fe0397adc32aea4341b9ffc5dd7a690a',104858000,169462000,5,5,1,'ideaIC-2021.3.3.exe',NULL,'ideaIC-2021.3.3.exe',NULL,NULL,'2022-12-12 15:19:22');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
