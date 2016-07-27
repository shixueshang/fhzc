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
  `avatar` VARCHAR(255) NULL COMMENT '用户图像',
  `passport_type_id` INT NULL COMMENT '证件类型',
  `passport_code` VARCHAR(45) NULL COMMENT '证件号码（加密）',
  `passport_agent` VARCHAR(45) NULL COMMENT '发证机关（加密）',
  `passport_address` VARCHAR(45) NULL COMMENT '证件地址（加密）',
  `passport_expire` VARCHAR(45) NULL COMMENT '证件有效期',
  `mobile` VARCHAR(45) NULL COMMENT '移动电话',
  `phone` VARCHAR(45) NULL COMMENT '固定电话',
  `phone_ext` VARCHAR(45) NULL COMMENT '其他电话',
  `email` VARCHAR(45) NULL COMMENT '邮箱',
  `address` VARCHAR(200) NULL COMMENT '通信地址（加密）',
  `login_role` ENUM('customer','planner') NOT NULL COMMENT '登陆角色（客户、理财师）',
  `device_uuid` VARCHAR(45) NULL COMMENT '登陆设备uuid',
  `area_id` INT NULL COMMENT '区域id',
  `is_del` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除',
  `ctime` DATETIME NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE INDEX `login` (`login` ASC),
  INDEX `area` (`area_id` ASC),
  UNIQUE INDEX `uniq_mobile` (`mobile` ASC))
  ENGINE = InnoDB
  COMMENT = 'App登录用户总表';


-- -----------------------------------------------------
-- Table `bank`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`product` ;

CREATE TABLE IF NOT EXISTS `bank`.`product` (
  `pid` INT NOT NULL AUTO_INCREMENT,
  `status` TINYINT NOT NULL DEFAULT -1 COMMENT '基金状态 -1未知|0产品预热|1募集中|2募集结束|3募集失败|4产品成立|5产品到期|6提前结束',
  `code` VARCHAR(45) NOT NULL COMMENT '产品代码',
  `name` VARCHAR(45) NOT NULL COMMENT '产品名称',
  `found_day` DATE NOT NULL COMMENT '成立日',
  `value_day` DATE NOT NULL COMMENT '起息日',
  `expiry_day` DATE NOT NULL COMMENT '到期日',
  `issue_type` INT(1) NOT NULL COMMENT '发行模式',
  `product_type` VARCHAR(200) NOT NULL COMMENT '产品类型',
  `renew_deadline` VARCHAR(200) NULL COMMENT '产品续存期限',
  `dividend_day` VARCHAR(200) NULL COMMENT '派息日期',
  `annual_yield` VARCHAR(200) NULL COMMENT '年化收益率',
  `income_distribution_type` VARCHAR(200) NULL COMMENT '收益分配方式',
  `investment_orientation` VARCHAR(200) NULL COMMENT '投资方向',
  `increase_trust` VARCHAR(200) NULL COMMENT '增信措施',
  `highlights` VARCHAR(200) NULL COMMENT '产品亮点',
  `fund_management_fee` VARCHAR(45) NULL COMMENT '基金管理费',
  `fund_subscription_fee` VARCHAR(45) NULL COMMENT '基金认购费',
  `fund_manager` VARCHAR(45) NULL COMMENT '基金管理人',
  `custodian` VARCHAR(45) NULL COMMENT '基金托管人',
  `investment_type` VARCHAR(45) NULL COMMENT '投资类型',
  `project_source` VARCHAR(45) NULL COMMENT '项目来源',
  `issuer` VARCHAR(45) NULL COMMENT '发行公司',
  `is_record` VARCHAR(45) NOT NULL DEFAULT 0 COMMENT '备案与否 0否|1是',
  `prove_url` VARCHAR(255) NULL COMMENT '备案证明文件存储地址',
  `display_order` INT NULL COMMENT '显示顺序',
  `level` INT NULL COMMENT '允许购买的客户等级',
  `risk` INT NULL COMMENT '能接受的客户风险等级',
  `cover` VARCHAR(255) NULL COMMENT '产品封面',
  `notice` VARCHAR(255) NULL COMMENT '产品成立公告',
  `score_factor` TINYINT UNSIGNED NOT NULL DEFAULT 100 COMMENT '产品积分系数百分比,默认100%',
  `is_ recommend` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否推荐 1是|0否',
  `is_display` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '产品是否显示 1显示|0不显示',
  `is_renew` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否是续存期产品 0否|1是',
  `desc` TEXT NULL COMMENT '产品简介',
  `ctime` DATETIME NULL COMMENT '记录增加时间',
  PRIMARY KEY (`pid`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC))
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
  `admin_id` INT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  COMMENT = '后台系统变更日志';


