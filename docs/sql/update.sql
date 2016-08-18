ALTER TABLE `bank`.`planner_achivements_daily`
CHANGE COLUMN `expire_date` `period` INT NULL DEFAULT NULL COMMENT '期限' ;
ALTER TABLE `bank`.`activity`
CHANGE COLUMN `begin_time` `begin_time` DATETIME NULL DEFAULT NULL COMMENT '活动开始时间' ,
CHANGE COLUMN `end_time` `end_time` DATETIME NULL DEFAULT NULL COMMENT '活动结束时间' ;
ALTER TABLE `bank`.`planner_customer`
ADD COLUMN `memo` TEXT NULL DEFAULT '' COMMENT '理财师给客户的备注' AFTER `is_main`;


##2016-08-09
DROP TABLE IF EXISTS `bank`.`push_token` ;
CREATE TABLE `bank`.`push_token` (
  `id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `device_type` INT(1) NULL COMMENT '设备类型',
  `device_token` VARCHAR(100) NULL COMMENT '设备编号',
  `allow_push` INT(1) NULL COMMENT '是否允许推送',
  `allow_sound` INT(1) NULL COMMENT '是否开启声音',
  `bind_date` DATETIME NULL COMMENT '绑定日期',
  `is_delete` INT(1) NULL COMMENT '数据状态',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '消息推送';

ALTER TABLE `bank`.`dictionary`
CHANGE COLUMN `id` `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT ;
ALTER TABLE `bank`.`customer`
CHANGE COLUMN `risk` `risk` INT NULL DEFAULT 1 ;

#2016-08-10
ALTER TABLE `bank`.`activity`
CHANGE COLUMN `sponsor` `sponsor` VARCHAR(500) NULL DEFAULT NULL COMMENT '活动主办方' ;

#2016-08-11
ALTER TABLE `bank`.`planner_achivements_daily`
ADD COLUMN `company` INT(11) NULL COMMENT '理财师所属分公司' AFTER `ctime`,
ADD COLUMN `team` INT(11) NULL COMMENT '理财师所属团队' AFTER `company`,
ADD COLUMN `department_id` INT(11) NULL COMMENT '理财师所属部门' AFTER `team`;

ALTER TABLE `bank`.`planner_achivements_monthly`
ADD COLUMN `company` INT(11) NULL COMMENT '理财师所属分公司' AFTER `area_id`,
ADD COLUMN `team` INT(11) NULL COMMENT '理财师所属团队' AFTER `company`,
ADD COLUMN `department_id` INT(11) NULL COMMENT '理财师所属部门' AFTER `team`;

#2016-08-12
ALTER TABLE `bank`.`department`
ADD COLUMN `level` INT(1) NULL COMMENT '部门层级' AFTER `leader_uid`;

ALTER TABLE `bank`.`banner`
ADD COLUMN `status` INT(1) NULL COMMENT 'banner状态(0上线1已下线)' AFTER `from_type`;

#2016-08-16
DROP TABLE IF EXISTS `bank`.`about_app` ;
CREATE TABLE `bank`.`about_app` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `version` VARCHAR(45) NULL COMMENT '版本',
  `introduction` TEXT NULL COMMENT '简介',
  `is_using` INT(1) NULL COMMENT '是否正在使用',
  `type` VARCHAR(10) NULL COMMENT '类型1、关于App2、联系我们',
  PRIMARY KEY (`id`))
  COMMENT = '关于App';

ALTER TABLE `bank`.`planner_achivements_daily`
ADD COLUMN `area` INT(11) NULL COMMENT '区总' AFTER `department_id`;

ALTER TABLE `bank`.`planner_achivements_monthly`
ADD COLUMN `area` INT(11) NULL COMMENT '区总' AFTER `department_id`;


ALTER TABLE `bank`.`planner_achivements_daily`
ADD COLUMN `root_dept` INT(11) NULL COMMENT '顶级机构' AFTER `area`;

ALTER TABLE `bank`.`planner_achivements_monthly`
ADD COLUMN `root_dept` INT(11) NULL COMMENT '顶级机构' AFTER `area`;

#2016-08-17
DROP TABLE IF EXISTS `bank`.`system_notice` ;
CREATE TABLE `bank`.`system_notice` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NULL COMMENT '标题',
  `content` TEXT NULL COMMENT '内容',
  `push_status` INT(1) NULL COMMENT '推送状态0未推送1、待推送2、已推送',
  `push_channel` VARCHAR(45) NULL COMMENT '推送途径1、系统2、短信3、推送4、邮件',
  `publish_time` DATETIME NULL COMMENT '发布时间',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '消息推送维护表';

DROP TABLE IF EXISTS `bank`.`system_notice_record` ;
CREATE TABLE `bank`.`system_notice_record` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `notice_id` INT(11) NULL COMMENT '消息id',
  `user_id` INT(11) NULL COMMENT '推送用户',
  `content` TEXT NULL COMMENT '内容',
  `push_status` INT(1) NULL COMMENT '推送状态0未推送1、待推送2、已推送',
  `push_channel` INT(1) NULL COMMENT '推送途径1、系统2、短信3、推送4、邮件',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '推送记录表';

ALTER TABLE `bank`.`about_app`
ADD COLUMN `android_url` VARCHAR(255) NULL COMMENT '安装包下载地址' AFTER `type`,
ADD COLUMN `ios_url` VARCHAR(255) NULL AFTER `android_url`;


CREATE TABLE `bank`.`suggest` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL COMMENT '问题类型',
  `content` TEXT NULL COMMENT '问题内容',
  `imgs` TEXT NULL COMMENT '问题图片',
  `mobile` VARCHAR(45) NULL COMMENT '电话号码',
  PRIMARY KEY (`id`))
COMMENT = '用户反馈';
