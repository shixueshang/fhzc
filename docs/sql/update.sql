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