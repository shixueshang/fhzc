#2016-08-30
DROP TABLE IF EXISTS `bank`.`assets_recommend` ;
CREATE TABLE `bank`.`assets_recommend` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `recommend_type` VARCHAR(45) NULL COMMENT '推荐配置类别',
  `proportion` DECIMAL(4,2) NULL COMMENT '配置比例',
  `status` INT(1) NULL COMMENT '状态0正常1删除',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '资产推荐配置';
  
 #2016-09-06
 alter table product modify column annual_yield varchar(500) ;
 alter table rights add column rights_num varchar(45) not null;
 
#2016-09-07
ALTER TABLE `bank`.`system_notice_record`
ADD COLUMN `push_time` DATETIME NULL COMMENT '推送时间' AFTER `push_channel`;
alter table product add column annual_interval varchar(50) NULL COMMENT '参考业绩标准区间(%)';
alter table product modify column fund_manager varchar(200) ;

#2016-09-09 
alter table product alter column score_factor drop default; 
alter table product alter column score_factor set default 1.00;

#2016-09-10
alter table planner_achivements_monthly modify column customer_name varchar(200) ;
alter table product modify column renew_deadline varchar(500) ;

#2016-09-14
alter table score_history add column reservation_id int(11) ;
alter table report add column desc text ;