

TRUNCATE TABLE `admin`;
TRUNCATE TABLE `admin_role`;
TRUNCATE TABLE `system_module`;
TRUNCATE TABLE `system_admin_module`;
/*
-- Query: SELECT * FROM bank.admin_role
-- Date: 2016-07-08 18:08
*/
INSERT INTO `admin_role` (`role_id`,`role_name`) VALUES (2,'分总');
INSERT INTO `admin_role` (`role_id`,`role_name`) VALUES (1,'区总');

/*
-- Query: SELECT * FROM bank.admin
-- Date: 2016-07-08 16:31
*/
INSERT INTO `admin` (`id`,`login`,`password`,`realname`,`role`,`login_ip`,`last_login_time`) VALUES (1,'admin','098f6bcd4621d373cade4e832627b4f6','管理员',1,NULL,NULL);
INSERT INTO `admin` (`id`,`login`,`password`,`realname`,`role`,`login_ip`,`last_login_time`) VALUES (2,'test','098f6bcd4621d373cade4e832627b4f6','测试',1,NULL,NULL);


/*
-- Query: SELECT * FROM bank.system_module
-- Date: 2016-07-08 16:28
*/
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (1,'顶级节点',0,1,'/');
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (2,'业务管理',1,1,'/business/manage');
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (3,'业绩管理',1,1,'/performance/manage');
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (4,'订单管理',1,1,'/order/manage');
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (5,'理财师管理',1,1,'/financial/manage');
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (6,'组织架构管理',1,1,'/organization/manage');
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (7,'客户管理',1,1,'/user/manage');
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (8,'产品管理',2,1,'/business/product');
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (9,'产品列表',8,1,'/business/product/list');

/*
-- Query: select * FROM bank.system_admin_module
-- Date: 2016-07-08 16:32
*/
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (1,1,1,'r');
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (2,1,2,'r');
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (3,1,3,'r');
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (4,1,4,'r');
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (5,1,5,'r');
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (6,1,6,'r');
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (7,1,7,'r');
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (8,2,2,'r');
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (9,2,3,'r');



##2016-07-15 改表，增加初始数据
ALTER TABLE bank.report_category
CHANGE COLUMN cid cid INT(11) NOT NULL AUTO_INCREMENT ;
INSERT INTO bank.report_category (name) VALUES ('每周点评');
INSERT INTO bank.report_category (name) VALUES ('复华财经新视点');
INSERT INTO bank.report_category (name) VALUES ('复华资产研究报告');

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
CHANGE COLUMN `ctime` `ctime` DATETIME NULL COMMENT '记录增加时间' ,
CHANGE COLUMN `invest_term_min` `invest_term_min` INT(11) NOT NULL COMMENT '最少投资期限' ,
CHANGE COLUMN `invest_term_max` `invest_term_max` INT(11) NOT NULL COMMENT '最大投资期限' ,
DROP INDEX `code_UNIQUE` ;

ALTER TABLE `bank`.`product`
ADD COLUMN `expected_min` TINYINT NULL COMMENT '预期年化收益率min' AFTER `invest_threshold`,
ADD COLUMN `expected_max` TINYINT NULL COMMENT '预期年化收益率max' AFTER `expected_min`;
#产品派息数据1:n
CREATE TABLE `bank`.`product_dividend_day` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pid` INT NOT NULL COMMENT '产品id',
  `day` DATE NOT NULL COMMENT '派息日',
  PRIMARY KEY (`id`))
COMMENT = '产品派息日';

INSERT INTO `bank`.`rights_category` (`name`) VALUES ('健康管理');
INSERT INTO `bank`.`rights_category` (`name`) VALUES ('旅行家');
INSERT INTO `bank`.`rights_category` (`name`) VALUES ('艺术家');
INSERT INTO `bank`.`rights_category` (`name`) VALUES ('商旅通');
INSERT INTO `bank`.`rights_category` (`name`) VALUES ('奢生活');
INSERT INTO `bank`.`rights_category` (`name`) VALUES ('爱体育');
INSERT INTO `bank`.`rights_category` (`name`) VALUES ('商学院');
INSERT INTO `bank`.`rights_category` (`name`) VALUES ('俱乐部');

CREATE TABLE `bank`.`activity_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL COMMENT '活动名称',
  PRIMARY KEY (`id`))
COMMENT = '活动分类';
INSERT INTO `bank`.`activity_category` (`name`) VALUES ('生命管理俱乐部');
INSERT INTO `bank`.`activity_category` (`name`) VALUES ('高尔夫俱乐部');
INSERT INTO `bank`.`activity_category` (`name`) VALUES ('教育传承俱乐部');
INSERT INTO `bank`.`activity_category` (`name`) VALUES ('商旅俱乐部');
INSERT INTO `bank`.`activity_category` (`name`) VALUES ('投资者俱乐部');

ALTER TABLE `bank`.`activity`
ADD COLUMN `summary` VARCHAR(255) NULL COMMENT '活动摘要' AFTER `is_display`;
ALTER TABLE `bank`.`activity`
ADD COLUMN `cid` TINYINT NULL COMMENT '活动类型' AFTER `summary`;
ALTER TABLE `bank`.`activity`
ADD COLUMN `cover` VARCHAR(45) NULL DEFAULT NULL COMMENT '封面' AFTER `cid`;
ALTER TABLE `bank`.`activity`
ADD COLUMN `department_id` TINYINT NULL COMMENT '活动发布角色部门' AFTER `dead_time`;
ALTER TABLE `bank`.`rights`
ADD COLUMN `supply` VARCHAR(255) NULL AFTER `ctime`,
ADD COLUMN `summary` TEXT NULL COMMENT '简介' AFTER `supply`;
ALTER TABLE `bank`.`rights`
ADD COLUMN `cover` VARCHAR(255) NULL COMMENT '封面' AFTER `summary`;
ALTER TABLE `bank`.`rights`
CHANGE COLUMN `spend_type` `spend_type` ENUM('var','static') NULL DEFAULT 'static' COMMENT '兑换积分是否固定' ,
CHANGE COLUMN `level` `level` TINYINT(3) UNSIGNED NULL COMMENT '需要的客户等级' ;
DROP TABLE `bank`.`rights_provider`;