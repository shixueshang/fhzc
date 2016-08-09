-- MySQL Script generated by MySQL Workbench
-- 08/05/16 14:20:12
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bank
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bank` ;

-- -----------------------------------------------------
-- Schema bank
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bank` DEFAULT CHARACTER SET utf8 ;
USE `bank` ;

-- -----------------------------------------------------
-- Table `bank`.`activity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`activity` ;

CREATE TABLE IF NOT EXISTS `bank`.`activity` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL COMMENT '活动名称(标题）',
  `content` TEXT NULL DEFAULT NULL COMMENT '活动内容',
  `address` VARCHAR(45) NULL DEFAULT NULL COMMENT '活动地点',
  `apply_begin_time` DATETIME NULL DEFAULT NULL COMMENT '报名开始时间',
  `apply_end_time` DATETIME NULL DEFAULT NULL COMMENT '报名结束时间',
  `begin_time` DATE NULL DEFAULT NULL COMMENT '活动开始时间',
  `end_time` DATE NULL DEFAULT NULL COMMENT '活动结束时间',
  `memo` VARCHAR(255) NULL DEFAULT NULL COMMENT '注意事项',
  `sponsor` VARCHAR(45) NULL DEFAULT NULL COMMENT '活动主办方',
  `area_id` INT(11) NULL DEFAULT NULL,
  `ctime` DATETIME NOT NULL,
  `url` VARCHAR(255) NULL DEFAULT NULL COMMENT '活动链接',
  `status` INT(1) NOT NULL DEFAULT '0' COMMENT '活动状态 0预约中|1已完成',
  `is_display` INT(1) NOT NULL DEFAULT '0' COMMENT '是否显示 0否|1是',
  `summary` VARCHAR(255) NULL DEFAULT NULL COMMENT '活动摘要',
  `cid` INT(1) NULL DEFAULT NULL COMMENT '活动类型',
  `cover` VARCHAR(255) NULL DEFAULT NULL COMMENT '封面',
  `department_id` INT(11) NULL DEFAULT NULL COMMENT '活动发布角色部门',
  `is_ recommend` INT(1) UNSIGNED NULL DEFAULT '0' COMMENT '是否推荐 1是|0否',
  `user_req` VARCHAR(255) NULL DEFAULT NULL COMMENT '用户要求',
  PRIMARY KEY (`id`),
  INDEX `area` (`area_id` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '活动';


-- -----------------------------------------------------
-- Table `bank`.`activity_apply`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`activity_apply` ;

CREATE TABLE IF NOT EXISTS `bank`.`activity_apply` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `activity_id` INT(11) NOT NULL,
  `customer_id` INT(11) NOT NULL,
  `person_num` INT(11) NOT NULL DEFAULT '1' COMMENT '报名人数',
  `planner_id` INT(11) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL COMMENT '为他人报名留下电话',
  `type` ENUM('self', 'invite') NOT NULL DEFAULT 'self' COMMENT 'self:客户主动参与；invite:理财师邀请',
  `result` INT(11) NOT NULL DEFAULT '0' COMMENT '是否参加 0否|1是',
  `ctime` DATETIME NOT NULL,
  `is_contact` INT(1) NULL DEFAULT '0' COMMENT '是否电话联系1 已联系，0未联系',
  `is_sure` INT(1) NULL DEFAULT '0' COMMENT '是否确认参加 1 参加，0不参加',
  `person_name` VARCHAR(45) NULL DEFAULT NULL COMMENT '预约客户姓名',
  PRIMARY KEY (`id`),
  INDEX `activity_index` (`activity_id` ASC),
  INDEX `planner_index` (`planner_id` ASC),
  INDEX `type_index` (`type` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '活动邀请';


-- -----------------------------------------------------
-- Table `bank`.`admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`admin` ;

CREATE TABLE IF NOT EXISTS `bank`.`admin` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `realname` VARCHAR(45) NULL DEFAULT NULL,
  `login_ip` VARCHAR(45) NULL DEFAULT NULL,
  `last_login_time` DATETIME NULL DEFAULT NULL,
  `role` INT(11) NULL DEFAULT NULL,
  `status` INT(1) NULL DEFAULT NULL COMMENT '用户状态',
  `mobile` VARCHAR(45) NULL DEFAULT NULL COMMENT '手机号',
  `organ` INT(11) NULL DEFAULT NULL COMMENT '所属公司',
  `area` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_uniq` (`login` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '后台管理用户表';


-- -----------------------------------------------------
-- Table `bank`.`admin_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`admin_role` ;

