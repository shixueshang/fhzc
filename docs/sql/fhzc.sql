SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bank
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bank` DEFAULT CHARACTER SET utf8 ;
USE `bank` ;

-- -----------------------------------------------------
-- Table `bank`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`user` ;

CREATE TABLE IF NOT EXISTS `bank`.`user` (
  `uid` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL COMMENT '前台用户（客户、理财师）',
  `password` VARCHAR(45) NOT NULL COMMENT '密码',
  `realname` VARCHAR(45) NULL COMMENT '姓名',
  `gender` ENUM('male','female') NULL DEFAULT 'male' COMMENT '性别',
  `birthday` DATE NULL COMMENT '生日',
  `passport_type` INT NULL COMMENT '证件类型',
  `passport_code` VARCHAR(45) NULL COMMENT '证件号码',
  `passport_agent` VARCHAR(45) NULL COMMENT '发证机关',
  `phone1` VARCHAR(45) NULL COMMENT '手机号1',
  `phone2` VARCHAR(45) NULL COMMENT '手机号2',
  `phone3` VARCHAR(45) NULL COMMENT '手机号3',
  `login_role` ENUM('customer','planner') NOT NULL COMMENT '登陆角色（客户、理财师）',
  `device_uuid` VARCHAR(45) NULL COMMENT '登陆设备uuid',
  `area_id` INT NULL COMMENT '区域id',
  `is_del` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除',
  `ctime` DATETIME NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE INDEX `login` (`login` ASC),
  INDEX `area` (`area_id` ASC),
  UNIQUE INDEX `uniq_mobile` (`phone1` ASC))
ENGINE = InnoDB
COMMENT = 'App登录用户总表';


-- -----------------------------------------------------
-- Table `bank`.`business`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`product` ;

CREATE TABLE IF NOT EXISTS `bank`.`product` (
  `pid` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NOT NULL COMMENT '产品代码',
  `name` VARCHAR(45) NOT NULL COMMENT '产品名称',
  `desc` TEXT NULL COMMENT '产品简介',
  `status` TINYINT NOT NULL DEFAULT -1 COMMENT '在售产品的状态 -1未知|0产品预热|1募集中|2募集结束|3募集失败|4产品成立|5产品到期',
  `value_day` DATE NULL COMMENT '起息日',
  `expiry_day` DATE NULL COMMENT '到期日',
  `dividend_day` DATE NULL COMMENT '派息日',
  `sort` INT NULL COMMENT '排序',
  `level` INT NULL COMMENT '允许购买的客户等级',
  `risk` INT NULL COMMENT '能接受的客户风险等级',
  `cover` VARCHAR(45) NULL COMMENT '产品封面',
  `notice` VARCHAR(45) NULL COMMENT '产品成立公告',
  `prove` VARCHAR(45) NULL COMMENT '备案证明',
  `score_factor` FLOAT NULL COMMENT '产品积分系数',
  `issuer` VARCHAR(45) NULL COMMENT '发行公司',
  `ctime` DATETIME NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE INDEX `name_uniq` (`name` ASC))
ENGINE = InnoDB
COMMENT = '产品';


-- -----------------------------------------------------
-- Table `bank`.`template`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`template` ;

CREATE TABLE IF NOT EXISTS `bank`.`template` (
  `tid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `content` TEXT NULL,
  PRIMARY KEY (`tid`))
ENGINE = InnoDB
COMMENT = '消息模板';


-- -----------------------------------------------------
-- Table `bank`.`log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`log` ;

CREATE TABLE IF NOT EXISTS `bank`.`log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `desc` VARCHAR(45) NULL,
  `ctime` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '后台系统变更日志';