-- -----------------------------------------------------
-- Table `bank`.`planner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`planner` ;

CREATE TABLE IF NOT EXISTS `bank`.`planner` (
  `id` INT NOT NULL,
  `uid` INT NOT NULL,
  `work_num` VARCHAR(45) NOT NULL COMMENT '工号',
  `department_id` INT NULL COMMENT '所属分公司',
  `avatar` VARCHAR(255) NULL COMMENT '头像',
  `status` ENUM('on','off') NOT NULL DEFAULT 'on' COMMENT '理财师状态,on在职,off离职',
  `role_id` INT NULL COMMENT '理财师角色',
  `entry_time` DATE NULL COMMENT '入职时间',
  `leave_time` DATE NULL COMMENT '离职时间',
  `md_uid` INT NULL COMMENT '归属市场总监',
  `sub_mg_uid` INT NULL COMMENT '归属副分总',
  `mg_uid` INT NULL COMMENT '归属分总',
  `area_uid` INT NULL COMMENT '归属区总',
  `dept_1` INT NULL COMMENT '一级部门',
  `dept_2` INT NULL COMMENT '二级部门',
  `dept_3` INT NULL COMMENT '三级部门',
  `dept_4` INT NULL,
  `job_title_cn` VARCHAR(45) NULL COMMENT '岗位名称',
  `position` VARCHAR(45) NULL COMMENT '岗位序列',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `work_num_uniq` (`work_num` ASC),
  INDEX `dept_index` (`department_id` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bank`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`customer` ;

CREATE TABLE IF NOT EXISTS `bank`.`customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `cb_id` INT NULL COMMENT '客户号（来自excel）',
  `level_id` INT NULL,
  `risk` VARCHAR(45) NULL,
  `department_id` INT NOT NULL COMMENT '客户所属分公司',
  `bank_info_id` INT NULL COMMENT '银行信息id',
  PRIMARY KEY (`customer_id`),
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
  `name` VARCHAR(45) NOT NULL COMMENT '会员等级名称：投资人，准客户，蓝卡，银卡，金卡，白金卡，XX卡',
  `value` TINYINT NOT NULL DEFAULT 0 COMMENT '级别',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_uniq` (`name` ASC))
  ENGINE = InnoDB
  COMMENT = '客户分级';


-- -----------------------------------------------------
-- Table `bank`.`planner_achivements_monthly`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`planner_achivements_monthly` ;

CREATE TABLE IF NOT EXISTS `bank`.`planner_achivements_monthly` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `planner_uid` INT NOT NULL COMMENT '理财规划师uid',
  `planner_percent` VARCHAR(5) NOT NULL COMMENT '理财师分单占比',
  `manager_uid` INT NULL COMMENT '客户经理uid',
  `mannager_percent` VARCHAR(45) NULL COMMENT '客户经理分单占比',
  `product_id` INT NOT NULL COMMENT '产品名称',
  `product_type` VARCHAR(45) NULL,
  `customer_uid` INT NOT NULL COMMENT '认购人uid',
  `customer_buy` INT NOT NULL COMMENT '认购金额',
  `annualised` INT NULL COMMENT '年化金额',
  `product_cycle` INT NULL COMMENT '产品周期（月）',
  `transfer_date` DATE NULL COMMENT '划款到账日期',
  `memo` VARCHAR(255) NULL COMMENT '备注',
  `ctime` DATETIME NOT NULL,
  `area_id` INT NULL COMMENT '地区',
  PRIMARY KEY (`id`),
  INDEX `uid_index` (`planner_uid` ASC))
  ENGINE = InnoDB
  COMMENT = '理财师每月确认业绩';


