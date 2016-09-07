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
 alter table product add column annual_interval varchar(50) NULL COMMENT '年化收益率区间'