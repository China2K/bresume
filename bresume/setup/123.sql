/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.27 : Database - bresume
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `br_cus_contact` */

DROP TABLE IF EXISTS `br_cus_contact`;

CREATE TABLE `br_cus_contact` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `USER_ID` char(32) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CELL_PHONE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `STATUS` tinyint(4) DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_edu_experience` */

DROP TABLE IF EXISTS `br_edu_experience`;

CREATE TABLE `br_edu_experience` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `RESUME_ID` char(32) COLLATE utf8_bin DEFAULT NULL,
  `START_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  `S_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `MAJOR_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `DEGREE` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `DESC` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `ORDER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_job_intension` */

DROP TABLE IF EXISTS `br_job_intension`;

CREATE TABLE `br_job_intension` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `RESUME_ID` char(32) COLLATE utf8_bin DEFAULT NULL,
  `JOB_TYPE` tinyint(4) DEFAULT NULL,
  `ADDRESS` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `TRADE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PROFESSION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `EXPERT_SALARY` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `READY_TIME` tinyint(4) DEFAULT NULL,
  `SELF_EVALUATION` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_notification` */

DROP TABLE IF EXISTS `br_notification`;

CREATE TABLE `br_notification` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `TYPE` varchar(2) COLLATE utf8_bin NOT NULL,
  `SENDER` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `ADDRESS` text COLLATE utf8_bin NOT NULL,
  `CC` text COLLATE utf8_bin,
  `BCC` text COLLATE utf8_bin,
  `REPLYTO` text COLLATE utf8_bin,
  `SUBJECT` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT` text COLLATE utf8_bin,
  `SENT_FLAG` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `SENT_TIME` datetime DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_oauth` */

DROP TABLE IF EXISTS `br_oauth`;

