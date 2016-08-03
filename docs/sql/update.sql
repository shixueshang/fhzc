###字段更新记录


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
ADD COLUMN `cid` INT(1) NULL COMMENT '活动类型' AFTER `summary`;
ALTER TABLE `bank`.`activity`
ADD COLUMN `cover` VARCHAR(255) NULL DEFAULT NULL COMMENT '封面' AFTER `cid`;
ALTER TABLE `bank`.`activity`
ADD COLUMN `department_id` INT(11) NULL COMMENT '活动发布角色部门' AFTER `cover`;


ALTER TABLE `bank`.`rights`
ADD COLUMN `supply` VARCHAR(255) NULL AFTER `ctime`,
ADD COLUMN `summary` TEXT NULL COMMENT '简介' AFTER `supply`;
ALTER TABLE `bank`.`rights`
ADD COLUMN `cover` VARCHAR(255) NULL COMMENT '封面' AFTER `summary`;
ALTER TABLE `bank`.`rights`
CHANGE COLUMN `spend_type` `spend_type` ENUM('var','static') NULL DEFAULT 'static' COMMENT '兑换积分是否固定' ,
CHANGE COLUMN `level` `level` TINYINT(3) UNSIGNED NULL COMMENT '需要的客户等级' ;

#2016-07-19
DROP TABLE IF EXISTS `bank`.`product_dividend_day` ;
DROP TABLE IF EXISTS `bank`.`report_category` ;
DROP TABLE IF EXISTS `bank`.`rights_category` ;
DROP TABLE IF EXISTS `bank`.`activity_category` ;
DROP TABLE `bank`.`contract_tmp`;

#2016-07-20
ALTER TABLE `bank`.`product_reservation`
ADD COLUMN `amount` INT NULL COMMENT '投资金额' AFTER `result`;

#2016-07-21
ALTER TABLE `bank`.`activity`
ADD COLUMN `is_recommend` INT(1) UNSIGNED NULL DEFAULT '0' COMMENT '是否推荐 1是|0否' AFTER `department_id`;
ALTER TABLE `bank`.`report`
ADD COLUMN `is_recommend` TINYINT(3) UNSIGNED NULL DEFAULT '0' COMMENT '是否推荐 1是|0否' AFTER `is_display`;


#2016-07-22
ALTER TABLE `bank`.`department`
ADD COLUMN `leaf` INT(1) UNSIGNED NULL DEFAULT '0' COMMENT '是否叶子节点' AFTER `ctime`;

#2016-07-22
ALTER TABLE `bank`.`department`
ADD COLUMN `status` INT(1) NULL COMMENT '数据状态(0正常1删除)' AFTER `leaf`;

ALTER TABLE `bank`.`product`
CHANGE COLUMN `buy_day` `buy_day` VARCHAR(200) NULL DEFAULT NULL COMMENT '开放申购日' ,
CHANGE COLUMN `redeem_day` `redeem_day` VARCHAR(200) NULL DEFAULT NULL COMMENT '赎回日' ;


#2016-07-23
ALTER TABLE `bank`.`product`
ADD COLUMN `throw_department` INT NULL COMMENT '投放分公司' AFTER `expected_max`;

DROP TABLE IF EXISTS `bank`.`product_areas` ;

ALTER TABLE `bank`.`report` CHANGE COLUMN  `is_recommend` `is_recommend` INT(1) NULL comment '是否推荐';

ALTER TABLE `bank`.`product`
CHANGE COLUMN `invest_threshold` `invest_threshold` DECIMAL(12,2) NULL DEFAULT 1000000 COMMENT '起投金额' ;
ALTER TABLE `bank`.`report`
CHANGE COLUMN `is_recommend` `is_recommend` TINYINT(3) UNSIGNED NULL DEFAULT '0' COMMENT '是否推荐 1是|0否' ;

#2016-07-25
ALTER TABLE `bank`.`planner_achivements_monthly`
ADD COLUMN `customer_name` VARCHAR(45) NULL COMMENT '客户姓名' AFTER `customer_uid`;


#2016-07-25
ALTER TABLE `bank`.`admin`
ADD COLUMN `mobile` VARCHAR(45) NULL COMMENT '手机号' AFTER `status`,
ADD COLUMN `organ` INT(11) NULL COMMENT '所属公司' AFTER `mobile`,
ADD COLUMN `area` VARCHAR(45) NULL AFTER `organ`;
ALTER TABLE `bank`.`product`
CHANGE COLUMN `invest_threshold` `invest_threshold` DECIMAL(12,2) NOT NULL DEFAULT '1000000.00' COMMENT '起投金额' ,
CHANGE COLUMN `expected_min` `expected_min` DECIMAL(12,2) NULL DEFAULT NULL COMMENT '预期年化收益率min' ,
CHANGE COLUMN `expected_max` `expected_max` DECIMAL(12,2) NULL DEFAULT NULL COMMENT '预期年化收益率max' ,
ADD COLUMN `collect_start` DATE NULL COMMENT '募集期的开始' AFTER `throw_department`,
ADD COLUMN `collect_end` DATE NULL COMMENT '募集期的结束' AFTER `collect_start`;

#2016-07-25
ALTER TABLE `bank`.`admin_role`
ADD COLUMN `description` VARCHAR(500) NULL COMMENT '描述' AFTER `role_name` ,
ADD COLUMN `status` INT(1) NULL COMMENT '状态0正常1禁用' AFTER `description`;
ALTER TABLE `bank`.`planner`
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;