-- -----------------------------------------------------
-- Table `bank`.`planner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`planner` ;

CREATE TABLE IF NOT EXISTS `bank`.`planner` (
  `id` INT NOT NULL,
  `uid` INT NULL,
  `work_num` VARCHAR(45) NOT NULL COMMENT '工号',
  `department_id` INT NULL COMMENT '所属分公司',
  `avatar` VARCHAR(45) NULL,
  `status` ENUM('on','off') NOT NULL DEFAULT 'on' COMMENT '理财师状态,on在职,off离职',
  `role_id` INT NULL COMMENT '理财师角色',
  `entry_time` DATE NULL COMMENT '入职时间',
  `leave_time` DATE NULL COMMENT '离职时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `work_num_uniq` (`work_num` ASC),
  INDEX `dept_index` (`department_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bank`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`customer` ;

CREATE TABLE IF NOT EXISTS `bank`.`customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `level_id` INT NULL,
  `risk` VARCHAR(45) NULL,
  `department_id` INT NOT NULL COMMENT '客户所属分公司',
  PRIMARY KEY (`id`),
  INDEX `dept_index` (`department_id` ASC),
  UNIQUE INDEX `customer_uniq` (`uid` ASC))
ENGINE = InnoDB
COMMENT = '客户信息表';


-- -----------------------------------------------------
-- Table `bank`.`department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`department` ;

CREATE TABLE IF NOT EXISTS `bank`.`department` (
  `department_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL COMMENT '部门名',
  `parent_dept_id` INT NULL COMMENT '上级部门的ID',
  `ctime` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`department_id`),
  UNIQUE INDEX `title_uniq` (`title` ASC))
ENGINE = InnoDB
COMMENT = '组织结构';


-- -----------------------------------------------------
-- Table `bank`.`planner_department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`planner_department` ;

CREATE TABLE IF NOT EXISTS `bank`.`planner_department` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NULL,
  `department_id` INT NULL COMMENT '部门ID',
  `role` TINYINT NULL COMMENT '角色',
  PRIMARY KEY (`id`),
  INDEX `planner_index` (`uid` ASC),
  INDEX `dept_index` (`department_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bank`.`rights`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`rights` ;

CREATE TABLE IF NOT EXISTS `bank`.`rights` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cid` INT UNSIGNED NOT NULL COMMENT '权益分类名称',
  `name` VARCHAR(45) NOT NULL,
  `spend_score` INT NOT NULL COMMENT '花费积分数量',
  `spend_type` ENUM('var','static') NOT NULL DEFAULT 'static' COMMENT '兑换积分是否固定',
  `level` TINYINT UNSIGNED NOT NULL COMMENT '需要的客户等级',
  `ctime` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  INDEX `cid_index` (`cid` ASC))
ENGINE = InnoDB
COMMENT = '权益';


-- -----------------------------------------------------
-- Table `bank`.`level`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`level` ;

CREATE TABLE IF NOT EXISTS `bank`.`level` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL COMMENT '级别名称',
  `value` TINYINT NOT NULL DEFAULT 0 COMMENT '级别',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_uniq` (`name` ASC))
ENGINE = InnoDB
COMMENT = '客户分级';


-- -----------------------------------------------------
-- Table `bank`.`planner_achivements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`planner_achivements` ;

CREATE TABLE IF NOT EXISTS `bank`.`planner_achivements` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NULL,
  `sales` DECIMAL(15,2) NULL COMMENT '年化销售额',
  `time_frame` VARCHAR(45) NULL COMMENT '时间段',
  `ctime` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `uid_index` (`uid` ASC))
ENGINE = InnoDB
COMMENT = '理财师业绩';


-- -----------------------------------------------------
-- Table `bank`.`activity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`activity` ;

CREATE TABLE IF NOT EXISTS `bank`.`activity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL COMMENT '活动名称',
  `content` TEXT NULL COMMENT '活动内容',
  `address` VARCHAR(45) NULL COMMENT '活动地点',
  `begin_time` DATE NULL COMMENT '开始时间',
  `end_time` DATE NULL COMMENT '结束时间',
  `memo` VARCHAR(45) NULL COMMENT '注意事项',
  `sponsor` VARCHAR(45) NULL COMMENT '活动主办方',
  `area_id` INT NULL,
  `ctime` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `area` (`area_id` ASC))
ENGINE = InnoDB
COMMENT = '活动';


