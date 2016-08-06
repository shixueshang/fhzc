ALTER TABLE `bank`.`planner_achivements_daily`
CHANGE COLUMN `expire_date` `period` INT NULL DEFAULT NULL COMMENT '期限' ;