CREATE TABLE `br_oauth` (
  `ID` char(32) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID` char(32) COLLATE utf8_bin DEFAULT NULL,
  `TYPE` tinyint(2) DEFAULT NULL,
  `OPEN_ID` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ACCESS_TOKEN` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `EXPIRES_IN` int(11) DEFAULT NULL,
  `REFRESH_ACCESS_TIME` datetime DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `NICK_NAME` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ICON` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_persional_info` */

DROP TABLE IF EXISTS `br_persional_info`;

CREATE TABLE `br_persional_info` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `NAME` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `SEX` int(10) DEFAULT NULL,
  `BIRTHDAY` datetime DEFAULT NULL,
  `EXPERIENCE_YEAR` int(2) DEFAULT NULL,
  `PROFESSION` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `CREDENTIALS_TYPE` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '数据字段配置',
  `CREDENTIALS_NUMBER` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `CELL_PHONE` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `JOB_STATUS` tinyint(4) DEFAULT NULL,
  `SALARY` int(5) DEFAULT NULL,
  `SALARY_UNIT` int(2) DEFAULT NULL,
  `ADDRESS` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `AREA_CODE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `CITY_CODE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `PROVINCE_CODE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_BY` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `RESUME_ID` char(32) COLLATE utf8_bin DEFAULT NULL,
  `SITE_URL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_project_experience` */

DROP TABLE IF EXISTS `br_project_experience`;

CREATE TABLE `br_project_experience` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `RESUME_ID` char(32) COLLATE utf8_bin DEFAULT NULL,
  `START_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  `PROJECT_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PROJECT_DESC` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `RESP_DESC` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `ORDER` int(3) DEFAULT NULL,
  `SITE_URL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_resume` */

DROP TABLE IF EXISTS `br_resume`;

CREATE TABLE `br_resume` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID` char(32) COLLATE utf8_bin DEFAULT NULL,
  `STATUS` tinyint(4) DEFAULT NULL,
  `IS_PUBLIC` tinyint(1) DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `RECOMMENDED` tinyint(1) DEFAULT NULL,
  `COVER_URL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TEMPLATE_SN` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `ORDER` int(11) DEFAULT NULL,
  `DESC` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_resume_item` */

DROP TABLE IF EXISTS `br_resume_item`;

CREATE TABLE `br_resume_item` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `NAME` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `SN` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `DESC` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `ORDER` int(11) DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `IS_DEFAULT` tinyint(1) DEFAULT NULL,
  `REQUIRED` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_resume_log` */

DROP TABLE IF EXISTS `br_resume_log`;

CREATE TABLE `br_resume_log` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `RESUME_ID` char(32) COLLATE utf8_bin DEFAULT NULL,
  `EVENT_TYPE` tinyint(4) DEFAULT NULL,
  `USER_ID` char(32) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_resume_ref_item` */

DROP TABLE IF EXISTS `br_resume_ref_item`;

CREATE TABLE `br_resume_ref_item` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `RESUME_ID` char(32) COLLATE utf8_bin DEFAULT NULL,
  `ITEM_SN` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `ORDER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_skill` */

DROP TABLE IF EXISTS `br_skill`;

CREATE TABLE `br_skill` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `RESUME_ID` char(32) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `LEVEL` tinyint(4) DEFAULT NULL,
  `MASTER_TIME` int(11) DEFAULT NULL,
  `TIME_UNIT_CODE` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `DESC` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `ORDER` int(11) DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_sys_file` */

DROP TABLE IF EXISTS `br_sys_file`;

CREATE TABLE `br_sys_file` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `FILE_TYPE` int(2) DEFAULT NULL,
  `FILE_URL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `FILE_SIZE` bigint(15) DEFAULT NULL,
  `UPLOAD_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `FILE_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户文件上传记录';

/*Table structure for table `br_sys_hat_area` */

DROP TABLE IF EXISTS `br_sys_hat_area`;

CREATE TABLE `br_sys_hat_area` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `CODE` char(6) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `CITY_CODE` char(6) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_sys_hat_city` */

DROP TABLE IF EXISTS `br_sys_hat_city`;

CREATE TABLE `br_sys_hat_city` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `CODE` char(6) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `PROVINCE_CODE` char(6) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_sys_hat_province` */

DROP TABLE IF EXISTS `br_sys_hat_province`;

CREATE TABLE `br_sys_hat_province` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `CODE` char(6) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_sys_notice_template` */

DROP TABLE IF EXISTS `br_sys_notice_template`;

CREATE TABLE `br_sys_notice_template` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `NAME` varchar(64) CHARACTER SET utf8 NOT NULL,
  `SN` varchar(32) CHARACTER SET utf8 NOT NULL,
  `DESCRIPTION` varchar(128) CHARACTER SET utf8 DEFAULT NULL,
  `SUBJECT` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `CONTENT` longtext COLLATE utf8_bin,
  `STATUS` int(11) NOT NULL,
  `CREATED_BY` varchar(16) CHARACTER SET utf8 DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(16) CHARACTER SET utf8 DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `SENT_SYNC` tinyint(1) DEFAULT NULL COMMENT '‘0’-否；‘1‘-是',
  `TYPE` int(11) DEFAULT NULL,
  `MSG_CONTENT` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_sys_trade` */

DROP TABLE IF EXISTS `br_sys_trade`;

CREATE TABLE `br_sys_trade` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `CODE` char(32) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `PCODE` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `VERSION` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_sys_user` */

DROP TABLE IF EXISTS `br_sys_user`;

CREATE TABLE `br_sys_user` (
  `ID` varchar(32) CHARACTER SET utf8 NOT NULL,
  `USERNAME` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `PASSWORD` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `REAL_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `NUM` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL COMMENT '平台注册：0-激活；1-未激活；2-暂停；3-注销。\n            门户注册：0-激活；2-暂停；3-注销；4-注册未审批；5注册审批拒绝；',
  `ROLE_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `CELL_PHONE` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `DESC` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_BY` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_template` */

DROP TABLE IF EXISTS `br_template`;

CREATE TABLE `br_template` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `NAME` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `SN` varchar(10) COLLATE utf8_bin NOT NULL,
  `SITE_URL` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `DESC` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `RECOMMENDED` tinyint(1) DEFAULT NULL,
  `USED_COUNT` int(8) DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `SOURCE` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ORDER` int(11) DEFAULT NULL,
  `COVER_URL` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_BY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_user` */

DROP TABLE IF EXISTS `br_user`;

CREATE TABLE `br_user` (
  `ID` varchar(32) CHARACTER SET utf8 NOT NULL,
  `TYPE` int(1) DEFAULT NULL COMMENT '0-企业用户；1-个人用户；2-企业成员',
  `USERNAME` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `PASSWORD` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL COMMENT '平台注册：0-激活；1-未激活；2-暂停；3-注销。\n            门户注册：0-激活；2-暂停；3-注销；4-注册未审批；5注册审批拒绝；',
  `LEVEL` int(1) DEFAULT '0',
  `REGISTER_TYPE` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0A-门户；0B-运营管理平台',
  `CREATED_BY` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `CELL_PHONE` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `IS_PHONE_VERIFIED` tinyint(1) DEFAULT '0',
  `EMAIL` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `IS_EMAIL_VERIFIED` tinyint(1) DEFAULT '0',
  `LAST_PWD_ERROR_TIME` datetime DEFAULT NULL,
  `PWD_ERROR_TIMES` int(11) DEFAULT NULL,
  `NICK_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ICON` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_user_verified` */

DROP TABLE IF EXISTS `br_user_verified`;

CREATE TABLE `br_user_verified` (
  `ID` char(32) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID` char(32) COLLATE utf8_bin DEFAULT NULL,
  `CODE` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `VERIFIED_TIME` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `br_work_experience` */

DROP TABLE IF EXISTS `br_work_experience`;

CREATE TABLE `br_work_experience` (
  `ID` char(32) COLLATE utf8_bin NOT NULL,
  `RESUME_ID` char(32) COLLATE utf8_bin DEFAULT NULL,
  `START_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  `COMPANY_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `TRADE_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `DEGREE` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `DEPARTMENT` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `POSITION` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `DESC` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  `ORDER` int(3) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