-- -----------------------------------------------------
-- Table `bank`.`report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`report` ;

CREATE TABLE IF NOT EXISTS `bank`.`report` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cid` INT NULL COMMENT '分类',
  `name` VARCHAR(45) NOT NULL COMMENT '名称',
  `desc` TEXT NULL COMMENT '摘要',
  `url` VARCHAR(200) NULL COMMENT '投研报告外部url',
  `ctime` DATETIME NOT NULL,
  `is_del` TINYINT NOT NULL DEFAULT 0 COMMENT '1表示删除',
  PRIMARY KEY (`id`),
  INDEX `category` (`cid` ASC))
ENGINE = InnoDB
COMMENT = '投研报告';


-- -----------------------------------------------------
-- Table `bank`.`focus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`focus` ;

CREATE TABLE IF NOT EXISTS `bank`.`focus` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NULL,
  `fid` INT NULL,
  `ftype` ENUM('product','report','rights','other') NULL DEFAULT 'other' COMMENT '关注类型：产品|投研报告|权益|其他',
  `ctime` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `uid_index` (`uid` ASC),
  INDEX `ftype_index` (`ftype` ASC))
ENGINE = InnoDB
COMMENT = '客户关注';


-- -----------------------------------------------------
-- Table `bank`.`assets_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`assets_history` ;

CREATE TABLE IF NOT EXISTS `bank`.`assets_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `type` ENUM('dividend','purchase','renew','redemption') NOT NULL DEFAULT 'purchase',
  `ctime` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `customer_index` (`customer_id` ASC))
ENGINE = InnoDB
COMMENT = '客户资产配置变动';


-- -----------------------------------------------------
-- Table `bank`.`score_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`score_history` ;

CREATE TABLE IF NOT EXISTS `bank`.`score_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `score` INT NOT NULL COMMENT '积分数值，扣件为负数，增加为正数',
  `product_id` INT NULL COMMENT '获得积分来源的产品id',
  `status` ENUM('add','consume','frozen','expire') NOT NULL DEFAULT 'frozen',
  `operator_type` ENUM('user','admin') NOT NULL COMMENT '操作触发人员类型:用户|管理员',
  `operator_id` INT NOT NULL COMMENT '操作人id',
  `detail` VARCHAR(45) NULL COMMENT '积分变动描述',
  `from_type` ENUM('activity','product','other') NOT NULL DEFAULT 'other' COMMENT '积分变动来源',
  `vaild_time` DATE NULL COMMENT '积分有效时间',
  `ctime` DATETIME NOT NULL COMMENT '记录创建时间',
  `is_vaild` TINYINT NOT NULL DEFAULT 1 COMMENT '有效性',
  PRIMARY KEY (`id`),
  INDEX `uid_index` (`uid` ASC),
  INDEX `product_index` (`product_id` ASC))
ENGINE = InnoDB
COMMENT = '积分历史表';


-- -----------------------------------------------------
-- Table `bank`.`contract`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`contract` ;

CREATE TABLE IF NOT EXISTS `bank`.`contract` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `serial` VARCHAR(45) NOT NULL COMMENT '产品合同编号',
  `product_id` INT NOT NULL,
  `customer_id` INT NOT NULL COMMENT '客户id',
  `planner_id` INT NULL COMMENT '理财师id',
  `buy_time` DATE NOT NULL COMMENT '到账日期',
  `amount` INT NOT NULL COMMENT '投资额',
  `money_type` ENUM('rmb','usd','yin') NOT NULL DEFAULT 'rmb' COMMENT '币种',
  `ctime` DATETIME NOT NULL,
  `period` VARCHAR(45) NULL COMMENT '投资期限',
  `invaild` TINYINT NOT NULL DEFAULT 1 COMMENT '合同有效性:1有效|0无效',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `serial_UNIQUE` (`serial` ASC),
  INDEX `product_index` (`product_id` ASC),
  INDEX `planner_index` (`planner_id` ASC))
ENGINE = InnoDB
COMMENT = '客户合同表';


-- -----------------------------------------------------
-- Table `bank`.`admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`admin` ;

CREATE TABLE IF NOT EXISTS `bank`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `realname` VARCHAR(45) NULL,
  `role` INT NOT NULL,
  `login_ip` VARCHAR(45) NULL,
  `last_login_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_uniq` (`login` ASC))
ENGINE = InnoDB
COMMENT = '后台管理用户表';


