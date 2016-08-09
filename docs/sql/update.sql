ALTER TABLE `bank`.`planner_achivements_daily`
CHANGE COLUMN `expire_date` `period` INT NULL DEFAULT NULL COMMENT '期限' ;
ALTER TABLE `bank`.`activity`
CHANGE COLUMN `begin_time` `begin_time` DATETIME NULL DEFAULT NULL COMMENT '活动开始时间' ,
CHANGE COLUMN `end_time` `end_time` DATETIME NULL DEFAULT NULL COMMENT '活动结束时间' ;
ALTER TABLE `bank`.`planner_customer`
ADD COLUMN `memo` TEXT NULL DEFAULT '' COMMENT '理财师给客户的备注' AFTER `is_main`;