CREATE TABLE IF NOT EXISTS `bank`.`admin_role` (
  `role_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` VARCHAR(255) NOT NULL COMMENT '管理组角色名称',
  `description` VARCHAR(500) NULL DEFAULT NULL,
  `status` INT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `name_uniq` (`role_name` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank`.`areas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`areas` ;

CREATE TABLE IF NOT EXISTS `bank`.`areas` (
  `area_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` INT(10) UNSIGNED NOT NULL COMMENT '上一级的id值',
  `area_name` VARCHAR(50) NOT NULL COMMENT '地区名称',
  `display_order` INT(10) UNSIGNED NOT NULL DEFAULT '99' COMMENT '排序',
  PRIMARY KEY (`area_id`),
  INDEX `parent_id` (`parent_id` ASC),
  INDEX `sort` (`display_order` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '地区信息';


-- -----------------------------------------------------
-- Table `bank`.`assets_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`assets_history` ;

CREATE TABLE IF NOT EXISTS `bank`.`assets_history` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `customer_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  `type` ENUM('dividend', 'purchase', 'renew', 'redemption') NOT NULL DEFAULT 'purchase',
  `amount` INT(11) NULL DEFAULT NULL COMMENT '金额',
  `ctime` DATETIME NOT NULL,
  `dead_date` DATE COMMENT '截至日期',
  `payment_date` DATE NULL DEFAULT NULL COMMENT '到账日期',
  `serial` VARCHAR(45) NOT NULL COMMENT '合同编号',
  `customer_name` VARCHAR(45) NULL DEFAULT NULL,
  `planner_id` INT(11) NULL DEFAULT NULL COMMENT '理财师id',
  `buy_time` DATE NOT NULL COMMENT '到账日期',
  `amount_usd` INT(11) NOT NULL COMMENT '投资额（美元）',
  `amount_rmb` INT(11) NOT NULL COMMENT '投资额（人民币）',
  `annualised` INT(11) NULL DEFAULT NULL COMMENT '年化金额',
  `period` VARCHAR(45) NOT NULL COMMENT '投资期限',
  `invaild` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '合同有效性:1有效|0无效',
  `product_found_day` DATE NULL DEFAULT NULL COMMENT '产品成立日期',
  `value_date` DATE NULL DEFAULT NULL COMMENT '起息日',
  `product_expire_day` DATE NULL DEFAULT NULL COMMENT '产品到期日期',
  `expire_day` DATE NULL DEFAULT NULL COMMENT '合同到期日期',
  `bank` VARCHAR(45) NOT NULL COMMENT '开户银行信息',
  `bank_account` VARCHAR(45) NOT NULL COMMENT '银行账号',
  `lot` VARCHAR(45) NULL DEFAULT NULL COMMENT '基金份额',
  `duration_month` INT(11) NULL DEFAULT NULL COMMENT '投资期限（月）',
  `duration_day` INT(11) NULL DEFAULT NULL COMMENT '投资期限（日）',
  `pub_agent` VARCHAR(45) NULL DEFAULT NULL COMMENT '发行机构',
  `branch_agent` VARCHAR(45) NULL DEFAULT NULL COMMENT '分支机构',
  `is_member` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '是否是会员 0否|1是',
  `memo` VARCHAR(45) NULL DEFAULT NULL COMMENT '备注信息',
  `earning_rate` VARCHAR(45) NULL DEFAULT NULL COMMENT '收益率',
  `distribute_earning` VARCHAR(45) NULL DEFAULT NULL COMMENT '分配收益',
  `payment` VARCHAR(45) NULL DEFAULT NULL COMMENT '兑付合计',
  PRIMARY KEY (`id`),
  INDEX `customer_index` (`customer_id` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '客户资产配置变动';


-- -----------------------------------------------------
-- Table `bank`.`banner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`banner` ;

CREATE TABLE IF NOT EXISTS `bank`.`banner` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` ENUM('index_text', 'index_pic') NULL DEFAULT NULL,
  `text` VARCHAR(45) NULL DEFAULT NULL,
  `cover` VARCHAR(255) NULL DEFAULT NULL,
  `from_id` VARCHAR(45) NULL DEFAULT NULL,
  `from_type` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank`.`contract`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`contract` ;

CREATE TABLE IF NOT EXISTS `bank`.`contract` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `serial` VARCHAR(45) NOT NULL COMMENT '合同编号',
  `product_id` INT(11) NOT NULL,
  `customer_id` INT(11) NOT NULL COMMENT '客户id',
  `customer_name` VARCHAR(45) NULL DEFAULT NULL,
  `planner_id` INT(11) NULL DEFAULT NULL COMMENT '理财师id',
  `buy_time` DATE NOT NULL COMMENT '到账日期',
  `amount_usd` INT(11) NOT NULL COMMENT '投资额（美元）',
  `amount_rmb` INT(11) NOT NULL COMMENT '投资额（人民币）',
  `annualised` INT(11) NULL DEFAULT NULL COMMENT '年化金额',
  `period` VARCHAR(45) NOT NULL COMMENT '投资期限',
  `invaild` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '合同有效性:1有效|0无效',
  `product_found_day` DATE NULL DEFAULT NULL COMMENT '产品成立日期',
  `value_date` DATE NULL DEFAULT NULL COMMENT '起息日',
  `product_expire_day` DATE NULL DEFAULT NULL COMMENT '产品到期日期',
  `expire_day` DATE NULL DEFAULT NULL COMMENT '合同到期日期',
  `bank` VARCHAR(45) NOT NULL COMMENT '开户银行信息',
  `bank_account` VARCHAR(45) NOT NULL COMMENT '银行账号',
  `lot` VARCHAR(45) NULL DEFAULT NULL COMMENT '基金份额',
  `duration_month` INT(11) NULL DEFAULT NULL COMMENT '投资期限（月）',
  `duration_day` INT(11) NULL DEFAULT NULL COMMENT '投资期限（日）',
  `pub_agent` VARCHAR(45) NULL DEFAULT NULL COMMENT '发行机构',
  `branch_agent` VARCHAR(45) NULL DEFAULT NULL COMMENT '分支机构',
  `is_member` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '是否是会员 0否|1是',
  `memo` VARCHAR(45) NULL DEFAULT NULL COMMENT '备注信息',
  `ctime` DATETIME NOT NULL,
  `earning_rate` VARCHAR(45) NULL DEFAULT NULL COMMENT '收益率',
  `distribute_earning` VARCHAR(45) NULL DEFAULT NULL COMMENT '分配收益',
  `payment` VARCHAR(45) NULL DEFAULT NULL COMMENT '兑付合计',
  `has_passport` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '合同是否有身份证信息',
  PRIMARY KEY (`id`, `period`),
  INDEX `product_index` (`product_id` ASC),
  INDEX `planner_index` (`planner_id` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '客户合同表';


-- -----------------------------------------------------
-- Table `bank`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`customer` ;

CREATE TABLE IF NOT EXISTS `bank`.`customer` (
  `customer_id` INT(11) NOT NULL AUTO_INCREMENT,
  `uid` INT(11) NOT NULL,
  `cb_id` VARCHAR(45) NULL DEFAULT NULL COMMENT '客户号（来自excel）',
  `level_id` INT(11) NULL DEFAULT NULL,
  `risk` VARCHAR(45) NULL DEFAULT NULL,
  `department_id` INT(11) NOT NULL COMMENT '客户所属分公司',
  `bank_info_id` INT(11) NULL DEFAULT NULL COMMENT '银行信息id',
  `organ_name` VARCHAR(100) NULL DEFAULT NULL COMMENT '机构全称',
  `customer_type` ENUM('single', 'organ') NULL DEFAULT NULL,
  `business_license` VARCHAR(255) NULL DEFAULT NULL COMMENT '营业执照',
  `account_license` VARCHAR(255) NULL DEFAULT NULL COMMENT '开户许可',
  `contact_relation` VARCHAR(45) NULL DEFAULT NULL COMMENT '联系人关系',
  `entrusted_letter` VARCHAR(45) NULL DEFAULT NULL COMMENT '法人委托函',
  PRIMARY KEY (`customer_id`),
  UNIQUE INDEX `customer_uniq` (`uid` ASC),
  INDEX `dept_index` (`department_id` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '客户信息表';


-- -----------------------------------------------------
-- Table `bank`.`customer_bank`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`customer_bank` ;

CREATE TABLE IF NOT EXISTS `bank`.`customer_bank` (
  `bank_info_id` INT(11) NOT NULL AUTO_INCREMENT,
  `bank` VARCHAR(45) NOT NULL COMMENT '开户行',
  `bank_account` VARCHAR(45) NOT NULL COMMENT '银行卡号',
  `ctime` DATETIME NOT NULL COMMENT '记录添加时间',
  `is_del` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0否|1是',
  PRIMARY KEY (`bank_info_id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank`.`customer_organ`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`customer_organ` ;

CREATE TABLE IF NOT EXISTS `bank`.`customer_organ` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `customer_id` INT(11) NOT NULL COMMENT '客户id',
  `rights_enjoy_person` VARCHAR(45) NULL DEFAULT NULL COMMENT '权益享用人',
  `passport_type_id` INT(11) NULL DEFAULT NULL COMMENT '证件类型',
  `passport_code` VARCHAR(45) NULL DEFAULT NULL COMMENT '证件号',
  `mobile` VARCHAR(11) NULL DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '机构客户权益享用人表';


-- -----------------------------------------------------
-- Table `bank`.`department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`department` ;

CREATE TABLE IF NOT EXISTS `bank`.`department` (
  `department_id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL DEFAULT NULL COMMENT '部门名',
  `parent_dept_id` INT(11) NULL DEFAULT NULL COMMENT '上级部门的ID',
  `ctime` DATETIME NOT NULL COMMENT '创建时间',
  `leaf` INT(1) NULL DEFAULT NULL COMMENT '是否叶子节点',
  `status` INT(1) NULL DEFAULT NULL COMMENT '数据状态(0正常1删除)',
  `leader_uid` INT(11) NULL DEFAULT NULL COMMENT '部门负责人uid',
  PRIMARY KEY (`department_id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '组织结构';


-- -----------------------------------------------------
-- Table `bank`.`dictionary`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`dictionary` ;

CREATE TABLE IF NOT EXISTS `bank`.`dictionary` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `cat` VARCHAR(50) NULL DEFAULT NULL COMMENT '字典分类【英】',
  `name` VARCHAR(50) NULL DEFAULT NULL COMMENT '字典分类【中】',
  `key` VARCHAR(50) NULL DEFAULT NULL COMMENT '键',
  `value` VARCHAR(50) NULL DEFAULT NULL COMMENT '值',
  `is_default` INT(1) NULL DEFAULT NULL COMMENT '是否默认 1、是，0、否',
  `status` INT(1) NULL DEFAULT NULL COMMENT '状态',
  `is_modify` INT(1) NULL DEFAULT NULL COMMENT '是否允许修改 1、是，0、否',
  `date_created` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
  `last_updated` DATETIME NULL DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '字典';


-- -----------------------------------------------------
-- Table `bank`.`focus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`focus` ;

CREATE TABLE IF NOT EXISTS `bank`.`focus` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `uid` INT(11) NULL DEFAULT NULL,
  `fid` INT(11) NULL DEFAULT NULL,
  `ftype` ENUM('product', 'report', 'rights', 'activity') NULL DEFAULT NULL,
  `ctime` DATETIME NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT '0' COMMENT '关注状态 0 未关注 1 关注',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `fcs_uniq` (`uid` ASC, `fid` ASC, `ftype` ASC),
  INDEX `uid_index` (`uid` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '客户关注';


-- -----------------------------------------------------
-- Table `bank`.`im_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`im_message` ;

CREATE TABLE IF NOT EXISTS `bank`.`im_message` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL COMMENT '用户id',
  `content` TEXT NULL DEFAULT NULL COMMENT '内容',
  `send_time` DATETIME NOT NULL COMMENT '发送时间',
  `session_id` VARCHAR(50) NOT NULL COMMENT '聊天组唯一标识',
  `message_type` VARCHAR(20) NOT NULL COMMENT '消息类型',
  `to_user_id` INT(11) NOT NULL COMMENT '消息接收人',
  `is_read` TINYINT(1) NULL DEFAULT '0' COMMENT '是否已读',
  `duration` VARCHAR(10) NULL DEFAULT NULL COMMENT '语音长度',
  `mid` INT(1) NULL DEFAULT '0' COMMENT '每个聊天组每条信息的唯一标识',
  `status` VARCHAR(20) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '消息表';


-- -----------------------------------------------------
-- Table `bank`.`log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`log` ;

CREATE TABLE IF NOT EXISTS `bank`.`log` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `desc` VARCHAR(45) NULL DEFAULT NULL,
  `ctime` DATETIME NOT NULL,
  `admin_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '后台系统变更日志';


-- -----------------------------------------------------
-- Table `bank`.`planner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`planner` ;

CREATE TABLE IF NOT EXISTS `bank`.`planner` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `uid` INT(11) NOT NULL,
  `work_num` VARCHAR(45) NOT NULL COMMENT '工号',
  `department_id` INT(11) NULL DEFAULT NULL COMMENT '所属分公司',
  `avatar` VARCHAR(255) NULL DEFAULT NULL COMMENT '头像',
  `status` ENUM('on', 'off') NOT NULL DEFAULT 'on' COMMENT '理财师状态,on在职,off离职',
  `role_id` INT(11) NULL DEFAULT NULL COMMENT '理财师角色',
  `entry_time` DATE NULL DEFAULT NULL COMMENT '入职时间',
  `leave_time` DATE NULL DEFAULT NULL COMMENT '离职时间',
  `job_title_cn` VARCHAR(45) NULL DEFAULT NULL COMMENT '岗位名称',
  `position` VARCHAR(45) NULL DEFAULT NULL COMMENT '岗位序列',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `work_num_uniq` (`work_num` ASC),
  INDEX `dept_index` (`department_id` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank`.`planner_achivements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`planner_achivements` ;

CREATE TABLE IF NOT EXISTS `bank`.`planner_achivements` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `uid` INT(11) NULL DEFAULT NULL,
  `sales` DECIMAL(15,2) NULL DEFAULT NULL COMMENT '年化销售额',
  `time_frame` VARCHAR(45) NULL DEFAULT NULL COMMENT '时间段',
  `ctime` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `uid_index` (`uid` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '理财师业绩';


-- -----------------------------------------------------
-- Table `bank`.`planner_achivements_daily`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`planner_achivements_daily` ;

CREATE TABLE IF NOT EXISTS `bank`.`planner_achivements_daily` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `transfer_date` DATE NULL DEFAULT NULL COMMENT '划款到账日期',
  `area_id` INT(11) NULL DEFAULT NULL COMMENT '地区',
  `planner_uid` INT(11) NOT NULL COMMENT '理财规划师uid',
  `product_id` INT(11) NOT NULL COMMENT '产品名称',
  `annualised` INT(11) NULL DEFAULT NULL COMMENT '年化金额',
  `contract_amount` INT(11) NULL DEFAULT NULL COMMENT '合同金额',
  `expire_date` DATE NULL DEFAULT NULL,
  `product_type` VARCHAR(45) NULL DEFAULT NULL COMMENT '产品类型',
  `memo` VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
  `ctime` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '理财师每日业绩动态';


-- -----------------------------------------------------
-- Table `bank`.`planner_achivements_monthly`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`planner_achivements_monthly` ;

CREATE TABLE IF NOT EXISTS `bank`.`planner_achivements_monthly` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `planner_uid` INT(11) NOT NULL COMMENT '理财规划师uid',
  `planner_percent` VARCHAR(5) NOT NULL COMMENT '理财师分单占比',
  `manager_uid` INT(11) NULL DEFAULT NULL COMMENT '客户经理uid',
  `mannager_percent` VARCHAR(45) NULL DEFAULT NULL COMMENT '客户经理分单占比',
  `product_id` INT(11) NOT NULL COMMENT '产品名称',
  `product_type` VARCHAR(45) NULL DEFAULT NULL,
  `customer_uid` INT(11) NOT NULL COMMENT '认购人uid',
  `customer_name` VARCHAR(45) NULL DEFAULT NULL COMMENT '客户姓名',
  `customer_buy` INT(11) NOT NULL COMMENT '认购金额',
  `annualised` INT(11) NULL DEFAULT NULL COMMENT '年化金额',
  `product_cycle` INT(11) NULL DEFAULT NULL COMMENT '产品周期（月）',
  `transfer_date` DATE NULL DEFAULT NULL COMMENT '划款到账日期',
  `memo` VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
  `ctime` DATETIME NOT NULL,
  `area_id` INT(11) NULL DEFAULT NULL COMMENT '地区',
  PRIMARY KEY (`id`),
  INDEX `uid_index` (`planner_uid` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '理财师每月确认业绩';


-- -----------------------------------------------------
-- Table `bank`.`planner_areas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`planner_areas` ;

CREATE TABLE IF NOT EXISTS `bank`.`planner_areas` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `planner_id` INT(11) NULL DEFAULT NULL COMMENT '理财师id',
  `area_id` INT(11) NULL DEFAULT NULL COMMENT '区域id',
  `ctime` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  INDEX `area_index` (`area_id` ASC),
  INDEX `planner_index` (`planner_id` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '理财师地理权限范围';


-- -----------------------------------------------------
-- Table `bank`.`planner_customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`planner_customer` ;

CREATE TABLE IF NOT EXISTS `bank`.`planner_customer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `planner_id` INT(11) NULL DEFAULT NULL,
  `customer_id` INT(11) NULL DEFAULT NULL,
  `is_main` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否为主理财师 0否|1是',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '客户理财师关系表';


-- -----------------------------------------------------
-- Table `bank`.`planner_department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`planner_department` ;

CREATE TABLE IF NOT EXISTS `bank`.`planner_department` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `uid` INT(11) NULL DEFAULT NULL,
  `department_id` INT(11) NULL DEFAULT NULL COMMENT '部门ID',
  `role` TINYINT(4) NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`),
  INDEX `planner_index` (`uid` ASC),
  INDEX `dept_index` (`department_id` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`product` ;

CREATE TABLE IF NOT EXISTS `bank`.`product` (
  `pid` INT(11) NOT NULL AUTO_INCREMENT,
  `status` TINYINT(4) NULL DEFAULT '-1' COMMENT '基金状态 -1未知|0产品预热|1募集中|2募集结束|3募集失败|4产品成立|5产品到期|6提前结束',
  `code` VARCHAR(45) NULL DEFAULT NULL COMMENT '产品代码',
  `name` VARCHAR(45) NOT NULL COMMENT '产品名称',
  `found_day` DATE NULL DEFAULT NULL COMMENT '成立日',
  `buy_day` VARCHAR(200) NULL DEFAULT NULL COMMENT '开放申购日',
  `redeem_day` VARCHAR(200) NULL DEFAULT NULL COMMENT '赎回日',
  `value_day` DATE NULL DEFAULT NULL COMMENT '起息日',
  `expiry_day` DATE NULL DEFAULT NULL COMMENT '到期日',
  `issue_type` INT(1) NULL DEFAULT NULL COMMENT '发行模式',
  `product_type` VARCHAR(200) NULL DEFAULT NULL COMMENT '产品类型',
  `renew_deadline` VARCHAR(200) NULL DEFAULT NULL COMMENT '产品续存期限',
  `dividend_day` VARCHAR(200) NULL DEFAULT NULL COMMENT '派息日',
  `annual_yield` VARCHAR(200) NULL DEFAULT NULL COMMENT '年化收益率',
  `income_distribution_type` VARCHAR(200) NULL DEFAULT NULL COMMENT '收益分配方式',
  `credit` VARCHAR(255) NULL DEFAULT NULL COMMENT '增信措施',
  `investment_orientation` VARCHAR(200) NULL DEFAULT NULL COMMENT '投资方向',
  `increase_trust` VARCHAR(200) NULL DEFAULT NULL COMMENT '增信措施',
  `highlights` VARCHAR(200) NULL DEFAULT NULL COMMENT '产品亮点',
  `fund_management_fee` VARCHAR(45) NOT NULL COMMENT '基金管理费',
  `fund_subscription_fee` VARCHAR(45) NOT NULL COMMENT '基金认购费',
  `fund_manager` VARCHAR(45) NOT NULL COMMENT '基金管理人',
  `custodian` VARCHAR(45) NOT NULL COMMENT '基金托管人',
  `investment_type` VARCHAR(45) NULL DEFAULT NULL COMMENT '投资类型',
  `project_source` VARCHAR(45) NULL DEFAULT NULL COMMENT '项目来源',
  `issuer` VARCHAR(45) NULL DEFAULT NULL COMMENT '发行公司',
  `is_record` VARCHAR(45) NULL DEFAULT '0' COMMENT '备案与否 0否|1是',
  `prove_url` VARCHAR(255) NULL DEFAULT NULL COMMENT '备案证明文件存储地址',
  `display_order` INT(11) NULL DEFAULT NULL COMMENT '显示顺序',
  `level` INT(11) NULL DEFAULT NULL COMMENT '允许购买的客户等级',
  `risk` INT(11) NULL DEFAULT NULL COMMENT '能接受的客户风险等级',
  `cover` VARCHAR(255) NULL DEFAULT NULL COMMENT '产品封面',
  `notice` VARCHAR(45) NULL DEFAULT NULL COMMENT '产品成立公告',
  `score_factor` TINYINT(3) UNSIGNED NULL DEFAULT '100' COMMENT '产品积分系数百分比,默认100%',
  `is_ recommend` TINYINT(3) UNSIGNED NULL DEFAULT '0' COMMENT '是否推荐 1是|0否',
  `is_display` TINYINT(3) UNSIGNED NULL DEFAULT '0' COMMENT '产品是否显示 1显示|0不显示',
  `is_renew` TINYINT(3) UNSIGNED NULL DEFAULT '0' COMMENT '是否是续存期产品 0否|1是',
  `desc` TEXT NULL DEFAULT NULL COMMENT '产品简介',
  `ctime` DATETIME NULL DEFAULT NULL COMMENT '记录增加时间',
  `detail_content` TEXT NULL DEFAULT NULL COMMENT '详细内容',
  `detail_url` VARCHAR(255) NULL DEFAULT NULL COMMENT '详情链接',
  `invest_term_min` INT(11) NOT NULL COMMENT '最少投资期限',
  `invest_term_max` INT(11) NOT NULL COMMENT '最大投资期限',
  `invest_threshold` DECIMAL(12,2) NOT NULL DEFAULT '1000000.00' COMMENT '起投金额',
  `expected_min` DECIMAL(6,2) NULL DEFAULT NULL COMMENT '预期年化收益率min',
  `expected_max` DECIMAL(6,2) NULL DEFAULT NULL COMMENT '预期年化收益率max',
  `throw_department` INT(11) NULL DEFAULT NULL COMMENT '投放分公司',
  `collect_start` DATE NULL DEFAULT NULL COMMENT '募集期的开始',
  `collect_end` DATE NULL DEFAULT NULL COMMENT '募集期的结束',
  PRIMARY KEY (`pid`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '产品';


-- -----------------------------------------------------
-- Table `bank`.`product_reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`product_reservation` ;

CREATE TABLE IF NOT EXISTS `bank`.`product_reservation` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_id` INT(11) NOT NULL,
  `customer_id` INT(11) NOT NULL,
  `planner_id` INT(11) NULL DEFAULT NULL,
  `apply_time` DATETIME NULL DEFAULT NULL COMMENT '预约时间',
  `ctime` DATETIME NULL DEFAULT NULL,
  `result` ENUM('cancel', 'success', 'failed', 'wait') NULL DEFAULT 'wait' COMMENT '预约状态：取消\\成功\\失败\\等待',
  `amount` INT(11) NULL DEFAULT NULL COMMENT '投资金额',
  PRIMARY KEY (`id`),
  INDEX `customer_index` (`customer_id` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '产品预约';


-- -----------------------------------------------------
-- Table `bank`.`provider`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`provider` ;

CREATE TABLE IF NOT EXISTS `bank`.`provider` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `desc` VARCHAR(45) NULL DEFAULT NULL COMMENT '描述',
  `area_id` INT(11) NULL DEFAULT NULL,
  `ctime` DATETIME NOT NULL COMMENT '添加时间',
  `level` TINYINT(4) NULL DEFAULT NULL COMMENT '需要的客户等级',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_uniq` (`name` ASC),
  INDEX `area_index` (`area_id` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '供应商';


-- -----------------------------------------------------
-- Table `bank`.`report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`report` ;

CREATE TABLE IF NOT EXISTS `bank`.`report` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `cid` INT(11) NULL DEFAULT NULL COMMENT '分类',
  `name` VARCHAR(45) NOT NULL COMMENT '名称',
  `cover` VARCHAR(255) NULL DEFAULT NULL COMMENT '封面图',
  `summary` TEXT NULL DEFAULT NULL COMMENT '摘要',
  `url` VARCHAR(255) NULL DEFAULT NULL COMMENT '投研报告外部url',
  `ctime` DATETIME NOT NULL,
  `is_del` INT(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `is_display` INT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否显示 1显示|0不显示',
  `is_recommend` INT(1) UNSIGNED NULL DEFAULT '0' COMMENT '是否推荐 1是|0否',
  PRIMARY KEY (`id`),
  INDEX `category` (`cid` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '投研报告';


-- -----------------------------------------------------
-- Table `bank`.`reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`reservation` ;

CREATE TABLE IF NOT EXISTS `bank`.`reservation` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_id` INT(11) NULL DEFAULT NULL,
  `customer_id` INT(11) NULL DEFAULT NULL,
  `planner_id` INT(11) NULL DEFAULT NULL,
  `ctime` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '产品预约';


-- -----------------------------------------------------
-- Table `bank`.`rights`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`rights` ;

CREATE TABLE IF NOT EXISTS `bank`.`rights` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `cid` INT(10) UNSIGNED NOT NULL COMMENT '权益分类名称',
  `name` VARCHAR(45) NOT NULL,
  `spend_score` INT(11) NOT NULL COMMENT '花费积分数量',
  `spend_type` ENUM('var', 'static') NULL DEFAULT 'static' COMMENT '兑换积分是否固定',
  `level` INT(10) UNSIGNED NULL DEFAULT NULL COMMENT '需要的客户等级',
  `ctime` DATETIME NOT NULL COMMENT '创建时间',
  `supply` VARCHAR(255) NULL DEFAULT NULL,
  `summary` TEXT NULL DEFAULT NULL COMMENT '简介',
  `cover` VARCHAR(255) NULL DEFAULT NULL COMMENT '封面',
  `url` VARCHAR(255) NULL DEFAULT NULL COMMENT '详情链接',
  `is_ recommend` INT(10) UNSIGNED NULL DEFAULT '0' COMMENT '是否精选 1是|0否',
  `notice` VARCHAR(255) NULL DEFAULT '无' COMMENT '报名须知',
  PRIMARY KEY (`id`),
  INDEX `cid_index` (`cid` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '权益';


-- -----------------------------------------------------
-- Table `bank`.`rights_provider`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`rights_provider` ;

CREATE TABLE IF NOT EXISTS `bank`.`rights_provider` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `rights_id` INT(11) NULL DEFAULT NULL COMMENT '权益id',
  `provider_id` INT(11) NULL DEFAULT NULL COMMENT '供应商id',
  `ctime` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank`.`rights_reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`rights_reservation` ;

CREATE TABLE IF NOT EXISTS `bank`.`rights_reservation` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `rights_id` INT(11) NOT NULL COMMENT '预约的权益id',
  `customer_id` INT(11) NOT NULL COMMENT '客户id',
  `planner_id` INT(11) NULL DEFAULT NULL COMMENT '理财师id',
  `mark_date` DATETIME NOT NULL COMMENT '预约时间',
  `detail` VARCHAR(45) NULL DEFAULT NULL COMMENT '预约详情',
  `score_cost` INT(11) NULL DEFAULT NULL COMMENT '积分花费',
  `status` INT(1) NULL DEFAULT NULL COMMENT '预约状态 0预约中|1预约成功|2预约失败|3客户取消预约|4客户消费|5客户缺席',
  `memo` VARCHAR(200) NULL DEFAULT NULL COMMENT '备注',
  `ctime` DATETIME NOT NULL COMMENT '记录添加时间',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '客户权益预约';


-- -----------------------------------------------------
-- Table `bank`.`score_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`score_history` ;

CREATE TABLE IF NOT EXISTS `bank`.`score_history` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `uid` INT(11) NOT NULL,
  `score` INT(11) NOT NULL COMMENT '积分数值，扣件为负数，增加为正数',
  `event_id` INT(11) NULL DEFAULT NULL COMMENT '获得或者扣减积分的来源id(产品购买+、参加活动+，兑换权益-)',
  `status` ENUM('add', 'consume', 'frozen', 'expire') NOT NULL DEFAULT 'frozen',
  `operator_type` ENUM('user', 'admin') NOT NULL COMMENT '操作触发人员类型:用户|管理员',
  `operator_id` INT(11) NOT NULL COMMENT '操作人id',
  `detail` VARCHAR(45) NULL DEFAULT NULL COMMENT '积分变动描述',
  `from_type` ENUM('activity', 'product', 'rights', 'other') NOT NULL DEFAULT 'other' COMMENT '积分变动来源',
  `vaild_time` DATE NULL DEFAULT NULL COMMENT '积分有效时间',
  `ctime` DATETIME NOT NULL COMMENT '记录创建时间',
  `is_vaild` INT(1) NOT NULL DEFAULT '1' COMMENT '有效性',
  `is_approve` INT(1) NOT NULL DEFAULT '0' COMMENT '审批状态',
  PRIMARY KEY (`id`),
  INDEX `uid_index` (`uid` ASC),
  INDEX `product_index` (`event_id` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '积分历史表';


-- -----------------------------------------------------
-- Table `bank`.`system_module`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`system_module` ;

CREATE TABLE IF NOT EXISTS `bank`.`system_module` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL COMMENT '模块名称',
  `url` VARCHAR(50) NOT NULL COMMENT '功能资源',
  `desc` VARCHAR(45) NULL DEFAULT NULL COMMENT '描述',
  `parent_module_id` INT(11) NULL DEFAULT NULL COMMENT '父级资源',
  `level` INT(1) NULL DEFAULT NULL COMMENT '资源级别',
  `is_valid` TINYINT(1) NOT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '后台系统模块名称';


-- -----------------------------------------------------
-- Table `bank`.`system_role_module`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`system_role_module` ;

CREATE TABLE IF NOT EXISTS `bank`.`system_role_module` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `admin_role_id` INT(11) NULL DEFAULT NULL,
  `module_id` INT(11) NOT NULL COMMENT '模块id',
  `mode` ENUM('r', 'rw') NOT NULL DEFAULT 'r' COMMENT '模块读\\读写',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '后台人员权限';


-- -----------------------------------------------------
-- Table `bank`.`template`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`template` ;

CREATE TABLE IF NOT EXISTS `bank`.`template` (
  `tid` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `content` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`tid`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '消息模板';


-- -----------------------------------------------------
-- Table `bank`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`user` ;

CREATE TABLE IF NOT EXISTS `bank`.`user` (
  `uid` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL COMMENT '前台用户（客户、理财师）',
  `password` VARCHAR(45) NOT NULL COMMENT '密码',
  `realname` VARCHAR(45) NULL DEFAULT NULL COMMENT '姓名',
  `gender` ENUM('male', 'female') NULL DEFAULT 'male' COMMENT '性别',
  `birthday` DATE NULL DEFAULT NULL COMMENT '生日',
  `passport_type_id` INT(11) NULL DEFAULT NULL COMMENT '证件类型',
  `passport_code` VARCHAR(200) NULL DEFAULT NULL COMMENT '证件号码（加密）',
  `passport_agent` VARCHAR(45) NULL DEFAULT NULL COMMENT '发证机关（加密）',
  `passport_address` VARCHAR(45) NULL DEFAULT NULL COMMENT '证件地址（加密）',
  `passport_expire` VARCHAR(45) NULL DEFAULT NULL COMMENT '证件有效期',
  `mobile` VARCHAR(200) NULL DEFAULT NULL COMMENT '移动电话',
  `phone` VARCHAR(45) NULL DEFAULT NULL COMMENT '固定电话',
  `phone_ext` VARCHAR(45) NULL DEFAULT NULL COMMENT '其他电话',
  `email` VARCHAR(200) NULL DEFAULT NULL COMMENT '邮箱',
  `address` VARCHAR(200) NULL DEFAULT NULL COMMENT '通信地址（加密）',
  `login_role` ENUM('customer', 'planner') NOT NULL COMMENT '登陆角色（客户、理财师）',
  `device_uuid` VARCHAR(45) NULL DEFAULT NULL COMMENT '登陆设备uuid',
  `area_id` INT(11) NULL DEFAULT NULL COMMENT '区域id',
  `is_del` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `ctime` DATETIME NOT NULL,
  `avatar` VARCHAR(255) NULL DEFAULT NULL COMMENT '用户图像',
  `salt` VARCHAR(45) NULL DEFAULT NULL COMMENT '加密key',
  PRIMARY KEY (`uid`),
  UNIQUE INDEX `login` (`login` ASC),
  INDEX `area` (`area_id` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = 'App登录用户总表';


-- -----------------------------------------------------
-- Table `bank`.`user_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`user_log` ;

CREATE TABLE IF NOT EXISTS `bank`.`user_log` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `uid` INT(11) NULL DEFAULT NULL COMMENT '用户id',
  `login_role` ENUM('customer', 'planner') NULL DEFAULT 'customer' COMMENT '登陆用户类型',
  `action` VARCHAR(45) NULL DEFAULT NULL,
  `ctime` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `uid_index` (`uid` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '手机登陆用户操作存档';


-- -----------------------------------------------------
-- Table `bank`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`user_role` ;

CREATE TABLE IF NOT EXISTS `bank`.`user_role` (
  `role_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` VARCHAR(255) NOT NULL COMMENT '角色名称',
  `ctime` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `role_name` (`role_name` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

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

DROP TABLE IF EXISTS `bank`.`verify_code` ;
CREATE TABLE IF NOT EXISTS `verify_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `mobile` varchar(30) DEFAULT '' COMMENT '手机号',
  `code_value` varchar(6) DEFAULT '' COMMENT '验证码内容',
  `send_date` datetime DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8
  COMMENT='短信验证码';



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