-- -----------------------------------------------------
-- Table `bank`.`provider`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`provider` ;

CREATE TABLE IF NOT EXISTS `bank`.`provider` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `desc` VARCHAR(45) NULL COMMENT '描述',
  `area_id` INT NULL,
  `ctime` DATETIME NOT NULL COMMENT '添加时间',
  `level` TINYINT NULL COMMENT '需要的客户等级',
  PRIMARY KEY (`id`),
  INDEX `area_index` (`area_id` ASC),
  UNIQUE INDEX `name_uniq` (`name` ASC))
ENGINE = InnoDB
COMMENT = '供应商';


-- -----------------------------------------------------
-- Table `bank`.`areas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`areas` ;

CREATE TABLE IF NOT EXISTS `bank`.`areas` (
  `area_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` INT(10) UNSIGNED NOT NULL COMMENT '上一级的id值',
  `area_name` VARCHAR(50) NOT NULL COMMENT '地区名称',
  `sort` INT(10) UNSIGNED NOT NULL DEFAULT '99' COMMENT '排序',
  PRIMARY KEY (`area_id`),
  INDEX `parent_id` (`parent_id` ASC),
  INDEX `sort` (`sort` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 659004403
DEFAULT CHARACTER SET = utf8
COMMENT = '地区信息';


-- -----------------------------------------------------
-- Table `bank`.`activity_apply`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`activity_apply` ;

CREATE TABLE IF NOT EXISTS `bank`.`activity_apply` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `activity_id` INT NOT NULL,
  `customer_id` VARCHAR(45) NOT NULL,
  `planner_id` VARCHAR(45) NULL,
  `type` ENUM('self','invite') NOT NULL DEFAULT 'self' COMMENT 'self:客户主动参与；invite:理财师邀请',
  `result` VARCHAR(45) NULL,
  `ctime` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `activity_index` (`activity_id` ASC),
  INDEX `planner_index` (`planner_id` ASC),
  INDEX `type_index` (`type` ASC))
ENGINE = InnoDB
COMMENT = '活动邀请';


-- -----------------------------------------------------
-- Table `bank`.`planner_service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`planner_service` ;

CREATE TABLE IF NOT EXISTS `bank`.`planner_service` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `planner_id` INT NULL,
  `customer_id` INT NULL,
  `ctime` DATETIME NULL,
  `status` ENUM('invaild','vaild') NULL DEFAULT 'vaild',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `planner_customer_uniq` (`planner_id` ASC, `customer_id` ASC))
ENGINE = InnoDB
COMMENT = '客户理财师关系表';


-- -----------------------------------------------------
-- Table `bank`.`product_reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`product_reservation` ;

CREATE TABLE IF NOT EXISTS `bank`.`product_reservation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `planner_id` INT NULL,
  `ctime` DATETIME NULL,
  `result` ENUM('cancel','success','failed','wait') NULL DEFAULT 'wait' COMMENT '预约状态：取消\\成功\\失败\\等待',
  PRIMARY KEY (`id`),
  INDEX `customer_index` (`customer_id` ASC))
ENGINE = InnoDB
COMMENT = '产品预约';


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


-- -----------------------------------------------------
-- Table `bank`.`admin_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`admin_role` ;

CREATE TABLE IF NOT EXISTS `bank`.`admin_role` (
  `role_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` VARCHAR(255) NOT NULL COMMENT '管理组角色名称',
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `name_uniq` (`role_name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bank`.`rights_provider`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`rights_provider` ;

CREATE TABLE IF NOT EXISTS `bank`.`rights_provider` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rights_id` INT NULL COMMENT '权益id',
  `provider_id` INT NULL COMMENT '供应商id',
  `ctime` DATETIME NULL COMMENT '创建时间',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bank`.`system_module`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`system_module` ;

CREATE TABLE IF NOT EXISTS `bank`.`system_module` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL COMMENT '模块名称',
  `url` VARCHAR(50) NOT NULL COMMENT '功能资源',
  `desc` VARCHAR(45) NULL COMMENT '描述',
  `parent_module_id` INT NOT NULL COMMENT '父级资源',
  `level` TINYINT(1) NULL COMMENT '资源级别',
  `is_valid` TINYINT(1) NOT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `url_uniq` (`url` ASC))
ENGINE = InnoDB
COMMENT = '后台系统模块名称';


-- -----------------------------------------------------
-- Table `bank`.`system_admin_module`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`system_admin_module` ;

CREATE TABLE IF NOT EXISTS `bank`.`system_admin_module` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `admin_id` INT NOT NULL COMMENT '后台人员id',
  `module_id` INT NOT NULL COMMENT '模块id',
  `mode` ENUM('r','rw') NOT NULL DEFAULT 'r' COMMENT '模块读\\读写',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `admin_module_uniq` (`admin_id` ASC, `module_id` ASC))
ENGINE = InnoDB
COMMENT = '后台人员权限';


-- -----------------------------------------------------
-- Table `bank`.`rights_reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`rights_reservation` ;