-- -----------------------------------------------------
-- Table `bank`.`activity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`activity` ;

CREATE TABLE IF NOT EXISTS `bank`.`activity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL COMMENT '活动名称(标题）',
  `content` TEXT NULL COMMENT '活动内容',
  `address` VARCHAR(45) NULL COMMENT '活动地点',
  `apply_begin_time` DATETIME NULL COMMENT '报名开始时间',
  `apply_end_time` DATETIME NULL COMMENT '报名结束时间',
  `begin_time` DATE NULL COMMENT '活动开始时间',
  `end_time` DATE NULL COMMENT '活动结束时间',
  `memo` VARCHAR(255) NULL COMMENT '注意事项',
  `sponsor` VARCHAR(45) NULL COMMENT '活动主办方',
  `area_id` INT NULL,
  `ctime` DATETIME NOT NULL,
  `url` VARCHAR(255) NULL COMMENT '活动链接',
  `status` INT(1) NOT NULL DEFAULT 0 COMMENT '活动状态 0预约中|1已完成',
  `is_display` INT(1) NOT NULL DEFAULT 0 COMMENT '是否显示 0否|1是',
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
  `cover` VARCHAR(200) NULL COMMENT '封面图',
  `summary` TEXT NULL COMMENT '摘要',
  `url` VARCHAR(255) NULL COMMENT '投研报告外部url',
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
  `is_approve` TINYINT NOT NULL DEFAULT 0 COMMENT '审批状态',
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
  `serial` VARCHAR(45) NOT NULL COMMENT '合同编号',
  `product_id` INT NOT NULL,
  `customer_id` INT NOT NULL COMMENT '客户id',
  `planner_id` INT NULL COMMENT '理财师id',
  `buy_time` DATE NOT NULL COMMENT '到账日期',
  `amount_usd` INT NOT NULL COMMENT '投资额（美元）',
  `amount_rmb` INT NOT NULL COMMENT '投资额（人民币）',
  `annualised` INT NULL COMMENT '年化金额',
  `period` VARCHAR(45) NOT NULL COMMENT '投资期限',
  `invaild` TINYINT NOT NULL DEFAULT 1 COMMENT '合同有效性:1有效|0无效',
  `product_found_day` DATE NULL COMMENT '产品成立日期',
  `value_date` DATE NULL COMMENT '起息日',
  `product_expire_day` DATE NULL COMMENT '产品到期日期',
  `bank` VARCHAR(45) NOT NULL COMMENT '开户银行信息',
  `bank_account` VARCHAR(45) NOT NULL COMMENT '银行账号',
  `lot` VARCHAR(45) NULL COMMENT '基金份额',
  `duration_month` INT NULL COMMENT '投资期限（月）',
  `duration_day` INT NULL COMMENT '投资期限（日）',
  `pub_agent` VARCHAR(45) NULL COMMENT '发行机构',
  `branch_agent` VARCHAR(45) NULL COMMENT '分支机构',
  `is_member` TINYINT NOT NULL DEFAULT 1 COMMENT '是否是会员 0否|1是',
  `memo` VARCHAR(45) NULL COMMENT '备注信息',
  `ctime` DATETIME NOT NULL,
  `earning_rate` VARCHAR(45) NULL COMMENT '收益率',
  `distribute_earning` VARCHAR(45) NULL COMMENT '分配收益',
  `payment` VARCHAR(45) NULL COMMENT '兑付合计',
  `has_passport` TINYINT NOT NULL DEFAULT 0 COMMENT '合同是否有身份证信息',
  PRIMARY KEY (`id`, `period`),
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
  `display_order` INT(10) UNSIGNED NOT NULL DEFAULT '99' COMMENT '排序',
  PRIMARY KEY (`area_id`),
  INDEX `parent_id` (`parent_id` ASC),
  INDEX `sort` (`display_order` ASC))
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
  `phone` VARCHAR(45) NOT NULL COMMENT '参加互动的客户电话（可以为别人）',
  `type` ENUM('self','invite') NOT NULL DEFAULT 'self' COMMENT 'self:客户主动参与；invite:理财师邀请',
  `result` TINYINT NOT NULL DEFAULT 0 COMMENT '是否参加 0否|1是',
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
  `is_main` TINYINT NOT NULL DEFAULT 0 COMMENT '是否为主理财师 0否|1是',
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
-- Table `bank`.`system_module`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`system_module` ;

