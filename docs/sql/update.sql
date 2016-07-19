###字段更新记录

ALTER TABLE bank.report_category
CHANGE COLUMN cid cid INT(11) NOT NULL AUTO_INCREMENT ;


ALTER TABLE bank.report
ADD COLUMN is_display TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否显示 1显示|0不显示' AFTER is_del;
ALTER TABLE bank.product
ADD COLUMN buy_day DATE NULL COMMENT '开放申购日' AFTER found_day;
ALTER TABLE bank.product
ADD COLUMN redeem_day DATE NULL COMMENT '赎回日' AFTER buy_day;
ALTER TABLE bank.product
ADD COLUMN credit VARCHAR(255) NULL COMMENT '增信措施' AFTER income_distribution_type;
ALTER TABLE bank.product_areas
CHANGE COLUMN area_id area_id INT NULL DEFAULT NULL ,
ADD COLUMN department_id INT NULL COMMENT '分公司ID' AFTER area_id;
ALTER TABLE bank.product
ADD COLUMN detail_content TEXT NULL COMMENT '详细内容' AFTER ctime,
ADD COLUMN detail_url VARCHAR(255) NULL COMMENT '详情链接' AFTER detail_content;
ALTER TABLE bank.product
ADD COLUMN invest_term INT NULL COMMENT '投资期限' AFTER detail_url,
ADD COLUMN invest_threshold INT NULL COMMENT '起投金额' AFTER invest_term;
ALTER TABLE bank.product
CHANGE COLUMN invest_term invest_term_min INT(11) NULL DEFAULT NULL COMMENT '最少投资期限' ,
ADD COLUMN invest_term_max INT NULL COMMENT '最大投资期限' AFTER invest_term_min;

#2016-07-16
ALTER TABLE `product` MODIFY invest_threshold DECIMAL(12,2) COMMENT '起投金额';
alter table product modify issue_type tinyint(1) not null comment '发行模式';


#2016-07-18
ALTER TABLE `bank`.`product`
CHANGE COLUMN `status` `status` TINYINT(4) NULL DEFAULT '-1' COMMENT '基金状态 -1未知|0产品预热|1募集中|2募集结束|3募集失败|4产品成立|5产品到期|6提前结束' ,
CHANGE COLUMN `code` `code` VARCHAR(45) NULL COMMENT '产品代码' ,
CHANGE COLUMN `found_day` `found_day` DATE NULL COMMENT '成立日' ,
CHANGE COLUMN `value_day` `value_day` DATE NULL COMMENT '起息日' ,
CHANGE COLUMN `expiry_day` `expiry_day` DATE NULL COMMENT '到期日' ,
CHANGE COLUMN `issue_type` `issue_type` tinyint(1) NULL COMMENT '发行模式' ,
CHANGE COLUMN `product_type` `product_type` VARCHAR(200) NULL COMMENT '产品类型' ,
CHANGE COLUMN `fund_management_fee` `fund_management_fee` VARCHAR(45) NOT NULL COMMENT '基金管理费' ,
CHANGE COLUMN `fund_subscription_fee` `fund_subscription_fee` VARCHAR(45) NOT NULL COMMENT '基金认购费' ,
CHANGE COLUMN `fund_manager` `fund_manager` VARCHAR(45) NOT NULL COMMENT '基金管理人' ,
CHANGE COLUMN `custodian` `custodian` VARCHAR(45) NOT NULL COMMENT '基金托管人' ,
CHANGE COLUMN `is_record` `is_record` VARCHAR(45) NULL DEFAULT '0' COMMENT '备案与否 0否|1是' ,
CHANGE COLUMN `score_factor` `score_factor` TINYINT(3) UNSIGNED NULL DEFAULT '100' COMMENT '产品积分系数百分比,默认100%' ,
CHANGE COLUMN `is_ recommend` `is_ recommend` TINYINT(3) UNSIGNED NULL DEFAULT '0' COMMENT '是否推荐 1是|0否' ,
CHANGE COLUMN `is_display` `is_display` TINYINT(3) UNSIGNED NULL DEFAULT '0' COMMENT '产品是否显示 1显示|0不显示' ,
CHANGE COLUMN `is_renew` `is_renew` TINYINT(3) UNSIGNED NULL DEFAULT '0' COMMENT '是否是续存期产品 0否|1是' ,
CHANGE COLUMN `invest_term_min` `invest_term_min` INT(11) NOT NULL COMMENT '最少投资期限' ,
CHANGE COLUMN `invest_term_max` `invest_term_max` INT(11) NOT NULL COMMENT '最大投资期限' ,
DROP INDEX `code_UNIQUE` ;


ALTER TABLE `bank`.`product`
ADD COLUMN `expected_min` TINYINT NULL COMMENT '预期年化收益率min' AFTER `invest_threshold`,
ADD COLUMN `expected_max` TINYINT NULL COMMENT '预期年化收益率max' AFTER `expected_min`;


ALTER TABLE `bank`.`activity`
ADD COLUMN `summary` VARCHAR(255) NULL COMMENT '活动摘要' AFTER `is_display`;
ALTER TABLE `bank`.`activity`
ADD COLUMN `cid` TINYINT NULL COMMENT '活动类型' AFTER `summary`;
ALTER TABLE `bank`.`activity`
ADD COLUMN `cover` VARCHAR(45) NULL DEFAULT NULL COMMENT '封面' AFTER `cid`;
ALTER TABLE `bank`.`activity`
ADD COLUMN `department_id` TINYINT NULL COMMENT '活动发布角色部门' AFTER `cover`;
ALTER TABLE `bank`.`rights`
ADD COLUMN `supply` VARCHAR(255) NULL AFTER `ctime`,
ADD COLUMN `summary` TEXT NULL COMMENT '简介' AFTER `supply`;
ALTER TABLE `bank`.`rights`
ADD COLUMN `cover` VARCHAR(255) NULL COMMENT '封面' AFTER `summary`;
ALTER TABLE `bank`.`rights`
CHANGE COLUMN `spend_type` `spend_type` ENUM('var','static') NULL DEFAULT 'static' COMMENT '兑换积分是否固定' ,
CHANGE COLUMN `level` `level` TINYINT(3) UNSIGNED NULL COMMENT '需要的客户等级' ;