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