CREATE TABLE IF NOT EXISTS `bank`.`system_module` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL COMMENT '模块名称',
  `url` VARCHAR(50) NOT NULL COMMENT '功能资源',
  `desc` VARCHAR(45) NULL COMMENT '描述',
  `parent_module_id` INT NULL COMMENT '父级资源',
  `level` INT(1) NULL COMMENT '资源级别',
  `is_valid` TINYINT(1) NOT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  COMMENT = '后台系统模块名称';


-- -----------------------------------------------------
-- Table `bank`.`system_admin_module`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`system_role_module` ;

CREATE TABLE IF NOT EXISTS `bank`.`system_role_module` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `admin_role_id` INT NOT NULL COMMENT '后台人员id',
  `module_id` INT NOT NULL COMMENT '模块id',
  `mode` ENUM('r','rw') NOT NULL DEFAULT 'rw' COMMENT '模块读\\读写',
  PRIMARY KEY (`id`))
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
  `mark_date` DATETIME NOT NULL COMMENT '预约时间',
  `detail` VARCHAR(45) NULL COMMENT '预约详情',
  `score_cost` VARCHAR(45) NULL COMMENT '积分花费',
  `status` TINYINT NULL COMMENT '预约状态 0预约中|1预约成功|2预约失败|3客户取消预约|4客户消费|5客户缺席',
  `memo` VARCHAR(200) NULL COMMENT '备注',
  `ctime` DATETIME NOT NULL COMMENT '记录添加时间',
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
-- Table `bank`.`planner_achivements_daily`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`planner_achivements_daily` ;

CREATE TABLE IF NOT EXISTS `bank`.`planner_achivements_daily` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `transfer_date` DATE NULL COMMENT '划款到账日期',
  `area_id` INT NULL COMMENT '地区',
  `planner_uid` INT NOT NULL COMMENT '理财规划师uid',
  `product_id` INT NOT NULL COMMENT '产品名称',
  `annualised` INT NULL COMMENT '年化金额',
  `contract_amount` INT NULL COMMENT '合同金额',
  `expire_date` DATE NULL,
  `product_type` VARCHAR(45) NULL COMMENT '产品类型',
  `memo` VARCHAR(255) NULL COMMENT '备注',
  `ctime` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  COMMENT = '理财师每日业绩动态';


-- -----------------------------------------------------
-- Table `bank`.`customer_bank`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`customer_bank` ;

CREATE TABLE IF NOT EXISTS `bank`.`customer_bank` (
  `bank_info_id` INT NOT NULL,
  `bank` VARCHAR(45) NOT NULL COMMENT '开户行',
  `bank_account` VARCHAR(45) NOT NULL COMMENT '银行卡号',
  `ctime` DATETIME NOT NULL COMMENT '记录添加时间',
  `is_del` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除 0否|1是',
  PRIMARY KEY (`bank_info_id`))
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `im_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `content` text DEFAULT NULL COMMENT '内容',
  `send_time` datetime NOT NULL COMMENT '发送时间',
  `session_id` varchar(50) NOT NULL COMMENT '聊天组唯一标识',
  `message_type` varchar(20) NOT NULL COMMENT '消息类型',
  `to_user_id` int(11) NOT NULL COMMENT '消息接收人',
  `is_read` tinyint(1) DEFAULT 0 COMMENT '是否已读',
  `duration` varchar(10) DEFAULT NULL COMMENT '语音长度',
  `mid` int(1) DEFAULT 0 COMMENT '每个聊天组每条信息的唯一标识',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT '消息表';


DROP TABLE IF EXISTS `bank`.`dictionary` ;

CREATE TABLE IF NOT EXISTS `bank`.`dictionary` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cat` VARCHAR(50) DEFAULT NULL COMMENT '字典分类【英】',
  `name` VARCHAR(50) DEFAULT NULL COMMENT '字典分类【中】',
  `key` VARCHAR(50) DEFAULT NULL COMMENT '键',
  `value` VARCHAR(50) DEFAULT NULL COMMENT '值',
  `is_default` INT(1) DEFAULT NULL COMMENT '是否默认 1、是，0、否',
  `status` INT(1) DEFAULT NULL COMMENT '状态',
  `is_modify` INT(1) DEFAULT NULL COMMENT '是否允许修改 1、是，0、否',
  `date_created` datetime COMMENT '创建时间',
  `last_updated` datetime comment '最后修改时间',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  COMMENT = '字典表';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