CREATE TABLE IF NOT EXISTS `bank`.`rights_reservation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL COMMENT '客户id',
  `planner_id` INT NULL COMMENT '理财师id',
  `ctime` DATETIME NOT NULL COMMENT '预约时间',
  `detail` VARCHAR(45) NULL COMMENT '预约详情',
  `score_cost` VARCHAR(45) NULL COMMENT '积分花费',
  `memo` VARCHAR(200) NULL COMMENT '备注',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '客户权益预约';


-- -----------------------------------------------------
-- Table `bank`.`planner_areas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`planner_areas` ;

CREATE TABLE IF NOT EXISTS `bank`.`planner_areas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `planner_id` INT NULL COMMENT '理财师id',
  `area_id` INT NULL COMMENT '区域id',
  `ctime` DATETIME NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  INDEX `area_index` (`area_id` ASC),
  INDEX `planner_index` (`planner_id` ASC))
ENGINE = InnoDB
COMMENT = '理财师地理权限范围';


-- -----------------------------------------------------
-- Table `bank`.`user_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`user_log` ;

CREATE TABLE IF NOT EXISTS `bank`.`user_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NULL COMMENT '用户id',
  `login_role` ENUM('customer','planner') NULL DEFAULT 'customer' COMMENT '登陆用户类型',
  `action` VARCHAR(45) NULL,
  `ctime` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `uid_index` (`uid` ASC))
ENGINE = InnoDB
COMMENT = '手机登陆用户操作存档';


-- -----------------------------------------------------
-- Table `bank`.`passport`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`passport` ;

CREATE TABLE IF NOT EXISTS `bank`.`passport` (
  `passport_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL COMMENT '证件名称',
  `id_del` TINYINT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`passport_id`),
  UNIQUE INDEX `name_uniq` (`name` ASC))
ENGINE = InnoDB
COMMENT = '证件类型';


-- -----------------------------------------------------
-- Table `bank`.`product_areas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`product_areas` ;

CREATE TABLE IF NOT EXISTS `bank`.`product_areas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` VARCHAR(45) NULL,
  `area_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `area_index` (`area_id` ASC))
ENGINE = InnoDB
COMMENT = '产品发行区域';


-- -----------------------------------------------------
-- Table `bank`.`report_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`report_category` ;

CREATE TABLE IF NOT EXISTS `bank`.`report_category` (
  `cid` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE INDEX `name_uniq` (`name` ASC))
ENGINE = InnoDB
COMMENT = '投研报告分类';


-- -----------------------------------------------------
-- Table `bank`.`contract_tmp`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`contract_tmp` ;

CREATE TABLE IF NOT EXISTS `bank`.`contract_tmp` (
  `id` INT NOT NULL,
  `code` VARCHAR(45) NOT NULL COMMENT '合同编号',
  `customer_name` VARCHAR(45) NOT NULL COMMENT '客户姓名',
  `ctime` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_uniq` (`code` ASC))
ENGINE = InnoDB
COMMENT = '无身份证信息的合同临时表';


-- -----------------------------------------------------
-- Table `bank`.`rights_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`rights_category` ;

CREATE TABLE IF NOT EXISTS `bank`.`rights_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `ctime` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_uniq` (`name` ASC))
ENGINE = InnoDB
COMMENT = '权益分类';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