DROP TABLE IF EXISTS `bank`.`passport` ;
ALTER TABLE `bank`.`assets_history`
ADD COLUMN `amount` INT(11) NULL DEFAULT NULL COMMENT '金额' AFTER `type`;

ALTER TABLE `bank`.`product_reservation`
ADD COLUMN `apply_time` DATETIME NULL COMMENT '预约时间' AFTER `planner_id`;

ALTER TABLE `bank`.`department`
ADD COLUMN `leader_uid` INT NULL COMMENT '部门负责人uid' AFTER `status`;

ALTER TABLE `bank`.`planner`
DROP COLUMN `dept_4`,
DROP COLUMN `dept_3`,
DROP COLUMN `dept_2`,
DROP COLUMN `dept_1`,
DROP COLUMN `area_uid`,
DROP COLUMN `mg_uid`,
DROP COLUMN `sub_mg_uid`,
DROP COLUMN `md_uid`;

#2016-07-27
ALTER TABLE `bank`.`contract`
ADD COLUMN `customer_name` VARCHAR(45) NULL AFTER `customer_id`,
DROP INDEX `serial_UNIQUE` ;

DROP TABLE `bank`.`contract_tmp`;
ALTER TABLE `bank`.`assets_history`
ADD COLUMN `dead_date` DATE NULL COMMENT '截至日期' AFTER `ctime`,
ADD COLUMN `payment_date` DATE NULL COMMENT '到账日期' AFTER `dead_date`;

ALTER TABLE `bank`.`contract`
ADD COLUMN `expire_day` DATE NULL DEFAULT NULL COMMENT '合同到期日期' AFTER `product_expire_day`;

ALTER TABLE `bank`.`rights_reservation`
ADD COLUMN `rights_id` INT NOT NULL COMMENT '预约的权益id' AFTER `id`;

ALTER TABLE `bank`.`activity_apply`
ADD COLUMN `is_contact` INT(1) NULL DEFAULT 0 COMMENT '是否电话联系1 已联系，0未联系' AFTER `ctime`,
ADD COLUMN `is_sure` INT(1) NULL DEFAULT 0 COMMENT '是否确认参加 1 参加，0不参加' AFTER `is_contact`;

ALTER TABLE `bank`.`rights_reservation`
CHANGE COLUMN `status` `status` INT(1) NULL DEFAULT NULL COMMENT '预约状态 0预约中|1预约成功|2预约失败|3客户取消预约|4客户消费|5客户缺席' ;

ALTER TABLE `bank`.`activity_apply`
CHANGE COLUMN `customer_id` `customer_id` INT NOT NULL ,
CHANGE COLUMN `planner_id` `planner_id` INT NULL DEFAULT NULL ;

ALTER TABLE `bank`.`focus`
ADD COLUMN `status` INT NULL DEFAULT 0 COMMENT '关注状态 0 未关注 1 关注' AFTER `ctime`;

ALTER TABLE `bank`.`focus`
DROP COLUMN `ftype`;

ALTER TABLE `bank`.`focus`
ADD COLUMN `ftype` ENUM('product','report','rights','activity') NULL AFTER `fid`;

ALTER TABLE `bank`.`focus`
ADD UNIQUE INDEX `fcs_uniq` (`uid` ASC, `fid` ASC, `ftype` ASC);

#2016-07-29
DROP TABLE IF EXISTS `bank`.`level` ;

ALTER TABLE `bank`.`user`
ADD COLUMN `salt` VARCHAR(45) NULL COMMENT '加密key' AFTER `avatar`;
ALTER TABLE `bank`.`rights_reservation`
CHANGE COLUMN `score_cost` `score_cost` INT NULL DEFAULT NULL COMMENT '积分花费' ;

ALTER TABLE `bank`.`activity_apply`
ADD COLUMN `person_num` INT NOT NULL DEFAULT 1 COMMENT '报名人数' AFTER `customer_id`,
ADD COLUMN `person_name` VARCHAR(45) NULL COMMENT '预约客户姓名' AFTER `is_sure`;


#2016-08-01
ALTER TABLE `bank`.`customer`
ADD COLUMN `customer_type` ENUM('single', 'organ') NULL COMMENT '客户类型' AFTER `bank_info_id`;
ALTER TABLE `bank`.`customer`
ADD COLUMN `business_license` VARCHAR(255) NULL COMMENT '营业执照' AFTER `customer_type`;
ALTER TABLE `bank`.`customer`
ADD COLUMN `account_license` VARCHAR(255) NULL COMMENT '开户许可' AFTER `business_license`;
ALTER TABLE `bank`.`customer`
ADD COLUMN `contact_relation` VARCHAR(45) NULL COMMENT '联系人关系' AFTER `account_license`;
ALTER TABLE `bank`.`customer`
ADD COLUMN `entrusted_letter` VARCHAR(45) NULL COMMENT '法人委托函' AFTER `contact_ralation`;
ALTER TABLE `bank`.`customer`
ADD COLUMN `organ_name` VARCHAR(100) NULL COMMENT '机构全称' AFTER `customer_type`;

ALTER TABLE `bank`.`customer`
CHANGE COLUMN `cb_id` `cb_id` VARCHAR(45) NULL DEFAULT NULL COMMENT '客户号（来自excel）' ;

ALTER TABLE `bank`.`rights`
ADD COLUMN `notice` VARCHAR(255) NULL DEFAULT '无' COMMENT '报名须知' AFTER `is_ recommend`;

ALTER TABLE `bank`.`activity`
ADD COLUMN `user_req` VARCHAR(255) NULL COMMENT '用户要求' AFTER `is_ recommend`;



